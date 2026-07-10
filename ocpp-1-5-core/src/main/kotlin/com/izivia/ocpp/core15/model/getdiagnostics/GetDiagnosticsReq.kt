package com.izivia.ocpp.core15.model.getdiagnostics

import com.izivia.ocpp.core15.model.Request
import kotlin.time.Instant

data class GetDiagnosticsReq(
    val location: String,
    val retries: Int? = null,
    val retryInterval: Int? = null,
    val startTime: Instant? = null,
    val stopTime: Instant? = null
): Request
