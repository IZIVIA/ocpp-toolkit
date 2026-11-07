package com.izivia.ocpp.wamp.client.impl

import com.izivia.ocpp.OcppVersion.OCPP_1_6
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.utils.OcppErrorCategory
import com.izivia.ocpp.utils.OcppErrorEvent
import com.izivia.ocpp.utils.OcppErrorListener
import com.izivia.ocpp.wamp.client.OcppWampClient
import com.izivia.ocpp.wamp.client.WampOnActionHandler
import com.izivia.ocpp.wamp.getFreePort
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageType
import com.izivia.ocpp.wamp.server.impl.Undertow
import org.http4k.core.Request
import org.http4k.core.Uri
import org.http4k.routing.websocket.bind
import org.http4k.routing.websockets
import org.http4k.server.asServer
import org.http4k.websocket.Websocket
import org.http4k.websocket.WsMessage
import org.http4k.websocket.WsResponse
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull
import strikt.assertions.isNull
import strikt.assertions.isSameInstanceAs
import strikt.assertions.single
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit

class OkHttpOcppWampClientErrorListenerTest {

    private val call = """[2,"msg-1","Heartbeat",{}]"""
    private val handlerFailure = IllegalStateException("handler failure")

    @Test
    fun `should report a message that cannot be parsed without answering`() {
        val run = exchange("not a wamp message")

        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.PROTOCOL_ERROR)
            get { ocppId }.isEqualTo(OCPP_ID)
            get { ocppVersion }.isEqualTo("ocpp1.6")
            get { action }.isNull()
            get { messageId }.isNull()
            get { errorCode }.isNull()
        }
        expectThat(run.answers).isEmpty()
    }

    @Test
    fun `should report a call without a handler`() {
        val run = exchange(call)

        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.UNHANDLED_ACTION)
            get { ocppId }.isEqualTo(OCPP_ID)
            get { ocppVersion }.isEqualTo("ocpp1.6")
            get { action }.isEqualTo("Heartbeat")
            get { messageId }.isEqualTo("msg-1")
            get { errorCode }.isEqualTo(MessageErrorCode.INTERNAL_ERROR)
            get { cause }.isNull()
        }
        expectThat(run.answers).single().isCallError()
    }

    @Test
    fun `should report a throwing handler without answering`() {
        val run = exchange(call, onAction = { _, _ -> throw handlerFailure })

        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.HANDLER_ERROR)
            get { action }.isEqualTo("Heartbeat")
            get { messageId }.isEqualTo("msg-1")
            get { errorCode }.isNull()
            get { cause }.isSameInstanceAs(handlerFailure)
        }
        expectThat(run.answers).isEmpty()
    }

    @Test
    fun `should not answer a throwing handler without a listener`() {
        val run = exchange(call, errorListener = null, onAction = { _, _ -> throw handlerFailure })

        expectThat(run.answers).isEmpty()
    }

    @Test
    fun `should report nothing when a handler answers`() {
        val run = exchange(call, onAction = { _, msg -> WampMessage.CallResult(msg.msgId, "{}") })

        expectThat(run.events).isEmpty()
        expectThat(run.answers).single().isEqualTo("""[3,"msg-1",{}]""")
    }

    @Test
    fun `should answer a call without a handler the same without a listener`() {
        val run = exchange(call, errorListener = null)

        expectThat(run.answers).single().isCallError()
    }

    @Test
    fun `should answer a call without a handler the same when the listener throws`() {
        val run = exchange(call, errorListener = { throw IllegalStateException("listener failure") })

        expectThat(run.answers).single().isCallError()
    }

    @Test
    fun `should pass the listener through newClient`() {
        val run = exchange(call, newClient = true)

        expectThat(run.events).single().get { category }.isEqualTo(OcppErrorCategory.UNHANDLED_ACTION)
    }

    private fun strikt.api.Assertion.Builder<String>.isCallError() =
        get { WampMessage.parse(this) }.isNotNull().and {
            get { msgType }.isEqualTo(WampMessageType.CALL_ERROR)
            get { msgId }.isEqualTo("msg-1")
            get { errorCode }.isEqualTo(MessageErrorCode.INTERNAL_ERROR)
            get { errorDescription }.isEqualTo("No handler found")
        }

    private class Run(val answers: List<String>, val events: List<OcppErrorEvent>)

    /** Sends [message] to the client from a raw WebSocket server, which can send anything, and collects the answers. */
    private fun exchange(
        message: String,
        errorListener: OcppErrorListener? = OcppErrorListener { },
        onAction: WampOnActionHandler? = null,
        newClient: Boolean = false
    ): Run {
        val events = CopyOnWriteArrayList<OcppErrorEvent>()
        val listener = errorListener?.let { delegate ->
            OcppErrorListener { event ->
                events += event
                delegate.onError(event)
            }
        }
        val port = getFreePort()
        val csms = CompletableFuture<Websocket>()
        val answers = LinkedBlockingQueue<String>()
        val server = websockets(
            "/ws/{ocppId}" bind { _: Request ->
                WsResponse { ws ->
                    ws.onMessage { answers.add(it.bodyString()) }
                    csms.complete(ws)
                }
            }
        ).asServer(Undertow(port, enableHttp2 = false, wsSubprotocols = setOf(OCPP_1_6.subprotocol))).start()
        val target = Uri.of("ws://localhost:$port/ws")
        val client = if (newClient) {
            OcppWampClient.newClient(target, OCPP_ID, OCPP_1_6, errorListener = listener)
        } else {
            OkHttpOcppWampClient(target, OCPP_ID, OCPP_1_6, errorListener = listener)
        }
        try {
            onAction?.let(client::onAction)
            client.connect()
            csms.get(5, TimeUnit.SECONDS).send(WsMessage(message))
            val received = mutableListOf<String>()
            // the client handles messages on another thread: wait until it stays silent
            while (true) {
                received += answers.poll(1, TimeUnit.SECONDS) ?: break
            }
            return Run(received, events.toList())
        } finally {
            client.close()
            server.stop()
        }
    }

    companion object {
        private const val OCPP_ID = "CS1"
    }
}
