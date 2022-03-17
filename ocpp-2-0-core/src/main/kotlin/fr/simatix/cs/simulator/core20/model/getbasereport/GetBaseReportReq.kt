package fr.simatix.cs.simulator.core20.model.getbasereport

import fr.simatix.cs.simulator.core20.model.getbasereport.enumeration.ReportBaseEnumType

data class GetBaseReportReq(
    val requestId: Int,
    val reportBase: ReportBaseEnumType
)