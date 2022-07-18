package com.izivia.ocpp.soap15

import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver
import com.fasterxml.jackson.dataformat.xml.XmlFactory
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import com.izivia.ocpp.core15.model.authorize.AuthorizeReq
import com.izivia.ocpp.core15.model.authorize.AuthorizeResp
import com.izivia.ocpp.core15.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core15.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core15.model.bootnotification.enumeration.RegistrationStatus
import com.izivia.ocpp.core15.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.core15.model.cancelreservation.CancelReservationResp
import com.izivia.ocpp.core15.model.cancelreservation.enumeration.CancelReservationStatus
import com.izivia.ocpp.core15.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core15.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.core15.model.changeavailability.enumeration.AvailabilityStatus
import com.izivia.ocpp.core15.model.changeavailability.enumeration.AvailabilityType
import com.izivia.ocpp.core15.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.core15.model.changeconfiguration.ChangeConfigurationResp
import com.izivia.ocpp.core15.model.changeconfiguration.enumeration.ConfigurationStatus
import com.izivia.ocpp.core15.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core15.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core15.model.clearcache.enumeration.ClearCacheStatus
import com.izivia.ocpp.core15.model.common.IdTagInfo
import com.izivia.ocpp.core15.model.common.MeterValue
import com.izivia.ocpp.core15.model.common.enumeration.*
import com.izivia.ocpp.core15.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core15.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core15.model.datatransfer.enumeration.DataTransferStatus
import com.izivia.ocpp.core15.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core15.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.core15.model.diagnosticsstatusnotification.enumeration.DiagnosticsStatus
import com.izivia.ocpp.core15.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core15.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core15.model.firmwarestatusnotification.enumeration.FirmwareStatus
import com.izivia.ocpp.core15.model.getconfiguration.GetConfigurationReq
import com.izivia.ocpp.core15.model.getconfiguration.GetConfigurationResp
import com.izivia.ocpp.core15.model.getconfiguration.KeyValue
import com.izivia.ocpp.core15.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core15.model.getdiagnostics.GetDiagnosticsResp
import com.izivia.ocpp.core15.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.core15.model.getlocallistversion.GetLocalListVersionResp
import com.izivia.ocpp.core15.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core15.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core15.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core15.model.metervalues.MeterValuesResp
import com.izivia.ocpp.core15.model.remotestart.RemoteStartTransactionReq
import com.izivia.ocpp.core15.model.remotestart.RemoteStartTransactionResp
import com.izivia.ocpp.core15.model.remotestop.RemoteStopTransactionReq
import com.izivia.ocpp.core15.model.remotestop.RemoteStopTransactionResp
import com.izivia.ocpp.core15.model.reservenow.ReserveNowReq
import com.izivia.ocpp.core15.model.reservenow.ReserveNowResp
import com.izivia.ocpp.core15.model.reservenow.enumeration.ReservationStatus
import com.izivia.ocpp.core15.model.reset.ResetReq
import com.izivia.ocpp.core15.model.reset.ResetResp
import com.izivia.ocpp.core15.model.reset.enumeration.ResetStatus
import com.izivia.ocpp.core15.model.reset.enumeration.ResetType
import com.izivia.ocpp.core15.model.sendlocallist.AuthorisationData
import com.izivia.ocpp.core15.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.core15.model.sendlocallist.SendLocalListResp
import com.izivia.ocpp.core15.model.sendlocallist.enumeration.UpdateStatus
import com.izivia.ocpp.core15.model.sendlocallist.enumeration.UpdateType
import com.izivia.ocpp.core15.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core15.model.starttransaction.StartTransactionResp
import com.izivia.ocpp.core15.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core15.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core15.model.statusnotification.enumeration.ChargePointErrorCode
import com.izivia.ocpp.core15.model.statusnotification.enumeration.ChargePointStatus
import com.izivia.ocpp.core15.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core15.model.stoptransaction.StopTransactionResp
import com.izivia.ocpp.core15.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core15.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core15.model.unlockconnector.enumeration.UnlockStatus
import com.izivia.ocpp.core15.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core15.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.soap.BaseResolver
import com.izivia.ocpp.soap.OcppSoapMapper
import kotlinx.datetime.Instant
import javax.xml.stream.XMLInputFactory

