package com.izivia.ocpp.integration.test

import com.izivia.ocpp.api12.OcppCSCallbacks
import com.izivia.ocpp.core12.CSMSOperations
import com.izivia.ocpp.core12.ChargePointOperations
import com.izivia.ocpp.integration.ApiFactory
import com.izivia.ocpp.integration.model.CSMSSettings
import com.izivia.ocpp.integration.model.ServerSetting
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.transport.OcppVersion
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA

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
}
