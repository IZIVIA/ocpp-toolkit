package com.izivia.ocpp.core15.model.datatransfer

data class DataTransferReq(
    val vendorId: String,
    val messageId: String? = null,
    val data: String? = null
)