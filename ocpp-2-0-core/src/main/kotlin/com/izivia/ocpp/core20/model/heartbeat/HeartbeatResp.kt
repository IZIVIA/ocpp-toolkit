package com.izivia.ocpp.core20.model.heartbeat

import kotlinx.datetime.Instant

data class HeartbeatResp(
    val currentTime: Instant
)
