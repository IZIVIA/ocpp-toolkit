package com.izivia.ocpp.api.model.getdiagnostics

import com.izivia.ocpp.api.model.Request
import kotlin.time.Instant

/**
 * GetDiagnostics, an OCPP 1.x message: the three versions share this shape. It carries no request
 * id on the wire, so there is none here either.
 */
data class GetDiagnosticsReq(
    val location: String,
    val retries: Int? = null,
    val retryInterval: Int? = null,
    val startTime: Instant? = null,
    val stopTime: Instant? = null
) : Request
