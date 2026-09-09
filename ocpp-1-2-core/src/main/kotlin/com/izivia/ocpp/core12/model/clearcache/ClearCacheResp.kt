package com.izivia.ocpp.core12.model.clearcache

import com.izivia.ocpp.core12.model.Response
import com.izivia.ocpp.core12.model.clearcache.enumeration.ClearCacheStatus

data class ClearCacheResp(
    val status: ClearCacheStatus
): Response
