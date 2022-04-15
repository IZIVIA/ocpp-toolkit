package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.notifymonitoringreport.NotifyMonitoringReportReq
import fr.simatix.cs.simulator.core20.model.notifymonitoringreport.NotifyMonitoringReportResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.notifymonitoringreport.NotifyMonitoringReportReq as NotifyMonitoringReportReqGen
import fr.simatix.cs.simulator.api.model.notifymonitoringreport.NotifyMonitoringReportResp as NotifyMonitoringReportRespGen


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface NotifyMonitoringReportMapper {

    fun genToCoreReq(notifyMonitoringReportReq: NotifyMonitoringReportReqGen?): NotifyMonitoringReportReq

    fun coreToGenResp(notifyMonitoringReportResp: NotifyMonitoringReportResp?): NotifyMonitoringReportRespGen

}