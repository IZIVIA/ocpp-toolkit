package com.izivia.ocpp.soap16

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectReader
import com.izivia.ocpp.core16.model.authorize.AuthorizeReq
import com.izivia.ocpp.core16.model.authorize.AuthorizeResp
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core16.model.bootnotification.enumeration.RegistrationStatus
import com.izivia.ocpp.core16.model.common.IdTagInfo
import com.izivia.ocpp.core16.model.common.MeterValue
import com.izivia.ocpp.core16.model.common.SampledValue
import com.izivia.ocpp.core16.model.common.enumeration.*
import com.izivia.ocpp.core16.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core16.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core16.model.datatransfer.enumeration.DataTransferStatus
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core16.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core16.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core16.model.firmwarestatusnotification.enumeration.FirmwareStatus
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core16.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core16.model.metervalues.MeterValuesResp
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionResp
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core16.model.statusnotification.enumeration.ChargePointErrorCode
import com.izivia.ocpp.core16.model.statusnotification.enumeration.ChargePointStatus
import com.izivia.ocpp.core16.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core16.model.stoptransaction.StopTransactionResp
import com.izivia.ocpp.soap.RequestSoapMessage
import com.izivia.ocpp.soap.ResponseSoapMessage
import com.izivia.ocpp.soap.SoapEnvelope
import com.izivia.ocpp.soap.parseRequestFromSoap
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.*

class Ocpp16SoapParserTest {

    @Test
    fun `should parse message to AuthorizeReq`() {
        val message =
            """
                <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://www.w3.org/2003/05/soap-envelope" xmlns:cs="urn://Ocpp/Cs/2012/06/"
                                   xmlns:wsa5="http://www.w3.org/2005/08/addressing"
                >
                    <SOAP-ENV:Header>
                        <cs:chargeBoxIdentity>00:80:F4:44:13:AA</cs:chargeBoxIdentity>
                        <wsa5:MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</wsa5:MessageID>
                        <wsa5:From>
                            <wsa5:Address>http://:8082/</wsa5:Address>
                        </wsa5:From>
                        <wsa5:ReplyTo>
                            <wsa5:Address>http://www.w3.org/2005/08/addressing/anonymous</wsa5:Address>
                        </wsa5:ReplyTo>
                        <wsa5:To SOAP-ENV:mustUnderstand="true">http://prisedenice.vpn.l30.sodetrel.fr:80/ocpp/v15s/</wsa5:To>
                        <wsa5:Action SOAP-ENV:mustUnderstand="true">/Authorize</wsa5:Action>
                    </SOAP-ENV:Header>
                    <SOAP-ENV:Body>
                        <cs:authorizeRequest>
                            <cs:idTag>049E16B2EC7180</cs:idTag>
                        </cs:authorizeRequest>
                    </SOAP-ENV:Body>
                </SOAP-ENV:Envelope>
            """.trimIndent()

        expectThat(Ocpp16SoapParser().parseRequestFromSoap<AuthorizeReq>(message).payload).and {
            get { idTag }.isEqualTo("049E16B2EC7180")
        }
            .isA<AuthorizeReq>()
    }

    @Test
    fun `should parse message to BootNotificationReq`() {
        val message =
            """
                <ns0:Envelope xmlns:ns0="http://www.w3.org/2003/05/soap-envelope" xmlns:ns1="http://www.w3.org/2005/08/addressing"
                              xmlns:ns2="urn://Ocpp/Cs/2015/10/">
                    <ns0:Header>
                        <ns1:To>http://ev100-edfsa.vpn.l30.sodetrel.fr/ocpp/v16s/</ns1:To>
                        <ns1:From>
                            <ns1:Address>http://172.40.94.5:8084/chargePointService/ocpp16soap/</ns1:Address>
                        </ns1:From>
                        <ns2:chargeBoxIdentity>00:90:0B:9C:79:42</ns2:chargeBoxIdentity>
                        <ns1:MessageID>7230</ns1:MessageID>
                        <ns1:Action>/BootNotification</ns1:Action>
                    </ns0:Header>
                    <ns0:Body>
                        <ns2:bootNotificationRequest>
                            <ns2:chargePointVendor>Izivia</ns2:chargePointVendor>
                            <ns2:chargePointModel>LEC-3030A-SPX1</ns2:chargePointModel>
                            <ns2:chargePointSerialNumber>5aa469fd41344fe5a575368cd</ns2:chargePointSerialNumber>
                            <ns2:firmwareVersion>1.5.1.d723fd5</ns2:firmwareVersion>
                        </ns2:bootNotificationRequest>
                    </ns0:Body>
                </ns0:Envelope>
            """.trimIndent()

        expectThat(Ocpp16SoapParser().parseRequestFromSoap<BootNotificationReq>(message).payload).and {
            get { chargePointModel }.isEqualTo("LEC-3030A-SPX1")
            get { chargePointVendor }.isEqualTo("Izivia")
            get { chargePointSerialNumber }.isEqualTo("5aa469fd41344fe5a575368cd")
            get { chargeBoxSerialNumber }.isNull()
            get { firmwareVersion }.isEqualTo("1.5.1.d723fd5")
            get { iccid }.isNull()
            get { imsi }.isNull()
            get { meterSerialNumber }.isNull()
            get { meterType }.isNull()
        }
    }

    @Test
    fun `should parse message to DataTransferReq`() {
        val message =
            """
                <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://www.w3.org/2003/05/soap-envelope" xmlns:cs="urn://Ocpp/Cs/2012/06/"
                                   xmlns:wsa5="http://www.w3.org/2005/08/addressing"
                >
                    <SOAP-ENV:Header>
                        <cs:chargeBoxIdentity>00:80:F4:42:77:AB</cs:chargeBoxIdentity>
                        <wsa5:MessageID>urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</wsa5:MessageID>
                        <wsa5:From>
                            <wsa5:Address>http://:8082/</wsa5:Address>
                        </wsa5:From>
                        <wsa5:ReplyTo>
                            <wsa5:Address>http://www.w3.org/2005/08/addressing/anonymous</wsa5:Address>
                        </wsa5:ReplyTo>
                        <wsa5:To SOAP-ENV:mustUnderstand="true">http://wiiz.vpn.l30.sodetrel.fr:80/ocpp/v15s/</wsa5:To>
                        <wsa5:Action SOAP-ENV:mustUnderstand="true">/DataTransfer</wsa5:Action>
                    </SOAP-ENV:Header>
                    <SOAP-ENV:Body>
                        <cs:dataTransferRequest>
                            <cs:vendorId>Schneider Electric</cs:vendorId>
                            <cs:messageId>Detection loop</cs:messageId>
                            <cs:data>{"connectorId":10,"name":"Vehicle","state":"1","timestamp":"2022-05-17T15:42:03Z:"}</cs:data>
                        </cs:dataTransferRequest>
                    </SOAP-ENV:Body>
                </SOAP-ENV:Envelope>
            """.trimIndent()

        expectThat(Ocpp16SoapParser().parseRequestFromSoap<DataTransferReq>(message).payload).and {
            get { vendorId }.isEqualTo("Schneider Electric")
            get { messageId }.isEqualTo("Detection loop")
            get { data }.isEqualTo("{\"connectorId\":10,\"name\":\"Vehicle\",\"state\":\"1\",\"timestamp\":\"2022-05-17T15:42:03Z:\"}")
        }
    }

