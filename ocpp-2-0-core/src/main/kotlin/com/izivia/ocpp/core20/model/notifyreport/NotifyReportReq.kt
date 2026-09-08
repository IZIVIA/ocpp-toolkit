package com.izivia.ocpp.core20.model.notifyreport

import com.izivia.ocpp.utils.HasActionTimestamp
import kotlin.time.Instant

data class NotifyReportReq(
    val requestId: Int,
    val generatedAt: Instant,
    val seqNo: Int,
    val tbc: Boolean = false,
    val reportData: List<ReportDataType>? = null
) : HasActionTimestamp {
    override val timestamp: Instant
        get() = generatedAt
}