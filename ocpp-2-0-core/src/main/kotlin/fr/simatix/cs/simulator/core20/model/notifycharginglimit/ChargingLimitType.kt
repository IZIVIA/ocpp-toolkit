package fr.simatix.cs.simulator.core20.model.notifycharginglimit

import fr.simatix.cs.simulator.core20.model.common.enumeration.ChargingLimitSourceEnumType

data class ChargingLimitType(
    val chargingLimitSource: ChargingLimitSourceEnumType,
    val isGridCritical: Boolean? = null
)