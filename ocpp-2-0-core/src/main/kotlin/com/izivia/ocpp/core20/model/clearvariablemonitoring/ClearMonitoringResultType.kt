package com.izivia.ocpp.core20.model.clearvariablemonitoring

import com.izivia.ocpp.core20.model.clearvariablemonitoring.enumeration.ClearMonitoringStatusEnumType
import com.izivia.ocpp.core20.model.common.StatusInfoType

data class ClearMonitoringResultType(
    val status: ClearMonitoringStatusEnumType,
    val id: Int,
    val statusInfo: StatusInfoType? = null
)
