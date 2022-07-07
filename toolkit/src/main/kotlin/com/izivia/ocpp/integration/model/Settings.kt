package com.izivia.ocpp.integration.model

import com.izivia.ocpp.transport.OcppVersion
import java.util.*

data class Settings(
    val ocppVersion: OcppVersion,
    val transportType: TransportEnum,
    val domain: String = "localhost",
    val port: String = "8080",
    val path: String = "",
    val target: String = when (transportType) {
        TransportEnum.WEBSOCKET -> "ws://${domain}:${port}/${path}"
        TransportEnum.SOAP -> "http://$domain:$port/$path"
    },
    val clientPort: Int? = null,
    val clientPath: String? = null
)

data class CSMSSettings(
    val servers: List<ServerSetting>,
)

data class ServerSetting(
    val port: Int,
    val path: String = "",
    val ocppVersion: Set<OcppVersion>,
    val transportType: TransportEnum,
    val newMessageId: () -> String = { UUID.randomUUID().toString() }
)
