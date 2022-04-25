package fr.simatix.cs.simulator.core20.model.notifymonitoringreport

import fr.simatix.cs.simulator.core20.model.common.enumeration.MonitorEnumType

data class VariableMonitoringType(
        val id: Int,
        val transaction: Boolean,
        val value: Double,
        val type: MonitorEnumType,
        val severity: Int
)