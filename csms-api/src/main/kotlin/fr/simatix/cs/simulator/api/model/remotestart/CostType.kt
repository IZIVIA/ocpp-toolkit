package fr.simatix.cs.simulator.api.model.remotestart

import fr.simatix.cs.simulator.api.model.remotestart.enumeration.CostKindEnumType

data class CostType(
    val costKind: CostKindEnumType,
    val amount: Int,
    val amountMultiplier: Int? = null,
)