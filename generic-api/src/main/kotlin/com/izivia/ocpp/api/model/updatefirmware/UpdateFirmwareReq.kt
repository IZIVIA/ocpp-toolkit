package com.izivia.ocpp.api.model.updatefirmware

import com.izivia.ocpp.api.model.Request

data class UpdateFirmwareReq(
    val retries: Int? = null,
    val retryInterval: Int? = null,
    val requestId: Int,
    val firmware: FirmwareType
): Request
