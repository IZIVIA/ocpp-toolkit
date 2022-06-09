package com.izivia.ocpp.wamp.server.impl

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
import org.http4k.server.*
import org.http4k.sse.SseHandler
import org.http4k.websocket.WsHandler
import java.net.InetSocketAddress

/*
    This class is inspired by the one provided by http4k project, with the following additions:
    - support for websocket subprotocols
    - support to provide a predicate before upgrading the protocol for a websocket request
 */
class Undertow(val port: Int = 8000, val enableHttp2: Boolean,
               val acceptWebSocketPredicate:(HttpServerExchange) -> Boolean = { true },
               val wsSubprotocols:Set<String> = setOf()) : PolyServerConfig {
    override fun toServer(http: HttpHandler?, ws: WsHandler?, sse: SseHandler?): Http4kServer {
        val httpHandler =
            (http ?: { Response(BAD_REQUEST) }).let (::Http4kUndertowHttpHandler).let(::BlockingHandler)
        val wsCallback = ws?.let {
            if (wsSubprotocols.isEmpty()) {
                websocket(Http4kWebSocketCallback(it))
            } else {
                WebSocketProtocolHandshakeHandler(
                    listOf(
                        Hybi13Handshake(wsSubprotocols, false),
                        Hybi08Handshake(wsSubprotocols, false),
                        Hybi07Handshake(wsSubprotocols, false),
                    ),
                    Http4kWebSocketCallback(it)
                )
            }
        }
        val sseCallback = sse?.let { serverSentEvents(Http4kSseCallback(sse)) }

        val handlerWithWs = predicate(
            { exch -> requiresWebSocketUpgrade()(exch) && acceptWebSocketPredicate(exch)},
            wsCallback, httpHandler)

        val handlerWithSse = sseCallback
            ?.let { predicate(hasEventStreamContentType(), sseCallback, handlerWithWs) }
            ?: handlerWithWs

        return object : Http4kServer {
            val server = Undertow.builder()
                .addHttpListener(port, "0.0.0.0")
                .setServerOption(ENABLE_HTTP2, enableHttp2)
                .setHandler(handlerWithSse).build()

            override fun start() = apply { server.start() }

            override fun stop() = apply { server.stop() }

            override fun port(): Int = when {
                port > 0 -> port
                else -> (server.listenerInfo[0].address as InetSocketAddress).port
            }
        }
    }
}
