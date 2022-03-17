package fr.simatix.cs.simulator.core20.model.getreport

import fr.simatix.cs.simulator.core20.model.getreport.enumeration.ComponentCriterionEnumType

data class GetReportReq(
    val requestId: Int,
    val componentCriteria: List<ComponentCriterionEnumType>? = null,
    val componentVariable: List<ComponentVariableType>? = null
)