package io.simatix.ev.ocpp.wamp.client.impl

import io.simatix.ev.ocpp.CSOcppId
import io.simatix.ev.ocpp.OcppVersion
import io.simatix.ev.ocpp.wamp.client.OcppWampClient
import io.simatix.ev.ocpp.wamp.client.WampOnActionHandler
import io.simatix.ev.ocpp.wamp.core.WampCallManager
import io.simatix.ev.ocpp.wamp.messages.WampMessage
import io.simatix.ev.ocpp.wamp.messages.WampMessageMeta
import io.simatix.ev.ocpp.wamp.messages.WampMessageType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.http4k.core.Uri
import org.slf4j.LoggerFactory
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class OkHttpOcppWampClient(private val target: Uri, val ocppId: CSOcppId, val ocppVersion: OcppVersion, val timeoutInMs: Long = 30_000) : OcppWampClient {
    val serverUri = target.path("${target.path.removeSuffix("/")}/$ocppId")

    private var callManager: WampCallManager? = null
    private val handlers = mutableListOf<WampOnActionHandler>()


    private val socketOkHttpClient = OkHttpClient.Builder()
        .readTimeout(timeoutInMs, TimeUnit.MILLISECONDS)
        .connectTimeout(timeoutInMs, TimeUnit.MILLISECONDS)
        .hostnameVerifier { _, _ -> true }
        .build()
    private var websocket: WebSocket? = null

    override fun connect() {
        logger.info("connecting to $serverUri with ocpp version $ocppVersion")
        val latch = CountDownLatch(1)
        socketOkHttpClient.newWebSocket(
            Request.Builder().url(serverUri.toString())
                .header("Sec-WebSocket-Protocol", ocppVersion.subprotocol)
                .build(),
            object : WebSocketListener() {
                override fun onOpen(webSocket: WebSocket, response: Response) {
                    logger.info("connected to $serverUri")
                    callManager = WampCallManager(logger, { m: String -> webSocket.send(m) }, timeoutInMs)
                    websocket = webSocket
                    latch.countDown()
                }

                override fun onMessage(webSocket: WebSocket, text: String) {
                    CoroutineScope(Dispatchers.Default).launch {
                        val msgString = text
                        val msg = WampMessage.parse(msgString)

                        if (msg == null) {
                            logger.warn("can't parse wamp message from server: $msgString")
                        } else {
                            try {
                                withTimeout(5000L) {
                                    when {
                                        msg.msgType == WampMessageType.CALL_RESULT || msg.msgType == WampMessageType.CALL_ERROR -> {
                                            // outcoming call result
                                            callManager?.handleResult("[$ocppId]", msg)
                                        }

                                        msg.msgType == WampMessageType.CALL -> {
                                            // incoming call
                                            logger.info("[$ocppId] <- ${msg.action} - $msgString")
                                            val r = handlers.asSequence()
                                                // use sequence to avoid greedy mapping, to find the first handler with non null result

                                                .map { it(WampMessageMeta(ocppVersion, ocppId), msg) }
                                                .filterNotNull()
                                                .firstOrNull()
                                                ?: WampMessage.CallError(msg.msgId, "{}")
                                            logger.info("[$ocppId] -> ${r.toJson()}")
                                            websocket?.send(r.toJson())
                                        }

                                        else -> {
                                            logger.warn("unsupported wamp message type: ${msg.msgType} - message = $msgString")
                                        }
                                    }
                                }
                            } catch (e: TimeoutCancellationException) {
                                logger.error(e.message)
                                websocket?.send(WampMessage.CallError(msg.msgId, "{}").toJson())
                            }
                        }
                    }
                }

                override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                    if (websocket != null) {
                        logger.info("connection lost to $serverUri")
                        // TODO: auto reconnect strategy
                    }
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    logger.warn("error with web socket connection to $serverUri: $t", t)
                }
            }
        )
        latch.await(timeoutInMs, TimeUnit.MILLISECONDS)
    }

    override fun close() {
        logger.info("disconnecting from $serverUri")
        callManager?.close()
        val closingWs = websocket
        websocket = null
        callManager = null
        closingWs?.close(NORMAL_CLOSURE_STATUS, null)
    }

    override fun sendBlocking(message: WampMessage): WampMessage {
        return getCallManager().callBlocking("[$ocppId]", message)
    }

    override fun onAction(handler: WampOnActionHandler) {
        handlers.add(handler)
    }

    private fun getCallManager(): WampCallManager {
        val cm = callManager
        if (cm == null) {
            logger.error("not connected to $serverUri")
            throw IllegalStateException("not connected to $serverUri")
        }
        return cm
    }

    companion object {
        const val NORMAL_CLOSURE_STATUS = 1000

        private val logger = LoggerFactory.getLogger(OkHttpOcppWampClient::class.java)

    }
}
