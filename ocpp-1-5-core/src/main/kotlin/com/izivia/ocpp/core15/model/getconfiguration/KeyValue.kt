package com.izivia.ocpp.core15.model.getconfiguration

data class KeyValue(
    val key: String,
    val readonly: Boolean,
    val value: String? = null
)