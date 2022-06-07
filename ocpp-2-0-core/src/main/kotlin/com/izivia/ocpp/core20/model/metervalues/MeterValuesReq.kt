package com.izivia.ocpp.core20.model.metervalues

import com.izivia.ocpp.core20.model.common.MeterValueType

data class MeterValuesReq(
    val evseId: Int,
    val meterValue: List<MeterValueType>
)