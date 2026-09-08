package com.izivia.ocpp.core16.model.heartbeat

import com.izivia.ocpp.core16.model.Response
import kotlin.time.Instant

data class HeartbeatResp(
    val currentTime: Instant
) : Response
