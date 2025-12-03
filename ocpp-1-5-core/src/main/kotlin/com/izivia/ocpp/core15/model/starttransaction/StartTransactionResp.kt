package com.izivia.ocpp.core15.model.starttransaction

import com.izivia.ocpp.core15.model.Response
import com.izivia.ocpp.core15.model.common.IdTagInfo

data class StartTransactionResp(
    val idTagInfo: IdTagInfo,
    val transactionId: Int
): Response
