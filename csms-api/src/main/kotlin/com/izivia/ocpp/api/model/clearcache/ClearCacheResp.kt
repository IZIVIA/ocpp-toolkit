package com.izivia.ocpp.api.model.clearcache

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.clearcache.enumeration.ClearCacheStatusEnumType
import com.izivia.ocpp.api.model.common.StatusInfoType

data class ClearCacheResp(
    val status: ClearCacheStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response