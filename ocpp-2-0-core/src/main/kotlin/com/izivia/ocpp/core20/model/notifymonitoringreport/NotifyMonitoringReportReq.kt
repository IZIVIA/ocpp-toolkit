package com.izivia.ocpp.core20.model.notifymonitoringreport

import kotlinx.datetime.Instant

data class NotifyMonitoringReportReq(
    val requestId: Int,
    val tbc: Boolean = false,
    val seqNo: Int,
    val generatedAt: Instant,
    val monitor: List<MonitoringDataType>? = null
)