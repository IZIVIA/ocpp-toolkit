package com.izivia.ocpp.soap16

import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.izivia.ocpp.core16.model.authorize.AuthorizeReq
import com.izivia.ocpp.core16.model.authorize.AuthorizeResp
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core16.model.bootnotification.enumeration.RegistrationStatus
import com.izivia.ocpp.core16.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.core16.model.cancelreservation.CancelReservationResp
import com.izivia.ocpp.core16.model.cancelreservation.enumeration.CancelReservationStatus
import com.izivia.ocpp.core16.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core16.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.core16.model.changeavailability.enumeration.AvailabilityStatus
import com.izivia.ocpp.core16.model.changeavailability.enumeration.AvailabilityType
import com.izivia.ocpp.core16.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.core16.model.changeconfiguration.ChangeConfigurationResp
import com.izivia.ocpp.core16.model.changeconfiguration.enumeration.ConfigurationStatus
import com.izivia.ocpp.core16.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core16.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core16.model.clearcache.enumeration.ClearCacheStatus
import com.izivia.ocpp.core16.model.clearchargingprofile.ClearChargingProfileReq
import com.izivia.ocpp.core16.model.clearchargingprofile.ClearChargingProfileResp
import com.izivia.ocpp.core16.model.clearchargingprofile.enumeration.ClearChargingProfileStatus
import com.izivia.ocpp.core16.model.common.*
import com.izivia.ocpp.core16.model.common.enumeration.*
import com.izivia.ocpp.core16.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core16.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core16.model.datatransfer.enumeration.DataTransferStatus
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.enumeration.DiagnosticsStatus
import com.izivia.ocpp.core16.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core16.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core16.model.firmwarestatusnotification.enumeration.FirmwareStatus
import com.izivia.ocpp.core16.model.getcompositeschedule.GetCompositeScheduleReq
import com.izivia.ocpp.core16.model.getcompositeschedule.GetCompositeScheduleResp
import com.izivia.ocpp.core16.model.getcompositeschedule.enumeration.GetCompositeScheduleStatus
import com.izivia.ocpp.core16.model.getconfiguration.GetConfigurationReq
import com.izivia.ocpp.core16.model.getconfiguration.GetConfigurationResp
import com.izivia.ocpp.core16.model.getconfiguration.KeyValue
import com.izivia.ocpp.core16.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core16.model.getdiagnostics.GetDiagnosticsResp
import com.izivia.ocpp.core16.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.core16.model.getlocallistversion.GetLocalListVersionResp
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core16.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core16.model.metervalues.MeterValuesResp
import com.izivia.ocpp.core16.model.remotestart.ChargingSchedulePeriod
import com.izivia.ocpp.core16.model.remotestart.RemoteStartTransactionReq
import com.izivia.ocpp.core16.model.remotestart.RemoteStartTransactionResp
import com.izivia.ocpp.core16.model.remotestart.enumeration.ChargingProfileKindType
import com.izivia.ocpp.core16.model.remotestart.enumeration.RecurrencyKindType
import com.izivia.ocpp.core16.model.remotestop.RemoteStopTransactionReq
import com.izivia.ocpp.core16.model.remotestop.RemoteStopTransactionResp
import com.izivia.ocpp.core16.model.reservenow.ReserveNowReq
import com.izivia.ocpp.core16.model.reservenow.ReserveNowResp
import com.izivia.ocpp.core16.model.reservenow.enumeration.ReservationStatus
import com.izivia.ocpp.core16.model.reset.ResetReq
import com.izivia.ocpp.core16.model.reset.ResetResp
import com.izivia.ocpp.core16.model.reset.enumeration.ResetStatus
import com.izivia.ocpp.core16.model.reset.enumeration.ResetType
import com.izivia.ocpp.core16.model.sendlocallist.AuthorizationData
import com.izivia.ocpp.core16.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.core16.model.sendlocallist.SendLocalListResp
import com.izivia.ocpp.core16.model.sendlocallist.enumeration.UpdateStatus
import com.izivia.ocpp.core16.model.sendlocallist.enumeration.UpdateType
import com.izivia.ocpp.core16.model.setchargingprofile.SetChargingProfileReq
import com.izivia.ocpp.core16.model.setchargingprofile.SetChargingProfileResp
import com.izivia.ocpp.core16.model.setchargingprofile.enumeration.ChargingProfileStatus
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionResp
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core16.model.statusnotification.enumeration.ChargePointErrorCode
import com.izivia.ocpp.core16.model.statusnotification.enumeration.ChargePointStatus
import com.izivia.ocpp.core16.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core16.model.stoptransaction.StopTransactionResp
import com.izivia.ocpp.core16.model.stoptransaction.enumeration.Reason
import com.izivia.ocpp.core16.model.triggermessage.TriggerMessageReq
import com.izivia.ocpp.core16.model.triggermessage.TriggerMessageResp
import com.izivia.ocpp.core16.model.triggermessage.enumeration.MessageTrigger
import com.izivia.ocpp.core16.model.triggermessage.enumeration.TriggerMessageStatus
import com.izivia.ocpp.core16.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core16.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core16.model.unlockconnector.enumeration.UnlockStatus
import com.izivia.ocpp.core16.model.signedupdatefirmware.SignedUpdateFirmwareReq
import com.izivia.ocpp.core16.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.soap.*
import kotlinx.datetime.Instant
import java.math.BigDecimal

