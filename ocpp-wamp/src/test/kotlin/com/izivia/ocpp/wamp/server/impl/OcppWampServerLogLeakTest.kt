package com.izivia.ocpp.wamp.server.impl

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.classic.spi.ThrowableProxyUtil
import ch.qos.logback.core.read.ListAppender
import com.fasterxml.jackson.databind.ObjectMapper
import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion.OCPP_1_6
import com.izivia.ocpp.wamp.getFreePort
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.server.OcppWampServer
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import com.izivia.ocpp.wamp.server.asServer
import okhttp3.OkHttpClient
import org.http4k.core.Method
import org.http4k.websocket.PushPullAdaptingWebSocket
import org.http4k.websocket.WsMessage
import org.http4k.websocket.WsStatus
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.slf4j.LoggerFactory
import strikt.api.Assertion
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.all
import strikt.assertions.contains
import strikt.assertions.isEqualTo
import strikt.assertions.isNotEmpty
import strikt.assertions.isNotNull
import java.util.Base64
import java.util.UUID
import java.util.concurrent.CountDownLatch
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import org.http4k.core.Request as Http4kRequest

/**
 * Runs real exchanges through an Undertow server with every logger at TRACE and checks that no captured log event,
 * message or rendered throwable, holds a sentinel carried by a frame, a credential or an exception message.
 *
 * Each test first waits for the metadata event of the path it exercises, so that it cannot pass on a missing line.
 */
class OcppWampServerLogLeakTest {

    @Test
    fun `a malformed frame is logged without its content`() = withLogs { logs ->
        val sentinel = sentinel()
        withStation { station ->
            station.ws.send("""[2,"m-bad" $sentinel""")
            logs.awaitEvent("parse failure") { it.formattedMessage.contains("parsing Wamp message returns null") }
        }
        logs.expectNoLeak(sentinel)
    }

    @Test
    fun `a frame the parser fails on is logged without the parser exception message`() = withLogs { logs ->
        val sentinel = sentinel()
        withStation { station ->
            station.ws.send("""[x$sentinel,"m-type","Heartbeat",{}]""")
            logs.awaitEvent("parser failure") {
                it.formattedMessage.contains("An error appears when trying to parse message") &&
                    it.throwableProxy != null
            }
        }
        logs.expectNoLeak(sentinel)
    }

    @Test
    fun `a call whose payload a handler cannot parse is logged without the parser message`() = withLogs { logs ->
        val sentinel = sentinel()
        val handler: (WampMessage) -> WampMessage? = { ObjectMapper().readTree(it.payload); null }
        withStation(handler) { station ->
            station.ws.send("""[2,"m-json","Authorize",{"idTag": $sentinel}]""")
            logs.awaitEvent("call failure") {
                it.formattedMessage.contains("[m-json] [Authorize] call failed") &&
                    it.formattedMessage.contains("JsonParseException")
            }
        }
        logs.expectNoLeak(sentinel)
    }

    @Test
    fun `a call without a handler is logged without its payload`() = withLogs { logs ->
        val sentinel = sentinel()
        withStation { station ->
            station.ws.send("""[2,"m-unknown","UnknownAction",{"data":"$sentinel"}]""")
            logs.awaitEvent("no handler") {
                it.formattedMessage.contains("no action handler found") && it.formattedMessage.contains("[m-unknown]")
            }
        }
        logs.expectNoLeak(sentinel)
    }

    @Test
    fun `an answered call is logged without its request and response payloads`() = withLogs { logs ->
        val sentinel = sentinel()
        withStation(answering(sentinel)) { station ->
            station.ws.send("""[2,"m-ok","Authorize",{"idTag":"$sentinel"}]""")
            expectThat(station.received.poll(5, TimeUnit.SECONDS)).isNotNull().contains(sentinel)
            logs.awaitEvent("inbound request") {
                it.formattedMessage.contains("[IN][CALL][REQ]") && it.formattedMessage.contains("[m-ok]")
            }
            logs.awaitEvent("inbound response") {
                it.formattedMessage.contains("[IN][CALL][RESP]") && it.formattedMessage.contains("[m-ok]")
            }
        }
        logs.expectNoLeak(sentinel)
    }

