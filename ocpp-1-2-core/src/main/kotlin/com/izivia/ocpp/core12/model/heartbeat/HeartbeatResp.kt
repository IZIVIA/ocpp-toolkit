package com.izivia.ocpp.core12.model.heartbeat

import com.izivia.ocpp.core12.model.Response
import kotlin.time.Instant

data class HeartbeatResp(
    val currentTime: Instant
): Response
