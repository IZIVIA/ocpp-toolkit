package com.izivia.ocpp.core15.model.common

import com.izivia.ocpp.core15.model.common.enumeration.*
import kotlinx.datetime.Instant

data class MeterValue(
    val timestamp: Instant,
    val value: String,
    val context: ReadingContext? = ReadingContext.SamplePeriodic,
    val format: ValueFormat? = ValueFormat.Raw,
    val measurand: Measurand? = Measurand.EnergyActiveImportRegister,
    val location: Location? = Location.Outlet,
    val unit: UnitOfMeasure? = UnitOfMeasure.Wh
)
