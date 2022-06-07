package com.izivia.ocpp.api.model.notifymonitoringreport

import com.izivia.ocpp.api.model.Request
import kotlinx.datetime.Instant

data class NotifyMonitoringReportReq(
    val requestId: Int,
    val tbc: Boolean = false,
    val seqNo: Int,
    val generatedAt: Instant,
    val monitor: List<MonitoringDataType>? = null
): Request