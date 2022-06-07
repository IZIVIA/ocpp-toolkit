package com.izivia.ocpp.api.model.common

import kotlinx.datetime.Instant

data class MeterValueType(
    val sampledValue: List<SampledValueType>,
    val timestamp: Instant,
)
