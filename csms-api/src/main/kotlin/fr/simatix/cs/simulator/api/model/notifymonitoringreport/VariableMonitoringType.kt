package fr.simatix.cs.simulator.api.model.notifymonitoringreport

import fr.simatix.cs.simulator.api.model.common.enumeration.MonitorEnumType

data class VariableMonitoringType(
        val id: Int,
        val transaction: Boolean,
        val value: Double,
        val type: MonitorEnumType,
        val severity: Int
)