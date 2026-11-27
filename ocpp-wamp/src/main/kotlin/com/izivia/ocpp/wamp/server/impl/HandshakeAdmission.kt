package com.izivia.ocpp.wamp.server.impl

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import io.undertow.util.AttachmentKey
import io.undertow.websockets.WebSocketConnectionCallback
import io.undertow.websockets.spi.WebSocketHttpExchange

/** What the admission of a handshake decided: the station it opens for, and the handlers that accepted it. */
internal class HandshakeAdmission(val ocppId: CSOcppId, val handlers: List<OcppWampServerHandler>)

/**
 * The WebSocket handshake whose connection opens on the calling thread: what the admission of a handshake decided
 * reaches the opening of that handshake's own connection, and no other.
 *
 * It is bound to the call of the WebSocket handler that opens the connection, not to the socket, so that it survives
 * a [org.http4k.websocket.WsFilter] wrapping the request or the socket. The server's handler reads it when called,
 * so such a filter must call the downstream handler on the calling thread, synchronously, but may call the consumer
 * of its response later or on another thread. A downstream handler called later or elsewhere finds no handshake:
 * its connection is closed with status 1011, fails with an [IllegalStateException] and registers nothing. A consumer
 * called later must still subscribe before the station can send: the frames and the close received before it
 * subscribes reach no handler and are lost.
 */
internal object OpeningHandshake {
    private val current = ThreadLocal<WebSocketHttpExchange?>()

    /** [callback], opening each connection with its own handshake as the opening handshake of the calling thread. */
    fun carriedBy(callback: WebSocketConnectionCallback) = WebSocketConnectionCallback { exchange, channel ->
        opening(exchange) { callback.onConnect(exchange, channel) }
    }

    /** Runs [open] with [exchange] as the opening handshake of the calling thread, then restores the previous one. */
    fun <T> opening(exchange: WebSocketHttpExchange, open: () -> T): T {
        val previous = current.get()
        current.set(exchange)
        try {
            return open()
        } finally {
            if (previous == null) current.remove() else current.set(previous)
        }
    }

    /** The attachment [key] of the handshake opening on the calling thread, or null when none opens or it has none. */
    fun <T> attachment(key: AttachmentKey<T>): T? = current.get()?.getAttachment(key)
}