object Ocpp15SoapMapperIn : ObjectMapper(
    OcppSoapMapper
        .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
        .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
        .addMixIn(ReadingContext::class.java, EnumMixin::class.java)
        .addMixIn(Measurand::class.java, EnumMixin::class.java)
)


object Ocpp15SoapMapper : ObjectMapper(
    OcppSoapMapper
        .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
        .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
        .addMixIn(Measurand::class.java, EnumMixin::class.java)
        .addMixIn(IdTagInfo::class.java, IdTagInfoMixin::class.java)
        .addMixIn(ReadingContext::class.java, EnumMixin::class.java)
        .addMixIn(AuthorizeResp::class.java, AuthorizeRespMixin::class.java)
        .addMixIn(AuthorizeReq::class.java, AuthorizeReqMixin::class.java)
        .addMixIn(BootNotificationResp::class.java, BootNotificationRespMixin::class.java)
        .addMixIn(BootNotificationReq::class.java, BootNotificationReqMixin::class.java)
        .addMixIn(CancelReservationReq::class.java, CancelReservationReqMixin::class.java)
        .addMixIn(CancelReservationResp::class.java, CancelReservationRespMixin::class.java)
        .addMixIn(ChangeAvailabilityReq::class.java, ChangeAvailabilityReqMixin::class.java)
        .addMixIn(ChangeAvailabilityResp::class.java, ChangeAvailabilityRespMixin::class.java)
        .addMixIn(ChangeConfigurationReq::class.java, ChangeConfigurationReqMixin::class.java)
        .addMixIn(ChangeConfigurationResp::class.java, ChangeConfigurationRespMixin::class.java)
        .addMixIn(ClearCacheReq::class.java, ClearCacheReqMixin::class.java)
        .addMixIn(ClearCacheResp::class.java, ClearCacheRespMixin::class.java)
        .addMixIn(DataTransferResp::class.java, DataTransferRespMixin::class.java)
        .addMixIn(DataTransferReq::class.java, DataTransferReqMixin::class.java)
        .addMixIn(DiagnosticsStatusNotificationResp::class.java, DiagnosticsStatusNotificationRespMixin::class.java)
        .addMixIn(DiagnosticsStatusNotificationReq::class.java, DiagnosticsStatusNotificationReqMixin::class.java)
        .addMixIn(FirmwareStatusNotificationResp::class.java, FirmwareStatusNotificationRespMixin::class.java)
        .addMixIn(FirmwareStatusNotificationReq::class.java, FirmwareStatusNotificationReqMixin::class.java)
        .addMixIn(GetConfigurationReq::class.java, GetConfigurationReqMixin::class.java)
        .addMixIn(GetConfigurationResp::class.java, GetConfigurationRespMixin::class.java)
        .addMixIn(GetDiagnosticsReq::class.java, GetDiagnosticsReqMixin::class.java)
        .addMixIn(GetDiagnosticsResp::class.java, GetDiagnosticsRespMixin::class.java)
        .addMixIn(GetLocalListVersionReq::class.java, GetLocalListVersionReqMixin::class.java)
        .addMixIn(GetLocalListVersionResp::class.java, GetLocalListVersionRespMixin::class.java)
        .addMixIn(KeyValue::class.java, KeyValueMixin::class.java)
        .addMixIn(HeartbeatResp::class.java, HeartbeatRespMixin::class.java)
        .addMixIn(HeartbeatReq::class.java, HeartbeatReqMixin::class.java)
        .addMixIn(MeterValuesResp::class.java, MeterValuesRespMixin::class.java)
        .addMixIn(MeterValuesReq::class.java, MeterValuesReqMixin::class.java)
        .addMixIn(MeterValue::class.java, MeterValueMixin::class.java)
        .addMixIn(RemoteStartTransactionResp::class.java, RemoteStartTransactionRespMixin::class.java)
        .addMixIn(RemoteStartTransactionReq::class.java, RemoteStartTransactionReqMixin::class.java)
        .addMixIn(RemoteStopTransactionResp::class.java, RemoteStopTransactionRespMixin::class.java)
        .addMixIn(RemoteStopTransactionReq::class.java, RemoteStopTransactionReqMixin::class.java)
        .addMixIn(ReserveNowReq::class.java, ReserveNowReqMixin::class.java)
        .addMixIn(ReserveNowResp::class.java, ReserveNowRespMixin::class.java)
        .addMixIn(ResetReq::class.java, ResetReqMixin::class.java)
        .addMixIn(ResetResp::class.java, ResetRespMixin::class.java)
        .addMixIn(SendLocalListReq::class.java, SendLocalListReqMixin::class.java)
        .addMixIn(SendLocalListResp::class.java, SendLocalListRespMixin::class.java)
        .addMixIn(AuthorisationData::class.java, AuthorisationDataMixin::class.java)
        .addMixIn(StartTransactionResp::class.java, StartTransactionRespMixin::class.java)
        .addMixIn(StartTransactionReq::class.java, StartTransactionReqMixin::class.java)
        .addMixIn(StatusNotificationResp::class.java, StatusNotificationRespMixin::class.java)
        .addMixIn(StatusNotificationReq::class.java, StatusNotificationReqMixin::class.java)
        .addMixIn(StopTransactionResp::class.java, StopTransactionRespMixin::class.java)
        .addMixIn(StopTransactionReq::class.java, StopTransactionReqMixin::class.java)
        .addMixIn(UnlockConnectorReq::class.java, UnlockConnectorReqMixin::class.java)
        .addMixIn(UnlockConnectorResp::class.java, UnlockConnectorRespMixin::class.java)
        .addMixIn(UpdateFirmwareReq::class.java, UpdateFirmwareReqMixin::class.java)
        .addMixIn(UpdateFirmwareResp::class.java, UpdateFirmwareRespMixin::class.java)
)

