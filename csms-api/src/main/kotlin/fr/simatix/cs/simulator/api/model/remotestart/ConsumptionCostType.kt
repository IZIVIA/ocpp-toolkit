package fr.simatix.cs.simulator.api.model.remotestart

data class ConsumptionCostType(
    val cost: List<CostType>,
    val startValue: Double
)