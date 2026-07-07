package com.izivia.ocpp.core12.model.common

import kotlinx.datetime.Instant

data class MeterValue(
    val timestamp: Instant,
    val value: Int
)
