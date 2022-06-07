package com.izivia.ocpp.core20.model.setvariablemonitoring

import com.izivia.ocpp.core20.model.common.ComponentType
import com.izivia.ocpp.core20.model.common.VariableType
import com.izivia.ocpp.core20.model.common.enumeration.MonitorEnumType

data class SetMonitoringDataType(
        val value : Double,
        val type : MonitorEnumType,
        val severity : Int,
        val component : ComponentType,
        val variable : VariableType,
        val id : Int?=null,
        val transaction : Boolean?=false,
)
