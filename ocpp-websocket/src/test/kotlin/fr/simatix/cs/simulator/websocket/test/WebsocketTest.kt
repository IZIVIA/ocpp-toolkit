package fr.simatix.cs.simulator.websocket.test

import fr.simatix.cs.simulator.core16.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.core16.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.websocket.WebsocketClient
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.mockkStatic
import io.simatix.ev.ocpp.OcppVersion
import io.simatix.ev.ocpp.wamp.client.OcppWampClient
import io.simatix.ev.ocpp.wamp.client.impl.OkHttpOcppWampClient
import io.simatix.ev.ocpp.wamp.messages.WampMessage
import io.simatix.ev.ocpp.wamp.messages.WampMessageType
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Test
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
        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{\"currentTime\":\"2022-02-15T00:00:00.000Z\"}",
            action = "heartbeat"
        )
        mockkObject(OcppWampClient.Companion)
        every { OcppWampClient.Companion.newClient(any(), any(), any(), any()) } returns ocppWampClient

        val websocketClient = WebsocketClient("chargePoint2", OcppVersion.OCPP_1_6,"")
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
        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{\"currentTime\":\"2022-02-15T00:00:00.000Z\"}",
            action = "heartbeat"
        )
        mockkObject(OcppWampClient.Companion)
        every { OcppWampClient.Companion.newClient(any(), any(), any(), any()) } returns ocppWampClient

        val websocketClient = WebsocketClient("chargePoint2", OcppVersion.OCPP_1_6,"")
        expectCatching { websocketClient.sendMessageClass(HeartbeatResp::class, "heartbeat", HeartbeatReq()) }
            .isFailure()
            .isA<IllegalStateException>()
    }

}