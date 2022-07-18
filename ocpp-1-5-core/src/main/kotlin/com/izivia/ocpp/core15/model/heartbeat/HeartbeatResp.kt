package com.izivia.ocpp.core15.model.heartbeat

import kotlinx.datetime.Instant

data class HeartbeatResp(
    val currentTime: Instant
)
