package com.izivia.ocpp.wamp.client.autoreconnect

import com.izivia.ocpp.wamp.client.ConnectionListener
import com.izivia.ocpp.wamp.client.ConnectionState
import com.izivia.ocpp.wamp.client.NotConnectedException
import com.izivia.ocpp.wamp.client.OcppWampClient
import com.izivia.ocpp.wamp.client.WampOnActionHandler
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageType
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.*
import java.io.IOException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlin.time.TimeSource

/**
 * Drives [AutoReconnectOcppWampClient] over a fake client: the window between the connection being dropped and
 * the auto reconnect layer being notified cannot be opened deterministically over a real network connection.
 */
class AutoReconnectOcppWampClientTest {
    private val underlying = DroppableClient()
    private val client = AutoReconnectOcppWampClient(
        underlying,
        "TEST1",
        AutoReconnectConfig(
            callTimeout = 5.seconds,
            baseAutoReconnectDelay = 10.milliseconds,
            minDelayBetweenAttempts = 10.milliseconds
        )
    )

    @AfterEach
    fun tearDown() {
        client.close()
    }

    @Test
    fun `should wait for the reconnection when the connection drops before the connection lost is notified`() {
        client.connect()
        awaitState(ConnectionState.CONNECTED)

        // the connection is gone, but the auto reconnect layer still sees the client as connected
        underlying.dropped = true
        val response = CompletableFuture.supplyAsync { client.sendBlocking(WampMessage.Call("1", "Heartbeat", "{}")) }
        expectThat(underlying.refusedCall.await(5, TimeUnit.SECONDS)).isTrue()

        underlying.notifyConnectionLost()

        expectThat(response.get(5, TimeUnit.SECONDS)) {
            get { msgId }.isEqualTo("1")
            get { msgType }.isEqualTo(WampMessageType.CALL_RESULT)
        }
    }

    @Test
    fun `should give up waiting for the reconnection when the client is closed`() {
        client.connect()
        awaitState(ConnectionState.CONNECTED)

        underlying.dropped = true
        val response = CompletableFuture.supplyAsync { client.sendBlocking(WampMessage.Call("1", "Heartbeat", "{}")) }
        expectThat(underlying.refusedCall.await(5, TimeUnit.SECONDS)).isTrue()

        client.close()

        expectThat(runCatching { response.get(5, TimeUnit.SECONDS) }.exceptionOrNull())
            .isA<ExecutionException>()
            .get { cause }.isA<IllegalStateException>()
    }

    @Test
    fun `should fail the call when the connection lost is never notified`() {
        val shortTimeoutClient = AutoReconnectOcppWampClient(
            underlying,
            "TEST1",
            AutoReconnectConfig(callTimeout = 200.milliseconds)
        )
        try {
            shortTimeoutClient.connect()
            awaitState(ConnectionState.CONNECTED, shortTimeoutClient)

            underlying.dropped = true

            expectThat(runCatching { shortTimeoutClient.sendBlocking(WampMessage.Call("1", "Heartbeat", "{}")) })
                .isFailure()
                .isA<IOException>()
                .get { cause }.isA<NotConnectedException>()
        } finally {
            shortTimeoutClient.close()
        }
    }

    @Test
    fun `should fail the call when the connection keeps dropping until the call timeout`() {
        val shortTimeoutClient = AutoReconnectOcppWampClient(
            underlying,
            "TEST1",
            AutoReconnectConfig(
                callTimeout = 200.milliseconds,
                baseAutoReconnectDelay = 10.milliseconds,
                minDelayBetweenAttempts = 10.milliseconds
            )
        )
        try {
            shortTimeoutClient.connect()
            awaitState(ConnectionState.CONNECTED, shortTimeoutClient)

            // every reconnection succeeds, and every call finds its connection dropped again
            underlying.flapping = true
            underlying.dropped = true
            val response = CompletableFuture.supplyAsync {
                shortTimeoutClient.sendBlocking(WampMessage.Call("1", "Heartbeat", "{}"))
            }

            expectThat(runCatching { response.get(5, TimeUnit.SECONDS) }.exceptionOrNull())
                .isA<ExecutionException>()
                .get { cause }.isA<IOException>()
        } finally {
            underlying.flapping = false
            shortTimeoutClient.close()
        }
    }

    private fun awaitState(expected: ConnectionState, c: OcppWampClient = client) {
        val deadline = TimeSource.Monotonic.markNow() + 5.seconds
        while (c.state != expected && deadline.hasNotPassedNow()) {
            Thread.sleep(5)
        }
        expectThat(c.state).isEqualTo(expected)
    }

    /**
     * A client whose connection can be dropped without notifying its listener, the way
     * [com.izivia.ocpp.wamp.client.impl.OkHttpOcppWampClient] drops it before calling the listener.
     */
    private class DroppableClient : OcppWampClient {
        @Volatile
        var dropped = false

        // when set, a refused call notifies the connection lost, and a reconnection does not restore the connection
        @Volatile
        var flapping = false

        @Volatile
        private var listener: ConnectionListener? = null

        val refusedCall = CountDownLatch(1)

        override val state: ConnectionState
            get() = if (dropped) ConnectionState.DISCONNECTED else ConnectionState.CONNECTED

        override fun connect(listener: ConnectionListener?) {
            this.listener = listener
            if (!flapping) {
                dropped = false
            }
        }

        override fun close() {}

        override fun sendBlocking(message: WampMessage): WampMessage {
            if (dropped) {
                refusedCall.countDown()
                if (flapping) {
                    notifyConnectionLost()
                }
                throw NotConnectedException("not connected")
            }
            return WampMessage.CallResult(message.msgId, "{}")
        }

        override fun onAction(fn: WampOnActionHandler) {}

        fun notifyConnectionLost() {
            listener?.onConnectionLost(IOException("connection lost"))
        }
    }
}
