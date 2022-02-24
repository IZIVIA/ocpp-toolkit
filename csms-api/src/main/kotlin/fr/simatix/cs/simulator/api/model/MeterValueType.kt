package fr.simatix.cs.simulator.api.model

import kotlinx.datetime.Instant

data class MeterValueType(
    val sampledValue: List<SampledValueType>,
    val timestamp: Instant,
)
