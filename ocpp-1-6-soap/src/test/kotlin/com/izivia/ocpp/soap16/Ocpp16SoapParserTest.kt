package com.izivia.ocpp.soap16

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectReader
import com.izivia.ocpp.core16.model.authorize.AuthorizeReq
import com.izivia.ocpp.core16.model.authorize.AuthorizeResp
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core16.model.bootnotification.enumeration.RegistrationStatus
import com.izivia.ocpp.core16.model.common.IdTagInfo
import com.izivia.ocpp.core16.model.common.enumeration.*
import com.izivia.ocpp.core16.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core16.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core16.model.datatransfer.enumeration.DataTransferStatus
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
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
import com.izivia.ocpp.soap.OcppSoapMapper
import com.izivia.ocpp.soap.SoapBody
import com.izivia.ocpp.soap.SoapEnvelope
import com.izivia.ocpp.soap.SoapMessage
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

        expectThat(Ocpp16SoapParser().parseFromRequest<AuthorizeReq>(message).payload).and {
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

        expectThat(Ocpp16SoapParser().parseFromRequest<BootNotificationReq>(message).payload).and {
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
            .isA<BootNotificationReq>()
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

        expectThat(Ocpp16SoapParser().parseFromRequest<DataTransferReq>(message).payload).and {
            get { vendorId }.isEqualTo("Schneider Electric")
            get { messageId }.isEqualTo("Detection loop")
            get { data }.isEqualTo("{\"connectorId\":10,\"name\":\"Vehicle\",\"state\":\"1\",\"timestamp\":\"2022-05-17T15:42:03Z:\"}")
        }
            .isA<DataTransferReq>()
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

        expectThat(Ocpp16SoapParser().parseFromRequest<FirmwareStatusNotificationReq>(message).payload).and {
            get { status }.isEqualTo(FirmwareStatus.Installed)
        }
            .isA<FirmwareStatusNotificationReq>()
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

        expectThat(Ocpp16SoapParser().parseFromRequest<HeartbeatReq>(message).payload)
            .isA<HeartbeatReq>()
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

        expectThat(Ocpp16SoapParser().parseFromRequest<MeterValuesReq>(message).payload).and {
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
            .isA<MeterValuesReq>()
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

        expectThat(Ocpp16SoapParser().parseFromRequest<StartTransactionReq>(message).payload).and {
            get { connectorId }.isEqualTo(1)
            get { idTag }.isEqualTo("046924C2D86485")
            get { meterStart }.isEqualTo(18804500)
            get { timestamp }.isEqualTo(Instant.parse("2022-05-17T15:41:58.351Z"))
            get { reservationId }.isNull()
        }
            .isA<StartTransactionReq>()
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

        expectThat(Ocpp16SoapParser().parseFromRequest<StatusNotificationReq>(message).payload).and {
            get { connectorId }.isEqualTo(1)
            get { errorCode }.isEqualTo(ChargePointErrorCode.NoError)
            get { status }.isEqualTo(ChargePointStatus.Available)
            get { info }.isEqualTo("No error.")
            get { timestamp }.isEqualTo(Instant.parse("2022-05-17T15:41:59.486Z"))
            get { vendorErrorCode }.isEqualTo("0x0")
            get { vendorId }.isNull()
        }
            .isA<StatusNotificationReq>()
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

        expectThat(Ocpp16SoapParser().parseFromRequest<StopTransactionReq>(message).payload).and {
            get { meterStop }.isEqualTo(19224)
            get { timestamp }.isEqualTo(Instant.parse("2022-05-05T04:37:15Z"))
            get { transactionId }.isEqualTo(16696)
            get { idTag }.isEqualTo("2D0E360A")
            get { reason }.isNull()
            get { transactionData }.isNull()
        }
            .isA<StopTransactionReq>()
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

        expectThrows<ClassCastException> {
            Ocpp16SoapParser().parseFromRequest<AuthorizeReq>(message).payload as AuthorizeReq
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

        expectThrows<ClassCastException> {
            Ocpp16SoapParser().parseFromRequest<BootNotificationReq>(message).payload as BootNotificationReq
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

        expectThrows<ClassCastException> {
            Ocpp16SoapParser().parseFromRequest<DataTransferReq>(message).payload as DataTransferReq
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

        expectThrows<ClassCastException> {
            Ocpp16SoapParser().parseFromRequest<DiagnosticsStatusNotificationReq>(message).payload as DiagnosticsStatusNotificationReq
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

        expectThrows<ClassCastException> {
            Ocpp16SoapParser().parseFromRequest<FirmwareStatusNotificationReq>(message).payload as FirmwareStatusNotificationReq
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

        expectThrows<ClassCastException> {
            Ocpp16SoapParser().parseFromRequest<HeartbeatReq>(message).payload as HeartbeatReq
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

        expectThrows<ClassCastException> {
            Ocpp16SoapParser().parseFromRequest<MeterValuesReq>(message).payload as MeterValuesReq
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

        expectThrows<ClassCastException> {
            Ocpp16SoapParser().parseFromRequest<StartTransactionReq>(message).payload as StartTransactionReq
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

        expectThrows<ClassCastException> {
            Ocpp16SoapParser().parseFromRequest<StatusNotificationReq>(message).payload as StatusNotificationReq
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

        expectThrows<ClassCastException> {
            Ocpp16SoapParser().parseFromRequest<StopTransactionReq>(message).payload as StopTransactionReq
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

        val messageSoap = Ocpp16SoapParser().mapToResponse(
            SoapMessage(
                ocppId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                chargingStationId = "00:80:F4:44:13:AA",
                action = "Authorize",
                payload = response
            )
        )

        expectThat(messageSoap.replace("\n", "")) {
            get { this }.contains("<Action xmlns=\"http://www.w3.org/2005/08/addressing\">/AuthorizeResponse</Action>")
            get { this }.contains("<RelatesTo xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</RelatesTo>")
        }
        expectThat(parse(messageSoap)) {
            get { body.authorizeResponse }.isNotNull().and {
                get { idTagInfo }.and {
                    get { status }.isEqualTo(AuthorizationStatus.Accepted)
                    get { expiryDate }.isEqualTo(Instant.parse("2022-05-16T15:42:05.128Z"))
                }
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

        val messageSoap = Ocpp16SoapParser().mapToResponse(
            SoapMessage(
                ocppId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                chargingStationId = "00:80:F4:44:13:AA",
                action = "BootNotification",
                payload = response
            )
        )

        expectThat(messageSoap.replace("\n", "")) {
            get { this }.contains("<Action xmlns=\"http://www.w3.org/2005/08/addressing\">/BootNotificationResponse</Action>")
            get { this }.contains("<RelatesTo xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</RelatesTo>")
        }
        expectThat(parse(messageSoap)) {
            get { body.bootNotificationResponse }.isNotNull().and {
                get { status }.isEqualTo(RegistrationStatus.Rejected)
                get { currentTime }.isEqualTo(Instant.parse("2022-05-17T15:43:08.025Z"))
                get { interval }.isEqualTo(1800)
            }
        }
    }

    @Test
    fun `should map DataTransferResp to soap`() {
        val response = DataTransferResp(
            status = DataTransferStatus.Accepted
        )

        val messageSoap = Ocpp16SoapParser().mapToResponse(
            SoapMessage(
                ocppId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                chargingStationId = "00:80:F4:44:13:AA",
                action = "DataTransfer",
                payload = response
            )
        )

        expectThat(messageSoap.replace("\n", "")) {
            get { this }.contains("<Action xmlns=\"http://www.w3.org/2005/08/addressing\">/DataTransferResponse</Action>")
            get { this }.contains("<RelatesTo xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</RelatesTo>")
        }
        expectThat(parse(messageSoap)) {
            get { body.dataTransferResponse }.isNotNull().and {
                get { status }.isEqualTo(DataTransferStatus.Accepted)
            }
        }
    }

    @Disabled
    @Test
    fun `should map DiagnosticsStatusNotificationResp to soap`() {
        // TODO
    }

    @Test
    fun `should map FirmwareStatusNotificationResp to soap`() {
        val response = FirmwareStatusNotificationResp()

        val messageSoap = Ocpp16SoapParser().mapToResponse(
            SoapMessage(
                ocppId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                chargingStationId = "00:80:F4:44:13:AA",
                action = "FirmwareStatusNotification",
                payload = response
            )
        )

        expectThat(messageSoap.replace("\n", "")) {
            get { this }.contains("<Action xmlns=\"http://www.w3.org/2005/08/addressing\">/FirmwareStatusNotificationResponse</Action>")
            get { this }.contains("<RelatesTo xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</RelatesTo>")
        }
        expectThat(parse(messageSoap)) {
            get { body.firmwareStatusNotificationResponse }.isNotNull()
        }
    }

    @Test
    fun `should map HeartbeatResp to soap`() {
        val response = HeartbeatResp(
            currentTime = Instant.parse("2022-05-17T15:42:00.503Z")
        )

        val messageSoap = Ocpp16SoapParser().mapToResponse(
            SoapMessage(
                ocppId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                chargingStationId = "00:80:F4:44:13:AA",
                action = "Heartbeat",
                payload = response
            )
        )

        expectThat(messageSoap.replace("\n", "")) {
            get { this }.contains("<Action xmlns=\"http://www.w3.org/2005/08/addressing\">/HeartbeatResponse</Action>")
            get { this }.contains("<RelatesTo xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</RelatesTo>")
        }
        expectThat(parse(messageSoap)) {
            get { body.heartbeatResponse }.isNotNull().and {
                get { currentTime }.isEqualTo(Instant.parse("2022-05-17T15:42:00.503Z"))
            }
        }
    }

    @Test
    fun `should map MeterValuesResp to soap`() {
        val response = MeterValuesResp()

        val messageSoap = Ocpp16SoapParser().mapToResponse(
            SoapMessage(
                ocppId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                chargingStationId = "00:80:F4:44:13:AA",
                action = "MeterValues",
                payload = response
            )
        )

        expectThat(messageSoap.replace("\n", "")) {
            get { this }.contains("<Action xmlns=\"http://www.w3.org/2005/08/addressing\">/MeterValuesResponse</Action>")
            get { this }.contains("<RelatesTo xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</RelatesTo>")
        }
        expectThat(parse(messageSoap)) {
            get { body.meterValuesResponse }.isNotNull()
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

        val messageSoap = Ocpp16SoapParser().mapToResponse(
            SoapMessage(
                ocppId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                chargingStationId = "00:80:F4:44:13:AA",
                action = "StartTransaction",
                payload = response
            )
        )

        expectThat(messageSoap.replace("\n", "")) {
            get { this }.contains("<Action xmlns=\"http://www.w3.org/2005/08/addressing\">/StartTransactionResponse</Action>")
            get { this }.contains("<RelatesTo xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</RelatesTo>")
        }
        expectThat(parse(messageSoap)) {
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
    fun `should map StatusNotificationResp to soap`() {
        val response = StatusNotificationResp()

        val messageSoap = Ocpp16SoapParser().mapToResponse(
            SoapMessage(
                ocppId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                chargingStationId = "00:80:F4:44:13:AA",
                action = "StatusNotification",
                payload = response
            )
        )

        expectThat(messageSoap.replace("\n", "")) {
            get { this }.contains("<Action xmlns=\"http://www.w3.org/2005/08/addressing\">/StatusNotificationResponse</Action>")
            get { this }.contains("<RelatesTo xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</RelatesTo>")
        }
        expectThat(parse(messageSoap)) {
            get { body.statusNotificationResponse }.isNotNull()
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

        val messageSoap = Ocpp16SoapParser().mapToResponse(
            SoapMessage(
                ocppId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                chargingStationId = "00:80:F4:44:13:AA",
                action = "StopTransaction",
                payload = response
            )
        )

        expectThat(messageSoap.replace("\n", "")) {
            get { this }.contains("<Action xmlns=\"http://www.w3.org/2005/08/addressing\">/StopTransactionResponse</Action>")
            get { this }.contains("<RelatesTo xmlns=\"http://www.w3.org/2005/08/addressing\">urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</RelatesTo>")
        }
        expectThat(parse(messageSoap)) {
            get { body.stopTransactionResponse }.isNotNull().and {
                get { idTagInfo }.isNotNull().and {
                    get { status }.isEqualTo(AuthorizationStatus.Accepted)
                    get { expiryDate }.isEqualTo(Instant.parse("2022-05-16T15:42:05.128Z"))
                }
            }
        }
    }

    companion object {
        private val mapper: ObjectReader = OcppSoapMapper
            .addMixIn(Measurand::class.java, EnumMixin::class.java)
            .addMixIn(ReadingContext::class.java, EnumMixin::class.java)
            .addMixIn(Phase::class.java, EnumMixin::class.java)
            .readerFor(object : TypeReference<SoapEnvelope<Ocpp16SoapBodyResp>>() {})

        private fun parse(messageStr: String): SoapEnvelope<Ocpp16SoapBodyResp> =
            mapper.readValue(messageStr)
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
private data class Ocpp16SoapBodyResp(
    val authorizeResponse: AuthorizeResp?,
    val bootNotificationResponse: BootNotificationResp?,
    val dataTransferResponse: DataTransferResp?,
    val diagnosticsStatusNotificationResponse: DiagnosticsStatusNotificationResp?,
    val firmwareStatusNotificationResponse: FirmwareStatusNotificationResp?,
    val heartbeatResponse: HeartbeatResp?,
    val meterValuesResponse: MeterValuesResp?,
    val startTransactionResponse: StartTransactionResp?,
    val statusNotificationResponse: StatusNotificationResp?,
    val stopTransactionResponse: StopTransactionResp?,
) : SoapBody
