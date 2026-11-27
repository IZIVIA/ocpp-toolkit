package com.izivia.ocpp.wamp.server.impl

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.utils.OcppErrorCategory
import com.izivia.ocpp.utils.OcppErrorEvent
import com.izivia.ocpp.utils.OcppErrorListener
import com.izivia.ocpp.utils.notifySafely
import com.izivia.ocpp.wamp.core.FrameLog
import com.izivia.ocpp.wamp.core.WampCallManager
import com.izivia.ocpp.utils.escapedForLog
import com.izivia.ocpp.wamp.core.logRef
import com.izivia.ocpp.utils.redactedForLog
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.messages.WampMessageType
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import kotlin.time.Clock
import kotlin.time.Instant
import kotlin.time.TimeSource
import org.http4k.core.Request
import org.http4k.routing.websockets
import org.http4k.routing.websocket.bind
import org.http4k.websocket.Websocket
import org.http4k.websocket.WsMessage
import org.http4k.websocket.WsResponse
import org.slf4j.LoggerFactory
import java.io.IOException
import java.net.SocketException
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

/**
 * Websocket Server
 *
 * The handlers of a connection are called in the list order and return the first not null response.
 */
class OcppWampServerApp internal constructor(
    val ocppVersions: Set<OcppVersion>,
    private val listeners: EventsListeners,
    private val ocppWsEndpoint: OcppWsEndpoint,
    val settings: OcppWampServerSettings,
    private val clock: Clock,
    private val errorListener: OcppErrorListener?,
    /**
     * The station a socket opens for, and its handlers: looked up when the handler is called for the upgrade request,
     * then read once as the socket opens, which may be later or on another thread. A failure registers nothing.
     */
    private val admissionOf: (Request) -> (Websocket) -> HandshakeAdmission
) {
    /**
     * @param handlers gives the handlers of a station, the station being read from the path of the upgrade request
     */
    constructor(
        ocppVersions: Set<OcppVersion>,
        handlers: (CSOcppId) -> List<OcppWampServerHandler>,
        listeners: EventsListeners = EventsListeners(),
        ocppWsEndpoint: OcppWsEndpoint,
        settings: OcppWampServerSettings,
        clock: Clock = Clock.System,
        errorListener: OcppErrorListener? = null
    ) : this(ocppVersions, listeners, ocppWsEndpoint, settings, clock, errorListener, { request -> { _ ->
        val ocppId = ocppWsEndpoint.extractChargingStationOcppId(request.uri.path)
            ?.takeUnless { it.isEmpty() }
            // the path only: the query string and the header values may carry a token, and the message is logged
            // and rethrown
            ?: throw IllegalArgumentException(
                "malformed request - no chargingStationOcppId - ${request.uri.path.escapedForLog(MAX_LOGGED_PATH)}"
            )
        HandshakeAdmission(ocppId, handlers(ocppId))
    } })

    companion object {
        private val logger = LoggerFactory.getLogger("com.izivia.ocpp.wamp.server")
        private const val CALLS_EXECUTOR_TERMINATION_MS = 1_000L
    }

    private val connections = ConnectionsMap()
    private val stationLocks = StationLocks()
    private val shutdown = AtomicBoolean(false)
    private val inFlightInboundCalls = AtomicInteger()
    private val callsExecutor: Executor = settings.buildCallsExecutor()

    // the admission is looked up as the handler is called, so that a consumer called later or elsewhere still has it
    private fun newConnection(request: Request) = openConnection(request, admissionOf(request))

    private fun openConnection(request: Request, admissionOf: (Websocket) -> HandshakeAdmission) = WsResponse { ws ->
        try {
            if (shutdown.get()) {
                logger.warn("shutting down - rejecting connection")
                ws.close()
                return@WsResponse
            }
            val wsConnectionId = UUID.randomUUID().toString()
            // the path only: the query string and the header values may carry a token, and the message is logged
            // and rethrown
            val loggedPath = request.uri.path.escapedForLog(MAX_LOGGED_PATH)
            val ocppVersion = request.header("Sec-WebSocket-Protocol")
                ?.split(",")?.firstOrNull()?.trim()
                ?.let { ocppVersions.filter { v -> v.subprotocol == it.lowercase() }.firstOrNull() }
                ?: throw IllegalArgumentException(
                    "malformed request - unsupported or invalid ocpp version - $loggedPath"
                )
            // resolved before the connection is registered: a failure then leaves nothing registered
            val admission = admissionOf(ws)
            val chargingStationOcppId = admission.ocppId

            val chargingStationConnection = ChargingStationConnection(
                clock.now(),
                wsConnectionId,
                chargingStationOcppId,
                ws,
                request,
                ocppVersion,
                settings.timeoutInMs,
                shutdown
            )
            val logContext = chargingStationConnection.logContext
            val handler = admission.handlers
            var replacedConnection: ChargingStationConnection? = null
            try {
                withStationLock(chargingStationOcppId) {
                    val previousConnection = register(chargingStationConnection)
                    replacedConnection = previousConnection
                    if (previousConnection != null) {
                        logger.warn(
                            """[${chargingStationOcppId.escapedForLog()}] already connected
                                | - the new connection will replace the previous one
                                | - a connection reconnect notification will be sent
                                | - existing connection=$previousConnection
                                | - new connection=$chargingStationConnection
                                | - existing connection will be closed
                            """.trimMargin()
                        )
                        notifyConnection(logContext, "reconnect") {
                            listeners.onWsReconnectHandler(chargingStationOcppId, request.headers)
                        }
                    } else {
                        notifyConnection(logContext, "connect") {
                            listeners.onWsConnectHandler(chargingStationOcppId, request.headers)
                        }
                    }
                }
            } finally {
                // this close wont trigger a onWsCloseHandler, because we have already changed the current registered
                // connection; it runs out of the station lock, and even when a listener throws an Error
                replacedConnection?.close()
            }

            ws.onClose {
                handleWebSocketClosed(logContext, chargingStationConnection)
            }

            ws.onError { e ->
                // not sure yet if we should consider any error as a close, so we handle only connection resets
                if (e.isConnectionReset()) {
                    handleWebSocketClosed(logContext, chargingStationConnection, e)
                } else if (e !is IOException && e !== chargingStationConnection.reportedFailure) {
                    // An IOException is the network failing, which onWsCloseHandler already reports. The WebSocket
                    // callback routes here any other exception escaping ws.onMessage, so nothing reports those there.
                    errorListener?.notifySafely(
                        chargingStationConnection.errorEvent(OcppErrorCategory.TRANSPORT_ERROR, cause = e)
                    )
                }
            }

            if (ws is ControlFrameAwareWebsocket) {
                ws.onPong { notifyControlFrame(logContext, chargingStationConnection, listeners.onWsPongHandler) }
                ws.onPing { notifyControlFrame(logContext, chargingStationConnection, listeners.onWsPingHandler) }
            } else {
                logger.warn("$logContext websocket does not expose control frames - ping/pong will not be notified")
            }

            logger.info("$logContext connected - number of connections : ${connections.size}")
            ws.onMessage {
                val msgString = it.bodyString()
                FrameLog.inbound(logContext, msgString)
                if (msgString.trim().isEmpty()) {
                    logger.info("$logContext empty message received - ignored")
                    return@onMessage
                }
                val msg = WampMessage.parse(msgString)
                if (msg == null) {
                    logger.error("$logContext parsing Wamp message returns null - length=${msgString.length}")
                    errorListener?.notifySafely(
                        chargingStationConnection.errorEvent(
                            OcppErrorCategory.PROTOCOL_ERROR,
                            errorCode = MessageErrorCode.INTERNAL_ERROR
                        )
                    )
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
                            // count the call before checking the flag, so that shutdown either waits for it
                            // or this call sees the flag and is rejected
                            inFlightInboundCalls.incrementAndGet()
                            if (shutdown.get()) {
                                inFlightInboundCalls.decrementAndGet()
                                logger.info("$logContext - rejected call - shutting down - ${wampMessage.logRef()}")
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
                            chargingStationConnection.submitCall(wampMessage) {
                                var failureCategory = OcppErrorCategory.HANDLER_ERROR
                                try {
                                    val callQueueLatency = clock.now() - startCall
                                    logger.info(
                                        "$logContext [IN][CALL][REQ] -> ${wampMessage.logRef()}" +
                                            " length=${msgString.length} -- " +
                                            "[latency=${callQueueLatency.inWholeMilliseconds}ms]"
                                    )
                                    val resp = handler.asSequence()
                                        // use sequence to avoid greedy mapping,
                                        // to find the first handler with non null result
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
                                            "{}"
                                        ).also {
                                            logger.warn("$logContext no action handler found for ${wampMessage.logRef()}")
                                            errorListener?.notifySafely(
                                                chargingStationConnection.errorEvent(
                                                    OcppErrorCategory.UNHANDLED_ACTION,
                                                    wampMessage,
                                                    MessageErrorCode.INTERNAL_ERROR
                                                )
                                            )
                                        }
                                    failureCategory = OcppErrorCategory.PROTOCOL_ERROR
                                    val respJson = resp.toJson()
                                    failureCategory = OcppErrorCategory.TRANSPORT_ERROR

                                    val duration = clock.now() - startCall
                                    logger.info(
                                        "$logContext [IN][CALL][RESP] <- ${resp.logRef()}" +
                                            " length=${respJson.length} -- " +
                                            "[duration=${duration.inWholeMilliseconds}ms]" +
                                            "[latency=${callQueueLatency.inWholeMilliseconds}ms]"
                                    )
                                    FrameLog.outbound(logContext, respJson)
                                    ws.send(WsMessage(respJson))
                                } catch (t: Throwable) {
                                    // Throwable on purpose: a handler failing with an Error is still a handler failure.
                                    val sending = failureCategory == OcppErrorCategory.TRANSPORT_ERROR
                                    if (sending || t is VirtualMachineError) {
                                        // Not answered: the response may already be partly on the wire, and a second
                                        // send could answer the call twice; the JVM may be unable to go on.
                                        // An IOException while sending is the network failing, as in ws.onError.
                                        if (!sending || t !is IOException) {
                                            errorListener?.notifySafely(
                                                chargingStationConnection.errorEvent(
                                                    failureCategory,
                                                    wampMessage,
                                                    cause = t
                                                )
                                            )
                                        }
                                        throw t
                                    }
                                    // Answered, so that the station is not left waiting for its timeout before its
                                    // next CALL; the failure is reported here once and not rethrown.
                                    errorListener?.notifySafely(
                                        chargingStationConnection.errorEvent(
                                            failureCategory,
                                            wampMessage,
                                            MessageErrorCode.INTERNAL_ERROR,
                                            t
                                        )
                                    )
                                    chargingStationConnection.answerFailedCall(wampMessage, failureCategory, t)
                                }
                            }
                        }

                        WampMessageType.CALL_RESULT, WampMessageType.CALL_ERROR -> {
                            chargingStationConnection.callManager.handleResult(logContext, wampMessage)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            logger.error(
                "Error during new connection with ${request.uri.path.escapedForLog(MAX_LOGGED_PATH)}: " +
                    e.javaClass.name,
                e.redactedForLog()
            )
            throw e
        }
    }

    /**
     * Registers [connection] as the current connection of its station and returns the one it replaced, so that
     * concurrent connections of a station each replace, and close, a distinct connection. The caller holds the lock
     * of the station.
     */
    private fun register(connection: ChargingStationConnection): ChargingStationConnection? {
        val previous = connections[connection.ocppId]
        // a control frame of the previous connection being notified completes before the replacement
        synchronized(previous ?: connection) {
            connections[connection.ocppId] = connection
        }
        return previous
    }

    /**
     * Notifies the lifecycle [event] of a connection to its [listener], which only observes it: an exception it throws
     * is logged as a redacted copy, its classes and stack traces without messages, and the lifecycle goes on. A new
     * connection stays registered and served, so that the station stays reachable and its next connection is notified
     * as a reconnection; a closed connection stays removed, and the next connection of the station is notified as a
     * connection. An interrupted listener leaves the calling thread interrupted, and an [Error] propagates.
     */
    private fun notifyConnection(logContext: String, event: String, listener: () -> Unit) {
        try {
            listener()
        } catch (e: Exception) {
            if (e is InterruptedException) {
                Thread.currentThread().interrupt()
            }
            logger.error("$logContext $event listener failed: ${e.javaClass.name}", e.redactedForLog())
        }
    }

    /**
     * Runs [block] holding the lock of the station [ocppId], which is always taken before the lock of one of its
     * connections, never while holding it. Registering or removing a connection and notifying the matching event
     * under it delivers the events of a station in the order its connections were registered and removed.
     */
    private fun <T> withStationLock(ocppId: CSOcppId, block: () -> T): T =
        stationLocks.withLock(ocppId.lowercase(), block)

    /**
     * Runs an inbound call on the calls executor, keeping it counted in [inFlightInboundCalls]
     * until its response has been sent. The caller must have incremented the counter.
     *
     * A call the executor does not run, by a rejection or otherwise, is answered with an `InternalError` CALLERROR and
     * reported once as a [OcppErrorCategory.TRANSPORT_ERROR], unless the executor failed with a [VirtualMachineError].
     */
    private fun ChargingStationConnection.submitCall(message: WampMessage, call: () -> Unit) {
        // Claimed by the call when it starts, or by the submission when the executor fails without running it:
        // whichever claims it releases the count, exactly once, and only whichever claims it answers the call.
        val claimed = AtomicBoolean(false)
        try {
            callsExecutor.execute {
                if (claimed.compareAndSet(false, true)) {
                    try {
                        call()
                    } finally {
                        inFlightInboundCalls.decrementAndGet()
                    }
                }
            }
        } catch (t: Throwable) {
            if (claimed.compareAndSet(false, true)) {
                // The executor did not run the call: nothing else reports or answers it.
                if (t !is VirtualMachineError) {
                    try {
                        errorListener?.notifySafely(
                            errorEvent(OcppErrorCategory.TRANSPORT_ERROR, message, MessageErrorCode.INTERNAL_ERROR, t)
                        )
                        answerFailedCall(message, OcppErrorCategory.TRANSPORT_ERROR, t)
                    } catch (e: VirtualMachineError) {
                        // thrown by the send of the answer: reported by nobody, ws.onError must not report it either
                        reportedFailure = e
                        throw e
                    } finally {
                        inFlightInboundCalls.decrementAndGet()
                    }
                    return
                }
                inFlightInboundCalls.decrementAndGet()
                errorListener?.notifySafely(errorEvent(OcppErrorCategory.TRANSPORT_ERROR, message, cause = t))
            }
            // Otherwise the executor ran the call on this thread, which has already answered or reported it.
            // Either way, ws.onError must not report it again.
            reportedFailure = t
            throw t
        }
    }

    /**
     * Answers the call [message], failed with [failure] of [category], with an `InternalError` CALLERROR that never
     * carries the failure, and logs it once with the class of the failure only, since its message may quote a frame.
     * A send that fails is the network failing, which is not reported again: it is only logged, without its message.
     */
    private fun ChargingStationConnection.answerFailedCall(
        message: WampMessage,
        category: OcppErrorCategory,
        failure: Throwable
    ) {
        logger.warn(
            "$logContext ${message.logRef()} call failed ($category)" +
                " - answered InternalError: ${failure.javaClass.name}"
        )
        try {
            ws.send(WsMessage(internalErrorFor(message.msgId).toJson()))
        } catch (e: Throwable) {
            if (e is VirtualMachineError) {
                throw e
            }
            logger.warn("$logContext [${message.msgId.escapedForLog()}] CALLERROR not sent: ${e.javaClass.name}")
        }
    }

    private fun handleWebSocketClosed(
        logContext: String,
        chargingStationConnection: ChargingStationConnection,
        t: Throwable? = null
    ) = withStationLock(chargingStationConnection.ocppId) {
        val chargingStationOcppId = chargingStationConnection.ocppId
        val currentConnection = connections[chargingStationOcppId]
        // a control frame being notified completes before the removal
        val removed = synchronized(chargingStationConnection) { connections.remove(chargingStationConnection) }
        if (!removed) {
            logger.info(
                "$logContext warn: do not clear ws on close - not current connection in map" +
                    " - registered connection: $currentConnection" +
                    " - closed connection: $chargingStationConnection"
            )
        }
        val duration = clock.now() - chargingStationConnection.timestamp
        val errorInfo = t?.let { " onError ${it.javaClass.name} - " } ?: ""
        logger.info("$logContext disconnected - $duration -$errorInfo number of connections : ${connections.size}")

        if (removed) {
            // we notify only if we remove the connection, otherwise it has been replaced, and the station is still
            // connected, or the station disconnection has already been notified by the removal of its current one;
            // no connection can register before the notification, under the station lock
            notifyConnection(logContext, "close") {
                listeners.onWsCloseHandler(chargingStationOcppId, chargingStationConnection.request.headers)
            }
        } else {
            logger.debug("$logContext not notifying disconnection - still connected or already notified")
        }
    }

    private fun notifyControlFrame(
        logContext: String,
        chargingStationConnection: ChargingStationConnection,
        handler: (WsControlFrameEvent) -> Unit
    ) {
        val receivedAt = clock.now()
        // holding the connection lock, the connection can be neither replaced nor removed before the handler returns,
        // so a frame from a replaced or closed connection cannot be taken as a sign of life of the station
        synchronized(chargingStationConnection) {
            if (connections[chargingStationConnection.ocppId] !== chargingStationConnection) {
                logger.debug("$logContext control frame ignored - not current connection")
                return
            }
            handler(
                WsControlFrameEvent(
                    chargingStationConnection.ocppId,
                    chargingStationConnection.wsConnectionId,
                    chargingStationConnection.request.headers,
                    receivedAt
                )
            )
        }
    }

    fun shutdown() {
        shutdown.set(true)
        // an Error still propagates, as from every listener, once the calls executor the toolkit owns is shut down
        try {
            awaitInFlightInboundCalls()
            connections.values.toList().forEach { c ->
                c?.let {
                    // a failure is logged, so that it does not leave the next connections open
                    try {
                        it.callManager.await()
                        it.close()
                        // the close completes later, when the connection is no longer registered: notify it now
                        withStationLock(it.ocppId) {
                            if (synchronized(it) { connections.remove(it) }) {
                                notifyConnection(it.logContext, "close") {
                                    listeners.onWsCloseHandler(it.ocppId, it.request.headers)
                                }
                            }
                        }
                    } catch (e: Exception) {
                        logger.error(
                            "${it.logContext} shutting down - closing failed: ${e.javaClass.name}",
                            e.redactedForLog()
                        )
                    }
                }
            }
        } finally {
            shutdownOwnedCallsExecutor()
        }
    }

    /**
     * Shuts down the calls executor when the toolkit built it, never an executor of the caller. Its threads get
     * [CALLS_EXECUTOR_TERMINATION_MS] to end, then are interrupted and get [CALLS_EXECUTOR_TERMINATION_MS] more: a
     * shutdown lasts at most [OcppWampServerSettings.timeoutInMs] for the drain, the closing of the connections, and
     * these two waits. The queued calls were already dropped when the drain timed out; any the interruption still
     * drops are not answered either, and are no longer counted in flight. A second call returns at once.
     */
    private fun shutdownOwnedCallsExecutor() {
        val executor = callsExecutor as? ToolkitOwnedExecutor ?: return
        executor.shutdown()
        try {
            if (!executor.awaitTermination(CALLS_EXECUTOR_TERMINATION_MS, TimeUnit.MILLISECONDS)) {
                val dropped = executor.shutdownNowCounted()
                logger.warn("shutting down - calls executor still busy, interrupted, $dropped queued calls dropped")
                if (!executor.awaitTermination(CALLS_EXECUTOR_TERMINATION_MS, TimeUnit.MILLISECONDS)) {
                    logger.warn(
                        "shutting down - ${executor.activeCount} call threads still running after their interruption"
                    )
                }
            }
        } catch (e: InterruptedException) {
            executor.shutdownNowCounted()
            Thread.currentThread().interrupt()
        }
    }

    // a task shutdownNow returns never ran, so its call never claimed it: release its count here, exactly once
    private fun ToolkitOwnedExecutor.shutdownNowCounted(): Int =
        shutdownNow().size.also { inFlightInboundCalls.addAndGet(-it) }

    /**
     * Waits up to [OcppWampServerSettings.timeoutInMs] for the inbound calls in flight to be answered. On a timeout,
     * before the connections are closed, the calls still queued in a toolkit-owned executor are dropped: they never
     * run, so they are never answered, and are no longer counted in flight. A caller's executor is left as it is.
     */
    private fun awaitInFlightInboundCalls() {
        // a monotonic source, not the injectable clock: a fixed clock would never reach the deadline
        val start = TimeSource.Monotonic.markNow()
        while (
            inFlightInboundCalls.get() > 0 &&
            start.elapsedNow().inWholeMilliseconds < settings.timeoutInMs
        ) {
            Thread.sleep(10)
        }
        val remaining = inFlightInboundCalls.get()
        if (remaining > 0) {
            logger.warn("shutting down - $remaining inbound calls not answered within ${settings.timeoutInMs}ms")
            dropQueuedCalls()
        }
    }

    private fun dropQueuedCalls() {
        val executor = callsExecutor as? ToolkitOwnedExecutor ?: return
        val dropped = ArrayList<Runnable>()
        // a drained task never runs, so its call never claims it: release its count here, exactly once
        executor.queue.drainTo(dropped)
        if (dropped.isNotEmpty()) {
            inFlightInboundCalls.addAndGet(-dropped.size)
            logger.warn("shutting down - ${dropped.size} queued inbound calls dropped without an answer")
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
        /** The station id, escaped, and the connection id, to start the log lines of this connection. */
        val logContext = "[${ocppId.escapedForLog()}] [$wsConnectionId]"

        val callManager: WampCallManager =
            WampCallManager(logger, { m: String -> ws.send(WsMessage(m)) }, timeoutInMs, shutdown)

        // only set and read on the message thread: see submitCall
        var reportedFailure: Throwable? = null

        fun errorEvent(
            category: OcppErrorCategory,
            message: WampMessage? = null,
            errorCode: MessageErrorCode? = null,
            cause: Throwable? = null
        ) = OcppErrorEvent(category, ocppId, ocppVersion.subprotocol, message?.action, message?.msgId, errorCode, cause)

        fun sendBlocking(message: WampMessage, startCall: Instant, timeoutInMs: Long? = null): WampMessage =
            callManager.callBlocking(logContext, startCall, message, timeoutInMs)

        fun close() {
            logger.info("$logContext - closing")
            ws.close()
        }

        override fun toString(): String {
            return "ChargingStationConnection(" +
                "wsConnectionId='$wsConnectionId', ocppId='${ocppId.escapedForLog()}', ocppVersion=$ocppVersion, timestamp='$timestamp')"
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

/** The upgrade path is bounded higher than a station id: it holds the endpoint path too. */
private const val MAX_LOGGED_PATH = 256

class NoConnectionException(e: String) : IllegalStateException(e)

/**
 * The CALLERROR answering the call [msgId] whose handling failed. OCPP-J 1.6 §4.2.3: "If there are no error details
 * you MUST fill in an empty object {}". The failure itself is never sent to the peer.
 */
private fun internalErrorFor(msgId: String) =
    WampMessage.CallError(msgId, MessageErrorCode.INTERNAL_ERROR, "Internal error", "{}")

private fun Throwable.isConnectionReset(): Boolean =
    this is SocketException && this.message.equals("Connection reset")
