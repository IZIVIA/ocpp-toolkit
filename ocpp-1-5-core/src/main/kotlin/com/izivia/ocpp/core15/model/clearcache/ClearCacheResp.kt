package com.izivia.ocpp.core15.model.clearcache

import com.izivia.ocpp.core15.model.Response
import com.izivia.ocpp.core15.model.clearcache.enumeration.ClearCacheStatus

data class ClearCacheResp(
    val status: ClearCacheStatus
): Response
