package com.izivia.ocpp.wamp.core

import ch.qos.logback.classic.spi.ThrowableProxy
import ch.qos.logback.classic.spi.ThrowableProxyUtil
import com.izivia.ocpp.OcppVersion.OCPP_1_6
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.hasSize
import strikt.assertions.isEqualTo
import strikt.assertions.isLessThanOrEqualTo
import strikt.assertions.isNotNull
import strikt.assertions.isNotSameInstanceAs
import strikt.assertions.startsWith
import java.io.IOException
import java.util.Base64

class LogSafetyTest {

    @Test
    fun `redaction keeps every class name and the stack of the chain without any message`() {
        val nested = IOException("nested $SENTINEL")
        val suppressed = IllegalArgumentException("suppressed $SENTINEL")
        val suppressedOfCause = UnsupportedOperationException("deep $SENTINEL")
        val cause = IllegalStateException("cause $SENTINEL", nested).apply { addSuppressed(suppressedOfCause) }
        val top = RuntimeException("top $SENTINEL", cause).apply { addSuppressed(suppressed) }

        val redacted = top.redactedForLog()
        val rendered = ThrowableProxyUtil.asString(ThrowableProxy(redacted))

        expectThat(rendered).not().contains(SENTINEL)
        listOf(top, cause, nested, suppressed, suppressedOfCause).forEach {
            expectThat(rendered).contains(it.javaClass.name)
        }
        expectThat(rendered).contains(top.stackTrace.first().toString())
        expectThat(redacted).isNotSameInstanceAs(top)
        expectThat(redacted.stackTrace.toList()).isEqualTo(top.stackTrace.toList())
        expectThat(redacted.cause).isNotNull().and {
            get { message }.isEqualTo(IllegalStateException::class.java.name)
            get { stackTrace.toList() }.isEqualTo(cause.stackTrace.toList())
            get { this.suppressed.toList() }.hasSize(1)
            get { this.cause?.message }.isEqualTo(IOException::class.java.name)
        }
        expectThat(redacted.suppressed.toList()).hasSize(1)
        expectThat(redacted.suppressed.single().message).isEqualTo(IllegalArgumentException::class.java.name)
    }

    @Test
    fun `redaction survives a cause cycle and a self-suppression`() {
        val first = IllegalStateException("first $SENTINEL")
        val second = IllegalArgumentException("second $SENTINEL", first)
        first.initCause(second)
        second.addSuppressed(first)

        val rendered = ThrowableProxyUtil.asString(ThrowableProxy(first.redactedForLog()))

        expectThat(rendered).not().contains(SENTINEL)
        expectThat(rendered).contains(IllegalStateException::class.java.name)
        expectThat(rendered).contains(IllegalArgumentException::class.java.name)
    }

    @Test
    fun `redaction bounds a deep chain`() {
        var deep: Throwable = IllegalStateException("bottom $SENTINEL")
        repeat(1_000) { deep = IllegalStateException("level $it $SENTINEL", deep) }

        var depth = 0
        var current: Throwable? = deep.redactedForLog()
        while (current != null) {
            expectThat(current.message.orEmpty()).not().contains(SENTINEL)
            depth++
            current = current.cause
        }
        expectThat(depth).isLessThanOrEqualTo(64)
    }

    @Test
    fun `escaping renders control and line separator characters as unicode escapes`() {
        expectThat("CS1\r\nFAKE\u0000  \u007f\tX".escapedForLog())
            .isEqualTo("CS1\\u000d\\u000aFAKE\\u0000\\u2028\\u2029\\u007f\\u0009X")
        expectThat("CS-1_é".escapedForLog()).isEqualTo("CS-1_é")
    }

    @Test
    fun `escaping bounds the rendered string and gives the original length`() {
        val long = "A".repeat(100) + "\n".repeat(100)

        val escaped = long.escapedForLog(max = 10)

        expectThat(escaped).startsWith("A".repeat(10))
        expectThat(escaped).not().contains("\n")
        expectThat(escaped).contains("200")
        expectThat(escaped.length).isLessThanOrEqualTo(40)
        expectThat("\n".repeat(100).escapedForLog(max = 4).length).isLessThanOrEqualTo(60)
    }

    @Test
    fun `a message reference names the escaped id and action only`() {
        val call = WampMessage.Call("id\n1", "Heart\rbeat", """{"idTag":"$SENTINEL"}""")

        expectThat(call.logRef()).isEqualTo("[id\\u000a1] [Heart\\u000dbeat]")
        expectThat(WampMessage.CallResult("id-2", """{"x":"$SENTINEL"}""").logRef())
            .isEqualTo("[id-2] [CALL_RESULT]")
    }

    @Test
    fun `meta toString shows header names only`() {
        val basic = Base64.getEncoder().encodeToString("cs:$PASSWORD".toByteArray())
        val meta = WampMessageMeta(OCPP_1_6, "CS1", listOf("Authorization" to "Basic $basic", "X-Other" to SENTINEL))

        val rendered = meta.toString()

        expectThat(rendered).not().contains(basic)
        expectThat(rendered).not().contains(PASSWORD)
        expectThat(rendered).not().contains(SENTINEL)
        expectThat(rendered).contains("CS1")
        expectThat(rendered).contains(OCPP_1_6.toString())
        expectThat(rendered).contains("Authorization")
        expectThat(rendered).contains("X-Other")
        expectThat(meta.headers).isEqualTo(listOf("Authorization" to "Basic $basic", "X-Other" to SENTINEL))
    }

    private companion object {
        const val SENTINEL = "sentinel-168-unit"
        const val PASSWORD = "SENTINEL-PWD"
    }
}
