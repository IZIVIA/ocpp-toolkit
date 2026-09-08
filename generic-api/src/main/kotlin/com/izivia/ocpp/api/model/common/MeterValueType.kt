package com.izivia.ocpp.api.model.common

import kotlin.time.Instant

data class MeterValueType(
    val sampledValue: List<SampledValueType>,
    val timestamp: Instant,
)
