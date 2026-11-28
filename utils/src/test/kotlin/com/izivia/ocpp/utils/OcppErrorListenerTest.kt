package com.izivia.ocpp.utils

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.classic.spi.ThrowableProxyUtil
import ch.qos.logback.core.read.ListAppender
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import strikt.api.expectThat
import strikt.assertions.all
import strikt.assertions.contains
import strikt.assertions.containsExactly
import strikt.assertions.isEqualTo
import strikt.assertions.isNotEqualTo
import strikt.assertions.isNotNull
import strikt.assertions.single

class OcppErrorListenerTest {

    private val event = OcppErrorEvent(OcppErrorCategory.UNHANDLED_ACTION, ocppId = "CS1")

    @Test
    fun `should deliver the event to the listener`() {
        val received = mutableListOf<OcppErrorEvent>()
        val listener = OcppErrorListener { received.add(it) }

        listener.notifySafely(event)

        expectThat(received).containsExactly(event)
    }

    @Test
    fun `should compare events by value`() {
        val cause = IllegalStateException("failure")
        val same = OcppErrorEvent(OcppErrorCategory.HANDLER_ERROR, "CS1", "ocpp1.6", "Heartbeat", "msg-1", null, cause)
        val other = OcppErrorEvent(OcppErrorCategory.HANDLER_ERROR, "CS1", "ocpp1.6", "Heartbeat", "msg-2", null, cause)

        expectThat(same) {
            isEqualTo(OcppErrorEvent(OcppErrorCategory.HANDLER_ERROR, "CS1", "ocpp1.6", "Heartbeat", "msg-1", null, cause))
            get { hashCode() }.isEqualTo(
                OcppErrorEvent(OcppErrorCategory.HANDLER_ERROR, "CS1", "ocpp1.6", "Heartbeat", "msg-1", null, cause)
                    .hashCode()
            )
            isNotEqualTo(other)
        }
    }

    @Test
    fun `should describe every property`() {
        expectThat(OcppErrorEvent(OcppErrorCategory.UNHANDLED_ACTION, "CS1", "ocpp1.6", "Heartbeat", "msg-1").toString())
            .isEqualTo(
                "OcppErrorEvent(category=UNHANDLED_ACTION, ocppId=CS1, ocppVersion=ocpp1.6, action=Heartbeat, " +
                    "messageId=msg-1, errorCode=null, cause=null)"
            )
    }

    @Test
    fun `should isolate a listener throwing an exception`() {
        OcppErrorListener { throw IllegalStateException("listener failure") }.notifySafely(event)
    }

    @Test
    fun `should isolate a listener throwing an error`() {
        OcppErrorListener { throw NoClassDefFoundError("io/sentry/Sentry") }.notifySafely(event)
    }

    @Test
    fun `should log a failing listener without the messages of its exception nor of the event cause`() {
        val cause = IllegalStateException("cause $CAUSE_SENTINEL")
        val failing = OcppErrorEvent(OcppErrorCategory.PROTOCOL_ERROR, ocppId = "CS1", cause = cause)

        val events = withListenerLogs {
            OcppErrorListener { throw RuntimeException("listener $LISTENER_SENTINEL", it.cause) }.notifySafely(failing)
        }

        expectThat(events).single().and {
            get { level }.isEqualTo(Level.ERROR)
            get { formattedMessage }.contains("OCPP error listener failed on PROTOCOL_ERROR")
            get { formattedMessage }.contains(RuntimeException::class.java.name)
            get { throwableProxy }.isNotNull().get { ThrowableProxyUtil.asString(this) }.and {
                contains(RuntimeException::class.java.name)
                contains(IllegalStateException::class.java.name)
            }
        }
        listOf(LISTENER_SENTINEL, CAUSE_SENTINEL).forEach { sentinel ->
            expectThat(events).all {
                get { formattedMessage }.not().contains(sentinel)
                get { throwableProxy?.let { ThrowableProxyUtil.asString(it) }.orEmpty() }.not().contains(sentinel)
            }
        }
    }

    private fun withListenerLogs(block: () -> Unit): List<ILoggingEvent> {
        val logger = LoggerFactory.getLogger("com.izivia.ocpp.utils.OcppErrorListener") as Logger
        val appender = ListAppender<ILoggingEvent>().apply { start() }
        logger.addAppender(appender)
        try {
            block()
            return appender.list.toList()
        } finally {
            logger.detachAppender(appender)
            appender.stop()
        }
    }

    private companion object {
        const val LISTENER_SENTINEL = "SENTINEL-listener-168"
        const val CAUSE_SENTINEL = "SENTINEL-cause-168"
    }
}
