package com.izivia.ocpp.api.model.gettransactionstatus

import com.izivia.ocpp.api.model.Response

data class GetTransactionStatusResp(
        val messagesInQueue : Boolean,
        val ongoingIndicator : Boolean?=null
) : Response