package com.izivia.ocpp.wamp

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion.OCPP_1_6
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.server.ConnectionAuthorizer
import com.izivia.ocpp.wamp.server.OcppWampServer
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import com.izivia.ocpp.wamp.server.WebSocketUpgradeDecision
import com.izivia.ocpp.wamp.server.asServer
import com.izivia.ocpp.wamp.server.impl.AdmissionExecution
import com.izivia.ocpp.wamp.server.impl.EventsListeners
import com.izivia.ocpp.wamp.server.impl.Undertow
import io.undertow.server.HttpServerExchange
import okhttp3.OkHttpClient
import okhttp3.Request
import org.http4k.core.PolyHandler
import org.http4k.core.Status
import org.http4k.server.Http4kServer
import org.http4k.server.asServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.slf4j.LoggerFactory
import org.xnio.XnioIoThread
import strikt.api.expect
import strikt.api.expectThat
import strikt.assertions.all
import strikt.assertions.isEmpty
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import strikt.assertions.isNotEqualTo
import strikt.assertions.isTrue
import strikt.assertions.none
import java.net.Socket
import java.net.SocketTimeoutException
import java.time.Duration
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.RejectedExecutionException
import java.util.concurrent.Semaphore
import java.util.concurrent.TimeUnit.SECONDS
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicReference
import org.http4k.core.Response as HttpResponse

class WampDispatchedAdmissionTest {
    private val port = getFreePort()
    private val events = CopyOnWriteArrayList<String>()
    private val listeners = EventsListeners(
        onWsConnectHandler = { id, _ -> events += "connect $id" },
        onWsReconnectHandler = { id, _ -> events += "reconnect $id" },
    )

    // the stations whose admission waits for a gate, and the I/O thread of the last admitted exchange
    private val gates = ConcurrentHashMap<CSOcppId, Gate>()
    private val ioThread = AtomicReference<XnioIoThread>()
    private val pool = Executors.newCachedThreadPool()

    @AfterEach
    fun releaseAdmissions() {
        gates.values.forEach { it.release.countDown() }
        pool.shutdownNow()
    }

    @Test
    fun `slow admission delays neither an unrelated request nor another station`() {
        val gate = hold("CS1")
        val server = start(AdmissionExecution.Dispatched(maxConcurrent = 4, timeout = Duration.ofSeconds(10)))

        try {
            val slow = handshakeAsync("CS1")
            awaitOrFail(gate.entered, "admission of CS1 started")

            val unrelated = httpGet("/ping")
            val other = handshake(port, "CS2")
            gate.release.countDown()

            expect {
                that(unrelated).isEqualTo(200 to "pong")
                that(other.status).isEqualTo(101)
                that(slow.get(5, SECONDS).status).isEqualTo(101)
            }
        } finally {
            gate.release.countDown()
            server.stop()
        }
    }

    @Test
    fun `asServer runs the connection authorizer with the given admission`() {
        val entered = CountDownLatch(1)
        val release = CountDownLatch(1)
        val slowAuthorizer = ConnectionAuthorizer { _ ->
            entered.countDown()
            release.await(10, SECONDS)
            true
        }
        val server = wampServer(authorizer = slowAuthorizer)
            .asServer(port, AdmissionExecution.Dispatched(maxConcurrent = 1, timeout = SHORT_TIMEOUT, executor = pool))
            .start()

        try {
            val expired = handshake(port, "CS1")

            expect {
                that(entered.count).isEqualTo(0L)
                that(expired.status).isEqualTo(503)
                that(expired.retryAfter).isEqualTo("5")
                that(events).isEmpty()
            }
        } finally {
            release.countDown()
            server.stop()
        }
    }

