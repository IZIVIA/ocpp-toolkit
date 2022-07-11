package com.izivia.ocpp.http

data class HttpMessage(
    val ocppId: String,
    val action: String?,
    val payload: String
)
