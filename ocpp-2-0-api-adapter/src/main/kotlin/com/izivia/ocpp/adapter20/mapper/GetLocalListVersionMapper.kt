package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.core20.model.getlocallistversion.GetLocalListVersionResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.getlocallistversion.GetLocalListVersionReq as GetLocalListVersionReqGen
import com.izivia.ocpp.api.model.getlocallistversion.GetLocalListVersionResp as GetLocalListVersionRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface GetLocalListVersionMapper {

    fun genToCoreResp(getLocalListVersionResp: GetLocalListVersionRespGen?): GetLocalListVersionResp

    fun coreToGenReq(getLocalListVersionReq: GetLocalListVersionReq): GetLocalListVersionReqGen

}