package com.izivia.ocpp.api.model.setvariablemonitoring

import com.izivia.ocpp.api.model.common.ComponentType
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.common.VariableType
import com.izivia.ocpp.api.model.common.enumeration.MonitorEnumType
import com.izivia.ocpp.api.model.setvariablemonitoring.enumeration.SetMonitoringStatusEnumType

data class SetMonitoringResultType(
    val status : SetMonitoringStatusEnumType,
    val type : MonitorEnumType,
    val severity : Int,
    val component : ComponentType,
    val variable : VariableType,
    val id : Int?=null,
    val statusInfo: StatusInfoType?=null
)