    @Test
    fun `a throwing handler is logged without the messages of its exception chain`() = withLogs { logs ->
        val sentinel = sentinel()
        val failure = IllegalStateException("top $sentinel", IllegalArgumentException("cause $sentinel"))
            .apply { addSuppressed(UnsupportedOperationException("suppressed $sentinel")) }
        withStation({ throw failure }) { station ->
            station.ws.send("""[2,"m-throw","Heartbeat",{}]""")
            logs.awaitEvent("call failure") {
                it.formattedMessage.contains("[m-throw] [Heartbeat] call failed") &&
                    it.formattedMessage.contains(IllegalStateException::class.java.name)
            }
        }
        logs.expectNoLeak(sentinel)
    }

    @Test
    fun `a throwing connect listener is logged without its exception message`() = withLogs { logs ->
        val sentinel = sentinel()
        val listeners = EventsListeners(onWsConnectHandler = { _, _ -> error("connect $sentinel") })
        withStation(listeners = listeners) {
            logs.awaitEvent("listener failure") {
                it.formattedMessage.contains("connect listener failed") &&
                    it.formattedMessage.contains(IllegalStateException::class.java.name)
            }
        }
        logs.expectNoLeak(sentinel)
    }

    @Test
    fun `an outbound call and its result are logged without their payloads`() = withLogs { logs ->
        val sentinel = sentinel()
        withStation(stationAnswer = { id -> """[3,"$id",{"status":"$sentinel"}]""" }) { station ->
            val response = station.server.sendBlocking(OCPP_ID, outboundCall("m-out", sentinel))
            expectThat(response.payload).contains(sentinel)
            logs.awaitEvent("outbound request") {
                it.formattedMessage.contains("[OUT][CALL][REQ]") && it.formattedMessage.contains("[m-out]")
            }
            logs.awaitEvent("outbound response") {
                it.formattedMessage.contains("[OUT][CALL][RESP]") && it.formattedMessage.contains("[m-out]")
            }
        }
        logs.expectNoLeak(sentinel)
    }

    @Test
    fun `an unsolicited call result is logged without its payload`() = withLogs { logs ->
        val sentinel = sentinel()
        withStation { station ->
            station.ws.send("""[3,"m-unsolicited",{"status":"$sentinel"}]""")
            logs.awaitEvent("discarded result") {
                it.formattedMessage.contains("no pending call") && it.formattedMessage.contains("[m-unsolicited]")
            }
        }
        logs.expectNoLeak(sentinel)
    }

    @Test
    fun `an outbound call timing out fails and is logged without its payload`() = withLogs { logs ->
        val sentinel = sentinel()
        withStation { station ->
            expectThrows<TimeoutException> {
                station.server.sendBlocking(OCPP_ID, outboundCall("m-timeout", sentinel), timeoutInMs = 300)
            }.get { message }.isNotNull().and {
                not().contains(sentinel)
                contains("m-timeout")
                contains("DataTransfer")
            }
            logs.awaitEvent("outbound request") {
                it.formattedMessage.contains("[OUT][CALL][REQ]") && it.formattedMessage.contains("[m-timeout]")
            }
        }
        logs.expectNoLeak(sentinel)
    }

    @Test
    fun `a connection closed while a call is pending is logged without the call payload`() = withLogs { logs ->
        val sentinel = sentinel()
        withStation(stationAnswer = { _ -> null }) { station ->
            val call = Thread {
                runCatching { station.server.sendBlocking(OCPP_ID, outboundCall("m-pending", sentinel), 1_000) }
            }.apply { start() }
            expectThat(station.received.poll(5, TimeUnit.SECONDS)).isNotNull().contains("m-pending")
            station.ws.close(1000, "bye")
            logs.awaitEvent("disconnection") { it.formattedMessage.contains("disconnected") }
            call.join(5_000)
        }
        logs.expectNoLeak(sentinel)
    }

