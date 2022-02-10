package io.simatix.ev.ocpp.wamp.client.impl

import io.simatix.ev.ocpp.CSOcppId
import io.simatix.ev.ocpp.OcppVersion
import io.simatix.ev.ocpp.wamp.client.OcppWampClient
import io.simatix.ev.ocpp.wamp.client.WampOnActionHandler
import io.simatix.ev.ocpp.wamp.messages.WampMessage
import io.simatix.ev.ocpp.wamp.messages.WampMessageMeta
import io.simatix.ev.ocpp.wamp.messages.WampMessageType
import org.http4k.asString
import org.http4k.core.Uri
import org.http4k.websocket.Websocket
import org.http4k.websocket.WsMessage
import org.slf4j.LoggerFactory
import java.time.Duration
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class OcppWampClientImpl(target:Uri, val ocppId:CSOcppId, val ocppVersion:OcppVersion, val timeoutInMs:Long = 30_000) : OcppWampClient {
    val serverUri = target.path("${target.path.removeSuffix("/")}/$ocppId")

    private var ws: Websocket? = null
    private var lastResponse:FutureResponse? = null
    private val handlers = mutableListOf<WampOnActionHandler>()

    override fun connect() {
        logger.info("connecting to $serverUri with ocpp version $ocppVersion")
        val websocket = Http4kWebSocketClient.connnectWebsocket(
            uri = serverUri,
            headers = listOf("Sec-WebSocket-Protocol" to ocppVersion.subprotocol),
            timeout = Duration.ofMillis(timeoutInMs),
            onMessage = {
                val msgString = it.body.payload.asString()
                val msg = WampMessage.parse(msgString)
                if (msg == null) {
                    logger.warn("can't parse wamp message from server: $msgString")
                } else {
                    when {
                        msg.msgType == WampMessageType.CALL_RESULT || msg.msgType == WampMessageType.CALL_ERROR -> {
                            val pending = lastResponse
                            when {
                                pending == null -> {
                                    logger.warn("got a call result/error with no pending call - discarding $msgString")
                                }
                                pending.msg.msgId != msg.msgId -> {
                                    logger.warn("got a call result/error not corresponding to pending call" +
                                            " message id ${pending.msg.msgId} - discarding $msgString")
                                }
                                else -> {
                                    logger.info("[$ocppId] <= $msgString")
                                    pending.response = msg
                                    pending.latch.countDown()
                                }
                            }
                        }

                        msg.msgType == WampMessageType.CALL -> {
                            logger.info("[$ocppId] <- ${msg.action} - $msgString")
                            val r = handlers.asSequence()
                                // use sequence to avoid greedy mapping, to find the first handler with non null result

                                .map { it(WampMessageMeta(ocppVersion, ocppId), msg) }
                                .filterNotNull()
                                .firstOrNull()
                                ?: WampMessage.CallError(msg.msgId, "{}")
                            logger.info("[$ocppId] -> ${r.toJson()}")
                            ws?.send(WsMessage(r.toJson()))
                        }

                        else -> {
                            logger.warn("unsupported wamp message type: ${msg.msgType} - message = $msgString")
                        }
                    }
                }
            },
            onError = {
                logger.warn("error with web socket connection to $serverUri: $it", it)
            },
            onClose = {
                if (ws != null) {
                    logger.info("connection lost to $serverUri")
                    // TODO: auto reconnect strategy
                }
            }
        ) {
            logger.info("connected to $serverUri")
            ws = it
        }
    }

    override fun close() {
        if (lastResponse != null) {
            logger.warn("disconnecting from $serverUri while a pending call is in progress")
        } else {
            logger.info("disconnecting from $serverUri")
        }
        val closingWs = ws
        ws = null
        closingWs?.close()
    }

    override fun sendBlocking(message: WampMessage): WampMessage {
        checkConnected()
        if (lastResponse != null) {
            throw IllegalStateException("can't send a call when another one is pending")
        }
        lastResponse = FutureResponse(message)
        val msgString = message.toJson()
        logger.info("[$ocppId] => $msgString")
        ws?.send(WsMessage(msgString))
        lastResponse?.latch?.await(timeoutInMs, TimeUnit.MILLISECONDS)
        val response = lastResponse?.response
        if (response != null) {
            lastResponse = null
            return response
        } else {
            lastResponse = null
            throw IllegalStateException("timeout calling server with $msgString")
        }
    }

    override fun onAction(handler: WampOnActionHandler) {
        handlers.add(handler)
    }

    private fun checkConnected() {
        if (ws == null) {
            logger.error("not connected to $serverUri")
            throw IllegalStateException("not connected to $serverUri")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(OcppWampClientImpl::class.java)
    }

    private data class FutureResponse(val msg:WampMessage, val latch:CountDownLatch = CountDownLatch(1), var response:WampMessage? = null)
}


fun main() {
    val client = OcppWampClientImpl(Uri.of("ws://localhost:5000/ws"), "TEST1", OcppVersion.OCPP_1_6)
    client.connect()
    println("sending heartbeat...")
    val r = client.sendBlocking(WampMessage.Call("1", "Heartbeat", "{}"))
    println("heartbeat response: $r")
    client.close()
    println("done")
}
