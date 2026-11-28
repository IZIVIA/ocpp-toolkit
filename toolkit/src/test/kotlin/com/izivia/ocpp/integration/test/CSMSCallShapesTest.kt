package com.izivia.ocpp.integration.test

import com.izivia.ocpp.integration.CSMS
import com.izivia.ocpp.integration.model.CSMSSettings
import com.izivia.ocpp.integration.model.ServerSetting
import com.izivia.ocpp.integration.model.TransportEnum.WEBSOCKET
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.transport.OcppVersion.OCPP_1_6
import com.izivia.ocpp.transport.ServerTransport
import com.izivia.ocpp.wamp.server.impl.AdmissionExecution
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.hasSize
import java.time.Duration

/**
 * The Kotlin calls of the [CSMS] and [CSMSSettings] constructors: this source fails to compile when one of them
 * becomes ambiguous between the constructor of the releases before admission and the one taking it.
 */
class CSMSCallShapesTest {

    private val transports = emptyMap<ServerTransport, Pair<Int, Set<com.izivia.ocpp.transport.OcppVersion>>>()
    private val fn: (String) -> ChargingStationConfig = { ChargingStationConfig(acceptConnection = true, soapUrl = null) }
    private val dispatched = AdmissionExecution.Dispatched(4, Duration.ofSeconds(1))
    private val servers = listOf(ServerSetting(port = 8080, ocppVersion = setOf(OCPP_1_6), transportType = WEBSOCKET))

    @Test
    fun `every CSMS call shape resolves`() {
        listOf(
            CSMS(transports, emptySet(), fn),
            CSMS(transports, emptySet(), fn, dispatched),
            CSMS(transports = transports, csmsApis = emptySet(), fn = fn),
            CSMS(transports = transports, csmsApis = emptySet(), fn = fn, admission = dispatched),
        ).let { expectThat(it).hasSize(4) }
    }

    @Test
    fun `CSMSSettings calls without the admission run it inline`() {
        listOf(
            CSMSSettings(servers),
            CSMSSettings(servers = servers),
        ).forEach { expectThat(it.admission).isEqualTo(AdmissionExecution.Inline) }
    }

    @Test
    fun `CSMSSettings calls with the admission keep it`() {
        listOf(
            CSMSSettings(servers, dispatched),
            CSMSSettings(servers = servers, admission = dispatched),
        ).forEach { expectThat(it.admission).isEqualTo(dispatched) }
    }
}
