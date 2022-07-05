package com.izivia.ocpp.soap16

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.izivia.ocpp.core16.model.authorize.AuthorizeResp
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core16.model.common.enumeration.Measurand
import com.izivia.ocpp.core16.model.common.enumeration.Phase
import com.izivia.ocpp.core16.model.common.enumeration.ReadingContext
import com.izivia.ocpp.core16.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.core16.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core16.model.metervalues.MeterValuesResp
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionResp
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core16.model.stoptransaction.StopTransactionResp
import com.izivia.ocpp.soap.OcppSoapMapper
import com.izivia.ocpp.soap.SoapEnvelope
import com.izivia.ocpp.soap.SoapMessage
import java.util.*

class Soap16MessageParser {

    private val mapper: ObjectMapper = OcppSoapMapper
        .addMixIn(Measurand::class.java, EnumMixin::class.java)
        .addMixIn(ReadingContext::class.java, EnumMixin::class.java)
        .addMixIn(Phase::class.java, EnumMixin::class.java)
        .addMixIn(AuthorizeResp::class.java, AuthorizeRespMixin::class.java)
        .addMixIn(BootNotificationResp::class.java, BootNotificationRespMixin::class.java)
        .addMixIn(DataTransferResp::class.java, DataTransferRespMixin::class.java)
        .addMixIn(DiagnosticsStatusNotificationResp::class.java, DiagnosticsStatusNotificationRespMixin::class.java)
        .addMixIn(FirmwareStatusNotificationResp::class.java, FirmwareStatusNotificationRespMixin::class.java)
        .addMixIn(HeartbeatResp::class.java, HeartbeatRespMixin::class.java)
        .addMixIn(MeterValuesResp::class.java, MeterValuesRespMixin::class.java)
        .addMixIn(StartTransactionResp::class.java, StartTransactionRespMixin::class.java)
        .addMixIn(StatusNotificationResp::class.java, StatusNotificationRespMixin::class.java)
        .addMixIn(StopTransactionResp::class.java, StopTransactionRespMixin::class.java)

    fun <T> parseFromRequest(messageStr: String): SoapMessage<T> {
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
            else -> throw IllegalArgumentException("Unknown message operation.")
        }.let {
            SoapMessage(
                ocppId = envelope.header.messageId,
                chargingStationId = envelope.header.chargeBoxIdentity!!,
                action = envelope.header.action.removeSuffix("/"),
                payload = it
            )
        }
    }

    fun <T> mapToResponse(message: SoapMessage<T>): String =
        """
            <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope">
                <soap:Header>
                    <Action xmlns="http://www.w3.org/2005/08/addressing">/${message.action}Response</Action>
                    <MessageID xmlns="http://www.w3.org/2005/08/addressing">urn:uuid:${UUID.randomUUID()}</MessageID>
                    <To xmlns="http://www.w3.org/2005/08/addressing">http://www.w3.org/2005/08/addressing/anonymous</To>
                    <RelatesTo xmlns="http://www.w3.org/2005/08/addressing">${message.ocppId}</RelatesTo>
                </soap:Header>
                <soap:Body>
                    ${mapper.writeValueAsString(message.payload)}
                </soap:Body>
            </soap:Envelope>
        """.trimIndent()
}

abstract class EnumMixin(
    @JsonValue val value: String
)

@JsonRootName("authorizeResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class AuthorizeRespMixin

@JsonRootName("bootNotificationResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class BootNotificationRespMixin

@JsonRootName("dataTransferResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class DataTransferRespMixin

@JsonRootName("diagnosticsStatusNotificationResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class DiagnosticsStatusNotificationRespMixin

@JsonRootName("firmwareStatusNotificationResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class FirmwareStatusNotificationRespMixin

@JsonRootName("heartbeatResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class HeartbeatRespMixin

@JsonRootName("meterValuesResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class MeterValuesRespMixin

@JsonRootName("startTransactionResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class StartTransactionRespMixin

@JsonRootName("statusNotificationResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class StatusNotificationRespMixin

@JsonRootName("stopTransactionResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class StopTransactionRespMixin
