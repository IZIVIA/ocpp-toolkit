package com.izivia.ocpp.wamp.client.autoreconnect

import com.izivia.ocpp.wamp.client.ConnectionListener
import com.izivia.ocpp.wamp.client.ConnectionState
import com.izivia.ocpp.wamp.client.OcppWampClient
import com.izivia.ocpp.wamp.client.WampOnActionHandler
import com.izivia.ocpp.wamp.messages.WampMessage
import org.slf4j.LoggerFactory
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/**
 * An ocpp wamp client that wraps another client to add auto reconnection feature.
 *
 * Once connect is called, this client makes the best efforts to keep the connection to server, until close is called.
 *
 * Make sure to call close to stop the auto reconnection process if not needed anymore.
 */
class AutoReconnectOcppWampClient(
    val client: OcppWampClient,
    val debugContext: String,
    val config: AutoReconnectConfig
) : OcppWampClient {

    private val lock = ReentrantLock()
    private var autoReconnectState: AutoReconnectState = AutoReconnectIdleState(debugContext)
    private var connectionListener: ConnectionListener? = null

    override val state: ConnectionState
        get() = when (autoReconnectState) {
            is AutoReconnectIdleState -> ConnectionState.DISCONNECTED
            is AutoReconnectConnectingState -> ConnectionState.CONNECTING
            is AutoReconnectConnectedState -> ConnectionState.CONNECTED
        }

    /**
     * Asynchronously attempt to connect to the remote wamp server.
     *
     * Once called, this client is in auto reconnect mode, and will always do its best to keep the connection
     * to the wamp server.
     *
     * You can be notified of connections and deconnection by providing a ConnectionListener as parameter.
     *
     * Note that this method does not throw an exception if connection fails, since the connection may later be
     * available. So you need to listen to events, or use a client without auto reconnect if you are not sure
     * of the wamp server url or port and rather want to test a connection.
     */
    override fun connect(listener: ConnectionListener?) {
        logger.info("[$debugContext] connect (current state=${autoReconnectState::class.simpleName})")
        lock.withLock {
            if (connectionListener != null) {
                throw IllegalStateException(
                    "[$debugContext] can't connect with connection listener" +
                        " while already connected with another listener"
                )
            }
            connectionListener = listener
        }
        autoReconnectState.onConnecting()
        when (autoReconnectState) {
            is AutoReconnectIdleState -> moveTo(autoReconnectState, connectingState())
            is AutoReconnectConnectedState, is AutoReconnectConnectingState -> {}
        }
    }

    /**
     * Closes the current connection to server (if connected) and stops any further auto reconnection attempt
     */
    override fun close() {
        logger.info("[$debugContext] close (current state=${autoReconnectState::class.simpleName})")
        autoReconnectState.onClose()
        when (autoReconnectState) {
            is AutoReconnectIdleState -> {}
            is AutoReconnectConnectedState, is AutoReconnectConnectingState ->
                moveTo(autoReconnectState, AutoReconnectIdleState(debugContext))
        }
        lock.withLock {
            connectionListener = null
            client.close()
        }
    }

    override fun sendBlocking(message: WampMessage): WampMessage {
        autoReconnectState.onBeforeCall()
        return client.sendBlocking(message)
    }

    override fun onAction(fn: WampOnActionHandler) {
        client.onAction(fn)
    }

    private fun emitConnected() {
        autoReconnectState.onConnected()
        when (autoReconnectState) {
            is AutoReconnectConnectedState -> {}
            is AutoReconnectIdleState, is AutoReconnectConnectingState ->
                moveTo(autoReconnectState, AutoReconnectConnectedState(debugContext))
        }
        connectionListener?.onConnected()
    }

    private fun emitConnectionLost(t: Throwable?) {
        autoReconnectState.onDisconnected()
        when (autoReconnectState) {
            is AutoReconnectIdleState, is AutoReconnectConnectingState -> {}
            is AutoReconnectConnectedState -> moveTo(autoReconnectState, connectingState(isReconnect = true))
        }
        connectionListener?.onConnectionLost(t)
    }

    private fun emitConnectionFailure(t: Throwable) {
        autoReconnectState.onDisconnected()
        when (autoReconnectState) {
            is AutoReconnectIdleState, is AutoReconnectConnectingState -> {}
            is AutoReconnectConnectedState -> moveTo(autoReconnectState, connectingState())
        }
        connectionListener?.onConnectionFailure(t)
    }

    private fun connectingState(isReconnect: Boolean = false) =
        AutoReconnectConnectingState(
            debugContext,
            config,
            { l -> client.connect(l) },
            object : ConnectionListener {
                override fun onConnected() {
                    emitConnected()
                }

                override fun onConnectionFailure(t: Throwable) {
                    emitConnectionFailure(t)
                }

                override fun onConnectionLost(t: Throwable?) {
                    emitConnectionLost(t)
                }
            },
            isReconnect = isReconnect
        )

    private fun moveTo(from: AutoReconnectState, to: AutoReconnectState) {
        lock.withLock {
            if (autoReconnectState != from) {
                logger.error(
                    "[$debugContext] auto reconnect state change not accepted:" +
                        " expected from=$from; " +
                        "current=$autoReconnectState; " +
                        "requested to move to: $to"
                )
                return
            }
            autoReconnectState.exit(to)
            autoReconnectState = to
            to.enter()
        }
    }
}

private val logger = LoggerFactory.getLogger(AutoReconnectOcppWampClient::class.java)
