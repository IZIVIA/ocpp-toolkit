package com.izivia.ocpp.core20.model.getmonitoringreport

import com.izivia.ocpp.core20.model.getmonitoringreport.enumeration.MonitoringCriterionEnumType
import com.izivia.ocpp.core20.model.common.ComponentVariableType

data class GetMonitoringReportReq(
        val requestId : Int,
        val monitoringCriteria : List<MonitoringCriterionEnumType>?=null,
        val componentVariable : List<ComponentVariableType>?=null
)