    @Test
    fun `handshakes beyond maxConcurrent get an immediate 503 with Retry-After while the admitted ones succeed`() {
        val gate = hold("CS1", "CS2", "CS3")
        val server = start(AdmissionExecution.Dispatched(maxConcurrent = 3, timeout = Duration.ofSeconds(10)))

        try {
            val admitted = listOf("CS1", "CS2", "CS3").map(::handshakeAsync)
            awaitOrFail(gate.entered, "admissions of CS1, CS2 and CS3 started")

            val overloaded = handshake(port, "CS4")
            gate.release.countDown()

            expect {
                that(overloaded.status).isEqualTo(503)
                that(overloaded.retryAfter).isEqualTo("5")
                that(admitted.map { it.get(5, SECONDS).status }).all { isEqualTo(101) }
                that(events).none { isEqualTo("connect CS4") }
            }
        } finally {
            gate.release.countDown()
            server.stop()
        }
    }

    @Test
    fun `admission that times out gets a 503 with Retry-After and its late decision is dropped`() {
        val gate = hold("CS1")
        val executor = TrackingExecutor(pool)
        val server = start(AdmissionExecution.Dispatched(maxConcurrent = 1, timeout = SHORT_TIMEOUT, executor))

        try {
            val expired = handshake(port, "CS1")
            gate.release.countDown()
            // the only slot is free again once the late admission of CS1 has returned
            awaitOrFail(executor.completed, "late admission of CS1 returned")

            val next = handshake(port, "CS2")

            expect {
                that(expired.status).isEqualTo(503)
                that(expired.retryAfter).isEqualTo("5")
                that(next.status).isEqualTo(101)
                that(events.toList()).isEqualTo(listOf("connect CS2"))
            }
        } finally {
            gate.release.countDown()
            server.stop()
        }
    }

    @Test
    fun `late decision is dropped even when the station keeps its connection open after the 503`() {
        val gate = hold("CS1")
        val executor = TrackingExecutor(pool)
        val server = start(AdmissionExecution.Dispatched(maxConcurrent = 1, timeout = SHORT_TIMEOUT, executor))

        try {
            // Undertow refuses to upgrade an exchange already answered, and logs that request as failed
            val undertowErrors = errorsOf("io.undertow.request") { lateDecisionOnOpenConnection(gate, executor) }

            expectThat(undertowErrors).isEmpty()
        } finally {
            gate.release.countDown()
            server.stop()
        }
    }

    // a handshake that times out on a connection the station keeps open, then whose admission decides
    private fun lateDecisionOnOpenConnection(gate: Gate, executor: TrackingExecutor) =
        Socket("localhost", port).use { socket ->
            socket.soTimeout = 5_000
            socket.getOutputStream().write(upgradeRequest("CS1").toByteArray())
            val response = socket.getInputStream().bufferedReader()
            val status = response.readLine()
            generateSequence { response.readLine() }.takeWhile { it.isNotEmpty() }.toList()

            gate.release.countDown()
            awaitOrFail(executor.completed, "late admission of CS1 returned")
            val applied = CountDownLatch(1)
            ioThread.get().execute { applied.countDown() }
            awaitOrFail(applied, "late decision of CS1 applied")
            // what the late decision would have written is on the wire once it has been applied
            socket.soTimeout = 500
            val written = runCatching { response.readLine() }

            expect {
                that(status).isEqualTo("HTTP/1.1 503 Service Unavailable")
                that(written.exceptionOrNull()).isA<SocketTimeoutException>()
                that(events).isEmpty()
            }
        }

    private fun errorsOf(loggerName: String, block: () -> Unit): List<String> {
        val logger = LoggerFactory.getLogger(loggerName) as Logger
        val appender = ListAppender<ILoggingEvent>().apply { start() }
        logger.addAppender(appender)
        try {
            block()
        } finally {
            logger.detachAppender(appender)
            appender.stop()
        }
        return appender.list.filter { it.level == Level.ERROR }.map { it.formattedMessage }
    }

    @Test
    fun `admission that times out keeps its slot until its callback returns`() {
        val gate = hold("CS1")
        val server = start(AdmissionExecution.Dispatched(maxConcurrent = 1, timeout = SHORT_TIMEOUT))

        try {
            val expired = handshake(port, "CS1")
            val whileHung = handshake(port, "CS2")

            expect {
                that(expired.status).isEqualTo(503)
                that(whileHung.status).isEqualTo(503)
                that(whileHung.retryAfter).isEqualTo("5")
                that(events).isEmpty()
            }
        } finally {
            gate.release.countDown()
            server.stop()
        }
    }

