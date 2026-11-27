package com.izivia.ocpp.wamp.client.impl

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.wamp.client.ConnectionListener
import com.izivia.ocpp.wamp.client.ConnectionState
import com.izivia.ocpp.wamp.client.NotConnectedException
import com.izivia.ocpp.wamp.client.OcppWampClient
import com.izivia.ocpp.wamp.client.WampOnActionHandler
import com.izivia.ocpp.wamp.core.FrameLog
import com.izivia.ocpp.wamp.core.WampCallManager
import com.izivia.ocpp.wamp.core.escapedForLog
import com.izivia.ocpp.wamp.core.logRef
import com.izivia.ocpp.wamp.core.redactedForLog
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.messages.WampMessageMetaHeaders
import com.izivia.ocpp.wamp.messages.WampMessageType
import kotlinx.coroutines.*
import kotlin.time.Clock
import okhttp3.*
import org.http4k.core.Uri
import org.slf4j.LoggerFactory
import java.io.IOException
import java.net.ConnectException
import java.util.concurrent.*
import javax.net.ssl.HostnameVerifier

/**
 * OCPP-J client over OkHttp.
 *
 * Inbound CALLs are handled on [Dispatchers.IO]. Its threads are shared with the rest of the application, and by
 * default at most 64 of them, or as many as there are cores if that is larger, run at once (system property
 * `kotlinx.coroutines.io.parallelism`). Beyond that, a CALL waits for a thread, and [timeoutInMs] does not count
 * that wait: the server may give up on the CALL before its handler has started.
 *
 * [timeoutInMs] does not interrupt a handler either. Handlers do not suspend, so one that blocks (`Thread.sleep`,
 * a blocking call) holds its thread until it returns, and its answer is sent then.
 *
 * CALLRESULT and CALLERROR messages are handled on the OkHttp reader thread.
 */
