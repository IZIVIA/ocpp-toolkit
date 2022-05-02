package fr.simatix.cs.simulator.api.model.gettransactionstatus

import fr.simatix.cs.simulator.api.model.Request
data class GetTransactionStatusReq(
        val transactionId : String?=null
) : Request