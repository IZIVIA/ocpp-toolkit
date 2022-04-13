package fr.simatix.cs.simulator.core20.model.common

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import fr.simatix.cs.simulator.core20.model.common.enumeration.ChargingRateUnitEnumType
import fr.simatix.cs.simulator.core20.model.remotestart.SalesTariffType
import fr.simatix.cs.simulator.utils.InstantDeserializer
import fr.simatix.cs.simulator.utils.InstantSerializer
import kotlinx.datetime.Instant

data class ChargingScheduleType(
    val id: Int,
    val chargingRateUnit: ChargingRateUnitEnumType,
    val chargingSchedulePeriod: List<ChargingSchedulePeriodType>,
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val startSchedule: Instant? = null,
    val duration: Int? = null,
    val minChargingRate: Double? = null,
    val salesTariff: SalesTariffType? = null
)