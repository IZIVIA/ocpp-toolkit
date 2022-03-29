package fr.simatix.cs.simulator.api.model.notifyreport

import fr.simatix.cs.simulator.api.model.Request
import kotlinx.datetime.Instant

data class NotifyReportReq(
    val requestId: Int,
    val generatedAt: Instant,
    val seqNo: Int,
    val tbc: Boolean = false,
    val reportData: List<ReportDataType>? = null
): Request