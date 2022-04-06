package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.cancelreservation.CancelReservationReq
import fr.simatix.cs.simulator.core20.model.cancelreservation.CancelReservationResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.cancelreservation.CancelReservationReq as CancelReservationReqGen
import fr.simatix.cs.simulator.api.model.cancelreservation.CancelReservationResp as CancelReservationRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CancelReservationMapper {

    fun genToCoreResp(cancelReservationResp: CancelReservationRespGen?): CancelReservationResp

    fun coreToGenReq(cancelReservationReq: CancelReservationReq?): CancelReservationReqGen

}