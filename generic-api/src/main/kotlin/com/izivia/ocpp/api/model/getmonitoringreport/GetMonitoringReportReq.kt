package com.izivia.ocpp.api.model.getmonitoringreport

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.getmonitoringreport.enumeration.MonitoringCriterionEnumType
import com.izivia.ocpp.api.model.common.ComponentVariableType

data class GetMonitoringReportReq(
    val requestId : Int,
    val monitoringCriteria : List<MonitoringCriterionEnumType>?=null,
    val componentVariable : List<ComponentVariableType>?=null
) : Request
