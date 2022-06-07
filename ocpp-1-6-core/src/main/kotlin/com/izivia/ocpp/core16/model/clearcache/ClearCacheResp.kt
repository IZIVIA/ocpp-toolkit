package com.izivia.ocpp.core16.model.clearcache

import com.izivia.ocpp.core16.model.clearcache.enumeration.ClearCacheStatus

data class ClearCacheResp(
    val status: ClearCacheStatus
)