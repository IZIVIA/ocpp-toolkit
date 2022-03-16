package fr.simatix.cs.simulator.core20.model.transactionevent

import fr.simatix.cs.simulator.core20.model.common.IdTokenInfoType
import fr.simatix.cs.simulator.core20.model.common.MessageContentType

data class TransactionEventResp(
    val totalCost: Double? = null,
    val chargingPriority: Int? = 0,
    val idTokenInfo: IdTokenInfoType? = null,
    val updatedPersonalMessage: MessageContentType? = null
)
