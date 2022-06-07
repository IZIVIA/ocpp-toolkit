package com.izivia.ocpp.api.model.gettransactionstatus

import com.izivia.ocpp.api.model.Request
data class GetTransactionStatusReq(
        val transactionId : String?=null
) : Request