package fr.simatix.cs.simulator.websocket.test

import fr.simatix.cs.simulator.core16.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.core16.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.core20.model.authorize.AuthorizeReq
import fr.simatix.cs.simulator.core20.model.authorize.AuthorizeResp
import fr.simatix.cs.simulator.core20.model.common.IdTokenInfoType
import fr.simatix.cs.simulator.core20.model.common.enumeration.AuthorizationStatusEnumType
import fr.simatix.cs.simulator.websocket.WebsocketClient
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.mockkStatic
import io.simatix.ev.ocpp.OcppVersion
import io.simatix.ev.ocpp.wamp.client.OcppWampClient
import io.simatix.ev.ocpp.wamp.client.impl.OkHttpOcppWampClient
import io.simatix.ev.ocpp.wamp.messages.WampMessage
import io.simatix.ev.ocpp.wamp.messages.WampMessageMeta
import io.simatix.ev.ocpp.wamp.messages.WampMessageType
import io.simatix.ev.ocpp.wamp.server.OcppWampServer
import io.simatix.ev.ocpp.wamp.server.OcppWampServerHandler
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Disabled
import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import strikt.assertions.isFailure
import java.util.*

class WebsocketTest {

    @Test
    fun `sendMessageClass success`() {

        val id = "a727d144-82bb-497a-a0c7-4ef2295910d4"
        val uuid = UUID.fromString(id)
        mockkStatic(UUID::class)
        every { UUID.randomUUID() } returns uuid

        val ocppWampClient = mockk<OkHttpOcppWampClient>()
        every { ocppWampClient.connect() } returns Unit
        every { ocppWampClient.close() } returns Unit
        every { ocppWampClient.onAction(any()) } returns Unit
        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{\"currentTime\":\"2022-02-15T00:00:00.000Z\"}",
            action = "heartbeat"
        )
        mockkObject(OcppWampClient.Companion)
        every { OcppWampClient.Companion.newClient(any(), any(), any(), any()) } returns ocppWampClient

        val websocketClient = WebsocketClient("chargePoint2", OcppVersion.OCPP_1_6, "")
        val heartbeatResponse =
            websocketClient.sendMessageClass(HeartbeatResp::class, "heartbeat", HeartbeatReq())
        expectThat(heartbeatResponse)
            .and { get { this.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
    }

    @Test
    fun `wrong msgId`() {
        val id = "00000000-0000-0000-0000-000000000000"
        val uuid = UUID.fromString(id)
        mockkStatic(UUID::class)
        every { UUID.randomUUID() } returns uuid

        val ocppWampClient = mockk<OkHttpOcppWampClient>()
        every { ocppWampClient.connect() } returns Unit
        every { ocppWampClient.close() } returns Unit
        every { ocppWampClient.onAction(any()) } returns Unit
        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{\"currentTime\":\"2022-02-15T00:00:00.000Z\"}",
            action = "heartbeat"
        )
        mockkObject(OcppWampClient.Companion)
        every { OcppWampClient.Companion.newClient(any(), any(), any(), any()) } returns ocppWampClient

        val websocketClient = WebsocketClient("chargePoint2", OcppVersion.OCPP_1_6, "")
        expectCatching { websocketClient.sendMessageClass(HeartbeatResp::class, "heartbeat", HeartbeatReq()) }
            .isFailure()
            .isA<IllegalStateException>()
    }

    @Disabled("Disabled until it has been fixed")
    @Test
    fun `receiveMessageClass success`() {

        val port = 12345

        val server = OcppWampServer.newServer(port, setOf(OcppVersion.OCPP_1_6, OcppVersion.OCPP_2_0))
        server.register(object : OcppWampServerHandler {
            override fun accept(ocppId: String): Boolean = "chargePoint2" == ocppId

            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? = null
        })
        server.start()

        try {
            val websocketClient = WebsocketClient("chargePoint2", OcppVersion.OCPP_1_6,"ws://localhost:$port/ws")


            val heartbeatFun : (HeartbeatReq) -> HeartbeatResp = { HeartbeatResp(Clock.System.now()) }
            websocketClient.receiveMessageClass(HeartbeatReq::class, "heartbeat", heartbeatFun)

            val authorizeFun : (AuthorizeReq) -> AuthorizeResp = { AuthorizeResp(IdTokenInfoType(AuthorizationStatusEnumType.Blocked)) }
            websocketClient.receiveMessageClass(AuthorizeReq::class, "authorize", authorizeFun)

            websocketClient.connect()

            server.sendBlocking("chargePoint2", WampMessage(WampMessageType.CALL,"1","authorize","{\"idToken\": {\"idToken\": \"Tag1\", \"type\": \"Central\"}}"))

            server.sendBlocking("chargePoint2", WampMessage(WampMessageType.CALL,"2","heartbeat","{}"))

            websocketClient.close()
        } finally {
            server.stop()
        }
    }

}