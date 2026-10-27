package com.izivia.ocpp.websocket.test

import com.izivia.ocpp.core16.model.authorize.AuthorizeReq
import com.izivia.ocpp.core16.model.authorize.AuthorizeResp
import com.izivia.ocpp.core16.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core16.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core16.model.clearcache.enumeration.ClearCacheStatus
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
import strikt.assertions.isEqualTo
import strikt.assertions.isLessThan
import strikt.assertions.isTrue
import java.net.ServerSocket
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis
import com.izivia.ocpp.OcppVersion as OcppVersionWamp

class WebsocketServerShutdownTest {

    private fun getFreePort(): Int =
        ServerSocket(0).use { it.localPort }

    @Test
    fun `shutdown waits for a pending call to a charging station before closing its connection`() {
        val port = getFreePort()
        val transport = WebsocketServer(
            setOf(OcppVersion.OCPP_1_6),
            "ws",
            settings = OcppWampServerSettings(timeoutInMs = 5_000)
        )
        transport.receiveMessageClass(
            clazz = AuthorizeReq::class,
            action = "Authorize",
            ocppVersion = OcppVersion.OCPP_1_6,
            onAction = { _, _: AuthorizeReq -> AuthorizeResp(IdTagInfo(AuthorizationStatus.Accepted)) },
            accept = { ChargingStationConfig(acceptConnection = true, soapUrl = null) }
        )
        val server = transport.asServer(port)
        server.start()

        try {
            val handlerStarted = CountDownLatch(1)
            val client = WebsocketClient("TEST1", OcppVersionWamp.OCPP_1_6, "ws://localhost:$port/ws")
            client.receiveMessageClass(ClearCacheReq::class, "ClearCache") { _: ClearCacheReq ->
                handlerStarted.countDown()
                Thread.sleep(300)
                ClearCacheResp(ClearCacheStatus.Accepted)
            }
            client.connect()

            val response = CompletableFuture.supplyAsync {
                transport.sendMessageClass(ClearCacheResp::class, "TEST1", "ClearCache", ClearCacheReq())
            }
            expectThat(handlerStarted.await(5, TimeUnit.SECONDS)).isTrue()

            val shutdownDuration = measureTimeMillis { transport.shutdown() }

            expectThat(response.get(5, TimeUnit.SECONDS).status).isEqualTo(ClearCacheStatus.Accepted)
            expectThat(shutdownDuration).isLessThan(2_000)
        } finally {
            server.stop()
        }
    }
}
