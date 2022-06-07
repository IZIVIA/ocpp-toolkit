package com.izivia.ocpp.api.model.common

import com.izivia.ocpp.api.model.common.enumeration.ChargingRateUnitEnumType
import com.izivia.ocpp.api.model.remotestart.SalesTariffType
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