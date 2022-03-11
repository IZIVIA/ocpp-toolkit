package fr.simatix.cs.simulator.core16.model.remotestart

data class ChargingSchedulePeriod(
    val startPeriod: Int,
    val limit: Double,
    val numberPhases: Int? = 3
)