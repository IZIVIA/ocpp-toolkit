package com.izivia.ocpp.soap16

import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core16.model.statusnotification.enumeration.ChargePointErrorCode
import com.izivia.ocpp.core16.model.statusnotification.enumeration.ChargePointStatus
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isA
import strikt.assertions.isEqualTo

class Soap16MessageParserTest {

    @Test
    fun `should parse message to StatusNotificationReq`() {
        val message =
            """<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:cs="urn://Ocpp/Cs/2015/10/" xmlns:wsa5="http://www.w3.org/2005/08/addressing"><soap:Header><cs:chargeBoxIdentity soap:mustUnderstand="true">IES20210510592612</cs:chargeBoxIdentity><wsa5:From><wsa5:Address>http://10.66.5.160:8085</wsa5:Address></wsa5:From><wsa5:MessageID>urn:uuid:70c76024-1281-471f-8578-ad51a2a88134</wsa5:MessageID><wsa5:ReplyTo soap:mustUnderstand="true"><wsa5:Address>http://www.w3.org/2005/08/addressing/anonymous</wsa5:Address></wsa5:ReplyTo><wsa5:To soap:mustUnderstand="true">http://sigeif-ies.vpn.l30.sodetrel.fr/ocpp/v16s</wsa5:To><wsa5:Action soap:mustUnderstand="true">/StatusNotification</wsa5:Action></soap:Header><soap:Body><cs:statusNotificationRequest><cs:connectorId>1</cs:connectorId><cs:status>Available</cs:status><cs:errorCode>NoError</cs:errorCode><cs:info>No error.</cs:info><cs:timestamp>2022-05-17T15:41:59.486Z</cs:timestamp><cs:vendorErrorCode>0x0</cs:vendorErrorCode></cs:statusNotificationRequest></soap:Body></soap:Envelope>"""

        expectThat(Soap16MessageParser().parse<StatusNotificationReq>(message)) {
            get { connectorId }.isEqualTo(1)
            get { status }.isEqualTo(ChargePointStatus.Available)
            get { errorCode }.isEqualTo(ChargePointErrorCode.NoError)
            get { info }.isEqualTo("No error.")
            get { timestamp }.isEqualTo(Instant.parse("2022-05-17T15:41:59.486Z"))
            get { vendorErrorCode }.isEqualTo("0x0")
        }
    }

    @Test
    fun `should parse message to HeartbeatReq`() {
        val message =
            """<S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope"><S:Header><chargeBoxIdentity xmlns="urn://Ocpp/Cs/2015/10/">92073_05_04</chargeBoxIdentity><wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing"><wsa:Address>http://192.168.0.3:12800/ws</wsa:Address></wsa:From><To xmlns="http://www.w3.org/2005/08/addressing">http://sigeif-enovates.vpn.l30.sodetrel.fr/ocpp/v16s/</To><Action xmlns="http://www.w3.org/2005/08/addressing">/Heartbeat</Action><ReplyTo xmlns="http://www.w3.org/2005/08/addressing"><Address>http://www.w3.org/2005/08/addressing/anonymous</Address></ReplyTo><FaultTo xmlns="http://www.w3.org/2005/08/addressing"><Address>http://www.w3.org/2005/08/addressing/anonymous</Address></FaultTo><MessageID xmlns="http://www.w3.org/2005/08/addressing">uuid:b70e2eae-bd09-4546-b049-8b2be7ccf578</MessageID></S:Header><S:Body><heartbeatRequest xmlns="urn://Ocpp/Cs/2015/10/"/></S:Body></S:Envelope>"""

        expectThat(Soap16MessageParser().parse<HeartbeatReq>(message))
            .isA<HeartbeatReq>()
    }

    @Test
    fun `should not parse message to StatusNotificationReq because it is not a StatusNotification`() {
        val message =
            """<S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope"><S:Header><chargeBoxIdentity xmlns="urn://Ocpp/Cs/2015/10/">92073_05_04</chargeBoxIdentity><wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing"><wsa:Address>http://192.168.0.3:12800/ws</wsa:Address></wsa:From><To xmlns="http://www.w3.org/2005/08/addressing">http://sigeif-enovates.vpn.l30.sodetrel.fr/ocpp/v16s/</To><Action xmlns="http://www.w3.org/2005/08/addressing">/Heartbeat</Action><ReplyTo xmlns="http://www.w3.org/2005/08/addressing"><Address>http://www.w3.org/2005/08/addressing/anonymous</Address></ReplyTo><FaultTo xmlns="http://www.w3.org/2005/08/addressing"><Address>http://www.w3.org/2005/08/addressing/anonymous</Address></FaultTo><MessageID xmlns="http://www.w3.org/2005/08/addressing">uuid:b70e2eae-bd09-4546-b049-8b2be7ccf578</MessageID></S:Header><S:Body><heartbeatRequest xmlns="urn://Ocpp/Cs/2015/10/"/></S:Body></S:Envelope>"""

        expectThrows<ClassCastException> {
            Soap16MessageParser().parse<StatusNotificationReq>(message)
        }
    }

    @Test
    fun `should not parse message to HeartbeatReq because it is not an Heartbeat`() {
        val message =
            """<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:cs="urn://Ocpp/Cs/2015/10/" xmlns:wsa5="http://www.w3.org/2005/08/addressing"><soap:Header><cs:chargeBoxIdentity soap:mustUnderstand="true">IES20210510592612</cs:chargeBoxIdentity><wsa5:From><wsa5:Address>http://10.66.5.160:8085</wsa5:Address></wsa5:From><wsa5:MessageID>urn:uuid:70c76024-1281-471f-8578-ad51a2a88134</wsa5:MessageID><wsa5:ReplyTo soap:mustUnderstand="true"><wsa5:Address>http://www.w3.org/2005/08/addressing/anonymous</wsa5:Address></wsa5:ReplyTo><wsa5:To soap:mustUnderstand="true">http://sigeif-ies.vpn.l30.sodetrel.fr/ocpp/v16s</wsa5:To><wsa5:Action soap:mustUnderstand="true">/StatusNotification</wsa5:Action></soap:Header><soap:Body><cs:statusNotificationRequest><cs:connectorId>1</cs:connectorId><cs:status>Available</cs:status><cs:errorCode>NoError</cs:errorCode><cs:info>No error.</cs:info><cs:timestamp>2022-05-17T15:41:59.486Z</cs:timestamp><cs:vendorErrorCode>0x0</cs:vendorErrorCode></cs:statusNotificationRequest></soap:Body></soap:Envelope>"""

        expectThrows<ClassCastException> {
            Soap16MessageParser().parse<HeartbeatReq>(message)
        }
    }
}