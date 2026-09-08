package com.izivia.ocpp.wamp.client.autoreconnect

import kotlin.time.Instant
import org.slf4j.LoggerFactory
import java.util.concurrent.ScheduledFuture
import kotlin.time.Duration

/**
 * Represents an attempt to connect to a Wamp server
 * The attempt follows a state lifecycle:
 * - Queued - when the attempt is initialled created
 * - Planned - when the attempt is planned to be performed at a schedule instant
 * - Ongoing - when the attempt is being performed
 * - Successful - when the attempt has resulted to a a successful connection
 * - Failed - when the attempt has failed, and another attempt is needed
 * - Cancelled - when the attempt has been cancelled, usually in favor of another attempt
 *           (or because the client is closed)
 */
internal class ConnectionAttempt private constructor(
    val debugContext: String,
    val attemptCount: Int,
    val plannedAt: Instant, // at what time was this time planned, ie when it was created
    val scheduledAt: Instant, // at what time this attempt is scheduled to be performed
    val state: ConnectionAttemptState,
    val stateHistory: List<ConnectionAttemptState>
) {

    private constructor(
        debugContext: String,
        attemptCount: Int,
        plannedAt: Instant,
        scheduledAt: Instant,
        state: ConnectionAttemptState
    ) :
        this(debugContext, attemptCount, plannedAt, scheduledAt, state, listOf(state))

    constructor(
        debugContext: String,
        attemptCount: Int,
        plannedAt: Instant, // at what time was this time planned, ie when it was created
        scheduledAt: Instant
    ) :
        this(debugContext, attemptCount, plannedAt, scheduledAt, QueuedConnectionAttemptState())

    fun cancel(): ConnectionAttempt {
        if (state is PlannedConnectionAttemptState) {
            state.future.cancel(false)
        }
        return moveTo(CancelledConnectionAttemptState())
    }

    fun moveTo(state: ConnectionAttemptState) =
        ConnectionAttempt(debugContext, attemptCount, plannedAt, scheduledAt, state, stateHistory + state)
            .also { logger.info("[$debugContext] connection attempt $attemptCount => ${state::class.simpleName}") }

    fun plan(future: ScheduledFuture<*>) =
        checkStateToMove<QueuedConnectionAttemptState>("plan")
            .let { moveTo(PlannedConnectionAttemptState(future)) }

    fun ongoing(startedAt: Instant) =
        checkStateToMove<PlannedConnectionAttemptState>("ongoing")
            .let { moveTo(OngoingConnectionAttemptState(startedAt)) }

    fun success(successAt: Instant) =
        checkStateToMove<OngoingConnectionAttemptState>("success")
            .let { moveTo(SuccessfulConnectionAttemptState(successAt, successAt - it.startedAt)) }

    fun failed(failedAt: Instant, t: Throwable) =
        checkStateToMove<OngoingConnectionAttemptState>("failed")
            .let {
                moveTo(
                    FailedConnectionAttemptState(
                        failedAt,
                        failedAt - it.startedAt,
                        t::class.simpleName + ": " + (t.message ?: ""),
                        t
                    )
                )
            }

    inline fun <reified S> checkStateToMove(target: String): S = if (state is S) {
        state
    } else {
        throw IllegalStateException(
            "can't move to $target state from ${this.state::class.simpleName} - attempt=$this"
        )
    }

    override fun toString(): String {
        return "[$debugContext] ConnectionAttempt(" +
            "attemptCount=$attemptCount, " +
            "plannedAt=$plannedAt, " +
            "scheduledAt=$scheduledAt, " +
            "state=$state, " +
            "stateHistory=$stateHistory)"
    }

    companion object {
        private val logger = LoggerFactory.getLogger(AutoReconnectHandler::class.java)
    }
}

internal sealed class ConnectionAttemptState {
    abstract val completed: Boolean // true if the attempt has been completed, successfully or not
}

internal class QueuedConnectionAttemptState : ConnectionAttemptState() {
    override val completed: Boolean
        get() = false
}

internal data class PlannedConnectionAttemptState(
    val future: ScheduledFuture<*> // the future used to track this attempt
) : ConnectionAttemptState() {
    override val completed: Boolean
        get() = false
}

internal class CancelledConnectionAttemptState : ConnectionAttemptState() {
    override val completed: Boolean
        get() = false
}

internal data class OngoingConnectionAttemptState(
    val startedAt: Instant
) : ConnectionAttemptState() {
    override val completed: Boolean
        get() = false
}

internal data class SuccessfulConnectionAttemptState(
    val connectedAt: Instant,
    val duration: Duration
) : ConnectionAttemptState() {
    override val completed: Boolean
        get() = true
}

internal data class FailedConnectionAttemptState(
    val failedAt: Instant,
    val duration: Duration,
    val errorMessage: String,
    val error: Throwable?
) : ConnectionAttemptState() {
    override val completed: Boolean
        get() = true
}
