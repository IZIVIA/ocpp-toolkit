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
            envelope.body.statusNotificationRequest != null -> envelope.body.statusNotificationRequest as T
            envelope.body.heartbeatRequest != null -> envelope.body.heartbeatRequest as T
            else -> throw IllegalArgumentException("This message type has not been implemented yet.")
        }
    }
}
