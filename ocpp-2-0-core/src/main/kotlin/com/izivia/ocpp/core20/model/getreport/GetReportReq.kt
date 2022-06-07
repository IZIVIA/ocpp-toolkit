package com.izivia.ocpp.core20.model.getreport

import com.izivia.ocpp.core20.model.common.ComponentVariableType
import com.izivia.ocpp.core20.model.getreport.enumeration.ComponentCriterionEnumType

data class GetReportReq(
    val requestId: Int,
    val componentCriteria: List<ComponentCriterionEnumType>? = null,
    val componentVariable: List<ComponentVariableType>? = null
)