package com.izivia.ocpp.wamp

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion.OCPP_1_6
import com.izivia.ocpp.wamp.client.OcppWampClient
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.server.ConnectionAuthorizer
import com.izivia.ocpp.wamp.server.ConnectionRequest
import com.izivia.ocpp.wamp.server.OcppWampServer
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import com.izivia.ocpp.wamp.server.asServer
import com.izivia.ocpp.wamp.server.impl.EventsListeners
import org.http4k.core.Uri
import org.junit.jupiter.api.Test
import strikt.api.expect
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo
import java.security.MessageDigest
import java.util.Base64
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicInteger

class WampConnectionAuthorizerTest {
    private val port = getFreePort()
    private val passwords = mapOf(
        "CS1" to listOf("s3cr3t".toByteArray()),
        "CS16" to AUTHORIZATION_KEY.let { listOf(it.hexToByteArray(), it.toByteArray()) },
    )
    private val events = CopyOnWriteArrayList<String>()
    private val accepts = AtomicInteger()

    private val listeners = EventsListeners(
        onWsConnectHandler = { id, _ -> events += "connect $id" },
        onWsReconnectHandler = { id, _ -> events += "reconnect $id" },
        onWsCloseHandler = { id, _ -> events += "close $id" }
    )

    @Test
    fun `station with valid basic credentials connects and has its calls handled`() {
        val seen = CopyOnWriteArrayList<ConnectionRequest>()
        val server = start(ConnectionAuthorizer { request ->
            seen += request
            authorizer.authorize(request)
        })
        val client = client("CS1", basicAuthorization("CS1", "s3cr3t"))

        try {
            client.connect()

            expectThat(client.sendBlocking(WampMessage.Call("1", "Heartbeat", "{}")).payload)
                .isEqualTo("""{"handled":true}""")
            expectThat(seen.single().ocppId).isEqualTo("CS1")
            expectThat(seen.single().headers).contains("Authorization" to basicAuthorization("CS1", "s3cr3t").second)
        } finally {
            client.close()
            server.stop()
        }
    }

