package com.izivia.ocpp.api.model.heartbeat

import com.izivia.ocpp.api.model.Response
import kotlin.time.Instant

data class HeartbeatResp(
    val currentTime: Instant
): Response
