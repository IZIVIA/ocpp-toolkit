package com.izivia.ocpp.api16.impl

import com.izivia.ocpp.api16.IOcppCSCallbacks
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

open class OcppCSCallbacks : IOcppCSCallbacks {

    override fun reset (req: ResetReq): ResetResp {
        throw NotImplementedError()
    }

    override fun changeAvailability(
        req: ChangeAvailabilityReq
    ): ChangeAvailabilityResp {
        throw NotImplementedError()
    }

    override fun changeConfiguration(
        req: ChangeConfigurationReq
    ): ChangeConfigurationResp {
        throw NotImplementedError()
    }

    override fun clearCache(
        req: ClearCacheReq
    ): ClearCacheResp {
        throw NotImplementedError()
    }

    override fun remoteStartTransaction(
        req: RemoteStartTransactionReq
    ): RemoteStartTransactionResp {
        throw NotImplementedError()
    }

    override fun remoteStopTransaction(
        req: RemoteStopTransactionReq
    ): RemoteStopTransactionResp {
        throw NotImplementedError()
    }

    override fun unlockConnector(
        req: UnlockConnectorReq
    ): UnlockConnectorResp {
        throw NotImplementedError()
    }

    override fun getConfiguration(
        req: GetConfigurationReq
    ): GetConfigurationResp {
        throw NotImplementedError()
    }

    override fun cancelReservation(
        req: CancelReservationReq
    ): CancelReservationResp {
        throw NotImplementedError()
    }

    override fun clearChargingProfile(
        req: ClearChargingProfileReq
    ): ClearChargingProfileResp {
        throw NotImplementedError()
    }

    override fun getCompositeSchedule(
        req: GetCompositeScheduleReq
    ): GetCompositeScheduleResp {
        throw NotImplementedError()
    }

    override fun getLocalListVersion(
        req: GetLocalListVersionReq
    ): GetLocalListVersionResp {
        throw NotImplementedError()
    }

    override fun updateFirmware(
        req: UpdateFirmwareReq
    ): UpdateFirmwareResp {
        throw NotImplementedError()
    }

    override fun sendLocalList(
        req: SendLocalListReq
    ): SendLocalListResp {
        throw NotImplementedError()
    }

    override fun triggerMessage(
        req: TriggerMessageReq
    ): TriggerMessageResp {
        throw NotImplementedError()
    }

    override fun setChargingProfile(
        req: SetChargingProfileReq
    ): SetChargingProfileResp {
        throw NotImplementedError()
    }

    override fun reserveNow(
        req: ReserveNowReq
    ): ReserveNowResp {
        throw NotImplementedError()
    }

    override fun dataTransfer(
        req: DataTransferReq
    ): DataTransferResp {
        throw NotImplementedError()
    }

    override fun getDiagnostics(
        req: GetDiagnosticsReq
    ): GetDiagnosticsResp {
        throw NotImplementedError()
    }
}