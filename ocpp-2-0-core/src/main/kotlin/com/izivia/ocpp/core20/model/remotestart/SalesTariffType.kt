package com.izivia.ocpp.core20.model.remotestart

data class SalesTariffType(
    val id: Int,
    val salesTariffEntry: List<SalesTariffEntryType>,
    val salesTariffDescription: String? = null,
    val numEPriceLevels: Int? = null
)