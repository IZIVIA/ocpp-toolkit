package com.izivia.ocpp.wamp.server.impl

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion.OCPP_1_6
import com.izivia.ocpp.wamp.getFreePort
import com.izivia.ocpp.wamp.handshake
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import com.izivia.ocpp.wamp.server.WebSocketUpgradeDecision
import kotlin.concurrent.thread
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.http4k.core.PolyHandler
import org.http4k.core.Request
import org.http4k.routing.websockets
import org.http4k.server.Http4kServer
import org.http4k.server.asServer
import org.http4k.websocket.Websocket
import org.http4k.websocket.WsFilter
import org.http4k.websocket.WsMessage
import org.http4k.websocket.WsResponse
import org.http4k.websocket.then
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import strikt.api.expect
import strikt.api.expectThat
import strikt.assertions.containsExactly
import strikt.assertions.isA
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo
import strikt.assertions.isNull
import strikt.assertions.isTrue
import java.time.Duration
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import okhttp3.Request as OkRequest

/**
 * Serves the server's handler behind a [WsFilter], as an application composes it: the toolkit's [Undertow] with the
 * server's admission and subprotocols, and the handler wrapped by a filter that hands the connection a socket of its
 * own rather than the one the WebSocket callback built.
 */
class UndertowOcppWampServerFilterTest {
    private val connected = CopyOnWriteArrayList<CSOcppId>()
    private val opened = CountDownLatch(1)
    private val wamp = UndertowOcppWampServer(
        setOf(OCPP_1_6),
        listeners = EventsListeners(onWsConnectHandler = { id, _ ->
            connected += id
            opened.countDown()
        })
    )
    private val servers = mutableListOf<Http4kServer>()

    init {
        wamp.register(handler("H1") { it == "CS1" })
        wamp.register(handler("H2") { it == "CS2" })
    }

    @AfterEach
    fun stop() {
        servers.forEach { it.stop() }
    }

    @Test
    fun `should serve a station through a filter delegating the websocket`() {
        val port = serve(delegatingWebsocket)

        val answer = callOver(port, "CS1")

        expect {
            that(answer).isEqualTo(answerOf("H1"))
            that(opened.await(5, TimeUnit.SECONDS)).isTrue()
            that(connected).containsExactly("CS1")
        }
    }

    @Test
    fun `should serve a station admitted off the I-O thread through a filter delegating the websocket`() {
        val port = serve(delegatingWebsocket, AdmissionExecution.Dispatched(4, Duration.ofSeconds(5)))

        val answer = callOver(port, "CS1")

        expect {
            that(answer).isEqualTo(answerOf("H1"))
            that(opened.await(5, TimeUnit.SECONDS)).isTrue()
            that(connected).containsExactly("CS1")
        }
    }

    @Test
    fun `should serve a station through a filter rewriting the request and delegating the websocket`() {
        val rewritingRequest = WsFilter { next ->
            { request ->
                next(Request(request.method, request.uri).headers(request.headers).header("x-filtered", "true"))
            }
        }
        val port = serve(rewritingRequest.then(delegatingWebsocket))

        val answer = callOver(port, "CS1")

        expect {
            that(answer).isEqualTo(answerOf("H1"))
            that(opened.await(5, TimeUnit.SECONDS)).isTrue()
            that(connected).containsExactly("CS1")
        }
    }

    @Test
    fun `should serve a station through a filter rewriting the request only`() {
        val port = serve(rewritingRequest)

        val answer = callOver(port, "CS1")

        expect {
            that(answer).isEqualTo(answerOf("H1"))
            that(opened.await(5, TimeUnit.SECONDS)).isTrue()
            that(connected).containsExactly("CS1")
        }
    }

    @Test
    fun `should serve a station through a server negotiating no subprotocol`() {
        val port = getFreePort()
        val config = wamp.config()
        // Undertow's default: the WebSocket callback is http4k's rather than the toolkit's
        servers += PolyHandler(ws = websockets(delegatingWebsocket.then(config.handler))).asServer(
            Undertow(port, enableHttp2 = true, admitWebSocket = config.admitWebSocket, wsSubprotocols = emptySet())
        ).start()

        val answer = callOver(port, "CS1")

        expect {
            that(answer).isEqualTo(answerOf("H1"))
            that(opened.await(5, TimeUnit.SECONDS)).isTrue()
            that(connected).containsExactly("CS1")
        }
    }

