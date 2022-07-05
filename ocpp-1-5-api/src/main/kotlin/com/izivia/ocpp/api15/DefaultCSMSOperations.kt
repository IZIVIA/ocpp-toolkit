package com.izivia.ocpp.api15

import com.izivia.ocpp.core15.CMSOperations
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
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import kotlinx.datetime.Clock

class DefaultCSMSOperations(val ocppCSCallbacks: OcppCSCallbacks) : CMSOperations {
    override fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()), req, ocppCSCallbacks.reset(req)
        )

    override fun changeAvailability(
        meta: RequestMetadata, req: ChangeAvailabilityReq
    ): OperationExecution<ChangeAvailabilityReq, ChangeAvailabilityResp> = OperationExecution(
        ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()), req, ocppCSCallbacks.changeAvailability(req)
    )

    override fun changeConfiguration(
        meta: RequestMetadata, req: ChangeConfigurationReq
    ): OperationExecution<ChangeConfigurationReq, ChangeConfigurationResp> = OperationExecution(
        ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()),
        req,
        ocppCSCallbacks.changeConfiguration(req)
    )

    override fun clearCache(
        meta: RequestMetadata, req: ClearCacheReq
    ): OperationExecution<ClearCacheReq, ClearCacheResp> = OperationExecution(
        ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()), req, ocppCSCallbacks.clearCache(req)
    )

    override fun remoteStartTransaction(
        meta: RequestMetadata, req: RemoteStartTransactionReq
    ): OperationExecution<RemoteStartTransactionReq, RemoteStartTransactionResp> = OperationExecution(
        ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()),
        req,
        ocppCSCallbacks.remoteStartTransaction(req)
    )

    override fun remoteStopTransaction(
        meta: RequestMetadata, req: RemoteStopTransactionReq
    ): OperationExecution<RemoteStopTransactionReq, RemoteStopTransactionResp> = OperationExecution(
        ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()),
        req,
        ocppCSCallbacks.remoteStopTransaction(req)
    )

    override fun unlockConnector(
        meta: RequestMetadata, req: UnlockConnectorReq
    ): OperationExecution<UnlockConnectorReq, UnlockConnectorResp> = OperationExecution(
        ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()), req, ocppCSCallbacks.unlockConnector(req)
    )

    override fun getConfiguration(
        meta: RequestMetadata, req: GetConfigurationReq
    ): OperationExecution<GetConfigurationReq, GetConfigurationResp> = OperationExecution(
        ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()), req, ocppCSCallbacks.getConfiguration(req)
    )

    override fun cancelReservation(
        meta: RequestMetadata, req: CancelReservationReq
    ): OperationExecution<CancelReservationReq, CancelReservationResp> = OperationExecution(
        ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()), req, ocppCSCallbacks.cancelReservation(req)
    )

    override fun getLocalListVersion(
        meta: RequestMetadata, req: GetLocalListVersionReq
    ): OperationExecution<GetLocalListVersionReq, GetLocalListVersionResp> = OperationExecution(
        ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()),
        req,
        ocppCSCallbacks.getLocalListVersion(req)
    )

    override fun updateFirmware(
        meta: RequestMetadata, req: UpdateFirmwareReq
    ): OperationExecution<UpdateFirmwareReq, UpdateFirmwareResp> = OperationExecution(
        ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()), req, ocppCSCallbacks.updateFirmware(req)
    )

    override fun sendLocalList(
        meta: RequestMetadata, req: SendLocalListReq
    ): OperationExecution<SendLocalListReq, SendLocalListResp> = OperationExecution(
        ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()), req, ocppCSCallbacks.sendLocalList(req)
    )

    override fun reserveNow(
        meta: RequestMetadata, req: ReserveNowReq
    ): OperationExecution<ReserveNowReq, ReserveNowResp> = OperationExecution(
        ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()), req, ocppCSCallbacks.reserveNow(req)
    )

    override fun dataTransfer(
        meta: RequestMetadata, req: DataTransferReq
    ): OperationExecution<DataTransferReq, DataTransferResp> = OperationExecution(
        ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()), req, ocppCSCallbacks.dataTransfer(req)
    )

    override fun getDiagnostics(
        meta: RequestMetadata, req: GetDiagnosticsReq
    ): OperationExecution<GetDiagnosticsReq, GetDiagnosticsResp> = OperationExecution(
        ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()), req, ocppCSCallbacks.getDiagnostics(req)
    )
}
