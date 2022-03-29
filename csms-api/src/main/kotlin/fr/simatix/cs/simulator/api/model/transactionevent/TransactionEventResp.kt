package fr.simatix.cs.simulator.api.model.transactionevent

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.IdTokenInfoType
import fr.simatix.cs.simulator.api.model.common.MessageContentType

data class TransactionEventResp(
    val totalCost: Double? = null,
    val chargingPriority: Int? = 0,
    val idTokenInfo: IdTokenInfoType? = null,
    val updatedPersonalMessage: MessageContentType? = null,
): Response