class OkHttpOcppWampClient(
    target: Uri,
    val ocppId: CSOcppId,
    val ocppVersion: OcppVersion,
    val timeoutInMs: Long = 30_000,
    private val headers: WampMessageMetaHeaders = emptyList(),
    hostnameVerifier: HostnameVerifier = HostnameVerifier { _, _ -> true }
) : OcppWampClient {
    val serverUri = target.path("${target.path.removeSuffix("/")}/$ocppId")

    // the server URI as logs and exception messages show it: without the credentials nor the query string it may carry
    private val loggableServerUri = serverUri.copy(userInfo = "", query = "", fragment = "")

    /** The context of this client in log lines and exception messages: [serverUri] without credentials nor query. */
    val debugContext = "$ocppId -> $loggableServerUri"
    override val state: ConnectionState
        get() = connectionState

    private val handlers = mutableListOf<WampOnActionHandler>()
    // written by the caller's thread, read by OkHttp's
    @Volatile
    private var connectionListener: ConnectionListener? = null

    private val socketOkHttpClient = OkHttpClient.Builder()
        .readTimeout(timeoutInMs, TimeUnit.MILLISECONDS)
        .connectTimeout(timeoutInMs, TimeUnit.MILLISECONDS)
        .hostnameVerifier(hostnameVerifier)
        .build()

    private var wampConnection: WampConnection? = null

    // the HTTP status answered to the last upgrade request when it was refused, set by OkHttp's thread before the
    // failure is reported
    @Volatile
    private var handshakeStatus: Int? = null

    private var connectionState: ConnectionState = ConnectionState.DISCONNECTED
        set(value) {
            if (value != field) {
                logger.info("[$debugContext] connection state $field -> $value")
                field = value
            }
        }

    override fun connect(listener: ConnectionListener?) {
        connectionListener = listener
        tryToConnect().also { t ->
            if (t == null) {
                logger.info("[$debugContext] connected")
            } else {
                // the failure message may quote the server's answer: only its class and the HTTP status of a refused
                // upgrade are shown, the cause is kept
                val failure = t.javaClass.name + handshakeStatus?.let { " - HTTP $it" }.orEmpty()
                logger.error("[$debugContext] error when connecting - $failure")
                // covers both a failure reported by OkHttp and the timeout, before the cleanup drops the listener
                connectionListener?.onConnectionFailure(t)
                cleanupStateOnClose()
                throw IOException("[$debugContext] connection failed: $failure", t)
            }
        }
    }

    override fun close() {
        logger.info("[$debugContext] disconnecting")
        connectionState = ConnectionState.DISCONNECTING
        wampConnection?.close(NORMAL_CLOSURE_STATUS, "close")
        cleanupStateOnClose()
    }

    private fun cleanupStateOnClose() {
        wampConnection = null
        connectionState = ConnectionState.DISCONNECTED
        connectionListener = null
    }

    private fun tryToConnect(): Throwable? {
        val latch = CountDownLatch(1)
        var error: Throwable? = TimeoutException("connection timed out to $loggableServerUri")
        asyncConnect(object : ConnectionListener {
            override fun onConnected() {
                error = null
                latch.countDown()
            }

            override fun onConnectionFailure(t: Throwable) {
                error = t
                latch.countDown()
            }

            override fun onConnectionLost(t: Throwable?) {
                if (latch.count > 0) {
                    // shouldn't be possible, but just in case
                    error = t
                    latch.countDown()
                }
                connectionListener?.onConnectionLost(t)
            }
        })
        latch.await(timeoutInMs, TimeUnit.MILLISECONDS)
        return error
    }

    private fun asyncConnect(listener: ConnectionListener) {
        if (connectionState != ConnectionState.DISCONNECTED) {
            throw IllegalStateException(
                "can't async connect to $loggableServerUri when connection state is $connectionState"
            )
        }
        logger.debug("[$ocppId] connecting to $loggableServerUri - current connection state = $connectionState")
        connectionState = ConnectionState.CONNECTING
        handshakeStatus = null

        socketOkHttpClient.newWebSocket(
            Request.Builder().url(serverUri.toString())
                .header("Sec-WebSocket-Protocol", ocppVersion.subprotocol)
                .run {
                    headers
                        .filter { header -> header.second != null }
                        .foldRight(this) { header, acc ->
                            acc.header(header.first, header.second!!)
                        }
                }
                .build(),
            object : WebSocketListener() {
                override fun onOpen(webSocket: WebSocket, response: Response) {
                    onConnectedTo(webSocket)
                    listener.onConnected()
                }

                override fun onMessage(webSocket: WebSocket, text: String) {
                    onMessage(text)
                }

                override fun onClosing(closingWebSocket: WebSocket, code: Int, reason: String) {
                    val loggableReason = reason.escapedForLog()
                    logger.warn("[$ocppId] web socket closing $code $loggableReason - state = $connectionState")
                    if (code == CLEANUP_CLOSURE_STATUS) {
                        logger.info("[$ocppId] connection closed to $loggableServerUri due to reconnection")
                        closingWebSocket.closeSafely(code, reason)
                        return
                    }

                    closingWebSocket.closeSafely(code, reason)
                    val previousState = connectionState
                    connectionState = ConnectionState.DISCONNECTED
                    wampConnection = null
                    val closed = IOException("[$debugContext] websocket closed: code=$code; reason=$loggableReason")
                    when (previousState) {
                        ConnectionState.CONNECTING -> listener.onConnectionFailure(closed)
                        else -> listener.onConnectionLost(closed)
                    }
                }

                override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                    logger.info(
                        "[$ocppId] connection closed to $loggableServerUri - code=$code; reason=${reason.escapedForLog()}"
                    )
                    connectionState = ConnectionState.DISCONNECTED
                    wampConnection = null
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    val previousState = connectionState
                    connectionState = ConnectionState.DISCONNECTED
                    wampConnection = null
                    // the failure message may quote the server's answer: only its class is logged
                    val failure = t.javaClass.name
                    if (previousState == ConnectionState.CONNECTING) {
                        if (t is ConnectException) {
                            logger.warn("[$ocppId] web socket failed to connect to $loggableServerUri: $failure")
                        } else {
                            logger.warn(
                                "[$ocppId] web socket connection failure to $loggableServerUri: $failure",
                                t.redactedForLog()
                            )
                        }
                        handshakeStatus = response?.code
                        listener.onConnectionFailure(t)
                    } else {
                        logger.warn(
                            "[$ocppId] web socket failure with connection to $loggableServerUri: $failure",
                            t.redactedForLog()
                        )
                        listener.onConnectionLost(t)
                    }
                }
            }
        )
    }

    private fun onMessage(text: String) {
        FrameLog.inbound("[$ocppId]", text)
        val msg = WampMessage.parse(text)

        if (msg == null) {
            logger.warn("[$ocppId] can't parse wamp message from server - length=${text.length}")
            return
        }
        when (msg.msgType) {
            WampMessageType.CALL_RESULT, WampMessageType.CALL_ERROR -> {
                // outcoming call result, handled on the reader thread: it does not block, and it must not wait
                // for a pool that call handlers may be occupying while they wait for this very result
                wampConnection?.callManager?.handleResult("[$ocppId]", msg)
            }

            WampMessageType.CALL -> {
                // call handlers block their thread, so they run on the elastic pool meant for blocking work
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        withTimeout(timeoutInMs) {
                            // incoming call
                            logger.info("[$ocppId] [IN][CALL][REQ] <- ${msg.logRef()} length=${text.length}")
                            val r = handlers.asSequence()
                                // use sequence to avoid greedy mapping, to find the first handler with non null result
                                .map { it(WampMessageMeta(ocppVersion, ocppId), msg) }
                                .filterNotNull()
                                .firstOrNull()
                                ?: WampMessage.CallError(
                                    msg.msgId,
                                    MessageErrorCode.INTERNAL_ERROR,
                                    "No handler found",
                                    "{}"
                                )
                            val json = r.toJson()
                            logger.info("[$ocppId] [IN][CALL][RESP] -> ${r.logRef()} length=${json.length}")
                            FrameLog.outbound("[$ocppId]", json)
                            wampConnection?.websocket?.send(json)
                        }
                    } catch (e: TimeoutCancellationException) {
                        logger.error("[$ocppId] timeout during call handling of ${msg.logRef()}")
                        val json = WampMessage.CallError(
                            msg.msgId,
                            MessageErrorCode.INTERNAL_ERROR,
                            "Timeout during call handling",
                            "{}"
                        ).toJson()
                        FrameLog.outbound("[$ocppId]", json)
                        wampConnection?.websocket?.send(json)
                    } catch (e: kotlin.coroutines.cancellation.CancellationException) {
                        throw e
                    } catch (e: Exception) {
                        // logged here rather than by the uncaught exception handler, which prints the message: it
                        // may quote the payload. Nothing is answered, as before.
                        logger.error("[$ocppId] ${msg.logRef()} call failed: ${e.javaClass.name}", e.redactedForLog())
                    }
                }
            }
        }
    }

    private fun onConnectedTo(connectedWebsocket: WebSocket) {
        if (wampConnection?.websocket == connectedWebsocket) {
            logger.info("[$ocppId] already connected to $loggableServerUri - ignored")
            return
        }

        if (wampConnection != null) {
            logger.warn("connected to new websocket while another one was already open - closing previous one")
            // cleanup call manager and websocket
            wampConnection?.close(CLEANUP_CLOSURE_STATUS, "reconnection")
            wampConnection = null
        }

        wampConnection = WampConnection(
            connectedWebsocket,
            WampCallManager(logger, { m: String -> connectedWebsocket.send(m) }, timeoutInMs)
        )
        connectionState = ConnectionState.CONNECTED
    }

    override fun sendBlocking(message: WampMessage): WampMessage {
        return getCallManager().callBlocking("[$ocppId]", Clock.System.now(), message)
    }

    override fun onAction(handler: WampOnActionHandler) {
        handlers.add(handler)
    }

    private fun getCallManager(): WampCallManager {
        return wampConnection?.callManager
            ?: throw NotConnectedException("not connected to $loggableServerUri")
    }

    /**
     * Closes the web socket, mapping reserved close codes to [NORMAL_CLOSURE_STATUS].
     *
     * OkHttp's [WebSocket.close] throws when given a reserved code. Notably, a peer closing
     * without a status code is reported as 1005 (CLOSE_NO_STATUS_CODE), which is reserved, so
     * echoing it back here would throw. We map any reserved/out-of-range code to 1000.
     */
    private fun WebSocket.closeSafely(code: Int, reason: String?) {
        val safeCode = if (code in 1000..4999 && code !in 1004..1006 && code !in 1015..2999) {
            code
        } else {
            NORMAL_CLOSURE_STATUS
        }
        close(safeCode, reason)
    }

    companion object {
        const val NORMAL_CLOSURE_STATUS = 1000
        const val CLEANUP_CLOSURE_STATUS = 1001

        private val logger = LoggerFactory.getLogger(OkHttpOcppWampClient::class.java)
    }

    class WampConnection(val websocket: WebSocket, val callManager: WampCallManager) {
        fun close(code: Int, reason: String) {
            callManager.close()
            websocket.close(code, reason)
        }
    }
}
