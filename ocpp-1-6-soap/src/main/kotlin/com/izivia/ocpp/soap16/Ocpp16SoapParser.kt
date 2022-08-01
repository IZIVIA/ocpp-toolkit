package com.izivia.ocpp.soap16

import com.fasterxml.jackson.core.type.TypeReference
import com.izivia.ocpp.soap.OcppSoapParser
import com.izivia.ocpp.soap.RequestSoapMessage
import com.izivia.ocpp.soap.ResponseSoapMessage
import com.izivia.ocpp.soap.SoapEnvelope

class Ocpp16SoapParser : OcppSoapParser {

    private val mapper = Ocpp16SoapObjectMapper

    override fun parseAnyRequestFromSoap(messageStr: String): RequestSoapMessage<Any> {
        val envelope: SoapEnvelope<Ocpp16SoapBody> = mapper
            .readerFor(object : TypeReference<SoapEnvelope<Ocpp16SoapBody>>() {})
            .readValue(messageStr)
        return RequestSoapMessage(
            messageId = envelope.header.messageId,
            chargingStationId = envelope.header.chargeBoxIdentity!!,
            action = envelope.header.action.removePrefix("/"),
            from = envelope.header.from?.address,
            to = envelope.header.to,
            payload = getRequestBodyContent(envelope)
        )
    }

    private fun getRequestBodyContent(envelope: SoapEnvelope<Ocpp16SoapBody>): Any = when {
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
        val envelope: SoapEnvelope<Ocpp16SoapBody> = mapper
            .readerFor(object : TypeReference<SoapEnvelope<Ocpp16SoapBody>>() {})
            .readValue(messageStr)
        return ResponseSoapMessage(
            messageId = envelope.header.messageId,
            relatesTo = envelope.header.relatesTo
                ?: throw IllegalArgumentException("Malformed envelope: missing <RelatesTo> in the header. envelope = $envelope"),
            action = envelope.header.action.removePrefix("/").removeSuffix("Response"),
            payload = getResponseBodyContent(envelope)
        )
    }

    private fun getResponseBodyContent(envelope: SoapEnvelope<Ocpp16SoapBody>): Any = when {
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

    override fun <T> mapResponseToSoap(response: ResponseSoapMessage<T>): String =
        """
            <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope">
                <soap:Header>
                    <Action xmlns="http://www.w3.org/2005/08/addressing">/${response.action}Response</Action>
                    <MessageID xmlns="http://www.w3.org/2005/08/addressing">${response.messageId}</MessageID>
                    <To xmlns="http://www.w3.org/2005/08/addressing">http://www.w3.org/2005/08/addressing/anonymous</To>
                    <RelatesTo xmlns="http://www.w3.org/2005/08/addressing">${response.relatesTo}</RelatesTo>
                </soap:Header>
                <soap:Body>
                    ${mapper.writeValueAsString(response.payload).addXmlNamespace()}
                </soap:Body>
            </soap:Envelope>
        """.trimIndent()

    override fun <T> mapRequestToSoap(request: RequestSoapMessage<T>): String =
        """
            <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:cs="urn://Ocpp/Cs/2015/10/" xmlns:wsa5="http://www.w3.org/2005/08/addressing">
                <soap:Header>
                    <chargeBoxIdentity soap:mustUnderstand="true">${request.chargingStationId}</chargeBoxIdentity>
                    <MessageID>${request.messageId}</MessageID>
                    ${request.from?.let { "<From><Address>$it</Address></From>" }}
                    <ReplyTo soap:mustUnderstand="true">
                        <Address>http://www.w3.org/2005/08/addressing/anonymous</Address>
                    </ReplyTo>
                    ${request.to?.let { "<To soap:mustUnderstand=\"true\">$it</To>" }}
                    <Action soap:mustUnderstand="true">/${request.action}</Action>
                </soap:Header>
                <soap:Body>
                    ${mapper.writeValueAsString(request.payload)}
                </soap:Body>
            </soap:Envelope>
        """.trimIndent()
}

private fun String.addXmlNamespace(): String =
    if (matches(".*/>\$".toRegex()))
        split("/")[0] + " xmlns=\"urn://Ocpp/Cs/2015/10/\"/>"
    else
        split(">".toRegex(), limit = 2)
            .let { (first, second) ->
                first.removeSuffix(">") + " xmlns=\"urn://Ocpp/Cs/2015/10/\">" + second
            }

