package com.izivia.ocpp.core12.model.common

import kotlin.time.Instant

data class MeterValue(
    val timestamp: Instant,
    val value: Int
)
