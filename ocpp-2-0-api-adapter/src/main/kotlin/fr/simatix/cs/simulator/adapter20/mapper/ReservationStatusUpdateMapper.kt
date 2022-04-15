package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.reservationstatusupdate.ReservationStatusUpdateReq
import fr.simatix.cs.simulator.core20.model.reservationstatusupdate.ReservationStatusUpdateResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.reservationstatusupdate.ReservationStatusUpdateReq as ReservationStatusUpdateReqGen
import fr.simatix.cs.simulator.api.model.reservationstatusupdate.ReservationStatusUpdateResp as ReservationStatusUpdateRespGen


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ReservationStatusUpdateMapper {

    fun genToCoreReq(reservationStatusUpdateReq: ReservationStatusUpdateReqGen?): ReservationStatusUpdateReq

    fun coreToGenResp(reservationStatusUpdateResp: ReservationStatusUpdateResp?): ReservationStatusUpdateRespGen

}