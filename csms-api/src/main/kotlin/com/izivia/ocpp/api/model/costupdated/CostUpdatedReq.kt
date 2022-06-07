package com.izivia.ocpp.api.model.costupdated

import com.izivia.ocpp.api.model.Request

data class CostUpdatedReq(
        val totalCost: Double,
        val transactionId: String,
): Request