abstract class EnumMixin(
    @JsonValue val value: String
)

abstract class IdTagInfoMixin(
    @JacksonXmlProperty(localName = "o:expiryDate")
    val expiryDate: Instant? = null,
    @JacksonXmlProperty(localName = "o:parentIdTag")
    val parentIdTag: String? = null,
    @JacksonXmlProperty(localName = "o:status")
    val status: AuthorizationStatus
)

@JsonRootName("authorizeResponse")
abstract class AuthorizeRespMixin(
    @JacksonXmlProperty(localName = "o:idTagInfo")
    val idTagInfo: IdTagInfo
)

@JsonRootName("authorizeRequest")
abstract class AuthorizeReqMixin(
    @JacksonXmlProperty(localName = "o:idTag")
    val idTag: String
)

@JsonRootName("bootNotificationResponse")
abstract class BootNotificationRespMixin(
    @JacksonXmlProperty(localName = "o:currentTime")
    val currentTime: Instant,
    @JacksonXmlProperty(localName = "o:heartbeatInterval")
    val heartbeatInterval: Int,
    @JacksonXmlProperty(localName = "o:status")
    val status: RegistrationStatus
)

@JsonRootName("bootNotificationRequest")
abstract class BootNotificationReqMixin(
    @JacksonXmlProperty(localName = "o:chargeBoxSerialNumber")
    val chargeBoxSerialNumber: String? = null,
    @JacksonXmlProperty(localName = "o:chargePointModel")
    val chargePointModel: String,
    @JacksonXmlProperty(localName = "o:chargePointSerialNumber")
    val chargePointSerialNumber: String? = null,
    @JacksonXmlProperty(localName = "o:chargePointVendor")
    val chargePointVendor: String,
    @JacksonXmlProperty(localName = "o:firmwareVersion")
    val firmwareVersion: String? = null,
    @JacksonXmlProperty(localName = "o:iccid")
    val iccid: String? = null,
    @JacksonXmlProperty(localName = "o:imsi")
    val imsi: String? = null,
    @JacksonXmlProperty(localName = "o:meterSerialNumber")
    val meterSerialNumber: String? = null,
    @JacksonXmlProperty(localName = "o:meterType")
    val meterType: String? = null
)

@JsonRootName("cancelReservationResponse")
abstract class CancelReservationRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: CancelReservationStatus
)

