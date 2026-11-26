package com.izivia.ocpp.utils

import com.fasterxml.jackson.databind.ObjectReader
import java.time.ZoneId

/**
 * Tells [InstantDeserializer] how to read the timestamps without timezone sent by a charging station.
 *
 * Given per call through [withTimestampZone], so that a shared mapper can read each station in its own zone.
 * Without it, such timestamps are read as UTC.
 */
class TimestampZoneContext(val chargingStationId: String, resolveZone: (String) -> ZoneId?) {

    private val resolution: Result<ZoneId?> by lazy { runCatching { resolveZone(chargingStationId) } }

    /**
     * Zone of [chargingStationId], resolved on the first timestamp without timezone only, then reused, as is an
     * exception thrown by the resolver. `null` refuses such timestamps.
     */
    val zone: ZoneId? get() = resolution.getOrThrow()
}

fun ObjectReader.withTimestampZone(context: TimestampZoneContext): ObjectReader =
    withAttribute(TimestampZoneContext::class.java, context)
