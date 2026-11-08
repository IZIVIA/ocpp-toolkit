package com.izivia.ocpp.websocket.test

import com.izivia.ocpp.core16.model.authorize.AuthorizeReq
import com.izivia.ocpp.core16.model.authorize.AuthorizeResp
import com.izivia.ocpp.core16.model.common.IdTagInfo
import com.izivia.ocpp.core16.model.common.enumeration.AuthorizationStatus
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.wamp.server.ConnectionAuthorizer
import com.izivia.ocpp.websocket.WebsocketClient
import com.izivia.ocpp.websocket.WebsocketServer
import com.izivia.ocpp.websocket.asServer
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.net.ServerSocket
import java.net.URI
import java.net.http.HttpClient
import java.net.http.WebSocket
import java.net.http.WebSocketHandshakeException
import java.util.concurrent.CompletionException
import java.util.concurrent.TimeUnit
import com.izivia.ocpp.OcppVersion as OcppVersionWamp

class WebsocketServerAuthorizerTest {
    private val port = ServerSocket(0).use { it.localPort }
    private val token = "Bearer station-token"

    private val transport = WebsocketServer(
        setOf(OcppVersion.OCPP_1_6),
        "ws",
        authorizer = ConnectionAuthorizer { request -> ("Authorization" to token) in request.headers }
    ).apply {
        receiveMessageClass(
            clazz = AuthorizeReq::class,
            action = "authorize",
            ocppVersion = OcppVersion.OCPP_1_6,
            onAction = { _, _: AuthorizeReq -> AuthorizeResp(IdTagInfo(AuthorizationStatus.Accepted)) },
            accept = { ChargingStationConfig(acceptConnection = true, soapUrl = null) }
        )
    }

    @Test
    fun `authorized station connects and has its calls handled`() {
        val server = transport.asServer(port).start()
        val client = WebsocketClient(
            "CS1",
            OcppVersionWamp.OCPP_1_6,
            "ws://localhost:$port/ws",
            headers = listOf("Authorization" to token)
        )

        try {
            client.connect()

            expectThat(client.sendMessageClass(AuthorizeResp::class, "authorize", AuthorizeReq("Tag1")).idTagInfo.status)
                .isEqualTo(AuthorizationStatus.Accepted)
        } finally {
            client.close()
            server.stop()
        }
    }

    @Test
    fun `station refused by the authorizer fails its handshake with 401`() {
        val server = transport.asServer(port).start()

        try {
            expectThat(handshakeStatus("CS1")).isEqualTo(401)
        } finally {
            server.stop()
        }
    }

    private fun handshakeStatus(ocppId: String): Int? =
        try {
            HttpClient.newHttpClient().newWebSocketBuilder()
                .subprotocols(OcppVersionWamp.OCPP_1_6.subprotocol)
                .buildAsync(URI("ws://localhost:$port/ws/$ocppId"), object : WebSocket.Listener {})
                .orTimeout(5, TimeUnit.SECONDS)
                .join()
                .abort()
            101
        } catch (e: CompletionException) {
            (e.cause as? WebSocketHandshakeException)?.response?.statusCode()
        }
}
