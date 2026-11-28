package com.izivia.ocpp.wamp.server

import com.izivia.ocpp.wamp.server.impl.AdmissionExecution
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.hasSize
import java.time.Duration

/**
 * The Kotlin calls of [asServer]: this source fails to compile when one of them becomes ambiguous between the
 * signature of the releases before admission and the one taking it.
 */
class AsServerCallShapesTest {

    private val server = OcppWampServer.newServer()
    private val dispatched = AdmissionExecution.Dispatched(4, Duration.ofSeconds(1))

    @Test
    fun `every call shape resolves`() {
        listOf(
            server.asServer(),
            server.asServer(8000),
            server.asServer(port = 8000),
            server.asServer(admission = dispatched),
            server.asServer(8000, dispatched),
            server.asServer(port = 8000, admission = dispatched),
        ).let { expectThat(it).hasSize(6) }
    }
}
