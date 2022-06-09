package com.izivia.ocpp.integration.model

import com.izivia.ocpp.OcppVersion

data class Settings(
    val ocppVersion: OcppVersion,
    val transportType: TransportEnum,
    val domain: String = "",
    val port: String = "",
    val path: String = "",
    val target : String = "ws://${domain}:${port}/${path}"
)
