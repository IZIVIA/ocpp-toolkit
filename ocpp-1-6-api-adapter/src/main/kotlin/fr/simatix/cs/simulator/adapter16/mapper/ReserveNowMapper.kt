package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.api.model.reservenow.enumeration.ReserveNowStatusEnumType
import fr.simatix.cs.simulator.api.model.common.IdTokenType
import fr.simatix.cs.simulator.api.model.common.enumeration.IdTokenEnumType
import fr.simatix.cs.simulator.core16.model.reservenow.ReserveNowReq
import fr.simatix.cs.simulator.core16.model.reservenow.ReserveNowResp
import fr.simatix.cs.simulator.core16.model.reservenow.enumeration.ReservationStatus
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.reservenow.ReserveNowReq as ReserveNowReqGen
import fr.simatix.cs.simulator.api.model.reservenow.ReserveNowResp as ReserveNowRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class ReserveNowMapper {

    @Named("convertReservationStatus")
    fun convertReservationStatus(status: ReserveNowStatusEnumType): ReservationStatus =
        ReservationStatus.valueOf(status.name)

    @Named("convertIdToken")
    fun convertIdToken(idTag: String): IdTokenType =
        IdTokenType(idToken = idTag, type = IdTokenEnumType.Central)


    @Mapping(target = "status", source = "status", qualifiedByName = ["convertReservationStatus"])
    abstract fun genToCoreResp(reseveNowResp: ReserveNowRespGen): ReserveNowResp

    @Mapping(target = "evseId", source = "connectorId")
    @Mapping(target = "expiryDateTime", source = "expiryDate")
    @Mapping(target = "idToken", source = "idTag", qualifiedByName = ["convertIdToken"])
    @Mapping(target = "groupIdToken", source = "parentIdTag", qualifiedByName = ["convertIdToken"])
    @Mapping(target = "id", source = "reservationId")
    abstract fun coreToGenReq(reserveNowReq: ReserveNowReq): ReserveNowReqGen

}