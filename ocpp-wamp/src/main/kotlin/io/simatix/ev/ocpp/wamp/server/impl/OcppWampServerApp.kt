package io.simatix.ev.ocpp.wamp.server.impl

import io.simatix.ev.ocpp.CSOcppId
import io.simatix.ev.ocpp.OcppVersion
import io.simatix.ev.ocpp.wamp.messages.WampMessage
import io.simatix.ev.ocpp.wamp.messages.WampMessageMeta
import io.simatix.ev.ocpp.wamp.messages.WampMessageType
import io.simatix.ev.ocpp.wamp.server.OcppWampServerHandler
import org.http4k.routing.RoutingWsHandler
import org.http4k.routing.bind
import org.http4k.routing.websockets
import org.http4k.websocket.Websocket
import org.http4k.websocket.WsMessage
import org.slf4j.LoggerFactory
import java.util.*

fun OcppWampServerApp(ocppVersions:Set<OcppVersion>, handlers: (CSOcppId)->OcppWampServerHandler): RoutingWsHandler {
    val sockets:MutableMap<String, Websocket?> = mutableMapOf()
    val logger = LoggerFactory.getLogger("io.simatix.ev.ocpp.wamp.server")

    fun newConnection(ws: Websocket) {
        val wsConnectionId = UUID.randomUUID().toString()
        val chargingStationOcppId = Ocpp.extractChargingStationOcppId(ws.upgradeRequest.uri.path)
            ?.takeUnless { it.isEmpty() }
            ?:throw IllegalArgumentException("malformed request - no chargingStationOcppId - ${ws.upgradeRequest}")
        val ocppVersion = ws.upgradeRequest.header("Sec-WebSocket-Protocol")
            ?.split(",")?.firstOrNull()?.trim()
            ?.let { ocppVersions.filter { v -> v.subprotocol == it.lowercase() }.firstOrNull() }
            ?:throw IllegalArgumentException("malformed request - unsupported or invalid ocpp version - ${ws.upgradeRequest}")
        val handler = handlers(chargingStationOcppId)

        if (sockets[chargingStationOcppId] != null) {
            // already connected - refuse connection
            logger.warn("""[$chargingStationOcppId] already connected - refusing new connection """)
            ws.close()
            return
        }

        sockets[chargingStationOcppId] = ws
        ws.onClose {
            logger.info("""[$chargingStationOcppId] [$wsConnectionId] disconnected """)
            if (sockets[chargingStationOcppId] == ws) {
                sockets[chargingStationOcppId] = null
            } else {
                logger.info("warn: do not clear ws on close - not current ws in map")
            }
        }

        logger.info("""[$chargingStationOcppId] [$wsConnectionId] connected """)
        ws.onMessage {
            logger.info("""[$chargingStationOcppId] [$wsConnectionId] -> ${it.bodyString()}""")
            WampMessage.parse(it.bodyString())?.also { msg ->
                if (msg.msgType == WampMessageType.CALL) {
                    val resp = handler.onAction(WampMessageMeta(ocppVersion, chargingStationOcppId), msg)
                        ?: WampMessage.CallError(msg.msgId, "{}").also { logger.warn("no action handler found for $msg") }

                    logger.info("""[$chargingStationOcppId] [$wsConnectionId] <- ${resp.toJson()}""")
                    ws.send(WsMessage(resp.toJson()))
                }

                // TODO - handle CSMS -> CS communication
            }
        }
    }

    return websockets(Ocpp.uriTemplate.toString() bind ::newConnection)
}
