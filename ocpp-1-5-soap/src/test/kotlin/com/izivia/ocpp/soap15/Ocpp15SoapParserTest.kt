package com.izivia.ocpp.soap15

import com.izivia.ocpp.core15.Ocpp15ForcedFieldType
import com.izivia.ocpp.core15.model.authorize.AuthorizeReq
import com.izivia.ocpp.core15.model.authorize.AuthorizeResp
import com.izivia.ocpp.core15.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core15.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core15.model.bootnotification.enumeration.RegistrationStatus
import com.izivia.ocpp.core15.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core15.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core15.model.clearcache.enumeration.ClearCacheStatus
import com.izivia.ocpp.core15.model.common.IdTagInfo
import com.izivia.ocpp.core15.model.common.MeterValue
import com.izivia.ocpp.core15.model.common.SampledValue
import com.izivia.ocpp.core15.model.common.enumeration.*
import com.izivia.ocpp.core15.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core15.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core15.model.datatransfer.enumeration.DataTransferStatus
import com.izivia.ocpp.core15.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core15.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.core15.model.diagnosticsstatusnotification.enumeration.DiagnosticsStatus
import com.izivia.ocpp.core15.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core15.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core15.model.firmwarestatusnotification.enumeration.FirmwareStatus
import com.izivia.ocpp.core15.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core15.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core15.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core15.model.metervalues.MeterValuesResp
import com.izivia.ocpp.core15.model.remotestart.RemoteStartTransactionResp
import com.izivia.ocpp.core15.model.reset.ResetResp
import com.izivia.ocpp.core15.model.reset.enumeration.ResetStatus
import com.izivia.ocpp.core15.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core15.model.starttransaction.StartTransactionResp
import com.izivia.ocpp.core15.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core15.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core15.model.statusnotification.enumeration.ChargePointErrorCode
import com.izivia.ocpp.core15.model.statusnotification.enumeration.ChargePointStatus
import com.izivia.ocpp.core15.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core15.model.stoptransaction.StopTransactionResp
import com.izivia.ocpp.soap.RequestSoapMessage
import com.izivia.ocpp.soap.ResponseSoapMessage
import com.izivia.ocpp.soap.SoapFault
import com.izivia.ocpp.soap.parseRequestFromSoap
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

class Ocpp15SoapParserTest {

    companion object {
        private val ocpp15SoapParser = Ocpp15SoapParser()
    }

