package com.izivia.ocpp.integration.test

import com.izivia.ocpp.core16.ChargePointOperations
import com.izivia.ocpp.integration.ApiFactory
import com.izivia.ocpp.integration.CSMS
import com.izivia.ocpp.integration.model.CSMSSettings
import com.izivia.ocpp.integration.model.ServerSetting
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.wamp.server.ConnectionAuthorizer
import com.izivia.ocpp.wamp.server.impl.AdmissionExecution
import com.izivia.ocpp.wamp.server.impl.EventsListeners
import io.mockk.mockk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import strikt.api.expect
import strikt.api.expectThat
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo
import strikt.assertions.isNotEqualTo
import strikt.assertions.isTrue
import java.net.ServerSocket
import java.net.URI
import java.net.http.HttpClient
import java.net.http.WebSocket
import java.net.http.WebSocketHandshakeException
import java.time.Duration
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionException
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit.SECONDS
import com.izivia.ocpp.OcppVersion as WampOcppVersion

/** The admission mode of [CSMSSettings] reaches the WebSocket servers of the CSMS. */
class CSMSDispatchedAdmissionTest {
    private val port = ServerSocket(0).use { it.localPort }
    private val connected = CopyOnWriteArrayList<String>()
    private val pool = Executors.newCachedThreadPool()

    // the authorization of SLOW waits for release; returned counts down once it has returned
    private val entered = CountDownLatch(1)
    private val release = CountDownLatch(1)
    private val returned = CountDownLatch(1)
    private val slowAuthorizer = ConnectionAuthorizer { request ->
        if (request.ocppId == "SLOW") {
            try {
                entered.countDown()
                release.await(10, SECONDS)
            } finally {
                returned.countDown()
            }
        }
        true
    }

    @AfterEach
    fun releaseAdmissions() {
        release.countDown()
        pool.shutdownNow()
    }

    @Test
    fun `slow authorization of one station does not delay another one`() {
        val server = csms(AdmissionExecution.Dispatched(maxConcurrent = 4, timeout = Duration.ofSeconds(10), pool))
        server.start()

        try {
            val slow = handshakeAsync("SLOW")
            awaitOrFail(entered, "authorization of SLOW started")

            val other = handshake("OTHER")
            release.countDown()

            expect {
                that(other.status).isEqualTo(101)
                that(slow.get(5, SECONDS).status).isEqualTo(101)
            }
        } finally {
            server.stop()
        }
    }

    @Test
    fun `authorization that times out gets a 503 with Retry-After`() {
        val server = csms(AdmissionExecution.Dispatched(maxConcurrent = 1, timeout = Duration.ofMillis(300), pool))
        server.start()

        try {
            val expired = handshake("SLOW")

            expect {
                that(expired.status).isEqualTo(503)
                that(expired.retryAfter).isEqualTo("5")
                that(connected).isEmpty()
            }
        } finally {
            release.countDown()
            server.stop()
        }
    }

    @Test
    fun `authorization pending when the CSMS stops upgrades nothing`() {
        val server = csms(AdmissionExecution.Dispatched(maxConcurrent = 1, timeout = Duration.ofSeconds(10), pool))
        server.start()

        try {
            val pending = handshakeAsync("SLOW")
            awaitOrFail(entered, "authorization of SLOW started")

            server.stop()
            release.countDown()
            awaitOrFail(returned, "authorization of SLOW returned")

            expect {
                that(pending.get(5, SECONDS).status).isNotEqualTo(101)
                that(connected).isEmpty()
            }
        } finally {
            release.countDown()
            server.stop()
        }
    }

    private data class Handshake(val status: Int?, val retryAfter: String? = null)

    private fun csms(admission: AdmissionExecution): CSMS =
        ApiFactory.csmsOcppServer(
            csmsSettings = CSMSSettings(
                listOf(
                    ServerSetting(
                        port = port,
                        path = "ws",
                        ocppVersion = setOf(OcppVersion.OCPP_1_6),
                        transportType = TransportEnum.WEBSOCKET,
                        listeners = EventsListeners(onWsConnectHandler = { id, _ -> connected += id }),
                        authorizer = slowAuthorizer
                    )
                ),
                admission = admission
            ),
            csmsApiCallbacks = listOf(mockk<ChargePointOperations>(relaxed = true)),
            fn = { ChargingStationConfig(acceptConnection = true, soapUrl = null) }
        )

    // one upgrade attempt, never retried; a failed one reports its HTTP status and Retry-After header
    private fun handshakeAsync(ocppId: String): CompletableFuture<Handshake> =
        HttpClient.newHttpClient().newWebSocketBuilder()
            .subprotocols(WampOcppVersion.OCPP_1_6.subprotocol)
            .buildAsync(URI("ws://localhost:$port/ws/$ocppId"), object : WebSocket.Listener {})
            .orTimeout(5, SECONDS)
            .thenApply { webSocket ->
                webSocket.abort()
                Handshake(101)
            }
            .exceptionally { e ->
                val response = ((if (e is CompletionException) e.cause else e) as? WebSocketHandshakeException)?.response
                Handshake(response?.statusCode(), response?.headers()?.firstValue("Retry-After")?.orElse(null))
            }

    private fun handshake(ocppId: String): Handshake = handshakeAsync(ocppId).get(10, SECONDS)

    private fun awaitOrFail(latch: CountDownLatch, what: String) =
        expectThat(latch.await(5, SECONDS)).describedAs("$what within 5 s").isTrue()
}
