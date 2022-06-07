package com.izivia.ocpp.api.model.notifymonitoringreport

import com.izivia.ocpp.api.model.common.ComponentType
import com.izivia.ocpp.api.model.common.VariableType

data class MonitoringDataType(
    val component: ComponentType,
    val variable: VariableType,
    val variableMonitoring: List<VariableMonitoringType> = listOf()
)
