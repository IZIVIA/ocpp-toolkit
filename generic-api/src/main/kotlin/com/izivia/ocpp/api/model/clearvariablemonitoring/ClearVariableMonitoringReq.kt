package com.izivia.ocpp.api.model.clearvariablemonitoring

import com.izivia.ocpp.api.model.Request

data class ClearVariableMonitoringReq(
    val ids: List<Int>
): Request