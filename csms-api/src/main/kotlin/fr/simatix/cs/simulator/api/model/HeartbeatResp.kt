package fr.simatix.cs.simulator.api.model

import kotlinx.datetime.Instant

data class HeartbeatResp(
    val currentTime: Instant
)
