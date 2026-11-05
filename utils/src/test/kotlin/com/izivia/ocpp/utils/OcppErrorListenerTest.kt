package com.izivia.ocpp.utils

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.containsExactly
import strikt.assertions.isEqualTo
import strikt.assertions.isNotEqualTo

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
}
