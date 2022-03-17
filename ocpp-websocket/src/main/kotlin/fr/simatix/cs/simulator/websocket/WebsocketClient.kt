package fr.simatix.cs.simulator.websocket

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import fr.simatix.cs.simulator.transport.Transport
import io.simatix.ev.ocpp.OcppVersion
import io.simatix.ev.ocpp.wamp.client.OcppWampClient
import io.simatix.ev.ocpp.wamp.messages.WampMessage
import io.simatix.ev.ocpp.wamp.messages.WampMessageMeta
import io.simatix.ev.ocpp.wamp.messages.WampMessageType
import org.http4k.core.Uri
import java.net.ConnectException
import java.util.*
import kotlin.reflect.KClass

class WebsocketClient(ocppId: String, ocppVersion: OcppVersion, target: String) : Transport {

    private val client: OcppWampClient =
        OcppWampClient.newClient(Uri.of(target), ocppId, ocppVersion)
    private val mapper = jacksonObjectMapper()
    private val wampMessageMeta = WampMessageMeta(ocppVersion, ocppId)

    override fun connect(): Unit = client.connect()

    override fun close(): Unit = client.close()

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun <T, P : Any> sendMessageClass(clazz: KClass<P>, action: String, message: T): P =
        try {
            val msgId: String = UUID.randomUUID().toString()
            val response = client.sendBlocking(WampMessage.Call(msgId, action, mapper.writeValueAsString(message)))
            if (response.msgId != msgId) {
                throw IllegalStateException("Wrong response received : ${response.msgId} received, $msgId expected")
            }
            mapper.readValue(response.payload, clazz.java)
        } catch (e: Exception) {
            throw e
        }

    override fun <T : Any, P> receiveMessageClass(clazz: KClass<T>, action: String, fn: (T) -> P) {
        val handler: (WampMessageMeta, WampMessage) -> WampMessage? = { msgMeta: WampMessageMeta, wampMsg: WampMessage ->
            if (msgMeta == wampMessageMeta && wampMsg.action == action) {
                val response = fn(mapper.readValue(wampMsg.payload, clazz.java))
                val payload = mapper.writeValueAsString(response)
                WampMessage(WampMessageType.CALL_RESULT, wampMsg.msgId, wampMsg.action, payload)
            } else {
                null
            }
        }
        client.onAction(handler)
    }
}

