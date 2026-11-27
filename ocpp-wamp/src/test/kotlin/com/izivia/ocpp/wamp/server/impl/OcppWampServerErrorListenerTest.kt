package com.izivia.ocpp.wamp.server.impl

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
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
import org.slf4j.LoggerFactory
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.all
import strikt.assertions.contains
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo
import strikt.assertions.isFalse
import strikt.assertions.isLessThan
import strikt.assertions.isNotNull
import strikt.assertions.isNull
import strikt.assertions.isSameInstanceAs
import strikt.assertions.isTrue
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
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread
import kotlin.time.Duration.Companion.seconds
import kotlin.time.measureTime
import org.http4k.core.Request as Http4kRequest

class OcppWampServerErrorListenerTest {

    private val call = """[2,"msg-1","Heartbeat",{}]"""
    private val handlerFailure = IllegalStateException("handler failure $SENTINEL")
    private val handlerError = AssertionError("handler error $SENTINEL")
    private val rejection = RejectedExecutionException("saturated $SENTINEL")
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
            get { errorCode }.isEqualTo(MessageErrorCode.INTERNAL_ERROR)
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
    fun `should report a handler throwing an error`() {
        val run = exchange(onAction = { throw handlerError })

        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.HANDLER_ERROR)
            get { cause }.isSameInstanceAs(handlerError)
        }
    }

    @Test
    fun `should report a handler throwing an error once when the executor runs calls on the message thread`() {
        val run = exchange(settings = directSettings, onAction = { throw handlerError })

        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.HANDLER_ERROR)
            get { cause }.isSameInstanceAs(handlerError)
        }
    }

    @Test
    fun `should report a handler throwing an IOException`() {
        val failure = IOException("handler I/O failure")

        val run = exchange(onAction = { throw failure })

        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.HANDLER_ERROR)
            get { cause }.isSameInstanceAs(failure)
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
    fun `should report once a call the executor fails to run`() {
        val failure = IllegalStateException("executor failure")
        val events = mutableListOf<OcppErrorEvent>()
        val (_, websocket) = connectApp(FakeWebsocket(), events, failingSettings(failure))

        runCatching { websocket.triggerMessage(WsMessage(call)) }

        expectThat(events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.TRANSPORT_ERROR)
            get { messageId }.isEqualTo("msg-1")
            get { cause }.isSameInstanceAs(failure)
        }
    }

    @Test
    fun `should not wait on shutdown for a call the executor failed to run`() {
        val (app, websocket) = connectApp(
            FakeWebsocket(),
            mutableListOf(),
            failingSettings(IllegalStateException("executor failure"), timeoutInMs = 5_000)
        )
        runCatching { websocket.triggerMessage(WsMessage(call)) }

        val elapsed = measureTime { app.shutdown() }

        expectThat(elapsed).isLessThan(1.seconds)
    }

    @Test
    fun `should wait on shutdown for a call in flight after a call failed on the message thread`() {
        val held = CountDownLatch(1)
        val submitted = AtomicInteger()
        // runs the first call on the message thread, and holds the second one on its own thread
        val settings = object : OcppWampServerSettings(timeoutInMs = 5_000) {
            override fun buildCallsExecutor() = Executor { task ->
                if (submitted.getAndIncrement() == 0) {
                    task.run()
                } else {
                    thread { held.await(); task.run() }
                }
            }
        }
        val (app, websocket) = connectApp(FakeWebsocket(), mutableListOf(), settings) {
            if (it.msgId == "msg-1") throw handlerFailure else WampMessage.CallResult(it.msgId, "{}")
        }
        runCatching { websocket.triggerMessage(WsMessage(call)) }
        websocket.triggerMessage(WsMessage("""[2,"msg-2","Heartbeat",{}]"""))

        val stopped = CountDownLatch(1)
        thread { app.shutdown(); stopped.countDown() }

        expectThat(stopped.await(SETTLE_MS, TimeUnit.MILLISECONDS)).isFalse()
        held.countDown()
        expectThat(stopped.await(5, TimeUnit.SECONDS)).isTrue()
    }

    @Test
    fun `should report a response that cannot be sent as a transport error`() {
        val failure = IllegalStateException("cannot send")
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
    fun `should not report a response that cannot be sent because the network failed`() {
        val events = mutableListOf<OcppErrorEvent>()
        val websocket =
            connect(FakeWebsocket(IOException("Broken pipe")), events) { WampMessage.CallResult(it.msgId, "{}") }

        runCatching { websocket.triggerMessage(WsMessage(call)) }

        expectThat(events).isEmpty()
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
    fun `should answer a throwing handler once with InternalError and an empty object whatever the listener`() {
        expectAnsweredOnceWhateverTheListener(
            OcppErrorCategory.HANDLER_ERROR,
            handlerFailure,
            onAction = { throw handlerFailure }
        )
    }

    @Test
    fun `should answer a throwing handler once when the executor runs calls on the message thread`() {
        expectAnsweredOnceWhateverTheListener(
            OcppErrorCategory.HANDLER_ERROR,
            handlerFailure,
            settings = directSettings,
            onAction = { throw handlerFailure }
        )
    }

    @Test
    fun `should answer a handler throwing an error once`() {
        expectAnsweredOnceWhateverTheListener(
            OcppErrorCategory.HANDLER_ERROR,
            handlerError,
            onAction = { throw handlerError }
        )
    }

    @Test
    fun `should answer a handler throwing an error once when the executor runs calls on the message thread`() {
        expectAnsweredOnceWhateverTheListener(
            OcppErrorCategory.HANDLER_ERROR,
            handlerError,
            settings = directSettings,
            onAction = { throw handlerError }
        )
    }

    @Test
    fun `should answer a handler throwing an IOException once`() {
        val failure = IOException("handler I/O failure $SENTINEL")

        expectAnsweredOnceWhateverTheListener(OcppErrorCategory.HANDLER_ERROR, failure, onAction = { throw failure })
    }

    @Test
    fun `should answer a response that cannot be serialized once`() {
        val failure = IllegalStateException("cannot serialize $SENTINEL")
        val response = mockk<WampMessage> { every { toJson() } throws failure }

        expectAnsweredOnceWhateverTheListener(OcppErrorCategory.PROTOCOL_ERROR, failure, onAction = { response })
    }

    @Test
    fun `should answer a call the executor rejects once`() {
        expectAnsweredOnceWhateverTheListener(
            OcppErrorCategory.TRANSPORT_ERROR,
            rejection,
            settings = rejectingSettings,
            onAction = { WampMessage.CallResult(it.msgId, "{}") }
        )
    }

    @Test
    fun `should answer a call the executor fails to run once`() {
        val failure = IllegalStateException("executor failure $SENTINEL")

        expectAnsweredOnceWhateverTheListener(
            OcppErrorCategory.TRANSPORT_ERROR,
            failure,
            settings = failingSettings(failure),
            onAction = { WampMessage.CallResult(it.msgId, "{}") }
        )
    }

    @Test
    fun `should answer once a failed call the executor ran before failing itself`() {
        val executorFailure = IllegalStateException("executor failure $SENTINEL")
        val settings = object : OcppWampServerSettings() {
            override fun buildCallsExecutor() = Executor {
                it.run()
                throw executorFailure
            }
        }

        val run = exchange(settings = settings, onAction = { throw handlerFailure })

        expectAnsweredOnce(run, OcppErrorCategory.HANDLER_ERROR, handlerFailure)
    }

    @Test
    fun `should not answer a call whose response cannot be sent`() {
        listOf(IllegalStateException("cannot send"), IOException("Broken pipe")).forEach { failure ->
            val websocket = connect(FakeWebsocket(failure), mutableListOf()) { WampMessage.CallResult(it.msgId, "{}") }

            runCatching { websocket.triggerMessage(WsMessage(call)) }

            expectThat(websocket.sent).single().isEqualTo("""[3,"msg-1",{}]""")
        }
    }

    @Test
    fun `should not report again a CALLERROR that cannot be sent for a call the executor rejects`() {
        val sendFailure = IllegalStateException("cannot send $SENTINEL")
        val events = mutableListOf<OcppErrorEvent>()
        val (app, websocket) = connectApp(FakeWebsocket(sendFailure), events, rejectingSettings)

        websocket.triggerMessage(WsMessage(call))

        expectThat(websocket.sent.toList()).isEqualTo(listOf(INTERNAL_ERROR_FRAME))
        expectThat(events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.TRANSPORT_ERROR)
            get { cause }.isSameInstanceAs(rejection)
        }
        expectThat(measureTime { app.shutdown() }).isLessThan(1.seconds)
    }

    @Test
    fun `should not report again a CALLERROR that cannot be sent for a throwing handler`() {
        val sendFailure = IllegalStateException("cannot send $SENTINEL")
        val events = mutableListOf<OcppErrorEvent>()
        val websocket = connect(FakeWebsocket(sendFailure), events) { throw handlerFailure }

        websocket.triggerMessage(WsMessage(call))

        expectThat(websocket.sent.toList()).isEqualTo(listOf(INTERNAL_ERROR_FRAME))
        expectThat(events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.HANDLER_ERROR)
            get { cause }.isSameInstanceAs(handlerFailure)
        }
    }

    @Test
    fun `should log an answered handler failure once with its class only`() {
        val websocket = connect(FakeWebsocket(), mutableListOf()) { throw handlerFailure }

        val warnings = warningsOf { websocket.triggerMessage(WsMessage(call)) }

        expectLoggedOnce(warnings, OcppErrorCategory.HANDLER_ERROR, handlerFailure)
    }

    @Test
    fun `should log an answered call the executor rejects once with its class only`() {
        val (_, websocket) = connectApp(FakeWebsocket(), mutableListOf(), rejectingSettings)

        val warnings = warningsOf { websocket.triggerMessage(WsMessage(call)) }

        expectLoggedOnce(warnings, OcppErrorCategory.TRANSPORT_ERROR, rejection)
    }

    @Test
    fun `should neither answer nor swallow a handler failing with a virtual machine error`() {
        val failure = OutOfMemoryError("handler $SENTINEL")
        val events = mutableListOf<OcppErrorEvent>()
        val websocket = connect(FakeWebsocket(), events) { throw failure }

        expectThrows<OutOfMemoryError> { websocket.triggerMessage(WsMessage(call)) }.isSameInstanceAs(failure)

        expectThat(websocket.sent).isEmpty()
        expectThat(events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.HANDLER_ERROR)
            get { errorCode }.isNull()
            get { cause }.isSameInstanceAs(failure)
        }
    }

    @Test
    fun `should neither answer nor swallow an executor failing with a virtual machine error`() {
        val failure = OutOfMemoryError("unable to create native thread $SENTINEL")
        val events = mutableListOf<OcppErrorEvent>()
        val (app, websocket) = connectApp(FakeWebsocket(), events, failingSettings(failure, timeoutInMs = 5_000))

        expectThrows<OutOfMemoryError> { websocket.triggerMessage(WsMessage(call)) }.isSameInstanceAs(failure)

        expectThat(websocket.sent).isEmpty()
        expectThat(events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.TRANSPORT_ERROR)
            get { errorCode }.isNull()
            get { cause }.isSameInstanceAs(failure)
        }
        expectThat(measureTime { app.shutdown() }).isLessThan(1.seconds)
    }

    /**
     * Expects the failed call to be answered once with the same CALLERROR, without the failure, with no listener, a
     * recording one or a throwing one, and the recording listener to be notified once with the original [cause].
     */
    private fun expectAnsweredOnceWhateverTheListener(
        category: OcppErrorCategory,
        cause: Throwable,
        settings: OcppWampServerSettings = OcppWampServerSettings(),
        onAction: (WampMessage) -> WampMessage?
    ) {
        expectAnsweredOnce(exchange(onAction = onAction, settings = settings), category, cause)
        listOf(null, OcppErrorListener { error("listener failure") }).forEach { listener ->
            val run = exchange(onAction = onAction, settings = settings, listener = listener)
            expectThat(run.frames).isEqualTo(listOf(INTERNAL_ERROR_FRAME))
        }
    }

    private fun expectAnsweredOnce(run: Run, category: OcppErrorCategory, cause: Throwable) {
        expectThat(run.frames).isEqualTo(listOf(INTERNAL_ERROR_FRAME))
        expectThat(run.frames).all { not().contains(SENTINEL) }
        expectThat(run.events).single().and {
            get { this.category }.isEqualTo(category)
            get { action }.isEqualTo("Heartbeat")
            get { messageId }.isEqualTo("msg-1")
            get { errorCode }.isEqualTo(MessageErrorCode.INTERNAL_ERROR)
            get { this.cause }.isSameInstanceAs(cause)
        }
    }

    /** Expects one warning naming the [category] and the class of the [cause], without its message or stack trace. */
    private fun expectLoggedOnce(warnings: List<ILoggingEvent>, category: OcppErrorCategory, cause: Throwable) {
        expectThat(warnings).single().and {
            get { formattedMessage }.contains("[msg-1] [Heartbeat]")
            get { formattedMessage }.contains("($category)")
            get { formattedMessage }.contains(cause.javaClass.name)
            get { formattedMessage }.not().contains(SENTINEL)
            get { throwableProxy }.isNull()
        }
    }

    /** Runs [block] and returns the warnings and errors the server logger logged meanwhile. */
    private fun warningsOf(block: () -> Unit): List<ILoggingEvent> {
        val logger = LoggerFactory.getLogger("com.izivia.ocpp.wamp.server") as Logger
        val appender = ListAppender<ILoggingEvent>().apply { start() }
        logger.addAppender(appender)
        try {
            block()
        } finally {
            logger.detachAppender(appender)
            appender.stop()
        }
        return appender.list.filter { it.level.isGreaterOrEqual(Level.WARN) }
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

    /** The [frames] the client received within the settle window, and the [events] the listener received. */
    private class Run(val frames: List<String>, val events: List<OcppErrorEvent>) {
        val response: String? get() = frames.firstOrNull()
    }

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
            val frames = listOfNotNull(responses.poll(SETTLE_MS, TimeUnit.MILLISECONDS)).toMutableList()
            Thread.sleep(SETTLE_MS)
            responses.drainTo(frames)
            return Run(frames, events.toList())
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
        /** Every frame the application tried to send, failed or not. */
        val sent = CopyOnWriteArrayList<String>()

        override fun send(message: WsMessage) {
            sent.add(message.bodyString())
            sendFailure?.let { throw it }
        }

        override fun close(status: WsStatus) = Unit
    }

    /** Connects a fake websocket to the application, to fail in ways a real connection cannot reproduce on demand. */
    private fun connect(
        websocket: FakeWebsocket,
        events: MutableList<OcppErrorEvent>,
        onAction: (WampMessage) -> WampMessage? = { null }
    ): FakeWebsocket = connectApp(websocket, events, directSettings, onAction).second

    private fun connectApp(
        websocket: FakeWebsocket,
        events: MutableList<OcppErrorEvent>,
        settings: OcppWampServerSettings,
        onAction: (WampMessage) -> WampMessage? = { null }
    ): Pair<OcppWampServerApp, FakeWebsocket> {
        val app = OcppWampServerApp(
            ocppVersions = setOf(OCPP_1_6),
            handlers = { listOf(handlerOf(onAction)) },
            ocppWsEndpoint = OcppWsEndpoint("ws"),
            settings = settings,
            errorListener = OcppErrorListener { events.add(it) }
        )
        val request = Http4kRequest(Method.GET, "/ws/$OCPP_ID").header("Sec-WebSocket-Protocol", "ocpp1.6")
        app.newRoutingHandler()(request).consumer(websocket)
        return app to websocket
    }

    /** An executor failing without running the call, otherwise than by a rejection. */
    private fun failingSettings(failure: Throwable, timeoutInMs: Long = 30_000) =
        object : OcppWampServerSettings(timeoutInMs) {
            override fun buildCallsExecutor() = Executor { throw failure }
        }

    private companion object {
        const val OCPP_ID = "CS1"
        const val SETTLE_MS = 300L
        const val SENTINEL = "sentinel-166"
        const val INTERNAL_ERROR_FRAME = """[4,"msg-1","InternalError","Internal error",{}]"""
    }
}
