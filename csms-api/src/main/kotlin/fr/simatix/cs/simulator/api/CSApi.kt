package fr.simatix.cs.simulator.api

import fr.simatix.cs.simulator.api.model.reset.ResetReq
import fr.simatix.cs.simulator.api.model.reset.ResetResp
import fr.simatix.cs.simulator.operation.information.OperationExecution
import fr.simatix.cs.simulator.operation.information.RequestMetadata

interface CSApi {

    fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp>

}