package fr.simatix.cs.simulator.api.model.notifyreport

import fr.simatix.cs.simulator.api.model.common.ComponentType
import fr.simatix.cs.simulator.api.model.common.VariableType

data class ReportDataType(
    val component: ComponentType,
    val variable: VariableType,
    val variableAttribute: List<VariableAttributeType>,
    val variableCharacteristics: VariableCharacteristicsType? = null
)