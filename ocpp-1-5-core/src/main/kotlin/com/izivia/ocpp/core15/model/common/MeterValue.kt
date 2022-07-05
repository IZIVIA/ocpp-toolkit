package com.izivia.ocpp.core15.model.common

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.izivia.ocpp.core15.model.common.enumeration.*
import com.izivia.ocpp.utils.InstantDeserializer
import com.izivia.ocpp.utils.InstantSerializer
import kotlinx.datetime.Instant

data class MeterValue(
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val timestamp: Instant,
    val value: String,
    val context: ReadingContext? = ReadingContext.SamplePeriodic,
    val format: ValueFormat? = ValueFormat.Raw,
    val measurand: Measurand? = Measurand.EnergyActiveImportRegister,
    val location: Location? = Location.Outlet,
    val unit: UnitOfMeasure? = UnitOfMeasure.Wh
)
