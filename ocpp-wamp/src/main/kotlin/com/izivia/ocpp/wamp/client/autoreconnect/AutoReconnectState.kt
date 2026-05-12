package com.izivia.ocpp.wamp.client.autoreconnect

import com.izivia.ocpp.wamp.client.ConnectionListener
import org.slf4j.LoggerFactory
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Represents the state of an AutoReconnect client.
 *
 * It can be either:
 * - Idle, when the client is not supposed to be connected, either because connect has not been called
 *         or after a call to close
 * - Connecting, when the client is not connected, while it should be.
 *         In this mode, the client frequently attempts to reconnect
 * - Connected, when the client is properly connected to the server.
 */
internal sealed interface AutoReconnectState {
    fun enter() {}
    fun exit(to: AutoReconnectState) {}
    fun onConnecting() {}
    fun onConnected() {}
    fun onBeforeCall() {}
    fun onDisconnected() {}
    fun onClose() {}
}

internal class AutoReconnectConnectedState(val debugContext: String) : AutoReconnectState {
    override fun enter() {
        logger.info("[$debugContext] connected [auto-reconnect ON]")
    }

    override fun exit(to: AutoReconnectState) {
        when (to) {
            is AutoReconnectConnectingState ->
                logger.info("[$debugContext] disconnected [auto-reconnect ON]")

            is AutoReconnectIdleState ->
                logger.info("[$debugContext] disconnected [close]")

            is AutoReconnectConnectedState ->
                logger.info("[$debugContext] disconnected [new connection]")
        }
    }
}

internal class AutoReconnectConnectingState(
    private val debugContext: String,
    private val config: AutoReconnectConfig,
    doConnect: (l: ConnectionListener) -> Unit,
    connectionListener: ConnectionListener,
    private val isReconnect: Boolean = false
) : AutoReconnectState {
    private val handler = AutoReconnectHandler(
        debugContext,
        {
            try {
                doConnect(object : ConnectionListener {
                    // we handle only connection lost, connection failure will throw an exception
                    // and onConnected is handled by the AutoReconnectHandler

                    override fun onConnectionLost(t: Throwable?) {
                        connectionListener.onConnectionLost(t)
                    }
                })
                null
            } catch (t: Throwable) {
                t
            }
        },
        connectionListener,
        config.baseAutoReconnectDelay,
        config.minDelayBetweenAttempts
    )
    private val connectionLatch = CountDownLatch(1)

    override fun enter() {
        handler.checkIsCleanAndReady()
        if (connectionLatch.count != 1L) {
            throw IllegalStateException(
                "$debugContext - invalid state, connection count down latch is not 1: ${connectionLatch.count}"
            )
        }

        logger.info("[$debugContext] connecting [auto-reconnect ON]")
        if (isReconnect) {
            handler.planConnectionAttemptAfterDelay(config.baseAutoReconnectDelay)
        } else {
            handler.planConnectionAttemptNow()
        }
    }

    override fun onConnected() {
        connectionLatch.countDown()
    }

    override fun onBeforeCall() {
        handler.planConnectionAttemptNow()
        logger.debug("[$debugContext] waiting for reconnection for at most $config.callTimeout")
        if (!connectionLatch.await(config.callTimeout.inWholeMilliseconds, TimeUnit.MILLISECONDS)) {
            throw IOException("[$debugContext] currently not connected - retry later")
        }
    }

    override fun onClose() {
        handler.close()
    }
}

internal class AutoReconnectIdleState(val debugContext: String) : AutoReconnectState {
    override fun onBeforeCall() {
        throw IllegalStateException("[$debugContext] not connected")
    }
}

private val logger = LoggerFactory.getLogger(AutoReconnectState::class.java)
