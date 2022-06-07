package com.izivia.ocpp.adapter16.mapper

import com.izivia.ocpp.api.model.clearchargingprofile.ClearChargingProfileType
import com.izivia.ocpp.api.model.clearchargingprofile.enumeration.ClearChargingProfileStatusEnumType
import com.izivia.ocpp.api.model.common.enumeration.ChargingProfilePurposeEnumType
import com.izivia.ocpp.core16.model.clearchargingprofile.ClearChargingProfileReq
import com.izivia.ocpp.core16.model.clearchargingprofile.ClearChargingProfileResp
import com.izivia.ocpp.core16.model.clearchargingprofile.enumeration.ClearChargingProfileStatus
import com.izivia.ocpp.core16.model.common.enumeration.ChargingProfilePurposeType
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.clearchargingprofile.ClearChargingProfileReq as ClearChargingProfileReqGen
import com.izivia.ocpp.api.model.clearchargingprofile.ClearChargingProfileResp as ClearChargingProfileRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class ClearChargingProfileMapper {

    @Named("convertChargingStatus")
    fun convertChargingStatus(status: ClearChargingProfileStatusEnumType): ClearChargingProfileStatus =
        ClearChargingProfileStatus.valueOf(status.name)

    @Named("convertChargingProfilePurpose")
    fun convertChargingProfilePurpose(profilePurpose: ChargingProfilePurposeType?): ChargingProfilePurposeEnumType? =
        when (profilePurpose) {
            null -> null
            ChargingProfilePurposeType.ChargePointMaxProfile -> ChargingProfilePurposeEnumType.ChargingStationMaxProfile
            else -> ChargingProfilePurposeEnumType.valueOf(profilePurpose.name)
        }

    @Mapping(target = "status", source = "status", qualifiedByName = ["convertChargingStatus"])
    abstract fun genToCoreResp(clearChargingProfileResp: ClearChargingProfileRespGen): ClearChargingProfileResp

    fun coreToGenReq(clearChargingProfileReq: ClearChargingProfileReq): ClearChargingProfileReqGen =
        ClearChargingProfileReqGen(
            clearChargingProfileReq.id,
            ClearChargingProfileType(
                clearChargingProfileReq.connectorId,
                convertChargingProfilePurpose(clearChargingProfileReq.chargingProfilePurpose),
                clearChargingProfileReq.stackLevel
            )
        )

}