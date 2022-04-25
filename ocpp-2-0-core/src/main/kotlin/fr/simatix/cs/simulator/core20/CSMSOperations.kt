package fr.simatix.cs.simulator.core20

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
import fr.simatix.cs.simulator.core20.model.customerinformation.CustomerInformationReq
import fr.simatix.cs.simulator.core20.model.customerinformation.CustomerInformationResp
import fr.simatix.cs.simulator.core20.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.core20.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.core20.model.getbasereport.GetBaseReportReq
import fr.simatix.cs.simulator.core20.model.getbasereport.GetBaseReportResp
import fr.simatix.cs.simulator.core20.model.getchargingprofiles.GetChargingProfilesReq
import fr.simatix.cs.simulator.core20.model.getchargingprofiles.GetChargingProfilesResp
import fr.simatix.cs.simulator.core20.model.getlocallistversion.GetLocalListVersionReq
import fr.simatix.cs.simulator.core20.model.getlocallistversion.GetLocalListVersionResp
import fr.simatix.cs.simulator.core20.model.getcompositeschedule.GetCompositeScheduleReq
import fr.simatix.cs.simulator.core20.model.getcompositeschedule.GetCompositeScheduleResp
import fr.simatix.cs.simulator.core20.model.getinstalledcertificateids.GetInstalledCertificateIdsReq
import fr.simatix.cs.simulator.core20.model.getinstalledcertificateids.GetInstalledCertificateIdsResp
import fr.simatix.cs.simulator.core20.model.getlog.GetLogReq
import fr.simatix.cs.simulator.core20.model.getlog.GetLogResp
import fr.simatix.cs.simulator.core20.model.getreport.GetReportReq
import fr.simatix.cs.simulator.core20.model.getreport.GetReportResp
import fr.simatix.cs.simulator.core20.model.getvariables.GetVariablesReq
import fr.simatix.cs.simulator.core20.model.getvariables.GetVariablesResp
import fr.simatix.cs.simulator.core20.model.installcertificate.InstallCertificateReq
import fr.simatix.cs.simulator.core20.model.installcertificate.InstallCertificateResp
import fr.simatix.cs.simulator.core20.model.remotestart.RequestStartTransactionReq
import fr.simatix.cs.simulator.core20.model.remotestart.RequestStartTransactionResp
import fr.simatix.cs.simulator.core20.model.remotestop.RequestStopTransactionReq
import fr.simatix.cs.simulator.core20.model.remotestop.RequestStopTransactionResp
import fr.simatix.cs.simulator.core20.model.reservenow.ReserveNowReq
import fr.simatix.cs.simulator.core20.model.reservenow.ReserveNowResp
import fr.simatix.cs.simulator.core20.model.reset.ResetReq
import fr.simatix.cs.simulator.core20.model.reset.ResetResp
import fr.simatix.cs.simulator.core20.model.setchargingprofile.SetChargingProfileReq
import fr.simatix.cs.simulator.core20.model.setchargingprofile.SetChargingProfileResp
import fr.simatix.cs.simulator.core20.model.sendlocallist.SendLocalListReq
import fr.simatix.cs.simulator.core20.model.sendlocallist.SendLocalListResp
import fr.simatix.cs.simulator.core20.model.setvariablemonitoring.SetVariableMonitoringReq
import fr.simatix.cs.simulator.core20.model.setvariablemonitoring.SetVariableMonitoringResp
import fr.simatix.cs.simulator.core20.model.setmonitoringlevel.SetMonitoringLevelReq
import fr.simatix.cs.simulator.core20.model.setmonitoringlevel.SetMonitoringLevelResp
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
import fr.simatix.cs.simulator.operation.information.OperationExecution
import fr.simatix.cs.simulator.operation.information.RequestMetadata

interface CSMSOperations {

    fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp>

    fun changeAvailability(meta: RequestMetadata, req: ChangeAvailabilityReq): OperationExecution<ChangeAvailabilityReq, ChangeAvailabilityResp>

    fun setVariables(meta: RequestMetadata,  req: SetVariablesReq): OperationExecution<SetVariablesReq, SetVariablesResp>

    fun clearCache(meta: RequestMetadata,  req: ClearCacheReq): OperationExecution<ClearCacheReq, ClearCacheResp>

