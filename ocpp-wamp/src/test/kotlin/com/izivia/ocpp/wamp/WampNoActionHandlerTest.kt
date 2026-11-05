package com.izivia.ocpp.wamp

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion.OCPP_1_6
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.server.OcppWampServer
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import com.izivia.ocpp.wamp.server.asServer
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit

/**
 * Reads the frames through a raw WebSocket: the lenient regex parser behind `OcppWampClient` accepts a malformed
 * CALLERROR, so it can't tell whether the frame is valid JSON.
 */
class WampNoActionHandlerTest {
    private val port = getFreePort()
    private val strictJson = ObjectMapper().enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)

    @ParameterizedTest
    @ValueSource(
        strings = [
            """{"type":"ResetOrReboot","timestamp":"2026-11-05T13:00:00Z"}""",
            """{"note":"say \"hi\""}""",
            """{"path":"C:\\temp\\log"}""",
            """{"a":{"b":[{"c":"d"}]}}""",
        ]
    )
    fun `should answer a strictly valid call error when no handler matches`(payload: String) {
        val frame = exchange("""[2,"8","SecurityEventNotification",$payload]""")

        expectThat(frame).isEqualTo("""[4,"8","InternalError","No action handler found",{}]""")
        expectThat(strictJson.readTree(frame).size()).isEqualTo(5)
    }

    @Test
    fun `should preserve a message id containing a backslash when no handler matches`() {
        val frame = exchange("""[2,"a\\b","SecurityEventNotification",{"k":"v"}]""")

        expectThat(strictJson.readTree(frame)[1].asText()).isEqualTo("""a\b""")
    }

    private fun exchange(call: String): String {
        val wampServer = OcppWampServer.newServer(setOf(OCPP_1_6))
        val server = wampServer.asServer(port)
        wampServer.register(object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId): Boolean = "TEST1" == ocppId

            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? = null
        })
        server.start()

        val client = OkHttpClient()
        try {
            val frames = LinkedBlockingQueue<String>()
            val socket = client.newWebSocket(
                Request.Builder().url("ws://localhost:$port/ws/TEST1")
                    .header("Sec-WebSocket-Protocol", OCPP_1_6.subprotocol)
                    .build(),
                object : WebSocketListener() {
                    override fun onOpen(webSocket: WebSocket, response: Response) {
                        webSocket.send(call)
                    }

                    override fun onMessage(webSocket: WebSocket, text: String) {
                        frames.put(text)
                    }
                }
            )
            val frame = frames.poll(5, TimeUnit.SECONDS)
            socket.close(1000, null)
            expectThat(frame).isNotNull()
            return frame!!
        } finally {
            client.dispatcher.executorService.shutdown()
            client.connectionPool.evictAll()
            server.stop()
        }
    }
}
