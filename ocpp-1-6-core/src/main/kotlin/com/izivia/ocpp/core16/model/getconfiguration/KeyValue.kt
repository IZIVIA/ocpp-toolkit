package com.izivia.ocpp.core16.model.getconfiguration

data class KeyValue(
    val key: String,
    val readonly: Boolean,
    val value: String? = null
)