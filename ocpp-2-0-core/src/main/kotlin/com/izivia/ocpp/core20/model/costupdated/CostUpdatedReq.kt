package com.izivia.ocpp.core20.model.costupdated

data class CostUpdatedReq(
        val totalCost: Double,
        val transactionId: String,
)