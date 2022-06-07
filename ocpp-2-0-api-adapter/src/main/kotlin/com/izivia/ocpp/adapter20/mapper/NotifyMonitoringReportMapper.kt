package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.notifymonitoringreport.NotifyMonitoringReportReq
import com.izivia.ocpp.core20.model.notifymonitoringreport.NotifyMonitoringReportResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.notifymonitoringreport.NotifyMonitoringReportReq as NotifyMonitoringReportReqGen
import com.izivia.ocpp.api.model.notifymonitoringreport.NotifyMonitoringReportResp as NotifyMonitoringReportRespGen


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface NotifyMonitoringReportMapper {

    fun genToCoreReq(notifyMonitoringReportReq: NotifyMonitoringReportReqGen?): NotifyMonitoringReportReq

    fun coreToGenResp(notifyMonitoringReportResp: NotifyMonitoringReportResp?): NotifyMonitoringReportRespGen

}