package com.izivia.ocpp.integration.test

import com.izivia.ocpp.api.CSApi
import com.izivia.ocpp.api16.OcppCSCallbacks
import com.izivia.ocpp.integration.ApiFactory
import com.izivia.ocpp.integration.model.Settings
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.transport.OcppVersion
import io.mockk.mockk
import org.junit.jupiter.api.Test
import strikt.api.expectThrows
import strikt.assertions.contains
import strikt.assertions.isNotNull
import strikt.assertions.message

class SoapClientSettingsRequirementTest {
    @Test
    fun `rejects a SOAP charge point connection without soapClient settings`() {
        expectThrows<IllegalArgumentException> {
            ApiFactory.ocpp16ConnectionToCSMS(
                chargePointId = "CP001",
                csmsUrl = "http://localhost:8080/ocpp",
                transportType = TransportEnum.SOAP,
                ocppCSCallbacks = object : OcppCSCallbacks {}
            )
        }.message.isNotNull().contains("soapClient")
    }

    @Test
    fun `rejects SOAP generic API settings without soapClient settings`() {
        expectThrows<IllegalArgumentException> {
            ApiFactory.getCSMSApi(
                settings = Settings(ocppVersion = OcppVersion.OCPP_1_6, transportType = TransportEnum.SOAP),
                ocppId = "CP001",
                csApi = mockk<CSApi>(relaxed = true)
            )
        }.message.isNotNull().contains("soapClient")
    }
}
