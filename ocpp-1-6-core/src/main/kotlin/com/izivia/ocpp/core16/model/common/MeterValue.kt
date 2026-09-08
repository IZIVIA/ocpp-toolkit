package com.izivia.ocpp.core16.model.common

import kotlin.time.Instant

data class MeterValue(
    val sampledValue: List<SampledValue>,
    val timestamp: Instant
)