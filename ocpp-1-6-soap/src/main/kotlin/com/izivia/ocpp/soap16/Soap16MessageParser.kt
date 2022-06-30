package com.izivia.ocpp.soap16

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectReader
import com.izivia.ocpp.soap.OcppSoapMapper
import com.izivia.ocpp.soap.SoapEnvelope

class Soap16MessageParser {

    val reader: ObjectReader = OcppSoapMapper
        .readerFor(object : TypeReference<SoapEnvelope<Ocpp16SoapBody>>() {})

    inline fun <reified T> parse(messageStr: String): T {
        val envelope: SoapEnvelope<Ocpp16SoapBody> = reader.readValue(messageStr)
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
