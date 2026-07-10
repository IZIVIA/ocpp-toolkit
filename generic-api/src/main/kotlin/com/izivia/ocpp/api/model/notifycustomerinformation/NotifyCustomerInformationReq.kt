package com.izivia.ocpp.api.model.notifycustomerinformation

import com.izivia.ocpp.api.model.Request
import kotlin.time.Instant

data class NotifyCustomerInformationReq(
    val data: String,
    val tbc: Boolean = false,
    val seqNo: Int,
    val generatedAt: Instant,
    val requestId: Int
): Request