package com.izivia.ocpp.integration.model

import com.izivia.ocpp.http.SoapClientSettings
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.utils.toUriHost
import com.izivia.ocpp.wamp.server.impl.EventsListeners
import com.izivia.ocpp.wamp.server.impl.OcppWampServerSettings
import java.util.*

data class Settings(
    val ocppVersion: OcppVersion,
    val transportType: TransportEnum,
    val domain: String = "localhost",
    val port: String = "8080",
    val path: String = "",
    val target: String = when (transportType) {
        TransportEnum.WEBSOCKET -> "ws://${domain.toUriHost()}:$port/$path"
        TransportEnum.SOAP -> "http://${domain.toUriHost()}:$port/$path"
    },
    /**
     * Embedded HTTP server of the charge point, advertised to the CSMS in the WS-Addressing `From`
     * header. Required for SOAP, ignored for WebSocket.
     */
    val soapClient: SoapClientSettings? = null,
    /**
     * Whether the OCPP 1.6 charge point speaks the OCPP 1.6-J Security Whitepaper.
     * Ignored for the other OCPP versions.
     */
    val ocpp16SecurityExtensions: Boolean = false,
    val newMessageId: () -> String = { UUID.randomUUID().toString() },
)

data class CSMSSettings(
    val servers: List<ServerSetting>,
)

data class ServerSetting(
    val port: Int,
    val path: String = "",
    val ocppVersion: Set<OcppVersion>,
    val transportType: TransportEnum,
    val newMessageId: () -> String = { UUID.randomUUID().toString() },
    val wampSettings: OcppWampServerSettings = OcppWampServerSettings(),
    var listeners: EventsListeners = EventsListeners()
)
