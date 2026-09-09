package com.izivia.ocpp.json12

import com.izivia.ocpp.core12.Ocpp12ForcedFieldType
import com.izivia.ocpp.core12.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core12.model.bootnotification.enumeration.RegistrationStatus
import com.izivia.ocpp.core12.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.core12.model.common.enumeration.Actions
import com.izivia.ocpp.core12.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.utils.ActionTypeEnum
import com.izivia.ocpp.utils.ErrorDetailCode
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.utils.TypeConvertEnum
import com.izivia.ocpp.utils.fault.Fault
import com.networknt.schema.ValidatorTypeCode
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.*

class Ocpp12JsonParserErrorTest {

    companion object {
        val parser = Ocpp12JsonParser()
    }

    @Test
    fun `should parse Heartbeat request`() {
        val request = """[2,"messageId","Heartbeat",{}]"""

        expectThat(parser.parseAnyFromString(request))
            .get { action }.isEqualTo("Heartbeat")
    }

    @Test
    fun `should parse to Fault PROTOCOL_ERROR inconsistent request`() {
        val request = """[2,"messageId","StartTransaction",{"meterStart": 0,
            |"timestamp": "2022-08-03T11:00:01.916Z", "idTag": "idTag"}]
        """.trimMargin()

        expectThat(parser.parseAnyFromString(request))
            .and {
                get { action }.isEqualTo("Fault")
                get { errorCode }.isEqualTo(MessageErrorCode.PROTOCOL_ERROR)
                get { payload }.isA<Fault>()
                    .get { errorDetails }
                    .any {
                        get { code }.isEqualTo(ValidatorTypeCode.REQUIRED.errorCode)
                        get { detail }.contains("Validations error")
                    }
            }
    }

    @Test
    fun `should parse to Fault MESSAGE_TYPE_NOT_SUPPORTED inconsistent request`() {
        val request = """[15,"messageId","Heartbeat",{}]"""

        expectThat(parser.parseAnyFromString(request))
            .and {
                get { action }.isEqualTo("Fault")
                get { errorCode }.isEqualTo(MessageErrorCode.MESSAGE_TYPE_NOT_SUPPORTED)
                get { payload }.isA<Fault>()
                    .get { errorDetails }.hasSize(1)
                    .and {
                        get { get(0) }.and {
                            get { code }.isEqualTo(ErrorDetailCode.PAYLOAD.value)
                            get { detail }.isEqualTo(request)
                        }
                    }
            }
    }

    @Test
    fun `should parse to Fault NOT_IMPLEMENTED inconsistent request`() {
        // DataTransfer exists in OCPP 1.5 but not in 1.2.
        val request = """[2,"messageId","DataTransfer",{"vendorId":"vendor1"}]"""

        expectThat(parser.parseAnyFromString(request))
            .and {
                get { action }.isEqualTo("Fault")
                get { errorCode }.isEqualTo(MessageErrorCode.NOT_IMPLEMENTED)
                get { payload }.isA<Fault>()
                    .get { errorDetails }
                    .any {
                        get { code }.isEqualTo(ErrorDetailCode.ACTION.value)
                        get { detail }.isEqualTo("DataTransfer")
                    }
            }
    }

    @Test
    fun `should parse to Fault FORMAT_VIOLATION inconsistent request`() {
        val request = """{"Json": "Ok", "Ocpp": "not}"""

        expectThat(parser.parseAnyFromString(request))
            .and {
                get { action }.isEqualTo("Fault")
                get { errorCode }.isEqualTo(MessageErrorCode.FORMAT_VIOLATION)
                get { payload }.isA<Fault>()
                    .get { errorDetails }.hasSize(1)
                    .and {
                        get { get(0) }.and {
                            get { code }.isEqualTo(ErrorDetailCode.PAYLOAD.value)
                            get { detail }.isEqualTo(request)
                        }
                    }
            }
    }

