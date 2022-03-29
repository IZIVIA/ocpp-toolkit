package fr.simatix.cs.simulator.api.model.heartbeat

import fr.simatix.cs.simulator.api.model.Response
import kotlinx.datetime.Instant

data class HeartbeatResp(
    val currentTime: Instant
): Response
