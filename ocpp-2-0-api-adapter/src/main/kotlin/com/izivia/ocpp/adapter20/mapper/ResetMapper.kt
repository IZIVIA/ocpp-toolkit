package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.reset.ResetReq
import com.izivia.ocpp.core20.model.reset.ResetResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.reset.ResetReq as ResetReqGen
import com.izivia.ocpp.api.model.reset.ResetResp as ResetRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ResetMapper {

    fun genToCoreResp(resetResp: ResetRespGen): ResetResp

    fun coreToGenReq(resetReq: ResetReq): ResetReqGen

}
