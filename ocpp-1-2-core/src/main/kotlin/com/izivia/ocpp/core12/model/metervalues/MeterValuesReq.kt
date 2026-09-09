package com.izivia.ocpp.core12.model.metervalues

import com.izivia.ocpp.core12.model.Request
import com.izivia.ocpp.core12.model.common.MeterValue

data class MeterValuesReq(
    val connectorId: Int,
    val values: List<MeterValue>? = null
): Request
