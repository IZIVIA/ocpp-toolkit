package com.izivia.ocpp.core20.model.notifyreport

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.izivia.ocpp.utils.InstantDeserializer
import com.izivia.ocpp.utils.InstantSerializer
import kotlinx.datetime.Instant

data class NotifyReportReq(
    val requestId: Int,
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val generatedAt: Instant,
    val seqNo: Int,
    val tbc: Boolean = false,
    val reportData: List<ReportDataType>? = null
)