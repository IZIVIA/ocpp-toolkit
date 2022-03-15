package fr.simatix.cs.simulator.core16

import fr.simatix.cs.simulator.core16.model.changeavailability.ChangeAvailabilityReq
import fr.simatix.cs.simulator.core16.model.changeavailability.ChangeAvailabilityResp
import fr.simatix.cs.simulator.core16.model.changeconfiguration.ChangeConfigurationReq
import fr.simatix.cs.simulator.core16.model.changeconfiguration.ChangeConfigurationResp
import fr.simatix.cs.simulator.core16.model.clearcache.ClearCacheReq
import fr.simatix.cs.simulator.core16.model.clearcache.ClearCacheResp
import fr.simatix.cs.simulator.core16.model.remotestart.RemoteStartTransactionReq
import fr.simatix.cs.simulator.core16.model.remotestart.RemoteStartTransactionResp
import fr.simatix.cs.simulator.core16.model.remotestop.RemoteStopTransactionReq
import fr.simatix.cs.simulator.core16.model.remotestop.RemoteStopTransactionResp
import fr.simatix.cs.simulator.core16.model.reset.ResetReq
import fr.simatix.cs.simulator.core16.model.reset.ResetResp
import fr.simatix.cs.simulator.core16.model.unlockconnector.UnlockConnectorReq
import fr.simatix.cs.simulator.core16.model.unlockconnector.UnlockConnectorResp
import fr.simatix.cs.simulator.operation.information.OperationExecution
import fr.simatix.cs.simulator.operation.information.RequestMetadata

interface CSMSOperations {

    fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp>

    fun changeAvailability(meta: RequestMetadata, req: ChangeAvailabilityReq): OperationExecution<ChangeAvailabilityReq, ChangeAvailabilityResp>

    fun changeConfiguration(meta: RequestMetadata,  req: ChangeConfigurationReq): OperationExecution<ChangeConfigurationReq, ChangeConfigurationResp>

    fun clearCache(meta: RequestMetadata,  req: ClearCacheReq): OperationExecution<ClearCacheReq, ClearCacheResp>

    fun remoteStartTransaction(meta: RequestMetadata,  req: RemoteStartTransactionReq): OperationExecution<RemoteStartTransactionReq, RemoteStartTransactionResp>

    fun remoteStopTransaction(meta: RequestMetadata,  req: RemoteStopTransactionReq): OperationExecution<RemoteStopTransactionReq, RemoteStopTransactionResp>

    fun unlockConnector(meta: RequestMetadata,  req: UnlockConnectorReq): OperationExecution<UnlockConnectorReq, UnlockConnectorResp>
}