    @Test
    fun `station with a wrong password gets a 401 basic challenge and is never notified`() {
        val server = start(authorizer)

        try {
            val handshake = handshake("CS1", basicAuthorization("CS1", "wrong"))

            expect {
                that(handshake.status).isEqualTo(401)
                that(handshake.wwwAuthenticate).isEqualTo("""Basic realm="OCPP"""")
                that(accepts.get()).isEqualTo(0)
                that(events).isEmpty()
            }
        } finally {
            server.stop()
        }
    }

    @Test
    fun `unknown station without credentials gets 401 rather than 404`() {
        val server = start(authorizer, accept = { it in passwords })

        try {
            expectThat(handshake("UNKNOWN").status).isEqualTo(401)
        } finally {
            server.stop()
        }
    }

    @Test
    fun `authorized station refused by every handler still gets 404`() {
        val server = start(ConnectionAuthorizer { _ -> true }, accept = { false })

        try {
            expectThat(handshake("CS1").status).isEqualTo(404)
        } finally {
            server.stop()
        }
    }

    @Test
    fun `refused station does not evict the connected station with the same id`() {
        val (wamp, server) = startWamp(authorizer)
        val connected = client("CS1", basicAuthorization("CS1", "s3cr3t"))
        connected.onAction { _, msg -> WampMessage.CallResult(msg.msgId, """{"answeredBy":"CS1"}""") }

        try {
            connected.connect()

            val refused = handshake("CS1", basicAuthorization("CS1", "wrong"))
            val answer = wamp.sendBlocking("CS1", WampMessage.Call("1", "Reset", "{}"), timeoutInMs = 5_000)

            expect {
                that(refused.status).isEqualTo(401)
                that(events.toList()).isEqualTo(listOf("connect CS1"))
                that(answer.payload).isEqualTo("""{"answeredBy":"CS1"}""")
            }
        } finally {
            connected.close()
            server.stop()
        }
    }

    @Test
    fun `throwing authorizer refuses the station`() {
        val server = start(ConnectionAuthorizer { _ -> error("password store unavailable") })

        try {
            val handshake = handshake("CS1", basicAuthorization("CS1", "s3cr3t"))

            expect {
                that(handshake.status).isEqualTo(401)
                that(accepts.get()).isEqualTo(0)
                that(events).isEmpty()
            }
        } finally {
            server.stop()
        }
    }

    @Test
    fun `OCPP 1_6 station sending the bytes of its authorization key connects`() {
        val server = start(authorizer)

        try {
            expectThat(handshake("CS16", basicAuthorization("CS16", AUTHORIZATION_KEY.hexToByteArray())).status).isEqualTo(101)
        } finally {
            server.stop()
        }
    }

    @Test
    fun `OCPP 1_6 station sending the hexadecimal form of its authorization key connects`() {
        val server = start(authorizer)

        try {
            expectThat(handshake("CS16", basicAuthorization("CS16", AUTHORIZATION_KEY)).status).isEqualTo(101)
        } finally {
            server.stop()
        }
    }

    @Test
    fun `connection request does not show its headers when printed`() {
        val request = ConnectionRequest("CS1", listOf(basicAuthorization("CS1", "s3cr3t")))

        expectThat(request.toString()).isEqualTo("ConnectionRequest(ocppId=CS1)")
    }

    // a hand-written Basic authorizer, as an application may write its own ConnectionAuthorizer
    private val authorizer = ConnectionAuthorizer { request ->
        val expected = passwords[request.ocppId] ?: return@ConnectionAuthorizer false
        val credentials = request.headers
            .firstOrNull { (name, _) -> name.equals("Authorization", ignoreCase = true) }?.second
            ?.takeIf { it.startsWith("Basic ", ignoreCase = true) }
            ?.let { Base64.getDecoder().decode(it.substring(6).trim()) }
            ?: return@ConnectionAuthorizer false
        // compared as bytes: the username cannot hold a ':', but an OCPP 1.6 binary password can, and is not UTF-8
        val colon = credentials.indexOf(':'.code.toByte())
        if (colon < 0) return@ConnectionAuthorizer false
        val password = credentials.copyOfRange(colon + 1, credentials.size)
        // the username is the station id (A00.FR.204)
        String(credentials, 0, colon, Charsets.UTF_8) == request.ocppId &&
            expected.any { MessageDigest.isEqual(password, it) }
    }

    private fun start(authorizer: ConnectionAuthorizer, accept: (CSOcppId) -> Boolean = { true }) =
        startWamp(authorizer, accept).second

    private fun startWamp(authorizer: ConnectionAuthorizer, accept: (CSOcppId) -> Boolean = { true }) =
        OcppWampServer.newServer(setOf(OCPP_1_6), listeners = listeners, authorizer = authorizer).let { wamp ->
            wamp.register(object : OcppWampServerHandler {
                override fun accept(ocppId: CSOcppId): Boolean {
                    accepts.incrementAndGet()
                    return accept(ocppId)
                }

                override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? =
                    WampMessage.CallResult(msg.msgId, """{"handled":true}""")
            })
            wamp to wamp.asServer(port).start()
        }

    private fun client(ocppId: CSOcppId, vararg headers: Pair<String, String>) =
        OcppWampClient.newClient(
            Uri.of("ws://localhost:$port/ws"),
            ocppId,
            OCPP_1_6,
            timeoutInMs = 5_000,
            autoReconnect = false,
            headers = headers.toList()
        )

    private fun handshake(ocppId: CSOcppId, vararg headers: Pair<String, String>) = handshake(port, ocppId, *headers)

    companion object {
        // 16 random-looking bytes holding a ':' (3a) and a sequence that is not valid UTF-8 (c3 28)
        private const val AUTHORIZATION_KEY = "ff3ac3280102030405060708090a0b0c"
    }
}
