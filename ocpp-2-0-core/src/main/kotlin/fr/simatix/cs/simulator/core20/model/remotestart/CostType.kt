package fr.simatix.cs.simulator.core20.model.remotestart

import fr.simatix.cs.simulator.core20.model.remotestart.enumeration.CostKindEnumType

data class CostType(
    val costKind: CostKindEnumType,
    val amount: Int,
    val amountMultiplier: Int? = null,
)