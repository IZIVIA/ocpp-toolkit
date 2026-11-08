package com.izivia.ocpp.http.test

import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.http.OcppSoapServerTransport
import com.izivia.ocpp.http.asServer
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.soap16.Ocpp16SoapParser
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.transport.receiveMessage
import com.izivia.ocpp.utils.OcppErrorCategory
import com.izivia.ocpp.utils.OcppErrorEvent
import com.izivia.ocpp.utils.OcppErrorListener
import org.http4k.client.JavaHttpClient
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.INTERNAL_SERVER_ERROR
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull
import strikt.assertions.isNull
import strikt.assertions.isSameInstanceAs
import strikt.assertions.single
import java.net.ServerSocket
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.time.Instant

class OcppSoapServerErrorListenerTest {

    private val handlerFailure = IllegalStateException("handler failure")
    private val heartbeatResp = HeartbeatResp(currentTime = Instant.parse("2022-05-17T15:42:00.503Z"))

    @Test
    fun `should report an unknown action`() {
        val run = exchange(action = "Unknown", onHeartbeat = { heartbeatResp })

        expectThat(run.response.status).isEqualTo(NOT_FOUND)
        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.UNHANDLED_ACTION)
            get { ocppId }.isEqualTo(OCPP_ID)
            get { ocppVersion }.isEqualTo("ocpp1.6")
            get { action }.isEqualTo("Unknown")
            get { messageId }.isNull()
            get { cause }.isNull()
        }
    }

    @Test
    fun `should not report a charging station no handler accepts`() {
        val run = exchange(action = "Unknown", onHeartbeat = { heartbeatResp }, acceptStation = false)

        expectThat(run.response.status).isEqualTo(NOT_FOUND)
        expectThat(run.events).isEmpty()
    }

    @Test
    fun `should report the charging station the handler received`() {
        val run = exchange(onHeartbeat = { throw handlerFailure }, urlOcppId = "ANOTHER")

        expectThat(run.events).single().get { ocppId }.isEqualTo(OCPP_ID)
    }

    @Test
    fun `should report the charging station of the url before the envelope is parsed`() {
        val run = exchange(body = "not a soap envelope", onHeartbeat = { heartbeatResp }, urlOcppId = "ANOTHER")

        expectThat(run.events).single().get { ocppId }.isEqualTo("ANOTHER")
    }

    @Test
    fun `should report a malformed envelope as a protocol error`() {
        val run = exchange(body = "not a soap envelope", onHeartbeat = { heartbeatResp })

        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.PROTOCOL_ERROR)
            get { ocppId }.isEqualTo(OCPP_ID)
            get { ocppVersion }.isEqualTo("ocpp1.6")
            get { action }.isEqualTo("Heartbeat")
            get { messageId }.isNull()
            get { cause }.isNotNull()
        }
    }

    @Test
    fun `should report a throwing callback as a handler error`() {
        val run = exchange(onHeartbeat = { throw handlerFailure })

        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.HANDLER_ERROR)
            get { ocppId }.isEqualTo(OCPP_ID)
            get { ocppVersion }.isEqualTo("ocpp1.6")
            get { action }.isEqualTo("Heartbeat")
            get { messageId }.isEqualTo(MESSAGE_ID)
            get { cause }.isSameInstanceAs(handlerFailure)
        }
    }

    @Test
    fun `should report a response that cannot be serialized as a protocol error`() {
        val run = exchange(onHeartbeat = { Unserializable() })

        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.PROTOCOL_ERROR)
            get { messageId }.isEqualTo(MESSAGE_ID)
            get { cause }.isNotNull()
        }
    }

    @Test
    fun `should not report a handled call`() {
        val run = exchange(onHeartbeat = { heartbeatResp })

        expectThat(run.response.status).isEqualTo(OK)
        expectThat(run.events).isEmpty()
    }

    @Test
    fun `should answer the same way whatever the listener`() {
        val failures = listOf<Triple<String, String, (HeartbeatReq) -> Any>>(
            Triple("Unknown", HEARTBEAT_ENVELOPE) { heartbeatResp },
            Triple("Heartbeat", "not a soap envelope") { heartbeatResp },
            Triple("Heartbeat", HEARTBEAT_ENVELOPE) { throw handlerFailure },
            Triple("Heartbeat", HEARTBEAT_ENVELOPE) { Unserializable() }
        )

        failures.forEach { (action, body, onHeartbeat) ->
            val withoutListener = exchange(action, body, onHeartbeat, listener = null).response
            val recording = exchange(action, body, onHeartbeat).response
            val throwing = exchange(
                action,
                body,
                onHeartbeat,
                listener = OcppErrorListener { error("listener failure") }
            ).response

            expectThat(withoutListener.status.code).isEqualTo(
                if (action == "Unknown") NOT_FOUND.code else INTERNAL_SERVER_ERROR.code
            )
            expectThat(recording.status).isEqualTo(withoutListener.status)
            expectThat(throwing.status).isEqualTo(withoutListener.status)
            expectThat(recording.bodyString()).isEqualTo(withoutListener.bodyString())
            expectThat(throwing.bodyString()).isEqualTo(withoutListener.bodyString())
        }
    }

    private class Unserializable {
        val failing: String get() = throw IllegalStateException("cannot serialize")
    }

    private class Run(val response: Response, val events: List<OcppErrorEvent>)

    /** Sends one request through a real server and collects the answer and the events it produced. */
    private fun exchange(
        action: String = "Heartbeat",
        body: String = HEARTBEAT_ENVELOPE,
        onHeartbeat: (HeartbeatReq) -> Any,
        events: MutableCollection<OcppErrorEvent> = CopyOnWriteArrayList(),
        listener: OcppErrorListener? = OcppErrorListener { events.add(it) },
        urlOcppId: String = OCPP_ID,
        acceptStation: Boolean = true
    ): Run {
        val transport = OcppSoapServerTransport.create(
            path = "/ocpp/soap/",
            ocppSoapParser = Ocpp16SoapParser(),
            ocppVersion = OcppVersion.OCPP_1_6,
            errorListener = listener
        )
        transport.receiveMessage(
            "Heartbeat",
            OcppVersion.OCPP_1_6,
            { _: RequestMetadata, req: HeartbeatReq -> onHeartbeat(req) },
            { ChargingStationConfig(acceptStation, null) }
        )
        val port = ServerSocket(0).use { it.localPort }
        val server = transport.asServer(port)
        server.start()
        try {
            val response = JavaHttpClient()(
                Request(Method.POST, "http://127.0.0.1:$port/ocpp/soap/$action/$urlOcppId").body(body)
            )
            return Run(response, events.toList())
        } finally {
            server.stop()
        }
    }

    private companion object {
        const val OCPP_ID = "CS1"
        const val MESSAGE_ID = "uuid:b70e2eae-bd09-4546-b049-8b2be7ccf578"
        val HEARTBEAT_ENVELOPE =
            """
            <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                <S:Header>
                    <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2015/10/">$OCPP_ID</chargeBoxIdentity>
                    <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                        <wsa:Address>http://192.168.0.3:12800/ws</wsa:Address>
                    </wsa:From>
                    <To xmlns="http://www.w3.org/2005/08/addressing">http://example.fr/ocpp/v16s/</To>
                    <Action xmlns="http://www.w3.org/2005/08/addressing">/Heartbeat</Action>
                    <ReplyTo xmlns="http://www.w3.org/2005/08/addressing">
                        <Address>http://www.w3.org/2005/08/addressing/anonymous</Address>
                    </ReplyTo>
                    <FaultTo xmlns="http://www.w3.org/2005/08/addressing">
                        <Address>http://www.w3.org/2005/08/addressing/anonymous</Address>
                    </FaultTo>
                    <MessageID xmlns="http://www.w3.org/2005/08/addressing">$MESSAGE_ID</MessageID>
                </S:Header>
                <S:Body>
                    <heartbeatRequest xmlns="urn://Ocpp/Cs/2015/10/"/>
                </S:Body>
            </S:Envelope>
            """.trimIndent()
    }
}
