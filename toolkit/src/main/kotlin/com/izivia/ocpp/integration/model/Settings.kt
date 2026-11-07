package com.izivia.ocpp.integration.model

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.http.SoapClientSettings
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.utils.OcppErrorListener
import com.izivia.ocpp.utils.toUriHost
import com.izivia.ocpp.wamp.client.OcppWampClientSettings
import com.izivia.ocpp.wamp.server.ConnectionAuthorizer
import com.izivia.ocpp.wamp.server.impl.EventsListeners
import com.izivia.ocpp.wamp.server.impl.OcppWampServerSettings
import java.time.ZoneId
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
    /**
     * Call timeout and auto-reconnect of the WebSocket client.
     * Applies to [TransportEnum.WEBSOCKET] only and is ignored for [TransportEnum.SOAP].
     */
    val wampSettings: OcppWampClientSettings = OcppWampClientSettings(),
)

data class CSMSSettings(
    val servers: List<ServerSetting>,
) {
    init {
        // the first WebSocket endpoint claiming an upgrade request decides: a second one on the same path and port
        // would never admit a station, and its authorizer would be ignored
        servers.filter { it.transportType == TransportEnum.WEBSOCKET }
            .groupBy { it.port to it.path.trim('/') }
            .filterValues { it.size > 1 }
            .keys.firstOrNull()
            ?.let { (port, path) ->
                throw IllegalArgumentException("several WebSocket servers share the path '$path' on port $port")
            }
    }
}

data class ServerSetting(
    val port: Int,
    val path: String = "",
    val ocppVersion: Set<OcppVersion>,
    val transportType: TransportEnum,
    val newMessageId: () -> String = { UUID.randomUUID().toString() },
    /**
     * Call timeout and calls executor of the WebSocket server.
     * Applies to [TransportEnum.WEBSOCKET] only and is ignored for [TransportEnum.SOAP], whose
     * requests run on the HTTP server's worker threads (see [com.izivia.ocpp.http.OcppSoapServerTransport]).
     */
    val wampSettings: OcppWampServerSettings = OcppWampServerSettings(),
    var listeners: EventsListeners = EventsListeners(),
    /** Observes the failures while processing OCPP messages, whatever the [transportType]. */
    val errorListener: OcppErrorListener? = null,
    /**
     * Decides from the upgrade request whether a charging station may connect, for instance from its
     * HTTP Basic authentication (see [ConnectionAuthorizer]).
     * Applies to [TransportEnum.WEBSOCKET] only and is ignored for [TransportEnum.SOAP].
     */
    val authorizer: ConnectionAuthorizer = ConnectionAuthorizer.ALLOW_ALL,
    /**
     * Zone in which to read the timestamps without timezone sent by a charging station; `null` refuses them.
     * Applies to [TransportEnum.WEBSOCKET] only and is ignored for [TransportEnum.SOAP].
     * See [com.izivia.ocpp.websocket.WebsocketServer].
     */
    val timestampZoneFallback: (CSOcppId) -> ZoneId? = { ZoneId.of("UTC") },
)
