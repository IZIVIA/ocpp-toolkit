package com.izivia.ocpp.core20.model.remotestart

data class ConsumptionCostType(
    val cost: List<CostType>,
    val startValue: Double
)