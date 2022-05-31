package fr.simatix.cs.simulator.api20.impl

import fr.simatix.cs.simulator.api20.IOcppCSCallbacks
import fr.simatix.cs.simulator.core20.model.cancelreservation.CancelReservationReq
import fr.simatix.cs.simulator.core20.model.cancelreservation.CancelReservationResp
import fr.simatix.cs.simulator.core20.model.certificateSigned.CertificateSignedReq
import fr.simatix.cs.simulator.core20.model.certificateSigned.CertificateSignedResp
import fr.simatix.cs.simulator.core20.model.changeavailability.ChangeAvailabilityReq
import fr.simatix.cs.simulator.core20.model.changeavailability.ChangeAvailabilityResp
import fr.simatix.cs.simulator.core20.model.clearcache.ClearCacheReq
import fr.simatix.cs.simulator.core20.model.clearcache.ClearCacheResp
import fr.simatix.cs.simulator.core20.model.clearchargingprofile.ClearChargingProfileReq
import fr.simatix.cs.simulator.core20.model.clearchargingprofile.ClearChargingProfileResp
import fr.simatix.cs.simulator.core20.model.cleardisplaymessage.ClearDisplayMessageReq
import fr.simatix.cs.simulator.core20.model.cleardisplaymessage.ClearDisplayMessageResp
import fr.simatix.cs.simulator.core20.model.clearvariablemonitoring.ClearVariableMonitoringReq
import fr.simatix.cs.simulator.core20.model.clearvariablemonitoring.ClearVariableMonitoringResp
import fr.simatix.cs.simulator.core20.model.costupdated.CostUpdatedReq
import fr.simatix.cs.simulator.core20.model.costupdated.CostUpdatedResp
import fr.simatix.cs.simulator.core20.model.customerinformation.CustomerInformationReq
import fr.simatix.cs.simulator.core20.model.customerinformation.CustomerInformationResp
import fr.simatix.cs.simulator.core20.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.core20.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.core20.model.deletecertificate.DeleteCertificateReq
import fr.simatix.cs.simulator.core20.model.deletecertificate.DeleteCertificateResp
import fr.simatix.cs.simulator.core20.model.getbasereport.GetBaseReportReq
import fr.simatix.cs.simulator.core20.model.getbasereport.GetBaseReportResp
import fr.simatix.cs.simulator.core20.model.getchargingprofiles.GetChargingProfilesReq
import fr.simatix.cs.simulator.core20.model.getchargingprofiles.GetChargingProfilesResp
import fr.simatix.cs.simulator.core20.model.getcompositeschedule.GetCompositeScheduleReq
import fr.simatix.cs.simulator.core20.model.getcompositeschedule.GetCompositeScheduleResp
import fr.simatix.cs.simulator.core20.model.getdisplaymessages.GetDisplayMessagesReq
import fr.simatix.cs.simulator.core20.model.getdisplaymessages.GetDisplayMessagesResp
import fr.simatix.cs.simulator.core20.model.getinstalledcertificateids.GetInstalledCertificateIdsReq
import fr.simatix.cs.simulator.core20.model.getinstalledcertificateids.GetInstalledCertificateIdsResp
import fr.simatix.cs.simulator.core20.model.getlocallistversion.GetLocalListVersionReq
import fr.simatix.cs.simulator.core20.model.getlocallistversion.GetLocalListVersionResp
import fr.simatix.cs.simulator.core20.model.getlog.GetLogReq
import fr.simatix.cs.simulator.core20.model.getlog.GetLogResp
import fr.simatix.cs.simulator.core20.model.getmonitoringreport.GetMonitoringReportReq
import fr.simatix.cs.simulator.core20.model.getmonitoringreport.GetMonitoringReportResp
import fr.simatix.cs.simulator.core20.model.getreport.GetReportReq
import fr.simatix.cs.simulator.core20.model.getreport.GetReportResp
import fr.simatix.cs.simulator.core20.model.gettransactionstatus.GetTransactionStatusReq
import fr.simatix.cs.simulator.core20.model.gettransactionstatus.GetTransactionStatusResp
import fr.simatix.cs.simulator.core20.model.getvariables.GetVariablesReq
import fr.simatix.cs.simulator.core20.model.getvariables.GetVariablesResp
import fr.simatix.cs.simulator.core20.model.installcertificate.InstallCertificateReq
import fr.simatix.cs.simulator.core20.model.installcertificate.InstallCertificateResp
import fr.simatix.cs.simulator.core20.model.publishfirmware.PublishFirmwareReq
import fr.simatix.cs.simulator.core20.model.publishfirmware.PublishFirmwareResp
import fr.simatix.cs.simulator.core20.model.remotestart.RequestStartTransactionReq
import fr.simatix.cs.simulator.core20.model.remotestart.RequestStartTransactionResp
import fr.simatix.cs.simulator.core20.model.remotestop.RequestStopTransactionReq
import fr.simatix.cs.simulator.core20.model.remotestop.RequestStopTransactionResp
import fr.simatix.cs.simulator.core20.model.reservenow.ReserveNowReq
import fr.simatix.cs.simulator.core20.model.reservenow.ReserveNowResp
import fr.simatix.cs.simulator.core20.model.reset.ResetReq
import fr.simatix.cs.simulator.core20.model.reset.ResetResp
import fr.simatix.cs.simulator.core20.model.sendlocallist.SendLocalListReq
import fr.simatix.cs.simulator.core20.model.sendlocallist.SendLocalListResp
import fr.simatix.cs.simulator.core20.model.setchargingprofile.SetChargingProfileReq
import fr.simatix.cs.simulator.core20.model.setchargingprofile.SetChargingProfileResp
import fr.simatix.cs.simulator.core20.model.setdisplaymessage.SetDisplayMessageReq
import fr.simatix.cs.simulator.core20.model.setdisplaymessage.SetDisplayMessageResp
import fr.simatix.cs.simulator.core20.model.setmonitoringbase.SetMonitoringBaseReq
import fr.simatix.cs.simulator.core20.model.setmonitoringbase.SetMonitoringBaseResp
import fr.simatix.cs.simulator.core20.model.setmonitoringlevel.SetMonitoringLevelReq
import fr.simatix.cs.simulator.core20.model.setmonitoringlevel.SetMonitoringLevelResp
import fr.simatix.cs.simulator.core20.model.setnetworkprofile.SetNetworkProfileReq
import fr.simatix.cs.simulator.core20.model.setnetworkprofile.SetNetworkProfileResp
import fr.simatix.cs.simulator.core20.model.setvariablemonitoring.SetVariableMonitoringReq
import fr.simatix.cs.simulator.core20.model.setvariablemonitoring.SetVariableMonitoringResp
import fr.simatix.cs.simulator.core20.model.setvariables.SetVariablesReq
import fr.simatix.cs.simulator.core20.model.setvariables.SetVariablesResp
import fr.simatix.cs.simulator.core20.model.triggermessage.TriggerMessageReq
import fr.simatix.cs.simulator.core20.model.triggermessage.TriggerMessageResp
import fr.simatix.cs.simulator.core20.model.unlockconnector.UnlockConnectorReq
import fr.simatix.cs.simulator.core20.model.unlockconnector.UnlockConnectorResp
import fr.simatix.cs.simulator.core20.model.unpublishfirmware.UnpublishFirmwareReq
import fr.simatix.cs.simulator.core20.model.unpublishfirmware.UnpublishFirmwareResp
import fr.simatix.cs.simulator.core20.model.updatefirmware.UpdateFirmwareReq
import fr.simatix.cs.simulator.core20.model.updatefirmware.UpdateFirmwareResp

