package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.getreport.GetReportReq
import com.izivia.ocpp.core20.model.getreport.GetReportResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.getreport.GetReportReq as GetReportReqGen
import com.izivia.ocpp.api.model.getreport.GetReportResp as GetReportRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface GetReportMapper {

    fun genToCoreResp(getReportResp: GetReportRespGen?): GetReportResp

    fun coreToGenReq(getReportReq: GetReportReq): GetReportReqGen

}