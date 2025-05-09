package com.izivia.ocpp.wamp

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.OcppVersion.OCPP_1_6
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.wamp.client.OcppWampClient
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.messages.WampMessageType
import com.izivia.ocpp.wamp.server.OcppWampServer
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import com.izivia.ocpp.wamp.server.asServer
import com.izivia.ocpp.wamp.server.impl.EventsListeners
import com.izivia.ocpp.wamp.server.impl.OcppWampServerSettings
import kotlinx.datetime.Clock
import org.http4k.core.Uri
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.assertions.*
import kotlin.system.measureTimeMillis
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class WampIntegrationTest {
    private val port = getFreePort()

    @Test
    fun `should heartbeat`() {
        val heartbeatResponsePayload = """{"currentTime":"${Clock.System.now()}"}"""

        val wampServer = OcppWampServer.newServer(setOf(OCPP_1_6, OcppVersion.OCPP_2_0))
        val server = wampServer.asServer(port)
        wampServer.register(object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId): Boolean = "TEST1" == ocppId

            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? =
                when (msg.action?.lowercase()) {
                    "heartbeat" ->
                        WampMessage.CallResult(msg.msgId, heartbeatResponsePayload)

                    else -> {
                        println("unhandled action for message: ${msg.toJson()}")
                        WampMessage.CallError(
                            msg.msgId,
                            MessageErrorCode.NOT_SUPPORTED,
                            "",
                            "{}"
                        )
                    }
                }
        })
        server.start()

        try {
            val client = OcppWampClient.newClient(Uri.of("ws://localhost:$port/ws"), "TEST1", OCPP_1_6)
            client.connect()

            val r = client.sendBlocking(WampMessage.Call("1", "Heartbeat", "{}"))
            expectThat(r) {
                get { msgId }.isEqualTo("1")
                get { msgType }.isEqualTo(WampMessageType.CALL_RESULT)
                get { payload }.isEqualTo(heartbeatResponsePayload)
            }

            client.close()
        } finally {
            server.stop()
        }
    }

    @Test
    fun `should send notifications on connect - disconnect - reconnect`() {
        val heartbeatResponsePayload = """{"currentTime":"${Clock.System.now()}"}"""

        val connectedEvents: MutableList<CSOcppId> = ArrayList()
        val closeEvents: MutableList<CSOcppId> = ArrayList()
        val reconnectedEvents: MutableList<CSOcppId> = ArrayList()
        val wampServer = OcppWampServer.newServer(
            setOf(OCPP_1_6),
            listeners = EventsListeners(
                onWsConnectHandler = { id, headers ->
                    connectedEvents.add(id)
                },
                onWsCloseHandler = { id, headers ->
                    closeEvents.add(id)
                },
                onWsReconnectHandler = { id, headers ->
                    reconnectedEvents.add(id)
                }
            )
        )
        val server = wampServer.asServer(port)
        wampServer.register(object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId): Boolean = "TEST1" == ocppId

            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? =
                WampMessage.CallResult(msg.msgId, heartbeatResponsePayload)
        })
        server.start()

        try {
            expectThat(connectedEvents).isEmpty()
            expectThat(closeEvents).isEmpty()
            expectThat(reconnectedEvents).isEmpty()

            val client =
                OcppWampClient.newClient(Uri.of("ws://localhost:$port/ws"), "TEST1", OCPP_1_6, autoReconnect = false)
            client.connect()

            runWithTimeout {
                expectThat(connectedEvents).hasSize(1).first().isEqualTo("TEST1")
                expectThat(closeEvents).isEmpty()
                expectThat(reconnectedEvents).isEmpty()
                connectedEvents.clear()
            }

            client.sendBlocking(WampMessage.Call("1", "Heartbeat", "{}"))
            // should not send events on message
            expectThat(connectedEvents).isEmpty()
            expectThat(closeEvents).isEmpty()
            expectThat(reconnectedEvents).isEmpty()

            client.close()
            runWithTimeout {
                expectThat(connectedEvents).isEmpty()
                expectThat(closeEvents).hasSize(1).first().isEqualTo("TEST1")
                expectThat(reconnectedEvents).isEmpty()
                closeEvents.clear()
            }

            // reconnect after proper close will send a connect event, because we have sent a close event before
            client.connect()
            runWithTimeout {
                expectThat(connectedEvents).hasSize(1).first().isEqualTo("TEST1")
                expectThat(closeEvents).isEmpty()
                expectThat(reconnectedEvents).isEmpty()
                connectedEvents.clear()
            }

            // we simulate a reconnect with a second client on the asme ocpp id, it should close the first client
            // connection and trigger a reconnect event
            val client2 =
                OcppWampClient.newClient(Uri.of("ws://localhost:$port/ws"), "TEST1", OCPP_1_6, autoReconnect = false)
            client2.connect()
            runWithTimeout {
                expectThat(connectedEvents).isEmpty()
                expectThat(closeEvents).isEmpty()
                expectThat(reconnectedEvents).hasSize(1).first().isEqualTo("TEST1")
                reconnectedEvents.clear()
            }

            client.close()
            runWithTimeout {
                // no event, already disconnected
                expectThat(connectedEvents).isEmpty()
                expectThat(closeEvents).isEmpty()
                expectThat(reconnectedEvents).isEmpty()
            }

            client2.close()
            runWithTimeout {
                expectThat(connectedEvents).isEmpty()
                expectThat(closeEvents).hasSize(1).first().isEqualTo("TEST1")
                expectThat(reconnectedEvents).isEmpty()
            }
        } finally {
            server.stop()
        }
    }

    @Test
    fun `should timeout when calling server`() {
        val transport = OcppWampServer.newServer(setOf(OCPP_1_6, OcppVersion.OCPP_2_0))
        val server = transport.asServer(port)
        transport.register(object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId): Boolean = "TEST1" == ocppId

            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? {
                Thread.sleep(500)
                return null
            }
        })
        server.start()

        try {
            val client = OcppWampClient.newClient(
                Uri.of("ws://localhost:$port/ws"),
                "TEST1",
                OCPP_1_6,
                timeoutInMs = 200
            )
            client.connect()

            expectCatching {
                client.sendBlocking(WampMessage.Call("1", "Heartbeat", "{}"))
            }.isFailure()

            client.close()
        } finally {
            server.stop()
        }
    }

    @Test
    fun `should call from server to charging station`() {
        val heartbeatResponsePayload = """{"currentTime":"${Clock.System.now()}"}"""

        val transport = OcppWampServer.newServer(setOf(OCPP_1_6, OcppVersion.OCPP_2_0))
        val server = transport.asServer(port)
        transport.register(object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId): Boolean = "TEST1" == ocppId
            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? = null
        })
        server.start()

        try {
            val client = OcppWampClient.newClient(
                Uri.of("ws://localhost:$port/ws"),
                "TEST1",
                OCPP_1_6,
                autoReconnect = false
            )
            client.onAction { meta: WampMessageMeta, msg: WampMessage ->
                when (msg.action?.lowercase()) {
                    "remotebeat" ->
                        WampMessage.CallResult(msg.msgId, heartbeatResponsePayload)

                    else -> {
                        println("unhandled action for message: ${msg.toJson()}")
                        WampMessage.CallError(
                            msg.msgId,
                            MessageErrorCode.NOT_SUPPORTED,
                            "",
                            "{}"
                        )
                    }
                }
            }
            client.connect()

            val r = transport.sendBlocking("TEST1", WampMessage.Call("1", "remotebeat", "{}"))

            expectThat(r) {
                get { msgId }.isEqualTo("1")
                get { msgType }.isEqualTo(WampMessageType.CALL_RESULT)
                get { payload }.isEqualTo(heartbeatResponsePayload)
            }

            client.close()
        } finally {
            server.stop()
        }
    }

    @Test
    fun `should call from server to charging station timeout`() {
        val transport =
            OcppWampServer.newServer(setOf(OCPP_1_6, OcppVersion.OCPP_2_0), "ws", OcppWampServerSettings(200))
        val server = transport.asServer(port)
        transport.register(object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId): Boolean = "TEST1" == ocppId
            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? = null
        })
        server.start()

        try {
            val client = OcppWampClient.newClient(Uri.of("ws://localhost:$port/ws"), "TEST1", OCPP_1_6)
            client.onAction { meta: WampMessageMeta, msg: WampMessage ->
                Thread.sleep(500)
                null
            }
            client.connect()

            expectCatching {
                transport.sendBlocking("TEST1", WampMessage.Call("1", "remotebeat", "{}"))
            }.isFailure()

            client.close()
        } finally {
            server.stop()
        }
    }

    @Disabled("Disabled until it has been resolved")
    @Test
    fun `should 404 on unknown ocpp id`() {
        val transport = OcppWampServer.newServer(setOf(OCPP_1_6, OcppVersion.OCPP_2_0))
        val server = transport.asServer(port)
        transport.register(object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId): Boolean = "TEST1" == ocppId

            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? = null
        })
        server.start()

        try {
            val client = OcppWampClient.newClient(
                Uri.of("ws://localhost:$port/ws"),
                "TEST2",
                OCPP_1_6,
                timeoutInMs = 600
            )
            val time = measureTimeMillis {
                expectCatching { client.connect() }.isFailure()
            }
            expectThat(time)
                .describedAs("connection failure time ($time ms) for 404 should be fast")
                .isLessThan(500)
        } finally {
            server.stop()
        }
    }

    @Test
    fun `should cleanly fail on server close`() {
        val transport = OcppWampServer.newServer(setOf(OCPP_1_6, OcppVersion.OCPP_2_0))
        val server = transport.asServer(port)
        transport.register(object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId): Boolean = "TEST1" == ocppId
            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? = null
        })
        server.start()

        try {
            val client = OcppWampClient.newClient(
                Uri.of("ws://localhost:$port/ws"),
                "TEST1",
                OCPP_1_6,
                timeoutInMs = 500
            )
            client.connect()
            server.stop()

            expectCatching { client.sendBlocking(WampMessage.Call("1", "Heartbeat", "{}")) }.isFailure()

            client.close()
        } finally {
            server.stop()
        }
    }

    @Test
    fun `should cleanly fail on connection when no server`() {
        val client = OcppWampClient.newClient(
            Uri.of("ws://localhost:$port/ws"),
            "TEST1",
            OCPP_1_6,
            timeoutInMs = 500,
            autoReconnect = false
        )
        expectCatching { client.connect() }.isFailure()
        expectCatching { client.sendBlocking(WampMessage.Call("1", "Heartbeat", "{}")) }.isFailure()

        client.close()
    }

    @Test
    fun `should client be resilient to intermittent server lost`() {
        val transport = OcppWampServer.newServer(setOf(OCPP_1_6, OcppVersion.OCPP_2_0))
        val server = transport.asServer(port)
        transport.register(object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId): Boolean = "TEST1" == ocppId
            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? = null
        })
        server.start()

        try {
            val client = OcppWampClient.newClient(Uri.of("ws://localhost:$port/ws"), "TEST1", OCPP_1_6)
            client.connect()
            expectCatching { client.sendBlocking(WampMessage.Call("1", "Heartbeat", "{}")) }.isSuccess()

            // check we are resilient to connection loss after a first message has been sent
            server.stop()
            server.start()
            expectCatching { client.sendBlocking(WampMessage.Call("1", "Heartbeat", "{}")) }.isSuccess()

            // check we are resilient to connection loss with some delay before stop, start, and new attempt
            server.stop()
            Thread.sleep(50)
            server.start()
            Thread.sleep(50)
            expectCatching { client.sendBlocking(WampMessage.Call("1", "Heartbeat", "{}")) }.isSuccess()

            // close the client to check manual reconnect
            client.close()
            client.connect()
            // immediately stop without a single message sent - this usually triggers a brutal close of the connection
            server.stop()
            // restart immediately and check we can smoothly send a message
            server.start()
            expectCatching { client.sendBlocking(WampMessage.Call("1", "Heartbeat", "{}")) }.isSuccess()
            client.close()
        } finally {
            server.stop()
        }
    }

    @Test
    fun `should client immediately force reconnect attempt on send message`() {
        val transport = OcppWampServer.newServer(setOf(OCPP_1_6, OcppVersion.OCPP_2_0))
        val server = transport.asServer(port)
        transport.register(object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId): Boolean = "TEST1" == ocppId
            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? = null
        })
        server.start()

        try {
            val client = OcppWampClient.newClient(Uri.of("ws://localhost:$port/ws"), "TEST1", OCPP_1_6)
            client.connect()
            expectCatching { client.sendBlocking(WampMessage.Call("1", "Heartbeat", "{}")) }.isSuccess()

            // check we are resilient to connection loss with some delay before stop, start, and new attempt
            server.stop()
            Thread.sleep(100) // wait to make sure auto reconnect doesn't immediately reconnect
            server.start()
            Thread.sleep(100) // wait for the server to be ready
            val time = measureTimeMillis {
                expectCatching { client.sendBlocking(WampMessage.Call("1", "Heartbeat", "{}")) }.isSuccess()
            }
            expectThat(time).isLessThan(100) // make sure we dont wait for the 500ms of auto reconnect

            client.close()
        } finally {
            server.stop()
        }
    }

    @Test
    fun `should timeout on server not available`() {
        val port = 12346

        val client = OcppWampClient.newClient(
            Uri.of("ws://localhost:$port/ws"),
            "TEST1",
            OCPP_1_6,
            timeoutInMs = 50,
            autoReconnect = false
        )
        val time = measureTimeMillis {
            expectCatching { client.connect() }.isFailure()
        }
        expectThat(time).isLessThan(100) // 50ms timeout + 50ms tolerance
    }
}

fun runWithTimeout(
    timeout: Duration = 10.seconds,
    step: Duration = 100L.milliseconds,
    block: () -> Unit
) {
    val timeoutTimestamp = System.currentTimeMillis() + timeout.inWholeMilliseconds
    var attemptCount = 0
    while (true) {
        val startAttemptTimestamp = System.currentTimeMillis()
        try {
            attemptCount++
            block()
            break
        } catch (e: Throwable) {
            if (System.currentTimeMillis() > timeoutTimestamp) {
                throw e
            } else {
                val elapsed = System.currentTimeMillis() - startAttemptTimestamp
                println("failed attempt $attemptCount - took $elapsed ms - error: ${e.message}")
            }
        }
        Thread.sleep(step.inWholeMilliseconds)
    }
}
