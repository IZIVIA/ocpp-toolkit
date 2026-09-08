package com.izivia.ocpp.integration.test

import com.izivia.ocpp.integration.ApiFactory.Companion.csmsOcppServer
import com.izivia.ocpp.integration.model.CSMSSettings
import com.izivia.ocpp.integration.model.ServerSetting
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.wamp.server.impl.OcppWampServerSettings
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import strikt.api.expectThat
import strikt.assertions.isTrue
import java.util.concurrent.Executor
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
}
