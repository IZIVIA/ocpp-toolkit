package fr.simatix.cs.simulator.core20.model.setvariablemonitoring

import fr.simatix.cs.simulator.core20.model.common.ComponentType
import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.common.VariableType
import fr.simatix.cs.simulator.core20.model.common.enumeration.MonitorEnumType
import fr.simatix.cs.simulator.core20.model.setvariablemonitoring.enumeration.SetMonitoringStatusEnumType

data class SetMonitoringResultType(
        val status : SetMonitoringStatusEnumType,
        val type : MonitorEnumType,
        val severity : Int,
        val component : ComponentType,
        val variable : VariableType,
        val id : Int?=null,
        val statusInfo: StatusInfoType?=null
)
