package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.api.model.cancelreservation.enumeration.CancelReservationStatusEnumType
import fr.simatix.cs.simulator.core16.model.cancelreservation.CancelReservationReq
import fr.simatix.cs.simulator.core16.model.cancelreservation.CancelReservationResp
import fr.simatix.cs.simulator.core16.model.cancelreservation.enumeration.CancelReservationStatus
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.cancelreservation.CancelReservationReq as CancelReservationReqGen
import fr.simatix.cs.simulator.api.model.cancelreservation.CancelReservationResp as CancelReservationRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class CancelReservationMapper {

    @Named("convertReservationStatus")
    fun convertReservationStatus(status: CancelReservationStatusEnumType): CancelReservationStatus =
        CancelReservationStatus.valueOf(status.name)

    @Mapping(target = "status", source = "status", qualifiedByName = ["convertReservationStatus"])
    abstract fun genToCoreResp(cancelReservationResp: CancelReservationRespGen?): CancelReservationResp

    abstract fun coreToGenReq(cancelReservationReq: CancelReservationReq): CancelReservationReqGen

}