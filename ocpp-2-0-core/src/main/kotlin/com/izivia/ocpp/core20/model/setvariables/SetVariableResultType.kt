package com.izivia.ocpp.core20.model.setvariables

import com.izivia.ocpp.core20.model.common.ComponentType
import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.common.VariableType
import com.izivia.ocpp.core20.model.common.enumeration.AttributeEnumType
import com.izivia.ocpp.core20.model.setvariables.enumeration.SetVariableStatusEnumType

data class SetVariableResultType(
    val attributeStatus: SetVariableStatusEnumType,
    val component: ComponentType,
    val variable: VariableType,
    val attributeType: AttributeEnumType = AttributeEnumType.Actual,
    val attributeStatusInfo: StatusInfoType? = null
)