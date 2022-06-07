package com.izivia.ocpp.core20.model.clearcache

import com.izivia.ocpp.core20.model.clearcache.enumeration.ClearCacheStatusEnumType
import com.izivia.ocpp.core20.model.common.StatusInfoType

data class ClearCacheResp(
    val status: ClearCacheStatusEnumType,
    val statusInfo: StatusInfoType? = null
)