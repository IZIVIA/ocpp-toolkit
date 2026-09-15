package com.izivia.ocpp.integration.test

import com.izivia.ocpp.integration.model.Settings
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.transport.OcppVersion
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class SettingsTest {

    @Test
    fun `builds the default target from the domain, port and path`() {
        expectThat(Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET).target)
            .isEqualTo("ws://localhost:8080/")
        expectThat(Settings(OcppVersion.OCPP_1_6, TransportEnum.SOAP, domain = "192.168.0.3", port = "8081", path = "ocpp").target)
            .isEqualTo("http://192.168.0.3:8081/ocpp")
    }

    @Test
    fun `brackets an IPv6 domain in the default target`() {
        expectThat(Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, domain = "2001:db8::1").target)
            .isEqualTo("ws://[2001:db8::1]:8080/")
        expectThat(Settings(OcppVersion.OCPP_1_6, TransportEnum.SOAP, domain = "[2001:db8::1]", path = "ocpp").target)
            .isEqualTo("http://[2001:db8::1]:8080/ocpp")
    }
}
