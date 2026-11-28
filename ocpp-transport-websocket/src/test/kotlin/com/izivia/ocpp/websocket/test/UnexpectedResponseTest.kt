package com.izivia.ocpp.websocket.test

import com.izivia.ocpp.transport.OcppCallErrorException
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.websocket.resultOf
import org.junit.jupiter.api.Test
import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import strikt.assertions.isFailure
import strikt.assertions.isNotNull

/**
 * The response checks shared by `WebsocketServer` and `WebsocketClient`: an exception thrown on an unexpected
 * response names the call by its message id and action, and never quotes a payload.
 */
class UnexpectedResponseTest {

    @Test
    fun `should parse the payload of the call result of the call`() {
        val result = WampMessage.CallResult("m-1", """{"status":"Accepted"}""").resultOf("m-1", "Heartbeat") { it }

        expectThat(result).isEqualTo("""{"status":"Accepted"}""")
    }

    @Test
    fun `should throw the call error details of the call`() {
        val error = WampMessage.CallError("m-1", MessageErrorCode.INTERNAL_ERROR, "failure", """{"a":1}""")

        expectCatching { error.resultOf("m-1", "Heartbeat") { it } }
            .isFailure()
            .isA<OcppCallErrorException>()
            .get { payload }.isEqualTo("""{"a":1}""")
    }

    @Test
    fun `should name a response of an unexpected type without its payload`() {
        val response = WampMessage.Call("m-1", "DataTransfer", """{"data":"$SENTINEL"}""")

        expectCatching { response.resultOf("m-1", "Heartbeat") { it } }
            .isFailure()
            .isA<IllegalStateException>()
            .get { message }.isNotNull().and {
                contains("[m-1] [Heartbeat]")
                contains("CALL")
                not().contains(SENTINEL)
            }
    }

    @Test
    fun `should name a response to another call without its payload`() {
        val response = WampMessage.CallResult("m-2", """{"data":"$SENTINEL"}""")

        expectCatching { response.resultOf("m-1", "Heartbeat") { it } }
            .isFailure()
            .isA<IllegalStateException>()
            .get { message }.isNotNull().and {
                contains("[m-1] [Heartbeat]")
                contains("m-2")
                not().contains(SENTINEL)
            }
    }

    @Test
    fun `should name a response to another call by its escaped message id`() {
        val response = WampMessage.CallResult("m-2\r\nFORGED", "{}")

        expectCatching { response.resultOf("m-1", "Heartbeat") { it } }
            .isFailure()
            .isA<IllegalStateException>()
            .get { message }.isNotNull().and {
                contains("[m-2\\u000d\\u000aFORGED]")
                not().contains("\r")
                not().contains("\n")
            }
    }

    private companion object {
        const val SENTINEL = "sentinel-168-response"
    }
}
