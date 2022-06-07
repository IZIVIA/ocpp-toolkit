package com.izivia.ocpp.core16.model.remotestart

data class ChargingSchedulePeriod(
    val startPeriod: Int,
    val limit: Double,
    val numberPhases: Int? = 3
)