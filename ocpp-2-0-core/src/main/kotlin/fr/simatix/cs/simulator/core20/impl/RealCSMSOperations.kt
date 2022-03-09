package fr.simatix.cs.simulator.core20.impl

import fr.simatix.cs.simulator.core20.CSMSOperations
import fr.simatix.cs.simulator.core20.OperationsHandler
import fr.simatix.cs.simulator.core20.model.reset.ResetReq
import fr.simatix.cs.simulator.core20.model.reset.ResetResp
import fr.simatix.cs.simulator.core20.model.reset.enumeration.ResetStatusEnumType
import fr.simatix.cs.simulator.operation.information.ExecutionMetadata
import fr.simatix.cs.simulator.operation.information.OperationExecution
import fr.simatix.cs.simulator.operation.information.RequestMetadata
import fr.simatix.cs.simulator.operation.information.RequestStatus
import fr.simatix.cs.simulator.transport.Transport
import fr.simatix.cs.simulator.transport.receiveMessage

class RealCSMSOperations(private val client: Transport, private val handlers: OperationsHandler): CSMSOperations {

    override fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp> {
        client.receiveMessage("reset", handlers.reset)
        return OperationExecution(ExecutionMetadata(meta,RequestStatus.SUCCESS),req,
            ResetResp(ResetStatusEnumType.Accepted)
        )
    }
}