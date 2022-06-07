package com.izivia.ocpp.api.model.setvariablemonitoring

import com.izivia.ocpp.api.model.Response

data class SetVariableMonitoringResp(
    val setMonitoringResult : List<SetMonitoringResultType>
) : Response
