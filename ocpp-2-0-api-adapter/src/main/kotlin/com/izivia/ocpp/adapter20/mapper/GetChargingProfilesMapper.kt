package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.common.ChargingProfileCriterionType
import com.izivia.ocpp.core20.model.common.enumeration.ChargingLimitSourceEnumType
import com.izivia.ocpp.core20.model.common.enumeration.ChargingProfilePurposeEnumType
import com.izivia.ocpp.core20.model.getchargingprofiles.GetChargingProfilesResp
import com.izivia.ocpp.core20.model.getchargingprofiles.GetChargingProfilesReq
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.getchargingprofiles.GetChargingProfilesResp as GetChargingProfilesRespGen
import com.izivia.ocpp.api.model.getchargingprofiles.GetChargingProfilesReq as GetChargingProfilesReqGen
import com.izivia.ocpp.api.model.common.enumeration.ChargingProfilePurposeEnumType as ChargingProfilePurposeEnumTypeGen
import com.izivia.ocpp.api.model.common.enumeration.ChargingLimitSourceEnumType as ChargingLimitSourceEnumTypeGen
import com.izivia.ocpp.api.model.common.ChargingProfileCriterionType as ChargingProfileCriterionTypeGen


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class GetChargingProfilesMapper {

    fun chargingProfilePurpose(purpose: ChargingProfilePurposeEnumType): ChargingProfilePurposeEnumTypeGen =
            ChargingProfilePurposeEnumTypeGen.valueOf(purpose.value)

    fun chargingLimitSource(limitSource : List<ChargingLimitSourceEnumType>): List<ChargingLimitSourceEnumTypeGen>
        = limitSource.map { ChargingLimitSourceEnumTypeGen.valueOf(it.value) }




    fun chargingProfileCriterionType(criterion: ChargingProfileCriterionType?): ChargingProfileCriterionTypeGen =
            if (criterion == null) ChargingProfileCriterionTypeGen()
            else ChargingProfileCriterionTypeGen(
                    chargingProfilePurpose =  criterion.chargingProfilePurpose?.let { chargingProfilePurpose(it) },
                    chargingProfileId = criterion.chargingProfileId,
                    stackLevel =  criterion.stackLevel,
                    chargingLimitSource = criterion.chargingLimitSource?.let { chargingLimitSource(it) }
            )

    abstract fun genToCoreResp(getChargingProfilesResp: GetChargingProfilesRespGen): GetChargingProfilesResp

    fun coreToGenReq(getChargingProfilesReq: GetChargingProfilesReq): GetChargingProfilesReqGen {
        return GetChargingProfilesReqGen(
            getChargingProfilesReq.requestId,
            chargingProfileCriterionType(getChargingProfilesReq.chargingProfile),
            getChargingProfilesReq.evseId
        )
    }
}