    @Test
    @Disabled
    fun `should parse message to DiagnosticsStatusNotificationReq`() {
        // TODO
    }

    @Test
    fun `should parse message to FirmwareStatusNotificationReq`() {
        val message =
            """
                <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:cs="urn://Ocpp/Cs/2015/10/"
                               xmlns:wsa5="http://www.w3.org/2005/08/addressing"
                >
                    <soap:Header>
                        <cs:chargeBoxIdentity soap:mustUnderstand="true">IES20210511714911</cs:chargeBoxIdentity>
                        <wsa5:From>
                            <wsa5:Address>http://10.66.9.194:8085</wsa5:Address>
                        </wsa5:From>
                        <wsa5:MessageID>urn:uuid:8fe7ef88-0f2e-47f3-911d-dbd36b32f366</wsa5:MessageID>
                        <wsa5:ReplyTo soap:mustUnderstand="true">
                            <wsa5:Address>http://www.w3.org/2005/08/addressing/anonymous</wsa5:Address>
                        </wsa5:ReplyTo>
                        <wsa5:To soap:mustUnderstand="true">http://bretagne-sde35-ies.vpn.l30.sodetrel.fr/ocpp/v16s/</wsa5:To>
                        <wsa5:Action soap:mustUnderstand="true">/FirmwareStatusNotification</wsa5:Action>
                    </soap:Header>
                    <soap:Body>
                        <cs:firmwareStatusNotificationRequest>
                            <cs:status>Installed</cs:status>
                        </cs:firmwareStatusNotificationRequest>
                    </soap:Body>
                </soap:Envelope>
            """.trimIndent()

        expectThat(Ocpp16SoapParser().parseRequestFromSoap<FirmwareStatusNotificationReq>(message).payload).and {
            get { status }.isEqualTo(FirmwareStatus.Installed)
        }
    }

    @Test
    fun `should parse message to HeartbeatReq`() {
        val message =
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

        expectThat(Ocpp16SoapParser().parseRequestFromSoap<HeartbeatReq>(message).payload)
    }

    @Test
    fun `should parse message to MeterValuesReq`() {
        val message =
            """
                <v:Envelope xmlns:v="http://www.w3.org/2003/05/soap-envelope" xmlns:a="http://www.w3.org/2005/08/addressing"
                            xmlns:ocpp="urn://Ocpp/Cs/2015/10/">
                    <v:Header>
                        <ocpp:chargeBoxIdentity>FR-S35-E35238-003-1</ocpp:chargeBoxIdentity>
                        <a:Action v:mustUnderstand="true">/MeterValues</a:Action>
                        <a:MessageID>urn:uuid:f7523b7f-ccf3-425b-8177-8ab60db19d45</a:MessageID>
                        <a:From>
                            <a:Address>http://10.93.252.243/ocpp</a:Address>
                        </a:From>
                        <a:To v:mustUnderstand="true">http://sde35-g2.vpn.l30.sodetrel.fr/ocpp/v16s</a:To>
                        <a:ReplyTo v:mustUnderstand="true">
                            <a:Address>http://www.w3.org/2005/08/addressing/anonymous</a:Address>
                        </a:ReplyTo>
                    </v:Header>
                    <v:Body>
                        <ocpp:meterValuesRequest>
                            <ocpp:connectorId>1</ocpp:connectorId>
                            <ocpp:transactionId>15917</ocpp:transactionId>
                            <ocpp:meterValue>
                                <ocpp:timestamp>2022-05-17T15:41:19.912Z</ocpp:timestamp>
                                <ocpp:sampledValue>
                                    <ocpp:context>Sample.Periodic</ocpp:context>
                                    <ocpp:location>Inlet</ocpp:location>
                                    <ocpp:measurand>Energy.Active.Import.Register</ocpp:measurand>
                                    <ocpp:unit>Wh</ocpp:unit>
                                    <ocpp:value>15213716</ocpp:value>
                                </ocpp:sampledValue>
                            </ocpp:meterValue>
                        </ocpp:meterValuesRequest>
                    </v:Body>
                </v:Envelope>
            """.trimIndent()

        expectThat(Ocpp16SoapParser().parseRequestFromSoap<MeterValuesReq>(message).payload).and {
            get { connectorId }.isEqualTo(1)
            get { transactionId }.isEqualTo(15917)
            get { meterValue }.hasSize(1).first().and {
                get { timestamp }.isEqualTo(Instant.parse("2022-05-17T15:41:19.912Z"))
                get { sampledValue }.hasSize(1).first().and {
                    get { value }.isEqualTo("15213716")
                    get { context }.isEqualTo(ReadingContext.SamplePeriodic)
                    get { format }.isEqualTo(ValueFormat.Raw)
                    get { location }.isEqualTo(Location.Inlet)
                    get { measurand }.isEqualTo(Measurand.EnergyActiveImportRegister)
                    get { phase }.isNull()
                    get { unit }.isEqualTo(UnitOfMeasure.Wh)
                }
            }
        }
    }

