package com.izivia.ocpp.core16.model.common

import com.izivia.ocpp.core16.model.common.enumeration.*

data class SampledValue(
    val value: String,
    val context: ReadingContext = ReadingContext.SamplePeriodic,
    val format: ValueFormat = ValueFormat.Raw,
    val location: Location = Location.Outlet,
    val measurand: Measurand = Measurand.EnergyActiveImportRegister,
    val phase: Phase? = null,
    val unit: UnitOfMeasure = UnitOfMeasure.Wh
)