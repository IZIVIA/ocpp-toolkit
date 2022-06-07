package com.izivia.ocpp.core20.model.datatransfer

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.datatransfer.enumeration.DataTransferStatusEnumType

data class DataTransferResp(
    val status: DataTransferStatusEnumType,
    val data: Any? = null,
    val statusInfo: StatusInfoType? = null
)