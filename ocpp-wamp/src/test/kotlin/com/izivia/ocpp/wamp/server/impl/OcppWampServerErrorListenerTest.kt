package com.izivia.ocpp.wamp.server.impl

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion.OCPP_1_6
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.utils.OcppErrorCategory
import com.izivia.ocpp.utils.OcppErrorEvent
import com.izivia.ocpp.utils.OcppErrorListener
import com.izivia.ocpp.wamp.getFreePort
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.messages.WampMessageType
import com.izivia.ocpp.wamp.server.OcppWampServer
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import com.izivia.ocpp.wamp.server.asServer
import io.mockk.every
import io.mockk.mockk
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.http4k.core.Method
import org.http4k.websocket.PushPullAdaptingWebSocket
import org.http4k.websocket.WsMessage
import org.http4k.websocket.WsStatus
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull
import strikt.assertions.isNull
import strikt.assertions.isSameInstanceAs
import strikt.assertions.single
import java.io.IOException
import java.net.SocketException
import java.nio.channels.ClosedChannelException
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.RejectedExecutionException
import java.util.concurrent.TimeUnit
import org.http4k.core.Request as Http4kRequest

class OcppWampServerErrorListenerTest {

    private val call = """[2,"msg-1","Heartbeat",{}]"""
    private val handlerFailure = IllegalStateException("handler failure")
    private val rejection = RejectedExecutionException("saturated")
    private val rejectingSettings = object : OcppWampServerSettings() {
        override fun buildCallsExecutor() = Executor { throw rejection }
    }
    private val directSettings = object : OcppWampServerSettings() {
        override fun buildCallsExecutor() = Executor { it.run() }
    }

