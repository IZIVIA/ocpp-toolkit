package com.izivia.ocpp.http.test

import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.http.SoapClientSettings
import com.izivia.ocpp.http.OcppSoapClientTransport
import com.izivia.ocpp.soap16.Ocpp16SoapParser
import com.izivia.ocpp.transport.OcppCallErrorException
import com.izivia.ocpp.transport.receiveMessage
import com.izivia.ocpp.transport.sendMessage
import kotlinx.datetime.Instant
import org.http4k.client.JavaHttpClient
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.server.Undertow
import org.http4k.server.asServer
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.*

class OcppSoapClientTransportTest {

    @Test
    fun `should send an OCPP 1-6 HeartBeat request`() {
        val time = Instant.parse("2022-05-17T15:42:00.503Z")
        val server = createMockServerAndAlwaysRespond(
            """
                <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope">
                    <soap:Header>
                        <Action xmlns="http://www.w3.org/2005/08/addressing">/HeartbeatResponse</Action>
                        <MessageID xmlns="http://www.w3.org/2005/08/addressing">urn:uuid:b5a704a7-a917-4ed3-bb8f-9a59918f8ffd
                        </MessageID>
                        <To xmlns="http://www.w3.org/2005/08/addressing">http://www.w3.org/2005/08/addressing/anonymous</To>
                        <RelatesTo xmlns="http://www.w3.org/2005/08/addressing">uuid:b70e2eae-bd09-4546-b049-8b2be7ccf578</RelatesTo>
                    </soap:Header>
                    <soap:Body>
                        <heartbeatResponse xmlns="urn://Ocpp/Cs/2015/10/">
                            <currentTime>$time</currentTime>
                        </heartbeatResponse>
                    </soap:Body>
                </soap:Envelope>
            """.trimIndent()
        ).start()

        val client = OcppSoapClientTransport(
            SoapClientSettings(
                path = "/ocpp/soap",
                port = 5001,
            ),
            ocppId = "ocppChargePoint",
            target = "http://localhost:5002/api",
            ocppSoapParser = Ocpp16SoapParser()
        )
        client.connect()

        val response = client.sendMessage<HeartbeatReq, HeartbeatResp>("HeartBeat", HeartbeatReq())

        expectThat(response) {
            get { currentTime }.isEqualTo(time)
        }

        server.stop()
        client.close()
    }

    @Test
    fun `should throw an OcppCallErrorException because the target is unreachable`() {
        val client = OcppSoapClientTransport(
            SoapClientSettings(
                path = "/ocpp/soap",
                port = 5001,
            ),
            ocppId = "ocppChargePoint",
            target = "http://localhost:5002/api",
            ocppSoapParser = Ocpp16SoapParser()
        )
        client.connect()

        expectThrows<OcppCallErrorException> {
            client.sendMessage<HeartbeatReq, HeartbeatResp>("HeartBeat", HeartbeatReq())
        }

        client.close()
    }

    @Test
    fun `should receive an OCPP 1-6 HeartBeat request`() {
        var received = false
        val time = Instant.parse("2022-05-17T15:42:00.503Z")
        val client = OcppSoapClientTransport(
            SoapClientSettings(
                path = "/ocpp/soap",
                port = 5001,
            ),
            ocppId = "ocppChargePoint",
            target = "http://localhost:5002/api",
            ocppSoapParser = Ocpp16SoapParser()
        )

        client.receiveMessage<HeartbeatReq, HeartbeatResp>("HeartBeat") {
            received = true
            HeartbeatResp(currentTime = time)
        }

        client.connect()

        val request = Request(Method.POST, "http://127.0.0.1:5001/ocpp/soap/HeartBeat")
            .body(
                """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2015/10/">CS1</chargeBoxIdentity>
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
                        <MessageID xmlns="http://www.w3.org/2005/08/addressing">uuid:b70e2eae-bd09-4546-b049-8b2be7ccf578</MessageID>
                    </S:Header>
                    <S:Body>
                        <heartbeatRequest xmlns="urn://Ocpp/Cs/2015/10/"/>
                    </S:Body>
                </S:Envelope>
            """.trimIndent()
            )

        val result = JavaHttpClient().invoke(request)

        expectThat(result) {
            get { bodyString() }.isNotBlank()
            get { status }.isEqualTo(Status.OK)
        }
        expectThat(received).isTrue()

        client.close()
    }

    @Test
    fun `should not receive the OCPP 1-6 HeartBeat request because there is no handler`() {
        val received = false
        val client = OcppSoapClientTransport(
            SoapClientSettings(
                path = "/ocpp/soap",
                port = 5001,
            ),
            ocppId = "ocppChargePoint",
            target = "http://localhost:5002/api",
            ocppSoapParser = Ocpp16SoapParser()
        )
        client.connect()

        val request = Request(Method.POST, "http://127.0.0.1:5001/ocpp/soap/HeartBeat")
            .body(
                """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2015/10/">CS1</chargeBoxIdentity>
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
                        <MessageID xmlns="http://www.w3.org/2005/08/addressing">uuid:b70e2eae-bd09-4546-b049-8b2be7ccf578</MessageID>
                    </S:Header>
                    <S:Body>
                        <heartbeatRequest xmlns="urn://Ocpp/Cs/2015/10/"/>
                    </S:Body>
                </S:Envelope>
            """.trimIndent()
            )

        val result = JavaHttpClient().invoke(request)

        expectThat(result) {
            get { bodyString() }.isBlank()
            get { status }.isEqualTo(Status.NOT_FOUND)
        }
        expectThat(received).isFalse()

        client.close()
    }

    private fun createMockServerAndAlwaysRespond(handlerBody: String) =
        ("api" bind Method.POST to { Response(Status.OK).body(handlerBody) })
            .asServer(Undertow(5002))
}
