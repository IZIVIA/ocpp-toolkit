package io.simatix.ev.ocpp.wamp.client

import io.simatix.ev.ocpp.CSOcppId
import io.simatix.ev.ocpp.OcppVersion
import io.simatix.ev.ocpp.wamp.client.impl.KtorOcppWampClientImpl
import io.simatix.ev.ocpp.wamp.messages.WampMessage
import io.simatix.ev.ocpp.wamp.messages.WampMessageMeta
import org.http4k.core.Uri

interface OcppWampClient {
    suspend fun connect(onConnect: ()->Unit = {})
    suspend fun close()
    suspend fun sendBlocking(message:WampMessage):WampMessage
    suspend fun onAction(fn: WampOnActionHandler)

    companion object {
        fun newClient(target: Uri, ocppId:CSOcppId, ocppVersion: OcppVersion, timeoutInMs:Long = 30_000)
            = KtorOcppWampClientImpl(target, ocppId, ocppVersion, timeoutInMs)
    }
}

typealias WampOnActionHandler = (WampMessageMeta, WampMessage) -> WampMessage?