@JsonRootName("cancelReservationRequest")
abstract class CancelReservationReqMixin(
    @JacksonXmlProperty(localName = "o:reservationId")
    val reservationId: Int
)

@JsonRootName("changeAvailabilityRequest")
abstract class ChangeAvailabilityReqMixin(
    @JacksonXmlProperty(localName = "o:connectorId")
    val connectorId: Int,
    @JacksonXmlProperty(localName = "o:type")
    val type: AvailabilityType
)

@JsonRootName("changeAvailabilityResponse")
abstract class ChangeAvailabilityRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: AvailabilityStatus
)

@JsonRootName("changeConfigurationResponse")
abstract class ChangeConfigurationRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: ConfigurationStatus
)

@JsonRootName("changeConfigurationResponse")
abstract class ChangeConfigurationReqMixin(
    @JacksonXmlProperty(localName = "o:key")
    val key: String,
    @JacksonXmlProperty(localName = "o:value")
    val value: String
)

@JsonRootName("clearCacheRequest")
abstract class ClearCacheReqMixin

@JsonRootName("clearCacheResponse")
abstract class ClearCacheRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: ClearCacheStatus
)

@JsonRootName("dataTransferResponse")
abstract class DataTransferRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: DataTransferStatus,
    @JacksonXmlProperty(localName = "o:data")
    val data: String? = null
)

@JsonRootName("dataTransferRequest")
abstract class DataTransferReqMixin(
    @JacksonXmlProperty(localName = "o:vendorId")
    val vendorId: String,
    @JacksonXmlProperty(localName = "o:messageId")
    val messageId: String? = null,
    @JacksonXmlProperty(localName = "o:data")
    val data: String? = null
)

@JsonRootName("diagnosticsStatusNotificationResponse")
abstract class DiagnosticsStatusNotificationRespMixin

@JsonRootName("diagnosticsStatusNotificationRequest")
abstract class DiagnosticsStatusNotificationReqMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: DiagnosticsStatus
)

@JsonRootName("firmwareStatusNotificationResponse")
abstract class FirmwareStatusNotificationRespMixin

@JsonRootName("firmwareStatusNotificationRequest")
abstract class FirmwareStatusNotificationReqMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: FirmwareStatus
)

@JsonRootName("getConfigurationResponse")
abstract class GetConfigurationRespMixin(
    @JacksonXmlProperty(localName = "o:configurationKey")
    val configurationKey: List<KeyValue>? = null,
    @JacksonXmlProperty(localName = "o:unknownKey")
    val unknownKey: List<String>? = null
)

@JsonRootName("getConfigurationRequest")
abstract class GetConfigurationReqMixin(
    @JacksonXmlProperty(localName = "o:key")
    val key: List<String>? = null
)

abstract class KeyValueMixin(
    @JacksonXmlProperty(localName = "o:key")
    val key: String,
    @JacksonXmlProperty(localName = "o:readonly")
    val readonly: Boolean,
    @JacksonXmlProperty(localName = "o:value")
    val value: String? = null
)

@JsonRootName("getDiagnosticsRequest")
abstract class GetDiagnosticsReqMixin(
    @JacksonXmlProperty(localName = "o:location")
    val location: String,
    @JacksonXmlProperty(localName = "o:retries")
    val retries: Int? = null,
    @JacksonXmlProperty(localName = "o:retryInterval")
    val retryInterval: Int? = null,
    @JacksonXmlProperty(localName = "o:startTime")
    val startTime: Instant? = null,
    @JacksonXmlProperty(localName = "o:stopTime")
    val stopTime: Instant? = null
)

@JsonRootName("getDiagnosticsResponse")
abstract class GetDiagnosticsRespMixin(
    @JacksonXmlProperty(localName = "o:fileName")
    val fileName: String? = null
)

@JsonRootName("getLocalListVersionRequest")
abstract class GetLocalListVersionReqMixin

@JsonRootName("getLocalListVersionResponse")
abstract class GetLocalListVersionRespMixin(
    @JacksonXmlProperty(localName = "o:listVersion")
    val listVersion: Int
)

