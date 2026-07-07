package com.izivia.ocpp.soap12

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
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
import com.izivia.ocpp.core12.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core12.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.core12.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core12.model.firmwarestatusnotification.FirmwareStatusNotificationResp
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
import com.izivia.ocpp.soap.SoapBody
import com.izivia.ocpp.soap.SoapFault

@JsonIgnoreProperties(ignoreUnknown = true)
data class Ocpp12SoapBody(
    val authorizeRequest: AuthorizeReq? = null,
    val authorizeResponse: AuthorizeResp? = null,
    val bootNotificationRequest: BootNotificationReq? = null,
    val bootNotificationResponse: BootNotificationResp? = null,
    val changeAvailabilityRequest: ChangeAvailabilityReq? = null,
    val changeAvailabilityResponse: ChangeAvailabilityResp? = null,
    val changeConfigurationRequest: ChangeConfigurationReq? = null,
    val changeConfigurationResponse: ChangeConfigurationResp? = null,
    val clearCacheRequest: ClearCacheReq? = null,
    val clearCacheResponse: ClearCacheResp? = null,
    val diagnosticsStatusNotificationRequest: DiagnosticsStatusNotificationReq? = null,
    val diagnosticsStatusNotificationResponse: DiagnosticsStatusNotificationResp? = null,
    val firmwareStatusNotificationRequest: FirmwareStatusNotificationReq? = null,
    val firmwareStatusNotificationResponse: FirmwareStatusNotificationResp? = null,
    val getDiagnosticsRequest: GetDiagnosticsReq? = null,
    val getDiagnosticsResponse: GetDiagnosticsResp? = null,
    val heartbeatRequest: HeartbeatReq? = null,
    val heartbeatResponse: HeartbeatResp? = null,
    val meterValuesRequest: MeterValuesReq? = null,
    val meterValuesResponse: MeterValuesResp? = null,
    val remoteStartTransactionRequest: RemoteStartTransactionReq? = null,
    val remoteStartTransactionResponse: RemoteStartTransactionResp? = null,
    val remoteStopTransactionRequest: RemoteStopTransactionReq? = null,
    val remoteStopTransactionResponse: RemoteStopTransactionResp? = null,
    val resetRequest: ResetReq? = null,
    val resetResponse: ResetResp? = null,
    val startTransactionRequest: StartTransactionReq? = null,
    val startTransactionResponse: StartTransactionResp? = null,
    val statusNotificationRequest: StatusNotificationReq? = null,
    val statusNotificationResponse: StatusNotificationResp? = null,
    val stopTransactionRequest: StopTransactionReq? = null,
    val stopTransactionResponse: StopTransactionResp? = null,
    val unlockConnectorRequest: UnlockConnectorReq? = null,
    val unlockConnectorResponse: UnlockConnectorResp? = null,
    val updateFirmwareRequest: UpdateFirmwareReq? = null,
    val updateFirmwareResponse: UpdateFirmwareResp? = null,
    val fault: SoapFault? = null
) : SoapBody
