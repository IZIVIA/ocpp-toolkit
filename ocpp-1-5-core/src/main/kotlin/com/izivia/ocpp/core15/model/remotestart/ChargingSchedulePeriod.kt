package com.izivia.ocpp.core15.model.remotestart

data class ChargingSchedulePeriod(
    val startPeriod: Int,
    val limit: Double,
    val numberPhases: Int? = 3
)