package com.izivia.ocpp.api.model.notifymonitoringreport

import com.izivia.ocpp.api.model.common.enumeration.MonitorEnumType

data class VariableMonitoringType(
    val id: Int,
    val transaction: Boolean,
    val value: Double,
    val type: MonitorEnumType,
    val severity: Int
)