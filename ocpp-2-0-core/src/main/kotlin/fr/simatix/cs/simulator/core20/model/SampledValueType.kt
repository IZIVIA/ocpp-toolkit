package fr.simatix.cs.simulator.core20.model

import fr.simatix.cs.simulator.core20.model.enumeration.LocationEnumType
import fr.simatix.cs.simulator.core20.model.enumeration.MeasurandEnumType
import fr.simatix.cs.simulator.core20.model.enumeration.PhaseEnumType
import fr.simatix.cs.simulator.core20.model.enumeration.ReadingContextEnumType

data class SampledValueType (
    val value: Double,
    val context: ReadingContextEnumType? = ReadingContextEnumType.InterruptionBegin,
    val measurand: MeasurandEnumType? = MeasurandEnumType.EnergyActiveImportRegister,
    val phase: PhaseEnumType? = null,
    val location: LocationEnumType? = LocationEnumType.Outlet,
    val signedMeterValue: SignedMeterValueType? = null,
    val unitOfMeasure: UnitOfMeasure? = null
)
