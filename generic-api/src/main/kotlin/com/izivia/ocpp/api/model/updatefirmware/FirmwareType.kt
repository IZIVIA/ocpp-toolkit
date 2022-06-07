package com.izivia.ocpp.api.model.updatefirmware

import kotlinx.datetime.Instant

data class FirmwareType(
    val location: String,
    val retrieveDateTime: Instant,
    val installDateTime: Instant? = null,
    val signingCertificate: String? = null,
    val signature: String? = null
)
