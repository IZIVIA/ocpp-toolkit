package com.izivia.ocpp.wamp.client

/**
 * Tuning of an [OcppWampClient], the client-side counterpart of
 * [com.izivia.ocpp.wamp.server.impl.OcppWampServerSettings].
 */
data class OcppWampClientSettings(
    /**
     * Timeout of a call, and of the connection. With [autoReconnect], it also bounds the time spent
     * reconnecting before a call is sent.
     */
    val timeoutInMs: Long = 30_000,
    /** Whether the client reconnects by itself when the connection is lost. */
    val autoReconnect: Boolean = true,
    /** Initial delay before a reconnection attempt, doubled after each failed attempt. Ignored without [autoReconnect]. */
    val baseAutoReconnectDelayInMs: Long = 250,
) {
    init {
        require(timeoutInMs > 0) { "OcppWampClientSettings.timeoutInMs must be positive, got $timeoutInMs" }
        require(baseAutoReconnectDelayInMs >= 0) {
            "OcppWampClientSettings.baseAutoReconnectDelayInMs must not be negative, got $baseAutoReconnectDelayInMs"
        }
    }
}
