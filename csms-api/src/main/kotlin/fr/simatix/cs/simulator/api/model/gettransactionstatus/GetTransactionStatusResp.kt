package fr.simatix.cs.simulator.api.model.gettransactionstatus

import fr.simatix.cs.simulator.api.model.Response

data class GetTransactionStatusResp(
        val messagesInQueue : Boolean,
        val ongoingIndicator : Boolean?=null
) : Response