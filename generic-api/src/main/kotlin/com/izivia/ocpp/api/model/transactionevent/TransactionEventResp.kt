package com.izivia.ocpp.api.model.transactionevent

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.IdTokenInfoType
import com.izivia.ocpp.api.model.common.MessageContentType

data class TransactionEventResp(
    val totalCost: Double? = null,
    val chargingPriority: Int? = 0,
    val idTokenInfo: IdTokenInfoType? = null,
    val updatedPersonalMessage: MessageContentType? = null,
): Response