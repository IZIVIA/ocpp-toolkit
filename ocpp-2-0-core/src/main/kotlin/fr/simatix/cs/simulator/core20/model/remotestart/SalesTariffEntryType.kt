package fr.simatix.cs.simulator.core20.model.remotestart

data class SalesTariffEntryType(
    val relativeTimeInterval: RelativeTimeIntervalType,
    val ePriceLevel: Int? = null,
    val consumptionCost: List<ConsumptionCostType>? = null
)