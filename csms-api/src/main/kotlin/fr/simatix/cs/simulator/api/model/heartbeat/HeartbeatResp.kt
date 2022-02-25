package fr.simatix.cs.simulator.api.model.heartbeat

import kotlinx.datetime.Instant

data class HeartbeatResp(
    val currentTime: Instant
)
