package com.izivia.ocpp.adapter20.mapper


import com.izivia.ocpp.core20.model.getmonitoringreport.GetMonitoringReportResp
import com.izivia.ocpp.api.model.getmonitoringreport.GetMonitoringReportResp as GetMonitoringReportRespGen
import com.izivia.ocpp.core20.model.getmonitoringreport.GetMonitoringReportReq
import com.izivia.ocpp.api.model.getmonitoringreport.GetMonitoringReportReq as GetMonitoringReportReqGen

import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface GetMonitoringReportMapper {
    fun genToCoreResp(getMonitoringReportResp: GetMonitoringReportRespGen) : GetMonitoringReportResp

    fun coreToGenReq(getMonitoringReportReq: GetMonitoringReportReq) : GetMonitoringReportReqGen
}