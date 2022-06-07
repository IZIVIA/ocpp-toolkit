package com.izivia.ocpp.core20.model.setvariables

import com.izivia.ocpp.core20.model.common.ComponentType
import com.izivia.ocpp.core20.model.common.VariableType
import com.izivia.ocpp.core20.model.common.enumeration.AttributeEnumType

data class SetVariableDataType(
    val attributeValue: String,
    val component: ComponentType,
    val variable: VariableType,
    val attributeType: AttributeEnumType = AttributeEnumType.Actual
)