package fr.simatix.cs.simulator.api.model.remotestart

data class ChargingSchedulePeriodType(
    val startPeriod: Int,
    val limit: Double,
    val numberPhases: Int = 3,
    val phaseToUse: Int? = null
)