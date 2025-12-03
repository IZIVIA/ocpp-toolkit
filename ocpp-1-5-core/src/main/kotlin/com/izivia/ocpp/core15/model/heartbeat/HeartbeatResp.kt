package com.izivia.ocpp.core15.model.heartbeat

import com.izivia.ocpp.core15.model.Response
import kotlinx.datetime.Instant

data class HeartbeatResp(
    val currentTime: Instant
): Response
