package com.izivia.ocpp.json16

import com.izivia.ocpp.core16.model.heartbeat.HeartbeatResp
import kotlin.time.Instant
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class JsonSerialisationTest {
    val parser = Ocpp16JsonParser()

    @Test
    fun `should serialize timestamps at millis precision`() {
        val resp = parser.mapPayloadToString(HeartbeatResp(Instant.parse("2023-10-05T18:04:03.123456Z")))
        expectThat(resp).isEqualTo("""{"currentTime":"2023-10-05T18:04:03.123Z"}""")
    }
}