    @Test
    fun `the credentials of the upgrade request are not logged`() = withLogs { logs ->
        val password = sentinel()
        val basic = Base64.getEncoder().encodeToString("$OCPP_ID:$password".toByteArray())
        withStation(headers = listOf("Authorization" to "Basic $basic")) {
            logs.awaitEvent("connection attempt") { it.formattedMessage.contains("/ws/$OCPP_ID - connection attempt") }
            logs.awaitEvent("connected") { it.formattedMessage.contains("connected - number of connections") }
        }
        logs.expectNoLeak(password)
        logs.expectNoLeak(basic)
    }

    @Test
    fun `an upgrade refused for its subprotocol is logged without its query string and header values`() =
        withLogs { logs ->
            val sentinel = sentinel()
            withStation(
                ocppIdPath = "$OCPP_ID?token=$sentinel-Q",
                headers = secretHeaders(sentinel),
                subprotocol = null
            ) {
                logs.awaitEvent("refused upgrade") {
                    it.formattedMessage.contains("Error during new connection with /ws/$OCPP_ID: ") &&
                        it.formattedMessage.contains(IllegalArgumentException::class.java.name) &&
                        it.throwableProxy != null
                }
            }
            logs.expectNoLeak(sentinel)
        }

    @Test
    fun `an upgrade refused for its subprotocol fails without its query string and header values`() =
        withLogs { logs ->
            val sentinel = sentinel()
            expectRefusedUpgrade("/ws/$OCPP_ID?token=$sentinel-Q", subprotocol = null, sentinel)
                .contains("unsupported or invalid ocpp version")
                .contains("/ws/$OCPP_ID")
            logs.expectNoLeak(sentinel)
        }

    @Test
    fun `an upgrade refused for its station id fails without its query string and header values`() =
        withLogs { logs ->
            val sentinel = sentinel()
            expectRefusedUpgrade("/ws/%ZZ?token=$sentinel-Q", OCPP_1_6.subprotocol, sentinel)
                .contains("no chargingStationOcppId")
                .contains("/ws/%ZZ")
            logs.expectNoLeak(sentinel)
        }

    @Test
    fun `an accepted upgrade is logged without its query string and header values`() = withLogs { logs ->
        val sentinel = sentinel()
        withStation(ocppIdPath = "$OCPP_ID?token=$sentinel-Q", headers = secretHeaders(sentinel)) {
            logs.awaitEvent("connection attempt") {
                it.formattedMessage.contains("/ws/$OCPP_ID - connection attempt") &&
                    it.formattedMessage.contains("X-Secret")
            }
            logs.awaitEvent("connected") { it.formattedMessage.contains("connected - number of connections") }
        }
        logs.expectNoLeak(sentinel)
    }

    @Test
    fun `a station id holding a line break is logged escaped`() = withLogs { logs ->
        withStation(ocppIdPath = "CS1%0D%0AFAKE") {
            logs.awaitEvent("connected") {
                it.formattedMessage.contains("[CS1\\u000d\\u000aFAKE]") &&
                    it.formattedMessage.contains("connected - number of connections")
            }
        }
        expectThat(logs.events()).isNotEmpty().all {
            get { formattedMessage }.not().contains("\r")
            get { formattedMessage }.not().contains("\n")
        }
    }

    @Test
    fun `the frames logger at debug logs no frame`() = withLogs(framesLevel = Level.DEBUG) { logs ->
        val sentinel = sentinel()
        withStation(answering(sentinel)) { station ->
            station.ws.send("""[2,"m-frames","Authorize",{"idTag":"$sentinel"}]""")
            expectThat(station.received.poll(5, TimeUnit.SECONDS)).isNotNull().contains(sentinel)
            logs.awaitEvent("inbound call") {
                it.formattedMessage.contains("[IN][CALL][REQ]") && it.formattedMessage.contains("[m-frames]")
            }
            logs.awaitEvent("response") {
                it.formattedMessage.contains("[IN][CALL][RESP]") && it.formattedMessage.contains("[m-frames]")
            }
        }
        logs.expectNoLeak(sentinel)
    }

