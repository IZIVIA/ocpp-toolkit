package com.izivia.ocpp.wamp.core

import com.izivia.ocpp.wamp.messages.WampMessage
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.slf4j.Logger
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.time.Duration.Companion.milliseconds

class WampCallManager(
    private val logger: Logger,
    private val send: (str: String) -> Unit,
    val defaultTimeoutInMs: Long,
    private val shutdown: AtomicBoolean = AtomicBoolean(false)
) {
    private val clock = Clock.System
    private var currentCall: WampCall? = null

    fun callBlocking(logContext: String, startCall: Instant, message: WampMessage, specificTimeoutInMs: Long? = null): WampMessage {
        val now = clock.now()
        val timeoutInMs = specificTimeoutInMs ?: defaultTimeoutInMs
        synchronized(this) {
            while (currentCall != null && (clock.now() - startCall).inWholeMilliseconds < timeoutInMs) {
                Thread.sleep(10)
            }
            if (currentCall != null) {
                throw IllegalStateException("$logContext can't send a call when another one is pending")
            }
            currentCall = WampCall(logContext, message, timeoutInMs)
        }
        val pendingCallLatency = clock.now() - now
        if (pendingCallLatency > 400.milliseconds) {
            logger.warn("$logContext [OUT][CALL] queued request during $pendingCallLatency")
        }
        val msgString = message.toJson()
        val latency = clock.now() - startCall
        logger.info("$logContext [OUT][CALL][REQ] => $msgString -- [latency=${latency.inWholeMilliseconds}ms]")
        send(msgString)
        currentCall?.latch?.await(timeoutInMs - ((clock.now() - startCall).inWholeMilliseconds), TimeUnit.MILLISECONDS)
        val response = currentCall?.response
        if (response != null) {
            currentCall = null
            val duration = clock.now() - startCall
            logger.info(
                "$logContext [OUT][CALL][RESP] <= ${response.toJson()} -- " +
                    "[duration=${duration.inWholeMilliseconds}ms]" +
                    "[latency=${latency.inWholeMilliseconds}ms]"
            )
            return response
        } else {
            currentCall = null
            throw TimeoutException("$logContext timeout calling with $msgString")
        }
    }

    fun handleResult(logContext: String, message: WampMessage) {
        val msgString = message.toJson()
        val pending = currentCall
        when {
            pending == null -> {
                logger.warn("$logContext got a call result/error with no pending call - discarding $msgString")
            }

            pending.msg.msgId != message.msgId -> {
                logger.warn(
                    "$logContext got a call result/error not corresponding to pending call" +
                        " message id ${pending.msg.msgId} - discarding $msgString"
                )
            }

            else -> {
                logger.info("$logContext <= $msgString")
                pending.response = message
                pending.latch.countDown()
            }
        }
    }

    fun close() {
        currentCall?.also {
            logger.warn("${it.logContext} closing connection while a pending call is in progress: ${it.msg}")
        }
    }

    fun await() {
        val now = Clock.System.now()
        synchronized(this) {
            while (
                currentCall != null &&
                (Clock.System.now() - now).inWholeMilliseconds < (currentCall?.timeoutInMs ?: Long.MAX_VALUE)
            ) {
                Thread.sleep(10)
            }
            val call = currentCall

            if (call != null) {
                logger.warn("${call.logContext} current call not released within timeout")
            }
        }
    }

    private data class WampCall(
        val logContext: String,
        val msg: WampMessage,
        var timeoutInMs: Long,
        val latch: CountDownLatch = CountDownLatch(1),
        var response: WampMessage? = null
    )
}
