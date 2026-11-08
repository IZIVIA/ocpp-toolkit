package com.izivia.ocpp.websocket.test

import com.izivia.ocpp.core16.model.authorize.AuthorizeReq
import com.izivia.ocpp.core16.model.authorize.AuthorizeResp
import com.izivia.ocpp.core16.model.common.IdTagInfo
import com.izivia.ocpp.core16.model.common.enumeration.AuthorizationStatus
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.wamp.client.OcppWampClientSettings
import com.izivia.ocpp.websocket.WebsocketClient
import com.izivia.ocpp.websocket.WebsocketServer
import com.izivia.ocpp.websocket.asServer
import org.junit.jupiter.api.Test
import strikt.api.expectCatching
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import strikt.assertions.isFailure
import strikt.assertions.isSuccess
import java.net.ServerSocket
import java.util.concurrent.TimeoutException
import com.izivia.ocpp.OcppVersion as OcppVersionWamp

class WebsocketClientSettingsTest {

    private fun getFreePort(): Int =
        ServerSocket(0).use { it.localPort }

    /** Sends one authorize call to a server that answers it after [SLOW_ANSWER_MS]. */
    private fun authorizeOnSlowServer(settings: OcppWampClientSettings?): Result<AuthorizeResp> {
        val port = getFreePort()
        val transport = WebsocketServer(setOf(OcppVersion.OCPP_1_6), "ws")
        transport.receiveMessageClass(
            clazz = AuthorizeReq::class,
            action = "authorize",
            ocppVersion = OcppVersion.OCPP_1_6,
            onAction = { _, _: AuthorizeReq ->
                Thread.sleep(SLOW_ANSWER_MS)
                AuthorizeResp(IdTagInfo(AuthorizationStatus.Accepted))
            },
            accept = { ChargingStationConfig(acceptConnection = true, soapUrl = null) }
        )

        val server = transport.asServer(port)
        server.start()
        try {
            val target = "ws://localhost:$port/ws"
            val client = if (settings == null) {
                WebsocketClient("TEST1", OcppVersionWamp.OCPP_1_6, target)
            } else {
                WebsocketClient("TEST1", OcppVersionWamp.OCPP_1_6, target, settings = settings)
            }
            client.connect()
            try {
                return runCatching { client.sendMessageClass(AuthorizeResp::class, "authorize", AuthorizeReq("Tag1")) }
            } finally {
                client.close()
            }
        } finally {
            server.stop()
        }
    }

    @Test
    fun `with the default settings a slow answer is received`() {
        expectCatching { authorizeOnSlowServer(settings = null).getOrThrow() }
            .isSuccess()
            .get { idTagInfo.status }.isEqualTo(AuthorizationStatus.Accepted)
    }

    @Test
    fun `the call timeout given in the settings is applied`() {
        expectCatching { authorizeOnSlowServer(OcppWampClientSettings(timeoutInMs = 300)).getOrThrow() }
            .isFailure()
            .isA<TimeoutException>()
    }

    private companion object {
        const val SLOW_ANSWER_MS = 1_500L
    }
}