    @Test
    fun `the frames logger at trace logs the inbound call and its response only through itself`() =
        withLogs(framesLevel = Level.TRACE) { logs ->
            val sentinel = sentinel()
            withStation(answering(sentinel)) { station ->
                station.ws.send("""[2,"m-frames","Authorize",{"idTag":"$sentinel"}]""")
                expectThat(station.received.poll(5, TimeUnit.SECONDS)).isNotNull().contains(sentinel)
                logs.awaitEvent("inbound frame") {
                    it.loggerName == FRAMES && it.formattedMessage.contains("""{"idTag":"$sentinel"}""")
                }
                logs.awaitEvent("outbound frame") {
                    it.loggerName == FRAMES && it.formattedMessage.contains("parentIdTag")
                }
            }
            logs.expectNoLeak(sentinel, except = FRAMES)
        }

    @Test
    fun `the frames logger at trace logs an outbound call and its result only through itself`() =
        withLogs(framesLevel = Level.TRACE) { logs ->
            val sentinel = sentinel()
            withStation(stationAnswer = { id -> """[3,"$id",{"status":"$sentinel-result"}]""" }) { station ->
                station.server.sendBlocking(OCPP_ID, outboundCall("m-frames-out", sentinel))
                logs.awaitEvent("outbound frame") {
                    it.loggerName == FRAMES && it.formattedMessage.contains("""{"data":"$sentinel"}""")
                }
                logs.awaitEvent("inbound frame") {
                    it.loggerName == FRAMES && it.formattedMessage.contains("$sentinel-result")
                }
            }
            logs.expectNoLeak(sentinel, except = FRAMES)
        }

    private fun sentinel() = "sentinel-168-${UUID.randomUUID()}"

    private fun secretHeaders(sentinel: String) = listOf("X-Secret" to "$sentinel-H", "Cookie" to "s=$sentinel-C")

    /**
     * Runs an upgrade of [uri], carrying the [secretHeaders] of [sentinel], straight through the application, expects
     * it to fail with an [IllegalArgumentException] whose message holds no [sentinel], and returns that message.
     */
    private fun expectRefusedUpgrade(uri: String, subprotocol: String?, sentinel: String): Assertion.Builder<String> {
        val app = OcppWampServerApp(
            ocppVersions = setOf(OCPP_1_6),
            handlers = { emptyList() },
            ocppWsEndpoint = OcppWsEndpoint("ws"),
            settings = OcppWampServerSettings()
        )
        val upgrade = Http4kRequest(Method.GET, uri)
            .let { request -> subprotocol?.let { request.header("Sec-WebSocket-Protocol", it) } ?: request }
        val request = secretHeaders(sentinel).fold(upgrade) { request, (name, value) -> request.header(name, value) }
        val websocket = object : PushPullAdaptingWebSocket() {
            override fun send(message: WsMessage) = Unit
            override fun close(status: WsStatus) = Unit
        }
        return expectThrows<IllegalArgumentException> { app.newRoutingHandler()(request).consumer(websocket) }
            .get { message }.isNotNull().and { not().contains(sentinel) }
    }

    private fun outboundCall(msgId: String, sentinel: String) =
        WampMessage.Call(msgId, "DataTransfer", """{"data":"$sentinel"}""")

    private fun answering(sentinel: String): (WampMessage) -> WampMessage? = {
        WampMessage.CallResult(it.msgId, """{"idTagInfo":{"status":"Accepted","parentIdTag":"$sentinel"}}""")
    }

    /** The events every logger emitted while a test ran. */
    private class Logs(private val appender: ListAppender<ILoggingEvent>) {
        fun events(): List<ILoggingEvent> = synchronized(appender) { appender.list.toList() }

