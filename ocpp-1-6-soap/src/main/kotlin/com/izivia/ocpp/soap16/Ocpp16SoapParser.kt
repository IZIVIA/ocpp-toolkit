package com.izivia.ocpp.soap16

import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.izivia.ocpp.core16.model.authorize.AuthorizeReq
import com.izivia.ocpp.core16.model.authorize.AuthorizeResp
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core16.model.common.enumeration.Measurand
import com.izivia.ocpp.core16.model.common.enumeration.Phase
import com.izivia.ocpp.core16.model.common.enumeration.ReadingContext
import com.izivia.ocpp.core16.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core16.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.core16.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core16.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core16.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core16.model.metervalues.MeterValuesResp
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionResp
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core16.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core16.model.stoptransaction.StopTransactionResp
import com.izivia.ocpp.soap.*

class Ocpp16SoapParser : OcppSoapParser {

    internal val mapper: ObjectMapper = OcppSoapMapper
        .addMixIn(Measurand::class.java, EnumMixin::class.java)
        .addMixIn(ReadingContext::class.java, EnumMixin::class.java)
        .addMixIn(Phase::class.java, EnumMixin::class.java)
        .addMixIn(AuthorizeResp::class.java, AuthorizeRespMixin::class.java)
        .addMixIn(AuthorizeReq::class.java, AuthorizeReqMixin::class.java)
        .addMixIn(BootNotificationResp::class.java, BootNotificationRespMixin::class.java)
        .addMixIn(BootNotificationReq::class.java, BootNotificationReqMixin::class.java)
        .addMixIn(DataTransferResp::class.java, DataTransferRespMixin::class.java)
        .addMixIn(DataTransferReq::class.java, DataTransferReqMixin::class.java)
        .addMixIn(DiagnosticsStatusNotificationResp::class.java, DiagnosticsStatusNotificationRespMixin::class.java)
        .addMixIn(DiagnosticsStatusNotificationReq::class.java, DiagnosticsStatusNotificationReqMixin::class.java)
        .addMixIn(FirmwareStatusNotificationResp::class.java, FirmwareStatusNotificationRespMixin::class.java)
        .addMixIn(FirmwareStatusNotificationReq::class.java, FirmwareStatusNotificationReqMixin::class.java)
        .addMixIn(HeartbeatResp::class.java, HeartbeatRespMixin::class.java)
        .addMixIn(HeartbeatReq::class.java, HeartbeatReqMixin::class.java)
        .addMixIn(MeterValuesResp::class.java, MeterValuesRespMixin::class.java)
        .addMixIn(MeterValuesReq::class.java, MeterValuesReqMixin::class.java)
        .addMixIn(StartTransactionResp::class.java, StartTransactionRespMixin::class.java)
        .addMixIn(StartTransactionReq::class.java, StartTransactionReqMixin::class.java)
        .addMixIn(StatusNotificationResp::class.java, StatusNotificationRespMixin::class.java)
        .addMixIn(StatusNotificationReq::class.java, StatusNotificationReqMixin::class.java)
        .addMixIn(StopTransactionResp::class.java, StopTransactionRespMixin::class.java)
        .addMixIn(StopTransactionReq::class.java, StopTransactionReqMixin::class.java)

    override fun parseAnyRequestFromSoap(messageStr: String): RequestSoapMessage<Any> {
        val envelope: SoapEnvelope<Ocpp16SoapBody> = mapper
            .readerFor(object : TypeReference<SoapEnvelope<Ocpp16SoapBody>>() {})
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
            action = envelope.header.action.removePrefix("/"),
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
                    ${mapper.writeValueAsString(response.payload)}
                </soap:Body>
            </soap:Envelope>
        """.trimIndent()

    override fun <T> mapRequestToSoap(request: RequestSoapMessage<T>): String =
        """
            <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:cs="urn://Ocpp/Cs/2015/10/" xmlns:wsa5="http://www.w3.org/2005/08/addressing">
                <soap:Header>
                    <chargeBoxIdentity soap:mustUnderstand="true">${request.chargingStationId}</chargeBoxIdentity>
                    <From>
                        <Address>${request.from}</Address>
                    </From>
                    <MessageID>${request.messageId}</MessageID>
                    <ReplyTo soap:mustUnderstand="true">
                        <Address>http://www.w3.org/2005/08/addressing/anonymous</Address>
                    </ReplyTo>
                    <To soap:mustUnderstand="true">${request.to}</To>
                    <Action soap:mustUnderstand="true">/${request.action}</Action>
                </soap:Header>
                <soap:Body>
                    ${mapper.writeValueAsString(request.payload)}
                </soap:Body>
            </soap:Envelope>
        """.trimIndent()
}

abstract class EnumMixin(
    @JsonValue val value: String
)

@JsonRootName("authorizeResponse")
abstract class AuthorizeRespMixin

@JsonRootName("authorizeRequest")
abstract class AuthorizeReqMixin

@JsonRootName("bootNotificationResponse")
abstract class BootNotificationRespMixin

@JsonRootName("bootNotificationRequest")
abstract class BootNotificationReqMixin

@JsonRootName("dataTransferResponse")
abstract class DataTransferRespMixin

@JsonRootName("dataTransferRequest")
abstract class DataTransferReqMixin

@JsonRootName("diagnosticsStatusNotificationResponse")
abstract class DiagnosticsStatusNotificationRespMixin

@JsonRootName("diagnosticsStatusNotificationRequest")
abstract class DiagnosticsStatusNotificationReqMixin

@JsonRootName("firmwareStatusNotificationResponse")
abstract class FirmwareStatusNotificationRespMixin

@JsonRootName("firmwareStatusNotificationRequest")
abstract class FirmwareStatusNotificationReqMixin

@JsonRootName("heartbeatResponse")
abstract class HeartbeatRespMixin

@JsonRootName("heartbeatRequest")
abstract class HeartbeatReqMixin

@JsonRootName("meterValuesResponse")
abstract class MeterValuesRespMixin

@JsonRootName("meterValuesRequest")
abstract class MeterValuesReqMixin

@JsonRootName("startTransactionResponse")
abstract class StartTransactionRespMixin

@JsonRootName("startTransactionRequest")
abstract class StartTransactionReqMixin

@JsonRootName("statusNotificationResponse")
abstract class StatusNotificationRespMixin

@JsonRootName("statusNotificationRequest")
abstract class StatusNotificationReqMixin

@JsonRootName("stopTransactionResponse")
abstract class StopTransactionRespMixin

@JsonRootName("stopTransactionRequest")
abstract class StopTransactionReqMixin
