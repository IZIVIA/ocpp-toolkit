package fr.simatix.cs.simulator.api.model.getbasereport

import fr.simatix.cs.simulator.api.model.getbasereport.enumeration.ReportBaseEnumType

data class GetBaseReportReq(
    val requestId: Int,
    val reportBase: ReportBaseEnumType
)