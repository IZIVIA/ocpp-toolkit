package com.izivia.ocpp.wamp.server.impl

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.wamp.messages.WampMessageMetaHeaders
import kotlin.time.Instant

/**
 * Callbacks notified of the WebSocket lifecycle of the charging stations.
 *
 * [onWsPongHandler] and [onWsPingHandler] report transport-level activity only: a station whose OCPP
 * application is stuck may still answer control frames. They never imply that an OCPP message was received.
 * They are invoked on the WebSocket IO thread and must not block: the connection can be neither replaced nor
 * unregistered while they run.
 *
 * A control frame is reported only if its connection is the current connection of the station: frames from a
 * replaced or closed connection are dropped. A reported frame is always notified before the
 * [onWsReconnectHandler] or [onWsCloseHandler] event that ends its connection.
 */
class EventsListeners(
    val onWsConnectHandler: (CSOcppId, WampMessageMetaHeaders) -> Unit = { _, _ -> },
    val onWsReconnectHandler: (CSOcppId, WampMessageMetaHeaders) -> Unit = { _, _ -> },
    val onWsCloseHandler: (CSOcppId, WampMessageMetaHeaders) -> Unit = { _, _ -> },
    /** Called when a WebSocket Pong frame is received from a charging station. */
    val onWsPongHandler: (WsControlFrameEvent) -> Unit = {},
    /** Called when a WebSocket Ping frame is received from a charging station, after the Pong reply is sent. */
    val onWsPingHandler: (WsControlFrameEvent) -> Unit = {}
)

/**
 * A WebSocket control frame received from a charging station.
 *
 * @param wsConnectionId identifies the WebSocket connection the frame was received on; it changes on every
 * (re)connection of the station
 * @param headers the headers of the WebSocket upgrade request of that connection, left out of [toString] as they may
 * carry credentials
 */
data class WsControlFrameEvent(
    val ocppId: CSOcppId,
    val wsConnectionId: String,
    val headers: WampMessageMetaHeaders,
    val receivedAt: Instant
) {
    override fun toString(): String =
        "WsControlFrameEvent(ocppId=$ocppId, wsConnectionId=$wsConnectionId, receivedAt=$receivedAt)"
}