internal object Ocpp16SoapMapperIn : ObjectMapper(
    OcppSoapMapper()
        .addMixIn(ReadingContext::class.java, EnumMixin::class.java)
        .addMixIn(Measurand::class.java, EnumMixin::class.java)
        .addMixIn(Phase::class.java, EnumMixin::class.java)
)

internal object Ocpp16SoapMapper : ObjectMapper(
    OcppSoapMapper()
        .addMixIn(Measurand::class.java, EnumMixin::class.java)
        .addMixIn(Phase::class.java, EnumMixin::class.java)
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
        .addMixIn(ClearChargingProfileReq::class.java, ClearChargingProfileReqMixin::class.java)
        .addMixIn(ClearChargingProfileResp::class.java, ClearChargingProfileRespMixin::class.java)
        .addMixIn(DataTransferResp::class.java, DataTransferRespMixin::class.java)
        .addMixIn(DataTransferReq::class.java, DataTransferReqMixin::class.java)
        .addMixIn(DiagnosticsStatusNotificationResp::class.java, DiagnosticsStatusNotificationRespMixin::class.java)
        .addMixIn(DiagnosticsStatusNotificationReq::class.java, DiagnosticsStatusNotificationReqMixin::class.java)
        .addMixIn(FirmwareStatusNotificationResp::class.java, FirmwareStatusNotificationRespMixin::class.java)
        .addMixIn(FirmwareStatusNotificationReq::class.java, FirmwareStatusNotificationReqMixin::class.java)
        .addMixIn(GetCompositeScheduleReq::class.java, GetCompositeScheduleReqMixin::class.java)
        .addMixIn(GetCompositeScheduleResp::class.java, GetCompositeScheduleRespMixin::class.java)
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
        .addMixIn(SampledValue::class.java, SampledValueMixin::class.java)
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
        .addMixIn(AuthorizationData::class.java, AuthorisationDataMixin::class.java)
        .addMixIn(SetChargingProfileReq::class.java, SetChargingProfileReqMixin::class.java)
        .addMixIn(SetChargingProfileResp::class.java, SetChargingProfileRespMixin::class.java)
        .addMixIn(ChargingProfile::class.java, ChargingProfileMixin::class.java)
        .addMixIn(ChargingSchedule::class.java, ChargingScheduleMixin::class.java)
        .addMixIn(ChargingSchedulePeriod::class.java, ChargingSchedulePeriodMixin::class.java)
        .addMixIn(StartTransactionResp::class.java, StartTransactionRespMixin::class.java)
        .addMixIn(StartTransactionReq::class.java, StartTransactionReqMixin::class.java)
        .addMixIn(StatusNotificationResp::class.java, StatusNotificationRespMixin::class.java)
        .addMixIn(StatusNotificationReq::class.java, StatusNotificationReqMixin::class.java)
        .addMixIn(StopTransactionResp::class.java, StopTransactionRespMixin::class.java)
        .addMixIn(StopTransactionReq::class.java, StopTransactionReqMixin::class.java)
        .addMixIn(TriggerMessageReq::class.java, TriggerMessageReqMixin::class.java)
        .addMixIn(TriggerMessageResp::class.java, TriggerMessageRespMixin::class.java)
        .addMixIn(UnlockConnectorReq::class.java, UnlockConnectorReqMixin::class.java)
        .addMixIn(UnlockConnectorResp::class.java, UnlockConnectorRespMixin::class.java)
        .addMixIn(SignedUpdateFirmwareReq::class.java, UpdateFirmwareReqMixin::class.java)
        .addMixIn(UpdateFirmwareResp::class.java, UpdateFirmwareRespMixin::class.java)
        .addMixIn(SoapFault::class.java, SoapFaultMixin::class.java)
        .addMixIn(FaultCode::class.java, FaultCodeMixin::class.java)
        .addMixIn(FaultReason::class.java, FaultReasonMixin::class.java)
        .addMixIn(FaultSubCode::class.java, FaultSubCodeMixin::class.java)
)