    @Test
    fun `should serve a station whose filter calls the consumer later on another thread`() {
        val consumed = CompletableFuture<Throwable?>()
        val deferred = WsFilter { next ->
            { request ->
                val response = next(request)
                response.withConsumer { ws ->
                    val delegating = object : Websocket by ws {}
                    val finished = CountDownLatch(1)
                    // later: only once this consumer has finished on the thread opening the connection
                    thread {
                        consumed.complete(
                            runCatching {
                                check(finished.await(5, TimeUnit.SECONDS)) { "the filter's consumer never finished" }
                                response.consumer(delegating)
                            }.exceptionOrNull()
                        )
                    }
                    finished.countDown()
                }
            }
        }
        val port = serve(deferred)

        // the CALL is sent once the deferred consumer has subscribed to the messages
        val answer = callOver(port, "CS1", sendAfter = consumed)

        expect {
            that(consumed.get(5, TimeUnit.SECONDS)).isNull()
            that(answer).isEqualTo(answerOf("H1"))
            that(connected).containsExactly("CS1")
        }
    }

    @Test
    fun `should close with 1011 a station whose filter calls the downstream handler off the calling thread`() {
        val failure = CompletableFuture<Throwable?>()
        val port = serve(callingDownstreamOffThread(failure))

        val closeCode = closeCodeOf(port, "CS1")

        expect {
            that(closeCode).isEqualTo(1011)
            that(failure.get(5, TimeUnit.SECONDS)).isA<IllegalStateException>()
            that(opened.count).isEqualTo(1L)
            that(connected).isEmpty()
        }
    }

    @Test
    fun `should close with 1011 a station whose filter calls the downstream handler off the calling thread on a server negotiating no subprotocol`() {
        val failure = CompletableFuture<Throwable?>()
        val port = serve(callingDownstreamOffThread(failure), negotiatesSubprotocols = false)

        val closeCode = closeCodeOf(port, "CS1")

        expect {
            that(closeCode).isEqualTo(1011)
            that(failure.get(5, TimeUnit.SECONDS)).isA<IllegalStateException>()
            that(opened.count).isEqualTo(1L)
            that(connected).isEmpty()
        }
    }

    @Test
    fun `should close with 1011 a station opened without the admission of its handshake`() {
        val port = getFreePort()
        val config = wamp.config()
        // upgrades without the server's admission, so that the open fails on the calling thread
        servers += PolyHandler(ws = websockets(config.handler)).asServer(
            Undertow(
                port,
                enableHttp2 = true,
                admitWebSocket = { WebSocketUpgradeDecision.Accept },
                wsSubprotocols = config.wsSubprotocols,
            )
        ).start()

        val closeCode = closeCodeOf(port, "CS1")

        expect {
            that(closeCode).isEqualTo(1011)
            that(opened.count).isEqualTo(1L)
            that(connected).isEmpty()
        }
    }

    @Test
    fun `should not let forged headers change the station or its handlers`() {
        val port = serve(delegatingWebsocket)

        val answer = callOver(port, "CS1", *forgedHeaders)

        expect {
            that(answer).isEqualTo(answerOf("H1"))
            that(opened.await(5, TimeUnit.SECONDS)).isTrue()
            that(connected).containsExactly("CS1")
            that(handshake(port, "CS3", *forgedHeaders).status).isEqualTo(404)
            that(connected).containsExactly("CS1")
        }
    }

    // forged by the station: names http4k or the toolkit might read, each pointing at another station or handler
    private val forgedHeaders = arrayOf(
        "x-http4k-context" to "CS2",
        "x-uri-template" to "/ws/CS2",
        "x-ocpp-admission" to "CS2 H2",
        "x-ocpp-id" to "CS2",
    )

