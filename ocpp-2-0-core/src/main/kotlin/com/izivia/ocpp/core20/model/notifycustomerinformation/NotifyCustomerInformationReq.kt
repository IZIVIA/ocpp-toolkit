package com.izivia.ocpp.core20.model.notifycustomerinformation

import kotlinx.datetime.Instant

data class NotifyCustomerInformationReq(
    val data: String,
    val tbc: Boolean = false,
    val seqNo: Int,
    val generatedAt: Instant,
    val requestId: Int
)