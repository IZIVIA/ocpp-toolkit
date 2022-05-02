package fr.simatix.cs.simulator.api.model.common


data class ComponentVariableType(
    val component: ComponentType,
    val variable: VariableType? = null
)