package com.izivia.ocpp.soap

data class SoapMessage<T>(
    val ocppId: String,
    val chargingStationId: String,
    val action: String,
    val payload: T
)
