package com.izivia.ocpp.core16

import com.izivia.ocpp.core16.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.core16.model.cancelreservation.CancelReservationResp
import com.izivia.ocpp.core16.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core16.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.core16.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.core16.model.changeconfiguration.ChangeConfigurationResp
import com.izivia.ocpp.core16.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core16.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core16.model.clearchargingprofile.ClearChargingProfileReq
import com.izivia.ocpp.core16.model.clearchargingprofile.ClearChargingProfileResp
import com.izivia.ocpp.core16.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core16.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core16.model.getcompositeschedule.GetCompositeScheduleReq
import com.izivia.ocpp.core16.model.getcompositeschedule.GetCompositeScheduleResp
import com.izivia.ocpp.core16.model.getconfiguration.GetConfigurationReq
import com.izivia.ocpp.core16.model.getconfiguration.GetConfigurationResp
import com.izivia.ocpp.core16.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core16.model.getdiagnostics.GetDiagnosticsResp
import com.izivia.ocpp.core16.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.core16.model.getlocallistversion.GetLocalListVersionResp
import com.izivia.ocpp.core16.model.remotestart.RemoteStartTransactionReq
import com.izivia.ocpp.core16.model.remotestart.RemoteStartTransactionResp
import com.izivia.ocpp.core16.model.remotestop.RemoteStopTransactionReq
import com.izivia.ocpp.core16.model.remotestop.RemoteStopTransactionResp
import com.izivia.ocpp.core16.model.reservenow.ReserveNowReq
import com.izivia.ocpp.core16.model.reservenow.ReserveNowResp
import com.izivia.ocpp.core16.model.reset.ResetReq
import com.izivia.ocpp.core16.model.reset.ResetResp
import com.izivia.ocpp.core16.model.triggermessage.TriggerMessageReq
import com.izivia.ocpp.core16.model.triggermessage.TriggerMessageResp
import com.izivia.ocpp.core16.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.core16.model.sendlocallist.SendLocalListResp
import com.izivia.ocpp.core16.model.setchargingprofile.SetChargingProfileReq
import com.izivia.ocpp.core16.model.setchargingprofile.SetChargingProfileResp
import com.izivia.ocpp.core16.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core16.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core16.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core16.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata

interface CSMSOperations {

    fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp>

    fun changeAvailability(meta: RequestMetadata, req: ChangeAvailabilityReq): OperationExecution<ChangeAvailabilityReq, ChangeAvailabilityResp>

    fun changeConfiguration(meta: RequestMetadata,  req: ChangeConfigurationReq): OperationExecution<ChangeConfigurationReq, ChangeConfigurationResp>

    fun clearCache(meta: RequestMetadata,  req: ClearCacheReq): OperationExecution<ClearCacheReq, ClearCacheResp>

    fun remoteStartTransaction(meta: RequestMetadata,  req: RemoteStartTransactionReq): OperationExecution<RemoteStartTransactionReq, RemoteStartTransactionResp>

    fun remoteStopTransaction(meta: RequestMetadata,  req: RemoteStopTransactionReq): OperationExecution<RemoteStopTransactionReq, RemoteStopTransactionResp>

    fun unlockConnector(meta: RequestMetadata,  req: UnlockConnectorReq): OperationExecution<UnlockConnectorReq, UnlockConnectorResp>

    fun getConfiguration(meta: RequestMetadata,  req: GetConfigurationReq): OperationExecution<GetConfigurationReq, GetConfigurationResp>

    fun cancelReservation(meta: RequestMetadata, req: CancelReservationReq): OperationExecution<CancelReservationReq, CancelReservationResp>

    fun clearChargingProfile(meta: RequestMetadata, req: ClearChargingProfileReq): OperationExecution<ClearChargingProfileReq, ClearChargingProfileResp>

    fun getCompositeSchedule(meta: RequestMetadata, req: GetCompositeScheduleReq): OperationExecution<GetCompositeScheduleReq, GetCompositeScheduleResp>

    fun getLocalListVersion(meta: RequestMetadata, req: GetLocalListVersionReq): OperationExecution<GetLocalListVersionReq, GetLocalListVersionResp>

    fun updateFirmware(meta: RequestMetadata, req : UpdateFirmwareReq): OperationExecution<UpdateFirmwareReq, UpdateFirmwareResp>

    fun sendLocalList(meta: RequestMetadata, req: SendLocalListReq): OperationExecution<SendLocalListReq, SendLocalListResp>

    fun triggerMessage(meta: RequestMetadata,  req: TriggerMessageReq): OperationExecution<TriggerMessageReq, TriggerMessageResp>

    fun setChargingProfile(meta: RequestMetadata, req: SetChargingProfileReq): OperationExecution<SetChargingProfileReq, SetChargingProfileResp>

    fun reserveNow(meta: RequestMetadata,  req: ReserveNowReq): OperationExecution<ReserveNowReq, ReserveNowResp>

    fun dataTransfer(meta: RequestMetadata,  req: DataTransferReq): OperationExecution<DataTransferReq, DataTransferResp>

    fun getDiagnostics(meta: RequestMetadata, req: GetDiagnosticsReq): OperationExecution<GetDiagnosticsReq, GetDiagnosticsResp>
}