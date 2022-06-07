package com.izivia.ocpp.utils

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import kotlinx.datetime.Instant

class InstantDeserializer : JsonDeserializer<Instant>() {

    @Throws(IllegalStateException::class)
    override fun deserialize(jsonParser: JsonParser?, deserializationContext: DeserializationContext?): Instant =
        if (jsonParser != null) {
            Instant.parse(jsonParser.valueAsString)
        } else {
            throw IllegalStateException("Error while deserialization : jsonParser null")
        }
}