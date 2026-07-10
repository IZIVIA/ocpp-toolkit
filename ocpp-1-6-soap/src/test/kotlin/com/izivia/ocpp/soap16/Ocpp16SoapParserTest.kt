package com.izivia.ocpp.soap16

import com.izivia.ocpp.core16.Ocpp16ForcedFieldType
import com.izivia.ocpp.core16.Ocpp16IgnoredNullRestriction
import com.izivia.ocpp.core16.model.authorize.AuthorizeReq
import com.izivia.ocpp.core16.model.authorize.AuthorizeResp
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core16.model.bootnotification.enumeration.RegistrationStatus
import com.izivia.ocpp.core16.model.common.*
import com.izivia.ocpp.core16.model.common.enumeration.*
import com.izivia.ocpp.core16.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core16.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core16.model.datatransfer.enumeration.DataTransferStatus
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.enumeration.DiagnosticsStatus
import com.izivia.ocpp.core16.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core16.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core16.model.firmwarestatusnotification.enumeration.FirmwareStatus
import com.izivia.ocpp.core16.model.getcompositeschedule.GetCompositeScheduleReq
import com.izivia.ocpp.core16.model.getcompositeschedule.GetCompositeScheduleResp
import com.izivia.ocpp.core16.model.getcompositeschedule.enumeration.GetCompositeScheduleStatus
import com.izivia.ocpp.core16.model.getconfiguration.GetConfigurationResp
import com.izivia.ocpp.core16.model.getconfiguration.KeyValue
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core16.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core16.model.metervalues.MeterValuesResp
import com.izivia.ocpp.core16.model.remotestart.ChargingSchedulePeriod
import com.izivia.ocpp.core16.model.remotestart.RemoteStartTransactionReq
import com.izivia.ocpp.core16.model.remotestart.enumeration.ChargingProfileKindType
import com.izivia.ocpp.core16.model.remotestop.RemoteStopTransactionReq
import com.izivia.ocpp.core16.model.setchargingprofile.SetChargingProfileReq
import com.izivia.ocpp.core16.model.setchargingprofile.SetChargingProfileResp
import com.izivia.ocpp.core16.model.setchargingprofile.enumeration.ChargingProfileStatus
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionResp
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core16.model.statusnotification.enumeration.ChargePointErrorCode
import com.izivia.ocpp.core16.model.statusnotification.enumeration.ChargePointStatus
import com.izivia.ocpp.core16.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core16.model.stoptransaction.StopTransactionResp
import com.izivia.ocpp.core16.model.triggermessage.TriggerMessageReq
import com.izivia.ocpp.core16.model.triggermessage.TriggerMessageResp
import com.izivia.ocpp.core16.model.triggermessage.enumeration.MessageTrigger
import com.izivia.ocpp.core16.model.triggermessage.enumeration.TriggerMessageStatus
import com.izivia.ocpp.soap.*
import com.izivia.ocpp.utils.ActionTypeEnum
import com.izivia.ocpp.utils.ErrorDetailCode
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.utils.TypeConvertEnum
import com.izivia.ocpp.utils.fault.Fault
import kotlin.time.Instant
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.*
import java.util.*

class Ocpp16SoapParserTest {

    companion object {
        private val ocpp16SoapParser = Ocpp16SoapParser()
    }

    @Test
    fun `should parse message to AuthorizeReq`() {
        val message =
            """
                <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://www.w3.org/2003/05/soap-envelope"
                xmlns:cs="urn://Ocpp/Cs/2015/10/" xmlns:wsa5="http://www.w3.org/2005/08/addressing">
                    <SOAP-ENV:Header>
                        <cs:chargeBoxIdentity>CS1</cs:chargeBoxIdentity>
                        <wsa5:MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</wsa5:MessageID>
                        <wsa5:From>
                            <wsa5:Address>http://:8082/</wsa5:Address>
                        </wsa5:From>
                        <wsa5:ReplyTo>
                            <wsa5:Address>http://www.w3.org/2005/08/addressing/anonymous</wsa5:Address>
                        </wsa5:ReplyTo>
                        <wsa5:To SOAP-ENV:mustUnderstand="true">http://example.fr:80/ocpp/v15s/</wsa5:To>
                        <wsa5:Action SOAP-ENV:mustUnderstand="true">/Authorize</wsa5:Action>
                    </SOAP-ENV:Header>
                    <SOAP-ENV:Body>
                        <cs:authorizeRequest>
                            <cs:idTag>AAAAAAAA</cs:idTag>
                        </cs:authorizeRequest>
                    </SOAP-ENV:Body>
                </SOAP-ENV:Envelope>
            """

        expectThat(
            ocpp16SoapParser
                .parseRequestFromSoap<AuthorizeReq>(message)
        ).and {
            get { messageId }.isEqualTo("urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49")
            get { chargingStationId }.isEqualTo("CS1")
            get { action }.isEqualTo("Authorize")
            get { from }.isEqualTo("http://:8082/")
            get { to }.isEqualTo("http://example.fr:80/ocpp/v15s/")
            get { payload }.and {
                isA<AuthorizeReq>()
                get { idTag }.isEqualTo("AAAAAAAA")
            }
        }
    }

