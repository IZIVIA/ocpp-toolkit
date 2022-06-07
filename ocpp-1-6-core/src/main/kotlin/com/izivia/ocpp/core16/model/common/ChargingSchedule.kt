package com.izivia.ocpp.core16.model.common

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.izivia.ocpp.core16.model.common.enumeration.ChargingRateUnitType
import com.izivia.ocpp.core16.model.remotestart.ChargingSchedulePeriod
import com.izivia.ocpp.utils.InstantDeserializer
import com.izivia.ocpp.utils.InstantSerializer
import kotlinx.datetime.Instant

data class ChargingSchedule(
    val chargingRateUnit: ChargingRateUnitType,
    val chargingSchedulePeriod: List<ChargingSchedulePeriod>,
    val duration: Int? = null,
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val startSchedule: Instant? = null,
    val minChargingRate: Double? = null
)