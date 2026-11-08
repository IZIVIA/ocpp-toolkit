package com.izivia.ocpp.wamp.client

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.utils.OcppErrorListener
import com.izivia.ocpp.wamp.client.autoreconnect.AutoReconnectConfig
import com.izivia.ocpp.wamp.client.autoreconnect.AutoReconnectOcppWampClient
import com.izivia.ocpp.wamp.client.impl.OkHttpOcppWampClient
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.messages.WampMessageMetaHeaders
import org.http4k.core.Uri
import kotlin.time.Duration.Companion.milliseconds

interface OcppWampClient {
    fun connect(listener: ConnectionListener? = null)
    fun close()
    fun sendBlocking(message: WampMessage): WampMessage
    fun onAction(fn: WampOnActionHandler)

    val state: ConnectionState

    companion object {
        fun newClient(
            target: Uri,
            ocppId: CSOcppId,
            ocppVersion: OcppVersion,
            timeoutInMs: Long = 30_000,
            autoReconnect: Boolean = true,
            baseAutoReconnectDelayInMs: Long = 250,
            headers: WampMessageMetaHeaders = emptyList(),
            errorListener: OcppErrorListener? = null
        ) = OkHttpOcppWampClient(target, ocppId, ocppVersion, timeoutInMs, headers, errorListener = errorListener).let {
            if (autoReconnect) {
                AutoReconnectOcppWampClient(
                    it,
                    it.debugContext,
                    AutoReconnectConfig(
                        timeoutInMs.milliseconds,
                        baseAutoReconnectDelayInMs.milliseconds
                    )
                )
            } else {
                it
            }
        }
    }
}

typealias WampOnActionHandler = (WampMessageMeta, WampMessage) -> WampMessage?

enum class ConnectionState {
    CONNECTING, CONNECTED, DISCONNECTING, DISCONNECTED
}

interface ConnectionListener {
    fun onConnected() {}
    fun onConnectionFailure(t: Throwable) {}
    fun onConnectionLost(t: Throwable?) {}
}
