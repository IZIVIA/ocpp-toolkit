package com.izivia.ocpp.integration.test

import com.izivia.ocpp.integration.ApiFactory.Companion.csmsOcppServer
import com.izivia.ocpp.integration.model.CSMSSettings
import com.izivia.ocpp.integration.model.ServerSetting
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.wamp.client.OcppWampClient
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.server.impl.OcppWampServerSettings
import org.http4k.core.Uri
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isTrue
import java.net.ServerSocket
import java.time.ZoneId
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.Executor
import com.izivia.ocpp.OcppVersion as OcppVersionWamp
import com.izivia.ocpp.core16.ChargePointOperations as ChargePointOperations16

class ServerSettingSettingsTest {

    /** Settings that record whether the wamp server ever asked them for a calls executor. */
    private class RecordingSettings : OcppWampServerSettings() {
        var callsExecutorBuilt = false
            private set

        override fun buildCallsExecutor(): Executor {
            callsExecutorBuilt = true
            return Executor { it.run() }
        }
    }

    @Test
    fun `settings given to ServerSetting reach the websocket server`() {
        val settings = RecordingSettings()

        // The server is only built here, never started, so the port is never bound.
        csmsOcppServer(
            csmsSettings = CSMSSettings(
                listOf(
                    ServerSetting(
                        port = 8080,
                        ocppVersion = setOf(OcppVersion.OCPP_1_6),
                        transportType = TransportEnum.WEBSOCKET,
                        wampSettings = settings
                    )
                )
            ),
            csmsApiCallbacks = listOf(mock<ChargePointOperations16>()),
            fn = { ChargingStationConfig(acceptConnection = true, soapUrl = null) }
        )

        // The wamp server builds the calls executor eagerly, when the transport is created.
        expectThat(settings.callsExecutorBuilt).isTrue()
    }

    @Test
    fun `timestamp zone fallback given to ServerSetting reaches the websocket server`() {
        val port = ServerSocket(0).use { it.localPort }
        val resolvedStations = CopyOnWriteArrayList<String>()
        val csms = csmsOcppServer(
            csmsSettings = CSMSSettings(
                listOf(
                    ServerSetting(
                        port = port,
                        path = "ws",
                        ocppVersion = setOf(OcppVersion.OCPP_1_6),
                        transportType = TransportEnum.WEBSOCKET,
                        timestampZoneFallback = { resolvedStations.add(it); ZoneId.of("Europe/Paris") }
                    )
                )
            ),
            csmsApiCallbacks = listOf(mock<ChargePointOperations16>()),
            fn = { ChargingStationConfig(acceptConnection = true, soapUrl = null) }
        )
        csms.start()
        try {
            val client = OcppWampClient.newClient(
                Uri.of("ws://localhost:$port/ws"),
                "CS1",
                OcppVersionWamp.OCPP_1_6,
                autoReconnect = false
            )
            client.connect()
            try {
                // Only the resolution matters here: the mocked callbacks answer whatever they answer.
                client.sendBlocking(
                    WampMessage.Call(
                        "1",
                        "StartTransaction",
                        """{"connectorId":1,"idTag":"Tag1","meterStart":0,"timestamp":"2024-03-15T12:00:00"}"""
                    )
                )
            } finally {
                client.close()
            }
        } finally {
            csms.stop()
        }

        expectThat(resolvedStations.toList()).isEqualTo(listOf("CS1"))
    }
}
