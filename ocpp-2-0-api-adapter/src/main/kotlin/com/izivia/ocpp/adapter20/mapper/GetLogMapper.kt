package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.getlog.GetLogReq
import com.izivia.ocpp.core20.model.getlog.GetLogResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.getlog.GetLogReq as GetLogReqGen
import com.izivia.ocpp.api.model.getlog.GetLogResp as GetLogRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface GetLogMapper {

    fun genToCoreResp(getLogResp: GetLogRespGen?): GetLogResp

    fun coreToGenReq(getLogReq: GetLogReq?): GetLogReqGen

}