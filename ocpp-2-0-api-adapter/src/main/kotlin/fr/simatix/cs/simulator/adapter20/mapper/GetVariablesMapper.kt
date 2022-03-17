package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.getvariables.GetVariablesReq
import fr.simatix.cs.simulator.core20.model.getvariables.GetVariablesResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.getvariables.GetVariablesReq as GetVariablesReqGen
import fr.simatix.cs.simulator.api.model.getvariables.GetVariablesResp as GetVariablesRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface GetVariablesMapper {

    fun genToCoreResp(getVariablesResp: GetVariablesRespGen?): GetVariablesResp

    fun coreToGenReq(getVariablesReq: GetVariablesReq): GetVariablesReqGen

}