    @Test
    fun `admission rejected by its executor gets a 503 with Retry-After and gives its slot back`() {
        val rejectNext = AtomicBoolean(true)
        val executor = Executor { task ->
            if (rejectNext.getAndSet(false)) throw RejectedExecutionException("saturated") else pool.execute(task)
        }
        val server = start(AdmissionExecution.Dispatched(maxConcurrent = 1, timeout = Duration.ofSeconds(10), executor))

        try {
            val rejected = handshake(port, "CS1")
            val next = handshake(port, "CS2")

            expect {
                that(rejected.status).isEqualTo(503)
                that(rejected.retryAfter).isEqualTo("5")
                that(next.status).isEqualTo(101)
                that(events.toList()).isEqualTo(listOf("connect CS2"))
            }
        } finally {
            server.stop()
        }
    }

    @Test
    fun `dispatched admission keeps the statuses and the subprotocol of the handshake`() {
        val wamp = wampServer(
            authorizer = { request -> request.headers.any { (name, value) -> name == "Authorization" && value == AUTHORIZED.second } },
            accept = { ocppId -> if (ocppId == "BOOM") error("handler store unavailable") else ocppId != "REFUSED" }
        )
        val server = start(AdmissionExecution.Dispatched(maxConcurrent = 4, timeout = Duration.ofSeconds(10)), wamp)

        try {
            val unauthorized = handshake(port, "CS1")
            val refused = handshake(port, "REFUSED", AUTHORIZED)
            val failed = handshake(port, "BOOM", AUTHORIZED)
            val accepted = handshake(port, "CS1", AUTHORIZED)

            expect {
                that(unauthorized).isEqualTo(Handshake(401, """Basic realm="OCPP""""))
                that(refused.status).isEqualTo(404)
                that(failed.status).isEqualTo(500)
                that(accepted.status).isEqualTo(101)
                that(accepted.subprotocol).isEqualTo(OCPP_1_6.subprotocol)
            }
        } finally {
            server.stop()
        }
    }

    @Test
    fun `admission pending when the server stops upgrades nothing`() {
        val gate = hold("CS1")
        val executor = TrackingExecutor(pool)
        val server = start(AdmissionExecution.Dispatched(maxConcurrent = 1, timeout = Duration.ofSeconds(10), executor))

        try {
            val pending = handshakeAsync("CS1")
            awaitOrFail(gate.entered, "admission of CS1 started")

            server.stop()
            gate.release.countDown()
            awaitOrFail(executor.completed, "admission of CS1 returned")

            expect {
                that(pending.get(5, SECONDS).status).isNotEqualTo(101)
                that(events).isEmpty()
            }
        } finally {
            gate.release.countDown()
            server.stop()
        }
    }

    @Test
    fun `admission deciding while the server stops upgrades nothing`() {
        val gate = hold("CS1")
        val executor = TrackingExecutor(pool)
        val firstStop = AtomicBoolean(true)
        val server = start(AdmissionExecution.Dispatched(maxConcurrent = 1, timeout = Duration.ofSeconds(10), executor)) {
            // the admission decides once the server refuses to upgrade, and its decision is applied before the
            // server closes its connections: the exchange's I/O thread runs its tasks in order
            beforeServerStop = stop@{
                if (!firstStop.getAndSet(false)) return@stop
                gate.release.countDown()
                awaitOrFail(executor.completed, "admission of CS1 returned")
                val applied = CountDownLatch(1)
                ioThread.get().execute { applied.countDown() }
                awaitOrFail(applied, "decision of CS1 applied")
            }
        }

        try {
            val pending = handshakeAsync("CS1")
            awaitOrFail(gate.entered, "admission of CS1 started")

            server.stop()

            expect {
                that(pending.get(5, SECONDS).status).isNotEqualTo(101)
                that(events).isEmpty()
            }
        } finally {
            firstStop.set(false)
            gate.release.countDown()
            server.stop()
        }
    }

    @Test
    fun `dispatched admission needs a strictly positive bound and timeout`() {
        val invalid = listOf(
            0 to Duration.ofSeconds(1),
            -1 to Duration.ofSeconds(1),
            1 to Duration.ZERO,
            1 to Duration.ofMillis(-1),
        )

        invalid.forEach { (maxConcurrent, timeout) ->
            assertThrows<IllegalArgumentException> { AdmissionExecution.Dispatched(maxConcurrent, timeout) }
        }
    }

    private class Gate(parties: Int) {
        val entered = CountDownLatch(parties)
        val release = CountDownLatch(1)
    }

    // an executor telling when each of its tasks has returned
    private class TrackingExecutor(private val delegate: Executor) : Executor {
        val completed = Semaphore(0)

        override fun execute(command: Runnable) = delegate.execute {
            try {
                command.run()
            } finally {
                completed.release()
            }
        }
    }

    // the admission of these stations waits until the gate is released
    private fun hold(vararg ocppIds: CSOcppId) = Gate(ocppIds.size).also { gate -> ocppIds.forEach { gates[it] = gate } }

    private fun gated(admit: (HttpServerExchange) -> WebSocketUpgradeDecision?) = { exch: HttpServerExchange ->
        ioThread.set(exch.ioThread)
        gates[exch.requestURI.substringAfterLast('/')]?.let { gate ->
            gate.entered.countDown()
            gate.release.await(10, SECONDS)
        }
        admit(exch)
    }

    private fun wampServer(
        authorizer: ConnectionAuthorizer = ConnectionAuthorizer.ALLOW_ALL,
        accept: (CSOcppId) -> Boolean = { true },
    ) = OcppWampServer.newServer(setOf(OCPP_1_6), listeners = listeners, authorizer = authorizer).apply {
        register(object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId): Boolean = accept(ocppId)

            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? =
                WampMessage.CallResult(msg.msgId, "{}")
        })
    }

