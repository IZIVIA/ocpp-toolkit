package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.common.ChargingProfileCriterionType
import fr.simatix.cs.simulator.core20.model.common.enumeration.ChargingLimitSourceEnumType
import fr.simatix.cs.simulator.core20.model.common.enumeration.ChargingProfilePurposeEnumType
import fr.simatix.cs.simulator.core20.model.getchargingprofiles.GetChargingProfilesResp
import fr.simatix.cs.simulator.core20.model.getchargingprofiles.GetChargingProfilesReq
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.getchargingprofiles.GetChargingProfilesResp as GetChargingProfilesRespGen
import fr.simatix.cs.simulator.api.model.getchargingprofiles.GetChargingProfilesReq as GetChargingProfilesReqGen
import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingProfilePurposeEnumType as ChargingProfilePurposeEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingLimitSourceEnumType as ChargingLimitSourceEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.ChargingProfileCriterionType as ChargingProfileCriterionTypeGen


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