package io.simatix.ev.ocpp.wamp

import io.simatix.ev.ocpp.CSOcppId
import io.simatix.ev.ocpp.OcppVersion
import io.simatix.ev.ocpp.wamp.client.OcppWampClient
import io.simatix.ev.ocpp.wamp.messages.WampMessage
import io.simatix.ev.ocpp.wamp.messages.WampMessageMeta
import io.simatix.ev.ocpp.wamp.messages.WampMessageType
import io.simatix.ev.ocpp.wamp.server.OcppWampServer
import io.simatix.ev.ocpp.wamp.server.OcppWampServerHandler
import kotlinx.datetime.Clock
import org.http4k.core.Uri
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Disabled
import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isFailure
import strikt.assertions.isLessThan
import kotlin.system.measureTimeMillis

class WampIntegrationTest {
    @Test
    fun `should heartbeat`() {
        val heartbeatResponsePayload = """{"currentTime":"${Clock.System.now()}"}"""
        val port = 12345

        val server = OcppWampServer.newServer(port, setOf(OcppVersion.OCPP_1_6, OcppVersion.OCPP_2_0))
        server.register(object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId): Boolean = "TEST1" == ocppId

            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? =
                when (msg.action?.lowercase()) {
                    "heartbeat" ->
                        WampMessage.CallResult(msg.msgId, heartbeatResponsePayload)
                    else -> {
                        println("unhandled action for message: ${msg.toJson()}")
                        WampMessage.CallError(msg.msgId, "{}")
                    }
                }
        })
        server.start()

        try {
            val client = OcppWampClient.newClient(Uri.of("ws://localhost:$port/ws"), "TEST1", OcppVersion.OCPP_1_6)
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
    fun `should timeout when calling server`() {
        val port = 12345

        val server = OcppWampServer.newServer(port, setOf(OcppVersion.OCPP_1_6, OcppVersion.OCPP_2_0))
        server.register(object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId): Boolean = "TEST1" == ocppId

            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? {
                Thread.sleep(500)
                return null
            }
        })
        server.start()

        try {
            val client = OcppWampClient.newClient(Uri.of("ws://localhost:$port/ws"), "TEST1",
                OcppVersion.OCPP_1_6, timeoutInMs = 200)
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
        val port = 12345

        val server = OcppWampServer.newServer(port, setOf(OcppVersion.OCPP_1_6, OcppVersion.OCPP_2_0))
        server.register(object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId): Boolean = "TEST1" == ocppId
            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? = null
        })
        server.start()

        try {
            val client = OcppWampClient.newClient(Uri.of("ws://localhost:$port/ws"), "TEST1", OcppVersion.OCPP_1_6)
            client.onAction { meta: WampMessageMeta, msg: WampMessage ->
                when (msg.action?.lowercase()) {
                    "remotebeat" ->
                        WampMessage.CallResult(msg.msgId, heartbeatResponsePayload)
                    else -> {
                        println("unhandled action for message: ${msg.toJson()}")
                        WampMessage.CallError(msg.msgId, "{}")
                    }
                }
            }
            client.connect()

            val r = server.sendBlocking("TEST1", WampMessage.Call("1", "remotebeat", "{}"))

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
        val port = 12345

        val server = OcppWampServer.newServer(port, setOf(OcppVersion.OCPP_1_6, OcppVersion.OCPP_2_0), 200)
        server.register(object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId): Boolean = "TEST1" == ocppId
            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? = null
        })
        server.start()

        try {
            val client = OcppWampClient.newClient(Uri.of("ws://localhost:$port/ws"), "TEST1", OcppVersion.OCPP_1_6)
            client.onAction { meta: WampMessageMeta, msg: WampMessage ->
                Thread.sleep(500)
                null
            }
            client.connect()

            expectCatching {
                server.sendBlocking("TEST1", WampMessage.Call("1", "remotebeat", "{}"))
            }.isFailure()

            client.close()
        } finally {
            server.stop()
        }
    }


    @Disabled("Disabled until it has been resolved")
    @Test
    fun `should 404 on unknown ocpp id`() {
        val port = 12345

        val server = OcppWampServer.newServer(port, setOf(OcppVersion.OCPP_1_6, OcppVersion.OCPP_2_0))
        server.register(object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId): Boolean = "TEST1" == ocppId

            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? = null
        })
        server.start()

        try {
            val client = OcppWampClient.newClient(Uri.of("ws://localhost:$port/ws"), "TEST2", OcppVersion.OCPP_1_6,
                timeoutInMs = 600)
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
    fun `should disconnect on server close`() {
        val port = 12345

        val server = OcppWampServer.newServer(port, setOf(OcppVersion.OCPP_1_6, OcppVersion.OCPP_2_0))
        server.register(object : OcppWampServerHandler {
            override fun accept(ocppId: CSOcppId): Boolean = "TEST1" == ocppId
            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? = null
        })
        server.start()

        try {
            val client = OcppWampClient.newClient(Uri.of("ws://localhost:$port/ws"), "TEST1", OcppVersion.OCPP_1_6)
            client.connect()
            server.stop()

            expectCatching { client.sendBlocking(WampMessage.Call("1", "Heartbeat", "{}")) }.isFailure()

            client.close()
        } finally {
            server.stop()
        }
    }

    @Disabled("Disabled until it has been resolved")
    @Test
    fun `should timeout on server not available`() {
        val port = 12346

        val client = OcppWampClient.newClient(
            Uri.of("ws://localhost:$port/ws"), "TEST1", OcppVersion.OCPP_1_6,
            timeoutInMs = 50)
        val time = measureTimeMillis {
            expectCatching { client.connect() }.isFailure()
        }
        expectThat(time).isLessThan(100) // 50ms timeout + 50ms tolerance
    }
}
