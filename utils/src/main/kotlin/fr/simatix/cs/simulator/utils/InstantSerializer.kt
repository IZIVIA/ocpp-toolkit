package fr.simatix.cs.simulator.utils

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import kotlinx.datetime.Instant

class InstantSerializer : JsonSerializer<Instant>() {

    override fun serialize(instant: Instant?, jsonGenerator: JsonGenerator?, serializerProvider: SerializerProvider?) {
        jsonGenerator?.writeString(instant.toString())
    }
}