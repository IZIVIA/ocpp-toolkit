package com.izivia.ocpp.core20.model.gettransactionstatus

data class GetTransactionStatusResp(
        val messagesInQueue : Boolean,
        val ongoingIndicator : Boolean?=null
)