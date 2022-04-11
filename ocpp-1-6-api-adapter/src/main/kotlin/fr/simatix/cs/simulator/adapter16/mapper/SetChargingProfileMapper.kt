package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.api.model.setchargingprofile.enumeration.ChargingProfileStatusEnumType
import fr.simatix.cs.simulator.core16.model.setchargingprofile.SetChargingProfileReq
import fr.simatix.cs.simulator.core16.model.setchargingprofile.SetChargingProfileResp
import fr.simatix.cs.simulator.core16.model.setchargingprofile.enumeration.ChargingProfileStatus
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.setchargingprofile.SetChargingProfileReq as SetChargingProfileReqGen
import fr.simatix.cs.simulator.api.model.setchargingprofile.SetChargingProfileResp as SetChargingProfileRespGen

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