package com.izivia.ocpp.api.model.getvariables

import com.izivia.ocpp.api.model.common.ComponentType
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.common.VariableType
import com.izivia.ocpp.api.model.common.enumeration.AttributeEnumType
import com.izivia.ocpp.api.model.getvariables.enumeration.GetVariableStatusEnumType

data class GetVariableResultType(
    val attributeStatus: GetVariableStatusEnumType,
    val component: ComponentType,
    val variable: VariableType,
    val attributeType: AttributeEnumType = AttributeEnumType.Actual,
    val readonly: Boolean? = null,
    val attributeValue: String? = null,
    val attributeStatusInfo: StatusInfoType? = null
)