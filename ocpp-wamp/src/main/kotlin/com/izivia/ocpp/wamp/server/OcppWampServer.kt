package com.izivia.ocpp.wamp.server

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.utils.OcppErrorListener
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.messages.WampMessageMetaHeaders
import com.izivia.ocpp.wamp.server.impl.EventsListeners
import com.izivia.ocpp.wamp.server.impl.OcppWampServerSettings
import com.izivia.ocpp.wamp.server.impl.Undertow
import com.izivia.ocpp.wamp.server.impl.UndertowOcppWampServer
import io.undertow.server.HttpServerExchange
import org.http4k.core.Headers
import org.http4k.core.Status
import org.http4k.routing.RoutingWsHandler
import org.http4k.server.Http4kServer
import org.http4k.server.asServer

interface WsServerConfig {
    val handler: RoutingWsHandler

    /**
     * Decides whether a WebSocket upgrade request is accepted, before the handshake.
     * Returns null when the request is not for this endpoint.
     */
    val admitWebSocket: (HttpServerExchange) -> WebSocketUpgradeDecision?
    val wsSubprotocols:Set<String>
}

/**
 * The answer to a WebSocket upgrade request: either the handshake goes on, or it fails with
 * [Refuse.status] and [Refuse.headers].
 */
sealed interface WebSocketUpgradeDecision {
    data object Accept : WebSocketUpgradeDecision
    data class Refuse(val status: Status, val headers: Headers = emptyList()) : WebSocketUpgradeDecision
}

/**
 * Decides from its upgrade request whether a charging station may connect, for instance by checking the
 * HTTP Basic authentication of OCPP security profiles 1 and 2.
 *
 * It runs before any [OcppWampServerHandler.accept], on the WebSocket IO thread, so it must not block.
 * A station it refuses, or for which it throws, gets an HTTP 401 and is never registered nor notified.
 */
fun interface ConnectionAuthorizer {
    fun authorize(request: ConnectionRequest): Boolean

    companion object {
        val ALLOW_ALL = ConnectionAuthorizer { _ -> true }
    }
}

/**
 * The upgrade request of a charging station, as a [ConnectionAuthorizer] sees it before the handshake.
 *
 * It is a class rather than parameters so that what an authorizer can check may grow, for instance with the
 * client certificate of security profile 3, without breaking the existing authorizers.
 */
class ConnectionRequest(
    val ocppId: CSOcppId,
    /** As they were received. They carry credentials, so they are never logged, nor shown by [toString]. */
    val headers: WampMessageMetaHeaders,
) {
    override fun toString() = "ConnectionRequest(ocppId=$ocppId)"
}

fun OcppWampServer.asServer(port: Int = 8000): Http4kServer {
    val config = this.config()
    return config.handler.asServer(Undertow(
        port,
        enableHttp2 = true,
        admitWebSocket = config.admitWebSocket,
        wsSubprotocols = config.wsSubprotocols,
    ))
}

interface OcppWampServer {

    fun config(): WsServerConfig

    /**
     * attempt a graceful shutdown of the server:
     * - do not accept connection any more
     * - do not accept new calls any more
     * - close opened connections when pending calls are done
     *
     * Pending calls are both the calls received from charging stations and the calls sent to them.
     * This blocks until they are answered, waiting at most the settings timeout for the received
     * calls, then at most the call timeout for each sent call.
     *
     * The shutdown is irreversible: the server rejects every connection afterwards.
     */
    fun shutdown()

    /**
     * Sends a WampMessage call to a ChargingStation, identified by its ocpp id.
     *
     * @param timeoutInMs if not specified, the timeout from settings will be used
     * @throws IllegalStateException if no such ChargingStation is currently connected to this server
     */
    fun sendBlocking(ocppId: CSOcppId, message: WampMessage, timeoutInMs: Long? = null): WampMessage

    /**
     * registers a wamp server handler on this server.
     *
     * It is used to check if ocpp id is accepted, and then to handle actions if charging station has been accepted by the handler.
     *
     * The checks are iterated in order, the first returning true will be used to handle the charging station.
     * if no handler accepts by returning true boolean, the connection is refused with an HTTP 404.
     * The handlers are only checked once the [ConnectionAuthorizer] of the server authorized the station.
     *
     * The handler which accepted the charging station is later used for action handling.
     *
     * Note that an handler cannot be unregistered.
     */
    fun register(handler: OcppWampServerHandler)

    /**
     * Get the charging station ocpp version from the ocpp id
     * @throws IllegalStateException if no such ChargingStation is currently connected to this server
     */
    fun getChargingStationOcppVersion(ocppId: CSOcppId): OcppVersion

    companion object {
        fun newServer(
            ocppVersions: Set<OcppVersion> = OcppVersion.values().toSet(),
            path: String = "ws",
            settings: OcppWampServerSettings = OcppWampServerSettings(),
            listeners: EventsListeners = EventsListeners(),
            errorListener: OcppErrorListener? = null,
            authorizer: ConnectionAuthorizer = ConnectionAuthorizer.ALLOW_ALL
        ) = UndertowOcppWampServer(ocppVersions, path, settings, listeners, errorListener, authorizer)
    }
}

interface OcppWampServerHandler {
    /**
     * check for ocpp id existence
     *
     * this is used by server to accept connection for a given ocpp id.
     */
    fun accept(ocppId: CSOcppId): Boolean

    /**
     * a wamp action handler
     */
    fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage?
}
