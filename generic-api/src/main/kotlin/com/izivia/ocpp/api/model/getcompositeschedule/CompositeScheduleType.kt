package com.izivia.ocpp.api.model.getcompositeschedule

import com.izivia.ocpp.api.model.common.enumeration.ChargingRateUnitEnumType
import com.izivia.ocpp.api.model.common.ChargingSchedulePeriodType
import kotlinx.datetime.Instant

data class CompositeScheduleType(
    val evseId: Int,
    val duration: Int,
    val scheduleStart: Instant,
    val chargingRateUnit: ChargingRateUnitEnumType,
    val chargingSchedulePeriod: List<ChargingSchedulePeriodType>
)
