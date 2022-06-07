package com.izivia.ocpp.api.model.getallvariables

data class KeyValue(
    val key: String,
    val readonly: Boolean,
    val value: String? = null
)