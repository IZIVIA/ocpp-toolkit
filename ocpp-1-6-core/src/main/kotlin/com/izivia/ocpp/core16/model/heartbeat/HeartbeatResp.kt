package com.izivia.ocpp.core16.model.heartbeat

import kotlinx.datetime.Instant

data class HeartbeatResp(
    val currentTime: Instant
)
