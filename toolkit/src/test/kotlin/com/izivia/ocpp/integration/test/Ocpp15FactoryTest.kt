package com.izivia.ocpp.integration.test

import com.izivia.ocpp.adapter15.Ocpp15Adapter
import com.izivia.ocpp.api.CSApi
import com.izivia.ocpp.api.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.integration.ApiFactory
import com.izivia.ocpp.integration.model.Settings
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.soap15.Ocpp15SoapParser
import com.izivia.ocpp.transport.OcppVersion
import io.mockk.mockk
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import com.izivia.ocpp.core15.model.heartbeat.HeartbeatResp as HeartbeatResp15

class Ocpp15FactoryTest {
    @Test
    fun `creates generic API adapter for OCPP 1-5`() {
        val api = ApiFactory.getCSMSApi(
            settings = Settings(
                ocppVersion = OcppVersion.OCPP_1_5,
                transportType = TransportEnum.SOAP,
                clientPath = "/cp",
                clientPort = 8081
            ),
            ocppId = "CP001",
            csApi = mockk<CSApi>(relaxed = true)
        )

        expectThat(api).isA<Ocpp15Adapter>()
    }

    @Test
    fun `sends heartbeat through OCPP 1-5 SOAP generic adapter`() {
        val receivedActions = mutableListOf<String>()
        val server = soapServer(
            parser = Ocpp15SoapParser(),
            payload = HeartbeatResp15(TEST_CURRENT_TIME),
            receivedActions = receivedActions
        )

        try {
            val api = soapApi(OcppVersion.OCPP_1_5, server.port())
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
}
