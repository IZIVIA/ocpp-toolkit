package com.izivia.ocpp.soap

data class ResponseSoapMessage<T>(
    val action: String,
    val messageId: String,
    val relatesTo: String,
    val payload: T
)
