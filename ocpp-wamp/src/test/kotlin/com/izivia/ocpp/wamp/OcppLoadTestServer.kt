package com.izivia.ocpp.wamp

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.server.OcppWampServer
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import com.izivia.ocpp.wamp.server.asServer
import kotlinx.datetime.Clock
import java.net.ServerSocket

fun getFreePort(): Int =
    ServerSocket(0).use { it.localPort }

fun main() {
    val port = getFreePort()
    val transport = OcppWampServer.newServer(setOf(OcppVersion.OCPP_1_6, OcppVersion.OCPP_2_0))
    val server = transport.asServer(port)
    transport.register(object : OcppWampServerHandler {
        override fun accept(ocppId: CSOcppId): Boolean = true

        override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? =
            when (msg.action?.lowercase()) {
                "heartbeat" ->
                    WampMessage.CallResult(msg.msgId, """{"currentTime":"${Clock.System.now()}"}""")
                else -> {
                    println("unhandled action for message: ${msg.toJson()}")
                    WampMessage.CallError(
                        msg.msgId,
                        MessageErrorCode.NOT_SUPPORTED,
                        "",
                        "{}"
                    )
                }
            }
    })
    server.start()
}
