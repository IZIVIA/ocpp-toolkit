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

    override fun <T> parseRequestFromSoap(messageStr: String): RequestSoapMessage<T> {
        val envelope: SoapEnvelope<Ocpp16SoapBody> = mapper
            .readerFor(object : TypeReference<SoapEnvelope<Ocpp16SoapBody>>() {})
            .readValue(messageStr)
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
            else -> throw IllegalArgumentException("Unknown request message operation.")
        }.let {
            RequestSoapMessage(
                messageId = envelope.header.messageId,
                chargingStationId = envelope.header.chargeBoxIdentity!!,
                action = envelope.header.action.removePrefix("/"),
                from = envelope.header.from?.address
                    ?: throw IllegalArgumentException("Malformed envelope: missing <From> in the header"),
                to = envelope.header.to
                    ?: throw IllegalArgumentException("Malformed envelope: missing <To> in the header"),
                payload = it
            )
        }
    }

    override fun <T> parseResponseFromSoap(messageStr: String): ResponseSoapMessage<T> {
        val envelope: SoapEnvelope<Ocpp16SoapBody> = mapper
            .readerFor(object : TypeReference<SoapEnvelope<Ocpp16SoapBody>>() {})
            .readValue(messageStr)
        return when {
            envelope.body.authorizeResponse != null -> envelope.body.authorizeResponse as T
            envelope.body.bootNotificationResponse != null -> envelope.body.bootNotificationResponse as T
            envelope.body.dataTransferResponse != null -> envelope.body.dataTransferResponse as T
            envelope.body.diagnosticsStatusNotificationResponse != null -> envelope.body.diagnosticsStatusNotificationResponse as T
            envelope.body.firmwareStatusNotificationResponse != null -> envelope.body.firmwareStatusNotificationResponse as T
            envelope.body.heartbeatResponse != null -> envelope.body.heartbeatResponse as T
            envelope.body.meterValuesResponse != null -> envelope.body.meterValuesResponse as T
            envelope.body.startTransactionResponse != null -> envelope.body.startTransactionResponse as T
            envelope.body.statusNotificationResponse != null -> envelope.body.statusNotificationResponse as T
            envelope.body.stopTransactionResponse != null -> envelope.body.stopTransactionResponse as T
            else -> throw IllegalArgumentException("Unknown response message operation.")
        }.let {
            ResponseSoapMessage(
                messageId = envelope.header.messageId,
                relatesTo = envelope.header.relatesTo
                    ?: throw IllegalArgumentException("Malformed envelope: missing <RelatesTo> in the header"),
                action = envelope.header.action.removePrefix("/"),
                payload = it
            )
        }
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
