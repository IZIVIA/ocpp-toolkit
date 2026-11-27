package com.izivia.ocpp.wamp.server.impl

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.Executor
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

/**
 * The settings of a WebSocket CSMS server, and the executor running the CALLs the charging stations send.
 *
 * The default executor is bounded by [maxConcurrentCalls] and [maxQueuedCalls], over all the stations of the server:
 * there is no fairness between them, so one station sending slow calls can take every thread, and the calls of the
 * other stations are then rejected. A rejected CALL is answered with one `InternalError` CALLERROR and reported once
 * to the server's `OcppErrorListener`, as a `TRANSPORT_ERROR` event with the `INTERNAL_ERROR` code.
 *
 * @property timeoutInMs how long a call to a station waits for its answer, and how long the server shutdown waits for
 * the inbound calls in flight to be answered.
 */
open class OcppWampServerSettings(
    val timeoutInMs: Long = 30_000,
    /**
     * How many inbound calls the default executor runs at once, each on its own thread. The default,
     * `32 × availableProcessors()`, is the worker thread count the toolkit gives Undertow. Ignored when
     * [buildCallsExecutor] is overridden.
     */
    val maxConcurrentCalls: Int = defaultMaxConcurrentCalls(),
    /**
     * How many inbound calls wait in the default executor for a thread once [maxConcurrentCalls] run; a call beyond
     * is rejected. Zero rejects a call as soon as every thread is busy. Ignored when [buildCallsExecutor] is
     * overridden.
     */
    val maxQueuedCalls: Int = DEFAULT_MAX_QUEUED_CALLS
) {
    // the constructor of the releases before the call bounds, kept for the compiled callers: its default value also
    // keeps the synthetic constructor they call for `OcppWampServerSettings()`
    @Deprecated("Kept for binary compatibility", level = DeprecationLevel.HIDDEN)
    constructor(timeoutInMs: Long = 30_000) : this(timeoutInMs, defaultMaxConcurrentCalls(), DEFAULT_MAX_QUEUED_CALLS)

    init {
        require(maxConcurrentCalls > 0) { "maxConcurrentCalls must be positive, was $maxConcurrentCalls" }
        require(maxQueuedCalls >= 0) { "maxQueuedCalls must not be negative, was $maxQueuedCalls" }
    }

    /**
     * Builds the executor of the inbound calls, once per server.
     *
     * By default, a pool of at most [maxConcurrentCalls] daemon threads named `ocpp-wamp-call-<n>`, idle ones ending
     * after a minute, with a queue of [maxQueuedCalls] calls; beyond, a call is rejected. The server owns this
     * executor and shuts it down at the end of its shutdown, waiting up to two more seconds for its threads to end
     * (the last second after interrupting them). A queued call that has not started by then is dropped without an
     * answer, its connection being closed anyway.
     *
     * An override runs the calls on an executor of its own: [maxConcurrentCalls] and [maxQueuedCalls] no longer
     * apply, and the executor is the caller's to shut down, as the server never does. Returning
     * `super.buildCallsExecutor()` unchanged keeps the default executor, which the server still shuts down; wrapping
     * it makes the caller its owner. An executor that throws at submission makes the server answer and report the
     * call as rejected.
     */
    open fun buildCallsExecutor(): Executor = ToolkitOwnedExecutor(maxConcurrentCalls, maxQueuedCalls)
}

private const val DEFAULT_MAX_QUEUED_CALLS = 1024

private fun defaultMaxConcurrentCalls() = 32 * Runtime.getRuntime().availableProcessors()

/**
 * The executor [OcppWampServerSettings.buildCallsExecutor] builds by default: the server that runs its calls shuts it
 * down, since nobody else holds it. Up to [maxConcurrentCalls] threads, then up to [maxQueuedCalls] waiting calls,
 * then a [java.util.concurrent.RejectedExecutionException].
 */
internal class ToolkitOwnedExecutor(maxConcurrentCalls: Int, maxQueuedCalls: Int) : ThreadPoolExecutor(
    maxConcurrentCalls,
    maxConcurrentCalls,
    KEEP_ALIVE_SECONDS,
    TimeUnit.SECONDS,
    if (maxQueuedCalls == 0) SynchronousQueue() else ArrayBlockingQueue(maxQueuedCalls),
    callThreadFactory(),
    AbortPolicy()
) {
    init {
        allowCoreThreadTimeOut(true)
    }

    private companion object {
        const val KEEP_ALIVE_SECONDS = 60L

        // daemon: a call thread must not keep the JVM alive when the server is never shut down; a graceful end of
        // the calls comes from the in-flight drain of the server shutdown
        fun callThreadFactory(): ThreadFactory {
            val count = AtomicInteger()
            return ThreadFactory { task ->
                Thread(task, "ocpp-wamp-call-${count.incrementAndGet()}").apply { isDaemon = true }
            }
        }
    }
}