        /** Waits for an event matching [predicate], failing loudly when none shows up within 5 s. */
        fun awaitEvent(description: String, predicate: (ILoggingEvent) -> Boolean) {
            val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(5)
            while (System.nanoTime() < deadline) {
                if (events().any(predicate)) return
                Thread.sleep(10)
            }
            fail("no '$description' log event within 5 s - events: ${events().map { it.formattedMessage }}")
        }

        /** Expects that no event, except those of the logger [except], holds [sentinel]. */
        fun expectNoLeak(sentinel: String, except: String? = null) {
            val checked = events().filter { it.loggerName != except }
            expectThat(checked).isNotEmpty().all {
                get { formattedMessage }.not().contains(sentinel)
                get { throwableProxy?.let { ThrowableProxyUtil.asString(it) }.orEmpty() }.not().contains(sentinel)
            }
        }
    }

    /**
     * Captures every logger, `com.izivia` and the root at TRACE, the frames logger at [framesLevel], and restores the
     * levels afterwards.
     */
    private fun withLogs(framesLevel: Level = Level.OFF, block: (Logs) -> Unit) {
        val root = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME) as Logger
        val izivia = LoggerFactory.getLogger("com.izivia") as Logger
        val frames = LoggerFactory.getLogger(FRAMES) as Logger
        val previous = listOf(root, izivia, frames).associateWith { it.level }
        val appender = ListAppender<ILoggingEvent>().apply { start() }
        root.level = Level.TRACE
        izivia.level = Level.TRACE
        frames.level = framesLevel
        root.addAppender(appender)
        try {
            block(Logs(appender))
        } finally {
            root.detachAppender(appender)
            previous.forEach { (logger, level) -> logger.level = level }
            appender.stop()
        }
    }

    private class Station(val server: OcppWampServer, val ws: WebSocket, val received: LinkedBlockingQueue<String>)

    /**
     * Connects one OkHttp station to a real server and runs [block]. [stationAnswer] answers each CALL the station
     * receives, by its message id, or not at all when it returns null. Without a [subprotocol], the upgrade is
     * refused by the server and [block] runs without waiting for the connection to open.
     */
    private fun withStation(
        onAction: (WampMessage) -> WampMessage? = { null },
        listeners: EventsListeners = EventsListeners(),
        headers: List<Pair<String, String>> = emptyList(),
        ocppIdPath: String = OCPP_ID,
        subprotocol: String? = OCPP_1_6.subprotocol,
        stationAnswer: (String) -> String? = { null },
        block: (Station) -> Unit
    ) {
        val wampServer = OcppWampServer.newServer(setOf(OCPP_1_6), listeners = listeners)
        wampServer.register(object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId) = true
            override fun onAction(meta: WampMessageMeta, msg: WampMessage) = onAction(msg)
        })
        val port = getFreePort()
        val server = wampServer.asServer(port)
        server.start()
        val received = LinkedBlockingQueue<String>()
        val connected = CountDownLatch(1)
        val http = OkHttpClient()
        val request = Request.Builder()
            .url("ws://localhost:$port/ws/$ocppIdPath")
            .apply { subprotocol?.let { header("Sec-WebSocket-Protocol", it) } }
            .apply { headers.forEach { (name, value) -> header(name, value) } }
            .build()
        val ws = http.newWebSocket(
            request,
            object : WebSocketListener() {
                override fun onOpen(webSocket: WebSocket, response: Response) = connected.countDown()
                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) = Unit
                override fun onMessage(webSocket: WebSocket, text: String) {
                    received.add(text)
                    WampMessage.parse(text)
                        ?.takeIf { it.msgType.id == 2 }
                        ?.let { stationAnswer(it.msgId) }
                        ?.let { webSocket.send(it) }
                }
            }
        )
        try {
            if (subprotocol != null) expectThat(connected.await(5, TimeUnit.SECONDS)).isEqualTo(true)
            block(Station(wampServer, ws, received))
        } finally {
            ws.close(1000, null)
            http.dispatcher.executorService.shutdown()
            server.stop()
        }
    }

    private companion object {
        const val OCPP_ID = "CS1"
        const val FRAMES = "com.izivia.ocpp.wamp.frames"
    }
}
