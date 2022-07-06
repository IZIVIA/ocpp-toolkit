package com.izivia.ocpp.soap

interface OcppSoapParser {
    fun <T> parseRequestFromSoap(messageStr: String): RequestSoapMessage<T>
    fun <T> parseResponseFromSoap(messageStr: String): ResponseSoapMessage<T>
    fun <T> mapRequestToSoap(request: RequestSoapMessage<T>): String
    fun <T> mapResponseToSoap(response: ResponseSoapMessage<T>): String
}