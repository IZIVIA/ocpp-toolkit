package com.izivia.ocpp.json

import com.fasterxml.jackson.databind.JsonNode
import com.networknt.schema.JsonSchema
import com.networknt.schema.JsonSchemaFactory
import com.networknt.schema.SchemaValidatorsConfig
import com.networknt.schema.SpecVersion
import com.networknt.schema.ValidationMessage
import java.io.InputStream
import java.util.Locale

/**
 * Loads and caches the JSON schemas of a single OCPP version.
 *
 * Schemas are resolved as classpath resources by action name. Since every version module ships its
 * own schemas under the same names, [schemaFolder] lets a module namespace them; the default keeps
 * them at the resources root.
 */
class OcppJsonValidator @JvmOverloads constructor(
    private val specVersion: SpecVersion.VersionFlag,
    private val schemaFolder: String = ""
) {
    private val jsonSchemas = mutableMapOf<String, JsonSchema>()

    // Since json-schema-validator 1.5.x validation messages are localized using the
    // default JVM Locale. Force English so the OCPP error details stay deterministic
    // regardless of the server's locale.
    private val config: SchemaValidatorsConfig =
        SchemaValidatorsConfig.builder().locale(Locale.ENGLISH).build()

    private fun getJsonSchema(action: String): JsonSchema {
        val file = if (schemaFolder.isEmpty()) "$action.json" else "$schemaFolder/$action.json"
        val factory: JsonSchemaFactory = JsonSchemaFactory.getInstance(specVersion)
        val input: InputStream? = Thread.currentThread().contextClassLoader.getResourceAsStream(file)
        return factory.getSchema(input, config)
    }

    /**
     * Serialize the object with jackson and verify that the format is conformed to the
     * json schema
     */
    fun isValidObject(action: String, payload: JsonNode): List<ValidationMessage> =
        // Info :  loading JsonSchema is not thread safe. Can affect performance during the first instanciation
        (jsonSchemas[action] ?: getJsonSchema(action).also { jsonSchemas[action] = it })
            .validate(payload)
            .toList()
}
