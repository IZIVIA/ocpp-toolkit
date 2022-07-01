package com.izivia.ocpp.utils

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import kotlinx.datetime.Instant

class InstantDeserializer : StdDeserializer<Instant>(Instant::class.java) {

    @Throws(IllegalStateException::class)
    override fun deserialize(jsonParser: JsonParser?, deserializationContext: DeserializationContext?): Instant =
        if (jsonParser != null) {
            Instant.parse(jsonParser.valueAsString)
        } else {
            throw IllegalStateException("Error while deserialization : jsonParser null")
        }
}