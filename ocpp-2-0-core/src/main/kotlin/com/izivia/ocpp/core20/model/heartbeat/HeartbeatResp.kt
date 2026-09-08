package com.izivia.ocpp.core20.model.heartbeat

import kotlin.time.Instant

data class HeartbeatResp(
    val currentTime: Instant
)
