package com.izivia.ocpp.soap

interface OcppSoapParser {
    fun <T> parseFromRequest(messageStr: String): SoapMessage<T>
    fun <T> mapToResponse(message: SoapMessage<T>): String
}