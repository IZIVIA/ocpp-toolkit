package com.izivia.ocpp.core12.model.remotestop

import com.izivia.ocpp.core12.model.Request

data class RemoteStopTransactionReq(
    val transactionId: Int
): Request
