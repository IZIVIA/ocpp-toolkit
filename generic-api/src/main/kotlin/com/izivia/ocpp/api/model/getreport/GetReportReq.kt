package com.izivia.ocpp.api.model.getreport

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.common.ComponentVariableType
import com.izivia.ocpp.api.model.getreport.enumeration.ComponentCriterionEnumType

data class GetReportReq(
    val requestId: Int,
    val componentCriteria: List<ComponentCriterionEnumType>? = null,
    val componentVariable: List<ComponentVariableType>? = null
): Request