@JsonRootName("heartbeatResponse")
abstract class HeartbeatRespMixin(
    @JacksonXmlProperty(localName = "o:currentTime")
    val currentTime: Instant
)

@JsonRootName("heartbeatRequest")
abstract class HeartbeatReqMixin

@JsonRootName("meterValuesResponse")
abstract class MeterValuesRespMixin

@JsonRootName("meterValuesRequest")
abstract class MeterValuesReqMixin(
    @JacksonXmlProperty(localName = "o:connectorId")
    val connectorId: Int,
    @JacksonXmlProperty(localName = "o:values")
    val values: List<MeterValue>? = null,
    @JacksonXmlProperty(localName = "o:transactionId")
    val transactionId: Int? = null
)

abstract class MeterValueMixin(
    @JacksonXmlProperty(isAttribute = true)
    val timestamp: Instant,
    @JacksonXmlProperty(localName = "o:value")
    val value: String,
    @JacksonXmlProperty(isAttribute = true)
    val context: ReadingContext? = ReadingContext.SamplePeriodic,
    @JacksonXmlProperty(isAttribute = true)
    val format: ValueFormat? = ValueFormat.Raw,
    @JacksonXmlProperty(isAttribute = true)
    val measurand: Measurand? = Measurand.EnergyActiveImportRegister,
    @JacksonXmlProperty(isAttribute = true)
    val location: Location? = Location.Outlet,
    @JacksonXmlProperty(isAttribute = true)
    val unit: UnitOfMeasure? = UnitOfMeasure.Wh
)

abstract class MeterValue15Mixin(
    @JacksonXmlProperty(isAttribute = true)
    val timestamp: Instant,
    @JacksonXmlProperty(localName = "value")
    val value: String,
    @JacksonXmlProperty(isAttribute = true)
    val context: ReadingContext? = ReadingContext.SamplePeriodic,
    @JacksonXmlProperty(isAttribute = true)
    val format: ValueFormat? = ValueFormat.Raw,
    @JacksonXmlProperty(isAttribute = true)
    val measurand: Measurand? = Measurand.EnergyActiveImportRegister,
    @JacksonXmlProperty(isAttribute = true)
    val location: Location? = Location.Outlet,
    @JacksonXmlProperty(isAttribute = true)
    val unit: UnitOfMeasure? = UnitOfMeasure.Wh
)

@JsonRootName("remoteStartTransactionResponse")
abstract class RemoteStartTransactionRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: RemoteStartStopStatus
)


@JsonRootName("remoteStartTransactionRequest")
abstract class RemoteStartTransactionReqMixin(
    @JacksonXmlProperty(localName = "o:connectorId")
    val connectorId: Int? = null,
    @JacksonXmlProperty(localName = "o:idTag")
    val idTag: String
)

@JsonRootName("remoteStopTransactionResponse")
abstract class RemoteStopTransactionRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: RemoteStartStopStatus
)

@JsonRootName("remoteStopTransactionRequest")
abstract class RemoteStopTransactionReqMixin(
    @JacksonXmlProperty(localName = "o:transactionId")
    val transactionId: Int
)

@JsonRootName("reserveNowResponse")
abstract class ReserveNowRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: ReservationStatus
)

@JsonRootName("reserveNowReuest")
abstract class ReserveNowReqMixin(
    @JacksonXmlProperty(localName = "o:connectorId")
    val connectorId: Int,
    @JacksonXmlProperty(localName = "o:expiryDate")
    val expiryDate: Instant,
    @JacksonXmlProperty(localName = "o:idTag")
    val idTag: String,
    @JacksonXmlProperty(localName = "o:parentIdTag")
    val parentIdTag: String? = null,
    @JacksonXmlProperty(localName = "o:reservationId")
    val reservationId: Int
)

@JsonRootName("resetResponse")
abstract class ResetRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: ResetStatus
)

@JsonRootName("resetRequest")
abstract class ResetReqMixin(
    @JacksonXmlProperty(localName = "o:type")
    val type: ResetType
)

