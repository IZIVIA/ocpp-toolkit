package com.izivia.ocpp.wamp.client

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.wamp.client.impl.OkHttpOcppWampClient
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import org.http4k.core.Uri

interface OcppWampClient {
    fun connect()
    fun close()
    fun sendBlocking(message:WampMessage):WampMessage
    fun onAction(fn: WampOnActionHandler)

    companion object {
        fun newClient(target: Uri, ocppId:CSOcppId, ocppVersion: OcppVersion, timeoutInMs:Long = 30_000)
            = OkHttpOcppWampClient(target, ocppId, ocppVersion, timeoutInMs)
    }
}

typealias WampOnActionHandler = (WampMessageMeta, WampMessage) -> WampMessage?
