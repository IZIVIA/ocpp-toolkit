package com.izivia.ocpp.core20.model.notifymonitoringreport

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.izivia.ocpp.utils.InstantDeserializer
import com.izivia.ocpp.utils.InstantSerializer
import kotlinx.datetime.Instant

data class NotifyMonitoringReportReq(
    val requestId: Int,
    val tbc: Boolean = false,
    val seqNo: Int,
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val generatedAt: Instant,
    val monitor: List<MonitoringDataType>? = null
)