    @Test
    fun `should parse message to StartTransactionReq`() {
        val message =
            """
                <v:Envelope xmlns:v="http://www.w3.org/2003/05/soap-envelope" xmlns:a="http://www.w3.org/2005/08/addressing"
                            xmlns:ocpp="urn://Ocpp/Cs/2015/10/">
                    <v:Header>
                        <ocpp:chargeBoxIdentity>CU-35251-001-1</ocpp:chargeBoxIdentity>
                        <a:Action v:mustUnderstand="true">/StartTransaction</a:Action>
                        <a:MessageID>urn:uuid:0aa92653-5cd8-465f-89d0-eca833aec4c2</a:MessageID>
                        <a:From>
                            <a:Address>http://10.93.252.224/ocpp</a:Address>
                        </a:From>
                        <a:To v:mustUnderstand="true">http://sde35-g2.vpn.l30.sodetrel.fr/ocpp/v16s/</a:To>
                        <a:ReplyTo v:mustUnderstand="true">
                            <a:Address>http://www.w3.org/2005/08/addressing/anonymous</a:Address>
                        </a:ReplyTo>
                    </v:Header>
                    <v:Body>
                        <ocpp:startTransactionRequest>
                            <ocpp:connectorId>1</ocpp:connectorId>
                            <ocpp:idTag>046924C2D86485</ocpp:idTag>
                            <ocpp:meterStart>18804500</ocpp:meterStart>
                            <ocpp:timestamp>2022-05-17T15:41:58.351Z</ocpp:timestamp>
                        </ocpp:startTransactionRequest>
                    </v:Body>
                </v:Envelope>
            """.trimIndent()

        expectThat(Ocpp16SoapParser().parseRequestFromSoap<StartTransactionReq>(message).payload).and {
            get { connectorId }.isEqualTo(1)
            get { idTag }.isEqualTo("046924C2D86485")
            get { meterStart }.isEqualTo(18804500)
            get { timestamp }.isEqualTo(Instant.parse("2022-05-17T15:41:58.351Z"))
            get { reservationId }.isNull()
        }
    }

    @Test
    fun `should parse message to StatusNotificationReq`() {
        val message =
            """
                <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:cs="urn://Ocpp/Cs/2015/10/"
                               xmlns:wsa5="http://www.w3.org/2005/08/addressing"
                >
                    <soap:Header>
                        <cs:chargeBoxIdentity soap:mustUnderstand="true">IES20210510592612</cs:chargeBoxIdentity>
                        <wsa5:From>
                            <wsa5:Address>http://10.66.5.160:8085</wsa5:Address>
                        </wsa5:From>
                        <wsa5:MessageID>urn:uuid:70c76024-1281-471f-8578-ad51a2a88134</wsa5:MessageID>
                        <wsa5:ReplyTo soap:mustUnderstand="true">
                            <wsa5:Address>http://www.w3.org/2005/08/addressing/anonymous</wsa5:Address>
                        </wsa5:ReplyTo>
                        <wsa5:To soap:mustUnderstand="true">http://sigeif-ies.vpn.l30.sodetrel.fr/ocpp/v16s</wsa5:To>
                        <wsa5:Action soap:mustUnderstand="true">/StatusNotification</wsa5:Action>
                    </soap:Header>
                    <soap:Body>
                        <cs:statusNotificationRequest>
                            <cs:connectorId>1</cs:connectorId>
                            <cs:status>Available</cs:status>
                            <cs:errorCode>NoError</cs:errorCode>
                            <cs:info>No error.</cs:info>
                            <cs:timestamp>2022-05-17T15:41:59.486Z</cs:timestamp>
                            <cs:vendorErrorCode>0x0</cs:vendorErrorCode>
                        </cs:statusNotificationRequest>
                    </soap:Body>
                </soap:Envelope>
            """.trimIndent()

        expectThat(Ocpp16SoapParser().parseRequestFromSoap<StatusNotificationReq>(message).payload).and {
            get { connectorId }.isEqualTo(1)
            get { errorCode }.isEqualTo(ChargePointErrorCode.NoError)
            get { status }.isEqualTo(ChargePointStatus.Available)
            get { info }.isEqualTo("No error.")
            get { timestamp }.isEqualTo(Instant.parse("2022-05-17T15:41:59.486Z"))
            get { vendorErrorCode }.isEqualTo("0x0")
            get { vendorId }.isNull()
        }
    }

    @Test
    fun `should parse message to StopTransactionReq`() {
        val message =
            """
                <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://www.w3.org/2003/05/soap-envelope">
                    <SOAP-ENV:Header>
                        <Action xmlns="http://www.w3.org/2005/08/addressing">/StopTransaction</Action>
                        <MessageID xmlns="http://www.w3.org/2005/08/addressing">urn:uuid:3b30544e-eafe-4fba-af6e-5ae00612f788
                        </MessageID>
                        <To xmlns="http://www.w3.org/2005/08/addressing">http://wiiz.vpn.l30.sodetrel.fr/ocpp/v15s/</To>
                        <ReplyTo xmlns="http://www.w3.org/2005/08/addressing">
                            <Address>http://www.w3.org/2005/08/addressing/anonymous</Address>
                        </ReplyTo>
                        <From xmlns="http://www.w3.org/2005/08/addressing">
                            <Address>http://192.168.0.102:8080</Address>
                        </From>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2012/06/">00:13:F6:01:8A:BB</chargeBoxIdentity>
                    </SOAP-ENV:Header>
                    <SOAP-ENV:Body>
                        <stopTransactionRequest xmlns="urn://Ocpp/Cs/2012/06/">
                            <transactionId>16696</transactionId>
                            <idTag>2D0E360A</idTag>
                            <timestamp>2022-05-05T04:37:15Z</timestamp>
                            <meterStop>19224</meterStop>
                        </stopTransactionRequest>
                    </SOAP-ENV:Body>
                </SOAP-ENV:Envelope>
            """.trimIndent()

        expectThat(Ocpp16SoapParser().parseRequestFromSoap<StopTransactionReq>(message).payload).and {
            get { meterStop }.isEqualTo(19224)
            get { timestamp }.isEqualTo(Instant.parse("2022-05-05T04:37:15Z"))
            get { transactionId }.isEqualTo(16696)
            get { idTag }.isEqualTo("2D0E360A")
            get { reason }.isNull()
            get { transactionData }.isNull()
        }
    }

    @Test
    fun `should not parse message to AuthorizeReq because it is not an Authorize`() {
        val message =
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

        expectThrows<IllegalArgumentException> {
            Ocpp16SoapParser().parseRequestFromSoap<AuthorizeReq>(message)
        }
    }

    @Test
    fun `should not parse message to BootNotificationReq because it is not a BootNotification`() {
        val message =
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

        expectThrows<IllegalArgumentException> {
            Ocpp16SoapParser().parseRequestFromSoap<BootNotificationReq>(message)
        }
    }

    @Test
    fun `should not parse message to DataTransferReq because it is not a DataTransfer`() {
        val message =
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

        expectThrows<IllegalArgumentException> {
            Ocpp16SoapParser().parseRequestFromSoap<DataTransferReq>(message)
        }
    }

    @Test
    fun `should not parse message to DiagnosticsStatusNotificationReq because it is not a DiagnosticsStatusNotification`() {
        val message =
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

        expectThrows<IllegalArgumentException> {
            Ocpp16SoapParser().parseRequestFromSoap<DiagnosticsStatusNotificationReq>(message)
        }
    }

