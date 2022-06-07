package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.getcompositeschedule.GetCompositeScheduleReq
import com.izivia.ocpp.core20.model.getcompositeschedule.GetCompositeScheduleResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.getcompositeschedule.GetCompositeScheduleReq as GetCompositeScheduleReqGen
import com.izivia.ocpp.api.model.getcompositeschedule.GetCompositeScheduleResp as GetCompositeScheduleRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface GetCompositeScheduleMapper {

    fun genToCoreResp(getCompositeScheduleResp: GetCompositeScheduleRespGen): GetCompositeScheduleResp

    fun coreToGenReq(getCompositeScheduleReq: GetCompositeScheduleReq): GetCompositeScheduleReqGen

}