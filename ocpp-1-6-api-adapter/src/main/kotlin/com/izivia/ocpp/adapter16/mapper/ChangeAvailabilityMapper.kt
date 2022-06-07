package com.izivia.ocpp.adapter16.mapper

import com.izivia.ocpp.api.model.common.EVSEType
import com.izivia.ocpp.core16.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core16.model.changeavailability.ChangeAvailabilityResp
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.changeavailability.ChangeAvailabilityReq as ChangeAvailabilityReqGen
import com.izivia.ocpp.api.model.changeavailability.ChangeAvailabilityResp as ChangeAvailabilityRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class ChangeAvailabilityMapper {

    abstract fun genToCoreResp(availabilityResp: ChangeAvailabilityRespGen?): ChangeAvailabilityResp

    @Named("convertConnectorId")
    fun convertConnectorId(connectorId: Int): EVSEType =
        EVSEType(connectorId,connectorId)

    @Mapping( target = "operationalStatus", source = "type")
    @Mapping (target= "evse", source = "connectorId", qualifiedByName = ["convertConnectorId"])
    abstract fun coreToGenReq(availabilityReq: ChangeAvailabilityReq): ChangeAvailabilityReqGen

}