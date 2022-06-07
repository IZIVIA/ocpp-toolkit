package com.izivia.ocpp.adapter16.mapper

import com.izivia.ocpp.api.model.cancelreservation.enumeration.CancelReservationStatusEnumType
import com.izivia.ocpp.core16.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.core16.model.cancelreservation.CancelReservationResp
import com.izivia.ocpp.core16.model.cancelreservation.enumeration.CancelReservationStatus
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.cancelreservation.CancelReservationReq as CancelReservationReqGen
import com.izivia.ocpp.api.model.cancelreservation.CancelReservationResp as CancelReservationRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class CancelReservationMapper {

    @Named("convertReservationStatus")
    fun convertReservationStatus(status: CancelReservationStatusEnumType): CancelReservationStatus =
        CancelReservationStatus.valueOf(status.name)

    @Mapping(target = "status", source = "status", qualifiedByName = ["convertReservationStatus"])
    abstract fun genToCoreResp(cancelReservationResp: CancelReservationRespGen?): CancelReservationResp

    abstract fun coreToGenReq(cancelReservationReq: CancelReservationReq): CancelReservationReqGen

}