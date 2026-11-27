package com.izivia.ocpp.integration.test

import com.izivia.ocpp.integration.model.CSMSSettings
import com.izivia.ocpp.integration.model.ServerSetting
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.integration.model.TransportEnum.SOAP
import com.izivia.ocpp.integration.model.TransportEnum.WEBSOCKET
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.transport.OcppVersion.OCPP_1_6
import com.izivia.ocpp.transport.OcppVersion.OCPP_2_0
import org.junit.jupiter.api.Test
import strikt.api.expectCatching
import strikt.api.expectThrows
import strikt.assertions.contains
import strikt.assertions.isNotNull
import strikt.assertions.isSuccess

class CSMSSettingsTest {

    @Test
    fun `two websocket servers sharing a path on a port are refused`() {
        expectThrows<IllegalArgumentException> {
            CSMSSettings(listOf(setting(8080, "ws", WEBSOCKET), setting(8080, "/ws/", WEBSOCKET, OCPP_2_0)))
        }.get { message }.isNotNull().contains("8080").contains("ws")
    }

    @Test
    fun `websocket servers on distinct paths or ports are accepted`() {
        expectCatching {
            CSMSSettings(
                listOf(
                    setting(8080, "ws", WEBSOCKET),
                    setting(8080, "ws2", WEBSOCKET),
                    setting(8081, "ws", WEBSOCKET),
                )
            )
        }.isSuccess()
    }

    @Test
    fun `a websocket and a SOAP server may share a path on a port`() {
        expectCatching {
            CSMSSettings(listOf(setting(8080, "ocpp", WEBSOCKET), setting(8080, "ocpp", SOAP)))
        }.isSuccess()
    }

    private fun setting(port: Int, path: String, transport: TransportEnum, version: OcppVersion = OCPP_1_6) =
        ServerSetting(port = port, path = path, ocppVersion = setOf(version), transportType = transport)
}
