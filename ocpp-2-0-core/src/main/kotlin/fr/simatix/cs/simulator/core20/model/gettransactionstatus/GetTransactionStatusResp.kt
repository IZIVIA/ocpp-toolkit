package fr.simatix.cs.simulator.core20.model.gettransactionstatus

data class GetTransactionStatusResp(
        val messagesInQueue : Boolean,
        val ongoingIndicator : Boolean?=null
)