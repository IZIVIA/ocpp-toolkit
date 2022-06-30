package com.izivia.ocpp.core15.model.metervalues

import com.izivia.ocpp.core15.model.common.MeterValue

data class MeterValuesReq(
    val connectorId: Int,
    val values: List<MeterValue>?,
    val transactionId: Int? = null
)