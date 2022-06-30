package com.izivia.ocpp.soap16

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule

class Soap16MessageParser {

    val mapper: ObjectMapper = XmlMapper()
        .registerModule(kotlinModule())
        .registerModule(JavaTimeModule())
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)

    inline fun <reified T> parse(messageStr: String): T {
        val envelope = mapper.readValue(messageStr, SoapEnvelope::class.java)
        return when {
            envelope.body.authorizeRequest != null -> envelope.body.authorizeRequest as T
            envelope.body.bootNotificationRequest != null -> envelope.body.bootNotificationRequest as T
            envelope.body.dataTransferRequest != null -> envelope.body.dataTransferRequest as T
            envelope.body.diagnosticsStatusNotificationRequest != null -> envelope.body.diagnosticsStatusNotificationRequest as T
            envelope.body.firmwareStatusNotificationRequest != null -> envelope.body.firmwareStatusNotificationRequest as T
            envelope.body.heartbeatRequest != null -> envelope.body.heartbeatRequest as T
            envelope.body.meterValuesRequest != null -> envelope.body.meterValuesRequest as T
            envelope.body.startTransactionRequest != null -> envelope.body.startTransactionRequest as T
            envelope.body.statusNotificationRequest != null -> envelope.body.statusNotificationRequest as T
            envelope.body.stopTransactionRequest != null -> envelope.body.stopTransactionRequest as T
            else -> throw IllegalArgumentException("Unknown message operation.")
        }
    }
}
