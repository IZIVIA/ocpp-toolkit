package com.izivia.ocpp.wamp.server.impl

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.contains
import kotlin.time.Instant

class WsControlFrameEventTest {
    @Test
    fun `should not print the upgrade request headers`() {
        val event = WsControlFrameEvent(
            ocppId = "CS1",
            wsConnectionId = "connection-1",
            headers = listOf("Authorization" to "Basic Q1MxOnNlY3JldA=="),
            receivedAt = Instant.parse("2026-10-27T06:00:00Z")
        )

        expectThat(event.toString()) {
            contains("CS1")
            contains("connection-1")
            not().contains("Q1MxOnNlY3JldA==")
        }
    }
}