    @Test
    fun `should parse to Fault missing usedClazz`() {
        val response = """[3,"messageId",{}]"""

        expectThat(parser.parseAnyFromString(response))
            .and {
                get { action }.isEqualTo("Fault")
                get { errorCode }.isEqualTo(MessageErrorCode.NOT_IMPLEMENTED)
                get { payload }.isA<Fault>()
                    .get { errorDetails }
                    .any {
                        get { code }.isEqualTo(ErrorDetailCode.ACTION.value)
                        get { detail }
                            .contains("class used to retrieve the response action is not defined")
                    }
            }
    }

    @Test
    fun `should parse to Fault BOOTNOT inconsistent request`() {
        val request = """[2,"messageId","BootNotification",
            {"chargePointModel": "testModel", "chargePointVendor": "testVendor",
            "chargePointSerialNumber":"1234567891011121314151617181920"}]
        """.trimMargin()

        expectThat(parser.parseAnyFromString(request))
            .and {
                get { action }.isEqualTo("Fault")
                get { errorCode }.isEqualTo(MessageErrorCode.PROTOCOL_ERROR)
                get { payload }.isA<Fault>()
                    .get { errorDetails }
                    .any {
                        get { code }.isEqualTo(ValidatorTypeCode.MAX_LENGTH.errorCode)
                        get { detail }.contains("Validations error")
                    }
            }
    }

    @Test
    fun `should parse to BootNotification on ignoring 1013 inconsistent request`() {
        val ocppIgnore1013 = Ocpp12JsonParser(ignoredValidationCodes = listOf(ValidatorTypeCode.MAX_LENGTH))

        val request = """[2,"messageId","BootNotification",
            {"chargePointModel": "testModel", "chargePointVendor": "testVendor",
            "chargePointSerialNumber":"1234567891011121314151617181920"}]
        """.trimMargin()

        expectThat(ocppIgnore1013.parseAnyFromString(request))
            .and {
                get { action }.isEqualTo("BootNotification")
                get { warnings }.isNotNull().hasSize(1)
            }
    }

    @Test
    fun `should parse to BootNotification on ignoring additional properties request`() {
        val ocppIgnore = Ocpp12JsonParser(ignoredValidationCodes = listOf(ValidatorTypeCode.ADDITIONAL_PROPERTIES))

        val request = """[2,"messageId","BootNotification",
            {"chargePointModel": "testModel", "chargePointVendor": "testVendor",
            "chargePointSerialNumber":"12345678910", "additional": "Properties"}]
        """.trimMargin()

        expectThat(ocppIgnore.parseAnyFromString(request))
            .and {
                get { action }.isEqualTo("BootNotification")
                get { warnings }.isNotNull().hasSize(1)
            }
    }

    /* The schemas must be narrower than the OCPP 1.5 ones: every payload below is valid in 1.5. */

    @Test
    fun `should reject the 1-5 only Reserved charge point status`() {
        val request = """[2,"messageId","StatusNotification",
            {"connectorId": 1, "status": "Reserved", "errorCode": "NoError"}]
        """.trimMargin()

        expectRejectedWith(request, ValidatorTypeCode.ENUM)
    }

    @Test
    fun `should reject the 1-5 only GroundFailure error code`() {
        val request = """[2,"messageId","StatusNotification",
            {"connectorId": 1, "status": "Available", "errorCode": "GroundFailure"}]
        """.trimMargin()

        expectRejectedWith(request, ValidatorTypeCode.ENUM)
    }

    @Test
    fun `should reject the 1-5 only statusNotification fields`() {
        val request = """[2,"messageId","StatusNotification",
            {"connectorId": 1, "status": "Available", "errorCode": "NoError",
            "timestamp": "2022-02-15T00:00:00.000Z", "info": "Charging", "vendorId": "vendor1"}]
        """.trimMargin()

        expectRejectedWith(request, ValidatorTypeCode.ADDITIONAL_PROPERTIES)
    }

    @Test
    fun `should reject a stopTransaction idTag longer than the 1-2 limit`() {
        // 18 characters: within the 1.5 limit of 20, over the 1.2 limit of 15.
        val overLimit = stopTransactionWith(idTag = "012345678901234567")
        val atLimit = stopTransactionWith(idTag = "012345678901234")

        expectRejectedWith(overLimit, ValidatorTypeCode.MAX_LENGTH)
        expectThat(parser.parseAnyFromString(atLimit)).get { action }.isEqualTo("StopTransaction")
    }

