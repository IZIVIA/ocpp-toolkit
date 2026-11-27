package com.izivia.ocpp.wamp.server.impl

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion.OCPP_1_6
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.utils.OcppErrorCategory
import com.izivia.ocpp.utils.OcppErrorEvent
import com.izivia.ocpp.utils.OcppErrorListener
import com.izivia.ocpp.wamp.getFreePort
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.messages.WampMessageType
import com.izivia.ocpp.wamp.server.OcppWampServer
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import com.izivia.ocpp.wamp.server.asServer
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.http4k.server.Http4kServer
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.all
import strikt.assertions.contains
import strikt.assertions.containsExactlyInAnyOrder
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import strikt.assertions.isFalse
import strikt.assertions.isLessThan
import strikt.assertions.isLessThanOrEqualTo
import strikt.assertions.isNotNull
import strikt.assertions.isNull
import strikt.assertions.isTrue
import strikt.assertions.single
import strikt.assertions.startsWith
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference
import kotlin.concurrent.thread
import kotlin.time.Duration.Companion.seconds
import kotlin.time.measureTime

class OcppWampServerCallsExecutorTest {

    @Test
    fun `should bound the default executor by the number of processors and a queue of 1024 calls`() {
        val settings = OcppWampServerSettings()

        expectThat(settings) {
            get { timeoutInMs }.isEqualTo(30_000)
            get { maxConcurrentCalls }.isEqualTo(32 * Runtime.getRuntime().availableProcessors())
            get { maxQueuedCalls }.isEqualTo(1024)
        }
        val executor = settings.buildCallsExecutor()
        try {
            expectThat(executor).isA<ToolkitOwnedExecutor>().and {
                get { corePoolSize }.isEqualTo(settings.maxConcurrentCalls)
                get { maximumPoolSize }.isEqualTo(settings.maxConcurrentCalls)
                get { queue.remainingCapacity() }.isEqualTo(1024)
                get { rejectedExecutionHandler }.isA<ThreadPoolExecutor.AbortPolicy>()
            }
        } finally {
            (executor as ExecutorService).shutdownNow()
        }
    }

    @Test
    fun `should refuse call bounds that cannot run a call`() {
        expectThrows<IllegalArgumentException> { OcppWampServerSettings(maxConcurrentCalls = 0) }
            .get { message }.isNotNull().contains("maxConcurrentCalls").contains("0")
        expectThrows<IllegalArgumentException> { OcppWampServerSettings(maxConcurrentCalls = -3) }
            .get { message }.isNotNull().contains("maxConcurrentCalls").contains("-3")
        expectThrows<IllegalArgumentException> { OcppWampServerSettings(maxQueuedCalls = -1) }
            .get { message }.isNotNull().contains("maxQueuedCalls").contains("-1")
        expectThat(OcppWampServerSettings(200, maxQueuedCalls = 0).maxQueuedCalls).isEqualTo(0)
    }

    @Test
    fun `should answer a call over the bounds once with an InternalError and report it once`() {
        withServer(OcppWampServerSettings(timeoutInMs = 10_000, maxConcurrentCalls = 2, maxQueuedCalls = 1)) {
            val station = connect("CS1")
            station.send(blocked("block-1"), blocked("block-2"))
            awaitStarted(2)
            station.send(blocked("block-3"))
            station.send(call("over-1"))

            expectThat(station.next()).isEqualTo("""[4,"over-1","InternalError","Internal error",{}]""")
            release()
            expectThat((1..3).map { station.next().resultId() })
                .containsExactlyInAnyOrder("block-1", "block-2", "block-3")
            expectThat(station.noMoreFrames()).isTrue()
            expectThat(runs).isEqualTo(mapOf("block-1" to 1, "block-2" to 1, "block-3" to 1))
            expectThat(events).single().and {
                get { category }.isEqualTo(OcppErrorCategory.TRANSPORT_ERROR)
                get { errorCode }.isEqualTo(MessageErrorCode.INTERNAL_ERROR)
                get { messageId }.isEqualTo("over-1")
            }
            // the rejected call is no longer counted in flight: shutdown does not wait for it
            expectThat(measureTime { wampServer.shutdown() }).isLessThan(5.seconds)
        }
    }

    @Test
    fun `should run calls on at most maxConcurrentCalls named daemon threads`() {
        withServer(OcppWampServerSettings(maxConcurrentCalls = 2, maxQueuedCalls = 2)) {
            val station = connect("CS1")
            station.send(blocked("block-1"), blocked("block-2"), blocked("block-3"), blocked("block-4"))
            awaitStarted(2)
            release()

            expectThat((1..4).map { station.next().resultId() })
                .containsExactlyInAnyOrder("block-1", "block-2", "block-3", "block-4")
            expectThat(threads.toSet()) {
                get { size }.isLessThanOrEqualTo(2)
                all {
                    get { name }.startsWith("ocpp-wamp-call-")
                    get { isDaemon }.isTrue()
                }
            }
        }
    }