    @Test
    fun `should not parse message to FirmwareStatusNotificationReq because it is not a FirmwareStatusNotification`() {
        val message =
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

        expectThrows<IllegalArgumentException> {
            Ocpp16SoapParser().parseRequestFromSoap<FirmwareStatusNotificationReq>(message)
        }
    }

    @Test
    fun `should not parse message to HeartbeatReq because it is not an Heartbeat`() {
        val message =
            """
                <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:cs="urn://Ocpp/Cs/2015/10/"
                   xmlns:wsa5="http://www.w3.org/2005/08/addressing"
                >
                    <soap:Header>
                        <cs:chargeBoxIdentity soap:mustUnderstand="true">IES20210511714911</cs:chargeBoxIdentity>
                        <wsa5:From>
                            <wsa5:Address>http://10.66.9.194:8085</wsa5:Address>
                        </wsa5:From>
                        <wsa5:MessageID>urn:uuid:8fe7ef88-0f2e-47f3-911d-dbd36b32f366</wsa5:MessageID>
                        <wsa5:ReplyTo soap:mustUnderstand="true">
                            <wsa5:Address>http://www.w3.org/2005/08/addressing/anonymous</wsa5:Address>
                        </wsa5:ReplyTo>
                        <wsa5:To soap:mustUnderstand="true">http://bretagne-sde35-ies.vpn.l30.sodetrel.fr/ocpp/v16s/</wsa5:To>
                        <wsa5:Action soap:mustUnderstand="true">/FirmwareStatusNotification</wsa5:Action>
                    </soap:Header>
                    <soap:Body>
                        <cs:firmwareStatusNotificationRequest>
                            <cs:status>Installed</cs:status>
                        </cs:firmwareStatusNotificationRequest>
                    </soap:Body>
                </soap:Envelope>
            """.trimIndent()

        expectThrows<IllegalArgumentException> {
            Ocpp16SoapParser().parseRequestFromSoap<HeartbeatReq>(message)
        }
    }

    @Test
    fun `should not parse message to MeterValuesReq because it is not a MeterValues`() {
        val message =
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

        expectThrows<IllegalArgumentException> {
            Ocpp16SoapParser().parseRequestFromSoap<MeterValuesReq>(message)
        }
    }

    @Test
    fun `should not parse message to StartTransactionReq because it is not a StartTransaction`() {
        val message =
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

        expectThrows<IllegalArgumentException> {
            Ocpp16SoapParser().parseRequestFromSoap<StartTransactionReq>(message)
        }
    }

    @Test
    fun `should not parse message to StatusNotificationReq because it is not a StatusNotification`() {
        val message =
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

        expectThrows<IllegalArgumentException> {
            Ocpp16SoapParser().parseRequestFromSoap<StatusNotificationReq>(message)
        }
    }

    @Test
    fun `should not parse message to StopTransactionReq because it is not a StopTransaction`() {
        val message =
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

        expectThrows<IllegalArgumentException> {
            Ocpp16SoapParser().parseRequestFromSoap<StopTransactionReq>(message)
        }
    }

    @Test
    fun `should map AuthorizeReq to soap`() {
        val request = AuthorizeReq(
            idTag = "049E16B2EC7180"
        )

        val messageSoap = Ocpp16SoapParser().mapRequestToSoap(
            RequestSoapMessage(
                messageId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                chargingStationId = "00:80:F4:44:13:AA",
                action = "Authorize",
                from = "source",
                to = "destination",
                payload = request
            )
        )

        val expectedEnvelope =
            """
                <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:cs="urn://Ocpp/Cs/2015/10/" xmlns:wsa5="http://www.w3.org/2005/08/addressing">
                    <soap:Header>
                        <chargeBoxIdentity soap:mustUnderstand="true">00:80:F4:44:13:AA</chargeBoxIdentity>
                        <MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</MessageID>
                        <From>
                            <Address>source</Address>
                        </From>
                        <ReplyTo soap:mustUnderstand="true">
                            <Address>http://www.w3.org/2005/08/addressing/anonymous</Address>
                        </ReplyTo>
                        <To soap:mustUnderstand="true">destination</To>
                        <Action soap:mustUnderstand="true">/Authorize</Action>
                    </soap:Header>
                    <soap:Body>
                        <authorizeRequest>
                            <idTag>049E16B2EC7180</idTag>
                        </authorizeRequest>
                    </soap:Body>
                </soap:Envelope>
            """.trimIndent()

        expectThat(messageSoap.inline()).isEqualTo(expectedEnvelope.inline())
        expectThat(parseToEnvelope(messageSoap)) {
            get { body.authorizeRequest }.isNotNull().and {
                get { idTag }.isEqualTo("049E16B2EC7180")
            }
        }
    }

    @Test
    fun `should map AuthorizeResp to soap`() {
        val response = AuthorizeResp(
            idTagInfo = IdTagInfo(
                status = AuthorizationStatus.Accepted,
                expiryDate = Instant.parse("2022-05-16T15:42:05.128Z")
            )
        )

        val messageSoap = Ocpp16SoapParser().mapResponseToSoap(
            ResponseSoapMessage(
                messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                action = "Authorize",
                payload = response
            )
        )

        val expectedEnvelope =
            """
                <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope">
                    <soap:Header>
                        <Action xmlns="http://www.w3.org/2005/08/addressing">/AuthorizeResponse</Action>
                        <MessageID xmlns="http://www.w3.org/2005/08/addressing">urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</MessageID>
                        <To xmlns="http://www.w3.org/2005/08/addressing">http://www.w3.org/2005/08/addressing/anonymous</To>
                        <RelatesTo xmlns="http://www.w3.org/2005/08/addressing">urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</RelatesTo>
                    </soap:Header>
                    <soap:Body>
                        <authorizeResponse xmlns="urn://Ocpp/Cs/2015/10/">
                            <idTagInfo>
                                <status>Accepted</status>
                                <expiryDate>2022-05-16T15:42:05.128Z</expiryDate>
                            </idTagInfo>
                        </authorizeResponse>
                    </soap:Body>
                </soap:Envelope>
            """.trimIndent()

        expectThat(messageSoap.inline()).isEqualTo(expectedEnvelope.inline())
        expectThat(parseToEnvelope(messageSoap)) {
            get { body.authorizeResponse }.isNotNull().and {
                get { idTagInfo }.and {
                    get { status }.isEqualTo(AuthorizationStatus.Accepted)
                    get { expiryDate }.isEqualTo(Instant.parse("2022-05-16T15:42:05.128Z"))
                }
            }
        }
    }

