package io.simatix.ev.ocpp.wamp.client

import io.simatix.ev.ocpp.wamp.messages.WampMessage
import io.simatix.ev.ocpp.wamp.messages.WampMessageMeta

interface OcppWampClient {
    fun connect()
    fun close()
    fun sendBlocking(message:WampMessage):WampMessage
    fun onAction(fn: WampOnActionHandler)
}

typealias WampOnActionHandler = (WampMessageMeta, WampMessage) -> WampMessage?
