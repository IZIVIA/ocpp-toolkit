package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.getreport.GetReportReq
import fr.simatix.cs.simulator.core20.model.getreport.GetReportResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.getreport.GetReportReq as GetReportReqGen
import fr.simatix.cs.simulator.api.model.getreport.GetReportResp as GetReportRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class GetReportMapper {

    abstract fun genToCoreResp(getReportResp: GetReportRespGen?): GetReportResp

    abstract fun coreToGenReq(getReportReq: GetReportReq): GetReportReqGen

}