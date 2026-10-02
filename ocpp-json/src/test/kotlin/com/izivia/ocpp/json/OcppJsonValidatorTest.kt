package com.izivia.ocpp.json

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.izivia.ocpp.utils.ErrorDetail
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.utils.fault.Fault
import com.networknt.schema.SpecVersion
import com.networknt.schema.ValidationMessage
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.contains
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull
import strikt.assertions.message

/* ocpp-json ships no schema, so every folder is missing on this module's classpath. */
class OcppJsonValidatorTest {

    @Test
    fun `names the missing schema when it is not on the classpath`() {
        val validator = OcppJsonValidator(SpecVersion.VersionFlag.V4, OcppSchemaFolder.OCPP_1_6)

        expectThrows<SchemaNotFoundException> {
            validator.isValidObject("Authorize", JsonNodeFactory.instance.objectNode(), "messageId")
        }
            .message.isNotNull().contains("ocpp16/Authorize.json")
    }

    @Test
    fun `reports the missing schema in the call error returned by a parser`() {
        // OcppJsonParser turns every failure into a CALL_ERROR: the schema name has to survive that.
        expectThat(SchemalessParser.parseAnyFromString("""[2,"messageId","Heartbeat",{}]"""))
            .and {
                // OCPP-J: a CALL_ERROR echoes the MessageId of the CALL it answers.
                get { msgId }.isEqualTo("messageId")
                get { errorCode }.isEqualTo(MessageErrorCode.INTERNAL_ERROR)
                get { payload }.isA<Fault>().get { errorDetails }
                    .contains(ErrorDetail(code = "schema", detail = "ocpp16/Heartbeat.json"))
            }
    }

    private object SchemalessParser : OcppJsonParser(
        mapper = jacksonObjectMapper(),
        ocppJsonValidator = OcppJsonValidator(SpecVersion.VersionFlag.V4, OcppSchemaFolder.OCPP_1_6)
    ) {
        override fun getRequestPayloadClass(action: String, errorHandler: (e: Exception) -> Throwable) =
            Map::class.java

        override fun getResponsePayloadClass(action: String, errorHandler: (e: Exception) -> Throwable) =
            Map::class.java

        override fun getActionFromClass(className: String) = className

        override fun validateJson(
            jsonMessage: JsonMessage<JsonNode>,
            errorsHandler: (errors: List<ValidationMessage>) -> Unit
        ) {
            ocppJsonValidator?.isValidObject(jsonMessage.action!!, jsonMessage.payload, jsonMessage.msgId)
                ?.let(errorsHandler)
        }
    }
}
