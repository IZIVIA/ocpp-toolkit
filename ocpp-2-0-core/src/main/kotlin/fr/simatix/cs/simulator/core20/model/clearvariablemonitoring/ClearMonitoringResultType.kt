package fr.simatix.cs.simulator.core20.model.clearvariablemonitoring

import fr.simatix.cs.simulator.core20.model.clearvariablemonitoring.enumeration.ClearMonitoringStatusEnumType
import fr.simatix.cs.simulator.core20.model.common.StatusInfoType

data class ClearMonitoringResultType(
    val status: ClearMonitoringStatusEnumType,
    val id: Int,
    val statusInfo: StatusInfoType? = null
)
