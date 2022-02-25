package fr.simatix.cs.simulator

import fr.simatix.cs.simulator.adapter16.Ocpp16Adapter
import fr.simatix.cs.simulator.api.model.*
import fr.simatix.cs.simulator.api.model.common.*
import fr.simatix.cs.simulator.api.model.common.enumeration.*
import fr.simatix.cs.simulator.core16.ChargePointOperations
import fr.simatix.cs.simulator.core16.impl.RealChargePointOperations
import fr.simatix.cs.simulator.core16.model.*
import fr.simatix.cs.simulator.core16.model.authorize.AuthorizeResp
import fr.simatix.cs.simulator.core16.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.core16.model.metervalues.MeterValuesResp
import fr.simatix.cs.simulator.core16.model.common.IdTagInfo
import fr.simatix.cs.simulator.core16.model.common.enumeration.AuthorizationStatus
import fr.simatix.cs.simulator.transport.Transport
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatReq as HeartbeatReqGen
import fr.simatix.cs.simulator.api.model.authorize.AuthorizeReq as AuthorizeReqGen
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesReq as MeterValuesReqGen


class AdapterTest {
    @Test
    fun `heartbeat request`() {
        val transport = mockk<Transport>()
        val requestMetadata = RequestMetadata("")

        val chargePointOperations = mockk<RealChargePointOperations>()
        every { chargePointOperations.heartbeat(any(), any()) } returns CoreExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            HeartbeatResp(
                currentTime = Instant.parse("2022-02-15T00:00:00.000Z")
            )
        )

        mockkObject(ChargePointOperations.Companion)
        every { ChargePointOperations.Companion.newChargePointOperations(any()) } returns chargePointOperations

        val operations = Ocpp16Adapter(transport)
        val request = HeartbeatReqGen()
        val response = operations.heartbeat(requestMetadata, request)
        expectThat(response)
            .and { get { this.response.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
    }
}