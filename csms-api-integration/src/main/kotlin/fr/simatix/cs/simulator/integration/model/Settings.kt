package fr.simatix.cs.simulator.integration.model

import io.simatix.ev.ocpp.OcppVersion

data class Settings(
    val ocppVersion: OcppVersion,
    val transportType: TransportEnum,
    val url: String = "",
    val port: String = "",
    val path: String = "",
    val target : String = "ws://${url}:${port}/${path}"
)