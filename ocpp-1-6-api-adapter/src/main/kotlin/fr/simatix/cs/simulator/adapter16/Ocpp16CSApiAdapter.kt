package fr.simatix.cs.simulator.adapter16

import fr.simatix.cs.simulator.adapter16.mapper.ResetMapper
import fr.simatix.cs.simulator.api.CSApi
import fr.simatix.cs.simulator.core16.CSMSOperations
import fr.simatix.cs.simulator.core16.model.reset.ResetReq
import fr.simatix.cs.simulator.core16.model.reset.ResetResp
import fr.simatix.cs.simulator.operation.information.ExecutionMetadata
import fr.simatix.cs.simulator.operation.information.OperationExecution
import fr.simatix.cs.simulator.operation.information.RequestMetadata
import fr.simatix.cs.simulator.operation.information.RequestStatus
import org.mapstruct.factory.Mappers

class Ocpp16CSApiAdapter(private val csApi: CSApi): CSMSOperations {

    override fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp> {
        val mapper: ResetMapper = Mappers.getMapper(ResetMapper::class.java)
        val response = csApi.reset(meta, mapper.coreToGenReq(req))
        return OperationExecution(ExecutionMetadata(meta,RequestStatus.SUCCESS),req,mapper.genToCoreResp(response.response))
    }

}