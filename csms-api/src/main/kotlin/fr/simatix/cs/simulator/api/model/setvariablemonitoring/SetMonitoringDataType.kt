package fr.simatix.cs.simulator.api.model.setvariablemonitoring

import fr.simatix.cs.simulator.api.model.common.ComponentType
import fr.simatix.cs.simulator.api.model.common.VariableType
import fr.simatix.cs.simulator.api.model.common.enumeration.MonitorEnumType

data class SetMonitoringDataType(
        val value : Double,
        val type : MonitorEnumType,
        val severity : Int,
        val component : ComponentType,
        val variable : VariableType,
        val id : Int?=null,
        val transaction : Boolean?=false
)
