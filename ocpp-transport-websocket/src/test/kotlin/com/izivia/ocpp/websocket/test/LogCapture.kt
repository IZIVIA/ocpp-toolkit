package com.izivia.ocpp.websocket.test

import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
import org.slf4j.LoggerFactory
import strikt.api.expectThat
import strikt.assertions.all
import strikt.assertions.contains

/** The logger of raw frames, which logs them as received on purpose, for a diagnosis. */
private const val FRAMES = "com.izivia.ocpp.wamp.frames"

/**
 * The log events of the toolkit's regular loggers emitted while [block] ran, read from an appender on the root logger:
 * the raw frames logger is left out.
 */
internal fun withToolkitLogs(block: () -> Unit): List<ILoggingEvent> {
    val root = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME) as Logger
    val appender = ListAppender<ILoggingEvent>().apply { start() }
    root.addAppender(appender)
    try {
        block()
        return synchronized(appender) { appender.list.toList() }
            .filter { it.loggerName.startsWith("com.izivia") && it.loggerName != FRAMES }
    } finally {
        root.detachAppender(appender)
        appender.stop()
    }
}

/** Expects that no event holds a raw line break, which would let a peer forge a log line. */
internal fun expectNoRawLineBreak(events: List<ILoggingEvent>) {
    expectThat(events).all {
        get { formattedMessage }.not().contains("\r")
        get { formattedMessage }.not().contains("\n")
    }
}
