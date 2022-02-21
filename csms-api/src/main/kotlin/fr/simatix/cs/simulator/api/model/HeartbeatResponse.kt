package fr.simatix.cs.simulator.api.model

import kotlinx.datetime.Instant

data class HeartbeatResponse(
    val currentTime: Instant
)
