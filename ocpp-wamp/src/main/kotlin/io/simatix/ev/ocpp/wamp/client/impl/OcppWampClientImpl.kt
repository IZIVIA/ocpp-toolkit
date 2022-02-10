package io.simatix.ev.ocpp.wamp.client.impl

import io.simatix.ev.ocpp.CSOcppId
import io.simatix.ev.ocpp.OcppVersion
import io.simatix.ev.ocpp.wamp.client.OcppWampClient
import io.simatix.ev.ocpp.wamp.messages.WampMessage
import io.simatix.ev.ocpp.wamp.messages.WampMessageType
import org.http4k.asString
import org.http4k.client.WebsocketClient
import org.http4k.core.Uri
import org.http4k.websocket.Websocket
import org.http4k.websocket.WsMessage
import org.slf4j.LoggerFactory
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class OcppWampClientImpl(target:Uri, val ocppId:CSOcppId, val ocppVersion:OcppVersion, val timeoutInMs:Long = 30_000) : OcppWampClient {
    val serverUri = target.path("${target.path.removeSuffix("/")}/$ocppId")

    var ws: Websocket? = null
    var lastResponse:FutureResponse? = null

    override fun connect() {
        logger.info("connecting to $serverUri with ocpp version $ocppVersion")
        val latch = CountDownLatch(1)
        val websocket = WebsocketClient.nonBlocking(
            serverUri, headers = listOf("Sec-WebSocket-Protocol" to ocppVersion.subprotocol)) {
            logger.info("connected to $serverUri")
            ws = it
            latch.countDown()
        }
        websocket.onError {
            logger.warn("error with web socket connection to $serverUri: $it", it)
            latch.countDown()
        }
        websocket.onClose {
            if (ws != null) {
                logger.info("connection lost to $serverUri")
                // TODO: auto reconnect strategy
            }
        }
        websocket.onMessage {
            val msgString = it.body.payload.asString()
            val msg = WampMessage.parse(msgString)
            if (msg == null) {
                logger.warn("can't parse wamp message from server: $msgString")
                return@onMessage
            }
            when {
                msg.msgType == WampMessageType.CALL_RESULT || msg.msgType == WampMessageType.CALL_ERROR -> {
                    val pending = lastResponse
                    if (pending == null) {
                        logger.warn("got a call result/error with no pending call - discarding $msgString")
                        return@onMessage
                    }
                    if (pending.msg.msgId != msg.msgId) {
                        logger.warn("got a call result/error not corresponding to pending call message id ${pending.msg.msgId} - discarding $msgString")
                        return@onMessage
                    }
                    pending.response = msg
                    pending.latch.countDown()
                }
                else -> {
                    TODO("call from server not implemented yet")
                }
            }
        }
        latch.await(timeoutInMs, TimeUnit.MILLISECONDS)
        if (ws == null) {
            throw IllegalStateException("timeout connecting to $serverUri")
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
        ws?.send(WsMessage(message.toJson()))
        lastResponse?.latch?.await(timeoutInMs, TimeUnit.MILLISECONDS)
        val response = lastResponse?.response
        if (response != null) {
            lastResponse = null
            return response
        } else {
            lastResponse = null
            throw IllegalStateException("timeout calling server with ${message.toJson()}")
        }
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
}

data class FutureResponse(val msg:WampMessage, val latch:CountDownLatch = CountDownLatch(1), var response:WampMessage? = null)

fun main() {
    val client = OcppWampClientImpl(Uri.of("ws://localhost:5000/ws"), "TEST1", OcppVersion.OCPP_1_6)
    client.connect()
    println("sending heartbeat...")
    val r = client.sendBlocking(WampMessage.Call("1", "Heartbeat", "{}"))
    println("heartbeat response: $r")
    client.close()
    println("done")
}
