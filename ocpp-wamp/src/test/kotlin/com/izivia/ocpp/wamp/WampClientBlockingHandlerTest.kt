package com.izivia.ocpp.wamp

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion.OCPP_1_6
import com.izivia.ocpp.wamp.client.OcppWampClient
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.server.OcppWampServer
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import com.izivia.ocpp.wamp.server.asServer
import com.izivia.ocpp.wamp.server.impl.EventsListeners
import org.http4k.core.Uri
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.all
import strikt.assertions.hasSize
import strikt.assertions.isEqualTo
import strikt.assertions.isTrue
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

/**
 * Call handlers block their thread: a `CSApi` implementation waiting for a simulation loop or a database answers
 * only once it is done. The client must keep accepting calls, and processing the results of its own calls, while
 * they do. The dispatcher pools are shared by the whole JVM, so each test spreads the calls over several stations.
 */
class WampClientBlockingHandlerTest {
    private val port = getFreePort()
    private val cores = Runtime.getRuntime().availableProcessors()

    @Test
    fun `should run more blocking call handlers at once than there are cores`() {
        // the parallelism of Dispatchers.Default, plus one
        val stations = cores.coerceAtLeast(2) + 1
        // the parallelism of Dispatchers.IO
        assumeTrue(stations <= 64)
        val arrived = CountDownLatch(stations)

        val payloads = callEveryStation(stations) { _, msg ->
            arrived.countDown()
            WampMessage.CallResult(msg.msgId, """{"allArrived":${arrived.await(2, TimeUnit.SECONDS)}}""")
        }

        expectThat(payloads).hasSize(stations).all { isEqualTo("""{"allArrived":true}""") }
    }

    @Test
    fun `should process call results while call handlers occupy every thread`() {
        // the parallelism of Dispatchers.IO
        val stations = cores.coerceAtLeast(64)
        val arrived = CountDownLatch(stations)

        val payloads = callEveryStation(stations) { client, msg ->
            arrived.countDown()
            // every handler holds its thread before calling, so no thread is left for the results
            val allArrived = arrived.await(5, TimeUnit.SECONDS)
            val answered = runCatching { client.sendBlocking(WampMessage.Call("out", "Heartbeat", "{}")) }.isSuccess
            WampMessage.CallResult(msg.msgId, """{"allArrived":$allArrived,"answered":$answered}""")
        }

        expectThat(payloads).hasSize(stations).all { isEqualTo("""{"allArrived":true,"answered":true}""") }
    }

    /**
     * Connects [stations] charging stations whose calls are answered by [handler], sends a call to all of them at
     * once from the server, and returns the payloads of their answers.
     */
    private fun callEveryStation(
        stations: Int,
        handler: (OcppWampClient, WampMessage) -> WampMessage
    ): List<String> {
        val registered = CountDownLatch(stations)
        val transport = OcppWampServer.newServer(
            setOf(OCPP_1_6),
            listeners = EventsListeners(onWsConnectHandler = { _, _ -> registered.countDown() })
        )
        val server = transport.asServer(port)
        transport.register(object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId): Boolean = true
            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage =
                WampMessage.CallResult(msg.msgId, "{}")
        })
        server.start()

        val clients = (0 until stations).map { i ->
            OcppWampClient.newClient(
                Uri.of("ws://localhost:$port/ws"),
                "CS$i",
                OCPP_1_6,
                timeoutInMs = 5_000,
                autoReconnect = false
            )
        }
        try {
            clients.forEach { client ->
                client.onAction { _, msg -> handler(client, msg) }
                client.connect()
            }
            // a client is connected before the server registers it: a station missing the call would leave every
            // other handler waiting at the rendezvous
            expectThat(registered.await(5, TimeUnit.SECONDS)).isTrue()

            val payloads = arrayOfNulls<String>(stations)
            (0 until stations)
                .map { i ->
                    thread {
                        payloads[i] = runCatching {
                            transport.sendBlocking("CS$i", WampMessage.Call("in", "TriggerMessage", "{}")).payload
                        }.getOrElse { it.toString() }
                    }
                }
                .forEach { it.join() }
            return payloads.map { it.toString() }
        } finally {
            clients.forEach { it.close() }
            server.stop()
        }
    }
}
