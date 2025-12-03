package com.izivia.ocpp.core15.model.datatransfer

import com.izivia.ocpp.core15.model.Request

data class DataTransferReq(
    val vendorId: String,
    val messageId: String? = null,
    val data: String? = null
): Request
