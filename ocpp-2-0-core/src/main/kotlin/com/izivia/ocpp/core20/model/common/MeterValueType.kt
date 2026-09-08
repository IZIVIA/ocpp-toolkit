package com.izivia.ocpp.core20.model.common

import kotlin.time.Instant

data class MeterValueType(
    val sampledValue: List<SampledValueType>,
    val timestamp: Instant,
)
