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
import java.lang.management.ManagementFactory
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.time.Clock
import kotlin.time.Duration.Companion.seconds
import kotlin.time.Instant
import kotlin.time.TimeSource

/**
 * Drives [OcppWampServerApp] with in-memory sockets: a control frame arriving on a replaced or closed connection
 * cannot be produced deterministically over a real network connection.
 */
class OcppWampServerAppConnectionsTest {
    private val now = Instant.parse("2026-10-27T06:00:00Z")
    private val pongEvents = CopyOnWriteArrayList<WsControlFrameEvent>()
    private val pingEvents = CopyOnWriteArrayList<WsControlFrameEvent>()
    private val connectEvents = CopyOnWriteArrayList<String>()
    private val reconnectEvents = CopyOnWriteArrayList<String>()
    private val closeEvents = CopyOnWriteArrayList<String>()

    /** The lifecycle events of every station, in the order the listeners received them: `connect:CS1`, ... */
    private val events = CopyOnWriteArrayList<String>()

    @Volatile
    private var pongHandler: (WsControlFrameEvent) -> Unit = { pongEvents.add(it) }

    /** Called with a lifecycle event as its listener is entered, before the event is recorded. */
    @Volatile
    private var lifecycleHook: (String) -> Unit = {}

    private val app = OcppWampServerApp(
        ocppVersions = setOf(OCPP_1_6),
        handlers = { emptyList() },
        listeners = EventsListeners(
            onWsConnectHandler = { id, _ -> lifecycleEvent("connect:$id") { connectEvents.add(id) } },
            onWsReconnectHandler = { id, _ -> lifecycleEvent("reconnect:$id") { reconnectEvents.add(id) } },
            onWsCloseHandler = { id, _ -> lifecycleEvent("close:$id") { closeEvents.add(id) } },
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
    fun `should not notify the close of a replaced connection closing after the current one`() {
        val previous = connect("CS1")
        val current = connect("CS1")

        current.disconnect()
        previous.disconnect()

        expectThat(closeEvents).containsExactly("CS1")
    }

    @Test
    fun `should notify the close of a station once on shutdown`() {
        val socket = connect("CS1")

        app.shutdown()
        expectThat(closeEvents).containsExactly("CS1")

        socket.disconnect()
        expectThat(closeEvents).containsExactly("CS1")
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

    @Test
    fun `should close every connection replaced by concurrent connections`() {
        val previous = connect("CS1")
        // holding the lock of the previous connection, both new connections are registering when it is released
        val release = blockNextPong { previous.receivePong() }

        val sockets = CopyOnWriteArrayList<FakeWebsocket>()
        val connecting = List(2) { thread { sockets.add(connect("CS1")) } }
        awaitBlockedRegistering(connecting)

        release()
        connecting.forEach { it.join(5_000) }

        expectThat(previous.closed).isTrue()
        expectThat(sockets).hasSize(2)
        expectThat(sockets.filterNot { it.closed }).hasSize(1)
        expectThat(connectEvents).containsExactly("CS1")
        expectThat(reconnectEvents).containsExactly("CS1", "CS1")

        // the released pong handler still lets frames through: only the remaining connection is current
        expectThat(pongEvents).hasSize(1)
        sockets.single { it.closed }.receivePong()
        expectThat(pongEvents).hasSize(1)
        sockets.single { !it.closed }.receivePong()
        expectThat(pongEvents).hasSize(2)
    }

    @Test
    fun `should notify a single connect when first connections race`() {
        repeat(200) { round ->
            val ocppId = "CS$round"
            val start = CountDownLatch(1)
            val sockets = CopyOnWriteArrayList<FakeWebsocket>()
            val connecting = List(4) { thread { start.await(); sockets.add(connect(ocppId)) } }
            start.countDown()
            connecting.forEach { it.join(5_000) }

            expectThat(sockets).describedAs("sockets of $ocppId").hasSize(4)
            expectThat(sockets.filterNot { it.closed }).describedAs("open sockets of $ocppId").hasSize(1)
            expectThat(connectEvents.filter { it == ocppId }).describedAs("connections of $ocppId").hasSize(1)
            expectThat(reconnectEvents.filter { it == ocppId }).describedAs("reconnections of $ocppId").hasSize(3)
        }
    }

    @Test
    fun `should notify the close of a station before the connection that follows it`() {
        val previous = connect("CS1")
        val release = blockNextLifecycleEvent("close:CS1") { previous.disconnect() }

        val connecting = thread { connect("CS1") }
        connecting.join(300)
        expectThat(events).containsExactly("connect:CS1")

        release()
        connecting.join(5_000)
        expectThat(events).containsExactly("connect:CS1", "close:CS1", "connect:CS1")
    }

    @Test
    fun `should notify a reconnection after the connection it follows`() {
        val release = blockNextLifecycleEvent("connect:CS1") { connect("CS1") }

        val reconnecting = thread { connect("CS1") }
        reconnecting.join(300)
        expectThat(events).isEmpty()

        release()
        reconnecting.join(5_000)
        expectThat(events).containsExactly("connect:CS1", "reconnect:CS1")
    }

    @Test
    fun `should order the events of a station whatever the case of its id`() {
        val release = blockNextLifecycleEvent("connect:CS1") { connect("CS1") }

        val reconnecting = thread { connect("cs1") }
        reconnecting.join(300)
        expectThat(events).isEmpty()

        release()
        reconnecting.join(5_000)
        expectThat(events).containsExactly("connect:CS1", "reconnect:cs1")
    }

    @Test
    fun `should notify the close on shutdown after the connection being notified`() {
        val release = blockNextLifecycleEvent("connect:CS1") { connect("CS1") }

        val stopping = thread { app.shutdown() }
        stopping.join(300)
        expectThat(events).isEmpty()

        release()
        stopping.join(5_000)
        expectThat(events).containsExactly("connect:CS1", "close:CS1")
    }

    @Test
    fun `should close a replaced connection even when the reconnection listener fails`() {
        val previous = connect("CS1")
        lifecycleHook = { if (it == "reconnect:CS1") error("listener failure") }

        runCatching { connect("CS1") }

        expectThat(previous.closed).isTrue()
    }

    @Test
    fun `should leave the station connected when a close races the next connection`() {
        repeat(200) { round ->
            val ocppId = "CS$round"
            val previous = connect(ocppId)
            val start = CountDownLatch(1)
            val racing = listOf(
                thread { start.await(); previous.disconnect() },
                thread { start.await(); connect(ocppId) }
            )
            start.countDown()
            racing.forEach { it.join(5_000) }

            // the close is notified before the next connection registers, or not at all once it has replaced it
            expectThat(events.filter { it.substringAfter(':') == ocppId }.map { it.substringBefore(':') })
                .describedAs("events of $ocppId")
                .isContainedIn(listOf(listOf("connect", "close", "connect"), listOf("connect", "reconnect")))
        }
    }

    /**
     * Waits until every thread is blocked registering its connection, entering the monitor of the station or of the
     * connection it is about to replace.
     */
    private fun awaitBlockedRegistering(threads: List<Thread>) {
        val threadsBean = ManagementFactory.getThreadMXBean()
        val deadline = TimeSource.Monotonic.markNow() + 5.seconds
        while (
            threads.any { t ->
                val info = threadsBean.getThreadInfo(t.threadId())
                info?.threadState != Thread.State.BLOCKED ||
                    listOf("ChargingStationConnection", "StationLock").none { info.lockName?.contains(it) == true }
            }
        ) {
            check(deadline.hasNotPassedNow()) { "connections not blocked registering" }
            Thread.sleep(5)
        }
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

    private fun lifecycleEvent(event: String, record: () -> Unit) {
        lifecycleHook(event)
        events.add(event)
        record()
    }

    /**
     * Runs [trigger] in another thread whose listener of [event] blocks until the returned function is called, which
     * then waits for that thread to end.
     */
    private fun blockNextLifecycleEvent(event: String, trigger: () -> Unit): () -> Unit {
        val started = CountDownLatch(1)
        val released = CountDownLatch(1)
        lifecycleHook = {
            if (it == event) {
                lifecycleHook = {}
                started.countDown()
                released.await(5, TimeUnit.SECONDS)
            }
        }
        val triggering = thread { trigger() }
        expectThat(started.await(5, TimeUnit.SECONDS)).isTrue()
        return {
            released.countDown()
            triggering.join(5_000)
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

        @Volatile
        var closed = false
            private set

        override fun send(message: WsMessage) = Unit

        // like a real connection, closing only completes once the peer answers, see disconnect
        override fun close(status: WsStatus) {
            closed = true
        }

        fun disconnect() = triggerClose(WsStatus.NORMAL)
    }
}