    @Test
    fun `should map BootNotificationReq to soap`() {
        val request = BootNotificationReq(
            chargePointVendor = "Izivia",
            chargePointModel = "LEC-3030A-SPX1",
            chargePointSerialNumber = "5aa469fd41344fe5a575368cd",
            firmwareVersion = "1.5.1.d723fd5",
        )

        val messageSoap = Ocpp16SoapParser().mapRequestToSoap(
            RequestSoapMessage(
                messageId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                chargingStationId = "00:80:F4:44:13:AA",
                action = "BootNotification",
                from = "source",
                to = "destination",
                payload = request
            )
        )

        val expectedEnvelope =
            """
                <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:cs="urn://Ocpp/Cs/2015/10/" xmlns:wsa5="http://www.w3.org/2005/08/addressing">
                    <soap:Header>
                        <chargeBoxIdentity soap:mustUnderstand="true">00:80:F4:44:13:AA</chargeBoxIdentity>
                        <MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</MessageID>
                        <From>
                            <Address>source</Address>
                        </From>
                        <ReplyTo soap:mustUnderstand="true">
                            <Address>http://www.w3.org/2005/08/addressing/anonymous</Address>
                        </ReplyTo>
                        <To soap:mustUnderstand="true">destination</To>
                        <Action soap:mustUnderstand="true">/BootNotification</Action>
                    </soap:Header>
                    <soap:Body>
                        <bootNotificationRequest>
                            <chargePointModel>LEC-3030A-SPX1</chargePointModel>
                            <chargePointVendor>Izivia</chargePointVendor>
                            <chargePointSerialNumber>5aa469fd41344fe5a575368cd</chargePointSerialNumber>
                            <firmwareVersion>1.5.1.d723fd5</firmwareVersion>
                        </bootNotificationRequest>
                    </soap:Body>
                </soap:Envelope>
            """.trimIndent()

        expectThat(messageSoap.inline()).isEqualTo(expectedEnvelope.inline())
        expectThat(parseToEnvelope(messageSoap)) {
            get { body.bootNotificationRequest }.isNotNull().and {
                get { chargePointVendor }.isEqualTo("Izivia")
                get { chargePointModel }.isEqualTo("LEC-3030A-SPX1")
                get { chargePointSerialNumber }.isEqualTo("5aa469fd41344fe5a575368cd")
                get { firmwareVersion }.isEqualTo("1.5.1.d723fd5")
            }
        }
    }

    @Test
    fun `should map BootNotificationResp to soap`() {
        val response = BootNotificationResp(
            status = RegistrationStatus.Rejected,
            currentTime = Instant.parse("2022-05-17T15:43:08.025Z"),
            interval = 1800,
        )

        val messageSoap = Ocpp16SoapParser().mapResponseToSoap(
            ResponseSoapMessage(
                messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                action = "BootNotification",
                payload = response
            )
        )

        expectThat(messageSoap.inline()) {
            get { this }.contains("<Action xmlns=\"http://www.w3.org/2005/08/addressing\">/BootNotificationResponse</Action>")
            get { this }.contains("<MessageID xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</MessageID>")
            get { this }.contains("<RelatesTo xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</RelatesTo>")
        }
        expectThat(parseToEnvelope(messageSoap)) {
            get { body.bootNotificationResponse }.isNotNull().and {
                get { status }.isEqualTo(RegistrationStatus.Rejected)
                get { currentTime }.isEqualTo(Instant.parse("2022-05-17T15:43:08.025Z"))
                get { interval }.isEqualTo(1800)
            }
        }
    }

    @Test
    fun `should map DataTransferReq to soap`() {
        val request = DataTransferReq(
            vendorId = "Schneider Electric",
            messageId = "Detection loop",
            data = "{\"connectorId\":10,\"name\":\"Vehicle\",\"state\":\"1\",\"timestamp\":\"2022-05-17T15:42:03Z:\"}"
        )

        val messageSoap = Ocpp16SoapParser().mapRequestToSoap(
            RequestSoapMessage(
                messageId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                chargingStationId = "00:80:F4:44:13:AA",
                action = "DataTransfer",
                from = "source",
                to = "destination",
                payload = request
            )
        )

        val expectedEnvelope =
            """
                <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:cs="urn://Ocpp/Cs/2015/10/" xmlns:wsa5="http://www.w3.org/2005/08/addressing">
                    <soap:Header>
                        <chargeBoxIdentity soap:mustUnderstand="true">00:80:F4:44:13:AA</chargeBoxIdentity>
                        <MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</MessageID>
                        <From>
                            <Address>source</Address>
                        </From>
                        <ReplyTo soap:mustUnderstand="true">
                            <Address>http://www.w3.org/2005/08/addressing/anonymous</Address>
                        </ReplyTo>
                        <To soap:mustUnderstand="true">destination</To>
                        <Action soap:mustUnderstand="true">/DataTransfer</Action>
                    </soap:Header>
                    <soap:Body>
                        <dataTransferRequest>
                            <vendorId>Schneider Electric</vendorId>
                            <messageId>Detection loop</messageId>
                            <data>{"connectorId":10,"name":"Vehicle","state":"1","timestamp":"2022-05-17T15:42:03Z:"}</data>
                        </dataTransferRequest>
                    </soap:Body>
                </soap:Envelope>
            """.trimIndent()

        expectThat(messageSoap.inline()).isEqualTo(expectedEnvelope.inline())
        expectThat(parseToEnvelope(messageSoap)) {
            get { body.dataTransferRequest }.isNotNull().and {
                get { vendorId }.isEqualTo("Schneider Electric")
                get { messageId }.isEqualTo("Detection loop")
                get { data }.isEqualTo("{\"connectorId\":10,\"name\":\"Vehicle\",\"state\":\"1\",\"timestamp\":\"2022-05-17T15:42:03Z:\"}")
            }
        }
    }

    @Test
    fun `should map DataTransferResp to soap`() {
        val response = DataTransferResp(
            status = DataTransferStatus.Accepted
        )

        val messageSoap = Ocpp16SoapParser().mapResponseToSoap(
            ResponseSoapMessage(
                messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                action = "DataTransfer",
                payload = response
            )
        )

        expectThat(messageSoap.inline()) {
            get { this }.contains("<Action xmlns=\"http://www.w3.org/2005/08/addressing\">/DataTransferResponse</Action>")
            get { this }.contains("<MessageID xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</MessageID>")
            get { this }.contains("<RelatesTo xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</RelatesTo>")
        }
        expectThat(parseToEnvelope(messageSoap)) {
            get { body.dataTransferResponse }.isNotNull().and {
                get { status }.isEqualTo(DataTransferStatus.Accepted)
            }
        }
    }

    @Disabled
    @Test
    fun `should map DiagnosticsStatusNotificationReq to soap`() {
        // TODO
    }