open class OcppCSCallbacks : IOcppCSCallbacks {
    override fun reset(req: ResetReq): ResetResp {
        throw NotImplementedError()
    }

    override fun changeAvailability(req: ChangeAvailabilityReq): ChangeAvailabilityResp {
        throw NotImplementedError()
    }

    override fun setVariables(req: SetVariablesReq): SetVariablesResp {
        throw NotImplementedError()
    }

    override fun clearCache(req: ClearCacheReq): ClearCacheResp {
        throw NotImplementedError()
    }

    override fun requestStartTransaction(req: RequestStartTransactionReq): RequestStartTransactionResp {
        throw NotImplementedError()
    }

    override fun requestStopTransaction(req: RequestStopTransactionReq): RequestStopTransactionResp {
        throw NotImplementedError()
    }

    override fun unlockConnector(req: UnlockConnectorReq): UnlockConnectorResp {
        throw NotImplementedError()
    }

    override fun getReport(req: GetReportReq): GetReportResp {
        throw NotImplementedError()
    }

    override fun getBaseReport(req: GetBaseReportReq): GetBaseReportResp {
        throw NotImplementedError()
    }

    override fun getVariables(req: GetVariablesReq): GetVariablesResp {
        throw NotImplementedError()
    }

    override fun cancelReservation(req: CancelReservationReq): CancelReservationResp {
        throw NotImplementedError()
    }

