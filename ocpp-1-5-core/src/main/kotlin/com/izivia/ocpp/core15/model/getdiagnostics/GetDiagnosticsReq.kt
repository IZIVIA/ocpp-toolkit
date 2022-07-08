package com.izivia.ocpp.core15.model.getdiagnostics

import kotlinx.datetime.Instant

data class GetDiagnosticsReq(
    val location: String,
    val retries: Int? = null,
    val retryInterval: Int? = null,
    val startTime: Instant? = null,
    val stopTime: Instant? = null
)
