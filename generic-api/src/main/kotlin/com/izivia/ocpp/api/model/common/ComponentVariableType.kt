package com.izivia.ocpp.api.model.common


data class ComponentVariableType(
    val component: ComponentType,
    val variable: VariableType? = null
)