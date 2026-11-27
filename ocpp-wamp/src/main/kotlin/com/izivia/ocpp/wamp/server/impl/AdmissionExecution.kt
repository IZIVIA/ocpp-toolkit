package com.izivia.ocpp.wamp.server.impl

import java.time.Duration
import java.util.concurrent.Executor

/**
 * Where [Undertow] runs the admission of a WebSocket upgrade request, that is its `admitWebSocket` function: the
 * connection authorizer and the selection of the handlers accepting the charging station.
 *
 * The statuses of a refused handshake do not depend on it: 401 for a station that is not authorized, 404 when no
 * handler accepts it, 500 when the admission throws, and the subprotocol negotiation of an accepted one is unchanged.
 */
sealed interface AdmissionExecution {

    /**
     * The admission runs on the Undertow I/O thread that received the upgrade request, as it always did.
     *
     * This is the default. The admission callbacks must not block: while one runs, every other connection served by
     * that I/O thread, HTTP requests and WebSocket frames included, waits for it.
     */
    data object Inline : AdmissionExecution

    /**
     * The admission runs off the I/O thread, on [executor], or on Undertow's worker pool when [executor] is null, so
     * that a slow authorizer, such as one querying a database, delays neither the other connections nor the other
     * handshakes.
     *
     * - At most [maxConcurrent] admissions run at a time. A handshake arriving when they are all taken is answered
     *   at once with `503 Service Unavailable` and `Retry-After: 5` ([RETRY_AFTER_SECONDS]), and nothing is
     *   submitted. So is a handshake whose submission [executor] rejects.
     * - A handshake whose admission has not decided within [timeout] is answered with the same 503. Its admission
     *   still runs to its end, but its decision is dropped: the station is never upgraded nor notified.
     * - An admission keeps its slot until its callbacks return, not until its timeout: [maxConcurrent] bounds the
     *   threads actually blocked in admission. A callback that hangs keeps its slot for good, and once all slots are
     *   taken every handshake gets the 503: the server fails closed rather than piling up blocked threads.
     * - Once the server is stopping, no handshake is upgraded any more. `stop()` waits for the tasks of Undertow's
     *   worker pool, so with a null [executor] a callback that hangs also keeps `stop()` waiting.
     *
     * [executor] must run or reject (with a [java.util.concurrent.RejectedExecutionException]) every task it is
     * given: a policy that silently discards a task never gives its slot back. The toolkit never shuts it down; it is
     * owned by the caller. With a null [executor], no executor is created.
     *
     * @property maxConcurrent the number of admissions that may run at a time, strictly positive
     * @property timeout how long a handshake waits for its admission, at least 1 ms, the precision of Undertow's
     *   timers; a timeout longer than about 73 years is treated as 73 years
     * @property executor where the admissions run, or null for Undertow's worker pool
     */
    data class Dispatched(
        val maxConcurrent: Int,
        val timeout: Duration,
        val executor: Executor? = null,
    ) : AdmissionExecution {
        init {
            require(maxConcurrent > 0) { "maxConcurrent must be strictly positive: $maxConcurrent" }
            require(timeout >= Duration.ofMillis(1)) { "timeout must be at least 1 ms: $timeout" }
        }

        companion object {
            /** The `Retry-After` delay, in seconds, of a handshake refused for overload, rejection or timeout. */
            const val RETRY_AFTER_SECONDS: Int = 5
        }
    }
}
