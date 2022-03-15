package fr.simatix.cs.simulator.api.model.getreport

import fr.simatix.cs.simulator.api.model.common.ComponentType
import fr.simatix.cs.simulator.api.model.common.VariableType

data class ComponentVariableType(
    val component: ComponentType,
    val variable: VariableType? = null
)