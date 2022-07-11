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

object Ocpp16SoapMapper : ObjectMapper(
    OcppSoapMapper
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
