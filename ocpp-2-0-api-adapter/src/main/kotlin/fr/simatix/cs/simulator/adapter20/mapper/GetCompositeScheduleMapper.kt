package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.getcompositeschedule.GetCompositeScheduleReq
import fr.simatix.cs.simulator.core20.model.getcompositeschedule.GetCompositeScheduleResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.getcompositeschedule.GetCompositeScheduleReq as GetCompositeScheduleReqGen
import fr.simatix.cs.simulator.api.model.getcompositeschedule.GetCompositeScheduleResp as GetCompositeScheduleRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface GetCompositeScheduleMapper {

    fun genToCoreResp(getCompositeScheduleResp: GetCompositeScheduleRespGen): GetCompositeScheduleResp

    fun coreToGenReq(getCompositeScheduleReq: GetCompositeScheduleReq): GetCompositeScheduleReqGen

}