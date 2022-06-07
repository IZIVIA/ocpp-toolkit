package com.izivia.ocpp.core20.model.notifymonitoringreport

import com.izivia.ocpp.core20.model.common.ComponentType
import com.izivia.ocpp.core20.model.common.VariableType

data class MonitoringDataType(
    val component: ComponentType,
    val variable: VariableType,
    val variableMonitoring: List<VariableMonitoringType> = listOf()
)
