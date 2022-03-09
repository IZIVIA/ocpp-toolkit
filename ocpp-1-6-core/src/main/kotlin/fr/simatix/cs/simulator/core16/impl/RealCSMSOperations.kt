package fr.simatix.cs.simulator.core16.impl

import fr.simatix.cs.simulator.core16.CSMSOperations
import fr.simatix.cs.simulator.core16.OperationsHandler
import fr.simatix.cs.simulator.core16.model.reset.ResetReq
import fr.simatix.cs.simulator.core16.model.reset.ResetResp
import fr.simatix.cs.simulator.core16.model.reset.enumeration.ResetStatus
import fr.simatix.cs.simulator.operation.information.ExecutionMetadata
import fr.simatix.cs.simulator.operation.information.OperationExecution
import fr.simatix.cs.simulator.operation.information.RequestMetadata
import fr.simatix.cs.simulator.operation.information.RequestStatus
import fr.simatix.cs.simulator.transport.Transport
import fr.simatix.cs.simulator.transport.receiveMessage

class RealCSMSOperations(private val client: Transport, private val handlers: OperationsHandler) : CSMSOperations {

    override fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp> {
        client.receiveMessage("reset", handlers.reset)
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS), req,
            ResetResp(ResetStatus.Accepted)
        )
    }
}