package com.izivia.ocpp.wamp.server.impl

/**
 * Exposes the WebSocket control frames that http4k's `Websocket` does not surface.
 */
internal interface ControlFrameAwareWebsocket {
    fun onPing(fn: () -> Unit)
    fun onPong(fn: () -> Unit)
}
