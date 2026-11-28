package com.izivia.ocpp.websocket.test

import com.izivia.ocpp.core16.model.authorize.AuthorizeReq
import com.izivia.ocpp.core16.model.authorize.AuthorizeResp
import com.izivia.ocpp.core16.model.common.IdTagInfo
import com.izivia.ocpp.core16.model.common.enumeration.AuthorizationStatus
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.transport.receiveMessage
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.utils.OcppErrorCategory
import com.izivia.ocpp.utils.OcppErrorEvent
import com.izivia.ocpp.utils.OcppErrorListener
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageType
import com.izivia.ocpp.websocket.WebsocketServer
import com.izivia.ocpp.websocket.asServer
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.hasSize
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull
import strikt.assertions.isNull
import strikt.assertions.isSameInstanceAs
import strikt.assertions.isTrue
import strikt.assertions.single
import java.net.ServerSocket
import java.net.URI
import java.net.http.HttpClient
import java.net.http.WebSocket
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.TimeUnit
import com.izivia.ocpp.OcppVersion as OcppVersionWamp

class WebsocketServerErrorListenerTest {

    private val authorize = WampMessage.Call("msg-1", "Authorize", """{"idTag":"TAG"}""")
    private val malformedAuthorize = WampMessage.Call("msg-1", "Authorize", """{"idTag":{"not":"a string"}}""")
    private val handlerFailure = IllegalStateException("handler failure")

    private val accepted = AuthorizeResp(IdTagInfo(AuthorizationStatus.Accepted))
    private val failing: (AuthorizeReq) -> Any = { throw handlerFailure }

