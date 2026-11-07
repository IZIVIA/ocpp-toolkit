package com.izivia.ocpp.websocket

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.utils.OcppErrorCategory
import com.izivia.ocpp.utils.OcppErrorEvent
import com.izivia.ocpp.utils.OcppErrorListener
import com.izivia.ocpp.utils.notifySafely
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.transport.OcppCallErrorException
import com.izivia.ocpp.transport.OcppCallErrorPayload
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.transport.ServerTransport
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.messages.WampMessageType
import com.izivia.ocpp.wamp.server.OcppWampServer
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import com.izivia.ocpp.wamp.server.asServer
import com.izivia.ocpp.wamp.server.impl.EventsListeners
import com.izivia.ocpp.wamp.server.impl.OcppWampServerSettings
import mu.KotlinLogging
import org.http4k.server.Http4kServer
import java.util.*
import kotlin.reflect.KClass
import com.izivia.ocpp.OcppVersion as OcppVersionWamp

private val logger = KotlinLogging.logger {}

class WebsocketServer(
    ocppVersions: Set<OcppVersion>,
    path: String,
    val newMessageId: () -> String = { UUID.randomUUID().toString() },
    settings: OcppWampServerSettings = OcppWampServerSettings(),
    listeners: EventsListeners = EventsListeners(),
    private val errorListener: OcppErrorListener? = null,
) : ServerTransport {

    internal val server: OcppWampServer =
        OcppWampServer.newServer(
            ocppVersions = ocppVersions.map { OcppVersionWamp.valueOf(it.name) }.toSet(),
            path = path,
            settings = settings,
            listeners = listeners,
            errorListener = errorListener
        )
    val serverConfig = server.config()

    override fun <T, P : Any> sendMessageClass(clazz: KClass<P>, csOcppId: String, action: String, message: T): P =
        try {
            val msgId: String = newMessageId()
            val version = server.getChargingStationOcppVersion(csOcppId)
            val parser = getJsonMapper(version)
            val response =
                server.sendBlocking(csOcppId, WampMessage.Call(msgId, action, parser.mapPayloadToString(message)))
            if (response.msgId != msgId) {
                throw IllegalStateException(
                    "Wrong response received : ${response.msgId} received, $msgId expected\n"
                            + "Request : $message\n" + "Response : ${response.payload}"
                )
            }
            when (response.msgType) {
                WampMessageType.CALL_RESULT -> parser.parsePayloadFromJson(response.payload, clazz)
                WampMessageType.CALL_ERROR -> throw OcppCallErrorException(response.payload)
                else -> throw IllegalStateException(
                    "The message received type ${response.msgType} is not the one expected\n"
                            + "Request : $message\n" + "Response : ${response.payload}"
                )
            }
        } catch (e: Exception) {
            throw e
        }

    override fun <T : Any, P> receiveMessageClass(
        clazz: KClass<T>, action: String, ocppVersion: OcppVersion,
        onAction: (RequestMetadata, T) -> P, accept: (CSOcppId) -> ChargingStationConfig
    ) {
        server.register(handler = object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId): Boolean = accept(ocppId).acceptConnection

            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? =
                if (meta.ocppVersion == OcppVersionWamp.valueOf(ocppVersion.name) && msg.action == action) {
                    val parser = getJsonMapper(OcppVersionWamp.valueOf(ocppVersion.name))
                    var failureCategory = OcppErrorCategory.PROTOCOL_ERROR
                    try {
                        val request = parser.parsePayloadFromJson(msg.payload, clazz)
                        failureCategory = OcppErrorCategory.HANDLER_ERROR
                        val response = onAction(RequestMetadata(meta.ocppId, msg.msgId), request)
                        failureCategory = OcppErrorCategory.PROTOCOL_ERROR
                        val payload = parser.mapPayloadToString(response)
                        WampMessage.CallResult(msg.msgId, payload)
                    } catch (e: Exception) { // TODO Better mapping of exceptions https://izivia.atlassian.net/browse/IDEV-497
                        logger.error(e.message)
                        errorListener?.notifySafely(
                            OcppErrorEvent(
                                failureCategory,
                                meta.ocppId,
                                meta.ocppVersion.subprotocol,
                                msg.action,
                                msg.msgId,
                                MessageErrorCode.INTERNAL_ERROR,
                                e
                            )
                        )
                        WampMessage.CallError(
                            msgId = msg.msgId,
                            errorCode = MessageErrorCode.INTERNAL_ERROR,
                            errorDescription = "",
                            payload = OcppCallErrorPayload(e.message).toJson(parser)
                        )
                    }
                } else {
                    null
                }
        })
    }

    override fun canSendToChargingStation(chargingStationConfig: ChargingStationConfig): Boolean =
        chargingStationConfig.acceptConnection

    override fun shutdown() = server.shutdown()
}

fun WebsocketServer.asServer(port: Int = 8000): Http4kServer = server.asServer(port)