    @Disabled
    @Test
    fun `should map DiagnosticsStatusNotificationResp to soap`() {
        // TODO
    }

    @Test
    fun `should map FirmwareStatusNotificationReq to soap`() {
        val response = FirmwareStatusNotificationReq(
            status = FirmwareStatus.Installed
        )

        val messageSoap = Ocpp16SoapParser().mapRequestToSoap(
            RequestSoapMessage(
                messageId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                chargingStationId = "00:80:F4:44:13:AA",
                action = "FirmwareStatusNotification",
                from = "source",
                to = "destination",
                payload = response
            )
        )

        val expectedEnvelope =
            """
                <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:cs="urn://Ocpp/Cs/2015/10/" xmlns:wsa5="http://www.w3.org/2005/08/addressing">
                    <soap:Header>
                        <chargeBoxIdentity soap:mustUnderstand="true">00:80:F4:44:13:AA</chargeBoxIdentity>
                        <MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</MessageID>
                        <From>
                            <Address>source</Address>
                        </From>
                        <ReplyTo soap:mustUnderstand="true">
                            <Address>http://www.w3.org/2005/08/addressing/anonymous</Address>
                        </ReplyTo>
                        <To soap:mustUnderstand="true">destination</To>
                        <Action soap:mustUnderstand="true">/FirmwareStatusNotification</Action>
                    </soap:Header>
                    <soap:Body>
                        <firmwareStatusNotificationRequest>
                            <status>Installed</status>
                        </firmwareStatusNotificationRequest>
                    </soap:Body>
                </soap:Envelope>
            """.trimIndent()

        expectThat(messageSoap.inline()).isEqualTo(expectedEnvelope.inline())
        expectThat(parseToEnvelope(messageSoap)) {
            get { body.firmwareStatusNotificationRequest }.isNotNull().and {
                get { status }.isEqualTo(FirmwareStatus.Installed)
            }
        }
    }

    @Test
    fun `should map FirmwareStatusNotificationResp to soap`() {
        val response = FirmwareStatusNotificationResp()

        val messageSoap = Ocpp16SoapParser().mapResponseToSoap(
            ResponseSoapMessage(
                messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                action = "FirmwareStatusNotification",
                payload = response
            )
        )

        val expectedEnvelope =
            """
                <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope">
                    <soap:Header>
                        <Action xmlns="http://www.w3.org/2005/08/addressing">/FirmwareStatusNotificationResponse</Action>
                        <MessageID xmlns="http://www.w3.org/2005/08/addressing">urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</MessageID>
                        <To xmlns="http://www.w3.org/2005/08/addressing">http://www.w3.org/2005/08/addressing/anonymous</To>
                        <RelatesTo xmlns="http://www.w3.org/2005/08/addressing">urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</RelatesTo>
                    </soap:Header>
                    <soap:Body>
                        <firmwareStatusNotificationResponse xmlns="urn://Ocpp/Cs/2015/10/"/>
                    </soap:Body>
                </soap:Envelope>
            """.trimIndent()

        expectThat(messageSoap.inline()).isEqualTo(expectedEnvelope.inline())
        expectThat(parseToEnvelope(messageSoap)) {
            get { body.firmwareStatusNotificationResponse }.isNotNull()
        }
    }

    @Test
    fun `should map HeartbeatReq to soap`() {
        val response = HeartbeatReq()

        val messageSoap = Ocpp16SoapParser().mapRequestToSoap(
            RequestSoapMessage(
                messageId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                chargingStationId = "00:80:F4:44:13:AA",
                action = "Heartbeat",
                from = "source",
                to = "destination",
                payload = response
            )
        )

        val expectedEnvelope =
            """
                <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:cs="urn://Ocpp/Cs/2015/10/" xmlns:wsa5="http://www.w3.org/2005/08/addressing">
                    <soap:Header>
                        <chargeBoxIdentity soap:mustUnderstand="true">00:80:F4:44:13:AA</chargeBoxIdentity>
                        <MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</MessageID>
                        <From>
                            <Address>source</Address>
                        </From>
                        <ReplyTo soap:mustUnderstand="true">
                            <Address>http://www.w3.org/2005/08/addressing/anonymous</Address>
                        </ReplyTo>
                        <To soap:mustUnderstand="true">destination</To>
                        <Action soap:mustUnderstand="true">/Heartbeat</Action>
                    </soap:Header>
                    <soap:Body>
                        <heartbeatRequest/>
                    </soap:Body>
                </soap:Envelope>
            """.trimIndent()

        expectThat(messageSoap.inline()).isEqualTo(expectedEnvelope.inline())
        expectThat(parseToEnvelope(messageSoap)) {
            get { body.heartbeatRequest }.isNotNull()
        }
    }

    @Test
    fun `should map HeartbeatResp to soap`() {
        val response = HeartbeatResp(
            currentTime = Instant.parse("2022-05-17T15:42:00.503Z")
        )

        val messageSoap = Ocpp16SoapParser().mapResponseToSoap(
            ResponseSoapMessage(
                messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                action = "Heartbeat",
                payload = response
            )
        )

        expectThat(messageSoap.inline()) {
            get { this }.contains("<Action xmlns=\"http://www.w3.org/2005/08/addressing\">/HeartbeatResponse</Action>")
            get { this }.contains("<MessageID xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</MessageID>")
            get { this }.contains("<RelatesTo xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</RelatesTo>")
        }
        expectThat(parseToEnvelope(messageSoap)) {
            get { body.heartbeatResponse }.isNotNull().and {
                get { currentTime }.isEqualTo(Instant.parse("2022-05-17T15:42:00.503Z"))
            }
        }
    }

