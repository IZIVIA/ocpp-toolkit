package com.izivia.ocpp.api.model.getbasereport

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.getbasereport.enumeration.ReportBaseEnumType

data class GetBaseReportReq(
    val requestId: Int,
    val reportBase: ReportBaseEnumType
): Request