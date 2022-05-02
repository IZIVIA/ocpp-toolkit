package fr.simatix.cs.simulator.adapter20.mapper


import fr.simatix.cs.simulator.core20.model.getmonitoringreport.GetMonitoringReportResp
import fr.simatix.cs.simulator.api.model.getmonitoringreport.GetMonitoringReportResp as GetMonitoringReportRespGen
import fr.simatix.cs.simulator.core20.model.getmonitoringreport.GetMonitoringReportReq
import fr.simatix.cs.simulator.api.model.getmonitoringreport.GetMonitoringReportReq as GetMonitoringReportReqGen

import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface GetMonitoringReportMapper {
    fun genToCoreResp(getMonitoringReportResp: GetMonitoringReportRespGen) : GetMonitoringReportResp

    fun coreToGenReq(getMonitoringReportReq: GetMonitoringReportReq) : GetMonitoringReportReqGen
}