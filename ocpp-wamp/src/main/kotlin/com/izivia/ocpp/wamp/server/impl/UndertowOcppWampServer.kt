package com.izivia.ocpp.wamp.server.impl

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.utils.OcppErrorListener
import com.izivia.ocpp.utils.escapedForLog
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.server.ConnectionAuthorizer
import com.izivia.ocpp.wamp.server.ConnectionRequest
import com.izivia.ocpp.wamp.server.OcppWampServer
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import com.izivia.ocpp.wamp.server.WebSocketUpgradeDecision
import com.izivia.ocpp.wamp.server.WsServerConfig
import com.izivia.ocpp.wamp.server.asServer
import io.undertow.server.HttpServerExchange
import io.undertow.util.AttachmentKey
import kotlin.time.Clock
import org.http4k.core.Request
import org.http4k.core.Status
import org.http4k.routing.RoutingWsHandler
import org.http4k.websocket.Websocket
import org.http4k.websocket.WsStatus
import org.slf4j.LoggerFactory
import java.util.concurrent.CopyOnWriteArrayList

class UndertowOcppWampServer(
    val ocppVersions: Set<OcppVersion>,
    path: String = "ws",
    val settings: OcppWampServerSettings = OcppWampServerSettings(),
    private val listeners: EventsListeners = EventsListeners(),
    private val errorListener: OcppErrorListener? = null,
    private val authorizer: ConnectionAuthorizer = ConnectionAuthorizer.ALLOW_ALL
) : OcppWampServer {
    // registered while handshakes are admitted on the I/O threads
    private val handlers = CopyOnWriteArrayList<OcppWampServerHandler>()

    // one key per server: several servers may share one Undertow, and each reads only the handshakes it admitted
    private val admissionKey = AttachmentKey.create(HandshakeAdmission::class.java)
    private val wsApp: OcppWampServerApp
    private val serverConfig: WsServerConfig
    private val ocppWsEndpoint = OcppWsEndpoint(path)

    init {
        wsApp = OcppWampServerApp(
            ocppVersions = ocppVersions,
            listeners = listeners,
            ocppWsEndpoint = ocppWsEndpoint,
            settings = settings,
            clock = Clock.System,
            errorListener = errorListener,
            admissionOf = ::admissionOf
        )
        val handler = wsApp.newRoutingHandler()
        val wsSubprotocols = ocppVersions.map { it.subprotocol }.toSet()
        serverConfig = object : WsServerConfig {
            override val handler: RoutingWsHandler
                get() = handler
            override val admitWebSocket: (HttpServerExchange) -> WebSocketUpgradeDecision?
                get() = ::admit
            override val wsSubprotocols: Set<String>
                get() = wsSubprotocols
        }
    }

    private fun admit(exch: HttpServerExchange): WebSocketUpgradeDecision? {
        val ocppId = ocppWsEndpoint.extractChargingStationOcppId(exch.requestURI) ?: return null
        // authorized first, so that an unknown station gets the 401 of bad credentials rather than a 404 telling
        // its id is unknown: how long the answer takes is up to the authorizer
        if (!isAuthorized(ocppId, exch)) return UNAUTHORIZED

        // search for the handlers accepting this ocpp charging station, carried by this handshake to its connection
        val acceptingHandlers = handlers.filter { h -> h.accept(ocppId) }
        if (acceptingHandlers.isEmpty()) {
            logger.info("[${ocppId.escapedForLog()}] connection refused - no handler accepts it")
            return WebSocketUpgradeDecision.Refuse(Status.NOT_FOUND)
        }
        exch.putAttachment(admissionKey, HandshakeAdmission(ocppId, acceptingHandlers))
        return WebSocketUpgradeDecision.Accept
    }

    // the admission of the handshake opening on this thread, which only a handshake admitted by this server carries;
    // without it the socket is closed, as a handler called off that thread would otherwise leave it open and unserved
    private fun admissionOf(request: Request): (Websocket) -> HandshakeAdmission {
        val admission = OpeningHandshake.attachment(admissionKey)
        return { ws ->
            admission ?: run {
                ws.close(NOT_ADMITTED)
                throw IllegalStateException(
                    "connection opened without the admission of its handshake - " +
                        request.uri.path.escapedForLog(MAX_LOGGED_PATH)
                )
            }
        }
    }

    // fails closed, and logs neither the headers nor the exception message, which may echo them
    private fun isAuthorized(ocppId: CSOcppId, exch: HttpServerExchange): Boolean {
        val headers = exch.requestHeaders.flatMap { hv -> hv.map { hv.headerName.toString() to it } }
        val authorized = try {
            authorizer.authorize(ConnectionRequest(ocppId, headers))
        } catch (e: VirtualMachineError) {
            throw e
        } catch (e: Throwable) {
            // Throwable on purpose, as in notifySafely: a library missing at runtime fails with a NoClassDefFoundError
            logger.warn("[${ocppId.escapedForLog()}] connection refused - authorizer failed with ${e::class.qualifiedName}")
            return false
        }
        if (!authorized) {
            logger.info("[${ocppId.escapedForLog()}] connection refused - not authorized")
        }
        return authorized
    }

    override fun config(): WsServerConfig {
        return serverConfig
    }

    override fun shutdown() {
        logger.info("shutting down ocpp wamp server")
        wsApp.shutdown()
    }

    override fun sendBlocking(ocppId: CSOcppId, message: WampMessage, timeoutInMs: Long?): WampMessage =
        wsApp.sendBlocking(ocppId, message, timeoutInMs = timeoutInMs)

    override fun register(handler: OcppWampServerHandler) {
        handlers.add(handler)
    }

    override fun getChargingStationOcppVersion(ocppId: CSOcppId): OcppVersion =
        wsApp.getChargingStationOcppVersion(ocppId)

    companion object {
        private val logger = LoggerFactory.getLogger(UndertowOcppWampServer::class.java)

        private val UNAUTHORIZED = WebSocketUpgradeDecision.Refuse(
            Status.UNAUTHORIZED,
            listOf("WWW-Authenticate" to "Basic realm=\"OCPP\"")
        )

        // 1011, unexpected condition: a fixed reason, telling the station nothing of its path or headers
        private val NOT_ADMITTED = WsStatus(1011, "connection not admitted")
    }
}

// example only
fun main() {
    val transport = UndertowOcppWampServer(setOf(OcppVersion.OCPP_1_6))
    val server = transport.asServer(5000)
    transport.register(object : OcppWampServerHandler {
        override fun accept(ocppId: CSOcppId): Boolean = listOf("TEST1", "TEST2").contains(ocppId)

        override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? =
            when (msg.action?.lowercase()) {
                "heartbeat" ->
                    WampMessage.CallResult(msg.msgId, """{"currentTime":"${Clock.System.now()}"}""")

                else -> {
                    println("unhandled action for message: ${msg.toJson()}")
                    WampMessage.CallError(
                        msg.msgId,
                        MessageErrorCode.NOT_SUPPORTED,
                        "unhandled action for message",
                        """{"action":" ${msg.action}"}"""
                    )
                }
            }
    })

    server.start()
}

private const val MAX_LOGGED_PATH = 256
