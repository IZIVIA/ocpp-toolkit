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
import com.izivia.ocpp.wamp.client.OcppWampClient
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageType
import com.izivia.ocpp.websocket.WebsocketServer
import com.izivia.ocpp.websocket.asServer
import org.http4k.core.Uri
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo
import strikt.assertions.isNull
import strikt.assertions.isSameInstanceAs
import strikt.assertions.single
import java.net.ServerSocket
import java.util.concurrent.CopyOnWriteArrayList
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

        expectThat(run.response.msgType).isEqualTo(WampMessageType.CALL_RESULT)
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

    private fun expectSameResponseWhateverTheListener(call: WampMessage, onAuthorize: (AuthorizeReq) -> Any) {
        val withoutListener = exchange(call, onAuthorize, listener = null).response
        val recording = exchange(call, onAuthorize).response
        val throwing = exchange(call, onAuthorize, listener = OcppErrorListener { error("listener failure") }).response

        expectThat(withoutListener.msgType).isEqualTo(WampMessageType.CALL_ERROR)
        expectThat(recording.toJson()).isEqualTo(withoutListener.toJson())
        expectThat(throwing.toJson()).isEqualTo(withoutListener.toJson())
    }

    private class Unserializable {
        val failing: String get() = throw IllegalStateException("cannot serialize")
    }

    private class Run(val response: WampMessage, val events: List<OcppErrorEvent>)

    /** Sends one call through a real server and collects the answer and the events it produced. */
    private fun exchange(
        call: WampMessage,
        onAuthorize: (AuthorizeReq) -> Any,
        events: MutableCollection<OcppErrorEvent> = CopyOnWriteArrayList(),
        listener: OcppErrorListener? = OcppErrorListener { events.add(it) }
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
        val client = OcppWampClient.newClient(
            Uri.of("ws://localhost:$port/ws"),
            OCPP_ID,
            OcppVersionWamp.OCPP_1_6,
            autoReconnect = false
        )
        try {
            client.connect()
            val response = client.sendBlocking(call)
            // long enough for a duplicate event to show up
            Thread.sleep(SETTLE_MS)
            return Run(response, events.toList())
        } finally {
            client.close()
            server.stop()
        }
    }

    private companion object {
        const val OCPP_ID = "CS1"
        const val SETTLE_MS = 300L
    }
}
