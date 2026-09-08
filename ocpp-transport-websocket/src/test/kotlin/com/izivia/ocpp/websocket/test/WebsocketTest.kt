package com.izivia.ocpp.websocket.test

import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core20.model.authorize.AuthorizeReq
import com.izivia.ocpp.core20.model.authorize.AuthorizeResp
import com.izivia.ocpp.core20.model.common.IdTokenInfoType
import com.izivia.ocpp.core20.model.common.enumeration.AuthorizationStatusEnumType
import com.izivia.ocpp.wamp.client.OcppWampClient
import com.izivia.ocpp.wamp.client.impl.OkHttpOcppWampClient
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.server.OcppWampServer
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import com.izivia.ocpp.wamp.server.asServer
import com.izivia.ocpp.websocket.WebsocketClient
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import kotlin.time.Clock
import kotlin.time.Instant
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import strikt.assertions.isFailure
import java.net.ServerSocket

class WebsocketTest {

    @AfterEach
    fun destroy() {
        unmockkAll()
    }

    fun getFreePort(): Int =
        ServerSocket(0).use { it.localPort }

    /**
     * Retries [action] until the charging station connection is registered server-side
     * (sendBlocking throws NoConnectionException, an IllegalStateException, while absent).
     */
    private fun <T> awaitConnected(timeoutMs: Long = 5_000, action: () -> T): T {
        val deadline = System.currentTimeMillis() + timeoutMs
        while (true) {
            try {
                return action()
            } catch (e: IllegalStateException) {
                if (System.currentTimeMillis() >= deadline) throw e
                Thread.sleep(50)
            }
        }
    }

    @Test
    fun `sendMessageClass success`() {
        val id = "a727d144-82bb-497a-a0c7-4ef2295910d4"

        val ocppWampClient = mockk<OkHttpOcppWampClient>()
        every { ocppWampClient.connect() } returns Unit
        every { ocppWampClient.close() } returns Unit
        every { ocppWampClient.onAction(any()) } returns Unit
        every { ocppWampClient.sendBlocking(any()) } returns WampMessage.CallResult(
            msgId = id,
            payload = "{\"currentTime\":\"2022-02-15T00:00:00.000Z\"}"
        )
        mockkObject(OcppWampClient.Companion)
        every { OcppWampClient.Companion.newClient(any(), any(), any(), any()) } returns ocppWampClient

        val websocketClient = WebsocketClient("chargePoint2", OcppVersion.OCPP_1_6, "", newMessageId = { id })
        val heartbeatResponse =
            websocketClient.sendMessageClass(HeartbeatResp::class, "heartbeat", HeartbeatReq())
        expectThat(heartbeatResponse)
            .and { get { this.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
    }

    @Test
    fun `wrong msgId`() {
        val id = "00000000-0000-0000-0000-000000000000"

        val ocppWampClient = mockk<OkHttpOcppWampClient>()
        every { ocppWampClient.connect() } returns Unit
        every { ocppWampClient.close() } returns Unit
        every { ocppWampClient.onAction(any()) } returns Unit
        every { ocppWampClient.sendBlocking(any()) } returns WampMessage.CallResult(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            payload = "{\"currentTime\":\"2022-02-15T00:00:00.000Z\"}"
        )
        mockkObject(OcppWampClient.Companion)
        every { OcppWampClient.Companion.newClient(any(), any(), any(), any()) } returns ocppWampClient

        val websocketClient = WebsocketClient("chargePoint2", OcppVersion.OCPP_1_6, "", newMessageId = { id })
        expectCatching { websocketClient.sendMessageClass(HeartbeatResp::class, "heartbeat", HeartbeatReq()) }
            .isFailure()
            .isA<IllegalStateException>()
    }

    @Test
    fun `receiveMessageClass success`() {
        val port = getFreePort()

        val transport = OcppWampServer.newServer(setOf(OcppVersion.OCPP_1_6, OcppVersion.OCPP_2_0))
        val server = transport.asServer(port)
        transport.register(object : OcppWampServerHandler {
            override fun accept(ocppId: String): Boolean = "chargePoint2" == ocppId

            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? = null
        })
        server.start()

        try {
            val websocketClient = WebsocketClient("chargePoint2", OcppVersion.OCPP_1_6, "ws://localhost:$port/ws")

            val heartbeatFun: (HeartbeatReq) -> HeartbeatResp = { HeartbeatResp(Clock.System.now()) }
            websocketClient.receiveMessageClass(HeartbeatReq::class, "heartbeat", heartbeatFun)

            val authorizeFun: (AuthorizeReq) -> AuthorizeResp =
                { AuthorizeResp(IdTokenInfoType(AuthorizationStatusEnumType.Blocked)) }
            websocketClient.receiveMessageClass(AuthorizeReq::class, "authorize", authorizeFun)

            websocketClient.connect()

            // Wait until the WebSocket connection is registered server-side before pushing
            // a server-initiated CALL. The connection is established asynchronously, so a
            // fixed sleep is racy under load (e.g. GH Actions); retry until connected.
            awaitConnected {
                transport.sendBlocking(
                    "chargePoint2",
                    WampMessage.Call("1", "authorize", "{\"idToken\": {\"idToken\": \"Tag1\", \"type\": \"Central\"}}")
                )
            }

            transport.sendBlocking("chargePoint2", WampMessage.Call("2", "heartbeat", "{}"))

            websocketClient.close()
        } finally {
            server.stop()
        }
    }
}
