package com.izivia.ocpp.core16.model.starttransaction

import com.izivia.ocpp.core16.model.common.IdTagInfo

data class StartTransactionResp(
    val idTagInfo: IdTagInfo,
    val transactionId: Int
)