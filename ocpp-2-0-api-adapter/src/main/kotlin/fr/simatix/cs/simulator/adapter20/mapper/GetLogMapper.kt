package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.getlog.GetLogReq
import fr.simatix.cs.simulator.core20.model.getlog.GetLogResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.getlog.GetLogReq as GetLogReqGen
import fr.simatix.cs.simulator.api.model.getlog.GetLogResp as GetLogRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface GetLogMapper {

    fun genToCoreResp(getLogResp: GetLogRespGen?): GetLogResp

    fun coreToGenReq(getLogReq: GetLogReq?): GetLogReqGen

}