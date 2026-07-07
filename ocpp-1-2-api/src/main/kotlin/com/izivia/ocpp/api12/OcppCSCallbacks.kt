package com.izivia.ocpp.api12

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

interface OcppCSCallbacks {
    fun reset(req: ResetReq): ResetResp = throw NotImplementedError()
    fun changeAvailability(req: ChangeAvailabilityReq): ChangeAvailabilityResp = throw NotImplementedError()
    fun changeConfiguration(req: ChangeConfigurationReq): ChangeConfigurationResp = throw NotImplementedError()
    fun clearCache(req: ClearCacheReq): ClearCacheResp = throw NotImplementedError()
    fun remoteStartTransaction(req: RemoteStartTransactionReq): RemoteStartTransactionResp = throw NotImplementedError()
    fun remoteStopTransaction(req: RemoteStopTransactionReq): RemoteStopTransactionResp = throw NotImplementedError()
    fun unlockConnector(req: UnlockConnectorReq): UnlockConnectorResp = throw NotImplementedError()
    fun updateFirmware(req: UpdateFirmwareReq): UpdateFirmwareResp = throw NotImplementedError()
    fun getDiagnostics(req: GetDiagnosticsReq): GetDiagnosticsResp = throw NotImplementedError()
}
