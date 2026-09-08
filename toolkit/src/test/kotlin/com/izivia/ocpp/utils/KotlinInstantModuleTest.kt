package com.izivia.ocpp.utils

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
import com.fasterxml.jackson.databind.ObjectMapper
import java.time.ZoneId
import java.util.TimeZone
import kotlin.time.Instant
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.slf4j.LoggerFactory
import strikt.api.DescribeableBuilder
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class KotlinInstantModuleTest {
    private val mapper = ObjectMapper().registerModule(KotlinInstantModule())
    private lateinit var defaultTimeZone: TimeZone

    @BeforeEach
    fun saveDefaultTimeZone() {
        defaultTimeZone = TimeZone.getDefault()
    }

    @AfterEach
    fun restoreDefaultTimeZone() {
        TimeZone.setDefault(defaultTimeZone)
    }

    @Test
    fun `should truncation timestamps to millis`() {
        expectThat("2023-10-06T12:33:00Z").serializesWithMapperTo("2023-10-06T12:33:00Z")
        expectThat("2023-10-06T12:33:34Z").serializesWithMapperTo("2023-10-06T12:33:34Z")
        expectThat("2023-10-06T12:33:34.123Z").serializesWithMapperTo("2023-10-06T12:33:34.123Z")
        expectThat("2023-10-06T12:33:34.12Z").serializesWithMapperTo("2023-10-06T12:33:34.120Z")
        expectThat("2023-10-06T12:33:34.123456Z").serializesWithMapperTo("2023-10-06T12:33:34.123Z")
        expectThat("2023-10-06T12:33:34.123456789Z").serializesWithMapperTo("2023-10-06T12:33:34.123Z")
        expectThat("2023-10-06T12:33:34.123000Z").serializesWithMapperTo("2023-10-06T12:33:34.123Z")
        expectThat("2023-10-06T12:33:34.123000123Z").serializesWithMapperTo("2023-10-06T12:33:34.123Z")
    }

    @Test
    fun `should deserialize timestamp as kotlin time instant`() {
        expectThat(mapper.readValue("\"2023-10-06T12:33:34.123Z\"", Instant::class.java))
            .isEqualTo(Instant.parse("2023-10-06T12:33:34.123Z"))
    }

    @Test
    fun `should deserialize timestamp with explicit offset`() {
        expectThat(mapper.readValue("\"2023-10-06T12:33:34.123+02:00\"", Instant::class.java))
            .isEqualTo(Instant.parse("2023-10-06T10:33:34.123Z"))
    }

    @Test
    fun `should tolerate timestamp with incomplete offset`() {
        expectThat(mapper.readValue("\"2024-04-26T14:35:02+02\"", Instant::class.java))
            .isEqualTo(Instant.parse("2024-04-26T12:35:02Z"))
    }

    @Test
    fun `should warn when tolerating timestamp with incomplete offset`() {
        withInstantDeserializerLogs { events ->
            mapper.readValue("\"2024-04-26T14:35:02+02\"", Instant::class.java)

            assertTrue(
                events.any {
                    it.level == Level.WARN &&
                        it.formattedMessage ==
                        "Timestamp with incomplete timezone offset received: 2024-04-26T14:35:02+02. Accepting it for interoperability."
                }
            )
        }
    }

    @Test
    fun `should not warn about accepting invalid incomplete offset`() {
        withInstantDeserializerLogs { events ->
            assertThrows<Exception> {
                mapper.readValue("\"2024-04-26T14:35:02+99\"", Instant::class.java)
            }

            assertFalse(events.any { it.formattedMessage.contains("Accepting it for interoperability") })
            assertFalse(events.any { it.formattedMessage.contains("Interpreting it as UTC") })
        }
    }

    @Test
    fun `should deserialize timestamp without timezone as UTC`() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Europe/Paris")))

        expectThat(mapper.readValue("\"2023-10-06T12:33:34.123\"", Instant::class.java))
            .isEqualTo(Instant.parse("2023-10-06T12:33:34.123Z"))
    }

    @Test
    fun `should warn when tolerating timestamp without timezone`() {
        withInstantDeserializerLogs { events ->
            mapper.readValue("\"2023-10-06T12:33:34.123\"", Instant::class.java)

            assertTrue(
                events.any {
                    it.level == Level.WARN &&
                        it.formattedMessage ==
                        "Timestamp without timezone received: 2023-10-06T12:33:34.123. Interpreting it as UTC."
                }
            )
        }
    }

    @Test
    fun `should deserialize timestamp without timezone and without fraction`() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Europe/Paris")))

        expectThat(mapper.readValue("\"2023-01-06T12:33:34\"", Instant::class.java))
            .isEqualTo(Instant.parse("2023-01-06T12:33:34Z"))
    }

    @Test
    fun `should read local timestamp as UTC regardless of JVM default zone during daylight saving overlap`() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Europe/Paris")))

        expectThat(mapper.readValue("\"2023-10-29T02:30:00\"", Instant::class.java))
            .isEqualTo(Instant.parse("2023-10-29T02:30:00Z"))
    }

    @Test
    fun `should read local timestamp as UTC regardless of JVM default zone during daylight saving gap`() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Europe/Paris")))

        expectThat(mapper.readValue("\"2023-03-26T02:30:00\"", Instant::class.java))
            .isEqualTo(Instant.parse("2023-03-26T02:30:00Z"))
    }

    private fun DescribeableBuilder<String>.serializesWithMapperTo(expected: String) {
        expectThat(mapper.writeValueAsString(Instant.parse(this.subject))).isEqualTo('"' + expected + '"')
    }

    private fun withInstantDeserializerLogs(block: (List<ILoggingEvent>) -> Unit) {
        val logger = LoggerFactory.getLogger(InstantDeserializer::class.java) as Logger
        val appender = ListAppender<ILoggingEvent>().apply { start() }
        logger.addAppender(appender)

        try {
            block(appender.list)
        } finally {
            logger.detachAppender(appender)
            appender.stop()
        }
    }
}
