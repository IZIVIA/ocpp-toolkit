package fr.simatix.cs.simulator.api.model.remotestart

data class SalesTariffType(
    val id: Int,
    val salesTariffEntry: List<SalesTariffEntryType>,
    val salesTariffDescription: String? = null,
    val numEPriceLevels: Int? = null
)