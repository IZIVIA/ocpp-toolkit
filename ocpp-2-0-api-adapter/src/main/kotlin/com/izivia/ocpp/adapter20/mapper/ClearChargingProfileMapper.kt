package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.clearchargingprofile.ClearChargingProfileReq
import com.izivia.ocpp.core20.model.clearchargingprofile.ClearChargingProfileResp
import com.izivia.ocpp.core20.model.clearchargingprofile.ClearChargingProfileType
import com.izivia.ocpp.core20.model.common.enumeration.ChargingProfilePurposeEnumType
import org.mapstruct.Mapper
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.clearchargingprofile.ClearChargingProfileReq as ClearChargingProfileReqGen
import com.izivia.ocpp.api.model.clearchargingprofile.ClearChargingProfileResp as ClearChargingProfileRespGen
import com.izivia.ocpp.api.model.clearchargingprofile.ClearChargingProfileType as ClearChargingProfileTypeGen
import com.izivia.ocpp.api.model.common.enumeration.ChargingProfilePurposeEnumType as ChargingProfilePurposeEnumTypeGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class ClearChargingProfileMapper {

    @Named("chargingProfilePurpose")
    fun chargingProfilePurpose(purpose: ChargingProfilePurposeEnumType): ChargingProfilePurposeEnumTypeGen =
        ChargingProfilePurposeEnumTypeGen.valueOf(purpose.value)

    @Named("chargingProfileCriteria")
    fun chargingProfileCriteria(criteria: ClearChargingProfileType?): ClearChargingProfileTypeGen? =
        if (criteria == null) null
        else ClearChargingProfileTypeGen(
            criteria.evseId,
            if (criteria.chargingProfilePurpose == null) null else chargingProfilePurpose(criteria.chargingProfilePurpose!!),
            criteria.stackLevel
        )

    abstract fun genToCoreResp(clearChargingProfileResp: ClearChargingProfileRespGen?): ClearChargingProfileResp

    fun coreToGenReq(clearChargingProfileReq: ClearChargingProfileReq): ClearChargingProfileReqGen {
        return ClearChargingProfileReqGen(
            clearChargingProfileReq.chargingProfileId,
            chargingProfileCriteria(clearChargingProfileReq.chargingProfileCriteria)
        )
    }
}