private abstract class ChargingSchedulePeriodMixin(
    @JacksonXmlProperty(localName = "o:startPeriod")
    val startPeriod: Int,
    @JacksonXmlProperty(localName = "o:limit")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    val limit: BigDecimal,
    @JacksonXmlProperty(localName = "o:numberPhases")
    val numberPhases: Int? = null
)

private abstract class ChargingScheduleMixin(
    @JacksonXmlProperty(localName = "o:duration")
    val duration: Int? = null,
    @JacksonXmlProperty(localName = "o:startSchedule")
    val startSchedule: Instant? = null,
    @JacksonXmlProperty(localName = "o:chargingRateUnit")
    val chargingRateUnit: ChargingRateUnitType,
    @JacksonXmlProperty(localName = "o:chargingSchedulePeriod")
    val chargingSchedulePeriod: List<ChargingSchedulePeriod>,
    @JacksonXmlProperty(localName = "o:minChargingRate")
    val minChargingRate: BigDecimal? = null
)

private abstract class ChargingProfileMixin(
    @JacksonXmlProperty(localName = "o:chargingProfileId")
    val chargingProfileId: Int,
    @JacksonXmlProperty(localName = "o:transactionId")
    val transactionId: Int? = null,
    @JacksonXmlProperty(localName = "o:stackLevel")
    val stackLevel: Int,
    @JacksonXmlProperty(localName = "o:chargingProfilePurpose")
    val chargingProfilePurpose: ChargingProfilePurposeType,
    @JacksonXmlProperty(localName = "o:chargingProfileKind")
    val chargingProfileKind: ChargingProfileKindType,
    @JacksonXmlProperty(localName = "o:recurrencyKind")
    val recurrencyKind: RecurrencyKindType? = null,
    @JacksonXmlProperty(localName = "o:validFrom")
    val validFrom: Instant? = null,
    @JacksonXmlProperty(localName = "o:validTo")
    val validTo: Instant? = null,
    @JacksonXmlProperty(localName = "o:chargingSchedule")
    val chargingSchedule: ChargingSchedule
)

@JsonRootName("SetChargingProfileResponse")
private abstract class SetChargingProfileRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: ChargingProfileStatus
)

@JsonRootName("SetChargingProfileRequest")
private abstract class SetChargingProfileReqMixin(
    @JacksonXmlProperty(localName = "o:connectorId")
    val connectorId: Int? = null,
    @JacksonXmlProperty(localName = "o:csChargingProfiles")
    val csChargingProfiles: ChargingProfile
)

@JsonRootName("GetCompositeScheduleResponse")
private abstract class GetCompositeScheduleRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: GetCompositeScheduleStatus,
    @JacksonXmlProperty(localName = "o:connectorId")
    val connectorId: Int? = null,
    @JacksonXmlProperty(localName = "o:scheduleStart")
    val scheduleStart: Instant? = null,
    @JacksonXmlProperty(localName = "o:chargingSchedule")
    val chargingSchedule: ChargingSchedule? = null
)

