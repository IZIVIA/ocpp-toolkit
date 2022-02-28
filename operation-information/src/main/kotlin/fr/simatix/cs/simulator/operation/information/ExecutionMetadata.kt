package fr.simatix.cs.simulator.operation.information

import kotlinx.datetime.Instant

data class ExecutionMetadata(
    val reqMeta: RequestMetadata,
    val status: RequestStatus,
    val requestTime: Instant = Instant.parse("0000-01-01T00:00:00.000Z"),
    val responseTime: Instant = Instant.parse("0000-01-01T00:00:00.000Z")
)
