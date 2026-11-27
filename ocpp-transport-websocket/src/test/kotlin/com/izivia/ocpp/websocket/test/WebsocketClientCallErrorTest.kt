package com.izivia.ocpp.websocket.test

import ch.qos.logback.classic.spi.ThrowableProxyUtil
import com.izivia.ocpp.core16.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core16.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.transport.OcppCallErrorException
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageType
import com.izivia.ocpp.wamp.server.impl.NoConnectionException
import com.izivia.ocpp.websocket.WebsocketClient
import com.izivia.ocpp.websocket.WebsocketServer
import com.izivia.ocpp.websocket.asServer
import org.junit.jupiter.api.Test
import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.assertions.any
import strikt.assertions.contains
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import strikt.assertions.isFailure
import strikt.assertions.isNotNull
import java.net.ServerSocket
import kotlin.time.Clock
import com.izivia.ocpp.OcppVersion as OcppVersionWamp

/** A [WebsocketClient] whose handler fails answers the CALL of a real server with a CALLERROR. */
class WebsocketClientCallErrorTest {

    private val sentinelFailure = IllegalStateException("handler failure for $SENTINEL")

    @Test
    fun `should answer a throwing handler with an empty details object and without its message`() {
        val response = withThrowingClient { transport ->
            transport.server.sendBlocking(OCPP_ID, WampMessage.Call("msg-1", "ClearCache", "{}"))
        }

        // OCPP-J 1.6 and 2.0.1 §4.2.3: "If there are no error details you MUST fill in an empty object {}."
        expectThat(response) {
            get { msgType }.isEqualTo(WampMessageType.CALL_ERROR)
            get { msgId }.isEqualTo("msg-1")
            get { errorCode }.isEqualTo(MessageErrorCode.INTERNAL_ERROR)
            get { errorDescription }.isEqualTo("Internal error")
            get { payload }.isEqualTo("{}")
            get { toJson() }.not().contains(SENTINEL)
        }
    }

    @Test
    fun `should report the empty details object to a caller of a toolkit peer`() {
        expectCatching {
            withThrowingClient { transport ->
                transport.sendMessageClass(ClearCacheResp::class, OCPP_ID, "ClearCache", ClearCacheReq())
            }
        }
            .isFailure()
            .isA<OcppCallErrorException>()
            .get { payload }.isEqualTo("{}")
    }

    @Test
    fun `should log a throwing handler with the message id of the call escaped`() {
        val logs = withToolkitLogs {
            withThrowingClient { transport ->
                transport.server.sendBlocking(OCPP_ID, WampMessage.Call("msg-1\r\nFORGED", "ClearCache", "{}"))
            }
        }

        expectThat(logs.map { it.formattedMessage }).any {
            contains("while handling ClearCache [msgId=msg-1\\u000d\\u000aFORGED]")
        }
        expectNoRawLineBreak(logs)
    }

    @Test
    fun `should log a throwing handler with its class and stack trace but without its message`() {
        val logs = withToolkitLogs {
            withThrowingClient { transport ->
                transport.server.sendBlocking(OCPP_ID, WampMessage.Call("msg-1", "ClearCache", "{}"))
            }
        }

        // the client has no error listener: the log is the only place the stack trace of the failure can reach
        val failure = logs.single { it.formattedMessage.contains("while handling ClearCache") }
        expectThat(failure) {
            get { formattedMessage }.not().contains(SENTINEL)
            get { throwableProxy }.isNotNull().and {
                get { message }.isEqualTo(IllegalStateException::class.java.name)
                get { stackTraceElementProxyArray.map { it.stackTraceElement } }
                    .isEqualTo(sentinelFailure.stackTrace.toList())
                get { ThrowableProxyUtil.asString(this) }.not().contains(SENTINEL)
            }
        }
    }

    /** Runs [send] against a real server, with a connected client whose ClearCache handler throws. */
    private fun <T> withThrowingClient(send: (WebsocketServer) -> T): T {
        val port = ServerSocket(0).use { it.localPort }
        val transport = WebsocketServer(setOf(OcppVersion.OCPP_1_6), "ws")
        transport.receiveMessageClass(
            clazz = HeartbeatReq::class,
            action = "Heartbeat",
            ocppVersion = OcppVersion.OCPP_1_6,
            onAction = { _, _: HeartbeatReq -> HeartbeatResp(Clock.System.now()) },
            accept = { ChargingStationConfig(acceptConnection = true, soapUrl = null) }
        )
        val server = transport.asServer(port)
        val client = WebsocketClient(OCPP_ID, OcppVersionWamp.OCPP_1_6, "ws://localhost:$port/ws")
        server.start()
        try {
            client.receiveMessageClass(ClearCacheReq::class, "ClearCache") { _: ClearCacheReq ->
                throw sentinelFailure
            }
            client.connect()
            return awaitConnected { send(transport) }
        } finally {
            client.close()
            server.stop()
        }
    }

    /**
     * Retries [action] until the charging station connection is registered server-side: the client is connected
     * once it gets the handshake response, before the server registers the connection.
     */
    private fun <T> awaitConnected(timeoutMs: Long = 5_000, action: () -> T): T {
        val deadline = System.currentTimeMillis() + timeoutMs
        while (true) {
            try {
                return action()
            } catch (e: NoConnectionException) {
                if (System.currentTimeMillis() >= deadline) throw e
                Thread.sleep(50)
            }
        }
    }

    private companion object {
        const val OCPP_ID = "CS1"
        const val SENTINEL = "SENTINEL-idTag-4242"
    }
}
