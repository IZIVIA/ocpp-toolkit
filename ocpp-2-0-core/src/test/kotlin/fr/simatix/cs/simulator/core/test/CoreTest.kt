package fr.simatix.cs.simulator.core.test

import fr.simatix.cs.simulator.core20.CSMSOperations
import fr.simatix.cs.simulator.core20.ChargePointOperations
import fr.simatix.cs.simulator.core20.model.changeavailability.ChangeAvailabilityReq
import fr.simatix.cs.simulator.core20.model.changeavailability.ChangeAvailabilityResp
import fr.simatix.cs.simulator.core20.model.clearcache.ClearCacheReq
import fr.simatix.cs.simulator.core20.model.clearcache.ClearCacheResp
import fr.simatix.cs.simulator.core20.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.core20.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.core20.model.remotestart.RequestStartTransactionReq
import fr.simatix.cs.simulator.core20.model.remotestart.RequestStartTransactionResp
import fr.simatix.cs.simulator.core20.model.remotestop.RequestStopTransactionReq
import fr.simatix.cs.simulator.core20.model.remotestop.RequestStopTransactionResp
import fr.simatix.cs.simulator.core20.model.reset.ResetReq
import fr.simatix.cs.simulator.core20.model.reset.ResetResp
import fr.simatix.cs.simulator.core20.model.setvariables.SetVariablesReq
import fr.simatix.cs.simulator.core20.model.setvariables.SetVariablesResp
import fr.simatix.cs.simulator.core20.model.unlockconnector.UnlockConnectorReq
import fr.simatix.cs.simulator.core20.model.unlockconnector.UnlockConnectorResp
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
    fun init(){
        transport = mockk()
        every {transport.receiveMessage<Any,Any>(any(),any())} returns Unit
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

            override fun changeAvailability(
                meta: RequestMetadata,
                req: ChangeAvailabilityReq
            ): OperationExecution<ChangeAvailabilityReq, ChangeAvailabilityResp> {
                TODO("Not yet implemented")
            }

            override fun clearCache(
                meta: RequestMetadata,
                req: ClearCacheReq
            ): OperationExecution<ClearCacheReq, ClearCacheResp> {
                TODO("Not yet implemented")
            }

            override fun requestStartTransaction(
                meta: RequestMetadata,
                req: RequestStartTransactionReq
            ): OperationExecution<RequestStartTransactionReq, RequestStartTransactionResp> {
                TODO("Not yet implemented")
            }

            override fun requestStopTransaction(
                meta: RequestMetadata,
                req: RequestStopTransactionReq
            ): OperationExecution<RequestStopTransactionReq, RequestStopTransactionResp> {
                TODO("Not yet implemented")
            }

            override fun setVariables(
                meta: RequestMetadata,
                req: SetVariablesReq
            ): OperationExecution<SetVariablesReq, SetVariablesResp> {
                TODO("Not yet implemented")
            }

            override fun unlockConnector(
                meta: RequestMetadata,
                req: UnlockConnectorReq
            ): OperationExecution<UnlockConnectorReq, UnlockConnectorResp> {
                TODO("Not yet implemented")
            }
        }

        val operations =
            ChargePointOperations.newChargePointOperations("",transport,csmsOperations)
        val response = operations.heartbeat(RequestMetadata(""), HeartbeatReq())
        expectThat(response)
            .and { get { this.response.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
    }
}