    @Test
    fun `should parse message to BootNotificationReq`() {
        val message =
            """
                <ns0:Envelope
                xmlns:ns0="http://www.w3.org/2003/05/soap-envelope"
                xmlns:ns1="http://www.w3.org/2005/08/addressing"
                xmlns:ns2="urn://Ocpp/Cs/2015/10/">
                    <ns0:Header>
                        <ns1:To>http://example.fr/ocpp/v16s/</ns1:To>
                        <ns1:From>
                            <ns1:Address>http://DEADBEAF:8084/chargePointService/ocpp16soap/</ns1:Address>
                        </ns1:From>
                        <ns2:chargeBoxIdentity>XXXXXXXX</ns2:chargeBoxIdentity>
                        <ns1:MessageID>7230</ns1:MessageID>
                        <ns1:Action>/BootNotification</ns1:Action>
                    </ns0:Header>
                    <ns0:Body>
                        <ns2:bootNotificationRequest>
                            <ns2:chargePointVendor>YYYYYYYY</ns2:chargePointVendor>
                            <ns2:chargePointModel>ZZZZZZZZ</ns2:chargePointModel>
                            <ns2:chargePointSerialNumber>5aa469fd41344fe5a575368cd</ns2:chargePointSerialNumber>
                            <ns2:firmwareVersion>1.5.1.d723fd5</ns2:firmwareVersion>
                        </ns2:bootNotificationRequest>
                    </ns0:Body>
                </ns0:Envelope>
            """

        expectThat(
            ocpp16SoapParser.parseRequestFromSoap<BootNotificationReq>(message).payload
        ).and {
            get { chargePointModel }.isEqualTo("ZZZZZZZZ")
            get { chargePointVendor }.isEqualTo("YYYYYYYY")
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
    fun `should not parse BOOTNOTIFICATION message with missing chargePointModel to BootNotificationReq`() {
        val message =
            """<?xml version='1.0' encoding='UTF-8'?>
<S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
    <S:Header>
        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2015/10/">ocppId1</chargeBoxIdentity>
        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
            <wsa:Address>http://XXX.XXX.X.3:12800/ws</wsa:Address>
        </wsa:From>
        <To xmlns="http://www.w3.org/2005/08/addressing">http://sigeif-enovates.vpn.l30.sodetrel.fr/ocpp/v16s</To>
        <Action xmlns="http://www.w3.org/2005/08/addressing">/BootNotification</Action>
        <ReplyTo xmlns="http://www.w3.org/2005/08/addressing">
            <Address>http://www.w3.org/2005/08/addressing/anonymous</Address>
        </ReplyTo>
        <FaultTo xmlns="http://www.w3.org/2005/08/addressing">
            <Address>http://www.w3.org/2005/08/addressing/anonymous</Address>
        </FaultTo>
        <MessageID xmlns="http://www.w3.org/2005/08/addressing">uuid:9f70d5fe-2e47-4c9e-9faa-371446c19e41</MessageID>
    </S:Header>
    <S:Body>
        <bootNotificationRequest xmlns="urn://Ocpp/Cs/2015/10/">
            <chargePointVendor>eNovates</chargePointVendor>
            <chargePointSerialNumber>92075_03_03</chargePointSerialNumber>
            <chargeBoxSerialNumber>92075_03_03</chargeBoxSerialNumber>
            <firmwareVersion>1.8.6.3.2@110.8.3 3_256d3NOEVC</firmwareVersion>
            <meterType>1f_Inepro</meterType>
            <meterSerialNumber>537134162</meterSerialNumber>
        </bootNotificationRequest>
    </S:Body>
</S:Envelope>
            """.trimSoap()

        expectThat(
            ocpp16SoapParser.parseAnyRequestFromSoap(message).payload
        ).isA<Fault>()
    }

    @Test
    fun `should parse BOOTNOTIFICATION message with missing chargePointModel to BootNotificationReq`() {
        val parser = Ocpp16SoapParser(
            listOf(
                Ocpp16IgnoredNullRestriction(
                    Actions.BOOTNOTIFICATION,
                    actionType = ActionTypeEnum.REQUEST,
                    fieldPath = "chargePointModel",
                    defaultNullValue = "ValueInjected"
                )
            )
        )
        val message =
            """<?xml version='1.0' encoding='UTF-8'?>
<S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
    <S:Header>
        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2015/10/">ocppId1</chargeBoxIdentity>
        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
            <wsa:Address>http://XXX.XXX.X.3:12800/ws</wsa:Address>
        </wsa:From>
        <To xmlns="http://www.w3.org/2005/08/addressing">http://sigeif-enovates.vpn.l30.sodetrel.fr/ocpp/v16s</To>
        <Action xmlns="http://www.w3.org/2005/08/addressing">/BootNotification</Action>
        <ReplyTo xmlns="http://www.w3.org/2005/08/addressing">
            <Address>http://www.w3.org/2005/08/addressing/anonymous</Address>
        </ReplyTo>
        <FaultTo xmlns="http://www.w3.org/2005/08/addressing">
            <Address>http://www.w3.org/2005/08/addressing/anonymous</Address>
        </FaultTo>
        <MessageID xmlns="http://www.w3.org/2005/08/addressing">uuid:9f70d5fe-2e47-4c9e-9faa-371446c19e41</MessageID>
    </S:Header>
    <S:Body>
        <bootNotificationRequest xmlns="urn://Ocpp/Cs/2015/10/">
            <chargePointVendor>eNovates</chargePointVendor>
            <chargePointSerialNumber>92075_03_03</chargePointSerialNumber>
            <chargeBoxSerialNumber>92075_03_03</chargeBoxSerialNumber>
            <firmwareVersion>1.8.6.3.2@110.8.3 3_256d3NOEVC</firmwareVersion>
            <meterType>1f_Inepro</meterType>
            <meterSerialNumber>537134162</meterSerialNumber>
        </bootNotificationRequest>
    </S:Body>
</S:Envelope>
            """.trimSoap()

        expectThat(
            parser.parseAnyRequestFromSoap(message)
        ).and {
            get { payload }.isA<BootNotificationReq>()
                .and {
                    get { chargePointModel }.isEqualTo("ValueInjected")
                    get { chargePointVendor }.isEqualTo("eNovates")
                    get { chargePointSerialNumber }.isEqualTo("92075_03_03")
                    get { chargeBoxSerialNumber }.isEqualTo("92075_03_03")
                    get { firmwareVersion }.isEqualTo("1.8.6.3.2@110.8.3 3_256d3NOEVC")
                    get { iccid }.isNull()
                    get { imsi }.isNull()
                    get { meterSerialNumber }.isEqualTo("537134162")
                    get { meterType }.isEqualTo("1f_Inepro")
                }
            get { warnings }.isNotNull().hasSize(1)
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
                        <cs:chargeBoxIdentity>XXXXXXXX</cs:chargeBoxIdentity>
                        <wsa5:MessageID>urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</wsa5:MessageID>
                        <wsa5:From>
                            <wsa5:Address>http://:8082/</wsa5:Address>
                        </wsa5:From>
                        <wsa5:ReplyTo>
                            <wsa5:Address>http://www.w3.org/2005/08/addressing/anonymous</wsa5:Address>
                        </wsa5:ReplyTo>
                        <wsa5:To SOAP-ENV:mustUnderstand="true">http://example.fr:80/ocpp/v15s/</wsa5:To>
                        <wsa5:Action SOAP-ENV:mustUnderstand="true">/DataTransfer</wsa5:Action>
                    </SOAP-ENV:Header>
                    <SOAP-ENV:Body>
                        <cs:dataTransferRequest>
                            <cs:vendorId>XXXXXXXX</cs:vendorId>
                            <cs:messageId>Detection loop</cs:messageId>
                            <cs:data>{"connectorId":10,"name":"Vehicle","state":"1","timestamp":"2022-05-17T15:42:03Z:"}</cs:data>
                        </cs:dataTransferRequest>
                    </SOAP-ENV:Body>
                </SOAP-ENV:Envelope>
            """.trimSoap()

        expectThat(
            ocpp16SoapParser
                .parseRequestFromSoap<DataTransferReq>(message).payload
        ).and {
            get { vendorId }.isEqualTo("XXXXXXXX")
            get { messageId }.isEqualTo("Detection loop")
            get { data }.isEqualTo(
                "{\"connectorId\":10,\"name\":\"Vehicle\",\"state\":\"1\",\"timestamp\":\"2022-05-17T15:42:03Z:\"}"
            )
        }
    }

    @Test
    fun `should parse message to DiagnosticsStatusNotificationReq`() {
        val message = """
            <ns0:Envelope xmlns:ns0="http://www.w3.org/2003/05/soap-envelope"
             xmlns:ns1="http://www.w3.org/2005/08/addressing"
             xmlns:ns2="urn://Ocpp/Cs/2015/10/">
             <ns0:Header>
                <ns1:From><ns1:Address>http://192.168.153.113:8080</ns1:Address></ns1:From>
                <ns1:To>http://192.168.153.113:8080</ns1:To>
                <ns1:ReplyTo><ns1:Address>http://www.w3.org/2005/08/addressing/anonymous</ns1:Address></ns1:ReplyTo>
                <ns2:chargeBoxIdentity>TEST</ns2:chargeBoxIdentity>
                <ns1:MessageID>f8465c53A250aA4c53A9feeAd79510d972cf</ns1:MessageID>
                <ns1:Action>/DiagnosticsStatusNotification</ns1:Action>
             </ns0:Header>
             <ns0:Body>
                <ns2:diagnosticsStatusNotificationRequest>
                    <ns2:status>Uploaded</ns2:status>
                    </ns2:diagnosticsStatusNotificationRequest>
             </ns0:Body></ns0:Envelope>
        """.trimIndent()
        expectThat(ocpp16SoapParser.parseRequestFromSoap<DiagnosticsStatusNotificationReq>(message).payload).and {
            get { status }.isEqualTo(DiagnosticsStatus.Uploaded)
        }
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
                            <wsa5:Address>http://XXXXXXXX:8085</wsa5:Address>
                        </wsa5:From>
                        <wsa5:MessageID>urn:uuid:8fe7ef88-0f2e-47f3-911d-dbd36b32f366</wsa5:MessageID>
                        <wsa5:ReplyTo soap:mustUnderstand="true">
                            <wsa5:Address>http://www.w3.org/2005/08/addressing/anonymous</wsa5:Address>
                        </wsa5:ReplyTo>
                        <wsa5:To soap:mustUnderstand="true">http://example.fr/ocpp/v16s/</wsa5:To>
                        <wsa5:Action soap:mustUnderstand="true">/FirmwareStatusNotification</wsa5:Action>
                    </soap:Header>
                    <soap:Body>
                        <cs:firmwareStatusNotificationRequest>
                            <cs:status>Installed</cs:status>
                        </cs:firmwareStatusNotificationRequest>
                    </soap:Body>
                </soap:Envelope>
            """.trimSoap()

        expectThat(
            ocpp16SoapParser
                .parseRequestFromSoap<FirmwareStatusNotificationReq>(message).payload
        ).and {
            get { status }.isEqualTo(FirmwareStatus.Installed)
        }
    }

    @Test
    fun `should parse message to HeartbeatReq`() {
        val message =
            """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2015/10/">CS1</chargeBoxIdentity>
                        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                            <wsa:Address>http://XXXXXXXX:12800/ws</wsa:Address>
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
            """.trimSoap()

        expectThat(
            ocpp16SoapParser
                .parseRequestFromSoap<HeartbeatReq>(message).payload
        )
    }

    @Test
    fun `should parse message to MeterValuesReq`() {
        val message =
            """
                <v:Envelope xmlns:v="http://www.w3.org/2003/05/soap-envelope" xmlns:a="http://www.w3.org/2005/08/addressing"
                            xmlns:ocpp="urn://Ocpp/Cs/2015/10/">
                    <v:Header>
                        <ocpp:chargeBoxIdentity>XXXXXXXX</ocpp:chargeBoxIdentity>
                        <a:Action v:mustUnderstand="true">/MeterValues</a:Action>
                        <a:MessageID>urn:uuid:f7523b7f-ccf3-425b-8177-8ab60db19d45</a:MessageID>
                        <a:From>
                            <a:Address>http://XXXXXXXX/ocpp</a:Address>
                        </a:From>
                        <a:To v:mustUnderstand="true">http://example.fr/ocpp/v16s</a:To>
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
                                    <ocpp:phase>L1-N</ocpp:phase>
                                </ocpp:sampledValue>
                            </ocpp:meterValue>
                        </ocpp:meterValuesRequest>
                    </v:Body>
                </v:Envelope>
            """.trimSoap()

        expectThat(
            ocpp16SoapParser
                .parseRequestFromSoap<MeterValuesReq>(message).payload
        ).and {
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
                    get { phase }.isEqualTo(Phase.L1N)
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
                        <ocpp:chargeBoxIdentity>XXXXXXXX</ocpp:chargeBoxIdentity>
                        <a:Action v:mustUnderstand="true">/StartTransaction</a:Action>
                        <a:MessageID>urn:uuid:0aa92653-5cd8-465f-89d0-eca833aec4c2</a:MessageID>
                        <a:From>
                            <a:Address>http://XXXXXXXX/ocpp</a:Address>
                        </a:From>
                        <a:To v:mustUnderstand="true">http://example.fr/ocpp/v16s/</a:To>
                        <a:ReplyTo v:mustUnderstand="true">
                            <a:Address>http://www.w3.org/2005/08/addressing/anonymous</a:Address>
                        </a:ReplyTo>
                    </v:Header>
                    <v:Body>
                        <ocpp:startTransactionRequest>
                            <ocpp:connectorId>1</ocpp:connectorId>
                            <ocpp:idTag>AAAAAAAA</ocpp:idTag>
                            <ocpp:meterStart>18804500</ocpp:meterStart>
                            <ocpp:timestamp>2022-05-17T15:41:58.351Z</ocpp:timestamp>
                        </ocpp:startTransactionRequest>
                    </v:Body>
                </v:Envelope>
            """.trimSoap()

        expectThat(
            ocpp16SoapParser
                .parseRequestFromSoap<StartTransactionReq>(message).payload
        ).and {
            get { connectorId }.isEqualTo(1)
            get { idTag }.isEqualTo("AAAAAAAA")
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
                        <cs:chargeBoxIdentity soap:mustUnderstand="true">XXXXXXXX</cs:chargeBoxIdentity>
                        <wsa5:From>
                            <wsa5:Address>http://x.x.x.x:8085</wsa5:Address>
                        </wsa5:From>
                        <wsa5:MessageID>urn:uuid:70c76024-1281-471f-8578-ad51a2a88134</wsa5:MessageID>
                        <wsa5:ReplyTo soap:mustUnderstand="true">
                            <wsa5:Address>http://www.w3.org/2005/08/addressing/anonymous</wsa5:Address>
                        </wsa5:ReplyTo>
                        <wsa5:To soap:mustUnderstand="true">http://example.fr/ocpp/v16s</wsa5:To>
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
            """.trimSoap()

        expectThat(
            ocpp16SoapParser
                .parseRequestFromSoap<StatusNotificationReq>(message).payload
        ).and {
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
                        <To xmlns="http://www.w3.org/2005/08/addressing">http://example.fr/ocpp/v15s/</To>
                        <ReplyTo xmlns="http://www.w3.org/2005/08/addressing">
                            <Address>http://www.w3.org/2005/08/addressing/anonymous</Address>
                        </ReplyTo>
                        <From xmlns="http://www.w3.org/2005/08/addressing">
                            <Address>http://192.168.0.102:8080</Address>
                        </From>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2012/06/">CS1</chargeBoxIdentity>
                    </SOAP-ENV:Header>
                    <SOAP-ENV:Body>
                        <stopTransactionRequest xmlns="urn://Ocpp/Cs/2012/06/">
                            <transactionId>16696</transactionId>
                            <idTag>AAAAAAAA</idTag>
                            <timestamp>2022-05-05T04:37:15Z</timestamp>
                            <meterStop>19224</meterStop>
                        </stopTransactionRequest>
                    </SOAP-ENV:Body>
                </SOAP-ENV:Envelope>
            """.trimSoap()

        expectThat(
            ocpp16SoapParser
                .parseRequestFromSoap<StopTransactionReq>(message).payload
        ).and {
            get { meterStop }.isEqualTo(19224)
            get { timestamp }.isEqualTo(Instant.parse("2022-05-05T04:37:15Z"))
            get { transactionId }.isEqualTo(16696)
            get { idTag }.isEqualTo("AAAAAAAA")
            get { reason }.isNull()
            get { transactionData }.isNull()
        }
    }

    @Test
    fun `should parse message to TriggerMessageReq`() {
        val message =
            """
                <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://www.w3.org/2003/05/soap-envelope"
                xmlns:cs="urn://Ocpp/Cp/2015/10/" xmlns:wsa5="http://www.w3.org/2005/08/addressing">
                    <SOAP-ENV:Header>
                        <cs:chargeBoxIdentity>CS1</cs:chargeBoxIdentity>
                        <wsa5:MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</wsa5:MessageID>
                        <wsa5:From>
                            <wsa5:Address>http://:8082/</wsa5:Address>
                        </wsa5:From>
                        <wsa5:ReplyTo>
                            <wsa5:Address>http://www.w3.org/2005/08/addressing/anonymous</wsa5:Address>
                        </wsa5:ReplyTo>
                        <wsa5:To SOAP-ENV:mustUnderstand="true">http://example.fr:80/ocpp/v15s/</wsa5:To>
                        <wsa5:Action SOAP-ENV:mustUnderstand="true">/TriggerMessage</wsa5:Action>
                    </SOAP-ENV:Header>
                    <SOAP-ENV:Body>
                        <cs:triggerMessageRequest>
                            <cs:connectorId>1</cs:connectorId>
                            <cs:requestedMessage>BootNotification</cs:requestedMessage>
                        </cs:triggerMessageRequest>
                    </SOAP-ENV:Body>
                </SOAP-ENV:Envelope>
            """

        expectThat(
            ocpp16SoapParser
                .parseRequestFromSoap<TriggerMessageReq>(message)
        ).and {
            get { messageId }.isEqualTo("urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49")
            get { chargingStationId }.isEqualTo("CS1")
            get { action }.isEqualTo("TriggerMessage")
            get { from }.isEqualTo("http://:8082/")
            get { to }.isEqualTo("http://example.fr:80/ocpp/v15s/")
            get { payload }.and {
                isA<TriggerMessageReq>()
                get { connectorId }.isEqualTo(1)
                get { requestedMessage }.isEqualTo(MessageTrigger.BootNotification)
            }
        }
    }

    @Test
    fun `should parse TriggerMessageReq to soap message`() {
        val triggerMessageReq = RequestSoapMessage(
            messageId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
            chargingStationId = "CS1",
            action = "TriggerMessage",
            from = "http://:8082/",
            to = "http://example.fr:80/ocpp/v15s/",
            payload = TriggerMessageReq(MessageTrigger.BootNotification, 1)
        )

        expectThat(
            ocpp16SoapParser.mapRequestToSoap(triggerMessageReq)
        ).and {
            get { this }.contains("<a:Action>/TriggerMessage</a:Action>")
            get { this }.contains("xmlns:o=\"urn://Ocpp/Cp/2015/10/\"")
            get { this }.contains(
                """
                <o:triggerMessageRequest>
                    <o:requestedMessage>BootNotification</o:requestedMessage>
                    <o:connectorId>1</o:connectorId>
                </o:triggerMessageRequest>
            """.replace("\\n| ".toRegex(), "")
                    .trimIndent()
            )

        }
    }

    @Test
    fun `should parse soap message to TriggerMessageResp`() {
        val message = """
            <s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope"
                        xmlns:a="http://www.w3.org/2005/08/addressing"
                        xmlns:o="urn://Ocpp/Cp/2015/10/">
                <s:Header>
                    <a:MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:MessageID>
                    <a:Action>/TriggerMessageResponse</a:Action>
                    <a:From><a:Address>http://example.fr:80/ocpp/v15s/</a:Address></a:From>
                    <a:RelatesTo>CS1</a:RelatesTo>
                </s:Header>
                <s:Body>
                    <o:triggerMessageResponse><o:status>Accepted</o:status></o:triggerMessageResponse>
                </s:Body>
            </s:Envelope>
        """.trimIndent()

        expectThat(
            ocpp16SoapParser.parseResponseFromSoap<TriggerMessageResp>(message)
        ).and {
            get { messageId }.isEqualTo("urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49")
            get { relatesTo }.isEqualTo("CS1")
            get { action }.isEqualTo("TriggerMessage")
            get { from }.isEqualTo("http://example.fr:80/ocpp/v15s/")
            get { payload }.and {
                isA<TriggerMessageResp>()
                get { status }.isEqualTo(TriggerMessageStatus.Accepted)
            }
        }
    }

    @Test
    fun `should parse TriggerMessageResp to soap message`() {
        val triggerMessageResp = ResponseSoapMessage(
            action = "TriggerMessage",
            messageId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
            relatesTo = "CS1",
            from = "http://:8082/",
            to = "http://example.fr:80/ocpp/v15s/",
            payload = TriggerMessageResp(TriggerMessageStatus.Accepted)
        )

        println(
            ocpp16SoapParser.mapResponseToSoap(triggerMessageResp)
        )
        expectThat(
            ocpp16SoapParser.mapResponseToSoap(triggerMessageResp)
        ).and {
            get { this }.contains("<a:Action>/TriggerMessageResponse</a:Action>")
            get { this }.contains("xmlns:o=\"urn://Ocpp/Cp/2015/10/\"")
            get { this }.contains(
                """
                <o:triggerMessageResponse>
                    <o:status>Accepted</o:status>
                </o:triggerMessageResponse>
            """.replace("\\n| ".toRegex(), "")
                    .trimIndent()
            )

        }
    }

    @Test
    fun `should not parse message to AuthorizeReq because it is not an Authorize`() {
        val message =
            """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2015/10/">CS1</chargeBoxIdentity>
                        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                            <wsa:Address>http://x.x.x.x:12800/ws</wsa:Address>
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
            """.trimSoap()

        expectThrows<IllegalArgumentException> {
            ocpp16SoapParser
                .parseRequestFromSoap<AuthorizeReq>(message)
        }
    }

    @Test
    fun `should not parse message to BootNotificationReq because it is not a BootNotification`() {
        val message =
            """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2015/10/">CS1</chargeBoxIdentity>
                        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                            <wsa:Address>http://x.x.x.x:12800/ws</wsa:Address>
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
            """.trimSoap()

        expectThrows<IllegalArgumentException> {
            ocpp16SoapParser
                .parseRequestFromSoap<BootNotificationReq>(message)
        }
    }

    @Test
    fun `should not parse message to DataTransferReq because it is not a DataTransfer`() {
        val message =
            """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2015/10/">CS1</chargeBoxIdentity>
                        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                            <wsa:Address>http://x.x.x.x:12800/ws</wsa:Address>
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
            """.trimSoap()

        expectThrows<IllegalArgumentException> {
            ocpp16SoapParser
                .parseRequestFromSoap<DataTransferReq>(message)
        }
    }

    @Test
    fun `should not parse message to DiagnosticsStatusNotificationReq because it is not a DiagnosticsStatusNotification`() {
        val message =
            """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2015/10/">CS1</chargeBoxIdentity>
                        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                            <wsa:Address>http://x.x.x.x:12800/ws</wsa:Address>
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
            """.trimSoap()

        expectThrows<IllegalArgumentException> {
            ocpp16SoapParser
                .parseRequestFromSoap<DiagnosticsStatusNotificationReq>(message)
        }
    }

    @Test
    fun `should not parse message to FirmwareStatusNotificationReq because it is not a FirmwareStatusNotification`() {
        val message =
            """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2015/10/">CS1</chargeBoxIdentity>
                        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                            <wsa:Address>http://x.x.x.x:12800/ws</wsa:Address>
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
            """.trimSoap()

        expectThrows<IllegalArgumentException> {
            ocpp16SoapParser
                .parseRequestFromSoap<FirmwareStatusNotificationReq>(message)
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
                            <wsa5:Address>http://x.x.x.x:8085</wsa5:Address>
                        </wsa5:From>
                        <wsa5:MessageID>urn:uuid:8fe7ef88-0f2e-47f3-911d-dbd36b32f366</wsa5:MessageID>
                        <wsa5:ReplyTo soap:mustUnderstand="true">
                            <wsa5:Address>http://www.w3.org/2005/08/addressing/anonymous</wsa5:Address>
                        </wsa5:ReplyTo>
                        <wsa5:To soap:mustUnderstand="true">http://example.fr/ocpp/v16s/</wsa5:To>
                        <wsa5:Action soap:mustUnderstand="true">/FirmwareStatusNotification</wsa5:Action>
                    </soap:Header>
                    <soap:Body>
                        <cs:firmwareStatusNotificationRequest>
                            <cs:status>Installed</cs:status>
                        </cs:firmwareStatusNotificationRequest>
                    </soap:Body>
                </soap:Envelope>
            """.trimSoap()

        expectThrows<IllegalArgumentException> {
            ocpp16SoapParser
                .parseRequestFromSoap<HeartbeatReq>(message)
        }
    }

    @Test
    fun `should not parse message to MeterValuesReq because it is not a MeterValues`() {
        val message =
            """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2015/10/">CS1</chargeBoxIdentity>
                        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                            <wsa:Address>http://192.168.x.y:12800/ws</wsa:Address>
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
            """.trimSoap()

        expectThrows<IllegalArgumentException> {
            ocpp16SoapParser
                .parseRequestFromSoap<MeterValuesReq>(message)
        }
    }

    @Test
    fun `should not parse message to StartTransactionReq because it is not a StartTransaction`() {
        val message =
            """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2015/10/">CS1</chargeBoxIdentity>
                        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                            <wsa:Address>http://192.168.x.y:12800/ws</wsa:Address>
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
            """.trimSoap()

        expectThrows<IllegalArgumentException> {
            ocpp16SoapParser
                .parseRequestFromSoap<StartTransactionReq>(message)
        }
    }

    @Test
    fun `should not parse message to StatusNotificationReq because it is not a StatusNotification`() {
        val message =
            """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2015/10/">CS1</chargeBoxIdentity>
                        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                            <wsa:Address>http://192.168.x.y:12800/ws</wsa:Address>
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
            """.trimSoap()

        expectThrows<IllegalArgumentException> {
            ocpp16SoapParser
                .parseRequestFromSoap<StatusNotificationReq>(message)
        }
    }

    @Test
    fun `should not parse message to StopTransactionReq because it is not a StopTransaction`() {
        val message =
            """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2015/10/">CS1</chargeBoxIdentity>
                        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                            <wsa:Address>http://192.168.x.y:12800/ws</wsa:Address>
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
            """.trimSoap()

        expectThrows<IllegalArgumentException> {
            ocpp16SoapParser
                .parseRequestFromSoap<StopTransactionReq>(message)
        }
    }

    @Test
    fun `should map AuthorizeReq to soap`() {
        val msgId = UUID.randomUUID().toString()
        val request = AuthorizeReq(
            idTag = "AAAAAAAA"
        )

        val messageSoap = ocpp16SoapParser
            .mapRequestToSoap(
                RequestSoapMessage(
                    messageId = msgId,
                    chargingStationId = "CS1",
                    action = "Authorize",
                    from = "source",
                    to = "destination",
                    payload = request
                )
            )

        val expectedEnvelope =
            """<s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope"
                xmlns:a="http://www.w3.org/2005/08/addressing"
                xmlns:o="urn://Ocpp/Cs/2015/10/"><s:Header>
<a:MessageID>$msgId</a:MessageID>
<a:Action>/Authorize</a:Action><o:chargeBoxIdentity>CS1</o:chargeBoxIdentity>
<a:From><a:Address>source</a:Address></a:From><a:To>destination</a:To></s:Header>
<s:Body><o:authorizeRequest><o:idTag>AAAAAAAA</o:idTag></o:authorizeRequest></s:Body></s:Envelope>""".trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp16SoapParser.parseAnyRequestFromSoap(messageSoap)) {
            get { payload }
                .isA<AuthorizeReq>()
                .and {
                    get { idTag }.isEqualTo("AAAAAAAA")
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

        val messageSoap = ocpp16SoapParser
            .mapResponseToSoap(
                ResponseSoapMessage(
                    messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                    relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                    action = "Authorize",
                    payload = response,
                    from = "source",
                    to = "destination"
                )
            )

        val expectedEnvelope =
            """<s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope"
                xmlns:a="http://www.w3.org/2005/08/addressing"
                xmlns:o="urn://Ocpp/Cs/2015/10/"><s:Header>
<a:MessageID>urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</a:MessageID>
<a:Action>/AuthorizeResponse</a:Action>
<a:From><a:Address>source</a:Address></a:From><a:To>destination</a:To>
<a:RelatesTo>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:RelatesTo>
</s:Header><s:Body><o:authorizeResponse><o:idTagInfo><status>Accepted</status>
<expiryDate>2022-05-16T15:42:05.128Z</expiryDate></o:idTagInfo></o:authorizeResponse></s:Body></s:Envelope>"""
                .trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp16SoapParser.parseAnyResponseFromSoap(messageSoap)) {
            get { payload }.isA<AuthorizeResp>().and {
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
            chargePointModel = "XXXXXXXX",
            chargePointVendor = "YYYYYYYY",
            chargePointSerialNumber = "5aa469fd41344fe5a575368cd",
            firmwareVersion = "1.5.1.d723fd5"
        )

        val messageSoap = ocpp16SoapParser.mapRequestToSoap(
            RequestSoapMessage(
                messageId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                chargingStationId = "CS1",
                action = "BootNotification",
                from = "source",
                to = "destination",
                payload = request
            )
        )

        val expectedEnvelope =
            """<s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope"
                xmlns:a="http://www.w3.org/2005/08/addressing"
                 xmlns:o="urn://Ocpp/Cs/2015/10/"><s:Header>
<a:MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:MessageID>
<a:Action>/BootNotification</a:Action><o:chargeBoxIdentity>CS1</o:chargeBoxIdentity>
<a:From><a:Address>source</a:Address></a:From><a:To>destination</a:To></s:Header>
<s:Body><o:bootNotificationRequest>
<o:chargePointModel>XXXXXXXX</o:chargePointModel>
<o:chargePointVendor>YYYYYYYY</o:chargePointVendor>
<o:chargePointSerialNumber>5aa469fd41344fe5a575368cd</o:chargePointSerialNumber>
<o:firmwareVersion>1.5.1.d723fd5</o:firmwareVersion>
</o:bootNotificationRequest></s:Body></s:Envelope>""".trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp16SoapParser.parseAnyRequestFromSoap(messageSoap)) {
            get { payload }.isA<BootNotificationReq>().and {
                get { chargePointVendor }.isEqualTo("YYYYYYYY")
                get { chargePointModel }.isEqualTo("XXXXXXXX")
                get { chargePointSerialNumber }.isEqualTo("5aa469fd41344fe5a575368cd")
                get { firmwareVersion }.isEqualTo("1.5.1.d723fd5")
            }
        }
    }

    @Test
    fun `should map BootNotificationResp to soap`() {
        val response = BootNotificationResp(
            status = RegistrationStatus.Rejected,
            currentTime = Instant.parse("2022-05-17T15:43:08.123456Z"),
            interval = 1800
        )

        val messageSoap = ocpp16SoapParser
            .mapResponseToSoap(
                ResponseSoapMessage(
                    messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                    relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                    action = "BootNotification",
                    payload = response,
                    from = "source",
                    to = "destination"
                )
            )

        val expectedEnvelope =
            """<s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope"
                                xmlns:a="http://www.w3.org/2005/08/addressing"
                            xmlns:o="urn://Ocpp/Cs/2015/10/">
                    <s:Header>
                        <a:MessageID>urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</a:MessageID>
                        <a:Action>/BootNotificationResponse</a:Action>
                        <a:From>
                            <a:Address>source</a:Address>
                        </a:From>
                        <a:To>destination</a:To>
                        <a:RelatesTo>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:RelatesTo>
                    </s:Header>
                    <s:Body>
                        <o:bootNotificationResponse>
                            <o:currentTime>2022-05-17T15:43:08.123Z</o:currentTime>
                            <o:interval>1800</o:interval>
                            <o:status>Rejected</o:status>
                        </o:bootNotificationResponse>
                    </s:Body>
               </s:Envelope>""".trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)

        expectThat(ocpp16SoapParser.parseAnyResponseFromSoap(messageSoap)) {
            get { payload }.isA<BootNotificationResp>().and {
                get { status }.isEqualTo(RegistrationStatus.Rejected)
                get { currentTime }.isEqualTo(Instant.parse("2022-05-17T15:43:08.123Z"))
                get { interval }.isEqualTo(1800)
            }
        }
    }

    @Test
    fun `should map DataTransferReq to soap`() {
        val request = DataTransferReq(
            vendorId = "XXXXXXXX",
            messageId = "Detection loop",
            data = "{\"connectorId\":10,\"name\":\"Vehicle\",\"state\":\"1\",\"timestamp\":\"2022-05-17T15:42:03Z:\"}"
        )

        val messageSoap = ocpp16SoapParser
            .mapRequestToSoap(
                RequestSoapMessage(
                    messageId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                    chargingStationId = "CS1",
                    action = "DataTransfer",
                    from = "source",
                    to = "destination",
                    payload = request
                )
            )

        val expectedEnvelope =
            """<s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope"
                xmlns:a="http://www.w3.org/2005/08/addressing"
                 xmlns:o="urn://Ocpp/Cs/2015/10/"><s:Header>
<a:MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:MessageID><a:Action>/DataTransfer</a:Action>
<o:chargeBoxIdentity>CS1</o:chargeBoxIdentity><a:From><a:Address>source</a:Address></a:From>
<a:To>destination</a:To></s:Header><s:Body><o:dataTransferRequest><o:vendorId>XXXXXXXX</o:vendorId>
<o:messageId>Detection loop</o:messageId>
<o:data>{"connectorId":10,"name":"Vehicle","state":"1","timestamp":"2022-05-17T15:42:03Z:"}</o:data>
</o:dataTransferRequest></s:Body></s:Envelope>
            """.trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp16SoapParser.parseAnyRequestFromSoap(messageSoap)) {
            get { payload }
                .isA<DataTransferReq>().and {
                    get { vendorId }.isEqualTo("XXXXXXXX")
                    get { messageId }.isEqualTo("Detection loop")
                    get { data }
                        .isEqualTo(
                            "{\"connectorId\":10,\"name\":\"Vehicle\",\"state\":\"1\"," +
                                "\"timestamp\":\"2022-05-17T15:42:03Z:\"}"
                        )
                }
        }
    }

    @Test
    fun `should map DataTransferResp to soap`() {
        val response = DataTransferResp(
            status = DataTransferStatus.Accepted
        )

        val messageSoap = ocpp16SoapParser
            .mapResponseToSoap(
                ResponseSoapMessage(
                    messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                    relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                    action = "DataTransfer",
                    payload = response,
                    from = "source",
                    to = "destination"
                )
            )

        expectThat(messageSoap) {
            get { this }.contains(
                "<a:Action>/DataTransferResponse</a:Action>"
            )
            get { this }.contains("xmlns:o=\"urn://Ocpp/Cs/2015/10/\"")
            get { this }.contains(
                "<a:MessageID>urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</a:MessageID>"
            )
            get { this }.contains(
                "<a:RelatesTo>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:RelatesTo>"
            )
        }
        expectThat(ocpp16SoapParser.parseAnyResponseFromSoap(messageSoap)) {
            get { payload }.isA<DataTransferResp>().and {
                get { status }.isEqualTo(DataTransferStatus.Accepted)
            }
        }
    }

    @Test
    fun `should map DiagnosticsStatusNotificationReq to soap`() {
        val messageId = UUID.randomUUID().toString()

        val request = DiagnosticsStatusNotificationReq(
            status = DiagnosticsStatus.Uploaded
        )

        val reqSoap = ocpp16SoapParser.mapRequestToSoap(
            RequestSoapMessage(
                messageId = messageId,
                action = "DiagnosticsStatusNotification",
                payload = request,
                from = "source",
                to = "destination",
                chargingStationId = "testId"
            )
        )

        expectThat(reqSoap) {
            get { this }.contains("xmlns:o=\"urn://Ocpp/Cs/2015/10/\"")
            get { this }.contains("<a:Action>/DiagnosticsStatusNotification</a:Action>")
            get { this }.contains("<a:MessageID>$messageId</a:MessageID>")
        }
        expectThat(ocpp16SoapParser.parseAnyRequestFromSoap(reqSoap)) {
            get { payload }.isA<DiagnosticsStatusNotificationReq>().and {
                get { status }.isEqualTo(DiagnosticsStatus.Uploaded)
            }
        }
    }

    @Test
    fun `should map DiagnosticsStatusNotificationResp to soap`() {
        val messageId = UUID.randomUUID().toString()
        val relateTo = UUID.randomUUID().toString()
        val response = DiagnosticsStatusNotificationResp()
        val messageSoap = ocpp16SoapParser.mapResponseToSoap(
            ResponseSoapMessage(
                messageId = messageId,
                relatesTo = relateTo,
                action = "DiagnosticsStatusNotification",
                payload = response,
                from = "destination",
                to = "source"
            )
        )

        expectThat(messageSoap) {
            get { this }.contains("xmlns:o=\"urn://Ocpp/Cs/2015/10/\"")
            get { this }.contains("<a:Action>/DiagnosticsStatusNotificationResponse</a:Action>")
            get { this }.contains("<a:MessageID>$messageId</a:MessageID>")
            get { this }.contains("<a:RelatesTo>$relateTo</a:RelatesTo>")
        }
        expectThat(ocpp16SoapParser.parseAnyResponseFromSoap(messageSoap)) {
            get { payload }.isA<DiagnosticsStatusNotificationResp>()
        }
    }

    @Test
    fun `should map FirmwareStatusNotificationReq to soap`() {
        val response = FirmwareStatusNotificationReq(
            status = FirmwareStatus.Installed
        )

        val messageSoap = ocpp16SoapParser
            .mapRequestToSoap(
                RequestSoapMessage(
                    messageId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                    chargingStationId = "CS1",
                    action = "FirmwareStatusNotification",
                    from = "source",
                    to = "destination",
                    payload = response
                )
            )

        val expectedEnvelope =
            """<s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope"
                xmlns:a="http://www.w3.org/2005/08/addressing"
                xmlns:o="urn://Ocpp/Cs/2015/10/"><s:Header>
<a:MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:MessageID>
<a:Action>/FirmwareStatusNotification</a:Action><o:chargeBoxIdentity>CS1</o:chargeBoxIdentity>
<a:From><a:Address>source</a:Address></a:From><a:To>destination</a:To></s:Header>
<s:Body><o:firmwareStatusNotificationRequest><o:status>Installed</o:status></o:firmwareStatusNotificationRequest>
</s:Body></s:Envelope>""".trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp16SoapParser.parseAnyRequestFromSoap(messageSoap)) {
            get { payload }.isA<FirmwareStatusNotificationReq>().and {
                get { status }.isEqualTo(FirmwareStatus.Installed)
            }
        }
    }

    @Test
    fun `should map FirmwareStatusNotificationResp to soap`() {
        val response = FirmwareStatusNotificationResp()

        val messageSoap = ocpp16SoapParser
            .mapResponseToSoap(
                ResponseSoapMessage(
                    messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                    relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                    action = "FirmwareStatusNotification",
                    payload = response,
                    from = "source",
                    to = "destination"
                )
            )

        val expectedEnvelope =
            """<s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope"
                 xmlns:a="http://www.w3.org/2005/08/addressing"
                 xmlns:o="urn://Ocpp/Cs/2015/10/"><s:Header>
<a:MessageID>urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</a:MessageID>
<a:Action>/FirmwareStatusNotificationResponse</a:Action>
<a:From><a:Address>source</a:Address></a:From><a:To>destination</a:To>
<a:RelatesTo>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:RelatesTo>
</s:Header><s:Body><o:firmwareStatusNotificationResponse/></s:Body></s:Envelope>
            """.trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp16SoapParser.parseAnyResponseFromSoap(messageSoap)) {
            get { payload }.isA<FirmwareStatusNotificationResp>()
        }
    }

    @Test
    fun `should map HeartbeatReq to soap`() {
        val response = HeartbeatReq()

        val messageSoap = ocpp16SoapParser
            .mapRequestToSoap(
                RequestSoapMessage(
                    messageId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                    chargingStationId = "CS1",
                    action = "Heartbeat",
                    from = "source",
                    to = "destination",
                    payload = response
                )
            )

        val expectedEnvelope =
            """<s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope"
                xmlns:a="http://www.w3.org/2005/08/addressing"
                xmlns:o="urn://Ocpp/Cs/2015/10/"><s:Header>
<a:MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:MessageID>
<a:Action>/Heartbeat</a:Action><o:chargeBoxIdentity>CS1</o:chargeBoxIdentity><a:From>
<a:Address>source</a:Address></a:From><a:To>destination</a:To></s:Header><s:Body><o:heartbeatRequest/>
</s:Body></s:Envelope>
            """.trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp16SoapParser.parseAnyRequestFromSoap(messageSoap)) {
            get { payload }.isA<HeartbeatReq>()
        }
    }

