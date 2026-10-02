package com.izivia.ocpp.json

import com.fasterxml.jackson.databind.JsonNode
import com.izivia.ocpp.utils.ErrorDetail
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.utils.OcppParserException
import com.networknt.schema.JsonSchema
import com.networknt.schema.JsonSchemaFactory
import com.networknt.schema.SchemaValidatorsConfig
import com.networknt.schema.SpecVersion
import com.networknt.schema.ValidationMessage
import java.util.Locale

/**
 * Loads and caches the JSON schemas of a single OCPP version.
 *
 * Schemas are resolved as classpath resources by action name under [schemaFolder].
 */
class OcppJsonValidator(
    specVersion: SpecVersion.VersionFlag,
    private val schemaFolder: OcppSchemaFolder
) {
    private val jsonSchemas = mutableMapOf<String, JsonSchema>()

    // Since json-schema-validator 1.5.x validation messages are localized using the
    // default JVM Locale. Force English so the OCPP error details stay deterministic
    // regardless of the server's locale.
    private val config: SchemaValidatorsConfig =
        SchemaValidatorsConfig.builder().locale(Locale.ENGLISH).build()

    private val factory: JsonSchemaFactory = JsonSchemaFactory.getInstance(specVersion)

    private fun getJsonSchema(action: String, messageId: String?): JsonSchema {
        val file = "${schemaFolder.path}/$action.json"
        val input = Thread.currentThread().contextClassLoader.getResourceAsStream(file)
            ?: throw SchemaNotFoundException(file, messageId)
        return input.use { factory.getSchema(it, config) }
    }

    /**
     * Serialize the object with jackson and verify that the format is conformed to the
     * json schema
     *
     * @param messageId id of the message being validated, echoed in the call error if the schema is missing
     */
    fun isValidObject(action: String, payload: JsonNode, messageId: String?): List<ValidationMessage> =
        // Info :  loading JsonSchema is not thread safe. Can affect performance during the first instanciation
        (jsonSchemas[action] ?: getJsonSchema(action, messageId).also { jsonSchemas[action] = it })
            .validate(payload)
            .toList()
}

/**
 * Raised when a schema is missing from the classpath. Extends [OcppParserException] so that
 * [OcppJsonParser] reports the schema in the returned call error instead of a bare internal error.
 */
class SchemaNotFoundException(schema: String, override val messageId: String?) : OcppParserException(
    message = "Schema $schema not found on the classpath",
    errorCode = MessageErrorCode.INTERNAL_ERROR,
    messageId = messageId,
    errorDetails = listOf(
        ErrorDetail(
            code = MessageErrorCode.INTERNAL_ERROR.errorCode,
            detail = MessageErrorCode.INTERNAL_ERROR.description
        ),
        ErrorDetail(code = "schema", detail = schema)
    )
)
