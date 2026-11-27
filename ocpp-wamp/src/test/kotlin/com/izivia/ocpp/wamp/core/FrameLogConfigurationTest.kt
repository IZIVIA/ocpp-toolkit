package com.izivia.ocpp.wamp.core

import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.joran.JoranConfigurator
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isFalse
import strikt.assertions.isTrue

/** The logback configurations the README gives for the raw-frames logger, applied to a context of their own. */
class FrameLogConfigurationTest {

    @Test
    fun `logback without configuration lets the frames logger through`() {
        val context = LoggerContext()

        expectThat(context.getLogger(FrameLog.LOGGER_NAME).isDebugEnabled).isTrue()
    }

    @Test
    fun `the frames logger set to INFO is off while the root logs at DEBUG`() {
        val context = configured("""<logger name="com.izivia.ocpp.wamp.frames" level="INFO"/>""")

        expectThat(context.getLogger(FrameLog.LOGGER_NAME).isDebugEnabled).isFalse()
        expectThat(context.getLogger("com.izivia.ocpp.wamp.server").isDebugEnabled).isTrue()
    }

    @Test
    fun `the frames logger set to DEBUG is on while the root logs at INFO`() {
        val context = configured(
            """<logger name="com.izivia.ocpp.wamp.frames" level="DEBUG"/>""",
            rootLevel = "INFO"
        )

        expectThat(context.getLogger(FrameLog.LOGGER_NAME).isDebugEnabled).isTrue()
        expectThat(context.getLogger("com.izivia.ocpp.wamp.server").isDebugEnabled).isFalse()
    }

    private fun configured(loggerLine: String, rootLevel: String = "DEBUG"): LoggerContext {
        val xml = """<configuration>$loggerLine<root level="$rootLevel"/></configuration>"""
        return LoggerContext().also { context ->
            JoranConfigurator().apply { setContext(context) }.doConfigure(xml.byteInputStream())
        }
    }
}
