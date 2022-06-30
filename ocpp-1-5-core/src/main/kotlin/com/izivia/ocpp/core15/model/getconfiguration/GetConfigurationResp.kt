package com.izivia.ocpp.core15.model.getconfiguration

data class GetConfigurationResp(
    val configurationKey: List<KeyValue>? = null,
    val unknownKey: List<String>? = null
)