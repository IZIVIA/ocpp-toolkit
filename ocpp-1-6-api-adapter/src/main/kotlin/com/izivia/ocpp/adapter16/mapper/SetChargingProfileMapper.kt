package com.izivia.ocpp.adapter16.mapper

import com.izivia.ocpp.api.model.setchargingprofile.enumeration.ChargingProfileStatusEnumType
import com.izivia.ocpp.core16.model.setchargingprofile.SetChargingProfileReq
import com.izivia.ocpp.core16.model.setchargingprofile.SetChargingProfileResp
import com.izivia.ocpp.core16.model.setchargingprofile.enumeration.ChargingProfileStatus
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.setchargingprofile.SetChargingProfileReq as SetChargingProfileReqGen
import com.izivia.ocpp.api.model.setchargingprofile.SetChargingProfileResp as SetChargingProfileRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = [CommonMapper::class])
abstract class SetChargingProfileMapper {

    @Named("convertChargingProfileStatus")
    fun convertChargingProfileStatus(status: ChargingProfileStatusEnumType): ChargingProfileStatus =
        ChargingProfileStatus.valueOf(status.value)

    @Mapping(target = "status", source = "status", qualifiedByName = ["convertChargingProfileStatus"])
    abstract fun genToCoreResp(setChargingProfileResp: SetChargingProfileRespGen?): SetChargingProfileResp

    @Mapping(target = "evseId", source = "connectorId")
    @Mapping(
        target = "chargingProfile",
        source = "csChargingProfiles",
        qualifiedByName = ["convertChargingProfile"]
    )
    abstract fun coreToGenReq(setChargingProfileReq: SetChargingProfileReq): SetChargingProfileReqGen

}