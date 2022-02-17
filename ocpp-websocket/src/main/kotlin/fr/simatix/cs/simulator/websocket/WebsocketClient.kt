package fr.simatix.cs.simulator.websocket

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import fr.simatix.cs.simulator.transport.Transport
import io.simatix.ev.ocpp.OcppVersion
import io.simatix.ev.ocpp.wamp.client.OcppWampClient
import io.simatix.ev.ocpp.wamp.client.impl.OcppWampClientImpl
import io.simatix.ev.ocpp.wamp.messages.WampMessage
import org.http4k.core.Uri
import java.net.ConnectException
import java.util.*
import kotlin.reflect.KClass

class WebsocketClient(ocppId: String, ocppVersion: OcppVersion) : Transport {

    private val client: OcppWampClientImpl =
        OcppWampClient.newClient(Uri.of(Target.target), ocppId, ocppVersion)
    private val mapper = jacksonObjectMapper()

    override fun connect() {
        client.connect()
    }

    override fun close() {
        client.close()
    }

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun <T, P : Any> sendMessage(clazz: KClass<P>, action: String, message: T): P {
        val msgId: String = UUID.randomUUID().toString()
        return try {
            connect()
            val response = client.sendBlocking(WampMessage.Call(msgId, action, mapper.writeValueAsString(message)))
            if (response.msgId != msgId) {
                throw IllegalStateException("Wrong response received")
            }
            mapper.readValue(response.payload, clazz.java)
        } catch (e: Exception) {
            throw e
        } finally {
            close()
        }
    }
}

