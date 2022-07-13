package com.izivia.ocpp.soap15

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver
import com.izivia.ocpp.core15.model.authorize.AuthorizeReq
import com.izivia.ocpp.core15.model.authorize.AuthorizeResp
import com.izivia.ocpp.core15.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core15.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core15.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.core15.model.cancelreservation.CancelReservationResp
import com.izivia.ocpp.core15.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core15.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.core15.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.core15.model.changeconfiguration.ChangeConfigurationResp
import com.izivia.ocpp.core15.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core15.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core15.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core15.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core15.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core15.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.core15.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core15.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core15.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core15.model.getdiagnostics.GetDiagnosticsResp
import com.izivia.ocpp.core15.model.getlocallistversion.GetLocalListVersionReq
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
import com.izivia.ocpp.core15.model.reset.ResetReq
import com.izivia.ocpp.core15.model.reset.ResetResp
import com.izivia.ocpp.core15.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.core15.model.sendlocallist.SendLocalListResp
import com.izivia.ocpp.core15.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core15.model.starttransaction.StartTransactionResp
import com.izivia.ocpp.core15.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core15.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core15.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core15.model.stoptransaction.StopTransactionResp
import com.izivia.ocpp.core15.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core15.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core15.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core15.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.soap.SoapBody

@JsonIgnoreProperties(ignoreUnknown = true)
data class Ocpp15SoapBody(
    val authorizeRequest: AuthorizeReq?,
    val authorizeResponse: AuthorizeResp?,
    val bootNotificationRequest: BootNotificationReq?,
    val bootNotificationResponse: BootNotificationResp?,
    val cancelReservationRequest: CancelReservationReq?,
    val cancelReservationResponse: CancelReservationResp?,
    val changeAvailabilityRequest: ChangeAvailabilityReq?,
    val changeAvailabilityResponse: ChangeAvailabilityResp?,
    val changeConfigurationRequest: ChangeConfigurationReq?,
    val changeConfigurationResponse: ChangeConfigurationResp?,
    val clearCacheRequest: ClearCacheReq?,
    val clearCacheResponse: ClearCacheResp?,
    val dataTransferRequest: DataTransferReq?,
    val dataTransferResponse: DataTransferResp?,
    val diagnosticsStatusNotificationRequest: DiagnosticsStatusNotificationReq?,
    val diagnosticsStatusNotificationResponse: DiagnosticsStatusNotificationResp?,
    val firmwareStatusNotificationRequest: FirmwareStatusNotificationReq?,
    val firmwareStatusNotificationResponse: FirmwareStatusNotificationResp?,
    val getDiagnosticsRequest: GetDiagnosticsReq?,
    val getDiagnosticsResponse: GetDiagnosticsResp?,
    val getLocalListVersionResponse: GetLocalListVersionReq?,
    val getLocalListVersionReuest: GetLocalListVersionReq?,
    val heartbeatRequest: HeartbeatReq?,
    val heartbeatResponse: HeartbeatResp?,
    val meterValuesRequest: MeterValuesReq?,
    val meterValuesResponse: MeterValuesResp?,
    val remoteStartTransactionRequest: RemoteStartTransactionReq?,
    val remoteStartTransactionResponse: RemoteStartTransactionResp?,
    val remoteStopTransactionRequest: RemoteStopTransactionReq?,
    val remoteStopTransactionResponse: RemoteStopTransactionResp?,
    val reserveNowRequest: ReserveNowReq?,
    val reserveNowResponse: ReserveNowResp?,
    val resetRequest: ResetReq?,
    val resetResponse: ResetResp?,
    val sendLocalListRequest: SendLocalListReq?,
    val sendLocalListResponse: SendLocalListResp?,
    val startTransactionRequest: StartTransactionReq?,
    val startTransactionResponse: StartTransactionResp?,
    val statusNotificationRequest: StatusNotificationReq?,
    val statusNotificationResponse: StatusNotificationResp?,
    val stopTransactionRequest: StopTransactionReq?,
    val stopTransactionResponse: StopTransactionResp?,
    val unlockConnectorRequest: UnlockConnectorReq?,
    val unlockConnectorResponse: UnlockConnectorResp?,
    val updateFirmwareRequest: UpdateFirmwareReq?,
    val updateFirmwareResponse: UpdateFirmwareResp?
) : SoapBody
