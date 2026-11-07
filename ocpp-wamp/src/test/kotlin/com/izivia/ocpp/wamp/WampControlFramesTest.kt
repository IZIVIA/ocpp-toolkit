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
import com.izivia.ocpp.wamp.server.impl.WsControlFrameEvent
import org.http4k.core.Uri
import org.http4k.server.Http4kServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.*
import java.net.URI
import java.net.http.HttpClient
import java.net.http.WebSocket
import java.nio.ByteBuffer
import java.util.concurrent.CompletionStage
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class WampControlFramesTest {
    private val port = getFreePort()
    private val pongEvents = CopyOnWriteArrayList<WsControlFrameEvent>()
    private val pingEvents = CopyOnWriteArrayList<WsControlFrameEvent>()
    private val reconnectEvents = CopyOnWriteArrayList<CSOcppId>()
    private val httpClient = HttpClient.newHttpClient()
    private lateinit var server: Http4kServer

    @Volatile
    private var failNextPong = false

    @BeforeEach
    fun startServer() {
        val wampServer = OcppWampServer.newServer(
            setOf(OCPP_1_6),
            listeners = EventsListeners(
                onWsReconnectHandler = { id, _ -> reconnectEvents.add(id) },
                onWsPongHandler = {
                    if (failNextPong) {
                        failNextPong = false
                        throw IllegalStateException("failing listener")
                    }
                    pongEvents.add(it)
                },
                onWsPingHandler = { pingEvents.add(it) }
            )
        )
        wampServer.register(object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId): Boolean = true

            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? =
                WampMessage.CallResult(msg.msgId, "{}")
        })
        server = wampServer.asServer(port).start()
    }

    @AfterEach
    fun stopServer() {
        httpClient.close()
        server.stop()
    }

    @Test
    fun `should notify a pong received on an active connection`() {
        val station = connect("CS1")

        station.ws.sendPong(ByteBuffer.wrap("pong".toByteArray())).get(5, TimeUnit.SECONDS)

        runWithTimeout {
            expectThat(pongEvents).hasSize(1).first().and {
                get { ocppId }.isEqualTo("CS1")
                get { wsConnectionId }.isNotBlank()
                get { headers.map { it.first.lowercase() } }.contains("sec-websocket-protocol")
            }
        }
        expectThat(pingEvents).isEmpty()
        station.ws.abort()
    }

    @Test
    fun `should attribute pongs to their own station`() {
        val station1 = connect("CS1")
        val station2 = connect("CS2")

        station1.ws.sendPong(ByteBuffer.allocate(0)).get(5, TimeUnit.SECONDS)
        runWithTimeout { expectThat(pongEvents).hasSize(1) }
        station2.ws.sendPong(ByteBuffer.allocate(0)).get(5, TimeUnit.SECONDS)
        runWithTimeout { expectThat(pongEvents).hasSize(2) }

        expectThat(pongEvents.map { it.ocppId }).containsExactly("CS1", "CS2")
        expectThat(pongEvents.map { it.wsConnectionId }.toSet()).hasSize(2)
        station1.ws.abort()
        station2.ws.abort()
    }

    @Test
    fun `should report the new connection after a reconnection`() {
        val previous = connect("CS1")
        previous.ws.sendPong(ByteBuffer.allocate(0)).get(5, TimeUnit.SECONDS)
        runWithTimeout { expectThat(pongEvents).hasSize(1) }
        val previousConnectionId = pongEvents.first().wsConnectionId

        val current = connect("CS1")
        runWithTimeout { expectThat(reconnectEvents).containsExactly("CS1") }
        current.ws.sendPong(ByteBuffer.allocate(0)).get(5, TimeUnit.SECONDS)

        runWithTimeout {
            expectThat(pongEvents).hasSize(2).last().and {
                get { ocppId }.isEqualTo("CS1")
                get { wsConnectionId }.isNotEqualTo(previousConnectionId)
            }
        }
        previous.ws.abort()
        current.ws.abort()
    }

    @Test
    fun `should notify a ping and still answer it with a pong`() {
        val station = connect("CS1")

        station.ws.sendPing(ByteBuffer.wrap("ping".toByteArray())).get(5, TimeUnit.SECONDS)

        runWithTimeout {
            expectThat(pingEvents).hasSize(1).first().get { ocppId }.isEqualTo("CS1")
            expectThat(station.receivedPongs.get()).isEqualTo(1)
        }
        expectThat(pongEvents).isEmpty()
        station.ws.abort()
    }

    @Test
    fun `should keep the connection open when a listener fails`() {
        val station = connect("CS1")
        failNextPong = true

        station.ws.sendPong(ByteBuffer.allocate(0)).get(5, TimeUnit.SECONDS)
        station.ws.sendPong(ByteBuffer.allocate(0)).get(5, TimeUnit.SECONDS)

        runWithTimeout { expectThat(pongEvents).hasSize(1) }
        expectThat(failNextPong).isFalse()
        station.ws.abort()
    }

    @Test
    fun `should not notify control frames for OCPP messages`() {
        val client = OcppWampClient.newClient(Uri.of("ws://localhost:$port/ws"), "CS1", OCPP_1_6, autoReconnect = false)
        client.connect()
        client.sendBlocking(WampMessage.Call("1", "Heartbeat", "{}"))

        // a pong from another station acts as a barrier: once it is reported, earlier frames would have been too
        val barrier = connect("CS2")
        barrier.ws.sendPong(ByteBuffer.allocate(0)).get(5, TimeUnit.SECONDS)
        runWithTimeout { expectThat(pongEvents).isNotEmpty() }

        expectThat(pongEvents.map { it.ocppId }).containsExactly("CS2")
        expectThat(pingEvents).isEmpty()
        client.close()
        barrier.ws.abort()
    }

    private fun connect(ocppId: CSOcppId): TestStation {
        val receivedPongs = AtomicInteger()
        val ws = httpClient.newWebSocketBuilder()
            .subprotocols(OCPP_1_6.subprotocol)
            .buildAsync(
                URI("ws://localhost:$port/ws/$ocppId"),
                object : WebSocket.Listener {
                    override fun onPong(webSocket: WebSocket, message: ByteBuffer): CompletionStage<*>? {
                        receivedPongs.incrementAndGet()
                        return super.onPong(webSocket, message)
                    }
                }
            )
            .get(5, TimeUnit.SECONDS)
        return TestStation(ws, receivedPongs)
    }

    private class TestStation(val ws: WebSocket, val receivedPongs: AtomicInteger)
}
