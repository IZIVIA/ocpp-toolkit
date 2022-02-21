package fr.simatix.cs.simulator.core16.model

import fr.simatix.cs.simulator.core16.model.enumeration.*
import fr.simatix.cs.simulator.core16.model.enumeration.UnitOfMeasure

data class SampledValue(
    val value: String,
    val context: ReadingContext? = ReadingContext.SamplePeriodic,
    val format: ValueFormat? = ValueFormat.Raw,
    val location: Location? = Location.Outlet,
    val measurand: Measurand? = Measurand.EnergyActiveImportRegister,
    val phase: Phase? = null,
    val unit: UnitOfMeasure? = UnitOfMeasure.Wh
)