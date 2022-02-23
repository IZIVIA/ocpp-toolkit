package fr.simatix.cs.simulator.adapter.test

import fr.simatix.cs.simulator.adapter20.Ocpp20Adapter
import fr.simatix.cs.simulator.api.model.ExecutionMetadata
import fr.simatix.cs.simulator.api.model.HeartbeatReq
import fr.simatix.cs.simulator.api.model.RequestMetadata
import fr.simatix.cs.simulator.core20.ChargePointOperations
import fr.simatix.cs.simulator.core20.impl.RealChargePointOperations
import fr.simatix.cs.simulator.core20.model.CoreExecution
import fr.simatix.cs.simulator.core20.model.HeartbeatResp
import fr.simatix.cs.simulator.transport.Transport
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class AdapterTest {
    @Test
    fun `heartbeat request`() {
        val transport = mockk<Transport>()
        val requestMetadata = RequestMetadata("")

        val chargePointOperations = mockk<RealChargePointOperations>()
        every { chargePointOperations.heartbeat(any(),any()) } returns CoreExecution(
            ExecutionMetadata(requestMetadata, Clock.System.now(), Clock.System.now()),
            HeartbeatResp(
                currentTime = Instant.parse("2022-02-15T00:00:00.000Z")
            )
        )

        mockkObject(ChargePointOperations.Companion)
        every { ChargePointOperations.Companion.newChargePointOperations(any()) } returns chargePointOperations

        val operations = Ocpp20Adapter(transport)
        val request = HeartbeatReq()
        val response = operations.heartbeat(requestMetadata,request)
        expectThat(response)
            .and { get { this.response.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
    }
}