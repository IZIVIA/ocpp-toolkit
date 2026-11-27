package com.izivia.ocpp.wamp.server

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.utils.OcppErrorListener
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.messages.WampMessageMetaHeaders
import com.izivia.ocpp.wamp.server.impl.AdmissionExecution
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
    /**
     * The WebSocket handler of the server, to serve with [admitWebSocket] and [wsSubprotocols] on the toolkit's
     * [Undertow].
     *
     * A [org.http4k.websocket.WsFilter] in front of it may wrap the request and the websocket, and may call the
     * consumer of the response later or on another thread, but must call this handler on the calling thread,
     * synchronously: that call reads what the admission of the handshake decided. A handler called later or on
     * another thread finds no admission: the connection is closed with status 1011, registers nothing, and an
     * [IllegalStateException] is raised on the thread that called the consumer.
     *
     * A consumer called later still has to subscribe before the station can send: the frames and the close the
     * station sends before the consumer subscribes reach no handler and are lost.
     */
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
 * It runs before any [OcppWampServerHandler.accept]. By default ([AdmissionExecution.Inline]) it runs on the
 * WebSocket IO thread, so it must not block; with [AdmissionExecution.Dispatched] it runs off the IO thread, within
 * a bounded number of concurrent admissions and a timeout.
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

/**
 * An Undertow server for this OCPP WebSocket server, listening on [port].
 *
 * @param admission where the admission of a charging station (the [ConnectionAuthorizer] and the selection of the
 * handler accepting it) runs: on the I/O thread by default, see [AdmissionExecution]
 */
fun OcppWampServer.asServer(
    port: Int = 8000,
    admission: AdmissionExecution = AdmissionExecution.Inline,
): Http4kServer {
    val config = this.config()
    return config.handler.asServer(Undertow(
        port,
        enableHttp2 = true,
        admitWebSocket = config.admitWebSocket,
        wsSubprotocols = config.wsSubprotocols,
        admission = admission,
    ))
}

/**
 * An Undertow server for this OCPP WebSocket server, listening on [port], whose admission runs on the I/O thread: the
 * signature of the releases before the admission, kept for the Java and compiled callers.
 */
// its default value keeps the synthetic function the callers compiled against the previous releases use
fun OcppWampServer.asServer(port: Int = 8000): Http4kServer = asServer(port, AdmissionExecution.Inline)

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
     * The checks run on the WebSocket IO thread for every upgrade request, so they must be fast and must not throw:
     * a check that throws fails the handshake with an HTTP 500.
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
     *
     * It runs on the WebSocket IO thread for every upgrade request, before the handshake, so it must be fast (no
     * blocking database or network call) and must not throw: an exception fails the handshake with an HTTP 500.
     */
    fun accept(ocppId: CSOcppId): Boolean

    /**
     * a wamp action handler
     *
     * An exception it throws, other than a [VirtualMachineError], is answered to the charging station with an
     * `InternalError` CALLERROR whose description is `"Internal error"` and whose details are `{}`, never with the
     * exception message, and is reported once to the `OcppErrorListener` of the server with the original cause.
     */
    fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage?
}
