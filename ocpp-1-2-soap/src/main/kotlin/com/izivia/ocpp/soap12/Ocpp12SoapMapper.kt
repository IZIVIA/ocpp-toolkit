package com.izivia.ocpp.soap12

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.izivia.ocpp.core12.model.authorize.AuthorizeReq
import com.izivia.ocpp.core12.model.authorize.AuthorizeResp
import com.izivia.ocpp.core12.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core12.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core12.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core12.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.core12.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.core12.model.changeconfiguration.ChangeConfigurationResp
import com.izivia.ocpp.core12.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core12.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core12.model.common.IdTagInfo
import com.izivia.ocpp.core12.model.common.MeterValue
import com.izivia.ocpp.core12.model.common.enumeration.AuthorizationStatus
import com.izivia.ocpp.core12.model.common.enumeration.RemoteStartStopStatus
import com.izivia.ocpp.core12.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core12.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.core12.model.diagnosticsstatusnotification.enumeration.DiagnosticsStatus
import com.izivia.ocpp.core12.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core12.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core12.model.firmwarestatusnotification.enumeration.FirmwareStatus
import com.izivia.ocpp.core12.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core12.model.getdiagnostics.GetDiagnosticsResp
import com.izivia.ocpp.core12.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core12.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core12.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core12.model.metervalues.MeterValuesResp
import com.izivia.ocpp.core12.model.remotestart.RemoteStartTransactionReq
import com.izivia.ocpp.core12.model.remotestart.RemoteStartTransactionResp
import com.izivia.ocpp.core12.model.remotestop.RemoteStopTransactionReq
import com.izivia.ocpp.core12.model.remotestop.RemoteStopTransactionResp
import com.izivia.ocpp.core12.model.reset.ResetReq
import com.izivia.ocpp.core12.model.reset.ResetResp
import com.izivia.ocpp.core12.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core12.model.starttransaction.StartTransactionResp
import com.izivia.ocpp.core12.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core12.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core12.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core12.model.stoptransaction.StopTransactionResp
import com.izivia.ocpp.core12.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core12.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core12.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core12.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.soap.EnumMixin
import com.izivia.ocpp.soap.FaultCode
import com.izivia.ocpp.soap.FaultCodeMixin
import com.izivia.ocpp.soap.FaultReason
import com.izivia.ocpp.soap.FaultReasonMixin
import com.izivia.ocpp.soap.FaultSubCode
import com.izivia.ocpp.soap.FaultSubCodeMixin
import com.izivia.ocpp.soap.OcppSoapMapper
import com.izivia.ocpp.soap.SoapFault
import com.izivia.ocpp.soap.SoapFaultMixin
import kotlin.time.Instant

internal object Ocpp12SoapMapperIn : ObjectMapper(
    OcppSoapMapper()
)

