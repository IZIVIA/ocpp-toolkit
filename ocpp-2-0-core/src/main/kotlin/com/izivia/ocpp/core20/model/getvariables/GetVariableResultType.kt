package com.izivia.ocpp.core20.model.getvariables

import com.izivia.ocpp.core20.model.common.ComponentType
import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.common.VariableType
import com.izivia.ocpp.core20.model.common.enumeration.AttributeEnumType
import com.izivia.ocpp.core20.model.getvariables.enumeration.GetVariableStatusEnumType

data class GetVariableResultType(
    val attributeStatus: GetVariableStatusEnumType,
    val component: ComponentType,
    val variable: VariableType,
    val attributeType: AttributeEnumType = AttributeEnumType.Actual,
    val attributeValue: String? = null,
    val attributeStatusInfo: StatusInfoType? = null
)