    @Test
    fun `should map MeterValuesReq to soap`() {
        val response = MeterValuesReq(
            connectorId = 1,
            transactionId = 15917,
            meterValue = listOf(
                MeterValue(
                    timestamp = Instant.parse("2022-05-17T15:41:19.912Z"),
                    sampledValue = listOf(
                        SampledValue(
                            context = ReadingContext.SamplePeriodic,
                            location = Location.Inlet,
                            measurand = Measurand.EnergyActiveImportRegister,
                            unit = UnitOfMeasure.Wh,
                            value = "15213716"
                        )
                    )
                )
            )
        )

        val messageSoap = Ocpp16SoapParser().mapRequestToSoap(
            RequestSoapMessage(
                messageId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                chargingStationId = "00:80:F4:44:13:AA",
                action = "MeterValues",
                from = "source",
                to = "destination",
                payload = response
            )
        )

        val expectedEnvelope =
            """
                <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:cs="urn://Ocpp/Cs/2015/10/" xmlns:wsa5="http://www.w3.org/2005/08/addressing">
                    <soap:Header>
                        <chargeBoxIdentity soap:mustUnderstand="true">00:80:F4:44:13:AA</chargeBoxIdentity>
                        <MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</MessageID>
                        <From>
                            <Address>source</Address>
                        </From>
                        <ReplyTo soap:mustUnderstand="true">
                            <Address>http://www.w3.org/2005/08/addressing/anonymous</Address>
                        </ReplyTo>
                        <To soap:mustUnderstand="true">destination</To>
                        <Action soap:mustUnderstand="true">/MeterValues</Action>
                    </soap:Header>
                    <soap:Body>
                        <meterValuesRequest>
                            <connectorId>1</connectorId>
                            <meterValue>
                                <sampledValue>
                                    <value>15213716</value>
                                    <context>Sample.Periodic</context>
                                    <format>Raw</format>
                                    <location>Inlet</location>
                                    <measurand>Energy.Active.Import.Register</measurand>
                                    <unit>Wh</unit>
                                </sampledValue>
                                <timestamp>2022-05-17T15:41:19.912Z</timestamp>
                            </meterValue>
                            <transactionId>15917</transactionId>
                        </meterValuesRequest>
                    </soap:Body>
                </soap:Envelope>
            """.trimIndent()

        expectThat(messageSoap.inline()).isEqualTo(expectedEnvelope.inline())
        expectThat(parseToEnvelope(messageSoap)) {
            get { body.meterValuesRequest }.isNotNull().and {
                get { connectorId }.isEqualTo(1)
                get { transactionId }.isEqualTo(15917)
                get { meterValue }.hasSize(1).first().and {
                    get { timestamp }.isEqualTo(Instant.parse("2022-05-17T15:41:19.912Z"))
                    get { sampledValue }.hasSize(1).first().and {
                        get { context }.isEqualTo(ReadingContext.SamplePeriodic)
                        get { location }.isEqualTo(Location.Inlet)
                        get { measurand }.isEqualTo(Measurand.EnergyActiveImportRegister)
                        get { unit }.isEqualTo(UnitOfMeasure.Wh)
                        get { value }.isEqualTo("15213716")
                    }
                }
            }
        }
    }

    @Test
    fun `should map MeterValuesResp to soap`() {
        val response = MeterValuesResp()

        val messageSoap = Ocpp16SoapParser().mapResponseToSoap(
            ResponseSoapMessage(
                messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                action = "MeterValues",
                payload = response
            )
        )

        expectThat(messageSoap.inline()) {
            get { this }.contains("<Action xmlns=\"http://www.w3.org/2005/08/addressing\">/MeterValuesResponse</Action>")
            get { this }.contains("<MessageID xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</MessageID>")
            get { this }.contains("<RelatesTo xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</RelatesTo>")
        }
        expectThat(parseToEnvelope(messageSoap)) {
            get { body.meterValuesResponse }.isNotNull()
        }
    }

    @Test
    fun `should map StartTransactionReq to soap`() {
        val response = StartTransactionReq(
            connectorId = 1,
            idTag = "046924C2D86485",
            meterStart = 18804500,
            timestamp = Instant.parse("2022-05-17T15:41:58.351Z")
        )

        val messageSoap = Ocpp16SoapParser().mapRequestToSoap(
            RequestSoapMessage(
                messageId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                chargingStationId = "00:80:F4:44:13:AA",
                action = "StartTransaction",
                from = "source",
                to = "destination",
                payload = response
            )
        )

        val expectedEnvelope =
            """
                <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:cs="urn://Ocpp/Cs/2015/10/" xmlns:wsa5="http://www.w3.org/2005/08/addressing">
                    <soap:Header>
                        <chargeBoxIdentity soap:mustUnderstand="true">00:80:F4:44:13:AA</chargeBoxIdentity>
                        <MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</MessageID>
                        <From>
                            <Address>source</Address>
                        </From>
                        <ReplyTo soap:mustUnderstand="true">
                            <Address>http://www.w3.org/2005/08/addressing/anonymous</Address>
                        </ReplyTo>
                        <To soap:mustUnderstand="true">destination</To>
                        <Action soap:mustUnderstand="true">/StartTransaction</Action>
                    </soap:Header>
                    <soap:Body>
                        <startTransactionRequest>
                            <connectorId>1</connectorId>
                            <idTag>046924C2D86485</idTag>
                            <meterStart>18804500</meterStart>
                            <timestamp>2022-05-17T15:41:58.351Z</timestamp>
                        </startTransactionRequest>
                    </soap:Body>
                </soap:Envelope>
            """.trimIndent()

        expectThat(messageSoap.inline()).isEqualTo(expectedEnvelope.inline())
        expectThat(parseToEnvelope(messageSoap)) {
            get { body.startTransactionRequest }.isNotNull().and {
                get { connectorId }.isEqualTo(1)
                get { idTag }.isEqualTo("046924C2D86485")
                get { meterStart }.isEqualTo(18804500)
                get { timestamp }.isEqualTo(Instant.parse("2022-05-17T15:41:58.351Z"))
            }
        }
    }

    @Test
    fun `should map StartTransactionResp to soap`() {
        val response = StartTransactionResp(
            transactionId = 15671,
            idTagInfo = IdTagInfo(
                status = AuthorizationStatus.Accepted,
                expiryDate = Instant.parse("2022-05-16T15:42:02.617Z")
            )
        )

        val messageSoap = Ocpp16SoapParser().mapResponseToSoap(
            ResponseSoapMessage(
                messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                action = "StartTransaction",
                payload = response
            )
        )

        expectThat(messageSoap.inline()) {
            get { this }.contains("<Action xmlns=\"http://www.w3.org/2005/08/addressing\">/StartTransactionResponse</Action>")
            get { this }.contains("<MessageID xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</MessageID>")
            get { this }.contains("<RelatesTo xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</RelatesTo>")
        }
        expectThat(parseToEnvelope(messageSoap)) {
            get { body.startTransactionResponse }.isNotNull().and {
                get { transactionId }.isEqualTo(15671)
                get { idTagInfo }.and {
                    get { status }.isEqualTo(AuthorizationStatus.Accepted)
                    get { expiryDate }.isEqualTo(Instant.parse("2022-05-16T15:42:02.617Z"))
                }
            }
        }
    }

