package com.izivia.ocpp.core20.model.common

import kotlinx.datetime.Instant

data class MeterValueType(
    val sampledValue: List<SampledValueType>,
    val timestamp: Instant,
)
