package com.izivia.ocpp.wamp.server.impl

import com.izivia.ocpp.wamp.server.WebSocketUpgradeDecision
import io.undertow.server.Connectors
import io.undertow.server.HttpHandler
import io.undertow.server.HttpServerExchange
import io.undertow.util.HttpString
import io.undertow.util.SameThreadExecutor
import org.http4k.core.Status
import org.slf4j.LoggerFactory
import org.xnio.XnioExecutor
import java.util.concurrent.RejectedExecutionException
import java.util.concurrent.Semaphore
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

/**
 * Runs the admission of each WebSocket upgrade request off the I/O thread, as [AdmissionExecution.Dispatched] tells.
 *
 * Each handshake goes from pending to either decided, by its admission or by the rejection of its submission, or
 * expired, by its timeout: only the first transition answers the exchange, so that it is never answered twice nor
 * upgraded after its 503.
 */
internal class DispatchedAdmissionHandler(
    private val admit: (HttpServerExchange) -> WebSocketUpgradeDecision?,
    private val upgrade: HttpHandler,
    private val execution: AdmissionExecution.Dispatched,
    private val stopped: AtomicBoolean,
) : HttpHandler {
    private val slots = Semaphore(execution.maxConcurrent)
    private val timeoutMillis = try {
        execution.timeout.toMillis()
    } catch (_: ArithmeticException) {
        Long.MAX_VALUE
    }

    // on the I/O thread
    override fun handleRequest(exch: HttpServerExchange) {
        if (!slots.tryAcquire()) {
            refusal(OVERLOADED).handleRequest(exch)
            return
        }
        // dispatched, or Undertow ends the exchange once this handler returns (Connectors.executeRootHandler). The
        // same-thread executor submits it on this I/O thread once the call is over: a rejection of the admission
        // executor is answered here, rather than by Undertow's bare 503, which would keep the slot
        exch.dispatch(SameThreadExecutor.INSTANCE, Runnable { submit(exch) })
    }

    // on the I/O thread, outside of the handler call
    private fun submit(exch: HttpServerExchange) {
        val handshake = PendingHandshake(exch)
        handshake.timer = exch.ioThread.executeAfter(handshake::expire, timeoutMillis, TimeUnit.MILLISECONDS)
        try {
            (execution.executor ?: exch.connection.worker).execute(handshake::run)
        } catch (_: RejectedExecutionException) {
            // never run, so its slot is given back here rather than when its admission ends
            slots.release()
            handshake.reject()
        }
    }

    private inner class PendingHandshake(private val exch: HttpServerExchange) {
        private val state = AtomicInteger(PENDING)

        // set before the admission is submitted, read once it decides
        @Volatile
        lateinit var timer: XnioExecutor.Key

        // on the I/O thread
        fun expire() {
            if (state.compareAndSet(PENDING, EXPIRED)) answer(refusal(OVERLOADED))
        }

        // on the I/O thread
        fun reject() {
            if (state.compareAndSet(PENDING, DECIDED)) {
                timer.remove()
                answer(refusal(OVERLOADED))
            }
        }

        // on the admission executor: the slot is held until the admission returns, even past its timeout, so that
        // the bound counts the threads blocked in admission
        fun run() {
            try {
                if (state.get() != PENDING) return
                val decision = decide()
                try {
                    exch.ioThread.execute { apply(decision) }
                } catch (_: RejectedExecutionException) {
                    // the I/O thread is exiting: the server stops and closes the connection
                }
            } finally {
                slots.release()
            }
        }

        // fails closed: a throw is a 500, never an upgrade, except a VirtualMachineError, which propagates as in the
        // inline admission
        private fun decide(): WebSocketUpgradeDecision = try {
            admit(exch) ?: NOT_FOUND
        } catch (e: VirtualMachineError) {
            throw e
        } catch (e: Throwable) {
            logger.warn("websocket admission failed with ${e::class.qualifiedName}")
            FAILED
        }

        // on the I/O thread: a decision arriving after the timeout is dropped
        private fun apply(decision: WebSocketUpgradeDecision) {
            if (!state.compareAndSet(PENDING, DECIDED)) return
            timer.remove()
            answer(
                when {
                    decision is WebSocketUpgradeDecision.Refuse -> refusal(decision)
                    stopped.get() -> refusal(STOPPING)
                    else -> upgrade
                }
            )
        }

        // as HttpServerExchange.dispatch(Executor, HttpHandler) does, the handler runs as a root handler, and not on a
        // closed connection
        private fun answer(handler: HttpHandler) {
            if (exch.connection.isOpen) Connectors.executeRootHandler(handler, exch)
        }
    }

    private companion object {
        private val logger = LoggerFactory.getLogger(DispatchedAdmissionHandler::class.java)

        private const val PENDING = 0
        private const val DECIDED = 1
        private const val EXPIRED = 2

        private val OVERLOADED = WebSocketUpgradeDecision.Refuse(
            Status.SERVICE_UNAVAILABLE,
            listOf("Retry-After" to AdmissionExecution.Dispatched.RETRY_AFTER_SECONDS.toString())
        )
        private val STOPPING = WebSocketUpgradeDecision.Refuse(Status.SERVICE_UNAVAILABLE)
        private val NOT_FOUND = WebSocketUpgradeDecision.Refuse(Status.NOT_FOUND)
        private val FAILED = WebSocketUpgradeDecision.Refuse(Status.INTERNAL_SERVER_ERROR)
    }
}

/** Ends the exchange with the status and the headers of [decision], without upgrading it. */
internal fun refusal(decision: WebSocketUpgradeDecision.Refuse) = HttpHandler { exch ->
    exch.statusCode = decision.status.code
    decision.headers.forEach { (name, value) -> exch.responseHeaders.add(HttpString(name), value.orEmpty()) }
    exch.endExchange()
}
