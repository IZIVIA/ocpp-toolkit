package com.izivia.ocpp.utils

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.containsExactly

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
    fun `should isolate a listener throwing an exception`() {
        OcppErrorListener { throw IllegalStateException("listener failure") }.notifySafely(event)
    }

    @Test
    fun `should isolate a listener throwing an error`() {
        OcppErrorListener { throw NoClassDefFoundError("io/sentry/Sentry") }.notifySafely(event)
    }
}
