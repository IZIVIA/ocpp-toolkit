package com.izivia.ocpp.core20.model.common

import com.izivia.ocpp.core20.model.common.enumeration.ChargingRateUnitEnumType
import com.izivia.ocpp.core20.model.remotestart.SalesTariffType
import kotlinx.datetime.Instant

data class ChargingScheduleType(
    val id: Int,
    val chargingRateUnit: ChargingRateUnitEnumType,
    val chargingSchedulePeriod: List<ChargingSchedulePeriodType>,
    val startSchedule: Instant? = null,
    val duration: Int? = null,
    val minChargingRate: Double? = null,
    val salesTariff: SalesTariffType? = null
)