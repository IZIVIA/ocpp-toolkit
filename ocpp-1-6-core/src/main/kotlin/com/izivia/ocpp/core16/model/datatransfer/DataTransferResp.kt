package com.izivia.ocpp.core16.model.datatransfer

import com.izivia.ocpp.core16.model.datatransfer.enumeration.DataTransferStatus

data class DataTransferResp(
    val status: DataTransferStatus,
    val data: String? = null
)