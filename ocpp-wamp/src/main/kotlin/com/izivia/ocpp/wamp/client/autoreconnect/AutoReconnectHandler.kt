package com.izivia.ocpp.wamp.client.autoreconnect

import com.izivia.ocpp.wamp.client.ConnectionListener
import kotlin.time.Clock
import kotlin.time.Instant
import org.slf4j.LoggerFactory
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock
import kotlin.math.max
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

internal class AutoReconnectHandler(
    val debugContext: String,
    val tryToConnect: () -> Throwable?,
    val connectionListener: ConnectionListener,
    // initial delay before trying to reconnect. This delay will be doubled after each attempt, until successful
    val baseAutoReconnectDelay: Duration = 250.milliseconds,
    // minimum delay before trying to reconnect. This delay will prevent to make too much reconection attempts
    // when reconnection attempts are triggered by send calls
    val minDelayBetweenAttempts: Duration = 100.milliseconds
) {
    private val clock = Clock.System
    private val executor = Executors.newScheduledThreadPool(1)
    private val lock = ReentrantLock()
    private val lastConnectionAttempts: MutableList<ConnectionAttempt> = LinkedList()
    private var nextAttemptDelayInMs: Long = baseAutoReconnectDelay.inWholeMilliseconds
    private var closed: Boolean = false

    fun checkIsCleanAndReady() {
        if (!lastConnectionAttempts.isEmpty()) {
            throw IllegalStateException("[$debugContext] non clean state: $lastConnectionAttempts")
        }
        if (closed) {
            throw IllegalStateException("[$debugContext] auto reconnect handler closed")
        }
    }

    fun planConnectionAttemptNow() {
        planConnectionAttempt(clock.now())
    }

    fun planConnectionAttemptAfterDelay(delay: Duration) {
        planConnectionAttempt(clock.now() + delay)
    }

    fun planConnectionAttempt(scheduleAt: Instant, checkCurrentAttempt: Boolean = true) {
        withLock {
            val lastAttemptOrNull = lastConnectionAttempts.lastOrNull()
            if (checkCurrentAttempt) {
                lastAttemptOrNull?.also { currentAttempt ->
                    when (currentAttempt.state) {
                        is PlannedConnectionAttemptState -> {
                            if (currentAttempt.scheduledAt > scheduleAt) {
                                currentAttempt.cancel().also { updateAttempt(it) }
                            } else {
                                logger.info(
                                    "[$debugContext] ignoring call to plan connection attempt," +
                                        " another one is already planned before: $currentAttempt"
                                )
                                return
                            }
                        }

                        else -> {
                            logger.info(
                                "[$debugContext] there is an ongoing connection attempt," +
                                    " letting it finish and trigger another one asap if needed: $currentAttempt"
                            )
                            // this attempt is ongoing or done and will trigger a next attempt,
                            // we just need to adjust its delay

                            // 0 delay is immediate, next planning will take care of ensuring a minimum delay
                            // between attempts if needed
                            nextAttemptDelayInMs = 0
                            return
                        }
                    }
                }
            }

            val attemptCount = (lastAttemptOrNull?.attemptCount ?: 0) + 1

            val now = clock.now()
            val adjustedScheduledAt =
                listOfNotNull(
                    // at least minDelayBetweenAttempts since last completed attempt
                    lastConnectionAttempts.lastOrNull { it.state.completed }?.scheduledAt
                        ?.let { it + minDelayBetweenAttempts },
                    now + 5.milliseconds,
                    scheduleAt
                ).maxOrNull()!!

            val attempt = ConnectionAttempt(debugContext, attemptCount, now, adjustedScheduledAt).also {
                logger.info("[$debugContext] queuing new reconnection attempt $attemptCount")
                lastConnectionAttempts.add(it)
                if (lastConnectionAttempts.size > 3) {
                    lastConnectionAttempts.removeFirst()
                }
            }

            executor.schedule(
                {
                    try {
                        attemptToConnectAndPlanNextIfFailed(attempt)
                    } catch (e: Exception) {
                        logger.error(
                            "[$debugContext] attempt ${attempt.attemptCount} unexpected error $e" +
                                " - planning another one",
                            e
                        )
                        onAttemptFailed(attempt, now, e)
                    }
                },
                (adjustedScheduledAt - now).inWholeMilliseconds,
                TimeUnit.MILLISECONDS
            ).let { future ->
                attempt.plan(future).also { updateAttempt(it) }
            }
        }
    }

    private fun attemptToConnectAndPlanNextIfFailed(attempt: ConnectionAttempt) {
        withLock {
            val lastAttemptOrNull = lastConnectionAttempts.lastOrNull()
            if (closed) {
                logger.info("[$debugContext] cancelling (closed)")
                lastAttemptOrNull?.cancel()?.also { updateAttempt(it) }
                return
            }
            if (lastAttemptOrNull?.attemptCount != attempt.attemptCount) {
                logger.info("[$debugContext] replanning - not current attempt - ${lastAttemptOrNull?.attemptCount}")
                // current attempt has changed, replan
                planConnectionAttemptNow()
                return
            }
            lastAttemptOrNull.ongoing(clock.now()).also { updateAttempt(it) }
        }

        tryToConnect().also { t ->
            lock.withLock {
                if (closed) {
                    lastConnectionAttempts.lastOrNull()?.cancel()?.also { updateAttempt(it) }
                    return
                }
                val now = clock.now()
                if (t == null) {
                    currentAttempt(attempt).success(now).also { updateAttempt(it) }
                    connectionListener.onConnected()
                } else {
                    onAttemptFailed(attempt, now, t)
                }
            }
        }
    }

    private fun onAttemptFailed(attempt: ConnectionAttempt, now: Instant, t: Throwable) {
        currentAttempt(attempt).failed(now, t).also { updateAttempt(it) }

        planConnectionAttempt(now + nextAttemptDelayInMs.milliseconds, false)
        nextAttemptDelayInMs =
            max(baseAutoReconnectDelay.inWholeMilliseconds, nextAttemptDelayInMs) * 2
        connectionListener.onConnectionFailure(t)
    }

    private fun currentAttempt(attempt: ConnectionAttempt) =
        lastConnectionAttempts.firstOrNull { it.attemptCount == attempt.attemptCount }
            ?: throw IllegalStateException("no current attempt ${attempt.attemptCount}")

    private fun updateAttempt(attempt: ConnectionAttempt) {
        withLock {
            // attempt count is unique in the collection for a single instance of AutoReconnectHandler
            val index = lastConnectionAttempts.indexOfFirst { it.attemptCount == attempt.attemptCount }
            if (index == -1) {
                logger.warn(
                    "[$debugContext] connection attempt [${attempt.attemptCount}] not found" +
                        " in last connection attempts, aborting update.\n" +
                        "attempt=$attempt;\nhistory=$lastConnectionAttempts"
                )
            } else {
                if (index != lastConnectionAttempts.size - 1) {
                    logger.warn(
                        "[$debugContext] updating non current connection attempt [${attempt.attemptCount}]." +
                            " Current=${lastConnectionAttempts.lastOrNull()}"
                    )
                }
                lastConnectionAttempts[index] = attempt
                if (logger.isDebugEnabled) {
                    logger.debug("[$debugContext] attempt updated => $attempt")
                }
            }
        }
    }

    fun close() {
        withLock {
            closed = true
            lastConnectionAttempts.lastOrNull()?.cancel()?.also { updateAttempt(it) }
        }
    }

    inline fun <T> withLock(action: () -> T) = lock.withLock(action)

    companion object {
        private val logger = LoggerFactory.getLogger(AutoReconnectHandler::class.java)
    }
}
