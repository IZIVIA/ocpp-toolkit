package com.izivia.ocpp.wamp

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion.OCPP_1_6
import com.izivia.ocpp.wamp.client.OcppWampClient
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.server.OcppWampServer
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import com.izivia.ocpp.wamp.server.asServer
import com.izivia.ocpp.wamp.server.impl.EventsListeners
import com.izivia.ocpp.wamp.server.impl.Undertow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.http4k.core.PolyHandler
import org.http4k.core.Status
import org.http4k.core.Uri
import org.http4k.server.asServer
import org.junit.jupiter.api.Test
import strikt.api.expect
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.isA
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull
import java.io.IOException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import org.http4k.core.Response as HttpResponse

class WampRefusedHandshakeTest {
    private val port = getFreePort()

    @Test
    fun `refused station does not complete the handshake`() {
        val connected = CopyOnWriteArrayList<String>()
        val wamp = OcppWampServer.newServer(
            setOf(OCPP_1_6),
            listeners = EventsListeners(onWsConnectHandler = { id, _ -> connected += id })
        )
        wamp.register(handler(accept = { false }))
        val server = wamp.asServer(port).start()

        try {
            expectThat(handshakeStatus("CS1")).isEqualTo(404)
            expectThat(connected).isEmpty()
        } finally {
            server.stop()
        }
    }

    @Test
    fun `refused station does not evict an accepted one differing by case`() {
        val reconnected = CopyOnWriteArrayList<String>()
        val wamp = OcppWampServer.newServer(
            setOf(OCPP_1_6),
            listeners = EventsListeners(onWsReconnectHandler = { id, _ -> reconnected += id })
        )
        wamp.register(handler(accept = { it == "CP1" }))
        val server = wamp.asServer(port).start()
        val accepted = answeringClient("CP1")
        val refused = answeringClient("cp1")

        try {
            accepted.connect()

            val attempt = runCatching { refused.connect() }
            val answer = wamp.sendBlocking("CP1", WampMessage.Call("1", "Reset", "{}"), timeoutInMs = 5_000)

            expect {
                that(attempt.exceptionOrNull()).isA<IOException>().get { message }.isNotNull().contains("404")
                that(reconnected).isEmpty()
                that(answer.payload).isEqualTo("""{"answeredBy":"CP1"}""")
            }
        } finally {
            refused.close()
            accepted.close()
            server.stop()
        }
    }

    @Test
    fun `station refused then provisioned connects and has its calls handled`() {
        val provisioned = AtomicBoolean(false)
        val wamp = OcppWampServer.newServer(setOf(OCPP_1_6))
        wamp.register(handler(accept = { provisioned.get() }))
        val server = wamp.asServer(port).start()
        val client = answeringClient("CS1")

        try {
            expectThat(handshakeStatus("CS1")).isEqualTo(404)

            provisioned.set(true)
            client.connect()

            expectThat(client.sendBlocking(WampMessage.Call("1", "Heartbeat", "{}")).payload)
                .isEqualTo("""{"handled":true}""")
        } finally {
            client.close()
            server.stop()
        }
    }

    @Test
    fun `upgrade on a server without websocket routes reaches the http handler`() {
        val server = PolyHandler(http = { HttpResponse(Status.CONFLICT) })
            .asServer(Undertow(port, enableHttp2 = false))
            .start()

        try {
            expectThat(handshakeStatus("CS1")).isEqualTo(Status.CONFLICT.code)
        } finally {
            server.stop()
        }
    }

    private fun handler(accept: (CSOcppId) -> Boolean) = object : OcppWampServerHandler {
        override fun accept(ocppId: CSOcppId): Boolean = accept(ocppId)

        override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? =
            WampMessage.CallResult(msg.msgId, """{"handled":true}""")
    }

    private fun answeringClient(ocppId: CSOcppId) =
        OcppWampClient.newClient(
            Uri.of("ws://localhost:$port/ws"),
            ocppId,
            OCPP_1_6,
            timeoutInMs = 5_000,
            autoReconnect = false
        ).also { client ->
            client.onAction { _, msg -> WampMessage.CallResult(msg.msgId, """{"answeredBy":"$ocppId"}""") }
        }

    private fun handshakeStatus(ocppId: CSOcppId): Int? {
        val status = CompletableFuture<Int?>()
        val request = Request.Builder()
            .url("ws://localhost:$port/ws/$ocppId")
            .header("Sec-WebSocket-Protocol", OCPP_1_6.subprotocol)
            .build()
        val webSocket = OkHttpClient().newWebSocket(
            request,
            object : WebSocketListener() {
                override fun onOpen(webSocket: WebSocket, response: Response) {
                    status.complete(response.code)
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    status.complete(response?.code)
                }
            }
        )
        return try {
            status.get(5, TimeUnit.SECONDS)
        } finally {
            webSocket.cancel()
        }
    }
}
