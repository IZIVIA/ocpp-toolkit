package com.izivia.ocpp.websocket

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.izivia.ocpp.transport.ClientTransport
import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.wamp.client.OcppWampClient
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.messages.WampMessageType
import org.http4k.core.Uri
import java.net.ConnectException
import java.util.*
import kotlin.reflect.KClass

class WebsocketClient(ocppId: String, ocppVersion: OcppVersion, target: String) : ClientTransport {

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
            when (response.msgType) {
                WampMessageType.CALL_RESULT -> mapper.readValue(response.payload, clazz.java)
                WampMessageType.CALL_ERROR -> throw IllegalStateException("$response")
                else -> throw IllegalStateException("The message received type ${response.msgType} is not the one expected")
            }
        } catch (e: Exception) {
            throw e
        }

    override fun <T : Any, P> receiveMessageClass(clazz: KClass<T>, action: String, fn: (T) -> P) {
        val handler: (WampMessageMeta, WampMessage) -> WampMessage? =
            { msgMeta: WampMessageMeta, wampMsg: WampMessage ->
                if (msgMeta == wampMessageMeta && wampMsg.action == action) {
                    try {
                        val response = fn(mapper.readValue(wampMsg.payload, clazz.java))
                        val payload = mapper.writeValueAsString(response)
                        WampMessage(WampMessageType.CALL_RESULT, wampMsg.msgId, null, payload)
                    } catch (e: Exception) {
                        WampMessage(WampMessageType.CALL_ERROR, wampMsg.msgId, null, "{}")
                    }
                } else {
                    null
                }
            }
        client.onAction(handler)
    }
}

