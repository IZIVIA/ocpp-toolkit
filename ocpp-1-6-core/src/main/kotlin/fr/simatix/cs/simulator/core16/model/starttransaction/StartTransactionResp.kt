package fr.simatix.cs.simulator.core16.model.starttransaction

import fr.simatix.cs.simulator.core16.model.common.IdTagInfo

data class StartTransactionResp(
    val idTagInfo: IdTagInfo,
    val transactionId: Int
)