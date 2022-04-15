package fr.simatix.cs.simulator.api.model.notifymonitoringreport

import fr.simatix.cs.simulator.api.model.common.ComponentType
import fr.simatix.cs.simulator.api.model.common.VariableType

data class MonitoringDataType(
    val component: ComponentType,
    val variable: VariableType,
    val variableMonitoring: List<VariableMonitoringType> = listOf()
)
