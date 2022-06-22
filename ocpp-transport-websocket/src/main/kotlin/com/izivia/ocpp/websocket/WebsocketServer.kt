package com.izivia.ocpp.websocket

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.transport.ServerTransport
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.messages.WampMessageType
import com.izivia.ocpp.wamp.server.OcppWampServer
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import com.izivia.ocpp.OcppVersion as OcppVersionWamp
import java.util.*
import kotlin.reflect.KClass

class WebsocketServer(port: Int, ocppVersions: Set<OcppVersion>) : ServerTransport {

    private val server: OcppWampServer =
        OcppWampServer.newServer(port, ocppVersions.map { OcppVersionWamp.valueOf(it.name) }.toSet())
    private val mapper = jacksonObjectMapper()

    override fun start(): Unit = server.start()

    override fun stop(): Unit = server.stop()

    override fun <T, P : Any> sendMessageClass(clazz: KClass<P>, csOcppId: String, action: String, message: T): P =
        try {
            val msgId: String = UUID.randomUUID().toString()
            val response = server.sendBlocking(csOcppId, WampMessage.Call(msgId, action, mapper.writeValueAsString(message)))
            if (response.msgId != msgId) {
                throw IllegalStateException("Wrong response received : ${response.msgId} received, $msgId expected")
            }
            mapper.readValue(response.payload, clazz.java)
        } catch (e: Exception) {
            throw e
        }

    override fun <T : Any, P> receiveMessageClass(clazz: KClass<T>, action: String, ocppVersion: OcppVersion,
                                                  onAction: (RequestMetadata, T) -> P, accept: (CSOcppId) -> Boolean) {
        server.register(handler = object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId): Boolean = accept(ocppId)

            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? =
                if (meta.ocppVersion == OcppVersionWamp.valueOf(ocppVersion.name) && msg.action == action) {
                    val response = onAction(RequestMetadata(meta.ocppId), mapper.readValue(msg.payload, clazz.java))
                    val payload = mapper.writeValueAsString(response)
                    WampMessage(WampMessageType.CALL_RESULT, msg.msgId, null, payload)
                } else {
                    null
                }
        })
    }
}

