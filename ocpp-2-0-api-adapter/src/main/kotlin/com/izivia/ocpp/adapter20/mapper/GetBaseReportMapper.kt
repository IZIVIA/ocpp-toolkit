package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.getbasereport.GetBaseReportReq
import com.izivia.ocpp.core20.model.getbasereport.GetBaseReportResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.getbasereport.GetBaseReportReq as GetBaseReportReqGen
import com.izivia.ocpp.api.model.getbasereport.GetBaseReportResp as GetBaseReportRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface GetBaseReportMapper {

    fun genToCoreResp(getBaseReportResp: GetBaseReportRespGen?): GetBaseReportResp

    fun coreToGenReq(getBaseReportReq: GetBaseReportReq): GetBaseReportReqGen

}