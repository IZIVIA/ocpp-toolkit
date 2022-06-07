package com.izivia.ocpp.api.model.setvariables

import com.izivia.ocpp.api.model.common.ComponentType
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.common.VariableType
import com.izivia.ocpp.api.model.common.enumeration.AttributeEnumType
import com.izivia.ocpp.api.model.setvariables.enumeration.SetVariableStatusEnumType

data class SetVariableResultType(
    val attributeStatus: SetVariableStatusEnumType,
    val component: ComponentType,
    val variable: VariableType,
    val attributeType: AttributeEnumType = AttributeEnumType.Actual,
    val attributeStatusInfo: StatusInfoType? = null
)