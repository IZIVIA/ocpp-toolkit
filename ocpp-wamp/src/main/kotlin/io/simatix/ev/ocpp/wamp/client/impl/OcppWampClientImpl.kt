package io.simatix.ev.ocpp.wamp.client.impl

import io.simatix.ev.ocpp.CSOcppId
import io.simatix.ev.ocpp.OcppVersion
import io.simatix.ev.ocpp.wamp.client.OcppWampClient
import io.simatix.ev.ocpp.wamp.client.WampOnActionHandler
import io.simatix.ev.ocpp.wamp.core.WampCallManager
import io.simatix.ev.ocpp.wamp.messages.WampMessage
import io.simatix.ev.ocpp.wamp.messages.WampMessageMeta
import io.simatix.ev.ocpp.wamp.messages.WampMessageType
import kotlinx.coroutines.runBlocking
import org.http4k.asString
import org.http4k.core.Uri
import org.http4k.websocket.Websocket
import org.http4k.websocket.WsMessage
import org.slf4j.LoggerFactory
import java.time.Duration

class OcppWampClientImpl(target:Uri, val ocppId:CSOcppId, val ocppVersion:OcppVersion, val timeoutInMs:Long = 30_000) : OcppWampClient {
    val serverUri = target.path("${target.path.removeSuffix("/")}/$ocppId")

    private var ws: Websocket? = null
    private var callManager:WampCallManager? = null
    private val handlers = mutableListOf<WampOnActionHandler>()

    override suspend fun connect(onConnect: ()->Unit) {
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
            callManager = WampCallManager(logger, {m:String -> it.send(WsMessage(m))}, timeoutInMs)
            ws = it
        }
    }

    override suspend fun close() {
        logger.info("disconnecting from $serverUri")
        callManager?.close()
        val closingWs = ws
        ws = null
        callManager = null
        closingWs?.close()
    }

    override suspend fun sendBlocking(message: WampMessage): WampMessage {
        return getCallManager().callBlocking("[$ocppId]", message)
    }

    override suspend fun onAction(handler: WampOnActionHandler) {
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
        private val logger = LoggerFactory.getLogger(OcppWampClientImpl::class.java)
    }
}


fun main() {
    runBlocking {
        val client = OcppWampClientImpl(Uri.of("ws://localhost:5000/ws"), "TEST1", OcppVersion.OCPP_1_6)
        client.connect()
        println("sending heartbeat...")
        val r = client.sendBlocking(WampMessage.Call("1", "Heartbeat", "{}"))
        println("heartbeat response: $r")
        client.close()
        println("done")
    }
}
