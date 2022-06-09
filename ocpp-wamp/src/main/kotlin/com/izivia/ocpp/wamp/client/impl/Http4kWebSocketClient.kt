package com.izivia.ocpp.wamp.client.impl

import org.http4k.client.internal.AdaptingWebSocket
import org.http4k.client.internal.NonBlockingClient
import org.http4k.core.Headers
import org.http4k.core.Uri
import org.http4k.websocket.*
import org.java_websocket.drafts.Draft
import org.java_websocket.drafts.Draft_6455
import java.net.ConnectException
import java.time.Duration
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicReference

object Http4kWebSocketClient {
    // copied from http4k WebSocketClient - but with a blocking connect call and allowing to register callbacks before connect
    // indeed in case of 404 the on connect is not called, neither onError - so we cant use our own latch
    fun connnectWebsocket(uri: Uri, headers: Headers = emptyList(), timeout: Duration = Duration.ZERO,
                          onError: (Throwable) -> Unit = {}, onMessage: (WsMessage) -> Unit = {},
                          onClose: (WsStatus) -> Unit = {},
                          draft: Draft = Draft_6455(), onConnect: WsConsumer = {}): Websocket {
        val socket = AtomicReference<PushPullAdaptingWebSocket>()
        val client = NonBlockingClient(uri, headers, timeout, onConnect, draft, socket)
        socket.set(AdaptingWebSocket(uri, client).apply {
            onMessage(onMessage)
            onError(onError)
            onClose(onClose)
        })
        if (!client.connectBlocking(timeout.toMillis(), TimeUnit.MILLISECONDS)) {
            throw ConnectException("connection to $uri failed - the ocpp id may not be recognized").also(onError)
        }
        return socket.get()
    }
}