@JsonRootName("GetCompositeScheduleRequest")
private abstract class GetCompositeScheduleReqMixin(
    @JacksonXmlProperty(localName = "o:connectorId")
    val connectorId: Int,
    @JacksonXmlProperty(localName = "o:duration")
    val duration: Int,
    @JacksonXmlProperty(localName = "o:chargingRateUnit")
    val chargingRateUnit: ChargingRateUnitType? = null
)

@JsonRootName("clearChargingProfileResponse")
private abstract class ClearChargingProfileRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: ClearChargingProfileStatus
)

@JsonRootName("clearChargingProfileRequest")
private abstract class ClearChargingProfileReqMixin(
    @JacksonXmlProperty(localName = "o:id")
    val id: Int? = null,
    @JacksonXmlProperty(localName = "o:connectorId")
    val connectorId: Int? = null,
    @JacksonXmlProperty(localName = "o:chargingProfilePurpose")
    val chargingProfilePurpose: ChargingProfilePurposeType? = null,
    @JacksonXmlProperty(localName = "o:stackLevel")
    val stackLevel: Int? = null
)

@JsonRootName("triggerMessageRequest")
private abstract class TriggerMessageReqMixin(
    @JacksonXmlProperty(localName = "o:requestedMessage")
    val requestedMessage: MessageTrigger,
    @JacksonXmlProperty(localName = "o:connectorId")
    val connectorId: Int? = null
)

@JsonRootName("triggerMessageResponse")
private abstract class TriggerMessageRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: TriggerMessageStatus
)

private abstract class IdTagInfoMixin(
    @JacksonXmlProperty(localName = "o:expiryDate")
    val expiryDate: Instant? = null,
    @JacksonXmlProperty(localName = "o:parentIdTag")
    val parentIdTag: String? = null,
    @JacksonXmlProperty(localName = "o:status")
    val status: AuthorizationStatus
)

@JsonRootName("authorizeResponse")
private abstract class AuthorizeRespMixin(
    @JacksonXmlProperty(localName = "o:idTagInfo")
    val idTagInfo: IdTagInfo
)

@JsonRootName("authorizeRequest")
private abstract class AuthorizeReqMixin(
    @JacksonXmlProperty(localName = "o:idTag")
    val idTag: String
)

@JsonRootName("bootNotificationResponse")
private abstract class BootNotificationRespMixin(
    @JacksonXmlProperty(localName = "o:currentTime")
    val currentTime: Instant,
    @JacksonXmlProperty(localName = "o:interval")
    val interval: Int,
    @JacksonXmlProperty(localName = "o:status")
    val status: RegistrationStatus
)

@JsonRootName("bootNotificationRequest")
private abstract class BootNotificationReqMixin(
    @JacksonXmlProperty(localName = "o:chargePointModel")
    val chargePointModel: String,
    @JacksonXmlProperty(localName = "o:chargePointVendor")
    val chargePointVendor: String,
    @JacksonXmlProperty(localName = "o:chargePointSerialNumber")
    val chargePointSerialNumber: String? = null,
    @JacksonXmlProperty(localName = "o:chargeBoxSerialNumber")
    val chargeBoxSerialNumber: String? = null,
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
private abstract class CancelReservationRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: CancelReservationStatus
)

@JsonRootName("cancelReservationRequest")
private abstract class CancelReservationReqMixin(
    @JacksonXmlProperty(localName = "o:reservationId")
    val reservationId: Int
)

@JsonRootName("changeAvailabilityRequest")
private abstract class ChangeAvailabilityReqMixin(
    @JacksonXmlProperty(localName = "o:connectorId")
    val connectorId: Int,
    @JacksonXmlProperty(localName = "o:type")
    val type: AvailabilityType
)

@JsonRootName("changeAvailabilityResponse")
private abstract class ChangeAvailabilityRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: AvailabilityStatus
)

@JsonRootName("changeConfigurationResponse")
private abstract class ChangeConfigurationRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: ConfigurationStatus
)

@JsonRootName("changeConfigurationResponse")
private abstract class ChangeConfigurationReqMixin(
    @JacksonXmlProperty(localName = "o:key")
    val key: String,
    @JacksonXmlProperty(localName = "o:value")
    @JsonInclude(JsonInclude.Include.ALWAYS)
    val value: String
)

@JsonRootName("clearCacheRequest")
private abstract class ClearCacheReqMixin

