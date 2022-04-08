package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.api.model.clearchargingprofile.ClearChargingProfileType
import fr.simatix.cs.simulator.api.model.clearchargingprofile.enumeration.ClearChargingProfileStatusEnumType
import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingProfilePurposeEnumType
import fr.simatix.cs.simulator.core16.model.clearchargingprofile.ClearChargingProfileReq
import fr.simatix.cs.simulator.core16.model.clearchargingprofile.ClearChargingProfileResp
import fr.simatix.cs.simulator.core16.model.clearchargingprofile.enumeration.ClearChargingProfileStatus
import fr.simatix.cs.simulator.core16.model.common.enumeration.ChargingProfilePurposeType
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.clearchargingprofile.ClearChargingProfileReq as ClearChargingProfileReqGen
import fr.simatix.cs.simulator.api.model.clearchargingprofile.ClearChargingProfileResp as ClearChargingProfileRespGen

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