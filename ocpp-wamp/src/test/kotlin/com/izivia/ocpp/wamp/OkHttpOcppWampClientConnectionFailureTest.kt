package com.izivia.ocpp.wamp

import com.izivia.ocpp.OcppVersion.OCPP_1_6
import com.izivia.ocpp.wamp.client.ConnectionListener
import com.izivia.ocpp.wamp.client.OcppWampClient
import org.http4k.core.Uri
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isEqualTo
import strikt.assertions.isTrue
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

/**
 * Nothing listens on the target port, so every attempt fails while the client is still connecting.
 */
class OkHttpOcppWampClientConnectionFailureTest {
    private val target = Uri.of("ws://localhost:${getFreePort()}/ws")

    @Test
    fun `should notify a connection failure, not a connection lost, when connecting fails`() {
        val listener = RecordingListener()
        val client = newClient(autoReconnect = false)

        expectThrows<IOException> { client.connect(listener) }

        expectThat(listener.failures.get()).isEqualTo(1)
        expectThat(listener.losses.get()).isEqualTo(0)
    }

    @Test
    fun `should not report a connection lost while auto-reconnect attempts fail`() {
        val listener = RecordingListener()
        val client = newClient(autoReconnect = true)

        client.connect(listener)
        try {
            expectThat(listener.firstFailure.await(5, TimeUnit.SECONDS)).isTrue()
        } finally {
            client.close()
        }

        expectThat(listener.losses.get()).isEqualTo(0)
    }

    private fun newClient(autoReconnect: Boolean) =
        OcppWampClient.newClient(target, "chargepoint1", OCPP_1_6, timeoutInMs = 5_000, autoReconnect = autoReconnect)

    private class RecordingListener : ConnectionListener {
        val failures = AtomicInteger()
        val losses = AtomicInteger()
        val firstFailure = CountDownLatch(1)

        override fun onConnectionFailure(t: Throwable) {
            failures.incrementAndGet()
            firstFailure.countDown()
        }

        override fun onConnectionLost(t: Throwable?) {
            losses.incrementAndGet()
        }
    }
}
