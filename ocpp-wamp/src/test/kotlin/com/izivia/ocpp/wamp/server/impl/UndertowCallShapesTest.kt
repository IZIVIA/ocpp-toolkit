package com.izivia.ocpp.wamp.server.impl

import com.izivia.ocpp.wamp.server.WebSocketUpgradeDecision
import io.undertow.server.HttpServerExchange
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.time.Duration

/**
 * The Kotlin calls of the [Undertow] constructors: this source fails to compile when one of them becomes ambiguous
 * between the constructor of the releases before admission and the one taking it.
 */
class UndertowCallShapesTest {

    private val admit: (HttpServerExchange) -> WebSocketUpgradeDecision? = { WebSocketUpgradeDecision.Accept }
    private val subprotocols = setOf("ocpp1.6")
    private val dispatched = AdmissionExecution.Dispatched(4, Duration.ofSeconds(1))

    @Test
    fun `calls without the admission run it inline`() {
        listOf(
            Undertow(9000, true),
            Undertow(9000, enableHttp2 = true),
            Undertow(enableHttp2 = true),
            Undertow(9000, true, admit),
            Undertow(9000, true, admit, subprotocols),
            Undertow(9000, true, wsSubprotocols = subprotocols),
            Undertow(enableHttp2 = true, admitWebSocket = admit),
            Undertow(port = 9000, enableHttp2 = true, admitWebSocket = admit, wsSubprotocols = subprotocols),
        ).forEach { expectThat(it.admission).isEqualTo(AdmissionExecution.Inline) }
    }

    @Test
    fun `calls with the admission keep it`() {
        listOf(
            Undertow(9000, true, admission = dispatched),
            Undertow(enableHttp2 = true, admission = dispatched),
            Undertow(9000, true, admit, admission = dispatched),
            Undertow(9000, true, admit, subprotocols, dispatched),
            Undertow(9000, enableHttp2 = true, wsSubprotocols = subprotocols, admission = dispatched),
        ).forEach { expectThat(it.admission).isEqualTo(dispatched) }
    }

    @Test
    fun `the port defaults to 8000`() {
        expectThat(Undertow(enableHttp2 = true).port).isEqualTo(8000)
        expectThat(Undertow(enableHttp2 = true, admission = dispatched).port).isEqualTo(8000)
    }
}
