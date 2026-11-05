package com.izivia.ocpp.wamp

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.wamp.messages.WampMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull

class WampMessageSerializationTest {
    private val strictJson = ObjectMapper().enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)

    @Test
    fun `should keep the frame of an ordinary call`() {
        expectThat(WampMessage.Call("1", "Heartbeat", """{"a":"b"}""").toJson())
            .isEqualTo("""[2,"1","Heartbeat",{"a":"b"}]""")
    }

    @Test
    fun `should keep the frame of an ordinary call result`() {
        expectThat(WampMessage.CallResult("1", """{"a":"b"}""").toJson())
            .isEqualTo("""[3,"1",{"a":"b"}]""")
    }

    @Test
    fun `should keep the frame of an ordinary call error`() {
        val message = WampMessage.CallError("1", MessageErrorCode.NOT_SUPPORTED, "a problem, é", """{"a":"b"}""")

        expectThat(message.toJson()).isEqualTo("""[4,"1","NotSupported","a problem, é",{"a":"b"}]""")
    }

    @ParameterizedTest
    @ValueSource(strings = ["say \"hi\"", "C:\\temp\\", "first\nsecond\ttab", "\u0001"])
    fun `should write an error description a strict parser reads back`(description: String) {
        val message = WampMessage.CallError("1", MessageErrorCode.INTERNAL_ERROR, description, "{}")

        expectThat(strictJson.readTree(message.toJson())[3].asText()).isEqualTo(description)
    }

    @ParameterizedTest
    @ValueSource(strings = ["say \"hi\"", "C:\\temp\\", "first\nsecond"])
    fun `should write an action a strict parser reads back`(action: String) {
        val message = WampMessage.Call("1", action, "{}")

        expectThat(strictJson.readTree(message.toJson())[2].asText()).isEqualTo(action)
    }

    @ParameterizedTest
    @ValueSource(strings = ["say \"hi\"", "C:\\temp\\", "a \"quoted\", with a comma", "first\nsecond"])
    fun `should parse back the call error it writes`(description: String) {
        val frame = WampMessage.CallError("1", MessageErrorCode.INTERNAL_ERROR, description, """{"a":"b"}""").toJson()

        expectThat(WampMessage.parse(frame)).isNotNull().and {
            get { msgId }.isEqualTo("1")
            get { errorCode }.isEqualTo(MessageErrorCode.INTERNAL_ERROR)
            get { payload }.isEqualTo("""{"a":"b"}""")
        }
    }

    @Test
    fun `should keep a parsed description in wire form`() {
        expectThat(WampMessage.parse("""[4,"1","InternalError","say \"hi\"",{}]"""))
            .isNotNull()
            .get { errorDescription }.isEqualTo("""say \"hi\"""")
    }

    @Test
    fun `should still parse a description ending with a lone backslash`() {
        expectThat(WampMessage.parse("""[4,"1","InternalError","C:\",{"a":"b"}]""")).isNotNull().and {
            get { errorDescription }.isEqualTo("""C:\""")
            get { payload }.isEqualTo("""{"a":"b"}""")
        }
    }

    @Test
    fun `should parse a very long description full of escapes`() {
        val description = "\"".repeat(200_000)
        val frame = WampMessage.CallError("1", MessageErrorCode.INTERNAL_ERROR, description, "{}").toJson()

        expectThat(WampMessage.parse(frame)).isNotNull().get { payload }.isEqualTo("{}")
    }

    @Test
    fun `should escape a parsed description a second time when writing it back`() {
        val parsed = WampMessage.parse("""[4,"1","InternalError","say \"hi\"",{}]""")!!

        expectThat(parsed.toJson()).isEqualTo("""[4,"1","InternalError","say \\\"hi\\\"",{}]""")
    }

    @Test
    fun `should echo a parsed message id byte for byte`() {
        val parsed = WampMessage.parse("""[2,"a\\b","Heartbeat",{}]""")!!

        expectThat(WampMessage.CallResult(parsed.msgId, "{}").toJson()).isEqualTo("""[3,"a\\b",{}]""")
    }
}
