package com.izivia.ocpp.wamp.server.impl

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.server.OcppWampServer
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import com.izivia.ocpp.wamp.server.WsServerConfig
import com.izivia.ocpp.wamp.server.asServer
import io.undertow.server.HttpServerExchange
import kotlinx.datetime.Clock
import org.http4k.routing.RoutingWsHandler
import org.slf4j.LoggerFactory
import java.util.concurrent.ConcurrentHashMap

class UndertowOcppWampServer(
    val ocppVersions: Set<OcppVersion>,
    path: String = "ws",
    val settings: OcppWampServerSettings = OcppWampServerSettings(),
    private val listeners: EventsListeners = EventsListeners()
) : OcppWampServer {
    private val handlers = mutableListOf<OcppWampServerHandler>()
    private val selectedHandler = ConcurrentHashMap<CSOcppId, List<OcppWampServerHandler>>()
    private val wsApp: OcppWampServerApp
    private val serverConfig: WsServerConfig
    private val ocppWsEndpoint = OcppWsEndpoint(path)

    init {
        wsApp = OcppWampServerApp(
            ocppVersions = ocppVersions,
            handlers = { id -> selectedHandler[id] ?: throw IllegalStateException() },
            listeners = listeners,
            ocppWsEndpoint = ocppWsEndpoint,
            settings = settings
        )
        val handler = wsApp.newRoutingHandler()
        val acceptWebSocketPredicate: (HttpServerExchange) -> Boolean = { exch ->
            // search for an handler accepting this ocpp charging station, and memoize it in selectedHandler
            ocppWsEndpoint.extractChargingStationOcppId(exch.requestURI)?.let { ocppId ->
                handlers
                    .filter { h -> h.accept(ocppId) }
                    .also { selectedHandler[ocppId] = it }
            } != null
        }
        val wsSubprotocols = ocppVersions.map { it.subprotocol }.toSet()
        serverConfig = object : WsServerConfig {
            override val handler: RoutingWsHandler
                get() = handler
            override val acceptWebSocketPredicate: (HttpServerExchange) -> Boolean
                get() = acceptWebSocketPredicate
            override val wsSubprotocols: Set<String>
                get() = wsSubprotocols
        }
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
