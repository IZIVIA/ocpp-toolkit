package fr.simatix.cs.simulator.adapter.test

import fr.simatix.cs.simulator.adapter.Ocpp20Adapter
import fr.simatix.cs.simulator.api.model.HeartbeatRequest
import fr.simatix.cs.simulator.core20.ChargePointOperations
import fr.simatix.cs.simulator.core20.impl.ChargePointOperationsImpl
import fr.simatix.cs.simulator.core20.model.HeartbeatResponse
import fr.simatix.cs.simulator.transport.Transport
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class AdapterTest {
    @Test
    fun `heartbeat request`() {
        val transport = mockk<Transport>()

        val chargePointOperations = mockk<ChargePointOperationsImpl>()
        every { chargePointOperations.heartbeat(any()) } returns HeartbeatResponse(
            currentTime = Instant.parse("2022-02-15T00:00:00.000Z")
        )

        mockkObject(ChargePointOperations.Companion)
        every { ChargePointOperations.Companion.newChargePointOperations(any()) } returns chargePointOperations

        val operations = Ocpp20Adapter(transport)
        val response = operations.heartbeat(HeartbeatRequest())
        expectThat(response)
            .and { get { this.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
    }
}