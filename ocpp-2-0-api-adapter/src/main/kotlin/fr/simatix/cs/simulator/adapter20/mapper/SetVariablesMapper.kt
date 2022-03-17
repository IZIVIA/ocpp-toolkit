package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.setvariables.SetVariablesReq
import fr.simatix.cs.simulator.core20.model.setvariables.SetVariablesResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.setvariables.SetVariablesReq as SetVariablesReqGen
import fr.simatix.cs.simulator.api.model.setvariables.SetVariablesResp as SetVariablesRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SetVariablesMapper {

    fun genToCoreResp(changeConfigResp: SetVariablesRespGen): SetVariablesResp

    fun coreToGenReq(changeConfigReq: SetVariablesReq): SetVariablesReqGen
}