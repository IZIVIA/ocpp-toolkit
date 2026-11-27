package com.izivia.ocpp.websocket.test

import com.izivia.ocpp.core16.model.common.IdTagInfo
import com.izivia.ocpp.core16.model.common.enumeration.AuthorizationStatus
import com.izivia.ocpp.core16.model.getcompositeschedule.GetCompositeScheduleReq
import com.izivia.ocpp.core16.model.getcompositeschedule.GetCompositeScheduleResp
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionResp
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.wamp.client.OcppWampClient
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageType
import com.izivia.ocpp.wamp.server.impl.NoConnectionException
import com.izivia.ocpp.websocket.WebsocketServer
import com.izivia.ocpp.websocket.asServer
import org.http4k.core.Uri
import org.junit.jupiter.api.Test
import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.isEqualTo
import strikt.assertions.isFailure
import strikt.assertions.isNull
import java.net.ServerSocket
import java.time.ZoneId
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.time.Instant
import com.izivia.ocpp.OcppVersion as OcppVersionWamp

class WebsocketServerTimestampZoneTest {

    @Test
    fun `inbound call reads a timestamp without timezone as UTC by default`() {
        val received = CopyOnWriteArrayList<Instant>()
        val transport = WebsocketServer(setOf(OcppVersion.OCPP_1_6), "ws")
        transport.receiveStartTransaction(received)

        withStation(transport) { client ->
            val response = client.sendBlocking(WampMessage.Call("1", "StartTransaction", START_TRANSACTION))

            expectThat(response.msgType).isEqualTo(WampMessageType.CALL_RESULT)
            expectThat(received.toList()).isEqualTo(listOf(Instant.parse("2024-03-15T12:00:00Z")))
        }
    }

    @Test
    fun `inbound call reads a timestamp without timezone in the zone resolved for the charging station`() {
        val received = CopyOnWriteArrayList<Instant>()
        val stations = CopyOnWriteArrayList<String>()
        val transport = WebsocketServer(
            ocppVersions = setOf(OcppVersion.OCPP_1_6),
            path = "ws",
            timestampZoneFallback = { stations.add(it); PARIS }
        )
        transport.receiveStartTransaction(received)

        withStation(transport) { client ->
            val response = client.sendBlocking(WampMessage.Call("1", "StartTransaction", START_TRANSACTION))

            expectThat(response.msgType).isEqualTo(WampMessageType.CALL_RESULT)
            expectThat(received.toList()).isEqualTo(listOf(Instant.parse("2024-03-15T11:00:00Z")))
            expectThat(stations.toList()).isEqualTo(listOf(STATION))
        }
    }

    @Test
    fun `inbound call with a timestamp without timezone gets a CallError when the zone is refused`() {
        val received = CopyOnWriteArrayList<Instant>()
        val transport = WebsocketServer(
            ocppVersions = setOf(OcppVersion.OCPP_1_6),
            path = "ws",
            timestampZoneFallback = { null }
        )
        transport.receiveStartTransaction(received)

        withStation(transport) { client ->
            val response = client.sendBlocking(WampMessage.Call("1", "StartTransaction", START_TRANSACTION))

            expectThat(response.msgType).isEqualTo(WampMessageType.CALL_ERROR)
            expectThat(received.toList()).isEqualTo(emptyList())
        }
    }

    @Test
    fun `inbound call with a value that is no timestamp gets a CallError without resolving the zone`() {
        val received = CopyOnWriteArrayList<Instant>()
        val transport = WebsocketServer(
            ocppVersions = setOf(OcppVersion.OCPP_1_6),
            path = "ws",
            timestampZoneFallback = { error("zone resolved for $it") }
        )
        transport.receiveStartTransaction(received)

        withStation(transport) { client ->
            val response = client.sendBlocking(
                WampMessage.Call("1", "StartTransaction", START_TRANSACTION.replace("2024-03-15T12:00:00", "foo"))
            )

            expectThat(response.msgType).isEqualTo(WampMessageType.CALL_ERROR)
            expectThat(response.payload).contains("must use Z")
            expectThat(received.toList()).isEqualTo(emptyList())
        }
    }

