package com.izivia.ocpp.core20.model.setvariablemonitoring

import com.izivia.ocpp.core20.model.common.ComponentType
import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.common.VariableType
import com.izivia.ocpp.core20.model.common.enumeration.MonitorEnumType
import com.izivia.ocpp.core20.model.setvariablemonitoring.enumeration.SetMonitoringStatusEnumType

data class SetMonitoringResultType(
        val status : SetMonitoringStatusEnumType,
        val type : MonitorEnumType,
        val severity : Int,
        val component : ComponentType,
        val variable : VariableType,
        val id : Int?=null,
        val statusInfo: StatusInfoType?=null
)
