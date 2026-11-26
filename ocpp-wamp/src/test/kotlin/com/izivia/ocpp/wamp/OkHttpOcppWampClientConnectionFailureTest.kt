package com.izivia.ocpp.wamp

import com.izivia.ocpp.OcppVersion.OCPP_1_6
import com.izivia.ocpp.wamp.client.ConnectionListener
import com.izivia.ocpp.wamp.client.OcppWampClient
import org.http4k.core.Uri
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import strikt.assertions.isTrue
import java.io.IOException
import java.net.ConnectException
import java.net.ServerSocket
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference
import kotlin.concurrent.thread

/**
 * Unless a test starts its own server, nothing listens on the target port, so every attempt fails while the client
 * is still connecting.
 */
class OkHttpOcppWampClientConnectionFailureTest {
    private val target = Uri.of("ws://localhost:${getFreePort()}/ws")

    @Test
    fun `should notify a connection failure, not a connection lost, when connecting fails`() {
        val listener = RecordingListener()
        val client = newClient(autoReconnect = false)

        expectThrows<IOException> { client.connect(listener) }

        expectThat(listener.failures.get()).isEqualTo(1)
        expectThat(listener.lastFailure.get()).isA<ConnectException>()
        expectThat(listener.losses.get()).isEqualTo(0)
    }

    @Test
    fun `should not report a connection lost while auto-reconnect attempts fail`() {
        // a second failure proves the reconnection cycle does not report a connection lost either
        val listener = RecordingListener(expectedFailureCount = 2)
        val client = newClient(autoReconnect = true)

        client.connect(listener)
        try {
            expectThat(listener.expectedFailures.await(5, TimeUnit.SECONDS)).isTrue()
        } finally {
            client.close()
        }

        expectThat(listener.losses.get()).isEqualTo(0)
    }

    @Test
    fun `should notify a connection failure when connecting times out`() {
        ServerSocket(0).use { server ->
            val stall = thread { stallHandshake(server) }
            val listener = RecordingListener()
            val client = newClient(
                autoReconnect = false,
                target = Uri.of("ws://localhost:${server.localPort}/ws"),
                timeoutInMs = 1_000
            )

            expectThrows<IOException> { client.connect(listener) }

            expectThat(listener.failures.get()).isEqualTo(1)
            expectThat(listener.lastFailure.get()).isA<TimeoutException>()
            server.close()
            stall.join(5_000)
        }
    }

    /**
     * Accepts the connection and trickles response headers without ever ending them, so OkHttp's read timeout
     * never fires and only the client's own connection timeout can end the attempt.
     */
    private fun stallHandshake(server: ServerSocket) {
        runCatching {
            server.accept().use { socket ->
                val out = socket.getOutputStream()
                out.write("HTTP/1.1 101 Switching Protocols\r\n".toByteArray())
                while (!server.isClosed) {
                    out.write("X-Stall: 1\r\n".toByteArray())
                    out.flush()
                    Thread.sleep(100)
                }
            }
        }
    }

    private fun newClient(autoReconnect: Boolean, target: Uri = this.target, timeoutInMs: Long = 5_000) =
        OcppWampClient.newClient(target, "chargepoint1", OCPP_1_6, timeoutInMs, autoReconnect = autoReconnect)

    private class RecordingListener(expectedFailureCount: Int = 1) : ConnectionListener {
        val failures = AtomicInteger()
        val losses = AtomicInteger()
        val lastFailure = AtomicReference<Throwable>()
        val expectedFailures = CountDownLatch(expectedFailureCount)

        override fun onConnectionFailure(t: Throwable) {
            failures.incrementAndGet()
            lastFailure.set(t)
            expectedFailures.countDown()
        }

        override fun onConnectionLost(t: Throwable?) {
            losses.incrementAndGet()
        }
    }
}
