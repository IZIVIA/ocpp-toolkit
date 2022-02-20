package io.simatix.ev.ocpp.wamp.client.impl

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.cio.websocket.*
import io.simatix.ev.ocpp.CSOcppId
import io.simatix.ev.ocpp.OcppVersion
import io.simatix.ev.ocpp.wamp.client.OcppWampClient
import io.simatix.ev.ocpp.wamp.client.WampOnActionHandler
import io.simatix.ev.ocpp.wamp.core.WampCallManager
import io.simatix.ev.ocpp.wamp.messages.WampMessage
import io.simatix.ev.ocpp.wamp.messages.WampMessageMeta
import io.simatix.ev.ocpp.wamp.messages.WampMessageType
import kotlinx.coroutines.sync.Mutex
import org.http4k.core.Uri
import org.slf4j.LoggerFactory

class KtorOcppWampClientImpl(target: Uri, val ocppId:CSOcppId, val ocppVersion: OcppVersion, val timeoutInMs:Long = 30_000) :
    OcppWampClient {
    val serverUri = target.path("${target.path.removeSuffix("/")}/$ocppId")

    private var callManager: WampCallManager? = null
    private val handlers = mutableListOf<WampOnActionHandler>()

    val client = HttpClient(CIO) {
        install(WebSockets)
    }
    private var websocket: DefaultClientWebSocketSession? = null

    override suspend fun connect(onConnect: ()->Unit) {
        client.webSocket(serverUri.toString(), request = { header("Sec-WebSocket-Protocol", ocppVersion.subprotocol)}) {
            logger.info("connected to $serverUri")
            val ws = this
            callManager = WampCallManager(logger, { m:String -> ws.send(m)}, timeoutInMs)
            websocket = this

            onConnect()

            while(true) {
                val frame = incoming.receive() as? Frame.Text ?: continue
                onMessage(frame.readText())
            }
        }
    }

    suspend fun Mutex.await() {
        lock()
        unlock()
    }

    suspend fun onMessage(msgString:String) {
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
                    websocket?.send(r.toJson())
                }

                else -> {
                    logger.warn("unsupported wamp message type: ${msg.msgType} - message = $msgString")
                }
            }
        }
    }

    override suspend fun close() {
        logger.info("disconnecting from $serverUri")
        callManager?.close()
        val closingWs = websocket
        websocket = null
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
        private val logger = LoggerFactory.getLogger(KtorOcppWampClientImpl::class.java)
    }
}
