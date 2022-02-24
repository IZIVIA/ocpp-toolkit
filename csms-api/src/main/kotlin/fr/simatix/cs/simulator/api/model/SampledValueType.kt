package fr.simatix.cs.simulator.api.model

import fr.simatix.cs.simulator.api.model.enumeration.LocationEnumType
import fr.simatix.cs.simulator.api.model.enumeration.MeasurandEnumType
import fr.simatix.cs.simulator.api.model.enumeration.PhaseEnumType
import fr.simatix.cs.simulator.api.model.enumeration.ReadingContextEnumType

data class SampledValueType(
    val value: Double,
    val context: ReadingContextEnumType = ReadingContextEnumType.InterruptionBegin,
    val measurand: MeasurandEnumType = MeasurandEnumType.EnergyActiveImportRegister,
    val phase: PhaseEnumType? = null,
    val location: LocationEnumType = LocationEnumType.Outlet,
    val signedMeterValue: SignedMeterValueType? = null,
    val unitOfMeasure: UnitOfMeasure? = null
)
