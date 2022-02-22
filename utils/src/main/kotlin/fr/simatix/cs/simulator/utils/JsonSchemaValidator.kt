package fr.simatix.cs.simulator.utils

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.networknt.schema.JsonSchema
import com.networknt.schema.JsonSchemaFactory
import com.networknt.schema.SpecVersion
import com.networknt.schema.ValidationMessage
import java.io.InputStream

class JsonSchemaValidator {
    companion object {
        private val mapper = jacksonObjectMapper()

        private fun <T> getJsonNodeObject(value: T): JsonNode {
            return mapper.readTree(mapper.writeValueAsString(value))
        }

        private fun getJsonSchema(file: String, version : SpecVersion.VersionFlag): JsonSchema {
            val factory: JsonSchemaFactory = JsonSchemaFactory.getInstance(version)
            val input: InputStream? = Thread.currentThread().contextClassLoader.getResourceAsStream(file)
            return factory.getSchema(input)
        }


        /**
         * Serialize the object with jackson and verify that the format is conformed to the
         * json schema
         */
        fun <T> isValidObjectV4(value: T, file: String): MutableSet<ValidationMessage> {
            val jsonNode: JsonNode = getJsonNodeObject(value)
            val schema = getJsonSchema(file,SpecVersion.VersionFlag.V4)
            return schema.validate(jsonNode)
        }

        /**
         * Serialize the object with jackson and verify that the format is conformed to the
         * json schema
         */
        fun <T> isValidObjectV6(value: T, file: String): MutableSet<ValidationMessage> {
            val jsonNode: JsonNode = getJsonNodeObject(value)
            val schema = getJsonSchema(file,SpecVersion.VersionFlag.V6)
            return schema.validate(jsonNode)
        }

    }
}