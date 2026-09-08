package com.izivia.ocpp.core20.model.notifymonitoringreport

import com.izivia.ocpp.utils.HasActionTimestamp
import kotlin.time.Instant

data class NotifyMonitoringReportReq(
    val requestId: Int,
    val tbc: Boolean = false,
    val seqNo: Int,
    val generatedAt: Instant,
    val monitor: List<MonitoringDataType>? = null
) : HasActionTimestamp {
    override val timestamp: Instant
        get() = generatedAt
}