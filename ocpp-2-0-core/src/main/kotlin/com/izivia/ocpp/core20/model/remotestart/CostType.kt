package com.izivia.ocpp.core20.model.remotestart

import com.izivia.ocpp.core20.model.remotestart.enumeration.CostKindEnumType

data class CostType(
    val costKind: CostKindEnumType,
    val amount: Int,
    val amountMultiplier: Int? = null,
)