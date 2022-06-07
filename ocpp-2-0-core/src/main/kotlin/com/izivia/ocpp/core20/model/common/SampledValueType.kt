package com.izivia.ocpp.core20.model.common

import com.izivia.ocpp.core20.model.common.enumeration.LocationEnumType
import com.izivia.ocpp.core20.model.common.enumeration.MeasurandEnumType
import com.izivia.ocpp.core20.model.common.enumeration.PhaseEnumType
import com.izivia.ocpp.core20.model.common.enumeration.ReadingContextEnumType

data class SampledValueType (
    val value: Double,
    val context: ReadingContextEnumType = ReadingContextEnumType.InterruptionBegin,
    val measurand: MeasurandEnumType = MeasurandEnumType.EnergyActiveImportRegister,
    val phase: PhaseEnumType? = null,
    val location: LocationEnumType = LocationEnumType.Outlet,
    val signedMeterValue: SignedMeterValueType? = null,
    val unitOfMeasure: UnitOfMeasure? = null
)
