package com.izivia.ocpp.wamp.server.impl

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.wamp.core.WampCallManager
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.messages.WampMessageType
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import kotlin.time.Clock
import kotlin.time.Instant
import org.http4k.core.Request
import org.http4k.routing.websockets
import org.http4k.routing.websocket.bind
import org.http4k.websocket.Websocket
import org.http4k.websocket.WsMessage
import org.http4k.websocket.WsResponse
import org.slf4j.LoggerFactory
import java.net.SocketException
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.Executor
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Websocket Server
 *
 * @param handlers are called in the list order and return the first not null response
 */
class OcppWampServerApp(
    val ocppVersions: Set<OcppVersion>,
    private val handlers: (CSOcppId) -> List<OcppWampServerHandler>,
    private val listeners: EventsListeners = EventsListeners(),
    private val ocppWsEndpoint: OcppWsEndpoint,
    val settings: OcppWampServerSettings,
    private val clock: Clock = Clock.System
) {
    companion object {
        private val logger = LoggerFactory.getLogger("com.izivia.ocpp.wamp.server")
    }

    private val connections = ConnectionsMap()
    private val shutdown = AtomicBoolean(false)
    private val callsExecutor: Executor = settings.buildCallsExecutor()

    private fun newConnection(request: Request) = WsResponse { ws: Websocket ->
        try {
            if (shutdown.get()) {
                logger.warn("shutting down - rejecting connection")
                ws.close()
                return@WsResponse
            }
            val wsConnectionId = UUID.randomUUID().toString()
            val chargingStationOcppId = ocppWsEndpoint.extractChargingStationOcppId(request.uri.path)
                ?.takeUnless { it.isEmpty() }
                ?: throw IllegalArgumentException("malformed request - no chargingStationOcppId - $request")
            val ocppVersion = request.header("Sec-WebSocket-Protocol")
                ?.split(",")?.firstOrNull()?.trim()
                ?.let { ocppVersions.filter { v -> v.subprotocol == it.lowercase() }.firstOrNull() }
                ?: throw IllegalArgumentException(
                    "malformed request - unsupported or invalid ocpp version - $request"
                )

            val previousConnection = connections[chargingStationOcppId]
            val chargingStationConnection = ChargingStationConnection(
                clock.now(),
                wsConnectionId,
                chargingStationOcppId,
                ws,
                request,
                ocppVersion,
                settings.timeoutInMs,
                shutdown
            ).also {
                connections[chargingStationOcppId] = it
            }

            val reconnect = previousConnection != null

            if (reconnect) {
                logger.warn(
                    """[$chargingStationOcppId] already connected
                        | - the new connection will replace the previous one
                        | - a connection reconnect notification will be sent
                        | - existing connection=$previousConnection
                        | - new connection=$chargingStationConnection
                        | - existing connection will be closed
                    """.trimMargin()
                )
                // this close wont trigger a onWsCloseHandler,
                // because we have already changed the current registered connection
                previousConnection?.close()
                listeners.onWsReconnectHandler(chargingStationOcppId, request.headers)
            } else {
                listeners.onWsConnectHandler(chargingStationOcppId, request.headers)
            }

            val logContext = "[$chargingStationOcppId] [$wsConnectionId]"
            ws.onClose {
                handleWebSocketClosed(logContext, chargingStationConnection)
            }

            ws.onError { e ->
                // not sure yet if we should consider any error as a close, so we handle only connection resets
                if (e.isConnectionReset()) {
                    handleWebSocketClosed(logContext, chargingStationConnection, e)
                }
            }

            logger.info("$logContext connected - number of connections : ${connections.size}")
            val handler = handlers(chargingStationOcppId)
            ws.onMessage {
                val msgString = it.bodyString()
                if (logger.isDebugEnabled) {
                    logger.debug("$logContext onMessage `$msgString`")
                }
                if (msgString.trim().isEmpty()) {
                    logger.info("$logContext empty message received - ignored")
                    return@onMessage
                }
                val msg = WampMessage.parse(msgString)
                if (msg == null) {
                    logger.error("$logContext parsing Wamp message returns null - `$msgString`")
                    ws.send(
                        WsMessage(
                            WampMessage.CallError(
                                "",
                                MessageErrorCode.INTERNAL_ERROR,
                                "Parse error",
                                "{}"
                            ).toJson()
                        )
                    )
                    return@onMessage
                }
                msg.also { wampMessage ->
                    when (wampMessage.msgType) {
                        WampMessageType.CALL -> {
                            if (shutdown.get()) {
                                logger.info("$logContext - rejected call - shutting down - $msgString")
                                ws.send(
                                    WsMessage(
                                        WampMessage.CallError(
                                            wampMessage.msgId,
                                            MessageErrorCode.INTERNAL_ERROR,
                                            "Rejected call - shutting down",
                                            "{}"
                                        ).toJson()
                                    )
                                )
                                return@onMessage
                            }

                            val startCall = clock.now()
                            callsExecutor.execute {
                                val callQueueLatency = clock.now() - startCall
                                logger.info(
                                    "$logContext [IN][CALL][REQ] -> ${it.bodyString()} -- " +
                                        "[latency=${callQueueLatency.inWholeMilliseconds}ms]"
                                )
                                val resp = handler.asSequence()
                                    // use sequence to avoid greedy mapping, to find the first handler with non null result
                                    .map {
                                        it.onAction(
                                            WampMessageMeta(
                                                ocppVersion,
                                                chargingStationOcppId,
                                                request.headers
                                            ),
                                            wampMessage
                                        )
                                    }
                                    .filterNotNull()
                                    .firstOrNull()
                                    ?: WampMessage.CallError(
                                        wampMessage.msgId,
                                        MessageErrorCode.INTERNAL_ERROR,
                                        "No action handler found",
                                        """{"message":"$wampMessage"}"""
                                    ).also { logger.warn("$logContext no action handler found for $wampMessage") }

                                val duration = clock.now() - startCall
                                logger.info(
                                    "$logContext [IN][CALL][RESP] <- ${resp.toJson()} -- " +
                                        "[duration=${duration.inWholeMilliseconds}ms]" +
                                        "[latency=${callQueueLatency.inWholeMilliseconds}ms]"
                                )
                                ws.send(WsMessage(resp.toJson()))
                            }
                        }

                        WampMessageType.CALL_RESULT, WampMessageType.CALL_ERROR -> {
                            chargingStationConnection.callManager.handleResult(logContext, wampMessage)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            logger.error("Error during new connection with $request: $e", e)
            throw e
        }
    }

    private fun handleWebSocketClosed(
        logContext: String,
        chargingStationConnection: ChargingStationConnection,
        t: Throwable? = null
    ) {
        val chargingStationOcppId = chargingStationConnection.ocppId
        val currentConnection = connections[chargingStationOcppId]
        if (!connections.remove(chargingStationConnection)) {
            logger.info(
                "$logContext warn: do not clear ws on close - not current connection in map" +
                    " - registered connection: $currentConnection" +
                    " - closed connection: $chargingStationConnection"
            )
        }
        val duration = clock.now() - chargingStationConnection.timestamp
        val errorInfo = t?.let { " onError $t - " } ?: ""
        logger.info("$logContext disconnected - $duration -$errorInfo number of connections : ${connections.size}")

        if (connections[chargingStationOcppId] == null) {
            // we notify only if we remove the connection, otherwise we are still connected
            listeners.onWsCloseHandler(chargingStationOcppId, chargingStationConnection.request.headers)
        } else {
            logger.debug("$logContext not notifying disconnection - still connected")
        }
    }

    fun shutdown() {
        shutdown.set(true)
        connections.values.toList().forEach { c ->
            c?.let {
                it.callManager.await()
                it.close()
                connections.remove(it)
            }
        }
    }

    fun sendBlocking(
        ocppId: CSOcppId,
        message: WampMessage,
        startedCallAt: Instant = Clock.System.now(),
        timeoutInMs: Long? = null
    ): WampMessage = getChargingStationConnection(ocppId).sendBlocking(message, startedCallAt, timeoutInMs)

    // Throws NoConnectionException when no connection is found for the specified ocpp id
    private fun getChargingStationConnection(ocppId: CSOcppId): ChargingStationConnection {
        var backOffRetryMs = 10L
        var backOffRetryAttempts = 5
        var connection = connections[ocppId]
        while (connection == null && backOffRetryAttempts > 0) {
            Thread.sleep(backOffRetryMs)
            backOffRetryAttempts--
            backOffRetryMs *= 2
            connection = connections[ocppId]
        }
        return connection ?: throw NoConnectionException("no connection to $ocppId")
    }
    fun getChargingStationOcppVersion(ocppId: CSOcppId): OcppVersion =
        getChargingStationConnection(ocppId).ocppVersion

    fun newRoutingHandler() = websockets(ocppWsEndpoint.uriTemplate.toString() bind ::newConnection)

    private class ChargingStationConnection(
        val timestamp: Instant,
        val wsConnectionId: String,
        val ocppId: CSOcppId,
        val ws: Websocket,
        val request: Request,
        val ocppVersion: OcppVersion,
        timeoutInMs: Long,
        shutdown: AtomicBoolean
    ) {
        val callManager: WampCallManager =
            WampCallManager(logger, { m: String -> ws.send(WsMessage(m)) }, timeoutInMs, shutdown)

        fun sendBlocking(message: WampMessage, startCall: Instant, timeoutInMs: Long? = null): WampMessage =
            callManager.callBlocking("[$ocppId] [$wsConnectionId]", startCall, message, timeoutInMs)

        fun close() {
            logger.info("[$ocppId] [$wsConnectionId] - closing")
            ws.close()
        }

        override fun toString(): String {
            return "ChargingStationConnection(" +
                "wsConnectionId='$wsConnectionId', ocppId='$ocppId', ocppVersion=$ocppVersion, timestamp='$timestamp')"
        }
    }

    private class ConnectionsMap {
        private val connections: ConcurrentMap<String, ChargingStationConnection?> =
            ConcurrentHashMap<String, ChargingStationConnection?>()
        val values get() = connections.values

        operator fun get(ocppId: String) = connections[ocppId.lowercase()]
        operator fun set(ocppId: String, connection: ChargingStationConnection) {
            connections[ocppId.lowercase()] = connection
        }

        fun remove(connection: ChargingStationConnection) =
            connections.remove(connection.ocppId.lowercase(), connection)

        val size: Int get() = connections.size
    }
}

class NoConnectionException(e: String) : IllegalStateException(e)

private fun Throwable.isConnectionReset(): Boolean =
    this is SocketException && this.message.equals("Connection reset")
