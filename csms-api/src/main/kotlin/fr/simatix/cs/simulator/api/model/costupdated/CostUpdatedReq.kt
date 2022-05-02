package fr.simatix.cs.simulator.api.model.costupdated

import fr.simatix.cs.simulator.api.model.Request

data class CostUpdatedReq(
        val totalCost: Double,
        val transactionId: String,
): Request