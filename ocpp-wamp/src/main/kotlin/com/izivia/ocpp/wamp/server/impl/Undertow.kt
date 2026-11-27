package com.izivia.ocpp.wamp.server.impl

import com.izivia.ocpp.wamp.server.WebSocketUpgradeDecision
import io.undertow.Handlers.*
import io.undertow.Undertow
import io.undertow.UndertowOptions.ENABLE_HTTP2
import io.undertow.server.HttpServerExchange
import io.undertow.server.handlers.BlockingHandler
import io.undertow.websockets.WebSocketProtocolHandshakeHandler
import io.undertow.websockets.core.protocol.version07.Hybi07Handshake
import io.undertow.websockets.core.protocol.version08.Hybi08Handshake
import io.undertow.websockets.core.protocol.version13.Hybi13Handshake
import org.http4k.core.HttpHandler
import org.http4k.core.Response
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.server.*
import org.http4k.sse.SseHandler
import org.http4k.websocket.WsHandler
import java.net.InetSocketAddress
import java.util.concurrent.atomic.AtomicBoolean
import io.undertow.server.HttpHandler as UndertowHttpHandler

/*
    This class is inspired by the one provided by http4k project, with the following additions:
    - support for websocket subprotocols
    - support to decide, before upgrading the protocol, whether a websocket request is accepted
 */
/**
 * An Undertow server for http4k, which decides on each WebSocket upgrade request before upgrading it.
 *
 * @property port the port to listen on, or 0 for a free one
 * @property enableHttp2 whether HTTP/2 is enabled
 * @property admitWebSocket decides whether a WebSocket upgrade request is accepted; null means that no endpoint claims
 * it, which fails with a 404, and a throw fails with a 500
 * @property wsSubprotocols the WebSocket subprotocols the server negotiates, none when empty
 * @property admission where [admitWebSocket] runs: on the I/O thread by default, see [AdmissionExecution]
 */
class Undertow(val port: Int = 8000, val enableHttp2: Boolean,
               val admitWebSocket: (HttpServerExchange) -> WebSocketUpgradeDecision? = { WebSocketUpgradeDecision.Accept },
               val wsSubprotocols:Set<String> = setOf(),
               val admission: AdmissionExecution = AdmissionExecution.Inline) : PolyServerConfig {

    /**
     * A server running [admitWebSocket] on the I/O thread: the constructor of the releases before [admission], kept
     * for the Java and compiled callers.
     */
    // its default values keep the synthetic constructor the callers compiled against the previous releases use
    constructor(
        port: Int = 8000,
        enableHttp2: Boolean,
        admitWebSocket: (HttpServerExchange) -> WebSocketUpgradeDecision? = { WebSocketUpgradeDecision.Accept },
        wsSubprotocols: Set<String> = setOf(),
    ) : this(port, enableHttp2, admitWebSocket, wsSubprotocols, AdmissionExecution.Inline)

    // test seams: the number of I/O threads, Undertow's default when null, and a step run by stop() once the server
    // refuses to upgrade and before it closes its connections
    internal var ioThreads: Int? = null
    internal var beforeServerStop: () -> Unit = {}

    override fun toServer(http: HttpHandler?, ws: WsHandler?, sse: SseHandler?): Http4kServer {
        val stopped = AtomicBoolean()
        val httpHandler =
            (http ?: { Response(BAD_REQUEST) }).let (::Http4kUndertowHttpHandler).let(::BlockingHandler)
        val wsCallback = ws?.let {
            if (wsSubprotocols.isEmpty()) {
                websocket(OpeningHandshake.carriedBy(Http4kWebSocketCallback(it)))
            } else {
                WebSocketProtocolHandshakeHandler(
                    listOf(
                        Hybi13Handshake(wsSubprotocols, false),
                        Hybi08Handshake(wsSubprotocols, false),
                        Hybi07Handshake(wsSubprotocols, false),
                    ),
                    UndertowWebSocketCallBack(it)
                )
            }
        }

        val handlerWithWs = wsCallback?.let {
            val admissionHandler = when (val execution = admission) {
                AdmissionExecution.Inline -> admissionHandler(it)
                is AdmissionExecution.Dispatched -> DispatchedAdmissionHandler(admitWebSocket, it, execution, stopped)
            }
            predicate(requiresWebSocketUpgrade(), admissionHandler, httpHandler)
        } ?: httpHandler

        return object : Http4kServer {
            val server = Undertow.builder()
                .addHttpListener(port, "0.0.0.0")
                .setServerOption(ENABLE_HTTP2, enableHttp2)
                .setWorkerThreads(32 * Runtime.getRuntime().availableProcessors())
                .apply { ioThreads?.let(::setIoThreads) }
                .setHandler(handlerWithWs).build()

            override fun start() = apply { server.start() }

            // no handshake is upgraded once stopping, even one whose dispatched admission decides during the stop
            override fun stop() = apply {
                stopped.set(true)
                beforeServerStop()
                server.stop()
            }

            override fun port(): Int = when {
                port > 0 -> port
                else -> (server.listenerInfo[0].address as InetSocketAddress).port
            }
        }
    }

    // a refused upgrade fails with the status of its refusal, or the 404 of OCPP-J when no endpoint
    // claims it: it never reaches the http handler. Inline, admitWebSocket runs on the I/O thread, and a throw
    // becomes the 500 of Connectors.executeRootHandler
    private fun admissionHandler(upgrade: UndertowHttpHandler) = UndertowHttpHandler { exch ->
        when (val decision = admitWebSocket(exch) ?: WebSocketUpgradeDecision.Refuse(NOT_FOUND)) {
            WebSocketUpgradeDecision.Accept -> upgrade.handleRequest(exch)
            is WebSocketUpgradeDecision.Refuse -> refusal(decision).handleRequest(exch)
        }
    }
}