    @Test
    fun `should reject the 1-5 sampled value shape in meterValues`() {
        val request = """[2,"messageId","MeterValues",
            {"connectorId": 1, "values": [{"timestamp": "2022-02-15T00:00:00.000Z",
            "value": [{"value": "123456789", "unit": "Wh"}]}]}]
        """.trimMargin()

        expectRejectedWith(request, ValidatorTypeCode.TYPE)
    }

    @Test
    fun `should accept a bootNotification response carrying only a status`() {
        // currentTime and heartbeatInterval are mandatory in 1.5 but optional in 1.2: this fails if the
        // 1.5 schema is picked up from the classpath instead of the 1.2 one.
        val response = """[3,"messageId",{"status":"Accepted"}]"""

        expectThat(parser.parseAnyFromJson<BootNotificationResp>(response))
            .and {
                get { action }.isEqualTo("bootNotification")
                get { payload }.isA<BootNotificationResp>()
                    .and {
                        get { status }.isEqualTo(RegistrationStatus.Accepted)
                        get { currentTime }.isNull()
                        get { heartbeatInterval }.isNull()
                    }
            }
    }

    @Test
    fun `should skip validation when it is disabled`() {
        // Carries the 1.5-only fields, which the 1.2 schema rejects but Jackson simply ignores.
        val request = """[2,"messageId","StatusNotification",
            {"connectorId": 1, "status": "Available", "errorCode": "NoError",
            "timestamp": "2022-02-15T00:00:00.000Z", "info": "Charging"}]
        """.trimMargin()

        expectThat(parser.parseAnyFromString(request)).get { action }.isEqualTo("Fault")
        expectThat(Ocpp12JsonParser(enableValidation = false).parseAnyFromString(request))
            .and {
                get { action }.isEqualTo("StatusNotification")
                get { payload }.isA<StatusNotificationReq>().get { connectorId }.isEqualTo(1)
            }
    }

    @Test
    fun `should parse a changeConfiguration with a non string value`() {
        val ocppParser = Ocpp12JsonParser(
            enableValidation = false,
            forcedFieldTypes = listOf(
                Ocpp12ForcedFieldType(
                    action = Actions.CHANGECONFIGURATION,
                    actionType = ActionTypeEnum.REQUEST,
                    fieldPath = "value",
                    typeRequested = TypeConvertEnum.STRING
                )
            )
        )

        val request = """[2,"messageId","ChangeConfiguration",{"key":"HeartBeatInterval","value":1800}]"""

        expectThat(ocppParser.parseAnyFromString(request)).and {
            get { action }.isEqualTo(Actions.CHANGECONFIGURATION.camelCase())
            get { payload }.isA<ChangeConfigurationReq>().get { value }.isEqualTo("1800")
            get { warnings }
                .isNotNull()
                .hasSize(1)
                .and {
                    get { get(0) }.and {
                        get { code }.isEqualTo(ErrorDetailCode.CONVERT_FIELD_REPLACED.value)
                        get { detail }.contains("[value] converted to STRING")
                    }
                }
        }
    }

    private fun stopTransactionWith(idTag: String) =
        """[2,"messageId","StopTransaction",{"transactionId": 1, "idTag": "$idTag",
            "timestamp": "2022-02-15T00:00:00.000Z", "meterStop": 1000}]
        """.trimMargin()

    private fun expectRejectedWith(request: String, expectedCode: ValidatorTypeCode) {
        expectThat(parser.parseAnyFromString(request))
            .and {
                get { action }.isEqualTo("Fault")
                get { errorCode }.isEqualTo(MessageErrorCode.PROTOCOL_ERROR)
                get { payload }.isA<Fault>()
                    .get { errorDetails }
                    .any {
                        get { code }.isEqualTo(expectedCode.errorCode)
                        get { detail }.contains("Validations error")
                    }
            }
    }
}