@JsonRootName("sendLocalListRequest")
abstract class SendLocalListReqMixin(
    @JacksonXmlProperty(localName = "o:hash")
    val hash: String? = null,
    @JacksonXmlProperty(localName = "o:listVersion")
    val listVersion: Int,
    @JacksonXmlProperty(localName = "o:localAuthorizationList")
    val localAuthorizationList: List<AuthorisationData>? = null,
    @JacksonXmlProperty(localName = "o:updateType")
    val updateType: UpdateType
)

@JsonRootName("sendLocalListResponse")
abstract class SendLocalListRespMixin(
    @JacksonXmlProperty(localName = "o:hash")
    val hash: String? = null,
    @JacksonXmlProperty(localName = "o:status")
    val status: UpdateStatus
)

abstract class AuthorisationDataMixin(
    @JacksonXmlProperty(localName = "o:idTag")
    val idTag: String,
    @JacksonXmlProperty(localName = "o:idTagInfo")
    val idTagInfo: IdTagInfo? = null
)

@JsonRootName("startTransactionResponse")
abstract class StartTransactionRespMixin(
    @JacksonXmlProperty(localName = "o:idTagInfo")
    val idTagInfo: IdTagInfo,
    @JacksonXmlProperty(localName = "o:transactionId")
    val transactionId: Int
)

@JsonRootName("startTransactionRequest")
abstract class StartTransactionReqMixin(
    @JacksonXmlProperty(localName = "o:connectorId")
    val connectorId: Int,
    @JacksonXmlProperty(localName = "o:idTag")
    val idTag: String,
    @JacksonXmlProperty(localName = "o:meterStart")
    val meterStart: Int,
    @JacksonXmlProperty(localName = "o:reservationId")
    val reservationId: Int? = null,
    @JacksonXmlProperty(localName = "o:timestamp")
    val timestamp: Instant
)

@JsonRootName("statusNotificationResponse")
abstract class StatusNotificationRespMixin

@JsonRootName("statusNotificationRequest")
abstract class StatusNotificationReqMixin(
    @JacksonXmlProperty(localName = "o:connectorId")
    val connectorId: Int,
    @JacksonXmlProperty(localName = "o:errorCode")
    val errorCode: ChargePointErrorCode,
    @JacksonXmlProperty(localName = "o:info")
    val info: String? = null,
    @JacksonXmlProperty(localName = "o:status")
    val status: ChargePointStatus,
    @JacksonXmlProperty(localName = "o:timestamp")
    val timestamp: Instant? = null,
    @JacksonXmlProperty(localName = "o:vendorId")
    val vendorId: String? = null,
    @JacksonXmlProperty(localName = "o:vendorErrorCode")
    val vendorErrorCode: String? = null
)

@JsonRootName("stopTransactionResponse")
abstract class StopTransactionRespMixin(
    @JacksonXmlProperty(localName = "o:idTagInfo")
    val idTagInfo: IdTagInfo? = null
)

@JsonRootName("stopTransactionRequest")
abstract class StopTransactionReqMixin(
    @JacksonXmlProperty(localName = "o:idTag")
    val idTag: String? = null,
    @JacksonXmlProperty(localName = "o:meterStop")
    val meterStop: Int,
    @JacksonXmlProperty(localName = "o:timestamp")
    val timestamp: Instant,
    @JacksonXmlProperty(localName = "o:transactionId")
    val transactionId: Int,
    @JacksonXmlProperty(localName = "o:transactionData")
    val transactionData: List<MeterValue>? = null
)

@JsonRootName("unlockConnectorRequest")
abstract class UnlockConnectorReqMixin(
    @JacksonXmlProperty(localName = "o:connectorId")
    val connectorId: Int
)

@JsonRootName("unlockConnectorResponse")
abstract class UnlockConnectorRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: UnlockStatus
)

@JsonRootName("updateFirmwareRequest")
abstract class UpdateFirmwareReqMixin(
    @JacksonXmlProperty(localName = "o:location")
    val location: String,
    @JacksonXmlProperty(localName = "o:retries")
    val retries: Int? = null,
    @JacksonXmlProperty(localName = "o:retrieveDate")
    val retrieveDate: Instant,
    @JacksonXmlProperty(localName = "o:retryInterval")
    val retryInterval: Int? = null
)

@JsonRootName("updateFirmwareRequest")
abstract class UpdateFirmwareRespMixin
