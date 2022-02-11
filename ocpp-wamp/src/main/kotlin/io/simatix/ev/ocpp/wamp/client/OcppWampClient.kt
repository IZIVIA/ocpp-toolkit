package io.simatix.ev.ocpp.wamp.client

import io.simatix.ev.ocpp.CSOcppId
import io.simatix.ev.ocpp.OcppVersion
import io.simatix.ev.ocpp.wamp.client.impl.OcppWampClientImpl
import io.simatix.ev.ocpp.wamp.messages.WampMessage
import io.simatix.ev.ocpp.wamp.messages.WampMessageMeta
import org.http4k.core.Uri

interface OcppWampClient {
    fun connect()
    fun close()
    fun sendBlocking(message:WampMessage):WampMessage
    fun onAction(fn: WampOnActionHandler)

    companion object {
        fun newClient(target: Uri, ocppId:CSOcppId, ocppVersion: OcppVersion, timeoutInMs:Long = 30_000)
            = OcppWampClientImpl(target, ocppId, ocppVersion, timeoutInMs)
    }
}

typealias WampOnActionHandler = (WampMessageMeta, WampMessage) -> WampMessage?
