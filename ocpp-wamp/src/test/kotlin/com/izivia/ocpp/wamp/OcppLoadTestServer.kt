package com.izivia.ocpp.wamp

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.server.OcppWampServer
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import kotlinx.datetime.Clock

fun main() {
    val port = 12345
    val server = OcppWampServer.newServer(port, setOf(OcppVersion.OCPP_1_6, OcppVersion.OCPP_2_0))
    server.register(object : OcppWampServerHandler {
        override fun accept(ocppId: CSOcppId): Boolean = true

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
