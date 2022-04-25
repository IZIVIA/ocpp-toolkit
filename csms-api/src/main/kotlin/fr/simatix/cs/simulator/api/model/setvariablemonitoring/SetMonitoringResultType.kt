package fr.simatix.cs.simulator.api.model.setvariablemonitoring

import fr.simatix.cs.simulator.api.model.common.ComponentType
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.common.VariableType
import fr.simatix.cs.simulator.api.model.common.enumeration.MonitorEnumType
import fr.simatix.cs.simulator.api.model.setvariablemonitoring.enumeration.SetMonitoringStatusEnumType

data class SetMonitoringResultType(
        val status : SetMonitoringStatusEnumType,
        val type : MonitorEnumType,
        val severity : Int,
        val component : ComponentType,
        val variable : VariableType,
        val id : Int?=null,
        val statusInfo: StatusInfoType?=null
)
