package com.izivia.ocpp.websocket.test

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import com.izivia.ocpp.OcppVersion as OcppVersionWamp
import com.izivia.ocpp.transport.OcppVersion as OcppVersionTransport

/**
 * `WebsocketServer` bridges the transport and WAMP version enums with `OcppVersionWamp.valueOf(name)`,
 * a match nothing else checks. These tests fail on a divergence instead of letting it surface as an
 * `IllegalArgumentException` inside a WebSocket handler.
 */
class OcppVersionBridgeTest {

    @Test
    fun `both version enums declare the same constants`() {
        expectThat(OcppVersionWamp.entries.map { it.name })
            .isEqualTo(OcppVersionTransport.entries.map { it.name })
    }

    @Test
    fun `both version enums declare the same subprotocols`() {
        expectThat(OcppVersionWamp.entries.associate { it.name to it.subprotocol })
            .isEqualTo(OcppVersionTransport.entries.associate { it.name to it.subprotocol })
    }
}
