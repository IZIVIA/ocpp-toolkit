package com.izivia.ocpp.api.model.datatransfer

import com.izivia.ocpp.api.model.Request

data class DataTransferReq(
    val vendorId: String,
    val messageId: String? = null,
    val data: Any? = null,
): Request