@JsonRootName("clearCacheResponse")
private abstract class ClearCacheRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: ClearCacheStatus
)

@JsonRootName("dataTransferResponse")
private abstract class DataTransferRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: DataTransferStatus,
    @JacksonXmlProperty(localName = "o:data")
    val data: String? = null
)

@JsonRootName("dataTransferRequest")
private abstract class DataTransferReqMixin(
    @JacksonXmlProperty(localName = "o:vendorId")
    val vendorId: String,
    @JacksonXmlProperty(localName = "o:messageId")
    val messageId: String? = null,
    @JacksonXmlProperty(localName = "o:data")
    val data: String? = null
)

@JsonRootName("diagnosticsStatusNotificationResponse")
private abstract class DiagnosticsStatusNotificationRespMixin

@JsonRootName("diagnosticsStatusNotificationRequest")
private abstract class DiagnosticsStatusNotificationReqMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: DiagnosticsStatus
)

@JsonRootName("firmwareStatusNotificationResponse")
private abstract class FirmwareStatusNotificationRespMixin

@JsonRootName("firmwareStatusNotificationRequest")
private abstract class FirmwareStatusNotificationReqMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: FirmwareStatus
)

@JsonRootName("getConfigurationResponse")
private abstract class GetConfigurationRespMixin(
    @JacksonXmlProperty(localName = "o:configurationKey")
    val configurationKey: List<KeyValue>? = null,
    @JacksonXmlProperty(localName = "o:unknownKey")
    val unknownKey: List<String>? = null
)

@JsonRootName("getConfigurationRequest")
private abstract class GetConfigurationReqMixin(
    @JacksonXmlProperty(localName = "o:key")
    val key: List<String>? = null
)

private abstract class KeyValueMixin(
    @JacksonXmlProperty(localName = "o:key")
    val key: String,
    @JacksonXmlProperty(localName = "o:readonly")
    val readonly: Boolean,
    @JacksonXmlProperty(localName = "o:value")
    val value: String? = null
)

@JsonRootName("getDiagnosticsRequest")
private abstract class GetDiagnosticsReqMixin(
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
private abstract class GetDiagnosticsRespMixin(
    @JacksonXmlProperty(localName = "o:fileName")
    val fileName: String? = null
)

@JsonRootName("getLocalListVersionRequest")
private abstract class GetLocalListVersionReqMixin

@JsonRootName("getLocalListVersionResponse")
private abstract class GetLocalListVersionRespMixin(
    @JacksonXmlProperty(localName = "o:listVersion")
    val listVersion: Int
)

@JsonRootName("heartbeatResponse")
private abstract class HeartbeatRespMixin(
    @JacksonXmlProperty(localName = "o:currentTime")
    val currentTime: Instant
)

@JsonRootName("heartbeatRequest")
private abstract class HeartbeatReqMixin

@JsonRootName("meterValuesResponse")
private abstract class MeterValuesRespMixin

@JsonRootName("meterValuesRequest")
private abstract class MeterValuesReqMixin(
    @JacksonXmlProperty(localName = "o:connectorId")
    val connectorId: Int,
    @JacksonXmlProperty(localName = "o:meterValue")
    val meterValue: List<MeterValue>? = null,
    @JacksonXmlProperty(localName = "o:transactionId")
    val transactionId: Int? = null
)

private abstract class MeterValueMixin(
    @JacksonXmlProperty(localName = "o:timestamp")
    val timestamp: Instant,
    @JacksonXmlProperty(localName = "o:sampledValue")
    val sampledValue: List<SampledValue>
)

private abstract class SampledValueMixin(
    @JacksonXmlProperty(localName = "o:value")
    val value: String,
    @JacksonXmlProperty(localName = "o:context")
    val context: ReadingContext? = ReadingContext.SamplePeriodic,
    @JacksonXmlProperty(localName = "o:format")
    val format: ValueFormat? = ValueFormat.Raw,
    @JacksonXmlProperty(localName = "o:measurand")
    val measurand: Measurand? = Measurand.EnergyActiveImportRegister,
    @JacksonXmlProperty(localName = "o:location")
    val location: Location? = Location.Outlet,
    @JacksonXmlProperty(localName = "o:unit")
    val unit: UnitOfMeasure? = UnitOfMeasure.Wh,
    @JacksonXmlProperty(localName = "o:pĥase")
    val phase: Phase? = null
)

@JsonRootName("remoteStartTransactionResponse")
private abstract class RemoteStartTransactionRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: RemoteStartStopStatus
)

