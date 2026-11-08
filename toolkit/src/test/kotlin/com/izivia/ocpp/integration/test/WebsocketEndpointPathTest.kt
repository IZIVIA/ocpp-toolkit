package com.izivia.ocpp.integration.test

import com.izivia.ocpp.core16.ChargePointOperations
import com.izivia.ocpp.integration.ApiFactory
import com.izivia.ocpp.integration.CSMS
import com.izivia.ocpp.integration.model.CSMSSettings
import com.izivia.ocpp.integration.model.ServerSetting
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.transport.OcppVersion
import io.mockk.mockk
import org.junit.jupiter.api.Test
import strikt.api.expect
import strikt.assertions.isEqualTo
import strikt.assertions.isIn
import strikt.assertions.isNotNull
import java.net.ServerSocket
import java.net.URI
import java.net.http.HttpClient
import java.net.http.WebSocket
import java.net.http.WebSocketHandshakeException
import java.util.concurrent.CompletionException
import java.util.concurrent.TimeUnit
import com.izivia.ocpp.OcppVersion as WampOcppVersion

class WebsocketEndpointPathTest {
    private val port = ServerSocket(0).use { it.localPort }
    private val clientErrors = 400..499
    private val twoPathsOnOnePort = listOf(
        websocketSetting("ws16", OcppVersion.OCPP_1_6),
        websocketSetting("ws20", OcppVersion.OCPP_2_0)
    )

    @Test
    fun `station connects on each websocket path of a port`() {
        val server = csms(twoPathsOnOnePort)
        server.start()

        try {
            expect {
                that(handshakeStatus("/ws16/OK", WampOcppVersion.OCPP_1_6)).isEqualTo(101)
                that(handshakeStatus("/ws20/OK", WampOcppVersion.OCPP_2_0)).isEqualTo(101)
            }
        } finally {
            server.stop()
        }
    }

    @Test
    fun `url outside every websocket path of a port is refused as a client error`() {
        val server = csms(twoPathsOnOnePort)
        server.start()

        try {
            expect {
                that(handshakeStatus("/other/OK", WampOcppVersion.OCPP_1_6)).isNotNull().isIn(clientErrors)
                that(handshakeStatus("/ws16/OK/extra", WampOcppVersion.OCPP_1_6)).isNotNull().isIn(clientErrors)
            }
        } finally {
            server.stop()
        }
    }

    @Test
    fun `url outside the websocket path is refused as a client error`() {
        val server = csms(listOf(websocketSetting("ws", OcppVersion.OCPP_1_6)))
        server.start()

        try {
            expect {
                listOf("/other/OK", "/ws", "/ws/", "/", "/ws/OK/extra", "/ocpp/ws/OK").forEach { path ->
                    that(handshakeStatus(path, WampOcppVersion.OCPP_1_6))
                        .describedAs { "status of $path: $this" }
                        .isNotNull()
                        .isIn(clientErrors)
                }
            }
        } finally {
            server.stop()
        }
    }

    private fun websocketSetting(path: String, version: OcppVersion) = ServerSetting(
        port = port,
        path = path,
        ocppVersion = setOf(version),
        transportType = TransportEnum.WEBSOCKET
    )

    private fun csms(servers: List<ServerSetting>): CSMS =
        ApiFactory.csmsOcppServer(
            csmsSettings = CSMSSettings(servers),
            csmsApiCallbacks = listOf(mockk<ChargePointOperations>(relaxed = true)),
            fn = { ChargingStationConfig(acceptConnection = it == "OK", soapUrl = null) }
        )

    private fun handshakeStatus(path: String, version: WampOcppVersion): Int? =
        try {
            HttpClient.newHttpClient().newWebSocketBuilder()
                .subprotocols(version.subprotocol)
                .buildAsync(URI("ws://localhost:$port$path"), object : WebSocket.Listener {})
                .orTimeout(5, TimeUnit.SECONDS)
                .join()
                .abort()
            101
        } catch (e: CompletionException) {
            (e.cause as? WebSocketHandshakeException)?.response?.statusCode()
        }
}
