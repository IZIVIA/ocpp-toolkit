package com.izivia.ocpp.wamp.server.impl

import com.izivia.ocpp.wamp.server.WebSocketUpgradeDecision
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.undertow.server.HttpHandler
import io.undertow.server.HttpServerExchange
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.xnio.XnioExecutor
import org.xnio.XnioIoThread
import strikt.api.expect
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNull
import strikt.assertions.isSameInstanceAs
import java.time.Duration
import java.util.concurrent.Executor
import java.util.concurrent.RejectedExecutionException
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

// the handler on a mocked exchange, whose dispatch runs its task at once and whose connection is closed, so that
// no answer reaches Undertow
class DispatchedAdmissionHandlerTest {
    private val ioThread = mockk<XnioIoThread>(relaxed = true)
    private val exch = mockk<HttpServerExchange>(relaxed = true) {
        every { ioThread } returns this@DispatchedAdmissionHandlerTest.ioThread
        every { connection.isOpen } returns false
        every { dispatch(any<Executor>(), any<Runnable>()) } answers {
            secondArg<Runnable>().run()
            self as HttpServerExchange
        }
    }

    init {
        every { ioThread.executeAfter(any(), any(), any()) } returns mockk<XnioExecutor.Key>(relaxed = true)
    }

    @Test
    fun `admission failing with a VirtualMachineError propagates it rather than answering a 500`() {
        val failure = StackOverflowError("admission")
        val handler = handler(admit = { throw failure })

        val thrown = assertThrows<StackOverflowError> { handler.handleRequest(exch) }

        expectThat(thrown).isSameInstanceAs(failure)
        verify(exactly = 0) { ioThread.execute(any()) }
    }

    @Test
    fun `admission whose timer the I O thread rejects gives its slot back`() {
        // XNIO rejects a timer once the I/O thread is shutting down
        every { ioThread.executeAfter(any(), any(), any()) } throws
            RejectedExecutionException("I/O thread shutting down") andThen
            mockk<XnioExecutor.Key>(relaxed = true)
        val submitted = AtomicInteger()
        val handler = handler(executor = Executor { submitted.incrementAndGet() })

        val rejected = runCatching { handler.handleRequest(exch) }
        handler.handleRequest(exch)

        // the only slot is free again, so the second handshake is submitted rather than refused as overloaded
        expect {
            that(rejected.exceptionOrNull()).isNull()
            that(submitted.get()).isEqualTo(1)
        }
    }

    @Test
    fun `handshake arriving once the server stops is refused without being admitted nor taking a slot`() {
        val stopped = AtomicBoolean(true)
        val submitted = AtomicInteger()
        val handler = handler(executor = Executor { submitted.incrementAndGet() }, stopped = stopped)

        handler.handleRequest(exch)
        val dispatchedWhileStopped = submitted.get()
        stopped.set(false)
        handler.handleRequest(exch)

        expect {
            that(dispatchedWhileStopped).isEqualTo(0)
            that(submitted.get()).isEqualTo(1)
        }
        verify(exactly = 1) { exch.setStatusCode(503) }
        verify(exactly = 0) { exch.responseHeaders }
        verify(exactly = 1) { exch.dispatch(any<Executor>(), any<Runnable>()) }
    }

    private fun handler(
        admit: (HttpServerExchange) -> WebSocketUpgradeDecision? = { null },
        executor: Executor = Executor { it.run() },
        stopped: AtomicBoolean = AtomicBoolean(),
    ) = DispatchedAdmissionHandler(
        admit,
        HttpHandler { },
        AdmissionExecution.Dispatched(maxConcurrent = 1, timeout = Duration.ofSeconds(10), executor),
        stopped,
    )
}
