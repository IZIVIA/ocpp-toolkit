package com.izivia.ocpp.http.test

import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.http.OcppSoapClientTransport
import com.izivia.ocpp.http.SoapClientSettings
import com.izivia.ocpp.soap16.Ocpp16SoapParser
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.utils.OcppErrorCategory
import com.izivia.ocpp.utils.OcppErrorEvent
import com.izivia.ocpp.utils.OcppErrorListener
import kotlin.time.Instant
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
import java.util.concurrent.CopyOnWriteArrayList

class OcppSoapClientErrorListenerTest {

    private val handlerFailure = IllegalStateException("handler failure")
    private val heartbeatResp = HeartbeatResp(currentTime = Instant.parse("2022-05-17T15:42:00.503Z"))

    @Test
    fun `should report an unknown action`() {
        val run = exchange(body = envelope("Unknown"), onHeartbeat = { heartbeatResp })

        expectThat(run.response.status).isEqualTo(NOT_FOUND)
        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.UNHANDLED_ACTION)
            get { ocppId }.isEqualTo(OCPP_ID)
            get { ocppVersion }.isEqualTo("ocpp1.6")
            get { action }.isEqualTo("Unknown")
            get { messageId }.isNull()
            get { errorCode }.isNull()
            get { cause }.isNull()
        }
    }

    @Test
    fun `should report a malformed envelope as a protocol error`() {
        val run = exchange(body = "not a soap envelope", onHeartbeat = { heartbeatResp })

        expectThat(run.response.status).isEqualTo(NOT_FOUND)
        expectThat(run.events).single().and {
            get { category }.isEqualTo(OcppErrorCategory.PROTOCOL_ERROR)
            get { ocppId }.isEqualTo(OCPP_ID)
            get { ocppVersion }.isEqualTo("ocpp1.6")
            get { action }.isNull()
            get { messageId }.isNull()
            get { cause }.isNull()
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
            get { errorCode }.isNull()
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
    fun `should report no version when none is given`() {
        val run = exchange(onHeartbeat = { throw handlerFailure }, ocppVersion = null)

        expectThat(run.events).single().get { ocppVersion }.isNull()
    }

    @Test
    fun `should not report a handled call`() {
        val run = exchange(onHeartbeat = { heartbeatResp })

        expectThat(run.response.status).isEqualTo(OK)
        expectThat(run.events).isEmpty()
    }

    @Test
    fun `should answer the same way whatever the listener`() {
        val failures = listOf<Triple<String, (HeartbeatReq) -> Any, Int>>(
            Triple(envelope("Unknown"), { heartbeatResp }, NOT_FOUND.code),
            Triple("not a soap envelope", { heartbeatResp }, NOT_FOUND.code),
            Triple(envelope("Heartbeat"), { throw handlerFailure }, INTERNAL_SERVER_ERROR.code),
            Triple(envelope("Heartbeat"), { Unserializable() }, INTERNAL_SERVER_ERROR.code)
        )

        failures.forEach { (body, onHeartbeat, status) ->
            val withoutListener = exchange(body, onHeartbeat, listener = null).response
            val recording = exchange(body, onHeartbeat).response
            val throwing =
                exchange(body, onHeartbeat, listener = OcppErrorListener { error("listener failure") }).response

            expectThat(withoutListener.status.code).isEqualTo(status)
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

    /** Sends one request from the CSMS to the client's embedded server and collects the answer and the events. */
    private fun exchange(
        body: String = envelope("Heartbeat"),
        onHeartbeat: (HeartbeatReq) -> Any,
        events: MutableCollection<OcppErrorEvent> = CopyOnWriteArrayList(),
        listener: OcppErrorListener? = OcppErrorListener { events.add(it) },
        ocppVersion: OcppVersion? = OcppVersion.OCPP_1_6
    ): Run {
        val client = OcppSoapClientTransport.createClient(
            SoapClientSettings(port = 0, host = "localhost", path = "/ocpp/soap"),
            ocppId = OCPP_ID,
            target = "http://localhost:5002/api",
            ocppSoapParser = Ocpp16SoapParser(),
            ocppVersion = ocppVersion,
            errorListener = listener
        )
        client.receiveMessageClass(HeartbeatReq::class, "Heartbeat", onHeartbeat)
        client.connect()
        try {
            val response = JavaHttpClient()(Request(Method.POST, client.callbackUrl()).body(body))
            return Run(response, events.toList())
        } finally {
            client.close()
        }
    }

    private companion object {
        const val OCPP_ID = "CS1"
        const val MESSAGE_ID = "uuid:b70e2eae-bd09-4546-b049-8b2be7ccf578"

        fun envelope(action: String) =
            """
            <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                <S:Header>
                    <chargeBoxIdentity xmlns="urn://Ocpp/Cp/2015/10/">$OCPP_ID</chargeBoxIdentity>
                    <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                        <wsa:Address>http://192.168.0.1:8080/ocpp</wsa:Address>
                    </wsa:From>
                    <To xmlns="http://www.w3.org/2005/08/addressing">http://localhost/ocpp/soap</To>
                    <Action xmlns="http://www.w3.org/2005/08/addressing">/$action</Action>
                    <MessageID xmlns="http://www.w3.org/2005/08/addressing">$MESSAGE_ID</MessageID>
                </S:Header>
                <S:Body>
                    <heartbeatRequest xmlns="urn://Ocpp/Cs/2015/10/"/>
                </S:Body>
            </S:Envelope>
            """.trimIndent()
    }
}
