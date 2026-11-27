package com.izivia.ocpp.websocket

import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.transport.ClientTransport
import com.izivia.ocpp.transport.RequestHeaders
import com.izivia.ocpp.utils.escapedForLog
import com.izivia.ocpp.utils.redactedForLog
import com.izivia.ocpp.wamp.client.OcppWampClient
import com.izivia.ocpp.wamp.client.OcppWampClientSettings
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import mu.KotlinLogging
import org.http4k.core.Uri
import java.net.ConnectException
import java.util.*
import kotlin.reflect.KClass

private val logger = KotlinLogging.logger {}

class WebsocketClient(ocppId: String,
                      ocppVersion: OcppVersion,
                      target: String,
                      headers: RequestHeaders = emptyList(),
                      val newMessageId: () -> String = { UUID.randomUUID().toString() },
                      settings: OcppWampClientSettings = OcppWampClientSettings(),
) : ClientTransport {
    private val client: OcppWampClient =
        OcppWampClient.newClient(Uri.of(target), ocppId, ocppVersion, settings, headers)
    private val parser = getJsonMapper(ocppVersion)
    private val wampMessageMeta = WampMessageMeta(ocppVersion, ocppId)

    override fun connect(): Unit = client.connect()

    override fun close(): Unit = client.close()

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun <T, P : Any> sendMessageClass(clazz: KClass<P>, action: String, message: T): P =
        try {
            val msgId: String = newMessageId()
            val response = client.sendBlocking(WampMessage.Call(msgId, action, parser.mapPayloadToString(message)))
            response.resultOf(msgId, action) { parser.parsePayloadFromJson(it, clazz) }
        } catch (e: Exception) {
            throw e
        }

    override fun <T : Any, P> receiveMessageClass(clazz: KClass<T>, action: String, fn: (T) -> P) {
        val handler: (WampMessageMeta, WampMessage) -> WampMessage? =
            { msgMeta: WampMessageMeta, wampMsg: WampMessage ->
                if (msgMeta == wampMessageMeta && wampMsg.action == action) {
                    try {
                        val response = fn(parser.parsePayloadFromJson(wampMsg.payload, clazz))
                        val payload = parser.mapPayloadToString(response)
                        WampMessage.CallResult(wampMsg.msgId, payload)
                    } catch (e: Exception) { // TODO Better mapping of exceptions https://izivia.atlassian.net/browse/IDEV-497
                        // the exception message may quote the payload: it is logged as a redacted copy, which keeps
                        // its class and stack trace. No error listener gets the cause on the client side.
                        logger.error(
                            "${e.javaClass.name} while handling ${wampMsg.action?.escapedForLog()} " +
                                "[msgId=${wampMsg.msgId.escapedForLog()}]",
                            e.redactedForLog()
                        )
                        internalErrorFor(wampMsg.msgId)
                    }
                } else {
                    null
                }
            }
        client.onAction(handler)
    }
}

