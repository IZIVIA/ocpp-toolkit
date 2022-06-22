package com.izivia.ocpp.integration.model

import com.izivia.ocpp.OcppVersion

data class Settings(
    val ocppVersion: OcppVersion,
    val transportType: TransportEnum,
    val domain: String = "localhost",
    val port: String = "8080",
    val path: String = "",
    val target : String = when (transportType) {
        TransportEnum.WEBSOCKET -> "ws://${domain}:${port}/${path}"
        else -> ""
    })

data class CSMSSettings(
    val port: Int = 1234,
    val servers: List<ServerSetting>,
)
data class ServerSetting(
    val path: String = "",
    val ocppVersion: Set<OcppVersion>,
    val transportType: TransportEnum,
)
