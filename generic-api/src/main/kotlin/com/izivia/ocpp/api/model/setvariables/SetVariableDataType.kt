package com.izivia.ocpp.api.model.setvariables

import com.izivia.ocpp.api.model.common.ComponentType
import com.izivia.ocpp.api.model.common.VariableType
import com.izivia.ocpp.api.model.common.enumeration.AttributeEnumType

data class SetVariableDataType(
    val attributeValue: String,
    val component: ComponentType,
    val variable: VariableType,
    val attributeType: AttributeEnumType = AttributeEnumType.Actual
)