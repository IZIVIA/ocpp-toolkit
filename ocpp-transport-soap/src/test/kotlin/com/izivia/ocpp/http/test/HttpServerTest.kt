package com.izivia.ocpp.http.test

import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.http.HttpServer
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.soap16.Ocpp16SoapParser
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.transport.receiveMessage
import kotlinx.datetime.Instant
import org.http4k.client.JavaHttpClient
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.*

class HttpServerTest {

    @Test
    fun `should receive the OCPP 1-6 HeartBeat message`() {
        val server = HttpServer(
            port = 5002,
            path = "/ocpp/soap/",
            ocppSoapParser = Ocpp16SoapParser(),
            ocppVersion = OcppVersion.OCPP_1_6
        )
        server.start()
        var received = false

        server.receiveMessage("Heartbeat", OcppVersion.OCPP_1_6, { meta: RequestMetadata, req: HeartbeatReq ->
            received = true
            HeartbeatResp(currentTime = Instant.parse("2022-05-17T15:42:00.503Z"))
        }, { ChargingStationConfig(true, null) })

        val request = Request(Method.POST, "http://127.0.0.1:5002/ocpp/soap/HeartBeat/1234")
            .body(
                """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2015/10/">92073_05_04</chargeBoxIdentity>
                        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                            <wsa:Address>http://192.168.0.3:12800/ws</wsa:Address>
                        </wsa:From>
                        <To xmlns="http://www.w3.org/2005/08/addressing">http://sigeif-enovates.vpn.l30.sodetrel.fr/ocpp/v16s/</To>
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

        val client: HttpHandler = JavaHttpClient()
        val res = client(request)

        expectThat(res) {
            get { body.toString() }.isNotBlank()
            get { status }.isEqualTo(OK)
        }
        expectThat(received).isTrue()

        server.stop()
    }

    @Test
    fun `should not receive the OCPP 1-5 HeartBeat message because server is for 1-6`() {
        val server = HttpServer(
            port = 5002,
            path = "/ocpp/soap/",
            ocppSoapParser = Ocpp16SoapParser(),
            ocppVersion = OcppVersion.OCPP_1_6
        )
        server.start()
        var received = false

        server.receiveMessage("Heartbeat", OcppVersion.OCPP_1_5, { meta: RequestMetadata, req: HeartbeatReq ->
            received = true
            HeartbeatResp(currentTime = Instant.parse("2022-05-17T15:42:00.503Z"))
        }, { ChargingStationConfig(true, null) })

        val request = Request(Method.POST, "http://127.0.0.1:5002/ocpp/soap/HeartBeat/1234")
            .body(
                """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2015/10/">92073_05_04</chargeBoxIdentity>
                        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                            <wsa:Address>http://192.168.0.3:12800/ws</wsa:Address>
                        </wsa:From>
                        <To xmlns="http://www.w3.org/2005/08/addressing">http://sigeif-enovates.vpn.l30.sodetrel.fr/ocpp/v16s/</To>
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

        val client: HttpHandler = JavaHttpClient()
        val res = client(request)

        expectThat(res) {
            get { body.toString() }.isBlank()
            get { status }.isEqualTo(NOT_FOUND)
        }
        expectThat(received).isFalse()

        server.stop()
    }

}