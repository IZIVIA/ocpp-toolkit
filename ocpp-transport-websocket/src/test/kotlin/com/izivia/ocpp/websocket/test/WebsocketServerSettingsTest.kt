package com.izivia.ocpp.websocket.test

import com.izivia.ocpp.core16.model.authorize.AuthorizeReq
import com.izivia.ocpp.core16.model.authorize.AuthorizeResp
import com.izivia.ocpp.core16.model.common.IdTagInfo
import com.izivia.ocpp.core16.model.common.enumeration.AuthorizationStatus
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.wamp.server.impl.OcppWampServerSettings
import com.izivia.ocpp.websocket.WebsocketClient
import com.izivia.ocpp.websocket.WebsocketServer
import com.izivia.ocpp.websocket.asServer
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isGreaterThanOrEqualTo
import java.net.ServerSocket
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import com.izivia.ocpp.OcppVersion as OcppVersionWamp

class WebsocketServerSettingsTest {

    /** Settings whose calls executor records how many calls it was asked to run. */
    private class RecordingSettings : OcppWampServerSettings() {
        val executedCalls = AtomicInteger()
        private val delegate = Executors.newSingleThreadExecutor()

        override fun buildCallsExecutor(): Executor = Executor { command ->
            executedCalls.incrementAndGet()
            delegate.execute(command)
        }

        fun shutdown() {
            delegate.shutdownNow()
        }
    }

    private fun getFreePort(): Int =
        ServerSocket(0).use { it.localPort }

    @Test
    fun `settings given to WebsocketServer are used to handle inbound calls`() {
        val port = getFreePort()
        val settings = RecordingSettings()

        val transport = WebsocketServer(setOf(OcppVersion.OCPP_1_6), "ws", settings = settings)
        transport.receiveMessageClass(
            clazz = AuthorizeReq::class,
            action = "authorize",
            ocppVersion = OcppVersion.OCPP_1_6,
            onAction = { _, _: AuthorizeReq -> AuthorizeResp(IdTagInfo(AuthorizationStatus.Accepted)) },
            accept = { ChargingStationConfig(acceptConnection = true, soapUrl = null) }
        )

        val server = transport.asServer(port)
        server.start()

        try {
            val client = WebsocketClient("TEST1", OcppVersionWamp.OCPP_1_6, "ws://localhost:$port/ws")
            client.connect()
            try {
                client.sendMessageClass(AuthorizeResp::class, "authorize", AuthorizeReq("Tag1"))
            } finally {
                client.close()
            }

            expectThat(settings.executedCalls.get()).isGreaterThanOrEqualTo(1)
        } finally {
            server.stop()
            settings.shutdown()
        }
    }
}