    fun requestStartTransaction(meta: RequestMetadata,  req: RequestStartTransactionReq): OperationExecution<RequestStartTransactionReq, RequestStartTransactionResp>

    fun requestStopTransaction(meta: RequestMetadata,  req: RequestStopTransactionReq): OperationExecution<RequestStopTransactionReq, RequestStopTransactionResp>

    fun unlockConnector(meta: RequestMetadata,  req: UnlockConnectorReq): OperationExecution<UnlockConnectorReq, UnlockConnectorResp>

    fun getReport(meta: RequestMetadata,  req: GetReportReq): OperationExecution<GetReportReq, GetReportResp>

    fun getBaseReport(meta: RequestMetadata,  req: GetBaseReportReq): OperationExecution<GetBaseReportReq, GetBaseReportResp>

    fun getVariables(meta: RequestMetadata, req : GetVariablesReq): OperationExecution<GetVariablesReq, GetVariablesResp>

    fun cancelReservation(meta: RequestMetadata, req: CancelReservationReq): OperationExecution<CancelReservationReq, CancelReservationResp>

    fun clearChargingProfile(meta: RequestMetadata, req: ClearChargingProfileReq): OperationExecution<ClearChargingProfileReq, ClearChargingProfileResp>

    fun getCompositeSchedule(meta: RequestMetadata, req: GetCompositeScheduleReq): OperationExecution<GetCompositeScheduleReq, GetCompositeScheduleResp>

    fun getLocalListVersion(meta: RequestMetadata, req: GetLocalListVersionReq): OperationExecution<GetLocalListVersionReq, GetLocalListVersionResp>

    fun updateFirmware(meta: RequestMetadata, req : UpdateFirmwareReq): OperationExecution<UpdateFirmwareReq, UpdateFirmwareResp>

    fun sendLocalList(meta: RequestMetadata, req: SendLocalListReq): OperationExecution<SendLocalListReq, SendLocalListResp>

    fun triggerMessage(meta: RequestMetadata, req: TriggerMessageReq): OperationExecution<TriggerMessageReq, TriggerMessageResp>

    fun setChargingProfile(meta: RequestMetadata, req: SetChargingProfileReq): OperationExecution<SetChargingProfileReq, SetChargingProfileResp>

    fun reserveNow(meta: RequestMetadata, req : ReserveNowReq): OperationExecution<ReserveNowReq, ReserveNowResp>

    fun dataTransfer(meta: RequestMetadata, req : DataTransferReq): OperationExecution<DataTransferReq, DataTransferResp>

    fun certificateSigned(meta: RequestMetadata, req: CertificateSignedReq): OperationExecution<CertificateSignedReq, CertificateSignedResp>

    fun getLog(meta: RequestMetadata, req: GetLogReq): OperationExecution<GetLogReq, GetLogResp>

    fun clearDisplayMessage(meta: RequestMetadata, req: ClearDisplayMessageReq): OperationExecution<ClearDisplayMessageReq, ClearDisplayMessageResp>

    fun getChargingProfiles(meta: RequestMetadata, req: GetChargingProfilesReq): OperationExecution<GetChargingProfilesReq, GetChargingProfilesResp>

    fun getInstalledCertificateIds(meta: RequestMetadata, req: GetInstalledCertificateIdsReq): OperationExecution<GetInstalledCertificateIdsReq, GetInstalledCertificateIdsResp>

    fun installCertificate(meta: RequestMetadata, req: InstallCertificateReq): OperationExecution<InstallCertificateReq, InstallCertificateResp>

    fun customerInformation(meta: RequestMetadata, req: CustomerInformationReq): OperationExecution<CustomerInformationReq, CustomerInformationResp>


    fun unpublishFirmware(meta: RequestMetadata, req: UnpublishFirmwareReq): OperationExecution<UnpublishFirmwareReq, UnpublishFirmwareResp>


    fun setVariableMonitoring(meta: RequestMetadata, req: SetVariableMonitoringReq): OperationExecution<SetVariableMonitoringReq, SetVariableMonitoringResp>


    fun setMonitoringLevel(meta: RequestMetadata, req: SetMonitoringLevelReq): OperationExecution<SetMonitoringLevelReq, SetMonitoringLevelResp>

}