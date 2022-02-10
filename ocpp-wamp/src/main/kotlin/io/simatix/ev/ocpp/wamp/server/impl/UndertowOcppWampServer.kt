package io.simatix.ev.ocpp.wamp.server.impl

import io.simatix.ev.ocpp.CSOcppId
import io.simatix.ev.ocpp.OcppVersion
import io.simatix.ev.ocpp.wamp.messages.WampMessage
import io.simatix.ev.ocpp.wamp.messages.WampMessageMeta
import io.simatix.ev.ocpp.wamp.server.OcppWampServer
import io.simatix.ev.ocpp.wamp.server.OcppWampServerHandler
import kotlinx.datetime.Clock
import org.http4k.server.Http4kServer
import org.http4k.server.asServer
import org.slf4j.LoggerFactory

class UndertowOcppWampServer(val port:Int, val ocppVersions:Set<OcppVersion>) : OcppWampServer {
    private val handlers = mutableListOf<OcppWampServerHandler>()
    private val selectedHandler = mutableMapOf<CSOcppId, OcppWampServerHandler>()
    private var server: Http4kServer? = null

    override fun start() {
        server = OcppWampServerApp(ocppVersions, { id -> selectedHandler[id]?:throw IllegalStateException()})
            .asServer(Undertow(
                port = port,
                enableHttp2 = true,
                acceptWebSocketPredicate = { exch ->
                    // search for an handler accepting this ocpp charging station, and memoize it in selectedHandler
                    OcppWsEndpoint.extractChargingStationOcppId(exch.requestURI)
                        ?.let { handlers.find { h-> h.accept(it) }?.let { h -> it to h } }
                        ?.also { selectedHandler[it.first] = it.second } != null },
                wsSubprotocols = ocppVersions.map { it.subprotocol }.toSet()
            )).start()
        logger.info("starting ocpp wamp server on port $port")
    }

    override fun stop() {
        server?.stop()
        server = null
    }

    override fun sendBlocking(ocppId: CSOcppId, message: WampMessage): WampMessage {
        TODO()
    }

    override fun register(handler: OcppWampServerHandler) {
        handlers.add(handler)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(UndertowOcppWampServer::class.java)
    }
}

// example only
fun main() {
    val server = UndertowOcppWampServer(5000, setOf(OcppVersion.OCPP_1_6))
    server.register(object : OcppWampServerHandler {
        override fun accept(ocppId: CSOcppId): Boolean = listOf("TEST1", "TEST2").contains(ocppId)

        override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? =
                when (msg.action?.lowercase()) {
                    "heartbeat" ->
                        WampMessage.CallResult(msg.msgId, """{"currentTime":"${Clock.System.now()}"}""")
                    else -> {
                        println("unhandled action for message: ${msg.toJson()}")
                        WampMessage.CallError(msg.msgId, "{}")
                    }
                }
    })

    server.start()
}
