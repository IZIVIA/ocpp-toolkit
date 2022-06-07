package com.izivia.ocpp.adapter16.mapper

import com.izivia.ocpp.api.model.reservenow.enumeration.ReserveNowStatusEnumType
import com.izivia.ocpp.api.model.common.IdTokenType
import com.izivia.ocpp.api.model.common.enumeration.IdTokenEnumType
import com.izivia.ocpp.core16.model.reservenow.ReserveNowReq
import com.izivia.ocpp.core16.model.reservenow.ReserveNowResp
import com.izivia.ocpp.core16.model.reservenow.enumeration.ReservationStatus
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.reservenow.ReserveNowReq as ReserveNowReqGen
import com.izivia.ocpp.api.model.reservenow.ReserveNowResp as ReserveNowRespGen

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