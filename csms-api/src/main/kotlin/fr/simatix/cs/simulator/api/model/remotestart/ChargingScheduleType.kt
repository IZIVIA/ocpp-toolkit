package fr.simatix.cs.simulator.api.model.remotestart

import fr.simatix.cs.simulator.api.model.common.ChargingSchedulePeriodType
import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingRateUnitEnumType
import kotlinx.datetime.Instant

data class ChargingScheduleType(
    val id: Int? = null,
    val chargingRateUnit: ChargingRateUnitEnumType,
    val chargingSchedulePeriod: List<ChargingSchedulePeriodType>,
    val startSchedule: Instant? = null,
    val duration: Int? = null,
    val minChargingRate: Double? = null,
    val salesTariff: SalesTariffType? = null
)