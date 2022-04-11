package fr.simatix.cs.simulator.api.model.getcompositeschedule

import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingRateUnitEnumType
import fr.simatix.cs.simulator.api.model.common.ChargingSchedulePeriodType
import kotlinx.datetime.Instant

data class CompositeScheduleType(
    val evseId: Int,
    val duration: Int,
    val scheduleStart: Instant,
    val chargingRateUnit: ChargingRateUnitEnumType,
    val chargingSchedulePeriod: List<ChargingSchedulePeriodType>
)
