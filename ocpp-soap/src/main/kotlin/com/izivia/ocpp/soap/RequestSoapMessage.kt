package com.izivia.ocpp.soap

data class RequestSoapMessage<T>(
    val messageId: String,
    val chargingStationId: String,
    val action: String,
    val from: String,
    val to: String,
    val payload: T
)
