package com.izivia.ocpp.api.model.remotestart

data class ConsumptionCostType(
    val cost: List<CostType>,
    val startValue: Double
)