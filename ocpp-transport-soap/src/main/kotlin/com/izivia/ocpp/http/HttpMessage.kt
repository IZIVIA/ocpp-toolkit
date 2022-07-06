package com.izivia.ocpp.http

data class HttpMessage(
    val msgId: String,
    val action: String?,
    val payload: String
)
