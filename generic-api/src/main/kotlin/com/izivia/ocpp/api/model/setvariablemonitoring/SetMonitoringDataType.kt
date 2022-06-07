package com.izivia.ocpp.api.model.setvariablemonitoring

import com.izivia.ocpp.api.model.common.ComponentType
import com.izivia.ocpp.api.model.common.VariableType
import com.izivia.ocpp.api.model.common.enumeration.MonitorEnumType

data class SetMonitoringDataType(
    val value : Double,
    val type : MonitorEnumType,
    val severity : Int,
    val component : ComponentType,
    val variable : VariableType,
    val id : Int?=null,
    val transaction : Boolean?=false
)
