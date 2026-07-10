package com.izivia.ocpp.core16.model.updatefirmware

import com.izivia.ocpp.core16.model.Request
import kotlin.time.Instant

data class UpdateFirmwareReq(
    val location: String,
    val retries: Int? = null,
    val retrieveDate: Instant,
    val retryInterval: Int? = null
) : Request
