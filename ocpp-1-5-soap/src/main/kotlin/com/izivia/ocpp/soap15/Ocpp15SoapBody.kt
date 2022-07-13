package com.izivia.ocpp.soap15

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver
import com.izivia.ocpp.core15.model.authorize.AuthorizeReq
import com.izivia.ocpp.core15.model.authorize.AuthorizeResp
import com.izivia.ocpp.core15.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core15.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core15.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core15.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core15.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core15.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.core15.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core15.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core15.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core15.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core15.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core15.model.metervalues.MeterValuesResp
import com.izivia.ocpp.core15.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core15.model.starttransaction.StartTransactionResp
import com.izivia.ocpp.core15.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core15.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core15.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core15.model.stoptransaction.StopTransactionResp
import com.izivia.ocpp.soap.SoapBody

@JsonIgnoreProperties(ignoreUnknown = true)
data class Ocpp15SoapBody(
    val authorizeRequest: AuthorizeReq?,
    val authorizeResponse: AuthorizeResp?,
    val bootNotificationRequest: BootNotificationReq?,
    val bootNotificationResponse: BootNotificationResp?,
    val dataTransferRequest: DataTransferReq?,
    val dataTransferResponse: DataTransferResp?,
    val diagnosticsStatusNotificationRequest: DiagnosticsStatusNotificationReq?,
    val diagnosticsStatusNotificationResponse: DiagnosticsStatusNotificationResp?,
    val firmwareStatusNotificationRequest: FirmwareStatusNotificationReq?,
    val firmwareStatusNotificationResponse: FirmwareStatusNotificationResp?,
    val heartbeatRequest: HeartbeatReq?,
    val heartbeatResponse: HeartbeatResp?,
    val meterValuesRequest: MeterValuesReq?,
    val meterValuesResponse: MeterValuesResp?,
    val startTransactionRequest: StartTransactionReq?,
    val startTransactionResponse: StartTransactionResp?,
    val statusNotificationRequest: StatusNotificationReq?,
    val statusNotificationResponse: StatusNotificationResp?,
    val stopTransactionRequest: StopTransactionReq?,
    val stopTransactionResponse: StopTransactionResp?,
) : SoapBody
