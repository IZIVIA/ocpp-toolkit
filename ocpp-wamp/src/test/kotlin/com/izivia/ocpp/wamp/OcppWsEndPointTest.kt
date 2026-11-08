package com.izivia.ocpp.wamp

import com.izivia.ocpp.wamp.server.impl.OcppWsEndpoint
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNull

class OcppWsEndPointTest {

    val ocppWsEndpoint = OcppWsEndpoint("ws")

    @Test
    fun `should Parse with simple slash`() {
        val chargingStationOcppId =
            ocppWsEndpoint.extractChargingStationOcppId("""/ws/BOUY_0736""")
                ?.takeUnless { it.isEmpty() }
                ?: throw IllegalArgumentException("malformed request - no chargingStationOcppId ")

        expectThat(chargingStationOcppId).isEqualTo("BOUY_0736")
    }

    @Test
    fun `should Parse with double slash`() {
        val chargingStationOcppId =
            ocppWsEndpoint.extractChargingStationOcppId("""/ws//BOUY_0736""")
                ?.takeUnless { it.isEmpty() }
                ?: throw IllegalArgumentException("malformed request - no chargingStationOcppId ")

        expectThat(chargingStationOcppId).isEqualTo("BOUY_0736")
    }

    @Test
    fun `should Parse with a trailing slash`() {
        expectThat(ocppWsEndpoint.extractChargingStationOcppId("/ws/BOUY_0736/")).isEqualTo("BOUY_0736")
    }

    @ParameterizedTest
    @ValueSource(strings = ["/other/OK", "/ws", "/ws/", "/"])
    fun `should not find an id in a url outside the endpoint`(uri: String) {
        expectThat(ocppWsEndpoint.extractChargingStationOcppId(uri)).isNull()
    }

    @ParameterizedTest
    @ValueSource(strings = ["/ws/OK/extra", "/xws/OK", "/ocpp/ws/OK"])
    fun `should not find an id in a url that only contains the endpoint`(uri: String) {
        expectThat(ocppWsEndpoint.extractChargingStationOcppId(uri)).isNull()
    }

    @Test
    fun `should not find an id that cannot be decoded`() {
        expectThat(ocppWsEndpoint.extractChargingStationOcppId("/ws/%ZZ")).isNull()
    }
}