    @Test
    fun `should map HeartbeatResp to soap`() {
        val response = HeartbeatResp(
            currentTime = Instant.parse("2022-05-17T15:42:00.503Z")
        )

        val messageSoap = ocpp16SoapParser
            .mapResponseToSoap(
                ResponseSoapMessage(
                    messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                    relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                    action = "Heartbeat",
                    payload = response,
                    from = null,
                    to = "destination"
                )
            )

        expectThat(messageSoap) {
            get { this }.contains("<a:Action>/HeartbeatResponse</a:Action>")
            get { this }.contains(
                "<a:MessageID>urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</a:MessageID>"
            )
            get { this }.contains(
                "<a:RelatesTo>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:RelatesTo>"
            )
        }
        expectThat(ocpp16SoapParser.parseAnyResponseFromSoap(messageSoap)) {
            get { payload }.isA<HeartbeatResp>().and {
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

        val messageSoap = ocpp16SoapParser
            .mapRequestToSoap(
                RequestSoapMessage(
                    messageId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                    chargingStationId = "CS1",
                    action = "MeterValues",
                    from = null,
                    to = "destination",
                    payload = response
                )
            )

        val expectedEnvelope = """<s:Envelope
            xmlns:s="http://www.w3.org/2003/05/soap-envelope"
            xmlns:a="http://www.w3.org/2005/08/addressing"
            xmlns:o="urn://Ocpp/Cs/2015/10/"><s:Header>
<a:MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:MessageID>
<a:Action>/MeterValues</a:Action><o:chargeBoxIdentity>CS1</o:chargeBoxIdentity>
<a:From><a:Address>http://www.w3.org/2005/08/addressing/anonymous</a:Address></a:From><a:To>destination</a:To>
</s:Header><s:Body><o:meterValuesRequest><o:connectorId>1</o:connectorId>
<o:meterValue><sampledValue><value>15213716</value><context>Sample.Periodic</context><format>Raw</format>
<location>Inlet</location><measurand>Energy.Active.Import.Register</measurand><unit>Wh</unit></sampledValue>
<timestamp>2022-05-17T15:41:19.912Z</timestamp></o:meterValue>
<o:transactionId>15917</o:transactionId></o:meterValuesRequest></s:Body></s:Envelope>
        """.trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp16SoapParser.parseAnyRequestFromSoap(messageSoap)) {
            get { payload }.isA<MeterValuesReq>().and {
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

        val messageSoap = ocpp16SoapParser
            .mapResponseToSoap(
                ResponseSoapMessage(
                    messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                    relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                    action = "MeterValues",
                    payload = response,
                    from = "source",
                    to = null
                )
            )

        expectThat(messageSoap) {
            get { this }.contains(
                "<a:Action>/MeterValuesResponse</a:Action>"
            )
            get { this }.contains("xmlns:o=\"urn://Ocpp/Cs/2015/10/\"")
            get { this }.contains(
                "<a:MessageID>urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</a:MessageID>"
            )
            get { this }.contains(
                "<a:RelatesTo>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:RelatesTo>"
            )
        }
        expectThat(ocpp16SoapParser.parseAnyResponseFromSoap(messageSoap)) {
            get { payload }.isA<MeterValuesResp>()
        }
    }

    @Test
    fun `should map StartTransactionReq to soap`() {
        val response = StartTransactionReq(
            connectorId = 1,
            idTag = "AAAAAAAA",
            meterStart = 18804500,
            timestamp = Instant.parse("2022-05-17T15:41:58.351Z")
        )

        val messageSoap = ocpp16SoapParser
            .mapRequestToSoap(
                RequestSoapMessage(
                    messageId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                    chargingStationId = "CS1",
                    action = "StartTransaction",
                    from = null,
                    to = null,
                    payload = response
                )
            )

        val expectedEnvelope =
            """<s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope"
                xmlns:a="http://www.w3.org/2005/08/addressing" xmlns:o="urn://Ocpp/Cs/2015/10/"><s:Header>
<a:MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:MessageID><a:Action>/StartTransaction</a:Action>
<o:chargeBoxIdentity>CS1</o:chargeBoxIdentity>
<a:From><a:Address>http://www.w3.org/2005/08/addressing/anonymous</a:Address></a:From>
<a:To>http://www.w3.org/2005/08/addressing/anonymous</a:To>
</s:Header><s:Body><o:startTransactionRequest>
<connectorId>1</connectorId><idTag>AAAAAAAA</idTag><meterStart>18804500</meterStart>
<timestamp>2022-05-17T15:41:58.351Z</timestamp></o:startTransactionRequest></s:Body></s:Envelope>""".trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp16SoapParser.parseAnyRequestFromSoap(messageSoap)) {
            get { payload }.isA<StartTransactionReq>().and {
                get { connectorId }.isEqualTo(1)
                get { idTag }.isEqualTo("AAAAAAAA")
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

        val messageSoap = ocpp16SoapParser
            .mapResponseToSoap(
                ResponseSoapMessage(
                    messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                    relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                    action = "StartTransaction",
                    payload = response,
                    from = null,
                    to = null
                )
            )

        expectThat(messageSoap) {
            get { this }.contains(
                "<a:Action>/StartTransactionResponse</a:Action>"
            )
            get { this }.contains("xmlns:o=\"urn://Ocpp/Cs/2015/10/\"")
            get { this }.contains(
                "<a:MessageID>urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</a:MessageID>"
            )
            get { this }.contains(
                "<a:RelatesTo>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:RelatesTo>"
            )
            get { this }.contains(
                "<a:From><a:Address>http://www.w3.org/2005/08/addressing/anonymous</a:Address></a:From>"
            )
            get { this }.contains(
                "<a:To>http://www.w3.org/2005/08/addressing/anonymous</a:To>"
            )
        }
        expectThat(ocpp16SoapParser.parseAnyResponseFromSoap(messageSoap)) {
            get { payload }.isA<StartTransactionResp>().and {
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

        val messageSoap = ocpp16SoapParser
            .mapRequestToSoap(
                RequestSoapMessage(
                    messageId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                    chargingStationId = "CS1",
                    action = "StatusNotification",
                    from = "source",
                    to = "destination",
                    payload = response
                )
            )

        val expectedEnvelope =
            """<s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope"
                xmlns:a="http://www.w3.org/2005/08/addressing"
                xmlns:o="urn://Ocpp/Cs/2015/10/"><s:Header>
<a:MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:MessageID><a:Action>/StatusNotification</a:Action>
<o:chargeBoxIdentity>CS1</o:chargeBoxIdentity><a:From><a:Address>source</a:Address></a:From><a:To>destination</a:To>
</s:Header><s:Body><o:statusNotificationRequest><connectorId>1</connectorId><errorCode>NoError</errorCode>
<status>Available</status><info>No error.</info><timestamp>2022-05-17T15:41:59.486Z</timestamp>
<vendorErrorCode>0x0</vendorErrorCode></o:statusNotificationRequest></s:Body></s:Envelope>""".trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp16SoapParser.parseAnyRequestFromSoap(messageSoap)) {
            get { payload }.isA<StatusNotificationReq>().and {
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
        val messageId = UUID.randomUUID().toString()
        val relatesTo = UUID.randomUUID().toString()
        val action = "StatusNotification"

        val messageSoap = ocpp16SoapParser
            .mapResponseToSoap(
                ResponseSoapMessage(
                    messageId = messageId,
                    relatesTo = relatesTo,
                    action = action,
                    payload = response,
                    from = "",
                    to = null
                )
            )

        expectThat(messageSoap) {
            get { this }.contains(
                "<a:Action>/${action}Response</a:Action>"
            )
            get { this }.contains("xmlns:o=\"urn://Ocpp/Cs/2015/10/\"")
            get { this }.contains(
                "<a:MessageID>$messageId</a:MessageID>"
            )
            get { this }.contains(
                "<a:RelatesTo>$relatesTo</a:RelatesTo>"
            )
            get { this }.contains(
                "<a:From><a:Address/></a:From>"
            )
            get { this }.contains(
                "<a:To>http://www.w3.org/2005/08/addressing/anonymous</a:To>"
            )
        }
        expectThat(ocpp16SoapParser.parseAnyResponseFromSoap(messageSoap)) {
            get { payload }.isA<StatusNotificationResp>()
        }
    }

    @Test
    fun `should map StopTransactionReq to soap`() {
        val response = StopTransactionReq(
            transactionId = 16696,
            idTag = "AAAAAAAA",
            timestamp = Instant.parse("2022-05-05T04:37:15Z"),
            meterStop = 19224
        )

        val messageSoap = ocpp16SoapParser
            .mapRequestToSoap(
                RequestSoapMessage(
                    messageId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                    chargingStationId = "CS1",
                    action = "StopTransaction",
                    from = "source",
                    to = "destination",
                    payload = response
                )
            )

        val expectedEnvelope =
            """<s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope"
                xmlns:a="http://www.w3.org/2005/08/addressing"
                xmlns:o="urn://Ocpp/Cs/2015/10/"><s:Header>
<a:MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:MessageID>
<a:Action>/StopTransaction</a:Action><o:chargeBoxIdentity>CS1</o:chargeBoxIdentity>
<a:From><a:Address>source</a:Address></a:From><a:To>destination</a:To></s:Header><s:Body>
<o:stopTransactionRequest><meterStop>19224</meterStop><timestamp>2022-05-05T04:37:15Z</timestamp>
<transactionId>16696</transactionId><idTag>AAAAAAAA</idTag></o:stopTransactionRequest></s:Body>
</s:Envelope>""".trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp16SoapParser.parseAnyRequestFromSoap(messageSoap)) {
            get { payload }.isA<StopTransactionReq>().and {
                get { transactionId }.isEqualTo(16696)
                get { idTag }.isEqualTo("AAAAAAAA")
                get { timestamp }.isEqualTo(Instant.parse("2022-05-05T04:37:15Z"))
                get { meterStop }.isEqualTo(19224)
            }
        }
    }

    @Test
    fun `should map StopTransactionResp to soap`() {
        val messageId = UUID.randomUUID().toString()
        val relatesTo = UUID.randomUUID().toString()
        val action = "StopTransaction"

        val response = StopTransactionResp(
            idTagInfo = IdTagInfo(
                status = AuthorizationStatus.Accepted,
                expiryDate = Instant.parse("2022-05-16T15:42:05.128Z")
            )
        )

        val messageSoap = ocpp16SoapParser
            .mapResponseToSoap(
                ResponseSoapMessage(
                    messageId = messageId,
                    relatesTo = relatesTo,
                    action = "StopTransaction",
                    payload = response,
                    from = "source",
                    to = "destination"
                )
            )

        expectThat(messageSoap) {
            get { this }.contains(
                "<a:Action>/${action}Response</a:Action>"
            )
            get { this }.contains("xmlns:o=\"urn://Ocpp/Cs/2015/10/\"")
            get { this }.contains(
                "<a:MessageID>$messageId</a:MessageID>"
            )
            get { this }.contains(
                "<a:RelatesTo>$relatesTo</a:RelatesTo>"
            )
            get { this }.contains(
                "<a:From><a:Address>source</a:Address></a:From>"
            )
            get { this }.contains(
                "<a:To>destination</a:To>"
            )
        }
        expectThat(ocpp16SoapParser.parseAnyResponseFromSoap(messageSoap)) {
            get { payload }.isA<StopTransactionResp>().and {
                get { idTagInfo }.isNotNull().and {
                    get { status }.isEqualTo(AuthorizationStatus.Accepted)
                    get { expiryDate }.isEqualTo(Instant.parse("2022-05-16T15:42:05.128Z"))
                }
            }
        }
    }

    @Test
    fun `should parse message to RemoteStartTransactionReq`() {
        val message =
            """
                <s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope" xmlns:a="http://www.w3.org/2005/08/addressing"
                            xmlns:o="urn://Ocpp/Cp/2015/10/">
                    <s:Header>
                        <a:MessageID>3fd2ec71-dcd4-425a-a226-73b227748ced</a:MessageID>
                        <a:Action>/RemoteStartTransaction</a:Action>
                        <o:chargeBoxIdentity>XXXXXXXXXX</o:chargeBoxIdentity>
                        <a:From>
                            <a:Address>localhost:8095</a:Address>
                        </a:From>
                        <a:To>http://localhost:8102/api/soap/XXXXXXXXXX</a:To>
                    </s:Header>
                    <s:Body>
                        <o:remoteStartTransactionRequest>
                            <idTag>DEADBEAF</idTag>
                            <connectorId>4</connectorId>
                        </o:remoteStartTransactionRequest>
                    </s:Body>
                </s:Envelope>
            """.trimSoap()

        expectThat(
            ocpp16SoapParser
                .parseRequestFromSoap<RemoteStartTransactionReq>(message).payload
        ).and {
            get { connectorId }.isEqualTo(4)
            get { idTag }.isEqualTo("DEADBEAF")
        }
    }

    @Test
    fun `should parse message to RemoteStopTransactionReq`() {
        val message =
            """
                <s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope" xmlns:a="http://www.w3.org/2005/08/addressing"
                            xmlns:o="urn://Ocpp/Cp/2015/10/">
                    <s:Header>
                        <a:MessageID>3fd2ec71-dcd4-425a-a226-73b227748ced</a:MessageID>
                        <a:Action>/RemoteStartTransaction</a:Action>
                        <o:chargeBoxIdentity>XXXXXXXXXX</o:chargeBoxIdentity>
                        <a:From>
                            <a:Address>localhost:8095</a:Address>
                        </a:From>
                        <a:To>http://localhost:8102/api/soap/XXXXXXXXXX</a:To>
                    </s:Header>
                    <s:Body>
                        <o:remoteStopTransactionRequest>
                            <transactionId>1</transactionId>
                        </o:remoteStopTransactionRequest>
                    </s:Body>
                </s:Envelope>
            """.trimSoap()

        expectThat(
            ocpp16SoapParser
                .parseRequestFromSoap<RemoteStopTransactionReq>(message).payload
        ).and {
            get { transactionId }.isEqualTo(1)
        }
    }

    @Test
    fun `should parse message to GetCompositeScheduleRequest`() {
        val message = """<ns0:Envelope
            xmlns:ns0="http://www.w3.org/2003/05/soap-envelope"
            xmlns:ns1="http://www.w3.org/2005/08/addressing"
            xmlns:ns2="urn://Ocpp/Cp/2015/10/"><ns0:Header><ns1:To>http://127.0.0.1:28080</ns1:To>
            <ns1:From><ns1:Address>http://192.168.x.x:8084/centralSystemService</ns1:Address>
            </ns1:From><ns2:chargeBoxIdentity>ADP-ocpp-229</ns2:chargeBoxIdentity><ns1:MessageID>31451</ns1:MessageID>
            <ns1:Action>/GetCompositeSchedule</ns1:Action></ns0:Header><ns0:Body><ns2:getCompositeScheduleRequest>
            <ns2:connectorId>1</ns2:connectorId><ns2:duration>1800</ns2:duration>
            <ns2:chargingRateUnit>A</ns2:chargingRateUnit></ns2:getCompositeScheduleRequest>
            </ns0:Body></ns0:Envelope>
        """.trimMargin()

        expectThat(
            ocpp16SoapParser
                .parseRequestFromSoap<GetCompositeScheduleReq>(message).payload
        ).and {
            get { duration }.isEqualTo(1800)
            get { chargingRateUnit }.isEqualTo(ChargingRateUnitType.A)
            get { connectorId }.isEqualTo(1)
        }
    }

    @Test
    fun `should parse message to GetCompositeScheduleResp`() {
        val message = """<ns0:Envelope xmlns:ns0="http://www.w3.org/2003/05/soap-envelope"
            xmlns:ns1="http://www.w3.org/2005/08/addressing"
            xmlns:ns2="urn://Ocpp/Cp/2015/10/"><ns0:Header>
            <ns1:To>http://xxxxxxxx.fr/ocpp/v16s/</ns1:To>
            <ns1:ReplyTo><ns1:Address>http://www.w3.org/2005/08/addressing/anonymous</ns1:Address>
            </ns1:ReplyTo><ns2:chargeBoxIdentity>XX:XX:XX:XX:XX:XX</ns2:chargeBoxIdentity>
            <ns1:MessageID>urn:uuid:e2798428-c921-4c5b-be59-b684ab116378</ns1:MessageID>
            <ns1:RelatesTo>urn:uuid:40c39b9a-dae4-48ca-80e2-9e8f820c5ac3</ns1:RelatesTo>
            <ns1:Action>/GetCompositeScheduleResponse</ns1:Action></ns0:Header><ns0:Body>
            <ns2:getCompositeScheduleResponse><ns2:status>Accepted</ns2:status>
            <ns2:connectorId>1</ns2:connectorId>
            <ns2:scheduleStart>2023-01-18T13:22:55.768108Z</ns2:scheduleStart>
            <ns2:chargingSchedule><ns2:duration>6480000</ns2:duration>
            <ns2:chargingRateUnit>A</ns2:chargingRateUnit><ns2:chargingSchedulePeriod>
            <ns2:startPeriod>0</ns2:startPeriod><ns2:limit>0</ns2:limit>
            </ns2:chargingSchedulePeriod></ns2:chargingSchedule></ns2:getCompositeScheduleResponse>
            </ns0:Body></ns0:Envelope>
        """.trimSoap()

        expectThat(
            ocpp16SoapParser
                .parseAnyResponseFromSoap(message).payload
        )
            .isA<GetCompositeScheduleResp>()
            .and {
                get { connectorId }.isEqualTo(1)
                get { status }.isEqualTo(GetCompositeScheduleStatus.Accepted)
                get { scheduleStart }.isEqualTo(Instant.parse("2023-01-18T13:22:55.768108Z"))
                get { chargingSchedule }
                    .isNotNull()
                    .and {
                        get { duration }.isEqualTo(6480000)
                        get { chargingRateUnit }.isEqualTo(ChargingRateUnitType.A)
                        get { chargingSchedulePeriod }
                            .isNotEmpty()
                            .first()
                            .and {
                                get { limit }.isEqualTo(0)
                                get { startPeriod }.isEqualTo(0)
                            }
                    }
            }
    }

    @Test
    fun `should parse message to SetChargingProfileRequest`() {
        val message =
            """<ns0:Envelope xmlns:ns0="http://www.w3.org/2003/05/soap-envelope"
                xmlns:ns1="http://www.w3.org/2005/08/addressing"
                xmlns:ns2="urn://Ocpp/Cp/2015/10/"><ns0:Header>
                <ns1:To>http://127.0.0.1:28080</ns1:To><ns1:From>
                <ns1:Address>http://192.168.x.x:8084/centralSystemService</ns1:Address></ns1:From>
                <ns2:chargeBoxIdentity>ADP-ocpp-229</ns2:chargeBoxIdentity><ns1:MessageID>2462</ns1:MessageID>
                <ns1:Action>/SetChargingProfile</ns1:Action></ns0:Header><ns0:Body><ns2:setChargingProfileRequest>
                <ns2:connectorId>1</ns2:connectorId><ns2:csChargingProfiles>
                <ns2:chargingProfileId>101</ns2:chargingProfileId><ns2:stackLevel>2</ns2:stackLevel>
                <ns2:chargingProfilePurpose>TxDefaultProfile</ns2:chargingProfilePurpose>
                <ns2:chargingProfileKind>Relative</ns2:chargingProfileKind><ns2:chargingSchedule>
                <ns2:chargingRateUnit>A</ns2:chargingRateUnit><ns2:chargingSchedulePeriod>
                <ns2:startPeriod>0</ns2:startPeriod><ns2:limit>0</ns2:limit></ns2:chargingSchedulePeriod>
                </ns2:chargingSchedule></ns2:csChargingProfiles></ns2:setChargingProfileRequest></ns0:Body>
                </ns0:Envelope>""".trimSoap()

        expectThat(
            ocpp16SoapParser
                .parseAnyRequestFromSoap(message).payload
        )
            .isA<SetChargingProfileReq>()
            .and {
                get { connectorId }.isEqualTo(1)
                get { csChargingProfiles }
                    .and {
                        get { stackLevel }.isEqualTo(2)
                        get { chargingProfileId }.isEqualTo(101)
                        get { chargingProfileKind }.isEqualTo(ChargingProfileKindType.Relative)
                        get { chargingProfilePurpose }.isEqualTo(ChargingProfilePurposeType.TxDefaultProfile)
                        get { validFrom }.isNull()
                        get { validTo }.isNull()
                        get { recurrencyKind }.isNull()
                        get { chargingSchedule }
                            .and {
                                get { startSchedule }.isNull()
                                get { duration }.isNull()
                                get { chargingRateUnit }.isEqualTo(ChargingRateUnitType.A)
                                get { chargingSchedulePeriod }
                                    .isNotEmpty()
                                    .first()
                                    .and {
                                        get { limit }.isEqualTo(0)
                                        get { startPeriod }.isEqualTo(0)
                                    }
                            }
                    }
            }
    }

    @Test
    fun `should map SetChargingProfileReq to Soap`() {
        val action = "SetChargingProfile"
        val messageId = "test"
        val request = SetChargingProfileReq(
            connectorId = 1,
            csChargingProfiles = ChargingProfile(
                chargingProfileId = 100,
                stackLevel = 2,
                chargingProfilePurpose = ChargingProfilePurposeType.TxDefaultProfile,
                chargingProfileKind = ChargingProfileKindType.Relative,
                chargingSchedule = ChargingSchedule(
                    duration = 1800,
                    chargingRateUnit = ChargingRateUnitType.A,
                    chargingSchedulePeriod = listOf(
                        ChargingSchedulePeriod(
                            limit = 0,
                            startPeriod = 0
                        ),
                        ChargingSchedulePeriod(
                            limit = 20,
                            startPeriod = 800
                        )
                    )
                )
            )
        )

        val messageSoap = ocpp16SoapParser
            .mapRequestToSoap(
                RequestSoapMessage(
                    messageId = messageId,
                    chargingStationId = "ocppid",
                    action = action,
                    payload = request,
                    from = "source",
                    to = "destination"
                )
            )

        expectThat(messageSoap) {
            get { this }.contains(
                "<a:Action>/$action</a:Action>"
            )
            get { this }.contains(
                "<a:MessageID>$messageId</a:MessageID>"
            )
            get { this }.contains(
                "<a:From><a:Address>source</a:Address></a:From>"
            )
            get { this }.contains(
                "<a:To>destination</a:To>"
            )
            get { this }.contains(
                """<chargingSchedulePeriod><startPeriod>0</startPeriod><limit>0</limit>
</chargingSchedulePeriod><chargingSchedulePeriod><startPeriod>800</startPeriod>
<limit>20</limit></chargingSchedulePeriod>""".trimSoap()
            )
        }
        expectThat(ocpp16SoapParser.parseAnyRequestFromSoap(messageSoap)) {
            get { payload }.isA<SetChargingProfileReq>().and {
                get { connectorId }.isEqualTo(1)
                get { csChargingProfiles }
                    .and {
                        get { stackLevel }.isEqualTo(2)
                        get { chargingProfileId }.isEqualTo(100)
                        get { chargingProfileKind }.isEqualTo(ChargingProfileKindType.Relative)
                        get { chargingProfilePurpose }.isEqualTo(ChargingProfilePurposeType.TxDefaultProfile)
                        get { validFrom }.isNull()
                        get { validTo }.isNull()
                        get { recurrencyKind }.isNull()
                        get { chargingSchedule }
                            .and {
                                get { duration }.isEqualTo(1800)
                                get { startSchedule }.isNull()
                                get { chargingRateUnit }.isEqualTo(ChargingRateUnitType.A)
                                get { chargingSchedulePeriod }
                                    .isNotEmpty()
                                    .first()
                                    .and {
                                        get { limit }.isEqualTo(0)
                                        get { startPeriod }.isEqualTo(0)
                                        get { numberPhases }.isNull()
                                    }
                            }
                    }
            }
        }
    }

    @Test
    fun `should parse DataTransfert message with wrong data format to DataTransferReq`() {
        val parser = Ocpp16SoapParser(
            forcedFieldTypes = listOf(
                Ocpp16ForcedFieldType(
                    action = Actions.DATATRANSFER,
                    actionType = ActionTypeEnum.REQUEST,
                    fieldPath = "data",
                    typeRequested = TypeConvertEnum.STRING
                )
            )
        )
        val message =
            """<s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope"
                xmlns:a="http://www.w3.org/2005/08/addressing"
                 xmlns:o="urn://Ocpp/Cs/2015/10/"><s:Header>
<a:MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:MessageID><a:Action>/DataTransfer</a:Action>
<o:chargeBoxIdentity>CS1</o:chargeBoxIdentity><a:From><a:Address>source</a:Address></a:From>
<a:To>destination</a:To></s:Header><s:Body><o:dataTransferRequest><o:vendorId>XXXXXXXX</o:vendorId>
<o:messageId>Detection loop</o:messageId>
<o:data><not_a_good_format>Detection loop</not_a_good_format></o:data>
</o:dataTransferRequest></s:Body></s:Envelope>
            """.trimSoap()

        expectThat(
            parser.parseAnyRequestFromSoap(message)
        ).and {
            get { payload }.isA<DataTransferReq>()
                .and {
                    get { data }.isEqualTo("{\"not_a_good_format\":\"Detection loop\"}")
                }
            get { warnings }.isNotNull().hasSize(1).and {
                get { get(0) }.and {
                    get { code }.isEqualTo(ErrorDetailCode.CONVERT_FIELD_REPLACED.value)
                    get { detail }.contains("[data] converted to STRING")
                }
            }
        }
    }

    @Test
    fun `should parse message to SetChargingProfileResponse`() {
        val message = """<ns0:Envelope xmlns:ns0="http://www.w3.org/2003/05/soap-envelope"
            xmlns:ns1="http://www.w3.org/2005/08/addressing"
            xmlns:ns2="urn://Ocpp/Cp/2015/10/"><ns0:Header><ns1:From>
            <ns1:Address>anonymous</ns1:Address></ns1:From><ns1:To>http://0.0.0.0:28080</ns1:To>
            <ns1:ReplyTo><ns1:Address>http://www.w3.org/2005/08/addressing/anonymous</ns1:Address>
            </ns1:ReplyTo><ns2:chargeBoxIdentity>ADP-OCPP-229</ns2:chargeBoxIdentity>
            <ns1:MessageID>1OX17K</ns1:MessageID><ns1:Action>/SetChargingProfileResponse</ns1:Action>
            <ns1:RelatesTo>2462</ns1:RelatesTo></ns0:Header><ns0:Body><ns2:setChargingProfileResponse>
            <ns2:status>Accepted</ns2:status></ns2:setChargingProfileResponse></ns0:Body></ns0:Envelope>
        """.trimSoap()

        expectThat(
            ocpp16SoapParser
                .parseAnyResponseFromSoap(message).payload
        )
            .isA<SetChargingProfileResp>()
            .and {
                get { status }.isEqualTo(ChargingProfileStatus.Accepted)
            }
    }

    @Test
    fun `should map SoapFault to soap`() {
        val messageId = UUID.randomUUID().toString()
        val relatesTo = UUID.randomUUID().toString()
        val action = "StopTransaction"

        val response = SoapFault.internalError()
        val messageSoap = ocpp16SoapParser
            .mapResponseToSoap(
                ResponseSoapMessage(
                    messageId = messageId,
                    relatesTo = relatesTo,
                    action = action,
                    payload = response,
                    from = "source",
                    to = "destination"
                )
            )

        val expectedEnvelope = """<s:Envelope
                xmlns:s="http://www.w3.org/2003/05/soap-envelope"
                xmlns:a="http://www.w3.org/2005/08/addressing"
                xmlns:o="urn://Ocpp/Cs/2015/10/"><s:Header>
<a:MessageID>$messageId</a:MessageID><a:Action>/StopTransactionResponse</a:Action>
<a:From><a:Address>source</a:Address></a:From><a:To>destination</a:To>
<a:RelatesTo>$relatesTo</a:RelatesTo></s:Header><s:Body>
<s:Fault><Code><s:Value>Receiver</s:Value><o:Subcode><Value>InternalError</Value></o:Subcode>
</Code><Reason>
<s:Text lang="en">An internal error occurred and the receiver is not able to complete the operation.</s:Text>
</Reason></s:Fault></s:Body></s:Envelope>""".trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp16SoapParser.parseAnyResponseFromSoap(messageSoap)) {
            get { payload }.isA<SoapFault>().and {
                get { code }.and {
                    get { value.value }.isEqualTo("Receiver")
                    get { subCode.value.value }.isEqualTo("InternalError")
                }
                get { reason.text.value.value }.isEqualTo(
                    "An internal error occurred and the receiver is not able to complete the operation."
                )
            }
        }
    }

    @Test
    fun `should map message with missing relatesTo to soap`() {
        val message = """<?xml version="1.0" encoding="UTF-8"?>
            <SOAP-ENV:Envelope
            	xmlns:xsi="http://www.w3.org/1999/XMLSchema-instance"
            	xmlns:SOAP-ENC="http://www.w3.org/2003/05/soap-encoding"
            	xmlns:SOAP-ENV="http://www.w3.org/2003/05/soap-envelope" >
            	<SOAP-ENV:Header>
            		<Action xmlns="http://www.w3.org/2005/08/addressing">/GetConfigurationResponse</Action>
            		<MessageID
            			xmlns="http://www.w3.org/2005/08/addressing">urn:uuid:8608e9ad-9925-4d43-a356-49c12ff4dd31
            		</MessageID>
            		<chargeBoxIdentity
            			xmlns="http://www.w3.org/2005/08/addressing">00:13:F6:01:57:AD
            		</chargeBoxIdentity>
            	</SOAP-ENV:Header>
            	<SOAP-ENV:Body>
            		<getConfigurationResponse
            			xmlns="urn://Ocpp/Cp/2012/06/">
            			<configurationKey>
            				<key>And_Or</key>
            				<readonly>true</readonly>
            				<value>2</value>
            			</configurationKey>
            			<configurationKey>
            				<key>Access_control</key>
            				<readonly>true</readonly>
            				<value>2</value>
            			</configurationKey>
            			<configurationKey>
            				<key>Three-phase</key>
            				<readonly>1</readonly>
            				<value>0</value>
            			</configurationKey>
            			<configurationKey>
            				<key>Name</key>
            				<readonly>false</readonly>
            				<value>Park</value>
            			</configurationKey>
            			<configurationKey>
            				<key>Wh_per_impulse1</key>
            				<readonly>1</readonly>
            				<value>1</value>
            			</configurationKey>
            			<configurationKey>
            				<key>Wh_per_impulse2</key>
            				<readonly>1</readonly>
            				<value>1</value>
            			</configurationKey>
            			<configurationKey>
            				<key>NetworkFailure</key>
            				<readonly>0</readonly>
            				<value>0</value>
            			</configurationKey>
            			<configurationKey>
            				<key>SetPoint1</key>
            				<readonly>0</readonly>
            				<value>63</value>
            			</configurationKey>
            			<configurationKey>
            				<key>SetPoint2</key>
            				<readonly>0</readonly>
            				<value>63</value>
            			</configurationKey>
            			<configurationKey>
            				<key>HeartbeatInterval</key>
            				<readonly>0</readonly>
            				<value>1800</value>
            			</configurationKey>
            			<configurationKey>
            				<key>MeterValueSampleInterval</key>
            				<readonly>0</readonly>
            				<value>60</value>
            			</configurationKey>
            			<configurationKey>
            				<key>refcp</key>
            				<readonly>1</readonly>
            				<value>""</value>
            			</configurationKey>
            		</getConfigurationResponse>
            	</SOAP-ENV:Body>
            </SOAP-ENV:Envelope>""".trimSoap()

        println(ocpp16SoapParser.parseAnyResponseFromSoap(message))
        expectThat(ocpp16SoapParser.parseAnyResponseFromSoap(message)).and {
            get { action }.isEqualTo("GetConfiguration")
            get { relatesTo }.isEqualTo("missing-relatesTo")
            get { warnings }.isNotNull().isNotEmpty().get { this[0].code }
                .isEqualTo(ErrorDetailCode.MISSING_FIELD_REPLACED.value)
            get { payload }.isA<GetConfigurationResp>().and {
                get { configurationKey }.isNotNull().isNotEmpty().contains(
                    KeyValue("And_Or", true, "2"),
                    KeyValue("Three-phase", true, "0"),
                    KeyValue("Name", false, "Park"),
                    KeyValue("NetworkFailure", false, "0"),
                )
            }
        }
    }


    @Test
    fun `should map undeclared namespace to SoapFault`() {
        val request =
            """<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:a="http://www.w3.org/2005/08/addressing"
               xmlns="urn://Ocpp/Cs/2015/10/">
    <soap:Header>
        <chargeBoxIdentity>XXXXXXXX</chargeBoxIdentity>
        <a:Action>/StopTransaction</a:Action>
        <a:MessageID>urn:uuid:f81b9096-dccd-40ea-b16e-57f6086af321</a:MessageID>
        <a:From>
            <a:Address>http://XX.XX.XX.XX:80/</a:Address>
        </a:From>
        <a:To>http://xxxxx.fr/ocpp/v15s/</a:To>
    </soap:Header>
    <soap:Body>
        <stopTransactionRequest xmlns="urn://Ocpp/Cs/2012/06/">
            <transactionId xsi:nil="true"></transactionId>
            <idTag>AAAAAAAA</idTag>
            <timestamp>2022-09-20T19:02:11.000Z</timestamp>
            <meterStop>59</meterStop>
        </stopTransactionRequest>
    </soap:Body>
</soap:Envelope>""".trimSoap()

        expectThat(ocpp16SoapParser.parseAnyRequestFromSoap(request)) {
            get { chargingStationId }.isEqualTo("XXXXXXXX")
            get { messageId }.isEqualTo("urn:uuid:f81b9096-dccd-40ea-b16e-57f6086af321")
            get { action }.isEqualTo("Fault")
            get { payload }.isA<Fault>()
                .and {
                    get { errorCode }.isEqualTo(MessageErrorCode.PROTOCOL_ERROR.errorCode)
                    get { errorDescription }.isEqualTo(MessageErrorCode.PROTOCOL_ERROR.description)
                    get { errorDetails }.hasSize(4)
                        .and {
                            get { get(0) }
                                .and {
                                    get { code }.isEqualTo(MessageErrorCode.PROTOCOL_ERROR.errorCode)
                                    get { detail }.isEqualTo(
                                        "Sender's message does not comply with protocol specification."
                                    )
                                }
                            get { get(1) }
                                .and {
                                    get { code }.isEqualTo(ErrorDetailCode.STACKTRACE.value)
                                    get { detail }.contains(
                                        "JsonParseException: Undeclared namespace prefix"
                                    )
                                }
                            get { get(2) }
                                .and {
                                    get { code }.isEqualTo(ErrorDetailCode.PAYLOAD.value)
                                    get { detail }.isEqualTo(request)
                                }
                            get { get(3) }
                                .and {
                                    get { code }.isEqualTo(ErrorDetailCode.ACTION.value)
                                    get { detail }.isEqualTo("/StopTransaction")
                                }
                        }
                }
        }
    }
}

private fun String.trimSoap(): String =
    this.trimIndent()
        .replace("\n", "")
        .replace("\\s+".toRegex(), " ")
        .replace("> <", "><")