    @Test
    fun `result of a CSMS call reads a timestamp without timezone in the zone resolved for the charging station`() {
        val transport = WebsocketServer(
            ocppVersions = setOf(OcppVersion.OCPP_1_6),
            path = "ws",
            timestampZoneFallback = { if (it == STATION) PARIS else null }
        )
        transport.receiveStartTransaction(CopyOnWriteArrayList())

        withStation(transport, answer = COMPOSITE_SCHEDULE) {
            val response = awaitConnected { transport.getCompositeSchedule() }

            expectThat(response.scheduleStart).isEqualTo(Instant.parse("2024-03-15T11:00:00Z"))
        }
    }

    @Test
    fun `result of a CSMS call with a timestamp without timezone fails the call when the zone is refused`() {
        val transport = WebsocketServer(
            ocppVersions = setOf(OcppVersion.OCPP_1_6),
            path = "ws",
            timestampZoneFallback = { null }
        )
        transport.receiveStartTransaction(CopyOnWriteArrayList())

        withStation(transport, answer = COMPOSITE_SCHEDULE) {
            expectCatching { awaitConnected { transport.getCompositeSchedule() } }
                .isFailure()
                .get { cause?.message ?: message }
                .isEqualTo("Timestamp without timezone refused for charging station $STATION: 2024-03-15T12:00:00")
        }
    }

    @Test
    fun `result of a CSMS call with a timezone does not resolve the zone`() {
        val transport = WebsocketServer(
            ocppVersions = setOf(OcppVersion.OCPP_1_6),
            path = "ws",
            timestampZoneFallback = { error("zone resolved for $it") }
        )
        transport.receiveStartTransaction(CopyOnWriteArrayList())

        withStation(transport, answer = """{"status":"Accepted","scheduleStart":"2024-03-15T12:00:00+02:00"}""") {
            val response = awaitConnected { transport.getCompositeSchedule() }

            expectThat(response.scheduleStart).isEqualTo(Instant.parse("2024-03-15T10:00:00Z"))
            expectThat(response.chargingSchedule).isNull()
        }
    }

    private fun WebsocketServer.receiveStartTransaction(received: MutableList<Instant>) =
        receiveMessageClass(
            clazz = StartTransactionReq::class,
            action = "StartTransaction",
            ocppVersion = OcppVersion.OCPP_1_6,
            onAction = { _, req: StartTransactionReq ->
                received.add(req.timestamp)
                StartTransactionResp(IdTagInfo(AuthorizationStatus.Accepted), 1)
            },
            accept = { ChargingStationConfig(acceptConnection = true, soapUrl = null) }
        )

    private fun WebsocketServer.getCompositeSchedule(): GetCompositeScheduleResp =
        sendMessageClass(GetCompositeScheduleResp::class, STATION, "GetCompositeSchedule", GetCompositeScheduleReq(1, 60))

    /** Connects a raw station, which sends and answers hand-written payloads, unlike `WebsocketClient`. */
    private fun withStation(transport: WebsocketServer, answer: String = "{}", block: (OcppWampClient) -> Unit) {
        val port = ServerSocket(0).use { it.localPort }
        val server = transport.asServer(port)
        server.start()
        try {
            val client = OcppWampClient.newClient(
                Uri.of("ws://localhost:$port/ws"),
                STATION,
                OcppVersionWamp.OCPP_1_6,
                autoReconnect = false
            )
            client.onAction { _, msg -> WampMessage.CallResult(msg.msgId, answer) }
            client.connect()
            try {
                block(client)
            } finally {
                client.close()
            }
        } finally {
            server.stop()
        }
    }

    /** Retries [action] while the station connection is not yet registered server-side. */
    private fun <T> awaitConnected(timeoutMs: Long = 5_000, action: () -> T): T {
        val deadline = System.currentTimeMillis() + timeoutMs
        while (true) {
            try {
                return action()
            } catch (e: NoConnectionException) {
                if (System.currentTimeMillis() >= deadline) throw e
                Thread.sleep(50)
            }
        }
    }

    private companion object {
        const val STATION = "CS1"
        val PARIS: ZoneId = ZoneId.of("Europe/Paris")
        const val START_TRANSACTION =
            """{"connectorId":1,"idTag":"Tag1","meterStart":0,"timestamp":"2024-03-15T12:00:00"}"""
        const val COMPOSITE_SCHEDULE =
            """{"status":"Accepted","connectorId":1,"scheduleStart":"2024-03-15T12:00:00"}"""
    }
}
