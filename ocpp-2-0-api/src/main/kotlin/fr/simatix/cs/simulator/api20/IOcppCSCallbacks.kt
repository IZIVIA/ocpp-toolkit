package fr.simatix.cs.simulator.api20

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


interface IOcppCSCallbacks {
    fun reset(req: ResetReq): ResetResp

    fun changeAvailability(req: ChangeAvailabilityReq): ChangeAvailabilityResp

    fun setVariables(req: SetVariablesReq): SetVariablesResp

    fun clearCache(req: ClearCacheReq):ClearCacheResp

    fun requestStartTransaction(req: RequestStartTransactionReq): RequestStartTransactionResp

    fun requestStopTransaction(req: RequestStopTransactionReq): RequestStopTransactionResp

    fun unlockConnector(req: UnlockConnectorReq): UnlockConnectorResp

    fun getReport(req: GetReportReq): GetReportResp

    fun getBaseReport(req: GetBaseReportReq): GetBaseReportResp

    fun getVariables(req : GetVariablesReq): GetVariablesResp

    fun cancelReservation(req: CancelReservationReq): CancelReservationResp

    fun clearChargingProfile(req: ClearChargingProfileReq): ClearChargingProfileResp

    fun getCompositeSchedule(req: GetCompositeScheduleReq): GetCompositeScheduleResp

    fun getLocalListVersion(req: GetLocalListVersionReq): GetLocalListVersionResp

    fun updateFirmware(req : UpdateFirmwareReq): UpdateFirmwareResp

    fun sendLocalList(req: SendLocalListReq): SendLocalListResp

    fun triggerMessage(req: TriggerMessageReq): TriggerMessageResp

    fun setChargingProfile(req: SetChargingProfileReq):SetChargingProfileResp

    fun reserveNow(req : ReserveNowReq): ReserveNowResp

    fun dataTransfer(req : DataTransferReq): DataTransferResp

    fun certificateSigned(req: CertificateSignedReq): CertificateSignedResp

    fun getLog(req: GetLogReq): GetLogResp

    fun clearDisplayMessage(req: ClearDisplayMessageReq): ClearDisplayMessageResp

    fun getChargingProfiles(req: GetChargingProfilesReq): GetChargingProfilesResp

    fun getInstalledCertificateIds(req: GetInstalledCertificateIdsReq): GetInstalledCertificateIdsResp

    fun installCertificate(req: InstallCertificateReq): InstallCertificateResp

    fun customerInformation(req: CustomerInformationReq):CustomerInformationResp

    fun unpublishFirmware(req: UnpublishFirmwareReq): UnpublishFirmwareResp

    fun setVariableMonitoring(req: SetVariableMonitoringReq): SetVariableMonitoringResp

    fun setMonitoringLevel(req: SetMonitoringLevelReq): SetMonitoringLevelResp

    fun publishFirmware(req: PublishFirmwareReq): PublishFirmwareResp

    fun setNetworkProfile(req: SetNetworkProfileReq): SetNetworkProfileResp

    fun getTransactionStatus(req: GetTransactionStatusReq): GetTransactionStatusResp

    fun setMonitoringBase(req: SetMonitoringBaseReq): SetMonitoringBaseResp

    fun getDisplayMessages(req: GetDisplayMessagesReq): GetDisplayMessagesResp

    fun costUpdated(req: CostUpdatedReq): CostUpdatedResp

    fun setDisplayMessage(req: SetDisplayMessageReq):SetDisplayMessageResp

    fun deleteCertificate(req: DeleteCertificateReq): DeleteCertificateResp

    fun getMonitoringReport(req: GetMonitoringReportReq): GetMonitoringReportResp

    fun clearVariableMonitoring(req: ClearVariableMonitoringReq): ClearVariableMonitoringResp

}