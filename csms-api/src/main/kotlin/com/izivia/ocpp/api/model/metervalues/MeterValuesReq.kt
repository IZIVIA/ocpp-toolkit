package com.izivia.ocpp.api.model.metervalues

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.common.MeterValueType

data class MeterValuesReq(
    val evseId: Int,
    val meterValue: List<MeterValueType>
): Request