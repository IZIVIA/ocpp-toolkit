package com.izivia.ocpp.wamp.core

import com.izivia.ocpp.utils.escapedForLog
import com.izivia.ocpp.wamp.messages.WampMessage
import org.slf4j.LoggerFactory

/**
 * The only logger the toolkit sends raw OCPP frames to, at TRACE: the regular loggers emit metadata only.
 *
 * Frames carry payloads, such as id tags, and the toolkit cannot tell what else is sensitive in them, so this logger
 * is meant to be enabled on purpose, for a diagnosis. TRACE keeps it off by default: logback without configuration
 * and a root logger at DEBUG both leave it out, so enabling it means setting this logger to TRACE. It inherits its
 * level, so any ancestor set to TRACE enables it too: `com.izivia.ocpp.wamp`, `com.izivia.ocpp` or the root.
 */
internal object FrameLog {
    const val LOGGER_NAME = "com.izivia.ocpp.wamp.frames"

    private val logger = LoggerFactory.getLogger(LOGGER_NAME)

    /** Logs the raw [frame] received on the connection of [logContext]. */
    fun inbound(logContext: String, frame: String) {
        if (logger.isTraceEnabled) {
            logger.trace("{} [IN] {}", logContext, frame)
        }
    }

    /** Logs the raw [frame] sent on the connection of [logContext]. */
    fun outbound(logContext: String, frame: String) {
        if (logger.isTraceEnabled) {
            logger.trace("{} [OUT] {}", logContext, frame)
        }
    }
}

/**
 * The reference of this message in a log line: its message id and its action, or its type when it has no action,
 * both escaped, and never its payload.
 */
internal fun WampMessage.logRef(): String =
    "[${msgId.escapedForLog()}] [${(action ?: msgType.name).escapedForLog()}]"
