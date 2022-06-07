package com.izivia.ocpp.core20.model.updatefirmware

data class UpdateFirmwareReq(
    val retries: Int? = null,
    val retryInterval: Int? = null,
    val requestId: Int,
    val firmware: FirmwareType
)