    // a single I/O thread, so that an admission blocking it would delay every other connection
    private fun start(
        admission: AdmissionExecution,
        wamp: OcppWampServer = wampServer(),
        configure: Undertow.() -> Unit = {},
    ): Http4kServer {
        val config = wamp.config()
        val undertow = Undertow(port, false, gated(config.admitWebSocket), config.wsSubprotocols, admission)
            .apply { ioThreads = 1 }
            .apply(configure)
        return PolyHandler(http = { HttpResponse(Status.OK).body("pong") }, ws = config.handler)
            .asServer(undertow)
            .start()
    }

    private fun handshakeAsync(ocppId: CSOcppId): CompletableFuture<Handshake> =
        CompletableFuture.supplyAsync({ handshake(port, ocppId) }, pool)

    // an OCPP 1.6 upgrade request written by hand, so that the connection stays open whatever the answer
    private fun upgradeRequest(ocppId: CSOcppId) = listOf(
        "GET /ws/$ocppId HTTP/1.1",
        "Host: localhost:$port",
        "Upgrade: websocket",
        "Connection: Upgrade",
        "Sec-WebSocket-Key: dGhlIHNhbXBsZSBub25jZQ==",
        "Sec-WebSocket-Version: 13",
        "Sec-WebSocket-Protocol: ${OCPP_1_6.subprotocol}",
    ).joinToString("\r\n", postfix = "\r\n\r\n")

    private fun httpGet(path: String): Pair<Int, String?> =
        OkHttpClient.Builder().callTimeout(2, SECONDS).build()
            .newCall(Request.Builder().url("http://localhost:$port$path").build())
            .execute()
            .use { it.code to it.body?.string() }

    private fun awaitOrFail(latch: CountDownLatch, what: String) =
        expectThat(latch.await(5, SECONDS)).describedAs("$what within 5 s").isTrue()

    private fun awaitOrFail(permits: Semaphore, what: String) =
        expectThat(permits.tryAcquire(5, SECONDS)).describedAs("$what within 5 s").isTrue()

    companion object {
        private val SHORT_TIMEOUT = Duration.ofMillis(300)
        private val AUTHORIZED = "Authorization" to "Basic Q1MxOnMzY3IzdA=="
    }
}