    @Test
    fun `should report a malformed message without the context it could not read`() {
        val run = exchange(message = "not a wamp message")

        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.PROTOCOL_ERROR)
            get { ocppId }.isEqualTo(OCPP_ID)
            get { ocppVersion }.isEqualTo("ocpp1.6")
            get { errorCode }.isEqualTo(MessageErrorCode.INTERNAL_ERROR)
            get { messageId }.isNull()
            get { action }.isNull()
        }
    }

    @Test
    fun `should report a call without a handler`() {
        val run = exchange(onAction = { null })

        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.UNHANDLED_ACTION)
            get { ocppId }.isEqualTo(OCPP_ID)
            get { ocppVersion }.isEqualTo("ocpp1.6")
            get { action }.isEqualTo("Heartbeat")
            get { messageId }.isEqualTo("msg-1")
            get { errorCode }.isEqualTo(MessageErrorCode.INTERNAL_ERROR)
            get { cause }.isNull()
        }
        expectThat(WampMessage.parse(run.response!!)).isNotNull().and {
            get { msgType }.isEqualTo(WampMessageType.CALL_ERROR)
            get { msgId }.isEqualTo("msg-1")
            get { errorCode }.isEqualTo(MessageErrorCode.INTERNAL_ERROR)
        }
    }

    @Test
    fun `should report a throwing handler`() {
        val run = exchange(onAction = { throw handlerFailure })

        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.HANDLER_ERROR)
            get { action }.isEqualTo("Heartbeat")
            get { messageId }.isEqualTo("msg-1")
            get { errorCode }.isNull()
            get { cause }.isSameInstanceAs(handlerFailure)
        }
    }

    @Test
    fun `should report a throwing handler once when the executor runs calls on the message thread`() {
        val run = exchange(settings = directSettings, onAction = { throw handlerFailure })

        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.HANDLER_ERROR)
            get { cause }.isSameInstanceAs(handlerFailure)
        }
    }

    @Test
    fun `should report a response that cannot be serialized as a protocol error`() {
        val failure = IllegalStateException("cannot serialize")
        val response = mockk<WampMessage> { every { toJson() } throws failure }

        val run = exchange(onAction = { response })

        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.PROTOCOL_ERROR)
            get { action }.isEqualTo("Heartbeat")
            get { messageId }.isEqualTo("msg-1")
            get { cause }.isSameInstanceAs(failure)
        }
    }

    @Test
    fun `should report once a call the executor rejects`() {
        val run = exchange(settings = rejectingSettings)

        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.TRANSPORT_ERROR)
            get { ocppId }.isEqualTo(OCPP_ID)
            get { ocppVersion }.isEqualTo("ocpp1.6")
            get { action }.isEqualTo("Heartbeat")
            get { messageId }.isEqualTo("msg-1")
            get { cause }.isSameInstanceAs(rejection)
        }
    }

    @Test
    fun `should report a response that cannot be sent as a transport error`() {
        val failure = IOException("cannot send")
        val events = mutableListOf<OcppErrorEvent>()
        val websocket = connect(FakeWebsocket(failure), events) { WampMessage.CallResult(it.msgId, "{}") }

        runCatching { websocket.triggerMessage(WsMessage(call)) }

        expectThat(events.single()) {
            get { category }.isEqualTo(OcppErrorCategory.TRANSPORT_ERROR)
            get { action }.isEqualTo("Heartbeat")
            get { messageId }.isEqualTo("msg-1")
            get { cause }.isSameInstanceAs(failure)
        }
    }

    @Test
    fun `should report an unexpected websocket error`() {
        val failure = IllegalStateException("unexpected")
        val events = mutableListOf<OcppErrorEvent>()
        val websocket = connect(FakeWebsocket(), events)

        websocket.triggerError(failure)

        expectThat(events.single()) {
            get { category }.isEqualTo(OcppErrorCategory.TRANSPORT_ERROR)
            get { ocppId }.isEqualTo(OCPP_ID)
            get { cause }.isSameInstanceAs(failure)
        }
    }

    @Test
    fun `should not report a network failure`() {
        val events = mutableListOf<OcppErrorEvent>()
        val websocket = connect(FakeWebsocket(), events)

        websocket.triggerError(IOException("Broken pipe"))
        websocket.triggerError(ClosedChannelException())
        websocket.triggerError(SocketException("Connection reset"))

        expectThat(events).isEmpty()
    }

    @Test
    fun `should not report a handled call`() {
        val run = exchange(onAction = { WampMessage.CallResult(it.msgId, "{}") })

        expectThat(run.events).isEmpty()
    }

    @Test
    fun `should not report an empty message`() {
        val run = exchange(message = " ")

        expectThat(run.events).isEmpty()
    }

    @Test
    fun `should not report a client closing normally`() {
        val run = exchange(act = { it.close(1000, null) })

        expectThat(run.events).isEmpty()
    }

    @Test
    fun `should not report a client disconnecting abruptly`() {
        val run = exchange(act = { it.cancel() })

        expectThat(run.events).isEmpty()
    }

    @Test
    fun `should answer a malformed message the same way whatever the listener`() {
        expectSameResponseWhateverTheListener(message = "not a wamp message")
    }

    @Test
    fun `should answer a call without a handler the same way whatever the listener`() {
        expectSameResponseWhateverTheListener(onAction = { null })
    }

    @Test
    fun `should not answer a throwing handler whatever the listener`() {
        expectSameResponseWhateverTheListener(onAction = { throw handlerFailure })
    }

    private fun expectSameResponseWhateverTheListener(
        message: String = call,
        onAction: (WampMessage) -> WampMessage? = { null }
    ) {
        val withoutListener = exchange(message, onAction, listener = null).response
        val recording = exchange(message, onAction).response
        val throwing = exchange(message, onAction, listener = OcppErrorListener { error("listener failure") }).response

        expectThat(recording).isEqualTo(withoutListener)
        expectThat(throwing).isEqualTo(withoutListener)
    }

    private class Run(val response: String?, val events: List<OcppErrorEvent>)

    /** Sends one raw message through a real server and collects the answer and the events it produced. */
    private fun exchange(
        message: String = call,
        onAction: (WampMessage) -> WampMessage? = { null },
        settings: OcppWampServerSettings = OcppWampServerSettings(),
        events: MutableCollection<OcppErrorEvent> = CopyOnWriteArrayList(),
        listener: OcppErrorListener? = OcppErrorListener { events.add(it) },
        act: (WebSocket) -> Unit = { it.send(message) }
    ): Run {
        val wampServer = OcppWampServer.newServer(
            setOf(OCPP_1_6),
            settings = settings,
            errorListener = listener
        )
        wampServer.register(handlerOf(onAction))
        val port = getFreePort()
        val server = wampServer.asServer(port)
        server.start()
        val responses = LinkedBlockingQueue<String>()
        val connected = CountDownLatch(1)
        val http = OkHttpClient()
        val client = http.newWebSocket(
            Request.Builder().url("ws://localhost:$port/ws/$OCPP_ID").header("Sec-WebSocket-Protocol", "ocpp1.6").build(),
            object : WebSocketListener() {
                override fun onOpen(webSocket: WebSocket, response: Response) = connected.countDown()
                override fun onMessage(webSocket: WebSocket, text: String) {
                    responses.add(text)
                }
            }
        )
        try {
            expectThat(connected.await(5, TimeUnit.SECONDS)).isEqualTo(true)
            act(client)
            // long enough for a response, and for a duplicate event to show up
            val response = responses.poll(SETTLE_MS, TimeUnit.MILLISECONDS)
            Thread.sleep(SETTLE_MS)
            return Run(response, events.toList())
        } finally {
            client.close(1000, null)
            http.dispatcher.executorService.shutdown()
            server.stop()
        }
    }

    private fun handlerOf(onAction: (WampMessage) -> WampMessage?) = object : OcppWampServerHandler {
        override fun accept(ocppId: CSOcppId) = true
        override fun onAction(meta: WampMessageMeta, msg: WampMessage) = onAction(msg)
    }

    private class FakeWebsocket(private val sendFailure: Exception? = null) : PushPullAdaptingWebSocket() {
        override fun send(message: WsMessage) {
            sendFailure?.let { throw it }
        }

        override fun close(status: WsStatus) = Unit
    }

    /** Connects a fake websocket to the application, to fail in ways a real connection cannot reproduce on demand. */
    private fun connect(
        websocket: FakeWebsocket,
        events: MutableList<OcppErrorEvent>,
        onAction: (WampMessage) -> WampMessage? = { null }
    ): FakeWebsocket {
        val app = OcppWampServerApp(
            ocppVersions = setOf(OCPP_1_6),
            handlers = { listOf(handlerOf(onAction)) },
            ocppWsEndpoint = OcppWsEndpoint("ws"),
            settings = directSettings,
            errorListener = OcppErrorListener { events.add(it) }
        )
        val request = Http4kRequest(Method.GET, "/ws/$OCPP_ID").header("Sec-WebSocket-Protocol", "ocpp1.6")
        app.newRoutingHandler()(request).consumer(websocket)
        return websocket
    }

    private companion object {
        const val OCPP_ID = "CS1"
        const val SETTLE_MS = 300L
    }
}
