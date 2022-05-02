package fr.simatix.cs.simulator.api.model.getreport

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.common.ComponentVariableType
import fr.simatix.cs.simulator.api.model.getreport.enumeration.ComponentCriterionEnumType

data class GetReportReq(
    val requestId: Int,
    val componentCriteria: List<ComponentCriterionEnumType>? = null,
    val componentVariable: List<ComponentVariableType>? = null
): Request