package fr.simatix.cs.simulator.api.model

import kotlinx.datetime.Instant

data class ExecutionMetadata(
    val reqMeta: RequestMetadata,
    val requestTime: Instant,
    val responseTime: Instant
)
