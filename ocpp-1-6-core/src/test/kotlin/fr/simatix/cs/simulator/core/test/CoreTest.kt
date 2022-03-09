package fr.simatix.cs.simulator.core.test

import fr.simatix.cs.simulator.core16.CSMSOperations
import fr.simatix.cs.simulator.core16.ChargePointOperations
import fr.simatix.cs.simulator.core16.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.core16.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.core16.model.reset.ResetReq
import fr.simatix.cs.simulator.core16.model.reset.ResetResp
import fr.simatix.cs.simulator.operation.information.OperationExecution
import fr.simatix.cs.simulator.operation.information.RequestMetadata
import fr.simatix.cs.simulator.transport.Transport
import fr.simatix.cs.simulator.transport.receiveMessage
import fr.simatix.cs.simulator.transport.sendMessage
import io.mockk.every
import io.mockk.mockk
import kotlinx.datetime.Instant
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class CoreTest {
    private lateinit var transport: Transport

    @BeforeEach
    fun init() {
        transport = mockk()
        every { transport.receiveMessage<Any,Any>(any(),any()) } returns Unit
        every { transport.receiveMessageClass<Any,Any>(any(),any(),any()) } returns Unit

    }

    @Test
    fun `heartbeat request`() {

        every { transport.sendMessage<HeartbeatReq, HeartbeatResp>(any(), any()) } returns HeartbeatResp(
            currentTime = Instant.parse("2022-02-15T00:00:00.000Z")
        )

        val csmsOperations : CSMSOperations = object : CSMSOperations{
            override fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp> {
                TODO("Not yet implemented")
            }
        }

        val operations =
            ChargePointOperations.newChargePointOperations("",transport, csmsOperations)
        val response = operations.heartbeat(RequestMetadata(""), HeartbeatReq())
        expectThat(response).and { get { this.response.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
    }
}