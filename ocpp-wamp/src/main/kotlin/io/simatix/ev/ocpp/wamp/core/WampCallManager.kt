package io.simatix.ev.ocpp.wamp.core

import io.simatix.ev.ocpp.wamp.messages.WampMessage
import org.http4k.websocket.Websocket
import org.http4k.websocket.WsMessage
import org.slf4j.Logger
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class WampCallManager(
    private val logger:Logger,
    private val ws:Websocket,
    val timeoutInMs:Long) {

    private var currentCall:WampCall? = null

    fun callBlocking(logContext:String, message:WampMessage): WampMessage {
        if (currentCall != null) {
            throw IllegalStateException("$logContext can't send a call when another one is pending")
        }
        currentCall = WampCall(logContext, message)
        val msgString = message.toJson()
        logger.info("$logContext => $msgString")
        ws?.send(WsMessage(msgString))
        currentCall?.latch?.await(timeoutInMs, TimeUnit.MILLISECONDS)
        val response = currentCall?.response
        if (response != null) {
            currentCall = null
            return response
        } else {
            currentCall = null
            throw IllegalStateException("$logContext timeout calling with $msgString")
        }
    }

    fun handleResult(logContext:String, message:WampMessage) {
        val msgString = message.toJson()
        val pending = currentCall
        when {
            pending == null -> {
                logger.warn("$logContext got a call result/error with no pending call - discarding $msgString")
            }
            pending.msg.msgId != message.msgId -> {
                logger.warn("$logContext got a call result/error not corresponding to pending call" +
                        " message id ${pending.msg.msgId} - discarding $msgString")
            }
            else -> {
                logger.info("$logContext <= $msgString")
                pending.response = message
                pending.latch.countDown()
            }
        }
    }

    fun close() {
        if (currentCall != null) {
            logger.warn("closing connection while a pending call is in progress")
        }
    }

    private data class WampCall(val logContext: String, val msg: WampMessage,
                                val latch: CountDownLatch = CountDownLatch(1),
                                var response: WampMessage? = null)
}
