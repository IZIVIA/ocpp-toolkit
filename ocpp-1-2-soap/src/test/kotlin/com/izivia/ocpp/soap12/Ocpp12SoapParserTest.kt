package com.izivia.ocpp.soap12

import com.izivia.ocpp.core12.model.authorize.AuthorizeReq
import com.izivia.ocpp.core12.model.common.MeterValue
import com.izivia.ocpp.core12.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core12.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core12.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core12.model.reset.ResetReq
import com.izivia.ocpp.core12.model.reset.enumeration.ResetType
import com.izivia.ocpp.soap.RequestSoapMessage
import com.izivia.ocpp.soap.ResponseSoapMessage
import com.izivia.ocpp.soap.SoapFault
import com.izivia.ocpp.soap.parseRequestFromSoap
import kotlin.time.Instant
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.isA
import strikt.assertions.isEqualTo

class Ocpp12SoapParserTest {
    private val parser = Ocpp12SoapParser()

    @Test
    fun `parses charge point request with OCPP 1-2 CS namespace`() {
        val message = """
            <s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope"
                        xmlns:a="http://www.w3.org/2005/08/addressing"
                        xmlns:cs="urn://Ocpp/Cs/2010/08/">
              <s:Header>
                <cs:chargeBoxIdentity>CP001</cs:chargeBoxIdentity>
                <a:MessageID>uuid-1</a:MessageID>
                <a:Action>/Authorize</a:Action>
                <a:From><a:Address>source</a:Address></a:From>
                <a:To>destination</a:To>
              </s:Header>
              <s:Body>
                <cs:authorizeRequest>
                  <cs:idTag>ABC</cs:idTag>
                </cs:authorizeRequest>
              </s:Body>
            </s:Envelope>
        """.trimIndent()

        expectThat(parser.parseRequestFromSoap<AuthorizeReq>(message)).and {
            get { chargingStationId }.isEqualTo("CP001")
            get { action }.isEqualTo("Authorize")
            get { payload.idTag }.isEqualTo("ABC")
        }
    }

    @Test
    fun `parses central system request with OCPP 1-2 CP namespace`() {
        val message = """
            <s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope"
                        xmlns:a="http://www.w3.org/2005/08/addressing"
                        xmlns:cp="urn://Ocpp/Cp/2010/08/">
              <s:Header>
                <cp:chargeBoxIdentity>CP001</cp:chargeBoxIdentity>
                <a:MessageID>uuid-2</a:MessageID>
                <a:Action>/Reset</a:Action>
                <a:From><a:Address>source</a:Address></a:From>
                <a:To>destination</a:To>
              </s:Header>
              <s:Body>
                <cp:resetRequest>
                  <cp:type>Hard</cp:type>
                </cp:resetRequest>
              </s:Body>
            </s:Envelope>
        """.trimIndent()

        expectThat(parser.parseRequestFromSoap<ResetReq>(message).payload.type).isEqualTo(ResetType.Hard)
    }

    @Test
    fun `maps heartbeat request with OCPP 1-2 namespace`() {
        val soap = parser.mapRequestToSoap(
            RequestSoapMessage(
                messageId = "msg-1",
                chargingStationId = "CP001",
                action = "Heartbeat",
                from = "source",
                to = "destination",
                payload = HeartbeatReq()
            )
        )

        expectThat(soap).and {
            contains("""xmlns:o="urn://Ocpp/Cs/2010/08/"""")
            contains("<o:heartbeatRequest/>")
        }
    }

    @Test
    fun `maps heartbeat response with OCPP 1-2 namespace`() {
        val soap = parser.mapResponseToSoap(
            ResponseSoapMessage(
                action = "Heartbeat",
                messageId = "msg-2",
                relatesTo = "msg-1",
                to = "destination",
                from = "source",
                chargeBoxIdentity = "CP001",
                payload = HeartbeatResp(Instant.parse("2026-01-01T00:00:00Z"))
            )
        )

        expectThat(soap).and {
            contains("""xmlns:o="urn://Ocpp/Cs/2010/08/"""")
            contains("<o:heartbeatResponse>")
            contains("<o:currentTime>2026-01-01T00:00:00Z</o:currentTime>")
        }
    }

    @Test
    fun `parses OCPP 1-2 meter values as simple integer readings`() {
        val message = """
            <s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope"
                        xmlns:a="http://www.w3.org/2005/08/addressing"
                        xmlns:cs="urn://Ocpp/Cs/2010/08/">
              <s:Header>
                <cs:chargeBoxIdentity>CP001</cs:chargeBoxIdentity>
                <a:MessageID>uuid-3</a:MessageID>
                <a:Action>/MeterValues</a:Action>
                <a:From><a:Address>source</a:Address></a:From>
                <a:To>destination</a:To>
              </s:Header>
              <s:Body>
                <cs:meterValuesRequest>
                  <cs:connectorId>1</cs:connectorId>
                  <cs:values>
                    <cs:timestamp>2026-01-01T00:00:00Z</cs:timestamp>
                    <cs:value>42</cs:value>
                  </cs:values>
                </cs:meterValuesRequest>
              </s:Body>
            </s:Envelope>
        """.trimIndent()

        expectThat(parser.parseRequestFromSoap<MeterValuesReq>(message).payload.values).isEqualTo(
            listOf(MeterValue(Instant.parse("2026-01-01T00:00:00Z"), 42))
        )
    }

    @Test
    fun `unsupported OCPP 1-5 action is not routed as OCPP 1-2 payload`() {
        val message = """
            <s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope"
                        xmlns:a="http://www.w3.org/2005/08/addressing"
                        xmlns:cs="urn://Ocpp/Cs/2010/08/">
              <s:Header>
                <cs:chargeBoxIdentity>CP001</cs:chargeBoxIdentity>
                <a:MessageID>uuid-4</a:MessageID>
                <a:Action>/DataTransfer</a:Action>
                <a:From><a:Address>source</a:Address></a:From>
                <a:To>destination</a:To>
              </s:Header>
              <s:Body>
                <cs:dataTransferRequest/>
              </s:Body>
            </s:Envelope>
        """.trimIndent()

        expectThat(parser.parseAnyRequestFromSoap(message).payload).isA<SoapFault>()
    }
}
