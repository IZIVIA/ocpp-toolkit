package com.izivia.ocpp.api.model.remotestart

data class SalesTariffEntryType(
    val relativeTimeInterval: RelativeTimeIntervalType,
    val ePriceLevel: Int? = null,
    val consumptionCost: List<ConsumptionCostType>? = null
)