package com.izivia.ocpp.core16.model.signedupdatefirmware

import com.izivia.ocpp.core16.model.Request
import kotlin.time.Instant

data class SignedUpdateFirmwareReq(
    val firmware: FirmwareType,
    val retries: Int?,
    val requestId: Int,
    val retryInterval: Int?
) : Request

data class FirmwareType(
    val location: String,
    val retrieveDateTime: Instant,
    val installDateTime: Instant?,
    val signingCertificate: String,
    val signature: String
)
