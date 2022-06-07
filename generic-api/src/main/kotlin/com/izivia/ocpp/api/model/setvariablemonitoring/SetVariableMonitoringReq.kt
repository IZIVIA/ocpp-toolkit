package com.izivia.ocpp.api.model.setvariablemonitoring

import com.izivia.ocpp.api.model.Request

data class SetVariableMonitoringReq(
        val setMonitoringData : List<SetMonitoringDataType>
) : Request
