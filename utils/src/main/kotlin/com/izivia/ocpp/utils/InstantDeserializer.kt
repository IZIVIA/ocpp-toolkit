package com.izivia.ocpp.utils

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import java.time.DateTimeException
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.time.Instant
import kotlin.time.toKotlinInstant
import org.slf4j.LoggerFactory

class InstantDeserializer : StdDeserializer<Instant>(Instant::class.java) {

    @Throws(IllegalStateException::class)
    override fun deserialize(jsonParser: JsonParser?, deserializationContext: DeserializationContext?): Instant =
        if (jsonParser != null) {
            parseInstant(jsonParser.valueAsString)
        } else {
            throw IllegalStateException("Error while deserialization : jsonParser null")
        }

    private fun parseInstant(value: String): Instant =
        try {
            Instant.parse(value).also {
                warnIfIncompleteTimezoneOffset(value)
            }
        } catch (exception: IllegalArgumentException) {
            parseInstantWithoutTimezone(value)
        }

    private fun warnIfIncompleteTimezoneOffset(value: String) {
        if (INCOMPLETE_TIMEZONE_OFFSET_SUFFIX.matches(value)) {
            // Some OCPP charge point tooling emits incomplete offsets like +02.
            // Accept them for inbound interoperability, but keep outbound timestamps canonical:
            // https://github.com/monta-app/ocpp-emulator/issues/5
            logger.warn(
                "Timestamp with incomplete timezone offset received: {}. Accepting it for interoperability.",
                value,
            )
        }
    }

    private fun parseInstantWithoutTimezone(value: String): Instant {
        return try {
            // Compatibility fallback inspired by SteVe, which encountered charge points omitting the timezone:
            // https://github.com/steve-community/ocpp-jaxb/blob/main/ocpp-jaxb/src/main/java/de/rwth/idsg/ocpp/jaxb/Utils.java
            LocalDateTime
                .parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                .atZone(ZoneOffset.UTC)
                .toInstant()
                .toKotlinInstant()
        } catch (exception: DateTimeException) {
            throw IllegalArgumentException("Timestamp must use Z, +HH:mm/-HH:mm, or omit timezone entirely: $value", exception)
        }.also {
            logger.warn(
                "Timestamp without timezone received: {}. Interpreting it as UTC.",
                value,
            )
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(InstantDeserializer::class.java)

        private val INCOMPLETE_TIMEZONE_OFFSET_SUFFIX = Regex(""".*[Tt].*[+-]\d{2}$""")
    }
}
