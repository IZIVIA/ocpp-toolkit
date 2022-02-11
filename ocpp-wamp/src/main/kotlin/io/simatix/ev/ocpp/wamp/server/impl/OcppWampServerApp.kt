package io.simatix.ev.ocpp.wamp.server.impl

import io.simatix.ev.ocpp.CSOcppId
import io.simatix.ev.ocpp.OcppVersion
import io.simatix.ev.ocpp.wamp.core.WampCallManager
import io.simatix.ev.ocpp.wamp.messages.WampMessage
import io.simatix.ev.ocpp.wamp.messages.WampMessageMeta
import io.simatix.ev.ocpp.wamp.messages.WampMessageType
import io.simatix.ev.ocpp.wamp.server.OcppWampServerHandler
import org.http4k.routing.bind
import org.http4k.routing.websockets
import org.http4k.websocket.Websocket
import org.http4k.websocket.WsMessage
import org.slf4j.LoggerFactory
import java.util.*

class OcppWampServerApp(val ocppVersions:Set<OcppVersion>,
                        private val handlers: (CSOcppId)->OcppWampServerHandler,
                        val timeoutInMs:Long) {
    companion object {
        private val logger = LoggerFactory.getLogger("io.simatix.ev.ocpp.wamp.server")
    }
    private val connections:MutableMap<String, ChargingStationConnection?> = mutableMapOf()

    private fun newConnection(ws: Websocket) {
        val wsConnectionId = UUID.randomUUID().toString()
        val chargingStationOcppId = OcppWsEndpoint.extractChargingStationOcppId(ws.upgradeRequest.uri.path)
            ?.takeUnless { it.isEmpty() }
            ?:throw IllegalArgumentException("malformed request - no chargingStationOcppId - ${ws.upgradeRequest}")
        val ocppVersion = ws.upgradeRequest.header("Sec-WebSocket-Protocol")
            ?.split(",")?.firstOrNull()?.trim()
            ?.let { ocppVersions.filter { v -> v.subprotocol == it.lowercase() }.firstOrNull() }
            ?:throw IllegalArgumentException("malformed request - unsupported or invalid ocpp version - ${ws.upgradeRequest}")
        val handler = handlers(chargingStationOcppId)

        if (connections[chargingStationOcppId] != null) {
            // already connected - refuse connection
            logger.warn("""[$chargingStationOcppId] already connected - refusing new connection """)
            ws.close()
            return
        }

        val chargingStationConnection = ChargingStationConnection(wsConnectionId, chargingStationOcppId, ws, timeoutInMs)
        connections[chargingStationOcppId] = chargingStationConnection
        ws.onClose {
            logger.info("""[$chargingStationOcppId] [$wsConnectionId] disconnected """)
            if (connections[chargingStationOcppId]?.wsConnectionId == wsConnectionId) {
                connections[chargingStationOcppId] = null
            } else {
                logger.info("warn: do not clear ws on close - not current connection in map")
            }
        }

        logger.info("""[$chargingStationOcppId] [$wsConnectionId] connected """)
        ws.onMessage {
            val msgString = it.bodyString()
            WampMessage.parse(msgString)?.also { msg ->
                when {
                    msg.msgType == WampMessageType.CALL -> {
                        logger.info("""[$chargingStationOcppId] [$wsConnectionId] -> ${it.bodyString()}""")
                        val resp = handler.onAction(WampMessageMeta(ocppVersion, chargingStationOcppId), msg)
                            ?: WampMessage.CallError(msg.msgId, "{}").also { logger.warn("no action handler found for $msg") }

                        logger.info("""[$chargingStationOcppId] [$wsConnectionId] <- ${resp.toJson()}""")
                        ws.send(WsMessage(resp.toJson()))
                    }
                    msg.msgType == WampMessageType.CALL_RESULT || msg.msgType == WampMessageType.CALL_ERROR -> {
                        chargingStationConnection.callManager.handleResult(
                            "[$chargingStationOcppId] [$wsConnectionId]", msg)
                    }
                    else ->
                        logger.warn("unsupported wamp message type: ${msg.msgType} - message = $msgString")
                }
            }
        }
    }

    fun sendBlocking(ocppId: CSOcppId, message: WampMessage): WampMessage? =
        getChargingStationConnection(ocppId).sendBlocking(message)

    private fun getChargingStationConnection(ocppId: CSOcppId): ChargingStationConnection {
        var backOffRetryMs = 10L;
        var backOffRetryAttempts = 5;
        var connection = connections[ocppId]
        while (connection == null && backOffRetryAttempts > 0) {
            Thread.sleep(backOffRetryMs)
            backOffRetryAttempts--
            backOffRetryMs *= 2
            connection = connections[ocppId]
        }
        return connection ?: throw IllegalStateException("no connection to $ocppId")
    }

    fun newRoutingHandler() = websockets(OcppWsEndpoint.uriTemplate.toString() bind ::newConnection)

    private data class ChargingStationConnection(val wsConnectionId:String,
                                                 val ocppId:CSOcppId, val ws: Websocket,
                                                 val timeoutInMs:Long) {
        val callManager:WampCallManager = WampCallManager(logger, ws, timeoutInMs)

        fun sendBlocking(message: WampMessage): WampMessage =
            callManager.callBlocking("[$ocppId] [$wsConnectionId]", message)
    }
}