    @Test
    fun `should map StatusNotificationReq to soap`() {
        val response = StatusNotificationReq(
            connectorId = 1,
            status = ChargePointStatus.Available,
            errorCode = ChargePointErrorCode.NoError,
            info = "No error.",
            timestamp = Instant.parse("2022-05-17T15:41:59.486Z"),
            vendorErrorCode = "0x0"
        )

        val messageSoap = Ocpp16SoapParser().mapRequestToSoap(
            RequestSoapMessage(
                messageId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                chargingStationId = "00:80:F4:44:13:AA",
                action = "StatusNotification",
                from = "source",
                to = "destination",
                payload = response
            )
        )

        val expectedEnvelope =
            """
                <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:cs="urn://Ocpp/Cs/2015/10/" xmlns:wsa5="http://www.w3.org/2005/08/addressing">
                    <soap:Header>
                        <chargeBoxIdentity soap:mustUnderstand="true">00:80:F4:44:13:AA</chargeBoxIdentity>
                        <MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</MessageID>
                        <From>
                            <Address>source</Address>
                        </From>
                        <ReplyTo soap:mustUnderstand="true">
                            <Address>http://www.w3.org/2005/08/addressing/anonymous</Address>
                        </ReplyTo>
                        <To soap:mustUnderstand="true">destination</To>
                        <Action soap:mustUnderstand="true">/StatusNotification</Action>
                    </soap:Header>
                    <soap:Body>
                        <statusNotificationRequest>
                            <connectorId>1</connectorId>
                            <errorCode>NoError</errorCode>
                            <status>Available</status>
                            <info>No error.</info>
                            <timestamp>2022-05-17T15:41:59.486Z</timestamp>
                            <vendorErrorCode>0x0</vendorErrorCode>
                        </statusNotificationRequest>
                    </soap:Body>
                </soap:Envelope>
            """.trimIndent()

        expectThat(messageSoap.inline()).isEqualTo(expectedEnvelope.inline())
        expectThat(parseToEnvelope(messageSoap)) {
            get { body.statusNotificationRequest }.isNotNull().and {
                get { connectorId }.isEqualTo(1)
                get { status }.isEqualTo(ChargePointStatus.Available)
                get { errorCode }.isEqualTo(ChargePointErrorCode.NoError)
                get { info }.isEqualTo("No error.")
                get { timestamp }.isEqualTo(Instant.parse("2022-05-17T15:41:59.486Z"))
                get { vendorErrorCode }.isEqualTo("0x0")
            }
        }
    }

    @Test
    fun `should map StatusNotificationResp to soap`() {
        val response = StatusNotificationResp()

        val messageSoap = Ocpp16SoapParser().mapResponseToSoap(
            ResponseSoapMessage(
                messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                action = "StatusNotification",
                payload = response
            )
        )

        expectThat(messageSoap.inline()) {
            get { this }.contains("<Action xmlns=\"http://www.w3.org/2005/08/addressing\">/StatusNotificationResponse</Action>")
            get { this }.contains("<MessageID xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</MessageID>")
            get { this }.contains("<RelatesTo xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</RelatesTo>")
        }
        expectThat(parseToEnvelope(messageSoap)) {
            get { body.statusNotificationResponse }.isNotNull()
        }
    }

    @Test
    fun `should map StopTransactionReq to soap`() {
        val response = StopTransactionReq(
            transactionId = 16696,
            idTag = "2D0E360A",
            timestamp = Instant.parse("2022-05-05T04:37:15Z"),
            meterStop = 19224
        )

        val messageSoap = Ocpp16SoapParser().mapRequestToSoap(
            RequestSoapMessage(
                messageId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                chargingStationId = "00:80:F4:44:13:AA",
                action = "StopTransaction",
                from = "source",
                to = "destination",
                payload = response
            )
        )

        val expectedEnvelope =
            """
                <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:cs="urn://Ocpp/Cs/2015/10/" xmlns:wsa5="http://www.w3.org/2005/08/addressing">
                    <soap:Header>
                        <chargeBoxIdentity soap:mustUnderstand="true">00:80:F4:44:13:AA</chargeBoxIdentity>
                        <MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</MessageID>
                        <From>
                            <Address>source</Address>
                        </From>
                        <ReplyTo soap:mustUnderstand="true">
                            <Address>http://www.w3.org/2005/08/addressing/anonymous</Address>
                        </ReplyTo>
                        <To soap:mustUnderstand="true">destination</To>
                        <Action soap:mustUnderstand="true">/StopTransaction</Action>
                    </soap:Header>
                    <soap:Body>
                        <stopTransactionRequest>
                            <meterStop>19224</meterStop>
                            <timestamp>2022-05-05T04:37:15Z</timestamp>
                            <transactionId>16696</transactionId>
                            <idTag>2D0E360A</idTag>
                        </stopTransactionRequest>
                    </soap:Body>
                </soap:Envelope>
            """.trimIndent()

        expectThat(messageSoap.inline()).isEqualTo(expectedEnvelope.inline())
        expectThat(parseToEnvelope(messageSoap)) {
            get { body.stopTransactionRequest }.isNotNull().and {
                get { transactionId }.isEqualTo(16696)
                get { idTag }.isEqualTo("2D0E360A")
                get { timestamp }.isEqualTo(Instant.parse("2022-05-05T04:37:15Z"))
                get { meterStop }.isEqualTo(19224)
            }
        }
    }

    @Test
    fun `should map StopTransactionResp to soap`() {
        val response = StopTransactionResp(
            idTagInfo = IdTagInfo(
                status = AuthorizationStatus.Accepted,
                expiryDate = Instant.parse("2022-05-16T15:42:05.128Z")
            )
        )

        val messageSoap = Ocpp16SoapParser().mapResponseToSoap(
            ResponseSoapMessage(
                messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                action = "StopTransaction",
                payload = response
            )
        )

        expectThat(messageSoap.inline()) {
            get { this }.contains("<Action xmlns=\"http://www.w3.org/2005/08/addressing\">/StopTransactionResponse</Action>")
            get { this }.contains("<MessageID xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</MessageID>")
            get { this }.contains("<RelatesTo xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</RelatesTo>")
        }
        expectThat(parseToEnvelope(messageSoap)) {
            get { body.stopTransactionResponse }.isNotNull().and {
                get { idTagInfo }.isNotNull().and {
                    get { status }.isEqualTo(AuthorizationStatus.Accepted)
                    get { expiryDate }.isEqualTo(Instant.parse("2022-05-16T15:42:05.128Z"))
                }
            }
        }
    }

    companion object {
        private val reader: ObjectReader = Ocpp16Mapper
            .readerFor(object : TypeReference<SoapEnvelope<Ocpp16SoapBody>>() {})

        private fun parseToEnvelope(messageStr: String): SoapEnvelope<Ocpp16SoapBody> =
            reader.readValue(messageStr)
    }
}

private fun String.inline() = this
    .split('\n')
    .joinToString("") { it.trim() }