internal object Ocpp12SoapMapper : ObjectMapper(
    OcppSoapMapper()
        .addMixIn(IdTagInfo::class.java, IdTagInfoMixin::class.java)
        .addMixIn(AuthorizeReq::class.java, AuthorizeReqMixin::class.java)
        .addMixIn(AuthorizeResp::class.java, AuthorizeRespMixin::class.java)
        .addMixIn(BootNotificationReq::class.java, BootNotificationReqMixin::class.java)
        .addMixIn(BootNotificationResp::class.java, BootNotificationRespMixin::class.java)
        .addMixIn(ChangeAvailabilityReq::class.java, ChangeAvailabilityReqMixin::class.java)
        .addMixIn(ChangeAvailabilityResp::class.java, ChangeAvailabilityRespMixin::class.java)
        .addMixIn(ChangeConfigurationReq::class.java, ChangeConfigurationReqMixin::class.java)
        .addMixIn(ChangeConfigurationResp::class.java, ChangeConfigurationRespMixin::class.java)
        .addMixIn(ClearCacheReq::class.java, ClearCacheReqMixin::class.java)
        .addMixIn(ClearCacheResp::class.java, ClearCacheRespMixin::class.java)
        .addMixIn(DiagnosticsStatusNotificationReq::class.java, DiagnosticsStatusNotificationReqMixin::class.java)
        .addMixIn(DiagnosticsStatusNotificationResp::class.java, DiagnosticsStatusNotificationRespMixin::class.java)
        .addMixIn(FirmwareStatusNotificationReq::class.java, FirmwareStatusNotificationReqMixin::class.java)
        .addMixIn(FirmwareStatusNotificationResp::class.java, FirmwareStatusNotificationRespMixin::class.java)
        .addMixIn(GetDiagnosticsReq::class.java, GetDiagnosticsReqMixin::class.java)
        .addMixIn(GetDiagnosticsResp::class.java, GetDiagnosticsRespMixin::class.java)
        .addMixIn(HeartbeatReq::class.java, HeartbeatReqMixin::class.java)
        .addMixIn(HeartbeatResp::class.java, HeartbeatRespMixin::class.java)
        .addMixIn(MeterValue::class.java, MeterValueMixin::class.java)
        .addMixIn(MeterValuesReq::class.java, MeterValuesReqMixin::class.java)
        .addMixIn(MeterValuesResp::class.java, MeterValuesRespMixin::class.java)
        .addMixIn(RemoteStartTransactionReq::class.java, RemoteStartTransactionReqMixin::class.java)
        .addMixIn(RemoteStartTransactionResp::class.java, RemoteStartTransactionRespMixin::class.java)
        .addMixIn(RemoteStopTransactionReq::class.java, RemoteStopTransactionReqMixin::class.java)
        .addMixIn(RemoteStopTransactionResp::class.java, RemoteStopTransactionRespMixin::class.java)
        .addMixIn(ResetReq::class.java, ResetReqMixin::class.java)
        .addMixIn(ResetResp::class.java, ResetRespMixin::class.java)
        .addMixIn(StartTransactionReq::class.java, StartTransactionReqMixin::class.java)
        .addMixIn(StartTransactionResp::class.java, StartTransactionRespMixin::class.java)
        .addMixIn(StatusNotificationReq::class.java, StatusNotificationReqMixin::class.java)
        .addMixIn(StatusNotificationResp::class.java, StatusNotificationRespMixin::class.java)
        .addMixIn(StopTransactionReq::class.java, StopTransactionReqMixin::class.java)
        .addMixIn(StopTransactionResp::class.java, StopTransactionRespMixin::class.java)
        .addMixIn(UnlockConnectorReq::class.java, UnlockConnectorReqMixin::class.java)
        .addMixIn(UnlockConnectorResp::class.java, UnlockConnectorRespMixin::class.java)
        .addMixIn(UpdateFirmwareReq::class.java, UpdateFirmwareReqMixin::class.java)
        .addMixIn(UpdateFirmwareResp::class.java, UpdateFirmwareRespMixin::class.java)
        .addMixIn(AuthorizationStatus::class.java, EnumMixin::class.java)
        .addMixIn(RemoteStartStopStatus::class.java, EnumMixin::class.java)
        .addMixIn(DiagnosticsStatus::class.java, EnumMixin::class.java)
        .addMixIn(FirmwareStatus::class.java, EnumMixin::class.java)
        .addMixIn(SoapFault::class.java, SoapFaultMixin::class.java)
        .addMixIn(FaultCode::class.java, FaultCodeMixin::class.java)
        .addMixIn(FaultReason::class.java, FaultReasonMixin::class.java)
        .addMixIn(FaultSubCode::class.java, FaultSubCodeMixin::class.java)
)

private abstract class IdTagInfoMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: AuthorizationStatus,
    @JacksonXmlProperty(localName = "o:expiryDate")
    val expiryDate: Instant? = null,
    @JacksonXmlProperty(localName = "o:parentIdTag")
    val parentIdTag: String? = null
)

@JsonRootName("authorizeRequest")
private abstract class AuthorizeReqMixin(@JacksonXmlProperty(localName = "o:idTag") val idTag: String)

@JsonRootName("authorizeResponse")
private abstract class AuthorizeRespMixin(@JacksonXmlProperty(localName = "o:idTagInfo") val idTagInfo: IdTagInfo)

