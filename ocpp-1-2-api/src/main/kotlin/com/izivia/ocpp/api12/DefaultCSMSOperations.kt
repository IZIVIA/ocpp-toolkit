package com.izivia.ocpp.api12

import com.izivia.ocpp.core12.CSMSOperations
import com.izivia.ocpp.core12.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core12.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.core12.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.core12.model.changeconfiguration.ChangeConfigurationResp
import com.izivia.ocpp.core12.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core12.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core12.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core12.model.getdiagnostics.GetDiagnosticsResp
import com.izivia.ocpp.core12.model.remotestart.RemoteStartTransactionReq
import com.izivia.ocpp.core12.model.remotestart.RemoteStartTransactionResp
import com.izivia.ocpp.core12.model.remotestop.RemoteStopTransactionReq
import com.izivia.ocpp.core12.model.remotestop.RemoteStopTransactionResp
import com.izivia.ocpp.core12.model.reset.ResetReq
import com.izivia.ocpp.core12.model.reset.ResetResp
import com.izivia.ocpp.core12.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core12.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core12.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core12.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import kotlin.time.Clock

class DefaultCSMSOperations(
    private val ocppCSCallbacks: OcppCSCallbacks
) : CSMSOperations {
    override fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp> =
        OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()), req, ocppCSCallbacks.reset(req))

    override fun changeAvailability(
        meta: RequestMetadata,
        req: ChangeAvailabilityReq
    ): OperationExecution<ChangeAvailabilityReq, ChangeAvailabilityResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()),
            req,
            ocppCSCallbacks.changeAvailability(req)
        )

    override fun changeConfiguration(
        meta: RequestMetadata,
        req: ChangeConfigurationReq
    ): OperationExecution<ChangeConfigurationReq, ChangeConfigurationResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()),
            req,
            ocppCSCallbacks.changeConfiguration(req)
        )

    override fun clearCache(meta: RequestMetadata, req: ClearCacheReq): OperationExecution<ClearCacheReq, ClearCacheResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()),
            req,
            ocppCSCallbacks.clearCache(req)
        )

    override fun remoteStartTransaction(
        meta: RequestMetadata,
        req: RemoteStartTransactionReq
    ): OperationExecution<RemoteStartTransactionReq, RemoteStartTransactionResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()),
            req,
            ocppCSCallbacks.remoteStartTransaction(req)
        )

    override fun remoteStopTransaction(
        meta: RequestMetadata,
        req: RemoteStopTransactionReq
    ): OperationExecution<RemoteStopTransactionReq, RemoteStopTransactionResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()),
            req,
            ocppCSCallbacks.remoteStopTransaction(req)
        )

    override fun unlockConnector(
        meta: RequestMetadata,
        req: UnlockConnectorReq
    ): OperationExecution<UnlockConnectorReq, UnlockConnectorResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()),
            req,
            ocppCSCallbacks.unlockConnector(req)
        )

    override fun updateFirmware(
        meta: RequestMetadata,
        req: UpdateFirmwareReq
    ): OperationExecution<UpdateFirmwareReq, UpdateFirmwareResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()),
            req,
            ocppCSCallbacks.updateFirmware(req)
        )

    override fun getDiagnostics(
        meta: RequestMetadata,
        req: GetDiagnosticsReq
    ): OperationExecution<GetDiagnosticsReq, GetDiagnosticsResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, Clock.System.now()),
            req,
            ocppCSCallbacks.getDiagnostics(req)
        )
}
