package com.izivia.ocpp.json12

import com.fasterxml.jackson.databind.JsonNode
import com.izivia.ocpp.core12.Ocpp12ForcedFieldType
import com.izivia.ocpp.core12.Ocpp12IgnoredNullRestriction
import com.izivia.ocpp.core12.model.common.enumeration.Actions
import com.izivia.ocpp.json.JsonMessage
import com.izivia.ocpp.json.JsonMessageType
import com.izivia.ocpp.json.OcppJsonParser
import com.izivia.ocpp.json.OcppJsonValidator
import com.izivia.ocpp.utils.MessageTypeException
import com.networknt.schema.SpecVersion
import com.networknt.schema.ValidationMessage
import com.networknt.schema.ValidatorTypeCode

class Ocpp12JsonParser(
    override val ignoredNullRestrictions: List<Ocpp12IgnoredNullRestriction>? = null,
    override val ignoredValidationCodes: List<ValidatorTypeCode>? = null,
    override val forcedFieldTypes: List<Ocpp12ForcedFieldType>? = null,
    enableValidation: Boolean = true
) :
    OcppJsonParser(
        mapper = Ocpp12JsonObjectMapper,
        forcedFieldTypes = forcedFieldTypes,
        ignoredValidationCodes = ignoredValidationCodes,
        ignoredNullRestrictions = ignoredNullRestrictions,
        // OcppJsonValidator resolves schemas by name off the classpath, where 1.5, 1.6 and 2.0
        // already ship colliding names, so the 1.2 schemas sit in their own resource folder.
        ocppJsonValidator = OcppJsonValidator(SpecVersion.VersionFlag.V4, SCHEMA_FOLDER)
            .takeIf { enableValidation }
    ) {

    override fun getRequestPayloadClass(action: String, errorHandler: (e: Exception) -> Throwable): Class<out Any> =
        try {
            Actions.valueOf(action.uppercase()).classRequest
        } catch (e: Exception) {
            throw errorHandler(e)
        }

    override fun getResponsePayloadClass(action: String, errorHandler: (e: Exception) -> Throwable): Class<out Any> =
        try {
            Actions.valueOf(action.uppercase()).classResponse
        } catch (e: Exception) {
            throw errorHandler(e)
        }

    override fun getActionFromClass(className: String): String =
        Actions.valueOf(getActionFromClassName(className)).value

    override fun validateJson(
        jsonMessage: JsonMessage<JsonNode>,
        errorsHandler: (errors: List<ValidationMessage>) -> Unit
    ) {
        val action = Actions.valueOf(jsonMessage.action!!.uppercase()).camelCase()
        ocppJsonValidator?.isValidObject(
            action = when (jsonMessage.msgType) {
                JsonMessageType.CALL -> action
                JsonMessageType.CALL_RESULT -> "${action}Response"
                JsonMessageType.CALL_ERROR -> return
                else -> throw MessageTypeException(
                    message = "MessageType not supported ${jsonMessage.msgType}",
                    messageId = jsonMessage.msgId
                )
            },
            payload = jsonMessage.payload
        )
            ?.let {
                errorsHandler(it)
            }
    }

    private companion object {
        const val SCHEMA_FOLDER = "ocpp12"
    }
}
