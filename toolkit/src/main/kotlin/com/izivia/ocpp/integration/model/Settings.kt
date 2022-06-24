package com.izivia.ocpp.integration.model

import com.izivia.ocpp.transport.OcppVersion as OcppVersionTransport

data class Settings(
    val ocppVersion: OcppVersionTransport,
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
    val ocppVersion: Set<OcppVersionTransport>,
    val transportType: TransportEnum,
)
