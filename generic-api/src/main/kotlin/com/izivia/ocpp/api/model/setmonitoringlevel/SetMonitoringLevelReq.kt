package com.izivia.ocpp.api.model.setmonitoringlevel

import com.izivia.ocpp.api.model.Request

data class SetMonitoringLevelReq(
        val severity : Int
) : Request
