package com.izivia.ocpp.api.model.notifyreport

import com.izivia.ocpp.api.model.common.ComponentType
import com.izivia.ocpp.api.model.common.VariableType

data class ReportDataType(
    val component: ComponentType,
    val variable: VariableType,
    val variableAttribute: List<VariableAttributeType>,
    val variableCharacteristics: VariableCharacteristicsType? = null
)