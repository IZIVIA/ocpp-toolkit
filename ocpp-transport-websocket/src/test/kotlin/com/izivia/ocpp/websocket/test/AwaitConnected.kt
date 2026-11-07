package com.izivia.ocpp.websocket.test

import com.izivia.ocpp.wamp.server.impl.NoConnectionException

/**
 * Retries [action] until the charging station connection is registered server-side, which sendBlocking reports with
 * a NoConnectionException. Any other failure is rethrown: the call may already have been sent.
 */
internal fun <T> awaitConnected(timeoutMs: Long = 5_000, action: () -> T): T {
    val deadline = System.currentTimeMillis() + timeoutMs
    while (true) {
        try {
            return action()
        } catch (e: NoConnectionException) {
            if (System.currentTimeMillis() >= deadline) throw e
            Thread.sleep(50)
        }
    }
}
