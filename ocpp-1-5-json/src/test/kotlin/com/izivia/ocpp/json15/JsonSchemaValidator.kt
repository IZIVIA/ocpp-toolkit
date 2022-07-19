package com.izivia.ocpp.json15

import com.fasterxml.jackson.databind.JsonNode
import com.networknt.schema.JsonSchema
import com.networknt.schema.JsonSchemaFactory
import com.networknt.schema.SpecVersion
import com.networknt.schema.ValidationMessage
import java.io.InputStream

class JsonSchemaValidator {

    companion object {
        private val mapper = Ocpp15JsonObjectMapper

        private fun <T> getJsonNodeObject(value: T): JsonNode = mapper.readTree(mapper.writeValueAsString(value))

        private fun getJsonSchema(file: String): JsonSchema {
            val factory: JsonSchemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4)
            val input: InputStream? = Thread.currentThread().contextClassLoader.getResourceAsStream(file)
            return factory.getSchema(input)
        }

        /**
         * Serialize the object with jackson and verify that the format is conformed to the
         * json schema
         */
        fun <T> isValidObject(value: T, file: String): MutableSet<ValidationMessage> {
            val jsonNode: JsonNode = getJsonNodeObject(value)
            val schema = getJsonSchema(file)
            return schema.validate(jsonNode)
        }
    }
}
