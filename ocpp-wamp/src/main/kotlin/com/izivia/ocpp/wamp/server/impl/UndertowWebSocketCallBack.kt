package com.izivia.ocpp.wamp.server.impl

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
            logger.debug(
                "Replace Sec-WebSocket-Location header {} double slash",
                exchange.getResponseHeader(SEC_WEBSOCKET_LOCATION)
            )
            exchange.setResponseHeader(
                SEC_WEBSOCKET_LOCATION,
                exchange.getResponseHeader(SEC_WEBSOCKET_LOCATION).replace(Regex("""([^:])//"""), "$1/")
            )
        }

        val upgradeRequest = exchange.asRequest(channel)
        val logContext = upgradeRequest.uri.toString()
        logger.debug("{} - connection attempt: upgrade request={}", logContext, upgradeRequest)

        val socket = object : PushPullAdaptingWebSocket() {
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
                    "$logContext - closing websocket: WsStatus $status, channel $channel," +
                        " closeCode: ${channel.closeCode}, closeReason: ${channel.closeReason}"
                )
                WebSockets.sendClose(status.code, status.description, channel, null)
            }
        }.apply(ws(upgradeRequest))

        channel.addCloseTask {
            val status = WsStatus(it.closeCode, it.closeReason ?: "unknown")
            logger.warn("$logContext - closing websocket: WsStatus $status channel $it")
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
                    logger.error("$logContext - IOException on $message - $e", e)
                    throw e
                } catch (e: Exception) {
                    logger.error("$logContext - Exception on $message - $e", e)
                    socket.triggerError(e)
                    throw e
                }
            }

            override fun onFullBinaryMessage(
                channel: WebSocketChannel,
                message: BufferedBinaryMessage
            ) =
                message.data.resource.forEach { socket.triggerMessage(WsMessage(it)) }

            override fun onError(channel: WebSocketChannel, error: Throwable) {
                logger.warn("$logContext - Exception $error", error)
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
