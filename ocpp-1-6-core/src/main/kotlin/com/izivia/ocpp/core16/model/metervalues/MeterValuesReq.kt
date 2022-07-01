package com.izivia.ocpp.core16.model.metervalues

import com.izivia.ocpp.core16.model.common.MeterValue

data class MeterValuesReq(
    val connectorId: Int,
    val meterValue: List<MeterValue>,
    val transactionId: Int? = null
)