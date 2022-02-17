package fr.simatix.cs.simulator.utils

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import kotlinx.datetime.Instant

class InstantDeserializer: JsonDeserializer<Instant>() {

    override fun deserialize(jsonParser: JsonParser?, deserializationContext: DeserializationContext?): Instant {
        if (jsonParser != null) {
            return Instant.parse(jsonParser.valueAsString)
        } else{
            throw IllegalStateException("Error while deserialization")
        }
    }
}