package com.izivia.ocpp.api16

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

interface OcppCSCallbacks {
    fun reset(req: ResetReq): ResetResp = throw NotImplementedError()
    fun changeAvailability(req: ChangeAvailabilityReq): ChangeAvailabilityResp = throw NotImplementedError()

    fun changeConfiguration(req: ChangeConfigurationReq): ChangeConfigurationResp = throw NotImplementedError()

    fun clearCache(req: ClearCacheReq): ClearCacheResp = throw NotImplementedError()

    fun remoteStartTransaction(req: RemoteStartTransactionReq): RemoteStartTransactionResp = throw NotImplementedError()

    fun remoteStopTransaction(req: RemoteStopTransactionReq): RemoteStopTransactionResp = throw NotImplementedError()

    fun unlockConnector(req: UnlockConnectorReq): UnlockConnectorResp = throw NotImplementedError()

    fun getConfiguration(req: GetConfigurationReq): GetConfigurationResp = throw NotImplementedError()

    fun cancelReservation(req: CancelReservationReq): CancelReservationResp = throw NotImplementedError()

    fun clearChargingProfile(req: ClearChargingProfileReq): ClearChargingProfileResp = throw NotImplementedError()

    fun getCompositeSchedule(req: GetCompositeScheduleReq): GetCompositeScheduleResp = throw NotImplementedError()

    fun getLocalListVersion(req: GetLocalListVersionReq): GetLocalListVersionResp = throw NotImplementedError()

    fun updateFirmware(req: UpdateFirmwareReq): UpdateFirmwareResp = throw NotImplementedError()

    fun sendLocalList(req: SendLocalListReq): SendLocalListResp = throw NotImplementedError()

    fun triggerMessage(req: TriggerMessageReq): TriggerMessageResp = throw NotImplementedError()

    fun setChargingProfile(req: SetChargingProfileReq): SetChargingProfileResp = throw NotImplementedError()

    fun reserveNow(req: ReserveNowReq): ReserveNowResp = throw NotImplementedError()

    fun dataTransfer(req: DataTransferReq): DataTransferResp = throw NotImplementedError()

    fun getDiagnostics(req: GetDiagnosticsReq): GetDiagnosticsResp = throw NotImplementedError()
}