@JsonRootName("remoteStartTransactionRequest")
private abstract class RemoteStartTransactionReqMixin(
    @JacksonXmlProperty(localName = "o:connectorId")
    val connectorId: Int? = null,
    @JacksonXmlProperty(localName = "o:idTag")
    val idTag: String,
    @JacksonXmlProperty(localName = "o:chargingProfile")
    val chargingProfile: ChargingProfile? = null
)

@JsonRootName("remoteStopTransactionResponse")
private abstract class RemoteStopTransactionRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: RemoteStartStopStatus
)

@JsonRootName("remoteStopTransactionRequest")
private abstract class RemoteStopTransactionReqMixin(
    @JacksonXmlProperty(localName = "o:transactionId")
    val transactionId: Int
)

@JsonRootName("reserveNowResponse")
private abstract class ReserveNowRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: ReservationStatus
)

@JsonRootName("reserveNowRequest")
private abstract class ReserveNowReqMixin(
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
private abstract class ResetRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: ResetStatus
)

@JsonRootName("resetRequest")
private abstract class ResetReqMixin(
    @JacksonXmlProperty(localName = "o:type")
    val type: ResetType
)

@JsonRootName("sendLocalListRequest")
private abstract class SendLocalListReqMixin(
    @JacksonXmlProperty(localName = "o:listVersion")
    val listVersion: Int,
    @JacksonXmlProperty(localName = "o:localAuthorizationList")
    val localAuthorizationList: List<AuthorizationData>? = null,
    @JacksonXmlProperty(localName = "o:updateType")
    val updateType: UpdateType
)

@JsonRootName("sendLocalListResponse")
private abstract class SendLocalListRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: UpdateStatus
)

private abstract class AuthorisationDataMixin(
    @JacksonXmlProperty(localName = "o:idTag")
    val idTag: String,
    @JacksonXmlProperty(localName = "o:idTagInfo")
    val idTagInfo: IdTagInfo? = null
)

@JsonRootName("startTransactionResponse")
private abstract class StartTransactionRespMixin(
    @JacksonXmlProperty(localName = "o:idTagInfo")
    val idTagInfo: IdTagInfo,
    @JacksonXmlProperty(localName = "o:transactionId")
    val transactionId: Int
)

@JsonRootName("startTransactionRequest")
private abstract class StartTransactionReqMixin(
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
private abstract class StatusNotificationRespMixin

@JsonRootName("statusNotificationRequest")
private abstract class StatusNotificationReqMixin(
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
private abstract class StopTransactionRespMixin(
    @JacksonXmlProperty(localName = "o:idTagInfo")
    val idTagInfo: IdTagInfo? = null
)

@JsonRootName("stopTransactionRequest")
private abstract class StopTransactionReqMixin(
    @JacksonXmlProperty(localName = "o:idTag")
    val idTag: String? = null,
    @JacksonXmlProperty(localName = "o:meterStop")
    val meterStop: Int,
    @JacksonXmlProperty(localName = "o:timestamp")
    val timestamp: Instant,
    @JacksonXmlProperty(localName = "o:transactionId")
    val transactionId: Int,
    @JacksonXmlProperty(localName = "o:reason")
    val reason: Reason? = Reason.Local,
    @JacksonXmlProperty(localName = "o:transactionData")
    val transactionData: List<MeterValue>? = null
)

@JsonRootName("unlockConnectorRequest")
private abstract class UnlockConnectorReqMixin(
    @JacksonXmlProperty(localName = "o:connectorId")
    val connectorId: Int
)

@JsonRootName("unlockConnectorResponse")
private abstract class UnlockConnectorRespMixin(
    @JacksonXmlProperty(localName = "o:status")
    val status: UnlockStatus
)

@JsonRootName("updateFirmwareRequest")
private abstract class UpdateFirmwareReqMixin(
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
private abstract class UpdateFirmwareRespMixin