@JsonRootName("bootNotificationRequest")
private abstract class BootNotificationReqMixin(
    @JacksonXmlProperty(localName = "o:chargePointVendor") val chargePointVendor: String,
    @JacksonXmlProperty(localName = "o:chargePointModel") val chargePointModel: String,
    @JacksonXmlProperty(localName = "o:chargePointSerialNumber") val chargePointSerialNumber: String? = null,
    @JacksonXmlProperty(localName = "o:chargeBoxSerialNumber") val chargeBoxSerialNumber: String? = null,
    @JacksonXmlProperty(localName = "o:firmwareVersion") val firmwareVersion: String? = null,
    @JacksonXmlProperty(localName = "o:iccid") val iccid: String? = null,
    @JacksonXmlProperty(localName = "o:imsi") val imsi: String? = null,
    @JacksonXmlProperty(localName = "o:meterType") val meterType: String? = null,
    @JacksonXmlProperty(localName = "o:meterSerialNumber") val meterSerialNumber: String? = null
)

@JsonRootName("bootNotificationResponse")
private abstract class BootNotificationRespMixin(
    @JacksonXmlProperty(localName = "o:status") val status: Any,
    @JacksonXmlProperty(localName = "o:currentTime") val currentTime: Instant? = null,
    @JacksonXmlProperty(localName = "o:heartbeatInterval") val heartbeatInterval: Int? = null
)

@JsonRootName("changeAvailabilityRequest")
private abstract class ChangeAvailabilityReqMixin(
    @JacksonXmlProperty(localName = "o:connectorId") val connectorId: Int,
    @JacksonXmlProperty(localName = "o:type") val type: Any
)

@JsonRootName("changeAvailabilityResponse")
private abstract class ChangeAvailabilityRespMixin(@JacksonXmlProperty(localName = "o:status") val status: Any)

@JsonRootName("changeConfigurationRequest")
private abstract class ChangeConfigurationReqMixin(
    @JacksonXmlProperty(localName = "o:key") val key: String,
    @JacksonXmlProperty(localName = "o:value")
    @JsonInclude(JsonInclude.Include.ALWAYS)
    val value: String
)

@JsonRootName("changeConfigurationResponse")
private abstract class ChangeConfigurationRespMixin(@JacksonXmlProperty(localName = "o:status") val status: Any)

@JsonRootName("clearCacheRequest")
private abstract class ClearCacheReqMixin

@JsonRootName("clearCacheResponse")
private abstract class ClearCacheRespMixin(@JacksonXmlProperty(localName = "o:status") val status: Any)

@JsonRootName("diagnosticsStatusNotificationRequest")
private abstract class DiagnosticsStatusNotificationReqMixin(@JacksonXmlProperty(localName = "o:status") val status: DiagnosticsStatus)

@JsonRootName("diagnosticsStatusNotificationResponse")
private abstract class DiagnosticsStatusNotificationRespMixin

@JsonRootName("firmwareStatusNotificationRequest")
private abstract class FirmwareStatusNotificationReqMixin(@JacksonXmlProperty(localName = "o:status") val status: FirmwareStatus)

@JsonRootName("firmwareStatusNotificationResponse")
private abstract class FirmwareStatusNotificationRespMixin

@JsonRootName("getDiagnosticsRequest")
private abstract class GetDiagnosticsReqMixin(
    @JacksonXmlProperty(localName = "o:location") val location: String,
    @JacksonXmlProperty(localName = "o:startTime") val startTime: Instant? = null,
    @JacksonXmlProperty(localName = "o:stopTime") val stopTime: Instant? = null,
    @JacksonXmlProperty(localName = "o:retries") val retries: Int? = null,
    @JacksonXmlProperty(localName = "o:retryInterval") val retryInterval: Int? = null
)

@JsonRootName("getDiagnosticsResponse")
private abstract class GetDiagnosticsRespMixin(@JacksonXmlProperty(localName = "o:fileName") val fileName: String? = null)

@JsonRootName("heartbeatRequest")
private abstract class HeartbeatReqMixin

@JsonRootName("heartbeatResponse")
private abstract class HeartbeatRespMixin(@JacksonXmlProperty(localName = "o:currentTime") val currentTime: Instant)

private abstract class MeterValueMixin(
    @JacksonXmlProperty(localName = "o:timestamp") val timestamp: Instant,
    @JacksonXmlProperty(localName = "o:value") val value: Int
)

