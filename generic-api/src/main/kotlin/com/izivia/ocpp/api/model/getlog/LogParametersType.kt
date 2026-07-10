package com.izivia.ocpp.api.model.getlog

import kotlin.time.Instant

data class LogParametersType(
    val remoteLocation: String,
    val oldestTimestamp: Instant? = null,
    val latestTimestamp: Instant? = null
)