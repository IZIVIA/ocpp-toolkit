package com.izivia.ocpp.integration.test

/**
 * Retries [action] until the charging station connection is registered server-side
 * (sendBlocking throws NoConnectionException, an IllegalStateException, while absent).
 */
internal fun <T> awaitConnected(timeoutMs: Long = 5_000, action: () -> T): T {
    val deadline = System.currentTimeMillis() + timeoutMs
    while (true) {
        try {
            return action()
        } catch (e: IllegalStateException) {
            if (System.currentTimeMillis() >= deadline) throw e
            Thread.sleep(50)
        }
    }
}
