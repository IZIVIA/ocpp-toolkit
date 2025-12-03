package com.izivia.ocpp.core15.model.getconfiguration

import com.izivia.ocpp.core15.model.Request

data class GetConfigurationReq(
    val key: List<String>? = null
): Request
