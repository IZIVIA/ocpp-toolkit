package com.izivia.ocpp.core16.model.common

import com.izivia.ocpp.core16.model.common.enumeration.ChargingRateUnitType
import com.izivia.ocpp.core16.model.remotestart.ChargingSchedulePeriod
import kotlinx.datetime.Instant

data class ChargingSchedule(
    val chargingRateUnit: ChargingRateUnitType,
    val chargingSchedulePeriod: List<ChargingSchedulePeriod>,
    val duration: Int? = null,
    val startSchedule: Instant? = null,
    val minChargingRate: Double? = null
)