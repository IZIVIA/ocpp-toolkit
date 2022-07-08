package com.izivia.ocpp.core15

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
import com.izivia.ocpp.core15.model.getconfiguration.GetConfigurationReq
import com.izivia.ocpp.core15.model.getconfiguration.GetConfigurationResp
import com.izivia.ocpp.core15.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core15.model.getdiagnostics.GetDiagnosticsResp
import com.izivia.ocpp.core15.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.core15.model.getlocallistversion.GetLocalListVersionResp
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
import com.izivia.ocpp.core15.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core15.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core15.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core15.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.operation.information.CSCallbacks
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata

interface CSMSOperations : CSCallbacks {
    fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp>

    fun changeAvailability(
        meta: RequestMetadata, req: ChangeAvailabilityReq
    ): OperationExecution<ChangeAvailabilityReq, ChangeAvailabilityResp>

    fun changeConfiguration(
        meta: RequestMetadata, req: ChangeConfigurationReq
    ): OperationExecution<ChangeConfigurationReq, ChangeConfigurationResp>

    fun clearCache(meta: RequestMetadata, req: ClearCacheReq): OperationExecution<ClearCacheReq, ClearCacheResp>

    fun remoteStartTransaction(
        meta: RequestMetadata, req: RemoteStartTransactionReq
    ): OperationExecution<RemoteStartTransactionReq, RemoteStartTransactionResp>

    fun remoteStopTransaction(
        meta: RequestMetadata, req: RemoteStopTransactionReq
    ): OperationExecution<RemoteStopTransactionReq, RemoteStopTransactionResp>

    fun unlockConnector(
        meta: RequestMetadata, req: UnlockConnectorReq
    ): OperationExecution<UnlockConnectorReq, UnlockConnectorResp>

    fun getConfiguration(
        meta: RequestMetadata, req: GetConfigurationReq
    ): OperationExecution<GetConfigurationReq, GetConfigurationResp>

    fun cancelReservation(
        meta: RequestMetadata, req: CancelReservationReq
    ): OperationExecution<CancelReservationReq, CancelReservationResp>

    fun getLocalListVersion(
        meta: RequestMetadata, req: GetLocalListVersionReq
    ): OperationExecution<GetLocalListVersionReq, GetLocalListVersionResp>

    fun updateFirmware(
        meta: RequestMetadata, req: UpdateFirmwareReq
    ): OperationExecution<UpdateFirmwareReq, UpdateFirmwareResp>

    fun sendLocalList(
        meta: RequestMetadata, req: SendLocalListReq
    ): OperationExecution<SendLocalListReq, SendLocalListResp>

    fun reserveNow(meta: RequestMetadata, req: ReserveNowReq): OperationExecution<ReserveNowReq, ReserveNowResp>

    fun dataTransfer(meta: RequestMetadata, req: DataTransferReq): OperationExecution<DataTransferReq, DataTransferResp>

    fun getDiagnostics(
        meta: RequestMetadata, req: GetDiagnosticsReq
    ): OperationExecution<GetDiagnosticsReq, GetDiagnosticsResp>
}
