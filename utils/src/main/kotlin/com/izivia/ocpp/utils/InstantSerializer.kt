package com.izivia.ocpp.utils

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import kotlinx.datetime.Instant

class InstantSerializer : StdSerializer<Instant>(Instant::class.java) {

    override fun serialize(instant: Instant?, jsonGenerator: JsonGenerator?, serializerProvider: SerializerProvider?) {
        jsonGenerator?.writeString(instant.toString())
    }
}