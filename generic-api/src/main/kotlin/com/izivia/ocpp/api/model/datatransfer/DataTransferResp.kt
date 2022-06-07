package com.izivia.ocpp.api.model.datatransfer

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.datatransfer.enumeration.DataTransferStatusEnumType

data class DataTransferResp(
    val status: DataTransferStatusEnumType,
    val data: Any? = null,
    val statusInfo: StatusInfoType? = null
): Response