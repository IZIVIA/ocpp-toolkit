package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.core20.model.cancelreservation.CancelReservationResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.cancelreservation.CancelReservationReq as CancelReservationReqGen
import com.izivia.ocpp.api.model.cancelreservation.CancelReservationResp as CancelReservationRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CancelReservationMapper {

    fun genToCoreResp(cancelReservationResp: CancelReservationRespGen?): CancelReservationResp

    fun coreToGenReq(cancelReservationReq: CancelReservationReq?): CancelReservationReqGen

}