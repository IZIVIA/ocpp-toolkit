package com.izivia.ocpp.core20.model.getbasereport

import com.izivia.ocpp.core20.model.getbasereport.enumeration.ReportBaseEnumType

data class GetBaseReportReq(
    val requestId: Int,
    val reportBase: ReportBaseEnumType
)