    @Test
    fun `should report a payload that cannot be parsed as a protocol error`() {
        val run = exchange(malformedAuthorize, { accepted })

        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.PROTOCOL_ERROR)
            get { ocppId }.isEqualTo(OCPP_ID)
            get { ocppVersion }.isEqualTo("ocpp1.6")
            get { action }.isEqualTo("Authorize")
            get { messageId }.isEqualTo("msg-1")
            get { errorCode }.isEqualTo(MessageErrorCode.INTERNAL_ERROR)
        }
    }

    @Test
    fun `should report a throwing callback as a handler error`() {
        val run = exchange(authorize, failing)

        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.HANDLER_ERROR)
            get { ocppId }.isEqualTo(OCPP_ID)
            get { ocppVersion }.isEqualTo("ocpp1.6")
            get { action }.isEqualTo("Authorize")
            get { messageId }.isEqualTo("msg-1")
            get { errorCode }.isEqualTo(MessageErrorCode.INTERNAL_ERROR)
            get { cause }.isSameInstanceAs(handlerFailure)
        }
    }

    @Test
    fun `should report once a callback throwing an error, as a handler error`() {
        val error = AssertionError("callback error")

        // nothing answers the call, which only fails on the client's timeout
        val run = exchange(authorize, { throw error }, clientTimeoutInMs = SETTLE_MS)

        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.HANDLER_ERROR)
            get { action }.isEqualTo("Authorize")
            get { messageId }.isEqualTo("msg-1")
            get { cause }.isSameInstanceAs(error)
        }
    }

    @Test
    fun `should report a response that cannot be serialized as a protocol error`() {
        val run = exchange(authorize, { Unserializable() })

        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.PROTOCOL_ERROR)
            get { action }.isEqualTo("Authorize")
            get { messageId }.isEqualTo("msg-1")
        }
    }

    @Test
    fun `should report once a call without a handler`() {
        val run = exchange(WampMessage.Call("msg-1", "Heartbeat", "{}"), { accepted })

        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.UNHANDLED_ACTION)
            get { action }.isEqualTo("Heartbeat")
            get { messageId }.isEqualTo("msg-1")
            get { cause }.isNull()
        }
    }

    @Test
    fun `should not report a handled call`() {
        val run = exchange(authorize, { accepted })

        expectThat(run.response?.msgType).isEqualTo(WampMessageType.CALL_RESULT)
        expectThat(run.events).isEmpty()
    }

    @Test
    fun `should answer a parsing failure the same way whatever the listener`() {
        expectSameResponseWhateverTheListener(malformedAuthorize, { accepted })
    }

    @Test
    fun `should answer a throwing callback the same way whatever the listener`() {
        expectSameResponseWhateverTheListener(authorize, failing)
    }

    @Test
    fun `should answer a serialization failure the same way whatever the listener`() {
        expectSameResponseWhateverTheListener(authorize, { Unserializable() })
    }

    @Test
    fun `should answer a throwing callback with an empty details object and without its message`() {
        val sentinelFailure = IllegalStateException("handler failure for $SENTINEL")
        val run = exchange(authorize, { throw sentinelFailure })

        expectInternalErrorWithoutSentinel(run.frame)
        expectThat(run.events).single().get { cause }.isSameInstanceAs(sentinelFailure)
        expectSameResponseWhateverTheListener(authorize, { throw sentinelFailure })
    }

    @Test
    fun `should answer a parsing failure with an empty details object and without the input`() {
        // Jackson quotes an unrecognized token in its message, up to the first character that cannot be part of it
        val malformed = WampMessage.Call("msg-1", "Authorize", """{"idTag":$TOKEN_SENTINEL}""")
        val run = exchange(malformed, { accepted })

        expectInternalErrorWithoutSentinel(run.frame, TOKEN_SENTINEL)
        expectThat(run.events).single().get { category }.isEqualTo(OcppErrorCategory.PROTOCOL_ERROR)
        expectSameResponseWhateverTheListener(malformed, { accepted })
    }

    /** OCPP-J 1.6 and 2.0.1 §4.2.3: "If there are no error details you MUST fill in an empty object {}." */
    private fun expectInternalErrorWithoutSentinel(frame: String?, sentinel: String = SENTINEL) {
        expectThat(frame).isNotNull().not().contains(sentinel)
        val elements = jacksonObjectMapper().readTree(frame)
        expectThat(elements) {
            get { isArray }.isTrue()
            get { size() }.isEqualTo(5)
            get { get(0).asInt() }.isEqualTo(WampMessageType.CALL_ERROR.id)
            get { get(1).textValue() }.isEqualTo("msg-1")
            get { get(2).textValue() }.isEqualTo("InternalError")
            get { get(3).textValue() }.isEqualTo("Internal error")
            get { get(4) }.isEqualTo(jacksonObjectMapper().createObjectNode())
        }
    }

    private fun expectSameResponseWhateverTheListener(call: WampMessage, onAuthorize: (AuthorizeReq) -> Any) {
        val withoutListener = exchange(call, onAuthorize, listener = null)
        val recording = exchange(call, onAuthorize)
        val throwing = exchange(call, onAuthorize, listener = OcppErrorListener { error("listener failure") })

        expectThat(withoutListener.response?.msgType).isEqualTo(WampMessageType.CALL_ERROR)
        expectThat(recording.frame).isEqualTo(withoutListener.frame)
        expectThat(throwing.frame).isEqualTo(withoutListener.frame)
        expectThat(recording.events).hasSize(1)
    }

    private class Unserializable {
        val failing: String get() = throw IllegalStateException("cannot serialize")
    }

    /** [frame] is the raw text the server answered, null when the call was not answered before the client's timeout. */
    private class Run(val frame: String?, val events: List<OcppErrorEvent>) {
        val response: WampMessage? get() = frame?.let { WampMessage.parse(it) }
    }

    /**
     * Sends one call through a real server, over a plain JDK WebSocket that keeps the answer as raw text, and
     * collects the answer and the events it produced.
     */
    private fun exchange(
        call: WampMessage,
        onAuthorize: (AuthorizeReq) -> Any,
        events: MutableCollection<OcppErrorEvent> = CopyOnWriteArrayList(),
        listener: OcppErrorListener? = OcppErrorListener { events.add(it) },
        clientTimeoutInMs: Long = 30_000
    ): Run {
        val transport = WebsocketServer(
            ocppVersions = setOf(OcppVersion.OCPP_1_6),
            path = "ws",
            errorListener = listener
        )
        transport.receiveMessage(
            "Authorize",
            OcppVersion.OCPP_1_6,
            { _, req: AuthorizeReq -> onAuthorize(req) },
            { ChargingStationConfig(true, null) }
        )
        val port = ServerSocket(0).use { it.localPort }
        val server = transport.asServer(port)
        server.start()
        val answer = CompletableFuture<String>()
        try {
            // closed after the socket is aborted, so its selector thread does not outlive the exchange
            HttpClient.newHttpClient().use { http ->
                val socket = http.newWebSocketBuilder()
                    .subprotocols(OcppVersionWamp.OCPP_1_6.subprotocol)
                    .buildAsync(URI("ws://localhost:$port/ws/$OCPP_ID"), FirstTextFrame(answer))
                    .get(10, TimeUnit.SECONDS)
                try {
                    socket.sendText(call.toJson(), true).get(10, TimeUnit.SECONDS)
                    val frame = runCatching { answer.get(clientTimeoutInMs, TimeUnit.MILLISECONDS) }.getOrNull()
                    // long enough for a duplicate event to show up
                    Thread.sleep(SETTLE_MS)
                    return Run(frame, events.toList())
                } finally {
                    socket.abort()
                }
            }
        } finally {
            server.stop()
        }
    }

    /** Completes [answer] with the first text message, reassembled from its frames. */
    private class FirstTextFrame(private val answer: CompletableFuture<String>) : WebSocket.Listener {
        private val text = StringBuilder()

        override fun onText(webSocket: WebSocket, data: CharSequence, last: Boolean): CompletionStage<*>? {
            text.append(data)
            if (last) answer.complete(text.toString())
            webSocket.request(1)
            return null
        }
    }

    private companion object {
        const val OCPP_ID = "CS1"
        const val SETTLE_MS = 300L
        const val SENTINEL = "SENTINEL-idTag-4242"
        const val TOKEN_SENTINEL = "SENTINEL_idTag_4242"
    }
}
