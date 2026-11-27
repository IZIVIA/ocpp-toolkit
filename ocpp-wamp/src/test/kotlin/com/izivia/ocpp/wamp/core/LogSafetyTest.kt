package com.izivia.ocpp.wamp.core

import com.izivia.ocpp.OcppVersion.OCPP_1_6
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.isEqualTo
import java.util.Base64

class LogSafetyTest {

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
