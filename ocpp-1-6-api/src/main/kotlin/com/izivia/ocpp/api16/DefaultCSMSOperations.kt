package com.izivia.ocpp.api16

import com.izivia.ocpp.core16.CSMSOperations
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
import com.izivia.ocpp.core16.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.core16.model.sendlocallist.SendLocalListResp
import com.izivia.ocpp.core16.model.setchargingprofile.SetChargingProfileReq
import com.izivia.ocpp.core16.model.setchargingprofile.SetChargingProfileResp
import com.izivia.ocpp.core16.model.triggermessage.TriggerMessageReq
import com.izivia.ocpp.core16.model.triggermessage.TriggerMessageResp
import com.izivia.ocpp.core16.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core16.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core16.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core16.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import kotlinx.datetime.Clock.System.now

open class DefaultCSMSOperations(val ocppCSCallbacks : IOcppCSCallbacks): CSMSOperations {



    override fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp> =
         OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS, now()),
            req,
            ocppCSCallbacks.reset(req)
        )

    override fun changeAvailability(
        meta: RequestMetadata,
        req: ChangeAvailabilityReq
    ): OperationExecution<ChangeAvailabilityReq, ChangeAvailabilityResp> =
         OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS, now()),
            req,
            ocppCSCallbacks.changeAvailability(req)
        )

    override fun changeConfiguration(
        meta: RequestMetadata,
        req: ChangeConfigurationReq
    ): OperationExecution<ChangeConfigurationReq, ChangeConfigurationResp> =
         OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS, now()),
            req,
            ocppCSCallbacks.changeConfiguration(req)
        )

    override fun clearCache(
        meta: RequestMetadata,
        req: ClearCacheReq
    ): OperationExecution<ClearCacheReq, ClearCacheResp> =
         OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS, now()),
            req,
            ocppCSCallbacks.clearCache(req)
        )

    override fun remoteStartTransaction(
        meta: RequestMetadata,
        req: RemoteStartTransactionReq
    ): OperationExecution<RemoteStartTransactionReq, RemoteStartTransactionResp> =
         OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS, now()),
            req,
            ocppCSCallbacks.remoteStartTransaction(req)
        )

    override fun remoteStopTransaction(
        meta: RequestMetadata,
        req: RemoteStopTransactionReq
    ): OperationExecution<RemoteStopTransactionReq, RemoteStopTransactionResp> =
         OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS, now()),
            req,
            ocppCSCallbacks.remoteStopTransaction(req)
        )

    override fun unlockConnector(
        meta: RequestMetadata,
        req: UnlockConnectorReq
    ): OperationExecution<UnlockConnectorReq, UnlockConnectorResp> =
         OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS, now()),
            req,
            ocppCSCallbacks.unlockConnector(req)
        )

    override fun getConfiguration(
        meta: RequestMetadata,
        req: GetConfigurationReq
    ): OperationExecution<GetConfigurationReq, GetConfigurationResp> =
         OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS, now()),
            req,
            ocppCSCallbacks.getConfiguration(req)
        )

    override fun cancelReservation(
        meta: RequestMetadata,
        req: CancelReservationReq
    ): OperationExecution<CancelReservationReq, CancelReservationResp> =
         OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS, now()),
            req,
            ocppCSCallbacks.cancelReservation(req)
        )

    override fun clearChargingProfile(
        meta: RequestMetadata,
        req: ClearChargingProfileReq
    ): OperationExecution<ClearChargingProfileReq, ClearChargingProfileResp> =
         OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS, now()),
            req,
            ocppCSCallbacks.clearChargingProfile(req)
        )

    override fun getCompositeSchedule(
        meta: RequestMetadata,
        req: GetCompositeScheduleReq
    ): OperationExecution<GetCompositeScheduleReq, GetCompositeScheduleResp> =
         OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS, now()),
            req,
            ocppCSCallbacks.getCompositeSchedule(req)
        )

    override fun getLocalListVersion(
        meta: RequestMetadata,
        req: GetLocalListVersionReq
    ): OperationExecution<GetLocalListVersionReq, GetLocalListVersionResp> =
         OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS, now()),
            req,
            ocppCSCallbacks.getLocalListVersion(req)
        )

    override fun updateFirmware(
        meta: RequestMetadata,
        req: UpdateFirmwareReq
    ): OperationExecution<UpdateFirmwareReq, UpdateFirmwareResp> =
         OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS, now()),
            req,
            ocppCSCallbacks.updateFirmware(req)
        )

    override fun sendLocalList(
        meta: RequestMetadata,
        req: SendLocalListReq
    ): OperationExecution<SendLocalListReq, SendLocalListResp> =
         OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS, now()),
            req,
            ocppCSCallbacks.sendLocalList(req)
        )

    override fun triggerMessage(
        meta: RequestMetadata,
        req: TriggerMessageReq
    ): OperationExecution<TriggerMessageReq, TriggerMessageResp> =
         OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS, now()),
            req,
            ocppCSCallbacks.triggerMessage(req)
        )

    override fun setChargingProfile(
        meta: RequestMetadata,
        req: SetChargingProfileReq
    ): OperationExecution<SetChargingProfileReq, SetChargingProfileResp> =
         OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS, now()),
            req,
            ocppCSCallbacks.setChargingProfile(req)
        )

    override fun reserveNow(
        meta: RequestMetadata,
        req: ReserveNowReq
    ): OperationExecution<ReserveNowReq, ReserveNowResp> =
         OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS, now()),
            req,
            ocppCSCallbacks.reserveNow(req)
        )

    override fun dataTransfer(
        meta: RequestMetadata,
        req: DataTransferReq
    ): OperationExecution<DataTransferReq, DataTransferResp> =
         OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS, now()),
            req,
            ocppCSCallbacks.dataTransfer(req)
        )

    override fun getDiagnostics(
        meta: RequestMetadata,
        req: GetDiagnosticsReq
    ): OperationExecution<GetDiagnosticsReq, GetDiagnosticsResp> =
         OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS, now()),
            req,
            ocppCSCallbacks.getDiagnostics(req)
        )

}