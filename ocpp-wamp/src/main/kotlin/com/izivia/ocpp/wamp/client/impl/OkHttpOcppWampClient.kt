package com.izivia.ocpp.wamp.client.impl

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.wamp.client.ConnectionListener
import com.izivia.ocpp.wamp.client.ConnectionState
import com.izivia.ocpp.wamp.client.OcppWampClient
import com.izivia.ocpp.wamp.client.WampOnActionHandler
import com.izivia.ocpp.wamp.core.WampCallManager
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.messages.WampMessageMetaHeaders
import com.izivia.ocpp.wamp.messages.WampMessageType
import kotlinx.coroutines.*
import kotlinx.datetime.Clock
import okhttp3.*
import org.http4k.core.Uri
import org.slf4j.LoggerFactory
import java.io.IOException
import java.net.ConnectException
import java.util.concurrent.*
import javax.net.ssl.HostnameVerifier

class OkHttpOcppWampClient(
    target: Uri,
    val ocppId: CSOcppId,
    val ocppVersion: OcppVersion,
    val timeoutInMs: Long = 30_000,
    private val headers: WampMessageMetaHeaders = emptyList(),
    hostnameVerifier: HostnameVerifier = HostnameVerifier { _, _ -> true }
) : OcppWampClient {
    val serverUri = target.path("${target.path.removeSuffix("/")}/$ocppId")
    val debugContext = "$ocppId -> $serverUri"
    override val state: ConnectionState
        get() = connectionState

    private val handlers = mutableListOf<WampOnActionHandler>()
    private var connectionListener: ConnectionListener? = null

    private val socketOkHttpClient = OkHttpClient.Builder()
        .readTimeout(timeoutInMs, TimeUnit.MILLISECONDS)
        .connectTimeout(timeoutInMs, TimeUnit.MILLISECONDS)
        .hostnameVerifier(hostnameVerifier)
        .build()

    private var wampConnection: WampConnection? = null

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
                logger.error("[$debugContext] error when connecting - $t")
                cleanupStateOnClose()
                throw IOException("[$debugContext] connection failed: $t", t)
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
        var error: Throwable? = TimeoutException("connection timed out to $serverUri")
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
                "can't async connect to $serverUri when connection state is $connectionState"
            )
        }
        logger.debug("[$ocppId] connecting to $serverUri - current connection state = $connectionState")
        connectionState = ConnectionState.CONNECTING

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
                    logger.warn("[$ocppId] web socket closing $code $reason - state = $connectionState")
                    if (code == CLEANUP_CLOSURE_STATUS) {
                        logger.info("[$ocppId] connection closed to $serverUri due to reconnection")
                        closingWebSocket.closeSafely(code, reason)
                        return
                    }

                    closingWebSocket.closeSafely(code, reason)
                    val previousState = connectionState
                    connectionState = ConnectionState.DISCONNECTED
                    wampConnection = null
                    when (previousState) {
                        ConnectionState.CONNECTING -> {
                            listener.onConnectionFailure(
                                IOException(
                                    "[$debugContext] websocket closed:" +
                                        " ws=$closingWebSocket; code=$code; reason=$reason"
                                )
                            )
                        }

                        else -> {
                            listener.onConnectionLost(
                                IOException(
                                    "[$debugContext] websocket closed:" +
                                        " ws=$closingWebSocket; code=$code; reason=$reason"
                                )
                            )
                        }
                    }
                }

                override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                    logger.info("[$ocppId] connection closed to $serverUri - code=$code; reason=$reason")
                    connectionState = ConnectionState.DISCONNECTED
                    wampConnection = null
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    connectionState = ConnectionState.DISCONNECTED
                    wampConnection = null
                    if (connectionState == ConnectionState.CONNECTING) {
                        if (t is ConnectException) {
                            logger.warn("[$ocppId] web socket failed to connect to $serverUri: $t")
                        } else {
                            logger.warn("[$ocppId] web socket connection failure to $serverUri: $t", t)
                        }
                        listener.onConnectionFailure(t)
                    } else {
                        logger.warn("[$ocppId] web socket failure with connection to $serverUri: $t", t)
                        listener.onConnectionLost(t)
                    }
                }
            }
        )
    }

    private fun onMessage(text: String) {
        CoroutineScope(Dispatchers.Default).launch {
            val msgString = text
            logger.info("OkHttpOcppWampClient onMessage : $msgString")
            val msg = WampMessage.parse(msgString)

            if (msg == null) {
                logger.warn("can't parse wamp message from server: $msgString")
            } else {
                when (msg.msgType) {
                    WampMessageType.CALL_RESULT, WampMessageType.CALL_ERROR -> {
                        // outcoming call result
                        try {
                            withTimeout(timeoutInMs) {
                                logger.info("OkHttpOcppWampClient connect callManager : ${wampConnection?.callManager}")
                                wampConnection?.callManager?.handleResult("[$ocppId]", msg)
                            }
                        } catch (e: TimeoutCancellationException) {
                            logger.error(
                                "[$ocppId] timeout during call result handling of" +
                                    " ${msg.action} - $msgString -- ${e.message}"
                            )
                        }
                    }

                    WampMessageType.CALL -> {
                        try {
                            withTimeout(timeoutInMs) {
                                // incoming call
                                logger.info("[$ocppId] <- ${msg.action} - $msgString")
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
                                logger.info("[$ocppId] -> ${r.toJson()}")
                                wampConnection?.websocket?.send(r.toJson())
                            }
                        } catch (e: TimeoutCancellationException) {
                            logger.error(
                                "[$ocppId] timeout during call handling of" +
                                    " ${msg.action} - $msgString -- ${e.message}"
                            )
                            wampConnection?.websocket?.send(
                                WampMessage.CallError(
                                    msg.msgId,
                                    MessageErrorCode.INTERNAL_ERROR,
                                    "Timeout during call handling",
                                    "{}"
                                ).toJson()
                            )
                        }
                    }
                }
            }
        }
    }

    private fun onConnectedTo(connectedWebsocket: WebSocket) {
        if (wampConnection?.websocket == connectedWebsocket) {
            logger.info("[$ocppId] already connected to $serverUri - ignored")
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
            ?: throw IllegalStateException("not connected to $serverUri")
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
