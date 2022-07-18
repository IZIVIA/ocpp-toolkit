package com.izivia.ocpp.validator15

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.izivia.ocpp.core15.model.common.enumeration.Measurand
import com.izivia.ocpp.core15.model.common.enumeration.ReadingContext
import com.izivia.ocpp.utils.KotlinxInstantModule
import com.networknt.schema.JsonSchema
import com.networknt.schema.JsonSchemaFactory
import com.networknt.schema.SpecVersion
import com.networknt.schema.ValidationMessage
import java.io.InputStream

class JsonSchemaValidator {

    companion object {
        private val mapper = jacksonObjectMapper()
            .registerModule(KotlinxInstantModule())
            .addMixIn(Measurand::class.java, EnumMixin::class.java)
            .addMixIn(ReadingContext::class.java, EnumMixin::class.java)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)

        private fun <T> getJsonNodeObject(value: T): JsonNode = mapper.readTree(mapper.writeValueAsString(value))

        private fun getJsonSchema(file: String, version: SpecVersion.VersionFlag): JsonSchema {
            val factory: JsonSchemaFactory = JsonSchemaFactory.getInstance(version)
            val input: InputStream? = Thread.currentThread().contextClassLoader.getResourceAsStream(file)
            return factory.getSchema(input)
        }

        /**
         * Serialize the object with jackson and verify that the format is conformed to the
         * json schema
         */
        fun <T> isValidObject(value: T, file: String): MutableSet<ValidationMessage> {
            val jsonNode: JsonNode = getJsonNodeObject(value)
            val schema = getJsonSchema(file, SpecVersion.VersionFlag.V4)
            return schema.validate(jsonNode)
        }
    }

}

abstract class EnumMixin(
    @JsonValue val value: String
)
