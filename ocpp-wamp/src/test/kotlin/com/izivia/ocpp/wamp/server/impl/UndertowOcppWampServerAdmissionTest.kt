package com.izivia.ocpp.wamp.server.impl

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion.OCPP_1_6
import com.izivia.ocpp.utils.OcppErrorEvent
import com.izivia.ocpp.utils.OcppErrorListener
import com.izivia.ocpp.wamp.client.OcppWampClient
import com.izivia.ocpp.wamp.getFreePort
import com.izivia.ocpp.wamp.handshake
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import com.izivia.ocpp.wamp.server.WebSocketUpgradeDecision
import com.izivia.ocpp.wamp.server.asServer
import io.undertow.server.HttpServerExchange
import io.undertow.websockets.spi.AsyncWebSocketHttpServerExchange
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Uri
import org.http4k.websocket.PushPullAdaptingWebSocket
import org.http4k.websocket.WsMessage
import org.http4k.websocket.WsStatus
import org.junit.jupiter.api.Test
import strikt.api.expect
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.contains
import strikt.assertions.containsExactly
import strikt.assertions.isA
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull
import strikt.assertions.isTrue
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

/**
 * Drives the admission and the opening of each handshake separately, so that two handshakes of a station can
 * interleave in an order a real network connection cannot be made to produce on demand: admit A, admit B, open A.
 */
class UndertowOcppWampServerAdmissionTest {
    private val connected = CopyOnWriteArrayList<String>()
    private val errors = CopyOnWriteArrayList<OcppErrorEvent>()

    // calls run on the thread receiving them, so that the answer is sent when the message has been received
    private val directSettings = object : OcppWampServerSettings() {
        override fun buildCallsExecutor() = Executor { it.run() }
    }

    private val server = UndertowOcppWampServer(
        setOf(OCPP_1_6),
        settings = directSettings,
        listeners = EventsListeners(onWsConnectHandler = { id, _ -> connected += id }),
        errorListener = OcppErrorListener { errors += it }
    )

    @Volatile
    private var acceptedBy: (CSOcppId) -> String? = { null }

    init {
        server.register(handler("H1"))
        server.register(handler("H2"))
    }

    @Test
    fun `should open overlapping handshakes of a station with the handlers that accepted each`() {
        acceptedBy = { "H1" }
        val first = admit("CS1")
        acceptedBy = { "H2" }
        val second = admit("CS1")

        val firstSocket = open(first, "CS1")
        val secondSocket = open(second, "CS1")

        expect {
            that(firstSocket.answerer()).isEqualTo("H1")
            that(secondSocket.answerer()).isEqualTo("H2")
        }
    }

    @Test
    fun `should open overlapping handshakes of ids differing by case with their own handlers`() {
        acceptedBy = { id -> if (id == "CS1") "H1" else "H2" }
        val upper = admit("CS1")
        val lower = admit("cs1")

        val upperSocket = open(upper, "CS1")
        val lowerSocket = open(lower, "cs1")

        expect {
            that(upperSocket.answerer()).isEqualTo("H1")
            that(lowerSocket.answerer()).isEqualTo("H2")
        }
    }

    @Test
    fun `should fail the open of a handshake that was not admitted and register nothing`() {
        acceptedBy = { "H1" }
        val notAdmitted = HttpServerExchange(null).setRequestURI("/ws/CS1")

        expectThrows<IllegalStateException> { open(notAdmitted, "CS1") }
            .get { message }.isNotNull().contains("CS1")
        expect {
            that(connected).isEmpty()
            that(errors).isEmpty()
            that(runCatching { server.sendBlocking("CS1", call(), 1_000) }.exceptionOrNull())
                .isA<NoConnectionException>()
        }
    }

    @Test
    fun `should fail the open of a handshake admitted by another server`() {
        acceptedBy = { "H1" }
        val other = UndertowOcppWampServer(setOf(OCPP_1_6), settings = directSettings)
        other.register(handler("H1"))
        val admittedByOther = HttpServerExchange(null).setRequestURI("/ws/CS1")
        expectThat(other.config().admitWebSocket(admittedByOther)).isEqualTo(WebSocketUpgradeDecision.Accept)

        expectThrows<IllegalStateException> { open(admittedByOther, "CS1") }
        expectThat(connected).isEmpty()
    }

    @Test
    fun `should fail the open of a refused handshake without reusing an earlier admission of the station`() {
        acceptedBy = { "H1" }
        val earlier = open(admit("CS1"), "CS1")
        expectThat(earlier.answerer()).isEqualTo("H1")
        earlier.triggerClose(WsStatus.NORMAL)

        acceptedBy = { null }
        val refused = HttpServerExchange(null).setRequestURI("/ws/CS1")
        expectThat(server.config().admitWebSocket(refused)).isA<WebSocketUpgradeDecision.Refuse>()
            .get { status.code }.isEqualTo(404)

        expectThrows<IllegalStateException> { open(refused, "CS1") }
            .get { message }.isNotNull().contains("CS1")
        // only the earlier connection was notified
        expectThat(connected).containsExactly("CS1")
    }

    @Test
    fun `should open the handshake after a refusal with the handlers accepting it then`() {
        acceptedBy = { null }
        expectThat(server.config().admitWebSocket(HttpServerExchange(null).setRequestURI("/ws/CS1")))
            .isA<WebSocketUpgradeDecision.Refuse>()

        acceptedBy = { "H2" }
        val socket = open(admit("CS1"), "CS1")

        expectThat(socket.answerer()).isEqualTo("H2")
    }

