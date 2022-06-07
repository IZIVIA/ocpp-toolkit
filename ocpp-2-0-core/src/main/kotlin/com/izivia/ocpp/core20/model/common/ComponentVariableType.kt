package com.izivia.ocpp.core20.model.common


data class ComponentVariableType(
    val component: ComponentType,
    val variable: VariableType? = null
)
