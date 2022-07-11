package com.izivia.ocpp.core20.model.getcompositeschedule

import com.izivia.ocpp.core20.model.common.ChargingSchedulePeriodType
import com.izivia.ocpp.core20.model.common.enumeration.ChargingRateUnitEnumType
import kotlinx.datetime.Instant

data class CompositeScheduleType(
    val evseId: Int,
    val duration: Int,
    val scheduleStart: Instant,
    val chargingRateUnit: ChargingRateUnitEnumType,
    val chargingSchedulePeriod: List<ChargingSchedulePeriodType>
)