    @Test
    fun `should reject a call when every thread is busy and nothing may be queued`() {
        withServer(OcppWampServerSettings(maxConcurrentCalls = 2, maxQueuedCalls = 0)) {
            val station = connect("CS1")
            station.send(blocked("block-1"), blocked("block-2"))
            awaitStarted(2)
            station.send(call("over-1"))

            expectThat(station.next()).isEqualTo("""[4,"over-1","InternalError","Internal error",{}]""")
            release()
            expectThat((1..2).map { station.next().resultId() }).containsExactlyInAnyOrder("block-1", "block-2")
            expectThat(events).single().get { messageId }.isEqualTo("over-1")
        }
    }

    @Test
    fun `should serve another station while capacity remains`() {
        withServer(OcppWampServerSettings(maxConcurrentCalls = 3, maxQueuedCalls = 0)) {
            val busy = connect("CS1")
            val other = connect("CS2")
            busy.send(blocked("block-1"), blocked("block-2"))
            awaitStarted(2)

            other.send(call("other-1"))

            expectThat(other.next().resultId()).isEqualTo("other-1")
            release()
            expectThat((1..2).map { busy.next().resultId() }).containsExactlyInAnyOrder("block-1", "block-2")
            expectThat(events.toList()).isEqualTo(emptyList())
        }
    }

    @Test
    fun `should reject another station's call once one station holds every thread`() {
        // the bound is global, with no fairness between stations
        withServer(OcppWampServerSettings(maxConcurrentCalls = 2, maxQueuedCalls = 0)) {
            val busy = connect("CS1")
            val other = connect("CS2")
            busy.send(blocked("block-1"), blocked("block-2"))
            awaitStarted(2)

            other.send(call("other-1"))

            expectThat(other.next()).isEqualTo("""[4,"other-1","InternalError","Internal error",{}]""")
            release()
            expectThat((1..2).map { busy.next().resultId() }).containsExactlyInAnyOrder("block-1", "block-2")
        }
    }

    @Test
    fun `should wait for the calls in flight then stop the threads of its own executor on shutdown`() {
        withServer(OcppWampServerSettings(timeoutInMs = 10_000, maxConcurrentCalls = 2, maxQueuedCalls = 1)) {
            val station = connect("CS1")
            station.send(blocked("block-1"), blocked("block-2"), blocked("block-3"))
            awaitStarted(2)

            val shutdownDone = CountDownLatch(1)
            val shutdownThread = thread(name = "test-shutdown") {
                wampServer.shutdown()
                shutdownDone.countDown()
            }
            station.awaitShuttingDown()
            expectThat(shutdownDone.count).isEqualTo(1)
            release()

            expectThat((1..3).map { station.next().resultId() })
                .containsExactlyInAnyOrder("block-1", "block-2", "block-3")
            expectThat(shutdownDone.await(10, TimeUnit.SECONDS)).isTrue()
            shutdownThread.join(5_000)
            // awaitTermination returns slightly before a worker thread dies
            threads.toSet().forEach { it.join(5_000) }
            expectThat(threads.toSet()).all { get { isAlive }.isFalse() }
            expectThat((builtExecutor.get() as ExecutorService).isTerminated).isTrue()

            // a second shutdown is harmless
            expectThat(measureTime { wampServer.shutdown() }).isLessThan(5.seconds)
        }
    }

    @Test
    fun `should shut down the default executor returned by an override`() {
        val settings = object : OcppWampServerSettings(maxConcurrentCalls = 2) {
            override fun buildCallsExecutor() = super.buildCallsExecutor()
        }
        withServer(settings) {
            val station = connect("CS1")
            station.send(call("call-1"))
            expectThat(station.next().resultId()).isEqualTo("call-1")

            wampServer.shutdown()

            expectThat((builtExecutor.get() as ExecutorService).isShutdown).isTrue()
        }
    }

    @Test
    fun `should leave the executor of the caller running on shutdown`() {
        val own = Executors.newFixedThreadPool(2)
        try {
            val settings = object : OcppWampServerSettings(maxConcurrentCalls = 1, maxQueuedCalls = 0) {
                override fun buildCallsExecutor(): Executor = own
            }
            withServer(settings) {
                val station = connect("CS1")
                station.send(call("call-1"))
                expectThat(station.next().resultId()).isEqualTo("call-1")

                wampServer.shutdown()

                expectThat(own.isShutdown).isFalse()
                expectThat(own.submit<String> { "still running" }.get(5, TimeUnit.SECONDS)).isEqualTo("still running")
            }
        } finally {
            own.shutdownNow()
        }
    }

