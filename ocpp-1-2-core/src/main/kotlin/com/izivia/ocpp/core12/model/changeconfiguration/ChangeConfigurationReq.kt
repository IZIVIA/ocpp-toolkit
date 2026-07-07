package com.izivia.ocpp.core12.model.changeconfiguration

import com.izivia.ocpp.core12.model.Request

data class ChangeConfigurationReq(
    val key: String,
    val value: String
): Request
