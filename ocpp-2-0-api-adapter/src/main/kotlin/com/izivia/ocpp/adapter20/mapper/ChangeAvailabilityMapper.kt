package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core20.model.changeavailability.ChangeAvailabilityResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.changeavailability.ChangeAvailabilityReq as ChangeAvailabilityReqGen
import com.izivia.ocpp.api.model.changeavailability.ChangeAvailabilityResp as ChangeAvailabilityRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ChangeAvailabilityMapper {

    fun genToCoreResp(availabilityResp: ChangeAvailabilityRespGen?): ChangeAvailabilityResp

    fun coreToGenReq(availabilityReq: ChangeAvailabilityReq): ChangeAvailabilityReqGen

}