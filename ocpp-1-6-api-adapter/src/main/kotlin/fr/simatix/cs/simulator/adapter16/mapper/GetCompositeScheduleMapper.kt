package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingRateUnitEnumType
import fr.simatix.cs.simulator.api.model.getcompositeschedule.CompositeScheduleType
import fr.simatix.cs.simulator.api.model.getcompositeschedule.enumeration.GenericStatusEnumType
import fr.simatix.cs.simulator.core16.model.common.ChargingSchedule
import fr.simatix.cs.simulator.core16.model.common.enumeration.ChargingRateUnitType
import fr.simatix.cs.simulator.core16.model.getcompositeschedule.GetCompositeScheduleReq
import fr.simatix.cs.simulator.core16.model.getcompositeschedule.GetCompositeScheduleResp
import fr.simatix.cs.simulator.core16.model.getcompositeschedule.enumeration.GetCompositeScheduleStatus
import fr.simatix.cs.simulator.core16.model.remotestart.ChargingSchedulePeriod
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.getcompositeschedule.GetCompositeScheduleReq as GetCompositeScheduleReqGen
import fr.simatix.cs.simulator.api.model.getcompositeschedule.GetCompositeScheduleResp as GetCompositeScheduleRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class GetCompositeScheduleMapper {

    @Named("convertChargingRateUnit")
    fun convertChargingRateUnit(chargingRateUnit: ChargingRateUnitType?): ChargingRateUnitEnumType? =
        chargingRateUnit?.let { ChargingRateUnitEnumType.valueOf(it.value) }

    @Named("convertCompositeScheduleStatus")
    fun convertCompositeScheduleStatus(status: GenericStatusEnumType): GetCompositeScheduleStatus =
        GetCompositeScheduleStatus.valueOf(status.name)

    @Named("convertChargingSchedule")
    fun convertChargingSchedule(schedule: CompositeScheduleType?): ChargingSchedule? =
        schedule?.let {
            ChargingSchedule(
                chargingRateUnit = ChargingRateUnitType.valueOf(schedule.chargingRateUnit.value),
                duration = schedule.duration,
                startSchedule = schedule.scheduleStart,
                chargingSchedulePeriod = schedule.chargingSchedulePeriod.map {
                    ChargingSchedulePeriod(
                        it.startPeriod,
                        it.limit,
                        it.numberPhases
                    )
                }
            )
        }

    @Mapping(target = "status", source = "status", qualifiedByName = ["convertCompositeScheduleStatus"])
    @Mapping(target = "connectorId", source = "schedule.evseId")
    @Mapping(target = "scheduleStart", source = "schedule.scheduleStart")
    @Mapping(target = "chargingSchedule", source = "schedule", qualifiedByName = ["convertChargingSchedule"])
    abstract fun genToCoreResp(getCompositeScheduleResp: GetCompositeScheduleRespGen): GetCompositeScheduleResp

    @Mapping(target = "evseId", source = "connectorId")
    @Mapping(target = "chargingRateUnit", source = "chargingRateUnit", qualifiedByName = ["convertChargingRateUnit"])
    abstract fun coreToGenReq(getCompositeScheduleReq: GetCompositeScheduleReq): GetCompositeScheduleReqGen

}