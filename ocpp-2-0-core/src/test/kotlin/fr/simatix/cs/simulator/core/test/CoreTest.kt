package fr.simatix.cs.simulator.core.test

import fr.simatix.cs.simulator.api.model.RequestMetadata
import fr.simatix.cs.simulator.core20.ChargePointOperations
import fr.simatix.cs.simulator.core20.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.core20.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.transport.Transport
import fr.simatix.cs.simulator.transport.sendMessage
import io.mockk.every
import io.mockk.mockk
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class CoreTest {
    @Test
    fun `heartbeat request`() {
        val transport = mockk<Transport>()
        every { transport.sendMessage<HeartbeatReq, HeartbeatResp>(any(), any()) } returns HeartbeatResp(
            currentTime = Instant.parse("2022-02-15T00:00:00.000Z")
        )

        val operations =
            ChargePointOperations.newChargePointOperations(transport)
        val response = operations.heartbeat(RequestMetadata(""), HeartbeatReq())
        expectThat(response)
            .and { get { this.response.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
    }
}