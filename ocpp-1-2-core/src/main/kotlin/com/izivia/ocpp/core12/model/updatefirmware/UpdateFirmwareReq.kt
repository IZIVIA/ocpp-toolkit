package com.izivia.ocpp.core12.model.updatefirmware

import com.izivia.ocpp.core12.model.Request
import kotlinx.datetime.Instant

data class UpdateFirmwareReq(
    val location: String,
    val retries: Int? = null,
    val retrieveDate: Instant,
    val retryInterval: Int? = null
): Request
