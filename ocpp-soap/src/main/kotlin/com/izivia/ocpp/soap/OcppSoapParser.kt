package com.izivia.ocpp.soap

import kotlin.reflect.KClass

interface OcppSoapParser {
    fun parseAnyRequestFromSoap(messageStr: String): RequestSoapMessage<Any>
    fun parseAnyResponseFromSoap(messageStr: String): ResponseSoapMessage<Any>
    fun <T> mapRequestToSoap(request: RequestSoapMessage<T>): String
    fun <T> mapResponseToSoap(response: ResponseSoapMessage<T>): String
}

inline fun <reified T> OcppSoapParser.parseRequestFromSoap(messageStr: String): RequestSoapMessage<T> {
    val request = parseAnyRequestFromSoap(messageStr)
    if (request.payload !is T)
        throw IllegalArgumentException("The given request is not an instance of ${T::class.simpleName}. message = $messageStr")

    @Suppress("UNCHECKED_CAST")
    return request as RequestSoapMessage<T>
}

fun <T : Any> OcppSoapParser.parseRequestFromSoap(messageStr: String, clazz: KClass<T>): RequestSoapMessage<T> {
    val request = parseAnyRequestFromSoap(messageStr)
    if (request.payload::class != clazz)
        throw IllegalArgumentException("The given request is not an instance of ${clazz.simpleName}. message = $messageStr")

    @Suppress("UNCHECKED_CAST")
    return request as RequestSoapMessage<T>
}

inline fun <reified T> OcppSoapParser.parseResponseFromSoap(messageStr: String): ResponseSoapMessage<T> {
    val request = parseAnyResponseFromSoap(messageStr)
    if (request.payload !is T)
        throw IllegalArgumentException("The given response is not an instance of ${T::class.simpleName}. message = $messageStr")

    @Suppress("UNCHECKED_CAST")
    return request as ResponseSoapMessage<T>
}

fun <T : Any> OcppSoapParser.parseResponseFromSoap(messageStr: String, clazz: KClass<T>): ResponseSoapMessage<T> {
    val request = parseAnyResponseFromSoap(messageStr)
    if (request.payload::class != clazz)
        throw IllegalArgumentException("The given response is not an instance of ${clazz.simpleName}. message = $messageStr")

    @Suppress("UNCHECKED_CAST")
    return request as ResponseSoapMessage<T>
}
