package com.izivia.ocpp.wamp.client

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion
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
        /**
         * Equivalent to the [OcppWampClientSettings] overload, and validated the same way.
         *
         * @throws IllegalArgumentException if [timeoutInMs] is not positive or [baseAutoReconnectDelayInMs]
         * is negative. A `timeoutInMs` of 0 used to be accepted, but the client then gave up on the
         * connection and on every call without waiting.
         */
        fun newClient(
            target: Uri,
            ocppId: CSOcppId,
            ocppVersion: OcppVersion,
            timeoutInMs: Long = 30_000,
            autoReconnect: Boolean = true,
            baseAutoReconnectDelayInMs: Long = 250,
            headers: WampMessageMetaHeaders = emptyList()
        ): OcppWampClient = newClient(
            target,
            ocppId,
            ocppVersion,
            OcppWampClientSettings(timeoutInMs, autoReconnect, baseAutoReconnectDelayInMs),
            headers
        )

        fun newClient(
            target: Uri,
            ocppId: CSOcppId,
            ocppVersion: OcppVersion,
            settings: OcppWampClientSettings,
            headers: WampMessageMetaHeaders = emptyList()
        ): OcppWampClient =
            OkHttpOcppWampClient(target, ocppId, ocppVersion, settings.timeoutInMs, headers).let {
                if (settings.autoReconnect) {
                    AutoReconnectOcppWampClient(
                        it,
                        it.debugContext,
                        AutoReconnectConfig(
                            settings.timeoutInMs.milliseconds,
                            settings.baseAutoReconnectDelayInMs.milliseconds
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

/**
 * Thrown by [OcppWampClient.sendBlocking] when there is no connection to send the message on.
 *
 * Nothing has been sent when it is thrown, so the call can safely be retried once connected again.
 */
class NotConnectedException(message: String) : IllegalStateException(message)
