package fr.simatix.cs.simulator.api.model.clearvariablemonitoring

import fr.simatix.cs.simulator.api.model.clearvariablemonitoring.enumeration.ClearMonitoringStatusEnumType
import fr.simatix.cs.simulator.api.model.common.StatusInfoType

data class ClearMonitoringResultType(
    val status: ClearMonitoringStatusEnumType,
    val id: Int,
    val statusInfo: StatusInfoType? = null
)
