package fr.simatix.cs.simulator.core20.model.getmonitoringreport

import fr.simatix.cs.simulator.core20.model.getmonitoringreport.enumeration.MonitoringCriterionEnumType
import fr.simatix.cs.simulator.core20.model.common.ComponentVariableType

data class GetMonitoringReportReq(
        val requestId : Int,
        val monitoringCriteria : List<MonitoringCriterionEnumType>?=null,
        val componentVariable : List<ComponentVariableType>?=null
)
