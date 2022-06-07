package com.izivia.ocpp.api.model.clearvariablemonitoring

import com.izivia.ocpp.api.model.Response

data class ClearVariableMonitoringResp(
    val clearMonitoringResults : List<ClearMonitoringResultType>
): Response