package fr.simatix.cs.simulator.api.model.common

import kotlinx.datetime.Instant

data class MeterValueType(
    val sampledValue: List<SampledValueType>,
    val timestamp: Instant,
)
