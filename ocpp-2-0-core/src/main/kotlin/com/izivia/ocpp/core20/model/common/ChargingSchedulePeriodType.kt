package com.izivia.ocpp.core20.model.common

data class ChargingSchedulePeriodType(
    val startPeriod: Int,
    val limit: Double,
    val numberPhases: Int = 3,
    val phaseToUse: Int? = null
)