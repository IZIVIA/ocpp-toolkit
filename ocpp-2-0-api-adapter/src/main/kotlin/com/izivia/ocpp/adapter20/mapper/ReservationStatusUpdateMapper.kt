package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.reservationstatusupdate.ReservationStatusUpdateReq
import com.izivia.ocpp.core20.model.reservationstatusupdate.ReservationStatusUpdateResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.reservationstatusupdate.ReservationStatusUpdateReq as ReservationStatusUpdateReqGen
import com.izivia.ocpp.api.model.reservationstatusupdate.ReservationStatusUpdateResp as ReservationStatusUpdateRespGen


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ReservationStatusUpdateMapper {

    fun genToCoreReq(reservationStatusUpdateReq: ReservationStatusUpdateReqGen?): ReservationStatusUpdateReq

    fun coreToGenResp(reservationStatusUpdateResp: ReservationStatusUpdateResp?): ReservationStatusUpdateRespGen

}