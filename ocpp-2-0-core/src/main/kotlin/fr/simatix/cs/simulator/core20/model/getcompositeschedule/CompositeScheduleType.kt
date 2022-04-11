package fr.simatix.cs.simulator.core20.model.getcompositeschedule

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import fr.simatix.cs.simulator.core20.model.common.ChargingSchedulePeriodType
import fr.simatix.cs.simulator.core20.model.common.enumeration.ChargingRateUnitEnumType
import fr.simatix.cs.simulator.utils.InstantDeserializer
import fr.simatix.cs.simulator.utils.InstantSerializer
import kotlinx.datetime.Instant

data class CompositeScheduleType(
    val evseId: Int,
    val duration: Int,
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val scheduleStart: Instant,
    val chargingRateUnit: ChargingRateUnitEnumType,
    val chargingSchedulePeriod: List<ChargingSchedulePeriodType>
)