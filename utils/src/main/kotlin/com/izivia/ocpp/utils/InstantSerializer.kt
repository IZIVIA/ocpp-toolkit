package com.izivia.ocpp.utils

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import kotlin.time.Instant
import kotlin.time.toJavaInstant
import kotlin.time.toKotlinInstant
import java.time.temporal.ChronoUnit

private const val NANOS_PER_MILLIS = 1_000_000

class InstantSerializer : StdSerializer<Instant>(Instant::class.java) {

    override fun serialize(instant: Instant?, jsonGenerator: JsonGenerator?, serializerProvider: SerializerProvider?) {
        jsonGenerator?.writeString(
            instant.truncateToMillis().toString()
        )
    }

    private fun Instant?.truncateToMillis() =
        if (this.nanosOfMillis() != 0) {
            this?.toJavaInstant()?.truncatedTo(ChronoUnit.MILLIS)?.toKotlinInstant()
        } else {
            this
        }

    private fun Instant?.nanosOfMillis() = (this?.nanosecondsOfSecond ?: 0) % NANOS_PER_MILLIS
}
