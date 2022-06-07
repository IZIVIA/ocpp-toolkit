package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.getvariables.GetVariablesReq
import com.izivia.ocpp.core20.model.getvariables.GetVariablesResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.getvariables.GetVariablesReq as GetVariablesReqGen
import com.izivia.ocpp.api.model.getvariables.GetVariablesResp as GetVariablesRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface GetVariablesMapper {

    fun genToCoreResp(getVariablesResp: GetVariablesRespGen?): GetVariablesResp

    fun coreToGenReq(getVariablesReq: GetVariablesReq): GetVariablesReqGen

}