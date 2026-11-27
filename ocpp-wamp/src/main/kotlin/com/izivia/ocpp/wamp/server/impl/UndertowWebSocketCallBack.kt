package com.izivia.ocpp.wamp.server.impl

import com.izivia.ocpp.utils.escapedForLog
import com.izivia.ocpp.utils.redactedForLog
import io.undertow.server.HttpServerExchange
import io.undertow.websockets.WebSocketConnectionCallback
import io.undertow.websockets.core.*
import io.undertow.websockets.spi.WebSocketHttpExchange
import org.http4k.core.*
import org.http4k.websocket.PushPullAdaptingWebSocket
import org.http4k.websocket.WsHandler
import org.http4k.websocket.WsMessage
import org.http4k.websocket.WsStatus
import org.slf4j.LoggerFactory
import java.io.IOException
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Taken from Http4kUndertowWebSocketCallBack
 * With improvement on upgradeRequest to add source field
 * with caller ip address
 */
class UndertowWebSocketCallBack(private val ws: WsHandler) : WebSocketConnectionCallback {

    private val SEC_WEBSOCKET_LOCATION = "Sec-WebSocket-Location"

    companion object {
        private val logger = LoggerFactory.getLogger(UndertowWebSocketCallBack::class.java)
        private val doubleSlashRegex = Regex("""[^:]//""")
    }

    override fun onConnect(exchange: WebSocketHttpExchange, channel: WebSocketChannel) {
        // When chargerStation connect with //
        if (exchange.getResponseHeader(SEC_WEBSOCKET_LOCATION)?.contains(doubleSlashRegex) == true) {
            // the header value echoes the request uri, query string included: it is not logged
            logger.debug("Replace Sec-WebSocket-Location header double slash")
            exchange.setResponseHeader(
                SEC_WEBSOCKET_LOCATION,
                exchange.getResponseHeader(SEC_WEBSOCKET_LOCATION).replace(Regex("""([^:])//"""), "$1/")
            )
        }

        val upgradeRequest = exchange.asRequest(channel)
        // the path only, as the query string may carry a token, and the header names only, as their values may too
        val logContext = upgradeRequest.uri.path.escapedForLog(MAX_LOGGED_PATH)
        if (logger.isDebugEnabled) {
            logger.debug(
                "{} - connection attempt: upgrade request headers={}",
                logContext,
                upgradeRequest.headers.joinToString(prefix = "[", postfix = "]") { it.first }.escapedForLog(1024)
            )
        }

        val socket = object : PushPullAdaptingWebSocket(), ControlFrameAwareWebsocket {
            private val pingHandlers = CopyOnWriteArrayList<() -> Unit>()
            private val pongHandlers = CopyOnWriteArrayList<() -> Unit>()

            override fun onPing(fn: () -> Unit) {
                pingHandlers.add(fn)
            }

            override fun onPong(fn: () -> Unit) {
                pongHandlers.add(fn)
            }

            fun triggerPing() = pingHandlers.forEach { it() }

            fun triggerPong() = pongHandlers.forEach { it() }

            override fun send(message: WsMessage) =
                if (message.body is StreamBody) {
                    WebSockets.sendBinary(
                        message.body.payload,
                        channel,
                        null
                    )
                } else {
                    WebSockets.sendText(message.bodyString(), channel, null)
                }

            override fun close(status: WsStatus) {
                logger.info(
                    "$logContext - closing websocket: WsStatus ${status.logged()}, channel $channel," +
                        " closeCode: ${channel.closeCode}, closeReason: ${channel.closeReason?.escapedForLog()}"
                )
                WebSockets.sendClose(status.code, status.description, channel, null)
            }
        }.apply(ws(upgradeRequest))

        channel.addCloseTask {
            val status = WsStatus(it.closeCode, it.closeReason ?: "unknown")
            logger.warn("$logContext - closing websocket: WsStatus ${status.logged()} channel $it")
            socket.triggerClose(status)
        }

        channel.receiveSetter.set(object : AbstractReceiveListener() {
            override fun onFullTextMessage(
                channel: WebSocketChannel,
                message: BufferedTextMessage
            ) {
                try {
                    socket.triggerMessage(WsMessage(message.data))
                } catch (e: IOException) {
                    logger.error(
                        "$logContext - IOException on message length=${message.data.length} - ${e.javaClass.name}",
                        e.redactedForLog()
                    )
                    throw e
                } catch (e: Exception) {
                    logger.error(
                        "$logContext - Exception on message length=${message.data.length} - ${e.javaClass.name}",
                        e.redactedForLog()
                    )
                    socket.triggerError(e)
                    throw e
                }
            }

            override fun onFullBinaryMessage(
                channel: WebSocketChannel,
                message: BufferedBinaryMessage
            ) =
                message.data.resource.forEach { socket.triggerMessage(WsMessage(it)) }

            override fun onFullPingMessage(channel: WebSocketChannel, message: BufferedBinaryMessage) {
                // replies with a pong and frees the message
                super.onFullPingMessage(channel, message)
                socket.triggerPing()
            }

            override fun onFullPongMessage(channel: WebSocketChannel, message: BufferedBinaryMessage) {
                message.data.free()
                socket.triggerPong()
            }

            override fun onError(channel: WebSocketChannel, error: Throwable) {
                logger.warn("$logContext - Exception ${error.javaClass.name}", error.redactedForLog())
                return socket.triggerError(error)
            }
        })
        channel.resumeReceives()
    }
}

private fun WebSocketHttpExchange.asRequest(channel: WebSocketChannel) =
    Request(Method.GET, requestURI.replace("//", "/"))
        .headers(requestHeaders.toList().flatMap { h -> h.second.map { h.first to it } })
        .source(
            RequestSource(
                channel.sourceAddress.hostString,
                channel.sourceAddress.port,
                channel.requestScheme
            )
        )

/** A WebSocket status in a log line: its description may come from the peer, so it is escaped. */
private fun WsStatus.logged() = "$code ${description.escapedForLog()}"

/** The upgrade path is bounded higher than a station id: it holds the endpoint path too. */
private const val MAX_LOGGED_PATH = 256

fun requiresWebSocketUpgrade(): (HttpServerExchange) -> Boolean = { httpServerExchange ->
    val containsValidConnectionHeader = httpServerExchange.requestHeaders["Connection"]
        ?.any { headerValue ->
            headerValue.split(",")
                .map { it.trim().lowercase() }
                .contains("upgrade")
        } ?: false

    val containsValidUpgradeHeader = httpServerExchange.requestHeaders["Upgrade"]
        ?.any { it.equals("websocket", true) } ?: false

    containsValidConnectionHeader && containsValidUpgradeHeader
}
