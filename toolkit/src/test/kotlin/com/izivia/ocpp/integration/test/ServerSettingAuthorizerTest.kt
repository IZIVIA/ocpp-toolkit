package com.izivia.ocpp.integration.test

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
import com.izivia.ocpp.core16.ChargePointOperations
import com.izivia.ocpp.integration.ApiFactory
import com.izivia.ocpp.integration.CSMS
import com.izivia.ocpp.integration.model.CSMSSettings
import com.izivia.ocpp.integration.model.ServerSetting
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.wamp.client.OcppWampClient
import com.izivia.ocpp.wamp.server.ConnectionAuthorizer
import com.izivia.ocpp.wamp.server.impl.EventsListeners
import com.izivia.ocpp.wamp.server.impl.UndertowOcppWampServer
import io.mockk.mockk
import org.http4k.core.Uri
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import strikt.api.expect
import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.assertions.any
import strikt.assertions.contains
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo
import strikt.assertions.isSuccess
import strikt.assertions.none
import java.net.ServerSocket
import java.net.URI
import java.net.http.HttpClient
import java.net.http.WebSocket
import java.net.http.WebSocketHandshakeException
import java.util.Base64
import java.util.concurrent.CompletionException
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.TimeUnit
import com.izivia.ocpp.OcppVersion as WampOcppVersion

class ServerSettingAuthorizerTest {
    private val port = ServerSocket(0).use { it.localPort }
    private val connected = CopyOnWriteArrayList<String>()
    private val goodCredentials = basic("CS1", "s3cr3t")
    private val acceptsGoodCredentials =
        ConnectionAuthorizer { request -> ("Authorization" to goodCredentials) in request.headers }

    @Test
    fun `station is authorized through the server setting`() {
        val server = csms(acceptsGoodCredentials)
        server.start()

        try {
            expectThat(handshakeStatus("CS1", basic("CS1", "wrong"))).isEqualTo(401)
            expectThat(connected).isEmpty()

            expectCatching { connect("CS1", goodCredentials).close() }.isSuccess()
        } finally {
            server.stop()
        }
    }

    @Test
    fun `failing authorizer refuses the station without logging its credentials`() =
        withLogs(UndertowOcppWampServer::class.java.name) { appender ->
            val server = csms(ConnectionAuthorizer { request -> error("unexpected headers ${request.headers}") })
            server.start()

            try {
                expectThat(handshakeStatus("CS1", goodCredentials)).isEqualTo(401)

                val logs = appender.list.map { it.formattedMessage + it.throwableProxy?.message.orEmpty() }
                expect {
                    that(connected).isEmpty()
                    that(logs).any { contains("CS1") }
                    that(logs).none { contains(goodCredentials.substringAfter("Basic ")) }
                }
            } finally {
                server.stop()
            }
        }

    @Test
    fun `credentials of an authorized station are not logged`() =
        withLogs("com.izivia.ocpp.wamp", Level.DEBUG) { appender ->
            val server = csms(acceptsGoodCredentials)
            server.start()

            try {
                val client = connect("CS1", goodCredentials)
                // the server logs the connection on its IO thread, before notifying it
                val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(5)
                while ("CS1" !in connected && System.nanoTime() < deadline) Thread.sleep(10)
                client.close()

                val logs = appender.list.map { it.formattedMessage }
                expect {
                    that(connected).contains("CS1")
                    that(logs).any { contains("/ws/CS1 - connection attempt") }
                    that(logs).none { contains(goodCredentials.substringAfter("Basic ")) }
                }
            } finally {
                server.stop()
            }
        }

    private fun withLogs(loggerName: String, level: Level? = null, block: (ListAppender<ILoggingEvent>) -> Unit) {
        val logger = LoggerFactory.getLogger(loggerName) as Logger
        val previousLevel = logger.level
        val appender = ListAppender<ILoggingEvent>().apply { start() }
        level?.let { logger.level = it }
        logger.addAppender(appender)
        try {
            block(appender)
        } finally {
            logger.detachAppender(appender)
            logger.level = previousLevel
            appender.stop()
        }
    }

    private fun csms(authorizer: ConnectionAuthorizer): CSMS =
        ApiFactory.csmsOcppServer(
            csmsSettings = CSMSSettings(
                listOf(
                    ServerSetting(
                        port = port,
                        path = "ws",
                        ocppVersion = setOf(OcppVersion.OCPP_1_6),
                        transportType = TransportEnum.WEBSOCKET,
                        listeners = EventsListeners(onWsConnectHandler = { id, _ -> connected += id }),
                        authorizer = authorizer
                    )
                )
            ),
            csmsApiCallbacks = listOf(mockk<ChargePointOperations>(relaxed = true)),
            fn = { ChargingStationConfig(acceptConnection = true, soapUrl = null) }
        )

    private fun connect(ocppId: String, authorization: String) =
        OcppWampClient.newClient(
            Uri.of("ws://localhost:$port/ws"),
            ocppId,
            WampOcppVersion.OCPP_1_6,
            timeoutInMs = 5_000,
            autoReconnect = false,
            headers = listOf("Authorization" to authorization)
        ).also { it.connect() }

    private fun handshakeStatus(ocppId: String, authorization: String): Int? =
        try {
            HttpClient.newHttpClient().newWebSocketBuilder()
                .subprotocols(WampOcppVersion.OCPP_1_6.subprotocol)
                .header("Authorization", authorization)
                .buildAsync(URI("ws://localhost:$port/ws/$ocppId"), object : WebSocket.Listener {})
                .orTimeout(5, TimeUnit.SECONDS)
                .join()
                .abort()
            101
        } catch (e: CompletionException) {
            (e.cause as? WebSocketHandshakeException)?.response?.statusCode()
        }

    private fun basic(user: String, password: String) =
        "Basic " + Base64.getEncoder().encodeToString("$user:$password".toByteArray())
}
