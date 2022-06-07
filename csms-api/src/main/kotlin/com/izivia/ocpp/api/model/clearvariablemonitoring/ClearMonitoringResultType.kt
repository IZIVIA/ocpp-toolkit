package com.izivia.ocpp.api.model.clearvariablemonitoring

import com.izivia.ocpp.api.model.clearvariablemonitoring.enumeration.ClearMonitoringStatusEnumType
import com.izivia.ocpp.api.model.common.StatusInfoType

data class ClearMonitoringResultType(
    val status: ClearMonitoringStatusEnumType,
    val id: Int,
    val statusInfo: StatusInfoType? = null
)
