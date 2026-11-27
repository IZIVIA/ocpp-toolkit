package com.izivia.ocpp.wamp.core

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.joran.JoranConfigurator
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import strikt.api.expectThat
import strikt.assertions.containsExactly
import strikt.assertions.isEmpty
import strikt.assertions.isFalse
import strikt.assertions.isTrue
import strikt.assertions.map

/**
 * The level [FrameLog] emits raw frames at, and the logback configurations the README gives for the raw-frames
 * logger, applied to a context of their own.
 */
class FrameLogConfigurationTest {

    @Test
    fun `the frames logger at debug emits no frame`() {
        val events = framesEmittedAt(Level.DEBUG)

        expectThat(events).isEmpty()
    }

    @Test
    fun `the frames logger at trace emits the inbound and outbound frames`() {
        val events = framesEmittedAt(Level.TRACE)

        expectThat(events).map { it.formattedMessage }.containsExactly("[cs] [IN] frame-in", "[cs] [OUT] frame-out")
    }

    @Test
    fun `logback without configuration keeps the frames logger off`() {
        val context = LoggerContext()

        expectThat(context.getLogger(FrameLog.LOGGER_NAME).isTraceEnabled).isFalse()
        expectThat(context.getLogger("com.izivia.ocpp.wamp.server").isDebugEnabled).isTrue()
    }

    @Test
    fun `a root logger at DEBUG keeps the frames logger off`() {
        val context = configured("", rootLevel = "DEBUG")

        expectThat(context.getLogger(FrameLog.LOGGER_NAME).isTraceEnabled).isFalse()
    }

    @Test
    fun `the frames logger set to TRACE is on while the root logs at INFO`() {
        val context = configured(
            """<logger name="com.izivia.ocpp.wamp.frames" level="TRACE"/>""",
            rootLevel = "INFO"
        )

        expectThat(context.getLogger(FrameLog.LOGGER_NAME).isTraceEnabled).isTrue()
        expectThat(context.getLogger("com.izivia.ocpp.wamp.server").isDebugEnabled).isFalse()
    }

    /** Sets the frames logger to [level], logs one frame each way through [FrameLog] and returns what it emitted. */
    private fun framesEmittedAt(level: Level): List<ILoggingEvent> {
        val frames = LoggerFactory.getLogger(FrameLog.LOGGER_NAME) as Logger
        val previous = frames.level
        val appender = ListAppender<ILoggingEvent>().apply { start() }
        frames.level = level
        frames.addAppender(appender)
        try {
            FrameLog.inbound("[cs]", "frame-in")
            FrameLog.outbound("[cs]", "frame-out")
        } finally {
            frames.detachAppender(appender)
            frames.level = previous
            appender.stop()
        }
        return appender.list.toList()
    }

    private fun configured(loggerLine: String, rootLevel: String = "DEBUG"): LoggerContext {
        val xml = """<configuration>$loggerLine<root level="$rootLevel"/></configuration>"""
        return LoggerContext().also { context ->
            JoranConfigurator().apply { setContext(context) }.doConfigure(xml.byteInputStream())
        }
    }
}