    @Test
    fun `should parse message to AuthorizeReq`() {
        val message =
            """
                <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://www.w3.org/2003/05/soap-envelope" xmlns:cs="urn://Ocpp/Cs/2012/06/"
                                   xmlns:wsa5="http://www.w3.org/2005/08/addressing"
                >
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
            """.trimIndent()

        expectThat(ocpp15SoapParser.parseRequestFromSoap<AuthorizeReq>(message)).and {
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
                <ns0:Envelope xmlns:ns0="http://www.w3.org/2003/05/soap-envelope" xmlns:ns1="http://www.w3.org/2005/08/addressing"
                              xmlns:ns2="urn://Ocpp/Cs/2012/06/">
                    <ns0:Header>
                        <ns1:To>http://example.fr/ocpp/v16s/</ns1:To>
                        <ns1:From>
                            <ns1:Address>http://172.40.94.5:8084/chargePointService/ocpp16soap/</ns1:Address>
                        </ns1:From>
                        <ns2:chargeBoxIdentity>XXXXXXXX</ns2:chargeBoxIdentity>
                        <ns1:MessageID>7230</ns1:MessageID>
                        <ns1:Action>/BootNotification</ns1:Action>
                    </ns0:Header>
                    <ns0:Body>
                        <ns2:bootNotificationRequest>
                            <ns2:chargePointVendor>Izivia</ns2:chargePointVendor>
                            <ns2:chargePointModel>XXXXXXXX</ns2:chargePointModel>
                            <ns2:chargePointSerialNumber>5aa469fd41344fe5a575368cd</ns2:chargePointSerialNumber>
                            <ns2:firmwareVersion>1.5.1.d723fd5</ns2:firmwareVersion>
                        </ns2:bootNotificationRequest>
                    </ns0:Body>
                </ns0:Envelope>
            """.trimIndent()

        expectThat(ocpp15SoapParser.parseRequestFromSoap<BootNotificationReq>(message).payload).and {
            get { chargePointModel }.isEqualTo("XXXXXXXX")
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
            """.trimIndent()

        expectThat(ocpp15SoapParser.parseRequestFromSoap<DataTransferReq>(message).payload).and {
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
             xmlns:ns2="urn://Ocpp/Cp/2012/06/">
             <ns0:Header>
                <ns1:From><ns1:Address>http://XXX.YYY.153.113:8080</ns1:Address></ns1:From>
                <ns1:To>http://XXX.YYY.153.113:8080</ns1:To>
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
        expectThat(ocpp15SoapParser.parseRequestFromSoap<DiagnosticsStatusNotificationReq>(message).payload).and {
            get { status }.isEqualTo(DiagnosticsStatus.Uploaded)
        }
    }

    @Test
    fun `should parse message to FirmwareStatusNotificationReq`() {
        val message =
            """
                <s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope" xmlns:cs="urn://Ocpp/Cs/2012/06/"
                               xmlns:wsa5="http://www.w3.org/2005/08/addressing"
                >
                    <s:Header>
                        <cs:chargeBoxIdentity s:mustUnderstand="true">XXXXXXXX</cs:chargeBoxIdentity>
                        <wsa5:From>
                            <wsa5:Address>http://XXXXXXXX:8085</wsa5:Address>
                        </wsa5:From>
                        <wsa5:MessageID>urn:uuid:8fe7ef88-0f2e-47f3-911d-dbd36b32f366</wsa5:MessageID>
                        <wsa5:ReplyTo s:mustUnderstand="true">
                            <wsa5:Address>http://www.w3.org/2005/08/addressing/anonymous</wsa5:Address>
                        </wsa5:ReplyTo>
                        <wsa5:To s:mustUnderstand="true">http://example.fr/ocpp/v16s/</wsa5:To>
                        <wsa5:Action s:mustUnderstand="true">/FirmwareStatusNotification</wsa5:Action>
                    </s:Header>
                    <s:Body>
                        <cs:firmwareStatusNotificationRequest>
                            <cs:status>Installed</cs:status>
                        </cs:firmwareStatusNotificationRequest>
                    </s:Body>
                </s:Envelope>
            """.trimIndent()

        expectThat(ocpp15SoapParser.parseRequestFromSoap<FirmwareStatusNotificationReq>(message).payload).and {
            get { status }.isEqualTo(FirmwareStatus.Installed)
        }
    }

    @Test
    fun `should parse message to HeartbeatReq`() {
        val message =
            """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2012/06/">CS1</chargeBoxIdentity>
                        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                            <wsa:Address>http://XXX.YYY.0.3:12800/ws</wsa:Address>
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
                        <heartbeatRequest xmlns="urn://Ocpp/Cs/2012/06/"/>
                    </S:Body>
                </S:Envelope>
            """.trimIndent()

        expectThat(ocpp15SoapParser.parseRequestFromSoap<HeartbeatReq>(message).payload)
    }

    @Test
    fun `should parse message to MeterValuesReq`() {
        val message =
            """
                <?xml version="1.0" encoding="UTF-8"?>
                            <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://www.w3.org/2003/05/soap-envelope"
                            xmlns:SOAP-ENC="http://www.w3.org/2003/05/soap-encoding"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:cp="urn://Ocpp/Cp/2012/06/"
                            xmlns:chan="http://schemas.microsoft.com/ws/2005/02/duplex"
                            xmlns:wsa5="http://www.w3.org/2005/08/addressing"
                            xmlns:cs="urn://Ocpp/Cs/2012/06/">
                    <SOAP-ENV:Header>
                        <cs:chargeBoxIdentity>TEST</cs:chargeBoxIdentity>
                        <wsa5:MessageID>789456</wsa5:MessageID>
                        <wsa5:From><wsa5:Address>http://XXX.YYY.153.200:8080</wsa5:Address></wsa5:From>
                        <wsa5:ReplyTo><wsa5:Address>http://www.w3.org/2005/08/addressing/anonymous</wsa5:Address></wsa5:ReplyTo>
                        <wsa5:To SOAP-ENV:mustUnderstand="true">http://XXX.YYY.154.177:8084/centralSystemService/ocpp15soap</wsa5:To>
                        <wsa5:Action SOAP-ENV:mustUnderstand="true">/MeterValues</wsa5:Action>
                    </SOAP-ENV:Header>
                    <SOAP-ENV:Body>
                        <cs:meterValuesRequest>
                            <cs:connectorId>1</cs:connectorId>
                            <cs:transactionId>15095</cs:transactionId>
                            <cs:values>
                                <cs:timestamp>2022-05-17T15:41:19.912Z</cs:timestamp>
                                <cs:value unit="Wh" location="Inlet" measurand="Energy.Active.Import.Register" format="Raw" context="Sample.Periodic">12563</cs:value>
                                <cs:value unit="Wh" location="Outlet" measurand="Energy.Active.Import.Register" format="Raw" context="Sample.Periodic">12563</cs:value>
                            </cs:values>
                            <cs:values>
                                <cs:timestamp>2022-05-17T15:42:19.912Z</cs:timestamp>
                                <cs:value unit="Wh" location="Outlet" measurand="Energy.Active.Import.Register" format="Raw" context="Sample.Periodic">12563</cs:value>
                            </cs:values>
                        </cs:meterValuesRequest>
                    </SOAP-ENV:Body>
                </SOAP-ENV:Envelope>
            """.trimIndent()

        expectThat(ocpp15SoapParser.parseRequestFromSoap<MeterValuesReq>(message).payload).and {
            get { connectorId }.isEqualTo(1)
            get { transactionId }.isEqualTo(15095)
            get { values }.isNotNull().hasSize(2).and {
                get { get(0) }.and {
                    get { timestamp }.isEqualTo(Instant.parse("2022-05-17T15:41:19.912Z"))
                    get { value }.hasSize(2).and {
                        get { get(0) }.and {
                            get { value }.isEqualTo("12563")
                            get { context }.isEqualTo(ReadingContext.SamplePeriodic)
                            get { location }.isEqualTo(Location.Inlet)
                            get { measurand }.isEqualTo(Measurand.EnergyActiveImportRegister)
                            get { unit }.isEqualTo(UnitOfMeasure.Wh)
                        }
                        get { get(1) }.and {
                            get { value }.isEqualTo("12563")
                            get { context }.isEqualTo(ReadingContext.SamplePeriodic)
                            get { location }.isEqualTo(Location.Outlet)
                            get { measurand }.isEqualTo(Measurand.EnergyActiveImportRegister)
                            get { unit }.isEqualTo(UnitOfMeasure.Wh)
                        }
                    }
                }
                get { get(1) }.and {
                    get { timestamp }.isEqualTo(Instant.parse("2022-05-17T15:42:19.912Z"))
                    get { value }.hasSize(1).first().and {
                        get { value }.isEqualTo("12563")
                        get { context }.isEqualTo(ReadingContext.SamplePeriodic)
                        get { location }.isEqualTo(Location.Outlet)
                        get { measurand }.isEqualTo(Measurand.EnergyActiveImportRegister)
                        get { unit }.isEqualTo(UnitOfMeasure.Wh)
                    }
                }
            }
        }
    }

    @Test
    fun `should parse message with no options on sample value to MeterValuesReq`() {
        val message =
            """
                <?xml version="1.0" encoding="UTF-8"?>
                            <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://www.w3.org/2003/05/soap-envelope"
                            xmlns:SOAP-ENC="http://www.w3.org/2003/05/soap-encoding"
                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:cp="urn://Ocpp/Cp/2012/06/"
                            xmlns:chan="http://schemas.microsoft.com/ws/2005/02/duplex"
                            xmlns:wsa5="http://www.w3.org/2005/08/addressing"
                            xmlns:cs="urn://Ocpp/Cs/2012/06/">
                    <SOAP-ENV:Header>
                        <cs:chargeBoxIdentity>TEST</cs:chargeBoxIdentity>
                        <wsa5:MessageID>789456</wsa5:MessageID>
                        <wsa5:From><wsa5:Address>http://XXX.YYY.153.200:8080</wsa5:Address></wsa5:From>
                        <wsa5:ReplyTo><wsa5:Address>http://www.w3.org/2005/08/addressing/anonymous</wsa5:Address></wsa5:ReplyTo>
                        <wsa5:To SOAP-ENV:mustUnderstand="true">http://XXX.YYY.154.177:8084/centralSystemService/ocpp15soap</wsa5:To>
                        <wsa5:Action SOAP-ENV:mustUnderstand="true">/MeterValues</wsa5:Action>
                    </SOAP-ENV:Header>
                    <SOAP-ENV:Body>
                        <cs:meterValuesRequest>
                            <cs:connectorId>1</cs:connectorId>
                            <cs:transactionId>15095</cs:transactionId>
                            <cs:values>
                                <cs:timestamp>2022-05-17T15:42:19.912Z</cs:timestamp>
                                <cs:value>valueWithNoParam</cs:value>
                            </cs:values>
                        </cs:meterValuesRequest>
                    </SOAP-ENV:Body>
                </SOAP-ENV:Envelope>
            """.trimIndent()

        expectThat(ocpp15SoapParser.parseRequestFromSoap<MeterValuesReq>(message).payload).and {
            get { values }.isNotNull().hasSize(1).and {
                get { get(0) }.and {
                    get { value }.hasSize(1).and {
                        get { get(0) }.and {
                            get { value }.isEqualTo("valueWithNoParam")

                            // defaults should be taken for params
                            get { context }.isEqualTo(ReadingContext.SamplePeriodic)
                            get { location }.isEqualTo(Location.Outlet)
                            get { measurand }.isEqualTo(Measurand.EnergyActiveImportRegister)
                            get { unit }.isEqualTo(UnitOfMeasure.Wh)
                        }
                    }
                }
            }
        }
    }

    @Test
    fun `should parse message to StartTransactionReq`() {
        val message =
            """
                <v:Envelope xmlns:v="http://www.w3.org/2003/05/soap-envelope" xmlns:a="http://www.w3.org/2005/08/addressing"
                            xmlns:o="urn://Ocpp/Cs/2012/06/">
                    <v:Header>
                        <o:chargeBoxIdentity>XXXXXXXX</o:chargeBoxIdentity>
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
                        <o:startTransactionRequest>
                            <o:connectorId>1</o:connectorId>
                            <o:idTag>AAAAAAAA</o:idTag>
                            <o:meterStart>18804500</o:meterStart>
                            <o:timestamp>2022-05-17T15:41:58.351Z</o:timestamp>
                        </o:startTransactionRequest>
                    </v:Body>
                </v:Envelope>
            """.trimIndent()

        expectThat(ocpp15SoapParser.parseRequestFromSoap<StartTransactionReq>(message).payload).and {
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
                <s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope" xmlns:cs="urn://Ocpp/Cs/2012/06/"
                               xmlns:wsa5="http://www.w3.org/2005/08/addressing"
                >
                    <s:Header>
                        <cs:chargeBoxIdentity s:mustUnderstand="true">IES20210510592612</cs:chargeBoxIdentity>
                        <wsa5:From>
                            <wsa5:Address>http://X.X.X.X:8085</wsa5:Address>
                        </wsa5:From>
                        <wsa5:MessageID>urn:uuid:70c76024-1281-471f-8578-ad51a2a88134</wsa5:MessageID>
                        <wsa5:ReplyTo s:mustUnderstand="true">
                            <wsa5:Address>http://www.w3.org/2005/08/addressing/anonymous</wsa5:Address>
                        </wsa5:ReplyTo>
                        <wsa5:To s:mustUnderstand="true">http://example.fr/ocpp/v16s</wsa5:To>
                        <wsa5:Action s:mustUnderstand="true">/StatusNotification</wsa5:Action>
                    </s:Header>
                    <s:Body>
                        <cs:statusNotificationRequest>
                            <cs:connectorId>1</cs:connectorId>
                            <cs:status>Available</cs:status>
                            <cs:errorCode>NoError</cs:errorCode>
                            <cs:info>No error.</cs:info>
                            <cs:timestamp>2022-05-17T15:41:59.486Z</cs:timestamp>
                            <cs:vendorErrorCode>0x0</cs:vendorErrorCode>
                        </cs:statusNotificationRequest>
                    </s:Body>
                </s:Envelope>
            """.trimIndent()

        expectThat(ocpp15SoapParser.parseRequestFromSoap<StatusNotificationReq>(message).payload).and {
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
                            <Address>http://XXX.YYY.0.102:8080</Address>
                        </From>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2012/06/">CS1</chargeBoxIdentity>
                    </SOAP-ENV:Header>
                    <SOAP-ENV:Body>
                        <stopTransactionRequest xmlns="urn://Ocpp/Cs/2012/06/">
                            <transactionId>16696</transactionId>
                            <idTag>AAAAAAAA</idTag>
                            <timestamp>2022-05-05T04:37:15Z</timestamp>
                            <meterStop>19224</meterStop>
                            <transactionData />
                        </stopTransactionRequest>
                    </SOAP-ENV:Body>
                </SOAP-ENV:Envelope>
            """.trimIndent()

        expectThat(ocpp15SoapParser.parseRequestFromSoap<StopTransactionReq>(message).payload).and {
            get { meterStop }.isEqualTo(19224)
            get { timestamp }.isEqualTo(Instant.parse("2022-05-05T04:37:15Z"))
            get { transactionId }.isEqualTo(16696)
            get { idTag }.isEqualTo("AAAAAAAA")
            // It could be great to have an example with transaction data
            get { transactionData }.isNotNull().hasSize(0)
        }
    }

    @Test
    fun `should not parse message to AuthorizeReq because it is not an Authorize`() {
        val message =
            """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2012/06/">CS1</chargeBoxIdentity>
                        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                            <wsa:Address>http://XXX.YYY.0.3:12800/ws</wsa:Address>
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
                        <heartbeatRequest xmlns="urn://Ocpp/Cs/2012/06/"/>
                    </S:Body>
                </S:Envelope>
            """.trimIndent()

        expectThrows<IllegalArgumentException> {
            ocpp15SoapParser.parseRequestFromSoap<AuthorizeReq>(message)
        }
    }

    @Test
    fun `should not parse message to BootNotificationReq because it is not a BootNotification`() {
        val message =
            """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2012/06/">CS1</chargeBoxIdentity>
                        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                            <wsa:Address>http://XXX.YYY.0.3:12800/ws</wsa:Address>
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
                        <heartbeatRequest xmlns="urn://Ocpp/Cs/2012/06/"/>
                    </S:Body>
                </S:Envelope>
            """.trimIndent()

        expectThrows<IllegalArgumentException> {
            ocpp15SoapParser.parseRequestFromSoap<BootNotificationReq>(message)
        }
    }

    @Test
    fun `should not parse message to DataTransferReq because it is not a DataTransfer`() {
        val message =
            """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2012/06/">CS1</chargeBoxIdentity>
                        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                            <wsa:Address>http://XXX.YYY.0.3:12800/ws</wsa:Address>
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
                        <heartbeatRequest xmlns="urn://Ocpp/Cs/2012/06/"/>
                    </S:Body>
                </S:Envelope>
            """.trimIndent()

        expectThrows<IllegalArgumentException> {
            ocpp15SoapParser.parseRequestFromSoap<DataTransferReq>(message)
        }
    }

    @Test
    fun `should not parse message to DiagnosticsStatusNotificationReq because it is not a DiagnosticsStatusNotification`() {
        val message =
            """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2012/06/">CS1</chargeBoxIdentity>
                        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                            <wsa:Address>http://XXX.YYY.0.3:12800/ws</wsa:Address>
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
                        <heartbeatRequest xmlns="urn://Ocpp/Cs/2012/06/"/>
                    </S:Body>
                </S:Envelope>
            """.trimIndent()

        expectThrows<IllegalArgumentException> {
            ocpp15SoapParser.parseRequestFromSoap<DiagnosticsStatusNotificationReq>(message)
        }
    }

    @Test
    fun `should not parse message to FirmwareStatusNotificationReq because it is not a FirmwareStatusNotification`() {
        val message =
            """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2012/06/">CS1</chargeBoxIdentity>
                        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                            <wsa:Address>http://XXX.YYY.0.3:12800/ws</wsa:Address>
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
                        <heartbeatRequest xmlns="urn://Ocpp/Cs/2012/06/"/>
                    </S:Body>
                </S:Envelope>
            """.trimIndent()

        expectThrows<IllegalArgumentException> {
            ocpp15SoapParser.parseRequestFromSoap<FirmwareStatusNotificationReq>(message)
        }
    }

    @Test
    fun `should not parse message to HeartbeatReq because it is not an Heartbeat`() {
        val message =
            """
                <s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope" xmlns:cs="urn://Ocpp/Cs/2012/06/"
                   xmlns:wsa5="http://www.w3.org/2005/08/addressing"
                >
                    <s:Header>
                        <cs:chargeBoxIdentity s:mustUnderstand="true">IES20210511714911</cs:chargeBoxIdentity>
                        <wsa5:From>
                            <wsa5:Address>http://X.X.X.X:8085</wsa5:Address>
                        </wsa5:From>
                        <wsa5:MessageID>urn:uuid:8fe7ef88-0f2e-47f3-911d-dbd36b32f366</wsa5:MessageID>
                        <wsa5:ReplyTo s:mustUnderstand="true">
                            <wsa5:Address>http://www.w3.org/2005/08/addressing/anonymous</wsa5:Address>
                        </wsa5:ReplyTo>
                        <wsa5:To s:mustUnderstand="true">http://example.fr/ocpp/v16s/</wsa5:To>
                        <wsa5:Action s:mustUnderstand="true">/FirmwareStatusNotification</wsa5:Action>
                    </s:Header>
                    <s:Body>
                        <cs:firmwareStatusNotificationRequest>
                            <cs:status>Installed</cs:status>
                        </cs:firmwareStatusNotificationRequest>
                    </s:Body>
                </s:Envelope>
            """.trimIndent()

        expectThrows<IllegalArgumentException> {
            ocpp15SoapParser.parseRequestFromSoap<HeartbeatReq>(message)
        }
    }

    @Test
    fun `should not parse message to MeterValuesReq because it is not a MeterValues`() {
        val message =
            """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2012/06/">CS1</chargeBoxIdentity>
                        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                            <wsa:Address>http://XXX.YYY.0.3:12800/ws</wsa:Address>
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
                        <heartbeatRequest xmlns="urn://Ocpp/Cs/2012/06/"/>
                    </S:Body>
                </S:Envelope>
            """.trimIndent()

        expectThrows<IllegalArgumentException> {
            ocpp15SoapParser.parseRequestFromSoap<MeterValuesReq>(message)
        }
    }

    @Test
    fun `should not parse message to StartTransactionReq because it is not a StartTransaction`() {
        val message =
            """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2012/06/">CS1</chargeBoxIdentity>
                        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                            <wsa:Address>http://XXX.YYY.0.3:12800/ws</wsa:Address>
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
                        <heartbeatRequest xmlns="urn://Ocpp/Cs/2012/06/"/>
                    </S:Body>
                </S:Envelope>
            """.trimIndent()

        expectThrows<IllegalArgumentException> {
            ocpp15SoapParser.parseRequestFromSoap<StartTransactionReq>(message)
        }
    }

    @Test
    fun `should not parse message to StatusNotificationReq because it is not a StatusNotification`() {
        val message =
            """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2012/06/">CS1</chargeBoxIdentity>
                        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                            <wsa:Address>http://XXX.YYY.0.3:12800/ws</wsa:Address>
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
                        <heartbeatRequest xmlns="urn://Ocpp/Cs/2012/06/"/>
                    </S:Body>
                </S:Envelope>
            """.trimIndent()

        expectThrows<IllegalArgumentException> {
            ocpp15SoapParser.parseRequestFromSoap<StatusNotificationReq>(message)
        }
    }

    @Test
    fun `should not parse message to StopTransactionReq because it is not a StopTransaction`() {
        val message =
            """
                <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                    <S:Header>
                        <chargeBoxIdentity xmlns="urn://Ocpp/Cs/2012/06/">CS1</chargeBoxIdentity>
                        <wsa:From xmlns:wsa="http://www.w3.org/2005/08/addressing">
                            <wsa:Address>http://XXX.YYY.0.3:12800/ws</wsa:Address>
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
                        <heartbeatRequest xmlns="urn://Ocpp/Cs/2012/06/"/>
                    </S:Body>
                </S:Envelope>
            """.trimIndent()

        expectThrows<IllegalArgumentException> {
            ocpp15SoapParser.parseRequestFromSoap<StopTransactionReq>(message)
        }
    }

    @Test
    fun `should map AuthorizeReq to soap`() {
        val msgId = UUID.randomUUID().toString()
        val request = AuthorizeReq(
            idTag = "AAAAAAAA"
        )

        val messageSoap = ocpp15SoapParser.mapRequestToSoap(
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
                xmlns:o="urn://Ocpp/Cs/2012/06/"><s:Header>
<a:MessageID>$msgId</a:MessageID><a:Action>/Authorize</a:Action>
<o:chargeBoxIdentity>CS1</o:chargeBoxIdentity><a:From><a:Address>source</a:Address>
</a:From><a:To>destination</a:To></s:Header>
<s:Body><o:authorizeRequest><o:idTag>AAAAAAAA</o:idTag></o:authorizeRequest></s:Body></s:Envelope>
            """.trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp15SoapParser.parseAnyRequestFromSoap(messageSoap)) {
            get { payload }
                .isA<AuthorizeReq>()
                .and {
                    get { idTag }.isEqualTo("AAAAAAAA")
                }
            get { messageId }.isEqualTo(msgId)
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

        val messageSoap = ocpp15SoapParser.mapResponseToSoap(
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
                xmlns:o="urn://Ocpp/Cs/2012/06/"><s:Header>
<a:MessageID>urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</a:MessageID><a:Action>/AuthorizeResponse</a:Action>
<a:From><a:Address>source</a:Address></a:From>
<a:To>destination</a:To>
<a:RelatesTo>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:RelatesTo>
</s:Header><s:Body><o:authorizeResponse><o:idTagInfo><o:expiryDate>2022-05-16T15:42:05.128Z</o:expiryDate>
<o:status>Accepted</o:status></o:idTagInfo></o:authorizeResponse></s:Body></s:Envelope>""".trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp15SoapParser.parseAnyResponseFromSoap(messageSoap)) {
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
            chargePointVendor = "YYYYYYYY",
            chargePointModel = "XXXXXXXX",
            chargePointSerialNumber = "5aa469fd41344fe5a575368cd",
            firmwareVersion = "1.5.1.d723fd5"
        )

        val messageSoap = ocpp15SoapParser.mapRequestToSoap(
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
                xmlns:o="urn://Ocpp/Cs/2012/06/"><s:Header>
<a:MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:MessageID><a:Action>/BootNotification</a:Action>
<o:chargeBoxIdentity>CS1</o:chargeBoxIdentity>
<a:From><a:Address>source</a:Address></a:From>
<a:To>destination</a:To></s:Header><s:Body><o:bootNotificationRequest>
<o:chargePointModel>XXXXXXXX</o:chargePointModel>
<o:chargePointSerialNumber>5aa469fd41344fe5a575368cd</o:chargePointSerialNumber>
<o:chargePointVendor>YYYYYYYY</o:chargePointVendor><o:firmwareVersion>1.5.1.d723fd5</o:firmwareVersion>
</o:bootNotificationRequest></s:Body></s:Envelope>""".trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp15SoapParser.parseAnyRequestFromSoap(messageSoap)) {
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
            currentTime = Instant.parse("2022-05-17T15:43:08.025Z"),
            heartbeatInterval = 1800
        )

        val messageSoap = ocpp15SoapParser.mapResponseToSoap(
            ResponseSoapMessage(
                messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                action = "bootNotification",
                payload = response,
                from = "source",
                to = "destination"
            )
        )

        expectThat(messageSoap) {
            get { this }.contains("xmlns:o=\"urn://Ocpp/Cs/2012/06/\"")
            get { this }.contains("<a:Action>/BootNotificationResponse</a:Action>")
            get { this }.contains("<a:MessageID>urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</a:MessageID>")
            get { this }.contains("<a:RelatesTo>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:RelatesTo>")
        }
        expectThat(ocpp15SoapParser.parseAnyResponseFromSoap(messageSoap)) {
            get { payload }.isA<BootNotificationResp>().and {
                get { status }.isEqualTo(RegistrationStatus.Rejected)
                get { currentTime }.isEqualTo(Instant.parse("2022-05-17T15:43:08.025Z"))
                get { heartbeatInterval }.isEqualTo(1800)
            }
        }
    }

    @Test
    fun `should map DataTransferReq to soap`() {
        val msgId = UUID.randomUUID().toString()
        val request = DataTransferReq(
            vendorId = "XXXXXXXX",
            messageId = "Detection loop",
            data = "{\"connectorId\":10,\"name\":\"Vehicle\",\"state\":\"1\",\"timestamp\":\"2022-05-17T15:42:03Z:\"}"
        )

        val messageSoap = ocpp15SoapParser.mapRequestToSoap(
            RequestSoapMessage(
                messageId = msgId,
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
                xmlns:o="urn://Ocpp/Cs/2012/06/"><s:Header>
<a:MessageID>$msgId</a:MessageID><a:Action>/DataTransfer</a:Action>
<o:chargeBoxIdentity>CS1</o:chargeBoxIdentity><a:From><a:Address>source</a:Address></a:From>
<a:To>destination</a:To></s:Header><s:Body><o:dataTransferRequest>
<o:vendorId>XXXXXXXX</o:vendorId><o:messageId>Detection loop</o:messageId>
<o:data>{"connectorId":10,"name":"Vehicle","state":"1","timestamp":"2022-05-17T15:42:03Z:"}</o:data>
</o:dataTransferRequest></s:Body></s:Envelope>""".trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp15SoapParser.parseAnyRequestFromSoap(messageSoap)) {
            get { payload }
                .isA<DataTransferReq>()
                .and {
                    get { vendorId }.isEqualTo("XXXXXXXX")
                    get { messageId }.isEqualTo("Detection loop")
                    get { data }
                        .isEqualTo(
                            "{\"connectorId\":10,\"name\":\"Vehicle\",\"state\":\"1\"," +
                                "\"timestamp\":\"2022-05-17T15:42:03Z:\"}"
                        )
                }
            get { messageId }.isEqualTo(msgId)
        }
    }

    @Test
    fun `should map DataTransferResp to soap`() {
        val response = DataTransferResp(
            status = DataTransferStatus.Accepted
        )

        val messageSoap = ocpp15SoapParser.mapResponseToSoap(
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
            get { this }.contains("xmlns:o=\"urn://Ocpp/Cs/2012/06/\"")
            get { this }.contains("<a:Action>/DataTransferResponse</a:Action>")
            get { this }.contains("<a:MessageID>urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</a:MessageID>")
            get { this }.contains("<a:RelatesTo>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:RelatesTo>")
        }
        expectThat(ocpp15SoapParser.parseAnyResponseFromSoap(messageSoap)) {
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

        val reqSoap = ocpp15SoapParser.mapRequestToSoap(
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
            get { this }.contains("xmlns:o=\"urn://Ocpp/Cs/2012/06/\"")
            get { this }.contains("<a:Action>/DiagnosticsStatusNotification</a:Action>")
            get { this }.contains("<a:MessageID>$messageId</a:MessageID>")
        }
        expectThat(ocpp15SoapParser.parseAnyRequestFromSoap(reqSoap)) {
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
        val messageSoap = ocpp15SoapParser.mapResponseToSoap(
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
            get { this }.contains("xmlns:o=\"urn://Ocpp/Cs/2012/06/\"")
            get { this }.contains("<a:Action>/DiagnosticsStatusNotificationResponse</a:Action>")
            get { this }.contains("<a:MessageID>$messageId</a:MessageID>")
            get { this }.contains("<a:RelatesTo>$relateTo</a:RelatesTo>")
        }
        expectThat(ocpp15SoapParser.parseAnyResponseFromSoap(messageSoap)) {
            get { payload }.isA<DiagnosticsStatusNotificationResp>()
        }
    }

    @Test
    fun `should map FirmwareStatusNotificationReq to soap`() {
        val response = FirmwareStatusNotificationReq(
            status = FirmwareStatus.Installed
        )

        val messageSoap = ocpp15SoapParser.mapRequestToSoap(
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
                xmlns:o="urn://Ocpp/Cs/2012/06/"><s:Header>
<a:MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:MessageID>
<a:Action>/FirmwareStatusNotification</a:Action><o:chargeBoxIdentity>CS1</o:chargeBoxIdentity><a:From>
<a:Address>source</a:Address></a:From><a:To>destination</a:To>
</s:Header><s:Body><o:firmwareStatusNotificationRequest><o:status>Installed</o:status>
</o:firmwareStatusNotificationRequest></s:Body></s:Envelope>""".trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp15SoapParser.parseAnyRequestFromSoap(messageSoap)) {
            get { payload }.isA<FirmwareStatusNotificationReq>().and {
                get { status }.isEqualTo(FirmwareStatus.Installed)
            }
        }
    }

    @Test
    fun `should map FirmwareStatusNotificationResp to soap`() {
        val response = FirmwareStatusNotificationResp()

        val messageSoap = ocpp15SoapParser.mapResponseToSoap(
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
                xmlns:o="urn://Ocpp/Cs/2012/06/"><s:Header>
<a:MessageID>urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</a:MessageID>
<a:Action>/FirmwareStatusNotificationResponse</a:Action><a:From><a:Address>source</a:Address></a:From>
<a:To>destination</a:To>
<a:RelatesTo>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:RelatesTo></s:Header><s:Body>
<o:firmwareStatusNotificationResponse/></s:Body></s:Envelope>""".trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp15SoapParser.parseAnyResponseFromSoap(messageSoap)) {
            get { payload }.isA<FirmwareStatusNotificationResp>()
        }
    }

    @Test
    fun `should map HeartbeatReq to soap`() {
        val response = HeartbeatReq()

        val messageSoap = ocpp15SoapParser.mapRequestToSoap(
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
                xmlns:o="urn://Ocpp/Cs/2012/06/"><s:Header>
<a:MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:MessageID><a:Action>/Heartbeat</a:Action>
<o:chargeBoxIdentity>CS1</o:chargeBoxIdentity><a:From><a:Address>source</a:Address></a:From>
<a:To>destination</a:To></s:Header><s:Body><o:heartbeatRequest/></s:Body></s:Envelope>""".trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp15SoapParser.parseAnyRequestFromSoap(messageSoap)) {
            get { payload }.isA<HeartbeatReq>()
        }
    }

    @Test
    fun `should map HeartbeatResp to soap`() {
        val response = HeartbeatResp(
            currentTime = Instant.parse("2022-05-17T15:42:00.503Z")
        )

        val messageSoap = ocpp15SoapParser.mapResponseToSoap(
            ResponseSoapMessage(
                messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                action = "Heartbeat",
                payload = response,
                from = "source",
                to = "destination"
            )
        )

        expectThat(messageSoap) {
            get { this }.contains("xmlns:o=\"urn://Ocpp/Cs/2012/06/\"")
            get { this }.contains("<a:Action>/HeartbeatResponse</a:Action>")
            get { this }.contains("<a:MessageID>urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</a:MessageID>")
            get { this }.contains("<a:RelatesTo>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:RelatesTo>")
        }
        expectThat(ocpp15SoapParser.parseAnyResponseFromSoap(messageSoap)) {
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
            values = listOf(
                MeterValue(
                    timestamp = Instant.parse("2022-05-17T15:41:19.912Z"),
                    value = listOf(
                        SampledValue(
                            value = "15213716",
                            context = ReadingContext.SamplePeriodic,
                            location = Location.Inlet,
                            measurand = Measurand.EnergyActiveImportRegister,
                            unit = UnitOfMeasure.Wh
                        )
                    )
                )
            )
        )

        val messageSoap = ocpp15SoapParser.mapRequestToSoap(
            RequestSoapMessage(
                messageId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                chargingStationId = "CS1",
                action = "MeterValues",
                from = "source",
                to = "destination",
                payload = response
            )
        )

        val expectedEnvelope =
            """<s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope"
                xmlns:a="http://www.w3.org/2005/08/addressing"
                xmlns:o="urn://Ocpp/Cs/2012/06/"><s:Header>
<a:MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:MessageID><a:Action>/MeterValues</a:Action>
<o:chargeBoxIdentity>CS1</o:chargeBoxIdentity><a:From><a:Address>source</a:Address></a:From><a:To>destination</a:To>
</s:Header><s:Body><o:meterValuesRequest><o:connectorId>1</o:connectorId>
<o:values><o:timestamp>2022-05-17T15:41:19.912Z</o:timestamp>
<o:value context="Sample.Periodic" format="Raw" measurand=
"Energy.Active.Import.Register" location="Inlet" unit="Wh">15213716</o:value>
</o:values><o:transactionId>15917</o:transactionId></o:meterValuesRequest></s:Body></s:Envelope>""".trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp15SoapParser.parseAnyRequestFromSoap(messageSoap)) {
            get { payload }.isA<MeterValuesReq>().and {
                get { connectorId }.isEqualTo(1)
                get { transactionId }.isEqualTo(15917)
                get { values }.isNotNull().hasSize(1).first().and {
                    get { timestamp }.isEqualTo(Instant.parse("2022-05-17T15:41:19.912Z"))
                    get { value }.hasSize(1).first().and {
                        get { value }.isEqualTo("15213716")
                        get { context }.isEqualTo(ReadingContext.SamplePeriodic)
                        get { location }.isEqualTo(Location.Inlet)
                        get { measurand }.isEqualTo(Measurand.EnergyActiveImportRegister)
                        get { unit }.isEqualTo(UnitOfMeasure.Wh)
                    }
                }
            }
        }
    }

    @Test
    fun `should map MeterValuesResp to soap`() {
        val response = MeterValuesResp()

        val messageSoap = ocpp15SoapParser.mapResponseToSoap(
            ResponseSoapMessage(
                messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                action = "MeterValues",
                payload = response,
                from = "source",
                to = "destination"
            )
        )

        expectThat(messageSoap) {
            get { this }.contains("xmlns:o=\"urn://Ocpp/Cs/2012/06/\"")
            get { this }.contains("<a:Action>/MeterValuesResponse</a:Action>")
            get { this }.contains("<a:MessageID>urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</a:MessageID>")
            get { this }.contains("<a:RelatesTo>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:RelatesTo>")
        }
        expectThat(ocpp15SoapParser.parseAnyResponseFromSoap(messageSoap)) {
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

        val messageSoap = ocpp15SoapParser.mapRequestToSoap(
            RequestSoapMessage(
                messageId = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                chargingStationId = "CS1",
                action = "StartTransaction",
                from = "source",
                to = "destination",
                payload = response
            )
        )

        val expectedEnvelope =
            """<s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope"
                xmlns:a="http://www.w3.org/2005/08/addressing"
                xmlns:o="urn://Ocpp/Cs/2012/06/"><s:Header>
<a:MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:MessageID><a:Action>/StartTransaction</a:Action>
<o:chargeBoxIdentity>CS1</o:chargeBoxIdentity><a:From><a:Address>source</a:Address></a:From><a:To>destination</a:To>
</s:Header><s:Body><o:startTransactionRequest><o:connectorId>1</o:connectorId><o:idTag>AAAAAAAA</o:idTag>
<o:meterStart>18804500</o:meterStart><o:timestamp>2022-05-17T15:41:58.351Z</o:timestamp></o:startTransactionRequest>
</s:Body></s:Envelope>""".trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp15SoapParser.parseAnyRequestFromSoap(messageSoap)) {
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

        val messageSoap = ocpp15SoapParser.mapResponseToSoap(
            ResponseSoapMessage(
                messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                action = "StartTransaction",
                payload = response,
                from = "source",
                to = "destination"
            )
        )

        expectThat(messageSoap) {
            get { this }.contains("xmlns:o=\"urn://Ocpp/Cs/2012/06/\"")
            get { this }.contains("<a:Action>/StartTransactionResponse</a:Action>")
            get { this }.contains("<a:MessageID>urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</a:MessageID>")
            get { this }.contains("<a:RelatesTo>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:RelatesTo>")
        }
        expectThat(ocpp15SoapParser.parseAnyResponseFromSoap(messageSoap)) {
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

        val messageSoap = ocpp15SoapParser.mapRequestToSoap(
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
                xmlns:o="urn://Ocpp/Cs/2012/06/"><s:Header>
<a:MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:MessageID><a:Action>/StatusNotification</a:Action>
<o:chargeBoxIdentity>CS1</o:chargeBoxIdentity><a:From><a:Address>source</a:Address></a:From><a:To>destination</a:To>
</s:Header><s:Body><o:statusNotificationRequest><o:connectorId>1</o:connectorId><o:errorCode>NoError</o:errorCode>
<o:info>No error.</o:info><o:status>Available</o:status><o:timestamp>2022-05-17T15:41:59.486Z</o:timestamp>
<o:vendorErrorCode>0x0</o:vendorErrorCode></o:statusNotificationRequest></s:Body></s:Envelope>""".trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp15SoapParser.parseAnyRequestFromSoap(messageSoap)) {
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

        val messageSoap = ocpp15SoapParser.mapResponseToSoap(
            ResponseSoapMessage(
                messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                action = "StatusNotification",
                payload = response,
                from = "source",
                to = "destination"
            )
        )

        expectThat(messageSoap) {
            get { this }.contains("xmlns:o=\"urn://Ocpp/Cs/2012/06/\"")
            get { this }.contains("<a:Action>/StatusNotificationResponse</a:Action>")
            get { this }.contains("<a:MessageID>urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</a:MessageID>")
            get { this }.contains("<a:RelatesTo>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:RelatesTo>")
        }
        expectThat(ocpp15SoapParser.parseAnyResponseFromSoap(messageSoap)) {
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

        val messageSoap = ocpp15SoapParser.mapRequestToSoap(
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
                xmlns:o="urn://Ocpp/Cs/2012/06/"><s:Header>
<a:MessageID>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:MessageID>
<a:Action>/StopTransaction</a:Action><o:chargeBoxIdentity>CS1</o:chargeBoxIdentity>
<a:From><a:Address>source</a:Address></a:From><a:To>destination</a:To>
</s:Header><s:Body><o:stopTransactionRequest><o:idTag>AAAAAAAA</o:idTag><o:meterStop>19224</o:meterStop>
<o:timestamp>2022-05-05T04:37:15Z</o:timestamp><o:transactionId>16696</o:transactionId></o:stopTransactionRequest>
</s:Body></s:Envelope>""".trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp15SoapParser.parseAnyRequestFromSoap(messageSoap)) {
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
        val response = StopTransactionResp(
            idTagInfo = IdTagInfo(
                status = AuthorizationStatus.Accepted,
                expiryDate = Instant.parse("2022-05-16T15:42:05.128Z")
            )
        )

        val messageSoap = ocpp15SoapParser.mapResponseToSoap(
            ResponseSoapMessage(
                messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                action = "StopTransaction",
                payload = response,
                from = null,
                to = "destination"
            )
        )

        expectThat(messageSoap) {
            get { this }.contains("xmlns:o=\"urn://Ocpp/Cs/2012/06/\"")
            get { this }.contains("<a:Action>/StopTransactionResponse</a:Action>")
            get { this }.contains("<a:MessageID>urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</a:MessageID>")
            get { this }.contains("<a:RelatesTo>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:RelatesTo>")
            get { this }.contains(
                "<a:From><a:Address>http://www.w3.org/2005/08/addressing/anonymous</a:Address></a:From>"
            )
            get { this }.contains(
                "<a:To>destination</a:To>"
            )
        }
        expectThat(ocpp15SoapParser.parseAnyResponseFromSoap(messageSoap)) {
            get { payload }.isA<StopTransactionResp>().and {
                get { idTagInfo }.isNotNull().and {
                    get { status }.isEqualTo(AuthorizationStatus.Accepted)
                    get { expiryDate }.isEqualTo(Instant.parse("2022-05-16T15:42:05.128Z"))
                }
            }
        }
    }

    @Test
    fun `should map ClearCacheReq to SOAP`() {
        val request = ClearCacheReq()

        val messageSoap = ocpp15SoapParser.mapRequestToSoap(
            RequestSoapMessage(
                messageId = "test",
                chargingStationId = "CS1",
                action = "ClearCache",
                from = "source",
                to = "destination",
                payload = request
            )
        )

        val expectedEnvelope =
            """<s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope" xmlns:a="http://www.w3.org/2005/08/addressing" xmlns:o="urn://Ocpp/Cp/2012/06/"><s:Header><a:MessageID>test</a:MessageID><a:Action>/ClearCache</a:Action><o:chargeBoxIdentity>CS1</o:chargeBoxIdentity><a:From><a:Address>source</a:Address></a:From><a:To>destination</a:To></s:Header><s:Body><o:clearCacheRequest/></s:Body></s:Envelope>""".trimSoap()

        expectThat(messageSoap).isEqualTo(expectedEnvelope)
        expectThat(ocpp15SoapParser.parseAnyRequestFromSoap(messageSoap)) {
            get { payload }.isA<ClearCacheReq>()
        }
    }

    @Test
    fun `should parse remote start SOAP response without header`() {
        val response = """<s:Envelope
                                xmlns:s="http://www.w3.org/2003/05/soap-envelope" xmlns:a="http://www.w3.org/2005/08/addressing"
                                xmlns:tns="urn://Ocpp/Cp/2012/06/"
                                xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd">
                            <s:Body>
                                <remoteStartTransactionResponse xmlns="urn://Ocpp/Cp/2012/06/">
                                    <status>Accepted</status>
                                </remoteStartTransactionResponse>
                            </s:Body>
                        </s:Envelope>"""

        val message = ocpp15SoapParser.parseAnyResponseFromSoap(response)

        expectThat(message).and {
            get { payload }.isA<RemoteStartTransactionResp>().and {
                get { status }.isEqualTo(RemoteStartStopStatus.Accepted)
            }
        }
    }

    @Test
    fun `should parse SOAP response with missing messageId to ClearCacheResp`() {
        val response = """<?xml version="1.0" encoding="UTF-8"?>
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://www.w3.org/2003/05/soap-envelope" xmlns:SOAP-ENC="http://www.w3.org/2003/05/soap-encoding" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:cp="urn://Ocpp/Cp/2012/06/" xmlns:chan="http://schemas.microsoft.com/ws/2005/02/duplex" xmlns:wsa5="http://www.w3.org/2005/08/addressing" xmlns:cs="urn://Ocpp/Cs/2012/06/"><SOAP-ENV:Header><cp:chargeBoxIdentity>00:13:F6:01:91:26</cp:chargeBoxIdentity><wsa5:RelatesTo>test</wsa5:RelatesTo><wsa5:To SOAP-ENV:mustUnderstand="true">http://192.168.100.158:10001/api/ocpp-15/soap/</wsa5:To><wsa5:Action SOAP-ENV:mustUnderstand="true">/ClearCacheResponse</wsa5:Action></SOAP-ENV:Header><SOAP-ENV:Body><cp:clearCacheResponse><cp:status>Accepted</cp:status></cp:clearCacheResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>"""

        val message = ocpp15SoapParser.parseAnyResponseFromSoap(response)

        expectThat(message).and {
            get { payload }.isA<ClearCacheResp>().and {
                get { status }.isEqualTo(ClearCacheStatus.Accepted)
            }
        }
    }

    @Test
    fun `should map SoapFault to soap`() {
        val response = SoapFault.internalError()
        val messageSoap = ocpp15SoapParser.mapResponseToSoap(
            ResponseSoapMessage(
                messageId = "urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b",
                relatesTo = "urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49",
                action = "StopTransaction",
                payload = response,
                from = "source",
                to = "destination"
            )
        )

        expectThat(messageSoap) {
            get { this }.contains("<a:Action>/StopTransactionResponse</a:Action>")
            get { this }.contains("<a:MessageID>urn:uuid:739faeb1-da7c-4a50-8b61-2f631057fc2b</a:MessageID>")
            get { this }.contains("<a:RelatesTo>urn:uuid:a7ef37c1-2ac6-4247-a3ad-8ed5905a5b49</a:RelatesTo>")
        }
        expectThat(ocpp15SoapParser.parseAnyResponseFromSoap(messageSoap)) {
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
    fun `should parse DataTransfert message with wrong data format to DataTransferReq`() {
        val parser = Ocpp15SoapParser(
            forcedFieldTypes = listOf(
                Ocpp15ForcedFieldType(
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
    fun `should map undeclared namespace to SoapFault`() {
        val request =
            """<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:a="http://www.w3.org/2005/08/addressing"
               xmlns="urn://Ocpp/Cs/2012/06/">
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

        expectThat(ocpp15SoapParser.parseAnyRequestFromSoap(request)) {
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

    @Test
    fun `should parse ResetResp (without message id) from soap`() {
        val response = """
            <?xml version="1.0" encoding="UTF-8"?>
            <SOAP-ENV:Envelope
                    xmlns:SOAP-ENV="http://www.w3.org/2003/05/soap-envelope" xmlns:cp="urn://Ocpp/Cp/2012/06/"
                    xmlns:wsa5="http://www.w3.org/2005/08/addressing">
                <SOAP-ENV:Header>
                    <cp:chargeBoxIdentity>XXXXX</cp:chargeBoxIdentity>
                    <wsa5:RelatesTo>318ab371-0e80-4bf8-8eee-f706995966f7</wsa5:RelatesTo>
                    <wsa5:To SOAP-ENV:mustUnderstand="true">
                        http://xxxxx
                    </wsa5:To>
                    <wsa5:Action SOAP-ENV:mustUnderstand="true">/ResetResponse</wsa5:Action>
                </SOAP-ENV:Header>
                <SOAP-ENV:Body>
                    <cp:resetResponse>
                        <cp:status>Accepted</cp:status>
                    </cp:resetResponse>
                </SOAP-ENV:Body>
            </SOAP-ENV:Envelope>
        """.trimIndent()

        expectThat(ocpp15SoapParser.parseAnyResponseFromSoap(response))
            .isA<ResponseSoapMessage<ResetResp>>()
            .get { payload.status }.isEqualTo(ResetStatus.Accepted)
    }
}

private fun String.trimSoap(): String =
    this.trimIndent()
        .replace("\n", "")
        .replace("\\s+".toRegex(), " ")