@JsonRootName("meterValuesRequest")
private abstract class MeterValuesReqMixin(
    @JacksonXmlProperty(localName = "o:connectorId") val connectorId: Int,
    @JacksonXmlProperty(localName = "o:values") val values: List<MeterValue>? = null
)

@JsonRootName("meterValuesResponse")
private abstract class MeterValuesRespMixin

@JsonRootName("remoteStartTransactionRequest")
private abstract class RemoteStartTransactionReqMixin(@JacksonXmlProperty(localName = "o:idTag") val idTag: String)

@JsonRootName("remoteStartTransactionResponse")
private abstract class RemoteStartTransactionRespMixin(@JacksonXmlProperty(localName = "o:status") val status: RemoteStartStopStatus)

@JsonRootName("remoteStopTransactionRequest")
private abstract class RemoteStopTransactionReqMixin(@JacksonXmlProperty(localName = "o:transactionId") val transactionId: Int)

@JsonRootName("remoteStopTransactionResponse")
private abstract class RemoteStopTransactionRespMixin(@JacksonXmlProperty(localName = "o:status") val status: RemoteStartStopStatus)

@JsonRootName("resetRequest")
private abstract class ResetReqMixin(@JacksonXmlProperty(localName = "o:type") val type: Any)

@JsonRootName("resetResponse")
private abstract class ResetRespMixin(@JacksonXmlProperty(localName = "o:status") val status: Any)

@JsonRootName("startTransactionRequest")
private abstract class StartTransactionReqMixin(
    @JacksonXmlProperty(localName = "o:connectorId") val connectorId: Int,
    @JacksonXmlProperty(localName = "o:idTag") val idTag: String,
    @JacksonXmlProperty(localName = "o:timestamp") val timestamp: Instant,
    @JacksonXmlProperty(localName = "o:meterStart") val meterStart: Int
)

@JsonRootName("startTransactionResponse")
private abstract class StartTransactionRespMixin(
    @JacksonXmlProperty(localName = "o:transactionId") val transactionId: Int,
    @JacksonXmlProperty(localName = "o:idTagInfo") val idTagInfo: IdTagInfo
)

@JsonRootName("statusNotificationRequest")
private abstract class StatusNotificationReqMixin(
    @JacksonXmlProperty(localName = "o:connectorId") val connectorId: Int,
    @JacksonXmlProperty(localName = "o:status") val status: Any,
    @JacksonXmlProperty(localName = "o:errorCode") val errorCode: Any
)

@JsonRootName("statusNotificationResponse")
private abstract class StatusNotificationRespMixin

@JsonRootName("stopTransactionRequest")
private abstract class StopTransactionReqMixin(
    @JacksonXmlProperty(localName = "o:transactionId") val transactionId: Int,
    @JacksonXmlProperty(localName = "o:idTag") val idTag: String? = null,
    @JacksonXmlProperty(localName = "o:timestamp") val timestamp: Instant,
    @JacksonXmlProperty(localName = "o:meterStop") val meterStop: Int
)

@JsonRootName("stopTransactionResponse")
private abstract class StopTransactionRespMixin(@JacksonXmlProperty(localName = "o:idTagInfo") val idTagInfo: IdTagInfo? = null)

@JsonRootName("unlockConnectorRequest")
private abstract class UnlockConnectorReqMixin(@JacksonXmlProperty(localName = "o:connectorId") val connectorId: Int)

@JsonRootName("unlockConnectorResponse")
private abstract class UnlockConnectorRespMixin(@JacksonXmlProperty(localName = "o:status") val status: Any)

@JsonRootName("updateFirmwareRequest")
private abstract class UpdateFirmwareReqMixin(
    @JacksonXmlProperty(localName = "o:retrieveDate") val retrieveDate: Instant,
    @JacksonXmlProperty(localName = "o:location") val location: String,
    @JacksonXmlProperty(localName = "o:retries") val retries: Int? = null,
    @JacksonXmlProperty(localName = "o:retryInterval") val retryInterval: Int? = null
)

@JsonRootName("updateFirmwareResponse")
private abstract class UpdateFirmwareRespMixin
