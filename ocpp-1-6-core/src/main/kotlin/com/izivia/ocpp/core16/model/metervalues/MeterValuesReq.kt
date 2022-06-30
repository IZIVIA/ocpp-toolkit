package com.izivia.ocpp.core16.model.metervalues

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.izivia.ocpp.core16.model.common.MeterValue

data class MeterValuesReq(
    val connectorId: Int,
    @JacksonXmlElementWrapper(useWrapping = false)
    val meterValue: List<MeterValue>,
    val transactionId: Int? = null
)