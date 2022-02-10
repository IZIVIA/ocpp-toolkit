package io.simatix.ev.ocpp.wamp.client

import io.simatix.ev.ocpp.wamp.messages.WampMessage

interface OcppWampClient {
    fun sendBlocking(message:WampMessage):WampMessage
}