    private fun call(id: String) = """[2,"$id","Heartbeat",{}]"""

    private fun blocked(id: String) = """[2,"$id","Heartbeat",{"block":true}]"""

    private fun String.resultId(): String =
        WampMessage.parse(this).let {
            expectThat(it).isNotNull().get { msgType }.isEqualTo(WampMessageType.CALL_RESULT)
            it!!.msgId
        }

    /** A real server whose handler answers every call, after waiting for [release] when the call asks to block. */
    private class Harness(
        val wampServer: OcppWampServer,
        val server: Http4kServer,
        val port: Int,
        val events: List<OcppErrorEvent>,
        val builtExecutor: AtomicReference<Executor>
    ) {
        val threads = CopyOnWriteArrayList<Thread>()
        private val counts = ConcurrentHashMap<String, AtomicInteger>()
        private val started = LinkedBlockingQueue<String>()
        private val released = CountDownLatch(1)
        private val http = OkHttpClient()
        private val clients = CopyOnWriteArrayList<WebSocket>()

        val runs: Map<String, Int> get() = counts.mapValues { it.value.get() }

        fun onAction(msg: WampMessage): WampMessage {
            threads.add(Thread.currentThread())
            counts.computeIfAbsent(msg.msgId) { AtomicInteger() }.incrementAndGet()
            if (msg.payload.contains("block")) {
                started.add(msg.msgId)
                check(released.await(10, TimeUnit.SECONDS)) { "the test never released ${msg.msgId}" }
            }
            return WampMessage.CallResult(msg.msgId, "{}")
        }

        fun awaitStarted(count: Int) = repeat(count) {
            started.poll(5, TimeUnit.SECONDS) ?: throw AssertionError("no blocked call started within 5 s")
        }

        fun release() = released.countDown()

        fun connect(ocppId: String): Station {
            val frames = LinkedBlockingQueue<String>()
            val connected = CountDownLatch(1)
            val client = http.newWebSocket(
                Request.Builder().url("ws://localhost:$port/ws/$ocppId").header("Sec-WebSocket-Protocol", "ocpp1.6")
                    .build(),
                object : WebSocketListener() {
                    override fun onOpen(webSocket: WebSocket, response: Response) = connected.countDown()
                    override fun onMessage(webSocket: WebSocket, text: String) {
                        frames.add(text)
                    }
                }
            )
            clients.add(client)
            expectThat(connected.await(5, TimeUnit.SECONDS)).describedAs("$ocppId connected").isTrue()
            return Station(client, frames)
        }

        fun close() {
            release()
            clients.forEach { it.close(1000, null) }
            http.dispatcher.executorService.shutdown()
            server.stop()
            wampServer.shutdown()
        }
    }

    private class Station(private val client: WebSocket, private val frames: LinkedBlockingQueue<String>) {
        fun send(vararg messages: String) = messages.forEach { client.send(it) }

        fun next(): String = frames.poll(5, TimeUnit.SECONDS) ?: throw AssertionError("no frame within 5 s")

        // long enough for a duplicate answer to show up
        fun noMoreFrames(): Boolean = frames.poll(300, TimeUnit.MILLISECONDS) == null

        /**
         * Sends probes until the server rejects one because it is shutting down. A probe sent before is answered at
         * once too: by its handler, or by a rejection of the saturated executor.
         */
        fun awaitShuttingDown() {
            repeat(500) { attempt ->
                send("""[2,"probe-$attempt","Heartbeat",{}]""")
                val answer = WampMessage.parse(next())
                if (answer?.msgId == "probe-$attempt" && answer.errorDescription == "Rejected call - shutting down") {
                    return
                }
            }
            throw AssertionError("the server never started shutting down")
        }
    }

    private fun withServer(settings: OcppWampServerSettings, test: Harness.() -> Unit) {
        val events = CopyOnWriteArrayList<OcppErrorEvent>()
        val builtExecutor = AtomicReference<Executor>()
        // returns the very executor the settings build, so that its ownership is unchanged
        val recording = object : OcppWampServerSettings(
            settings.timeoutInMs, settings.maxConcurrentCalls, settings.maxQueuedCalls
        ) {
            override fun buildCallsExecutor() = settings.buildCallsExecutor().also { builtExecutor.set(it) }
        }
        val wampServer = OcppWampServer.newServer(
            setOf(OCPP_1_6),
            settings = recording,
            errorListener = OcppErrorListener { events.add(it) }
        )
        val port = getFreePort()
        val server = wampServer.asServer(port)
        val harness = Harness(wampServer, server, port, events, builtExecutor)
        wampServer.register(object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId) = true
            override fun onAction(meta: WampMessageMeta, msg: WampMessage) = harness.onAction(msg)
        })
        server.start()
        try {
            harness.test()
        } finally {
            harness.close()
        }
    }
}
