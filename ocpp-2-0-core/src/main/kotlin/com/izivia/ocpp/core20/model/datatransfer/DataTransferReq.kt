package com.izivia.ocpp.core20.model.datatransfer

data class DataTransferReq(
    val vendorId: String,
    val messageId: String? = null,
    val data: Any? = null,
)