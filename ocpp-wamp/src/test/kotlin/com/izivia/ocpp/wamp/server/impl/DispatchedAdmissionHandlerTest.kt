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
import strikt.api.expectThat
import strikt.assertions.isSameInstanceAs
import java.time.Duration
import java.util.concurrent.Executor
import java.util.concurrent.atomic.AtomicBoolean

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
