package fr.simatix.cs.simulator.api.model.getmonitoringreport

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.getmonitoringreport.enumeration.MonitoringCriterionEnumType
import fr.simatix.cs.simulator.api.model.common.ComponentVariableType

data class GetMonitoringReportReq(
        val requestId : Int,
        val monitoringCriteria : List<MonitoringCriterionEnumType>?=null,
        val componentVariable : List<ComponentVariableType>?=null
) : Request
