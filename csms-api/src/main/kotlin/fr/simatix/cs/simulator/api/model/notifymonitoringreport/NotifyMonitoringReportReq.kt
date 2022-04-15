package fr.simatix.cs.simulator.api.model.notifymonitoringreport

import fr.simatix.cs.simulator.api.model.Request
import kotlinx.datetime.Instant

data class NotifyMonitoringReportReq(
    val requestId: Int,
    val tbc: Boolean = false,
    val seqNo: Int,
    val generatedAt: Instant,
    val monitor: List<MonitoringDataType>? = null
): Request