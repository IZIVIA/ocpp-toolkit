package com.izivia.ocpp.core20.model.notifymonitoringreport

import com.izivia.ocpp.core20.model.common.enumeration.MonitorEnumType

data class VariableMonitoringType(
        val id: Int,
        val transaction: Boolean,
        val value: Double,
        val type: MonitorEnumType,
        val severity: Int
)