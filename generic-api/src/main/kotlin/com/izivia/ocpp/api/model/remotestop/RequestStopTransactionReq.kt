package com.izivia.ocpp.api.model.remotestop

import com.izivia.ocpp.api.model.Request

data class RequestStopTransactionReq(
    val transactionId: String
): Request