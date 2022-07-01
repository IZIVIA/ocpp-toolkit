package com.izivia.ocpp.core16.model.common

import kotlinx.datetime.Instant

data class MeterValue(
    val sampledValue: List<SampledValue>,
    val timestamp: Instant
)