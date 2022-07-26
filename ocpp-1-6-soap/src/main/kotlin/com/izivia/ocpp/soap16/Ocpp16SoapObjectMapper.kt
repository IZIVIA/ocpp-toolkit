package com.izivia.ocpp.soap16

import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.annotation.JsonValue
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
import com.izivia.ocpp.soap.OcppSoapMapper

internal object Ocpp16SoapObjectMapper : ObjectMapper(
    OcppSoapMapper()
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
)

private abstract class EnumMixin(
    @JsonValue val value: String
)

@JsonRootName("authorizeResponse")
private abstract class AuthorizeRespMixin

@JsonRootName("authorizeRequest")
private abstract class AuthorizeReqMixin

@JsonRootName("bootNotificationResponse")
private abstract class BootNotificationRespMixin

@JsonRootName("bootNotificationRequest")
private abstract class BootNotificationReqMixin

@JsonRootName("dataTransferResponse")
private abstract class DataTransferRespMixin

@JsonRootName("dataTransferRequest")
private abstract class DataTransferReqMixin

@JsonRootName("diagnosticsStatusNotificationResponse")
private abstract class DiagnosticsStatusNotificationRespMixin

@JsonRootName("diagnosticsStatusNotificationRequest")
private abstract class DiagnosticsStatusNotificationReqMixin

@JsonRootName("firmwareStatusNotificationResponse")
private abstract class FirmwareStatusNotificationRespMixin

@JsonRootName("firmwareStatusNotificationRequest")
private abstract class FirmwareStatusNotificationReqMixin

@JsonRootName("heartbeatResponse")
private abstract class HeartbeatRespMixin

@JsonRootName("heartbeatRequest")
private abstract class HeartbeatReqMixin

@JsonRootName("meterValuesResponse")
private abstract class MeterValuesRespMixin

@JsonRootName("meterValuesRequest")
private abstract class MeterValuesReqMixin

@JsonRootName("startTransactionResponse")
private abstract class StartTransactionRespMixin

@JsonRootName("startTransactionRequest")
private abstract class StartTransactionReqMixin

@JsonRootName("statusNotificationResponse")
private abstract class StatusNotificationRespMixin

@JsonRootName("statusNotificationRequest")
private abstract class StatusNotificationReqMixin

@JsonRootName("stopTransactionResponse")
private abstract class StopTransactionRespMixin

@JsonRootName("stopTransactionRequest")
private abstract class StopTransactionReqMixin
