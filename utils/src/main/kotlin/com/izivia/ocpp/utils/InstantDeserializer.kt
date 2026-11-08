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
            parseInstant(
                jsonParser.valueAsString,
                deserializationContext?.getAttribute(TimestampZoneContext::class.java) as? TimestampZoneContext
            )
        } else {
            throw IllegalStateException("Error while deserialization : jsonParser null")
        }

    private fun parseInstant(value: String, timestampZone: TimestampZoneContext?): Instant =
        try {
            Instant.parse(value).also {
                warnIfIncompleteTimezoneOffset(value)
            }
        } catch (exception: IllegalArgumentException) {
            parseInstantWithoutTimezone(value, timestampZone)
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

    private fun parseInstantWithoutTimezone(value: String, timestampZone: TimestampZoneContext?): Instant {
        // Compatibility fallback inspired by SteVe, which encountered charge points omitting the timezone:
        // https://github.com/steve-community/ocpp-jaxb/blob/main/ocpp-jaxb/src/main/java/de/rwth/idsg/ocpp/jaxb/Utils.java
        val localDateTime = try {
            LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        } catch (exception: DateTimeException) {
            throw IllegalArgumentException("Timestamp must use Z, +HH:mm/-HH:mm, or omit timezone entirely: $value", exception)
        }
        if (timestampZone == null) {
            logger.warn(
                "Timestamp without timezone received: {}. Interpreting it as UTC.",
                value,
            )
            return localDateTime.atZone(ZoneOffset.UTC).toInstant().toKotlinInstant()
        }
        val zone = timestampZone.zone ?: throw IllegalArgumentException(
            "Timestamp without timezone refused for charging station ${timestampZone.chargingStationId}: $value"
        )
        logger.warn(
            "Timestamp without timezone received from charging station {}: {}. Interpreting it in {}.",
            timestampZone.chargingStationId,
            value,
            zone,
        )
        // Follows LocalDateTime.atZone: a time in a daylight saving gap is shifted forward by the length of the gap,
        // a time in an overlap takes the earlier offset.
        return localDateTime.atZone(zone).toInstant().toKotlinInstant()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(InstantDeserializer::class.java)

        private val INCOMPLETE_TIMEZONE_OFFSET_SUFFIX = Regex(""".*[Tt].*[+-]\d{2}$""")
    }
}
