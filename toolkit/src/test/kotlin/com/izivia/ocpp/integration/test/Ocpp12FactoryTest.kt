package com.izivia.ocpp.integration.test

import com.izivia.ocpp.adapter12.Ocpp12Adapter
import com.izivia.ocpp.api.CSApi
import com.izivia.ocpp.api.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.api12.OcppCSCallbacks
import com.izivia.ocpp.core12.CSMSOperations
import com.izivia.ocpp.core12.ChargePointOperations
import com.izivia.ocpp.integration.ApiFactory
import com.izivia.ocpp.integration.model.CSMSSettings
import com.izivia.ocpp.integration.model.ServerSetting
import com.izivia.ocpp.integration.model.Settings
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.soap12.Ocpp12SoapParser
import com.izivia.ocpp.transport.OcppVersion
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import com.izivia.ocpp.core12.model.heartbeat.HeartbeatResp as HeartbeatResp12

class Ocpp12FactoryTest {
    @Test
    fun `creates OCPP 1-2 SOAP charge point connection`() {
        val operations = ApiFactory.ocpp12ConnectionToCSMS(
            chargePointId = "CP001",
            csmsUrl = "http://localhost:8080/ocpp",
            transportType = TransportEnum.SOAP,
            clientPath = "/cp",
            clientPort = 8081,
            ocppCSCallbacks = object : OcppCSCallbacks {}
        )

        expectThat(operations).isA<ChargePointOperations>()
    }

    @Test
    fun `creates CSMS API for OCPP 1-2 SOAP server`() {
        val chargePointOperations = ApiFactory.ocpp12ConnectionToCSMS(
            chargePointId = "CP001",
            csmsUrl = "http://localhost:8080/ocpp",
            transportType = TransportEnum.SOAP,
            clientPath = "/cp",
            clientPort = 8081,
            ocppCSCallbacks = object : OcppCSCallbacks {}
        )
        val csms = ApiFactory.csmsOcppServer(
            csmsSettings = CSMSSettings(
                listOf(
                    ServerSetting(
                        port = 0,
                        path = "/ocpp",
                        ocppVersion = setOf(OcppVersion.OCPP_1_2),
                        transportType = TransportEnum.SOAP
                    )
                )
            ),
            csmsApiCallbacks = listOf(chargePointOperations),
            fn = { ChargingStationConfig(acceptConnection = true, soapUrl = null) }
        )

        expectThat(csms.getCSApi12()).isA<CSMSOperations>()
    }

    @Test
    fun `creates generic API adapter for OCPP 1-2`() {
        val api = ApiFactory.getCSMSApi(
            settings = Settings(
                ocppVersion = OcppVersion.OCPP_1_2,
                transportType = TransportEnum.SOAP,
                clientPath = "/cp",
                clientPort = 8081
            ),
            ocppId = "CP001",
            csApi = mockk<CSApi>(relaxed = true)
        )

        expectThat(api).isA<Ocpp12Adapter>()
    }

    @Test
    fun `sends heartbeat through OCPP 1-2 SOAP generic adapter`() {
        val receivedActions = mutableListOf<String>()
        val server = soapServer(
            parser = Ocpp12SoapParser(),
            payload = HeartbeatResp12(TEST_CURRENT_TIME),
            receivedActions = receivedActions
        )

        try {
            val api = soapApi(OcppVersion.OCPP_1_2, server.port())
            try {
                api.connect()
                val response = api.heartbeat(RequestMetadata("CP001"), HeartbeatReq())

                expectThat(response.response.currentTime).isEqualTo(TEST_CURRENT_TIME)
                expectThat(receivedActions.toList()).isEqualTo(listOf("Heartbeat"))
            } finally {
                api.close()
            }
        } finally {
            server.close()
        }
    }

    @Test
    fun `rejects OCPP 1-2 over websocket with an actionable message`() {
        val error = assertThrows<IllegalArgumentException> {
            ApiFactory.getCSMSApi(
                settings = Settings(
                    ocppVersion = OcppVersion.OCPP_1_2,
                    transportType = TransportEnum.WEBSOCKET,
                    target = "ws://localhost:8080/ocpp"
                ),
                ocppId = "CP001",
                csApi = mockk<CSApi>(relaxed = true)
            )
        }

        expectThat(error.message).isEqualTo("OCPP 1.2 has no WebSocket transport")
    }

    @Test
    fun `rejects an OCPP 1-2 websocket server with an actionable message`() {
        val error = assertThrows<IllegalArgumentException> {
            ApiFactory.csmsOcppServer(
                csmsSettings = CSMSSettings(
                    listOf(
                        ServerSetting(
                            port = 0,
                            path = "/ocpp",
                            ocppVersion = setOf(OcppVersion.OCPP_1_2),
                            transportType = TransportEnum.WEBSOCKET
                        )
                    )
                ),
                csmsApiCallbacks = listOf(mockk<ChargePointOperations>(relaxed = true)),
                fn = { ChargingStationConfig(acceptConnection = true, soapUrl = null) }
            )
        }

        expectThat(error.message).isEqualTo("OCPP 1.2 has no WebSocket transport")
    }
}
