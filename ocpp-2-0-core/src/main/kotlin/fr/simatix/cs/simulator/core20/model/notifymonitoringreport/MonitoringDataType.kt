package fr.simatix.cs.simulator.core20.model.notifymonitoringreport

import fr.simatix.cs.simulator.core20.model.common.ComponentType
import fr.simatix.cs.simulator.core20.model.common.VariableType

data class MonitoringDataType(
    val component: ComponentType,
    val variable: VariableType,
    val variableMonitoring: List<VariableMonitoringType> = listOf()
)
