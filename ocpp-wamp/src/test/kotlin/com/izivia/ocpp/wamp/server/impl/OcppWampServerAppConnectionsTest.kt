package com.izivia.ocpp.wamp.server.impl

import com.izivia.ocpp.OcppVersion.OCPP_1_6
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.websocket.PushPullAdaptingWebSocket
import org.http4k.websocket.WsMessage
import org.http4k.websocket.WsStatus
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.*
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.time.Clock
import kotlin.time.Instant

/**
 * Drives [OcppWampServerApp] with in-memory sockets: a control frame arriving on a replaced or closed connection
 * cannot be produced deterministically over a real network connection.
 */
class OcppWampServerAppConnectionsTest {
    private val now = Instant.parse("2026-10-27T06:00:00Z")
    private val pongEvents = CopyOnWriteArrayList<WsControlFrameEvent>()
    private val pingEvents = CopyOnWriteArrayList<WsControlFrameEvent>()
    private val reconnectEvents = CopyOnWriteArrayList<String>()
    private val closeEvents = CopyOnWriteArrayList<String>()

    @Volatile
    private var pongHandler: (WsControlFrameEvent) -> Unit = { pongEvents.add(it) }

    private val app = OcppWampServerApp(
        ocppVersions = setOf(OCPP_1_6),
        handlers = { emptyList() },
        listeners = EventsListeners(
            onWsReconnectHandler = { id, _ -> reconnectEvents.add(id) },
            onWsCloseHandler = { id, _ -> closeEvents.add(id) },
            onWsPongHandler = { pongHandler(it) },
            onWsPingHandler = { pingEvents.add(it) }
        ),
        ocppWsEndpoint = OcppWsEndpoint("ws"),
        settings = OcppWampServerSettings(),
        clock = object : Clock {
            override fun now() = now
        }
    )
    private val routing = app.newRoutingHandler()

    @Test
    fun `should report control frames of the current connection`() {
        val socket = connect("CS1")

        socket.receivePong()
        socket.receivePing()

        expectThat(pongEvents).hasSize(1).first().and {
            get { ocppId }.isEqualTo("CS1")
            get { wsConnectionId }.isNotBlank()
            get { headers }.contains("Sec-WebSocket-Protocol" to "ocpp1.6")
            get { receivedAt }.isEqualTo(now)
        }
        expectThat(pingEvents).hasSize(1).first().get { wsConnectionId }
            .isEqualTo(pongEvents.first().wsConnectionId)
    }

    @Test
    fun `should drop control frames of a replaced connection`() {
        val previous = connect("CS1")
        val current = connect("CS1")

        previous.receivePong()
        previous.receivePing()
        expectThat(pongEvents).isEmpty()
        expectThat(pingEvents).isEmpty()

        current.receivePong()
        current.receivePing()
        expectThat(pongEvents).hasSize(1)
        expectThat(pingEvents).hasSize(1)
    }

    @Test
    fun `should drop control frames of a closed connection`() {
        val socket = connect("CS1")

        socket.disconnect()
        socket.receivePong()
        socket.receivePing()

        expectThat(pongEvents).isEmpty()
        expectThat(pingEvents).isEmpty()
    }

    @Test
    fun `should keep the other stations reporting when one connection is replaced`() {
        val other = connect("CS2")
        connect("CS1")
        connect("CS1")

        other.receivePong()

        expectThat(pongEvents.map { it.ocppId }).containsExactly("CS2")
    }

    @Test
    fun `should deliver an accepted frame before notifying the reconnection`() {
        val previous = connect("CS1")
        val release = blockNextPong { previous.receivePong() }

        val reconnection = thread { connect("CS1") }
        reconnection.join(300)
        expectThat(reconnectEvents).isEmpty()

        release()
        reconnection.join(5_000)
        expectThat(pongEvents).hasSize(1)
        expectThat(reconnectEvents).containsExactly("CS1")
    }

    @Test
    fun `should deliver an accepted frame before notifying the close`() {
        val socket = connect("CS1")
        val release = blockNextPong { socket.receivePong() }

        val closing = thread { socket.disconnect() }
        closing.join(300)
        expectThat(closeEvents).isEmpty()

        release()
        closing.join(5_000)
        expectThat(pongEvents).hasSize(1)
        expectThat(closeEvents).containsExactly("CS1")
    }

    /**
     * Receives a pong in another thread whose handler blocks until the returned function is called, which then
     * waits for that thread to end.
     */
    private fun blockNextPong(receivePong: () -> Unit): () -> Unit {
        val started = CountDownLatch(1)
        val released = CountDownLatch(1)
        pongHandler = {
            started.countDown()
            released.await(5, TimeUnit.SECONDS)
            pongEvents.add(it)
        }
        val receiving = thread { receivePong() }
        expectThat(started.await(5, TimeUnit.SECONDS)).isTrue()
        return {
            released.countDown()
            receiving.join(5_000)
        }
    }

    private fun connect(ocppId: String): FakeWebsocket =
        FakeWebsocket().also {
            routing(Request(GET, "/ws/$ocppId").header("Sec-WebSocket-Protocol", "ocpp1.6")).consumer(it)
        }

    private class FakeWebsocket : PushPullAdaptingWebSocket(), ControlFrameAwareWebsocket {
        private val pingHandlers = CopyOnWriteArrayList<() -> Unit>()
        private val pongHandlers = CopyOnWriteArrayList<() -> Unit>()

        override fun onPing(fn: () -> Unit) {
            pingHandlers.add(fn)
        }

        override fun onPong(fn: () -> Unit) {
            pongHandlers.add(fn)
        }

        fun receivePing() = pingHandlers.forEach { it() }

        fun receivePong() = pongHandlers.forEach { it() }

        override fun send(message: WsMessage) = Unit

        // like a real connection, closing only completes once the peer answers, see disconnect
        override fun close(status: WsStatus) = Unit

        fun disconnect() = triggerClose(WsStatus.NORMAL)
    }
}
