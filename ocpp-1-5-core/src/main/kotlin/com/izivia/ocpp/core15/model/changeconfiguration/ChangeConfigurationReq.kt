package com.izivia.ocpp.core15.model.changeconfiguration

import com.izivia.ocpp.core15.model.Request

data class ChangeConfigurationReq(
    val key: String,
    val value: String
): Request
