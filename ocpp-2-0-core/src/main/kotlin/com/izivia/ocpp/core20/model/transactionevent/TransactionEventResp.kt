package com.izivia.ocpp.core20.model.transactionevent

import com.izivia.ocpp.core20.model.common.IdTokenInfoType
import com.izivia.ocpp.core20.model.common.MessageContentType

data class TransactionEventResp(
    val totalCost: Double? = null,
    val chargingPriority: Int? = 0,
    val idTokenInfo: IdTokenInfoType? = null,
    val updatedPersonalMessage: MessageContentType? = null
)