    override fun clearChargingProfile(req: ClearChargingProfileReq): ClearChargingProfileResp {
        throw NotImplementedError()
    }

    override fun getCompositeSchedule(req: GetCompositeScheduleReq): GetCompositeScheduleResp {
        throw NotImplementedError()
    }

    override fun getLocalListVersion(req: GetLocalListVersionReq): GetLocalListVersionResp {
        throw NotImplementedError()
    }

    override fun updateFirmware(req: UpdateFirmwareReq): UpdateFirmwareResp {
        throw NotImplementedError()
    }

    override fun sendLocalList(req: SendLocalListReq): SendLocalListResp {
        throw NotImplementedError()
    }

    override fun triggerMessage(req: TriggerMessageReq): TriggerMessageResp {
        throw NotImplementedError()
    }

    override fun setChargingProfile(req: SetChargingProfileReq): SetChargingProfileResp {
        throw NotImplementedError()
    }

    override fun reserveNow(req: ReserveNowReq): ReserveNowResp {
        throw NotImplementedError()
    }

    override fun dataTransfer(req: DataTransferReq): DataTransferResp {
        throw NotImplementedError()
    }

    override fun certificateSigned(req: CertificateSignedReq): CertificateSignedResp {
        throw NotImplementedError()
    }

    override fun getLog(req: GetLogReq): GetLogResp {
        throw NotImplementedError()
    }

    override fun clearDisplayMessage(req: ClearDisplayMessageReq): ClearDisplayMessageResp {
        throw NotImplementedError()
    }

    override fun getChargingProfiles(req: GetChargingProfilesReq): GetChargingProfilesResp {
        throw NotImplementedError()
    }

    override fun getInstalledCertificateIds(req: GetInstalledCertificateIdsReq): GetInstalledCertificateIdsResp {
        throw NotImplementedError()
    }

    override fun installCertificate(req: InstallCertificateReq): InstallCertificateResp {
        throw NotImplementedError()
    }

    override fun customerInformation(req: CustomerInformationReq): CustomerInformationResp {
        throw NotImplementedError()
    }

    override fun unpublishFirmware(req: UnpublishFirmwareReq): UnpublishFirmwareResp {
        throw NotImplementedError()
    }

    override fun setVariableMonitoring(req: SetVariableMonitoringReq): SetVariableMonitoringResp {
        throw NotImplementedError()
    }

    override fun setMonitoringLevel(req: SetMonitoringLevelReq): SetMonitoringLevelResp {
        throw NotImplementedError()
    }

    override fun publishFirmware(req: PublishFirmwareReq): PublishFirmwareResp {
        throw NotImplementedError()
    }

    override fun setNetworkProfile(req: SetNetworkProfileReq): SetNetworkProfileResp {
        throw NotImplementedError()
    }

    override fun getTransactionStatus(req: GetTransactionStatusReq): GetTransactionStatusResp {
        throw NotImplementedError()
    }

    override fun setMonitoringBase(req: SetMonitoringBaseReq): SetMonitoringBaseResp {
        throw NotImplementedError()
    }

    override fun getDisplayMessages(req: GetDisplayMessagesReq): GetDisplayMessagesResp {
        throw NotImplementedError()
    }

    override fun costUpdated(req: CostUpdatedReq): CostUpdatedResp {
        throw NotImplementedError()
    }

    override fun setDisplayMessage(req: SetDisplayMessageReq): SetDisplayMessageResp {
        throw NotImplementedError()
    }

    override fun deleteCertificate(req: DeleteCertificateReq): DeleteCertificateResp {
        throw NotImplementedError()
    }

    override fun getMonitoringReport(req: GetMonitoringReportReq): GetMonitoringReportResp {
        throw NotImplementedError()
    }

    override fun clearVariableMonitoring(req: ClearVariableMonitoringReq): ClearVariableMonitoringResp {
        throw NotImplementedError()
    }
}