package com.izivia.ocpp.api.model.heartbeat

import com.izivia.ocpp.api.model.Response
import kotlinx.datetime.Instant

data class HeartbeatResp(
    val currentTime: Instant
): Response
