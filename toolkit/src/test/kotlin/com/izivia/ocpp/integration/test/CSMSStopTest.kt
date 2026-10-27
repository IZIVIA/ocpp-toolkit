package com.izivia.ocpp.integration.test

import com.izivia.ocpp.core16.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core16.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core16.model.clearcache.enumeration.ClearCacheStatus
import com.izivia.ocpp.integration.CSMS
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.wamp.server.impl.OcppWampServerSettings
import com.izivia.ocpp.websocket.WebsocketClient
import com.izivia.ocpp.websocket.WebsocketServer
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import org.junit.jupiter.api.Test
import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isEqualTo
import strikt.assertions.isSuccess
import strikt.assertions.isTrue
import java.net.ServerSocket
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import com.izivia.ocpp.OcppVersion as OcppVersionWamp
import com.izivia.ocpp.core16.ChargePointOperations as ChargePointOperations16

class CSMSStopTest {

    private fun getFreePort(): Int =
        ServerSocket(0).use { it.localPort }

    @Test
    fun `stop lets a pending call to a charging station complete`() {
        val port = getFreePort()
        val transport = WebsocketServer(
            setOf(OcppVersion.OCPP_1_6),
            "ws",
            settings = OcppWampServerSettings(timeoutInMs = 5_000)
        )
        val csms = CSMS(
            transports = mapOf(transport to (port to setOf(OcppVersion.OCPP_1_6))),
            csmsApis = setOf(mockk<ChargePointOperations16>(relaxed = true)),
            fn = { ChargingStationConfig(acceptConnection = true, soapUrl = null) }
        )
        csms.start()

        var stopped = false
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

            csms.stop()
            stopped = true

            expectThat(response.get(5, TimeUnit.SECONDS).status).isEqualTo(ClearCacheStatus.Accepted)
        } finally {
            if (!stopped) csms.stop()
        }
    }

    @Test
    fun `stop releases the ports even when a transport fails to shut down`() {
        val port = getFreePort()
        val transport = WebsocketServer(setOf(OcppVersion.OCPP_1_6), "ws")
        val failingTransport = spyk(transport) {
            every { shutdown() } throws IllegalStateException("shutdown failed")
        }
        val csms = CSMS(
            transports = mapOf(failingTransport to (port to setOf(OcppVersion.OCPP_1_6))),
            csmsApis = setOf(mockk<ChargePointOperations16>(relaxed = true)),
            fn = { ChargingStationConfig(acceptConnection = true, soapUrl = null) }
        )
        csms.start()

        expectThrows<IllegalStateException> { csms.stop() }

        expectCatching { ServerSocket(port).close() }.isSuccess()
    }
}
