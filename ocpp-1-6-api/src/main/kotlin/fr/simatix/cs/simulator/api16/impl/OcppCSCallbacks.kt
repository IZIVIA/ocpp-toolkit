package fr.simatix.cs.simulator.api16.impl

import fr.simatix.cs.simulator.api16.IOcppCSCallbacks
import fr.simatix.cs.simulator.core16.model.cancelreservation.CancelReservationReq
import fr.simatix.cs.simulator.core16.model.cancelreservation.CancelReservationResp
import fr.simatix.cs.simulator.core16.model.changeavailability.ChangeAvailabilityReq
import fr.simatix.cs.simulator.core16.model.changeavailability.ChangeAvailabilityResp
import fr.simatix.cs.simulator.core16.model.changeconfiguration.ChangeConfigurationReq
import fr.simatix.cs.simulator.core16.model.changeconfiguration.ChangeConfigurationResp
import fr.simatix.cs.simulator.core16.model.clearcache.ClearCacheReq
import fr.simatix.cs.simulator.core16.model.clearcache.ClearCacheResp
import fr.simatix.cs.simulator.core16.model.clearchargingprofile.ClearChargingProfileReq
import fr.simatix.cs.simulator.core16.model.clearchargingprofile.ClearChargingProfileResp
import fr.simatix.cs.simulator.core16.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.core16.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.core16.model.getcompositeschedule.GetCompositeScheduleReq
import fr.simatix.cs.simulator.core16.model.getcompositeschedule.GetCompositeScheduleResp
import fr.simatix.cs.simulator.core16.model.getconfiguration.GetConfigurationReq
import fr.simatix.cs.simulator.core16.model.getconfiguration.GetConfigurationResp
import fr.simatix.cs.simulator.core16.model.getdiagnostics.GetDiagnosticsReq
import fr.simatix.cs.simulator.core16.model.getdiagnostics.GetDiagnosticsResp
import fr.simatix.cs.simulator.core16.model.getlocallistversion.GetLocalListVersionReq
import fr.simatix.cs.simulator.core16.model.getlocallistversion.GetLocalListVersionResp
import fr.simatix.cs.simulator.core16.model.remotestart.RemoteStartTransactionReq
import fr.simatix.cs.simulator.core16.model.remotestart.RemoteStartTransactionResp
import fr.simatix.cs.simulator.core16.model.remotestop.RemoteStopTransactionReq
import fr.simatix.cs.simulator.core16.model.remotestop.RemoteStopTransactionResp
import fr.simatix.cs.simulator.core16.model.reservenow.ReserveNowReq
import fr.simatix.cs.simulator.core16.model.reservenow.ReserveNowResp
import fr.simatix.cs.simulator.core16.model.reset.ResetReq
import fr.simatix.cs.simulator.core16.model.reset.ResetResp
import fr.simatix.cs.simulator.core16.model.sendlocallist.SendLocalListReq
import fr.simatix.cs.simulator.core16.model.sendlocallist.SendLocalListResp
import fr.simatix.cs.simulator.core16.model.setchargingprofile.SetChargingProfileReq
import fr.simatix.cs.simulator.core16.model.setchargingprofile.SetChargingProfileResp
import fr.simatix.cs.simulator.core16.model.triggermessage.TriggerMessageReq
import fr.simatix.cs.simulator.core16.model.triggermessage.TriggerMessageResp
import fr.simatix.cs.simulator.core16.model.unlockconnector.UnlockConnectorReq
import fr.simatix.cs.simulator.core16.model.unlockconnector.UnlockConnectorResp
import fr.simatix.cs.simulator.core16.model.updatefirmware.UpdateFirmwareReq
import fr.simatix.cs.simulator.core16.model.updatefirmware.UpdateFirmwareResp

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