package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.setvariables.SetVariablesReq
import com.izivia.ocpp.core20.model.setvariables.SetVariablesResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.setvariables.SetVariablesReq as SetVariablesReqGen
import com.izivia.ocpp.api.model.setvariables.SetVariablesResp as SetVariablesRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SetVariablesMapper {

    fun genToCoreResp(changeConfigResp: SetVariablesRespGen): SetVariablesResp

    fun coreToGenReq(changeConfigReq: SetVariablesReq): SetVariablesReqGen
}