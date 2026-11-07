package com.izivia.ocpp.utils

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
import com.fasterxml.jackson.core.type.TypeReference
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
import org.junit.jupiter.api.fail
import org.slf4j.LoggerFactory
import strikt.api.DescribeableBuilder
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.containsExactly
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull

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

    @Test
    fun `should read timestamp without timezone in the zone resolved for the charging station`() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Asia/Tokyo")))

        expectThat(readInstant("2024-03-15T12:00:00", TimestampZoneContext("CS1") { PARIS }))
            .isEqualTo(Instant.parse("2024-03-15T11:00:00Z"))
    }

    @Test
    fun `should resolve the zone with the charging station id`() {
        val ids = mutableListOf<String>()

        readInstant("2024-03-15T12:00:00", TimestampZoneContext("CS1") { ids.add(it); PARIS })

        expectThat(ids).containsExactly("CS1")
    }

    @Test
    fun `should not resolve the zone of timestamps with a timezone`() {
        val context = TimestampZoneContext("CS1") { fail("zone resolved for $it") }

        expectThat(readInstant("2024-03-15T12:00:00Z", context)).isEqualTo(Instant.parse("2024-03-15T12:00:00Z"))
        expectThat(readInstant("2024-03-15T12:00:00+02:00", context)).isEqualTo(Instant.parse("2024-03-15T10:00:00Z"))
        expectThat(readInstant("2024-03-15T12:00:00+02", context)).isEqualTo(Instant.parse("2024-03-15T10:00:00Z"))
    }

    @Test
    fun `should refuse a value that is no timestamp without resolving the zone`() {
        val context = TimestampZoneContext("CS1") { fail("zone resolved for $it") }

        val exception = assertThrows<Exception> { readInstant("not a timestamp", context) }

        expectThat(exception.message).isNotNull().contains("must use Z")
    }

    @Test
    fun `should refuse timestamp without timezone when no zone is resolved`() {
        val exception = assertThrows<Exception> {
            readInstant("2024-03-15T12:00:00", TimestampZoneContext("CS1") { null })
        }

        expectThat(exception.message).isNotNull().contains("CS1").contains("2024-03-15T12:00:00")
    }

    @Test
    fun `should resolve the zone once per context`() {
        var resolutions = 0
        val context = TimestampZoneContext("CS1") { resolutions++; PARIS }

        val instants: List<Instant> = mapper.readerFor(object : TypeReference<List<Instant>>() {})
            .withTimestampZone(context)
            .readValue("""["2024-03-15T12:00:00","2024-03-15T13:00:00","2024-03-15T14:00:00"]""")

        expectThat(instants).containsExactly(
            Instant.parse("2024-03-15T11:00:00Z"),
            Instant.parse("2024-03-15T12:00:00Z"),
            Instant.parse("2024-03-15T13:00:00Z"),
        )
        expectThat(resolutions).isEqualTo(1)
    }

    @Test
    fun `should follow LocalDateTime atZone in a daylight saving gap of the resolved zone`() {
        // 02:30 does not exist in Paris on 2024-03-31: atZone shifts it forward by the gap, to 03:30+02:00.
        expectThat(readInstant("2024-03-31T02:30:00", TimestampZoneContext("CS1") { PARIS }))
            .isEqualTo(Instant.parse("2024-03-31T01:30:00Z"))
    }

    @Test
    fun `should follow LocalDateTime atZone in a daylight saving overlap of the resolved zone`() {
        // 02:30 happens twice in Paris on 2024-10-27: atZone keeps the earlier offset, +02:00.
        expectThat(readInstant("2024-10-27T02:30:00", TimestampZoneContext("CS1") { PARIS }))
            .isEqualTo(Instant.parse("2024-10-27T00:30:00Z"))
    }

    @Test
    fun `should warn with the charging station id and the zone used`() {
        withInstantDeserializerLogs { events ->
            readInstant("2024-03-15T12:00:00", TimestampZoneContext("CS1") { PARIS })

            assertTrue(
                events.any {
                    it.level == Level.WARN &&
                        it.formattedMessage ==
                        "Timestamp without timezone received from charging station CS1: 2024-03-15T12:00:00. " +
                        "Interpreting it in Europe/Paris."
                }
            )
        }
    }

    private fun readInstant(value: String, context: TimestampZoneContext): Instant =
        mapper.readerFor(Instant::class.java).withTimestampZone(context).readValue("\"$value\"")

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

    private companion object {
        val PARIS: ZoneId = ZoneId.of("Europe/Paris")
    }
}
