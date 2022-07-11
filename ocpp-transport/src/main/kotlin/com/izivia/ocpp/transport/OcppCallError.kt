package com.izivia.ocpp.transport

data class OcppCallErrorException(
    val payload: String
) : Exception(payload)

data class OcppCallErrorPayload(val exception: String?)
