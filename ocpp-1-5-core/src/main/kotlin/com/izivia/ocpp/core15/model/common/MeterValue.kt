package com.izivia.ocpp.core15.model.common

import kotlin.time.Instant

data class MeterValue(
    val timestamp: Instant,
    val value: List<SampledValue>
)
