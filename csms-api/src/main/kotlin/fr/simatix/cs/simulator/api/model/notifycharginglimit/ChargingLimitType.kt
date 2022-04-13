package fr.simatix.cs.simulator.api.model.notifycharginglimit

import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingLimitSourceEnumType

data class ChargingLimitType(
    val chargingLimitSource: ChargingLimitSourceEnumType,
    val isGridCritical: Boolean? = null
)
