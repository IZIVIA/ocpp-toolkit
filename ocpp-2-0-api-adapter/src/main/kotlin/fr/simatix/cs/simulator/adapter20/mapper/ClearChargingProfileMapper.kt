package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.clearchargingprofile.ClearChargingProfileReq
import fr.simatix.cs.simulator.core20.model.clearchargingprofile.ClearChargingProfileResp
import fr.simatix.cs.simulator.core20.model.clearchargingprofile.ClearChargingProfileType
import fr.simatix.cs.simulator.core20.model.common.enumeration.ChargingProfilePurposeEnumType
import org.mapstruct.Mapper
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.clearchargingprofile.ClearChargingProfileReq as ClearChargingProfileReqGen
import fr.simatix.cs.simulator.api.model.clearchargingprofile.ClearChargingProfileResp as ClearChargingProfileRespGen
import fr.simatix.cs.simulator.api.model.clearchargingprofile.ClearChargingProfileType as ClearChargingProfileTypeGen
import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingProfilePurposeEnumType as ChargingProfilePurposeEnumTypeGen

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