    @Test
    fun `should open the handshake after a close with the handlers accepting it then`() {
        acceptedBy = { "H1" }
        val closed = open(admit("CS1"), "CS1")
        expectThat(closed.answerer()).isEqualTo("H1")
        closed.triggerClose(WsStatus.NORMAL)

        acceptedBy = { "H2" }
        val socket = open(admit("CS1"), "CS1")

        expect {
            that(socket.answerer()).isEqualTo("H2")
            that(connected).containsExactly("CS1", "CS1")
        }
    }

    @Test
    fun `should serve a handshake of a real server with the handlers that accepted it`() {
        val port = getFreePort()
        val opened = CountDownLatch(1)
        val wamp = UndertowOcppWampServer(
            setOf(OCPP_1_6),
            listeners = EventsListeners(onWsConnectHandler = { id, _ -> if (id == "CS1") opened.countDown() })
        )
        acceptedBy = { id -> if (id == "CS1") "H1" else "H2" }
        wamp.register(handler("H1"))
        wamp.register(handler("H2"))
        val server = wamp.asServer(port).start()
        val client = OcppWampClient.newClient(
            Uri.of("ws://localhost:$port/ws"),
            "CS2",
            OCPP_1_6,
            timeoutInMs = 5_000,
            autoReconnect = false
        )

        try {
            expectThat(handshake(port, "CS1").status).isEqualTo(101)
            // without the admission the WebSocket callback exposes, the open would fail and notify no connection
            expectThat(opened.await(5, TimeUnit.SECONDS)).isTrue()

            client.connect()
            expectThat(WampMessage.parse(client.sendBlocking(call()).toJson())!!.payload)
                .isEqualTo(answerOf("H2"))
        } finally {
            client.close()
            server.stop()
        }
    }

    private fun handler(name: String) = object : OcppWampServerHandler {
        override fun accept(ocppId: CSOcppId): Boolean = acceptedBy(ocppId) == name

        override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage =
            WampMessage.CallResult(msg.msgId, answerOf(name))
    }

    private fun answerOf(name: String) = """{"answeredBy":"$name"}"""

    private fun call() = WampMessage.Call("1", "Heartbeat", "{}")

    /** Admits a handshake of [ocppId] as the server would on the I/O thread, and returns its exchange. */
    private fun admit(ocppId: CSOcppId): HttpServerExchange {
        val exchange = HttpServerExchange(null).setRequestURI("/ws/$ocppId")
        expectThat(server.config().admitWebSocket(exchange)).isEqualTo(WebSocketUpgradeDecision.Accept)
        return exchange
    }

    /** Opens the connection of the handshake [exchange], as the WebSocket callback does once it is upgraded. */
    private fun open(exchange: HttpServerExchange, ocppId: CSOcppId): HandshakeWebsocket =
        HandshakeWebsocket().also {
            OpeningHandshake.opening(AsyncWebSocketHttpServerExchange(exchange, emptySet())) {
                server.config().handler(Request(GET, "/ws/$ocppId").header("Sec-WebSocket-Protocol", "ocpp1.6"))
                    .consumer(it)
            }
        }

    /** Opens the connection of [ocppId] on a thread where no handshake is opening. */
    private fun openOutsideHandshake(ocppId: CSOcppId): HandshakeWebsocket =
        HandshakeWebsocket().also {
            server.config().handler(Request(GET, "/ws/$ocppId").header("Sec-WebSocket-Protocol", "ocpp1.6"))
                .consumer(it)
        }

    @Test
    fun `should not leave the handshake of an opened connection to the next open of the thread`() {
        acceptedBy = { "H1" }
        open(admit("CS1"), "CS1")

        expectThrows<IllegalStateException> { openOutsideHandshake("CS2") }
        expectThat(connected).containsExactly("CS1")
    }

    @Test
    fun `should not leave the handshake of a failed open to the next open of the thread`() {
        acceptedBy = { "H1" }
        val failed = admit("CS1")

        expectThrows<IllegalArgumentException> {
            OpeningHandshake.opening(AsyncWebSocketHttpServerExchange(failed, emptySet())) {
                throw IllegalArgumentException("open failed")
            }
        }

        expectThrows<IllegalStateException> { openOutsideHandshake("CS2") }
        expectThat(connected).isEmpty()
    }

    @Test
    fun `should open with the outer handshake once a nested one has returned`() {
        acceptedBy = { "H1" }
        val outer = admit("CS1")
        acceptedBy = { "H2" }
        val inner = admit("CS1")

        val socket = HandshakeWebsocket()
        OpeningHandshake.opening(AsyncWebSocketHttpServerExchange(outer, emptySet())) {
            OpeningHandshake.opening(AsyncWebSocketHttpServerExchange(inner, emptySet())) {}
            server.config().handler(Request(GET, "/ws/CS1").header("Sec-WebSocket-Protocol", "ocpp1.6"))
                .consumer(socket)
        }

        expectThat(socket.answerer()).isEqualTo("H1")
    }

    /** Sends a CALL from the station and returns the name of the handler that answered it. */
    private fun HandshakeWebsocket.answerer(): String {
        val before = sent.size
        triggerMessage(WsMessage(call().toJson()))
        val payload = WampMessage.parse(sent.drop(before).single())!!.payload
        return Regex(""""answeredBy":"([^"]+)"""").find(payload)?.groupValues?.get(1) ?: payload
    }

    /** A socket recording what the server sends, opened with its handshake as the WebSocket callback opens one. */
    private class HandshakeWebsocket : PushPullAdaptingWebSocket() {
        val sent = CopyOnWriteArrayList<String>()

        override fun send(message: WsMessage) {
            sent.add(message.bodyString())
        }

        override fun close(status: WsStatus) = Unit
    }
}
