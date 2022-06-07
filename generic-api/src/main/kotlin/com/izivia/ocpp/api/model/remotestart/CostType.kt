package com.izivia.ocpp.api.model.remotestart

import com.izivia.ocpp.api.model.remotestart.enumeration.CostKindEnumType

data class CostType(
    val costKind: CostKindEnumType,
    val amount: Int,
    val amountMultiplier: Int? = null,
)