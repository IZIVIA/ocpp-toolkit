package fr.simatix.cs.simulator.api.model.remotestop

import fr.simatix.cs.simulator.api.model.Request

data class RequestStopTransactionReq(
    val transactionId: String
): Request