package com.izivia.ocpp.integration.test

import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
import com.izivia.ocpp.core16.ChargePointOperations
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.integration.ApiFactory
import com.izivia.ocpp.integration.CSMS
import com.izivia.ocpp.integration.model.CSMSSettings
import com.izivia.ocpp.integration.model.ServerSetting
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.soap.RequestSoapMessage
import com.izivia.ocpp.soap16.Ocpp16SoapParser
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.wamp.client.OcppWampClient
import com.izivia.ocpp.wamp.server.impl.EventsListeners
import com.izivia.ocpp.wamp.server.impl.UndertowOcppWampServer
import io.mockk.every
import io.mockk.mockk
import org.http4k.core.Uri
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.assertions.any
import strikt.assertions.contains
import strikt.assertions.isA
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo
import strikt.assertions.isFailure
import strikt.assertions.isNotNull
import strikt.assertions.isSuccess
import java.io.IOException
import java.net.ServerSocket
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.net.http.WebSocket
import java.net.http.WebSocketHandshakeException
import java.util.concurrent.CompletionException
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.TimeUnit
import kotlin.time.Clock
import com.izivia.ocpp.OcppVersion as WampOcppVersion

class RefusedStationHandshakeTest {
    private val port = ServerSocket(0).use { it.localPort }
    private val connected = CopyOnWriteArrayList<String>()

    private val websocketSetting = ServerSetting(
        port = port,
        path = "ws",
        ocppVersion = setOf(OcppVersion.OCPP_1_6),
        transportType = TransportEnum.WEBSOCKET,
        listeners = EventsListeners(onWsConnectHandler = { id, _ -> connected += id })
    )

    @Test
    fun `station with acceptConnection false fails to connect`() {
        val server = csms(listOf(websocketSetting))
        server.start()

        try {
            expectCatching { connect("REFUSED") }
                .isFailure()
                .isA<IOException>()
                .get { message }.isNotNull().contains("404")
            expectThat(connected).isEmpty()

            expectCatching { connect("ACCEPTED").close() }.isSuccess()
        } finally {
            server.stop()
        }
    }

    @Test
    fun `refusal is logged with the station id`() {
        val logger = LoggerFactory.getLogger(UndertowOcppWampServer::class.java) as Logger
        val appender = ListAppender<ILoggingEvent>().apply { start() }
        logger.addAppender(appender)
        val server = csms(listOf(websocketSetting))
        server.start()

        try {
            expectThat(handshakeStatus("REFUSED")).isEqualTo(404)

            expectThat(appender.list.map { it.formattedMessage }).any { contains("REFUSED") }
        } finally {
            server.stop()
            logger.detachAppender(appender)
            appender.stop()
        }
    }

    @Test
    fun `refusal answers 404 with SOAP on the same port and SOAP calls still route`() {
        val soapSetting = ServerSetting(
            port = port,
            path = "soap",
            ocppVersion = setOf(OcppVersion.OCPP_1_6),
            transportType = TransportEnum.SOAP
        )
        val server = csms(listOf(websocketSetting, soapSetting))
        server.start()

        try {
            expectThat(handshakeStatus("REFUSED")).isEqualTo(404)
            expectThat(connected).isEmpty()

            expectThat(postHeartbeatOverSoap("ACCEPTED").statusCode()).isEqualTo(200)
        } finally {
            server.stop()
        }
    }

    private fun csms(servers: List<ServerSetting>): CSMS {
        val callbacks = mockk<ChargePointOperations>(relaxed = true)
        every { callbacks.heartbeat(any(), any()) } answers {
            OperationExecution(mockk(relaxed = true), secondArg(), HeartbeatResp(Clock.System.now()))
        }
        return ApiFactory.csmsOcppServer(
            csmsSettings = CSMSSettings(servers),
            csmsApiCallbacks = listOf(callbacks),
            fn = { ChargingStationConfig(acceptConnection = it == "ACCEPTED", soapUrl = null) }
        )
    }

    private fun connect(ocppId: String) =
        OcppWampClient.newClient(
            Uri.of("ws://localhost:$port/ws"),
            ocppId,
            WampOcppVersion.OCPP_1_6,
            timeoutInMs = 5_000,
            autoReconnect = false
        ).also { it.connect() }

    private fun handshakeStatus(ocppId: String): Int? =
        try {
            HttpClient.newHttpClient().newWebSocketBuilder()
                .subprotocols(WampOcppVersion.OCPP_1_6.subprotocol)
                .buildAsync(URI("ws://localhost:$port/ws/$ocppId"), object : WebSocket.Listener {})
                .orTimeout(5, TimeUnit.SECONDS)
                .join()
                .abort()
            101
        } catch (e: CompletionException) {
            (e.cause as? WebSocketHandshakeException)?.response?.statusCode()
        }

    private fun postHeartbeatOverSoap(ocppId: String): HttpResponse<String> {
        val body = Ocpp16SoapParser().mapRequestToSoap(
            RequestSoapMessage(
                messageId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                chargingStationId = ocppId,
                action = "Heartbeat",
                from = "http://localhost:8082/",
                to = "http://localhost:$port/soap/",
                payload = HeartbeatReq()
            )
        )
        return HttpClient.newHttpClient().send(
            HttpRequest.newBuilder(URI("http://localhost:$port/soap/Heartbeat/$ocppId"))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build(),
            HttpResponse.BodyHandlers.ofString()
        )
    }
}
