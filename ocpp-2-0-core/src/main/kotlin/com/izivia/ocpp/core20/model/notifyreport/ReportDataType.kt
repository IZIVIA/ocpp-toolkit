package com.izivia.ocpp.core20.model.notifyreport

import com.izivia.ocpp.core20.model.common.ComponentType
import com.izivia.ocpp.core20.model.common.VariableType

data class ReportDataType(
    val component: ComponentType,
    val variable: VariableType,
    val variableAttribute: List<VariableAttributeType>,
    val variableCharacteristics: VariableCharacteristicsType? = null
)