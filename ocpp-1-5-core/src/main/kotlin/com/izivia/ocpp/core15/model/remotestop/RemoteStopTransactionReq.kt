package com.izivia.ocpp.core15.model.remotestop

import com.izivia.ocpp.core15.model.Request

data class RemoteStopTransactionReq(
    val transactionId: Int
): Request