    /**
     * Serves [wamp] through [filter] on a free port, as an application composing its own handler does, negotiating
     * the server's subprotocols unless [negotiatesSubprotocols] is false.
     */
    private fun serve(
        filter: WsFilter,
        admission: AdmissionExecution = AdmissionExecution.Inline,
        negotiatesSubprotocols: Boolean = true,
    ): Int {
        val port = getFreePort()
        val config = wamp.config()
        val server = PolyHandler(ws = websockets(filter.then(config.handler))).asServer(
            Undertow(
                port,
                enableHttp2 = true,
                admitWebSocket = config.admitWebSocket,
                wsSubprotocols = if (negotiatesSubprotocols) config.wsSubprotocols else emptySet(),
                admission = admission,
            )
        ).start()
        servers += server
        return port
    }

    /** Calls the downstream handler on another thread, completing [failure] with what that call threw, if anything. */
    private fun callingDownstreamOffThread(failure: CompletableFuture<Throwable?>) = WsFilter { next ->
        { request ->
            WsResponse { ws ->
                thread { failure.complete(runCatching { next(request).consumer(ws) }.exceptionOrNull()) }
            }
        }
    }

    private fun handler(name: String, accepts: (CSOcppId) -> Boolean) = object : OcppWampServerHandler {
        override fun accept(ocppId: CSOcppId): Boolean = accepts(ocppId)

        override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage =
            WampMessage.CallResult(msg.msgId, answerOf(name))
    }

    private fun answerOf(name: String) = """{"answeredBy":"$name"}"""

    /**
     * Opens `/ws/<ocppId>` with raw OkHttp, sends one CALL and returns the payload of its answer, or fails when the
     * station is not answered within 5 seconds. The CALL waits for [sendAfter] to complete, when given.
     */
    private fun callOver(
        port: Int,
        ocppId: CSOcppId,
        vararg headers: Pair<String, String>,
        sendAfter: CompletableFuture<Throwable?>? = null,
    ): String {
        val answer = CompletableFuture<String>()
        val request = OkRequest.Builder()
            .url("ws://localhost:$port/ws/$ocppId")
            .header("Sec-WebSocket-Protocol", OCPP_1_6.subprotocol)
            .apply { headers.forEach { (name, value) -> header(name, value) } }
            .build()
        val webSocket = OkHttpClient().newWebSocket(
            request,
            object : WebSocketListener() {
                override fun onOpen(webSocket: WebSocket, response: Response) {
                    val send = { webSocket.send(WampMessage.Call("1", "Heartbeat", "{}").toJson()) }
                    sendAfter?.whenComplete { _, _ -> send() } ?: send()
                }

                override fun onMessage(webSocket: WebSocket, text: String) {
                    answer.complete(WampMessage.parse(text)!!.payload)
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    answer.completeExceptionally(t)
                }
            }
        )
        return try {
            answer.get(5, TimeUnit.SECONDS)
        } finally {
            webSocket.cancel()
        }
    }

    /**
     * Opens `/ws/<ocppId>` with raw OkHttp and returns the code of the close frame the server sends, or fails when
     * none arrives within 5 seconds.
     */
    private fun closeCodeOf(port: Int, ocppId: CSOcppId): Int {
        val closeCode = CompletableFuture<Int>()
        val request = OkRequest.Builder()
            .url("ws://localhost:$port/ws/$ocppId")
            .header("Sec-WebSocket-Protocol", OCPP_1_6.subprotocol)
            .build()
        val webSocket = OkHttpClient().newWebSocket(
            request,
            object : WebSocketListener() {
                override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                    closeCode.complete(code)
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    closeCode.completeExceptionally(t)
                }
            }
        )
        return try {
            closeCode.get(5, TimeUnit.SECONDS)
        } finally {
            webSocket.cancel()
        }
    }

    private companion object {
        /** Hands the downstream handler a request of its own, carrying the headers of the upgrade request and one more. */
        val rewritingRequest = WsFilter { next ->
            { request ->
                next(Request(request.method, request.uri).headers(request.headers).header("x-filtered", "true"))
            }
        }


        /** Hands the connection a socket of its own, delegating to the one the WebSocket callback built. */
        val delegatingWebsocket = WsFilter { next ->
            { request ->
                val response = next(request)
                response.withConsumer { ws ->
                    response.consumer(object : Websocket by ws {
                        override fun onMessage(fn: (WsMessage) -> Unit) = ws.onMessage { fn(it) }
                    })
                }
            }
        }
    }
}
