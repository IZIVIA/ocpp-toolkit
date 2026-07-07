package com.izivia.ocpp.core12.model.stoptransaction

import com.izivia.ocpp.core12.model.Response
import com.izivia.ocpp.core12.model.common.IdTagInfo

data class StopTransactionResp(
    val idTagInfo: IdTagInfo? = null
): Response
