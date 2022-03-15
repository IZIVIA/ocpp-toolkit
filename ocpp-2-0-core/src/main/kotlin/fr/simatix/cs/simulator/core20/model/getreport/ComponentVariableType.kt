package fr.simatix.cs.simulator.core20.model.getreport

import fr.simatix.cs.simulator.core20.model.common.ComponentType
import fr.simatix.cs.simulator.core20.model.common.VariableType

data class ComponentVariableType(
    val component: ComponentType,
    val variable: VariableType? = null
)