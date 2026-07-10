package com.izivia.ocpp.core15.model.updatefirmware

import com.izivia.ocpp.core15.model.Request
import kotlin.time.Instant

data class UpdateFirmwareReq(
    val location: String,
    val retries: Int? = null,
    val retrieveDate: Instant,
    val retryInterval: Int? = null
): Request
