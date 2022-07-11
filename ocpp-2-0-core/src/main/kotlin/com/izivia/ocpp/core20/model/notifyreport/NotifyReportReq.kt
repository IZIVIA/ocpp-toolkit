package com.izivia.ocpp.core20.model.notifyreport

import kotlinx.datetime.Instant

data class NotifyReportReq(
    val requestId: Int,
    val generatedAt: Instant,
    val seqNo: Int,
    val tbc: Boolean = false,
    val reportData: List<ReportDataType>? = null
)