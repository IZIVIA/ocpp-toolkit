package com.izivia.ocpp.api.model.getlog

import kotlinx.datetime.Instant

data class LogParametersType(
    val remoteLocation: String,
    val oldestTimestamp: Instant? = null,
    val latestTimestamp: Instant? = null
)