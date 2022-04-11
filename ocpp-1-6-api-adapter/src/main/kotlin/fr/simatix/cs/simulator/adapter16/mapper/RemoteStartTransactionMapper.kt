package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.api.model.common.IdTokenType
import fr.simatix.cs.simulator.api.model.common.enumeration.IdTokenEnumType
import fr.simatix.cs.simulator.api.model.remotestart.ChargingProfileType
import fr.simatix.cs.simulator.api.model.remotestart.ChargingScheduleType
import fr.simatix.cs.simulator.api.model.remotestart.RequestStartTransactionReq
import fr.simatix.cs.simulator.api.model.remotestart.RequestStartTransactionResp
import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingProfilePurposeEnumType
import fr.simatix.cs.simulator.core16.model.remotestart.ChargingProfile
import fr.simatix.cs.simulator.core16.model.common.ChargingSchedule
import fr.simatix.cs.simulator.core16.model.remotestart.RemoteStartTransactionReq
import fr.simatix.cs.simulator.core16.model.remotestart.RemoteStartTransactionResp
import fr.simatix.cs.simulator.core16.model.common.enumeration.ChargingProfilePurposeType
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = [CommonMapper::class])
abstract class RemoteStartTransactionMapper {

    abstract fun genToCoreResp(remoteStartResp: RequestStartTransactionResp?): RemoteStartTransactionResp

    @Named("convertIdTag")
    fun convertIdTag(idTag: String): IdTokenType = IdTokenType(idTag, IdTokenEnumType.Central)

    @Named("convertChargingProfilePurpose")
    fun convertChargingProfilePurpose(profilePurpose: ChargingProfilePurposeType): ChargingProfilePurposeEnumType =
        when (profilePurpose) {
            ChargingProfilePurposeType.ChargePointMaxProfile -> ChargingProfilePurposeEnumType.ChargingStationMaxProfile
            else -> ChargingProfilePurposeEnumType.valueOf(profilePurpose.name)
        }

    abstract fun convertChargingSchedule(chargingSchedule: ChargingSchedule): ChargingScheduleType

    @Named("convertChargingScheduleList")
    fun convertChargingScheduleList(chargingSchedule: ChargingSchedule): List<ChargingScheduleType> =
        listOf(convertChargingSchedule(chargingSchedule))

    @Named("convertChargingProfile")
    @Mapping(target = "id", source = "chargingProfileId")
    @Mapping(
        target = "chargingProfilePurpose",
        source = "chargingProfilePurpose",
        qualifiedByName = ["convertChargingProfilePurpose"]
    )
    @Mapping(
        target = "chargingSchedule",
        source = "chargingSchedule",
        qualifiedByName = ["convertChargingScheduleList"]
    )
    abstract fun convertChargingProfile(profile: ChargingProfile): ChargingProfileType

    @Mapping(target = "remoteStartId", source = "remoteStartId")
    @Mapping(target = "evseId", source = "remoteStartReq.connectorId")
    @Mapping(target = "idToken", source = "remoteStartReq.idTag", qualifiedByName = ["convertIdTag"])
    @Mapping(
        target = "chargingProfile",
        source = "remoteStartReq.chargingProfile",
        qualifiedByName = ["convertChargingProfile"]
    )
    abstract fun coreToGenReq(remoteStartReq: RemoteStartTransactionReq, remoteStartId: Int): RequestStartTransactionReq

}