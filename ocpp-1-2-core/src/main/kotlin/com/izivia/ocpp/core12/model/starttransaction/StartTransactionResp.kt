package com.izivia.ocpp.core12.model.starttransaction

import com.izivia.ocpp.core12.model.Response
import com.izivia.ocpp.core12.model.common.IdTagInfo

data class StartTransactionResp(
    val idTagInfo: IdTagInfo,
    val transactionId: Int
): Response
