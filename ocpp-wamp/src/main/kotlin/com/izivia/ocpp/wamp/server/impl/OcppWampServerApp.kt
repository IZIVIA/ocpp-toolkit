package com.izivia.ocpp.wamp.server.impl

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.wamp.core.WampCallManager
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.messages.WampMessageType
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import org.http4k.routing.bind
import org.http4k.routing.websockets
import org.http4k.websocket.Websocket
import org.http4k.websocket.WsMessage
import org.slf4j.LoggerFactory
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Websocket Server
 *
 * @param handlers are called in the list order and return the first not null response
 */
class OcppWampServerApp(val ocppVersions:Set<OcppVersion>,
                        private val handlers: (CSOcppId)-> List<OcppWampServerHandler>,
                        private val ocppWsEndpoint: OcppWsEndpoint,
                        val timeoutInMs:Long) {
    companion object {
        private val logger = LoggerFactory.getLogger("com.izivia.ocpp.wamp.server")
    }
    private val connections:MutableMap<String, ChargingStationConnection?> = mutableMapOf()
    private val shutdown = AtomicBoolean(false)

    private fun newConnection(ws: Websocket) {
        if (shutdown.get()) {
            logger.warn("shutting down - rejecting connection")
            ws.close()
            return
        }
        val wsConnectionId = UUID.randomUUID().toString()
        val chargingStationOcppId = ocppWsEndpoint.extractChargingStationOcppId(ws.upgradeRequest.uri.path)
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

        val chargingStationConnection = ChargingStationConnection(
            wsConnectionId, chargingStationOcppId, ws, timeoutInMs, shutdown)
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
                        if (shutdown.get()) {
                            logger.info("""[$chargingStationOcppId] [$wsConnectionId] - rejected call - shutting down - $msgString""")
                            ws.send(WsMessage(WampMessage.CallError(msg.msgId, "{}").toJson()))
                            return@onMessage
                        }

                        logger.info("""[$chargingStationOcppId] [$wsConnectionId] -> ${it.bodyString()}""")
                        val resp = handler.asSequence()
                            // use sequence to avoid greedy mapping, to find the first handler with non null result
                            .map { it.onAction(WampMessageMeta(ocppVersion, chargingStationOcppId), msg) }
                            .filterNotNull()
                            .firstOrNull()
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

    fun shutdown() {
        shutdown.set(true)
        connections.values.toList().forEach { c ->
            c?.let {
                it.callManager.await()
                it.close()
                connections[it.ocppId] = null
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

    fun newRoutingHandler() = websockets(ocppWsEndpoint.uriTemplate.toString() bind ::newConnection)

    private class ChargingStationConnection(val wsConnectionId:String,
                                                 val ocppId:CSOcppId, val ws: Websocket,
                                                 timeoutInMs:Long, shutdown: AtomicBoolean
    ) {
        val callManager:WampCallManager = WampCallManager(logger, {m:String -> ws.send(WsMessage(m))}, timeoutInMs, shutdown)

        fun sendBlocking(message: WampMessage): WampMessage =
            callManager.callBlocking("[$ocppId] [$wsConnectionId]", message)

        fun close() {
            logger.info("[$ocppId] [$wsConnectionId] - closing")
            ws.close()
        }
    }
}
