package com.izivia.ocpp.core12.model.heartbeat

import com.izivia.ocpp.core12.model.Response
import kotlinx.datetime.Instant

data class HeartbeatResp(
    val currentTime: Instant
): Response
