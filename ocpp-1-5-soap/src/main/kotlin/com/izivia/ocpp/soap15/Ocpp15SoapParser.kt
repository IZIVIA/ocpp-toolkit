package com.izivia.ocpp.soap15

import com.fasterxml.jackson.core.type.TypeReference
import com.izivia.ocpp.soap.*


class Ocpp15SoapParser : OcppSoapParser {

    private val mapper = Ocpp15SoapMapper
    private var NS_OCPP: String = "urn://Ocpp/Cp/2012/06/"

    override fun parseAnyRequestFromSoap(messageStr: String): RequestSoapMessage<Any> {
        val envelope: SoapEnvelope<Ocpp15SoapBody> = Ocpp15SoapMapperIn
            .readerFor(object : TypeReference<SoapEnvelope<Ocpp15SoapBody>>() {})
            .readValue(messageStr)
        return RequestSoapMessage(
            messageId = envelope.header.messageId,
            chargingStationId = envelope.header.chargeBoxIdentity!!,
            action = envelope.header.action.removePrefix("/"),
            from = envelope.header.from?.address
                ?: throw IllegalArgumentException("Malformed envelope: missing <From> in the header. envelope = $envelope"),
            to = envelope.header.to
                ?: throw IllegalArgumentException("Malformed envelope: missing <To> in the header. envelope = $envelope"),
            payload = getRequestBodyContent(envelope)
        )
    }

    private fun getRequestBodyContent(envelope: SoapEnvelope<Ocpp15SoapBody>): Any = when {
        envelope.body.authorizeRequest != null -> envelope.body.authorizeRequest!!
        envelope.body.bootNotificationRequest != null -> envelope.body.bootNotificationRequest!!
        envelope.body.dataTransferRequest != null -> envelope.body.dataTransferRequest!!
        envelope.body.diagnosticsStatusNotificationRequest != null -> envelope.body.diagnosticsStatusNotificationRequest!!
        envelope.body.firmwareStatusNotificationRequest != null -> envelope.body.firmwareStatusNotificationRequest!!
        envelope.body.heartbeatRequest != null -> envelope.body.heartbeatRequest!!
        envelope.body.meterValuesRequest != null -> envelope.body.meterValuesRequest!!
        envelope.body.startTransactionRequest != null -> envelope.body.startTransactionRequest!!
        envelope.body.statusNotificationRequest != null -> envelope.body.statusNotificationRequest!!
        envelope.body.stopTransactionRequest != null -> envelope.body.stopTransactionRequest!!
        else -> throw IllegalArgumentException("Unknown request message operation. enveloppe = $envelope")
    }

    override fun parseAnyResponseFromSoap(messageStr: String): ResponseSoapMessage<Any> {
        val envelope: SoapEnvelope<Ocpp15SoapBody> = Ocpp15SoapMapperIn
            .readerFor(object : TypeReference<SoapEnvelope<Ocpp15SoapBody>>() {})
            .readValue(messageStr)
        return ResponseSoapMessage(
            messageId = envelope.header.messageId,
            relatesTo = envelope.header.relatesTo
                ?: throw IllegalArgumentException("Malformed envelope: missing <RelatesTo> in the header. envelope = $envelope"),
            action = envelope.header.action.removePrefix("/"),
            payload = getResponseBodyContent(envelope)
        )
    }

    private fun getResponseBodyContent(envelope: SoapEnvelope<Ocpp15SoapBody>): Any = when {
        envelope.body.authorizeResponse != null -> envelope.body.authorizeResponse!!
        envelope.body.bootNotificationResponse != null -> envelope.body.bootNotificationResponse!!
        envelope.body.dataTransferResponse != null -> envelope.body.dataTransferResponse!!
        envelope.body.diagnosticsStatusNotificationResponse != null -> envelope.body.diagnosticsStatusNotificationResponse!!
        envelope.body.firmwareStatusNotificationResponse != null -> envelope.body.firmwareStatusNotificationResponse!!
        envelope.body.heartbeatResponse != null -> envelope.body.heartbeatResponse!!
        envelope.body.meterValuesResponse != null -> envelope.body.meterValuesResponse!!
        envelope.body.startTransactionResponse != null -> envelope.body.startTransactionResponse!!
        envelope.body.statusNotificationResponse != null -> envelope.body.statusNotificationResponse!!
        envelope.body.stopTransactionResponse != null -> envelope.body.stopTransactionResponse!!
        else -> throw IllegalArgumentException("Unknown response message operation. enveloppe = $envelope")
    }

    override fun <T> mapResponseToSoap(response: ResponseSoapMessage<T>): String {
        val action: String = response.action.replaceFirstChar { it.lowercase() } + "Response"
        val headers = SoapHeaderOut(messageId = response.messageId,
                                 action = "/" + action,
                                 to = "http://www.w3.org/2005/08/addressing/anonymous",
                                 relatesTo = response.relatesTo,
                                 from = null,
                                 chargeBoxIdentity = null)
        val xmlMapper = Ocpp15SoapMapper
        val xmlBuilder = SoapEnvelopeOut(header = headers,
                                         body = response.payload,
                                         ocpp=NS_OCPP)
        return xmlMapper.writeValueAsString(xmlBuilder)
    }

    override fun <T> mapRequestToSoap(request: RequestSoapMessage<T>): String {
        val headers = SoapHeaderOut(messageId = request.messageId,
            action = "/" + request.action,
            to = "http://www.w3.org/2005/08/addressing/anonymous",
            relatesTo = null,
            from = SoapHeaderFromOut(request.from),
            chargeBoxIdentity = request.chargingStationId)
        val xmlMapper = Ocpp15SoapMapper
        val xmlBuilder = SoapEnvelopeOut(header = headers,
                                         body = request.payload,
                                         ocpp=NS_OCPP)
        return xmlMapper.writeValueAsString(xmlBuilder)
    }
}

private fun String.addXmlNamespace(): String =
    if (matches(".*/>\$".toRegex()))
        split("/")[0] + " xmlns=\"urn://Ocpp/Cs/2012/06/\"/>"
    else
        split(">".toRegex(), limit = 2)
            .let { (first, second) ->
                first.removeSuffix(">") + " xmlns=\"urn://Ocpp/Cs/2012/06/\">" + second
            }
