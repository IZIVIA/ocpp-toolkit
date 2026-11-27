package com.izivia.ocpp.websocket.test

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.core16.model.authorize.AuthorizeReq
import com.izivia.ocpp.core16.model.authorize.AuthorizeResp
import com.izivia.ocpp.core16.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core16.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core16.model.clearcache.enumeration.ClearCacheStatus
import com.izivia.ocpp.core16.model.common.IdTagInfo
import com.izivia.ocpp.core16.model.common.enumeration.AuthorizationStatus
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.transport.OcppCallErrorException
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.transport.receiveMessage
import com.izivia.ocpp.utils.OcppErrorCategory
import com.izivia.ocpp.utils.OcppErrorEvent
import com.izivia.ocpp.utils.OcppErrorListener
import com.izivia.ocpp.websocket.WebsocketClient
import com.izivia.ocpp.websocket.WebsocketServer
import com.izivia.ocpp.websocket.asServer
import org.junit.jupiter.api.Test
import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import strikt.assertions.isFailure
import strikt.assertions.single
import java.net.ServerSocket
import java.net.URI
import java.net.http.HttpClient
import java.net.http.WebSocket
import java.net.http.WebSocketHandshakeException
import java.util.concurrent.CompletionException
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.time.Instant
import com.izivia.ocpp.OcppVersion as OcppVersionWamp

class WebsocketServerAcceptanceTest {
    private val port = ServerSocket(0).use { it.localPort }
    private val events = CopyOnWriteArrayList<OcppErrorEvent>()
    private val transport = WebsocketServer(
        setOf(OcppVersion.OCPP_1_6),
        "ws",
        errorListener = OcppErrorListener { events += it }
    )

    @Test
    fun `an accepted station is accepted with one call to the accept function and served by every route`() {
        val accept = CountingAccept(acceptConnection = true)
        transport.registerAuthorize(accept)
        transport.registerHeartbeat(accept)
        transport.registerClearCache(accept)

        withConnectedStation { client ->
            expectThat(client.sendMessageClass(AuthorizeResp::class, "Authorize", AuthorizeReq("Tag1")).idTagInfo.status)
                .isEqualTo(AuthorizationStatus.Accepted)
            expectThat(client.sendMessageClass(HeartbeatResp::class, "Heartbeat", HeartbeatReq()).currentTime)
                .isEqualTo(CURRENT_TIME)
        }
        expectThat(accept.calls.get()).isEqualTo(1)
    }

    @Test
    fun `a refused station is refused with one call to the accept function`() {
        val accept = CountingAccept(acceptConnection = false)
        transport.registerAuthorize(accept)
        transport.registerHeartbeat(accept)
        transport.registerClearCache(accept)
        val server = transport.asServer(port).start()

        try {
            expectThat(handshakeStatus(OCPP_ID)).isEqualTo(404)
            expectCatching { transport.server.getChargingStationOcppVersion(OCPP_ID) }.isFailure()
        } finally {
            server.stop()
        }
        expectThat(accept.calls.get()).isEqualTo(1)
    }

    @Test
    fun `each accept function is called once and a station is served only the routes of the one accepting it`() {
        val accepting = CountingAccept(acceptConnection = true)
        val refusing = CountingAccept(acceptConnection = false)
        transport.registerAuthorize(accepting)
        transport.registerClearCache(refusing)
        transport.registerHeartbeat(accepting)

        withConnectedStation { client ->
            expectThat(client.sendMessageClass(AuthorizeResp::class, "Authorize", AuthorizeReq("Tag1")).idTagInfo.status)
                .isEqualTo(AuthorizationStatus.Accepted)
            expectThat(client.sendMessageClass(HeartbeatResp::class, "Heartbeat", HeartbeatReq()).currentTime)
                .isEqualTo(CURRENT_TIME)
            // answered as a call no handler serves
            expectCatching { client.sendMessageClass(ClearCacheResp::class, "ClearCache", ClearCacheReq()) }
                .isFailure().isA<OcppCallErrorException>()
        }
        expectThat(accepting.calls.get()).isEqualTo(1)
        expectThat(refusing.calls.get()).isEqualTo(1)
        expectThat(events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.UNHANDLED_ACTION)
            get { action }.isEqualTo("ClearCache")
        }
    }

    private fun withConnectedStation(block: (WebsocketClient) -> Unit) {
        val server = transport.asServer(port).start()
        val client = WebsocketClient(OCPP_ID, OcppVersionWamp.OCPP_1_6, "ws://localhost:$port/ws")
        try {
            client.connect()
            block(client)
        } finally {
            client.close()
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

    private class CountingAccept(private val acceptConnection: Boolean) : (CSOcppId) -> ChargingStationConfig {
        val calls = AtomicInteger()

        override fun invoke(ocppId: CSOcppId): ChargingStationConfig {
            calls.incrementAndGet()
            return ChargingStationConfig(acceptConnection = acceptConnection, soapUrl = null)
        }
    }

    private fun WebsocketServer.registerAuthorize(accept: (CSOcppId) -> ChargingStationConfig) =
        receiveMessage("Authorize", OcppVersion.OCPP_1_6, { _, _: AuthorizeReq ->
            AuthorizeResp(IdTagInfo(AuthorizationStatus.Accepted))
        }, accept)

    private fun WebsocketServer.registerHeartbeat(accept: (CSOcppId) -> ChargingStationConfig) =
        receiveMessage("Heartbeat", OcppVersion.OCPP_1_6, { _, _: HeartbeatReq -> HeartbeatResp(CURRENT_TIME) }, accept)

    private fun WebsocketServer.registerClearCache(accept: (CSOcppId) -> ChargingStationConfig) =
        receiveMessage("ClearCache", OcppVersion.OCPP_1_6, { _, _: ClearCacheReq ->
            ClearCacheResp(ClearCacheStatus.Accepted)
        }, accept)

    private companion object {
        const val OCPP_ID = "CS1"
        val CURRENT_TIME = Instant.fromEpochSeconds(1_700_000_000)
    }
}
