package com.izivia.ocpp.soap16

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.izivia.ocpp.core16.model.authorize.AuthorizeReq
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core16.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core16.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core16.model.stoptransaction.StopTransactionReq

@JsonIgnoreProperties(ignoreUnknown = true)
data class SoapEnvelope(
    @JsonProperty("Header")
    val header: SoapHeader,
    @JsonProperty("Body")
    val body: SoapBody
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class SoapHeader(
    val chargeBoxIdentity: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class SoapBody(
    val authorizeRequest: AuthorizeReq?,
    val bootNotificationRequest: BootNotificationReq?,
    val dataTransferRequest: DataTransferReq?,
    val diagnosticsStatusNotificationRequest: DiagnosticsStatusNotificationReq?,
    val firmwareStatusNotificationRequest: FirmwareStatusNotificationReq?,
    val heartbeatRequest: HeartbeatReq?,
    val meterValuesRequest: MeterValuesReq?,
    val startTransactionRequest: StartTransactionReq?,
    val statusNotificationRequest: StatusNotificationReq?,
    val stopTransactionRequest: StopTransactionReq?,
)