package fr.simatix.cs.simulator.adapter20.mapper

import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

import fr.simatix.cs.simulator.core20.model.costupdated.CostUpdatedReq
import fr.simatix.cs.simulator.core20.model.costupdated.CostUpdatedResp
import fr.simatix.cs.simulator.api.model.costupdated.CostUpdatedReq as CostUpdatedReqGen
import fr.simatix.cs.simulator.api.model.costupdated.CostUpdatedResp as CostUpdatedRespGen
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CostUpdatedMapper {

    fun genToCoreResp(costUpdatedResp: CostUpdatedRespGen): CostUpdatedResp
    fun coreToGenReq(costUpdatedReq: CostUpdatedReq): CostUpdatedReqGen
}
