package com.izivia.ocpp.core20

import com.izivia.ocpp.core20.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.core20.model.cancelreservation.CancelReservationResp
import com.izivia.ocpp.core20.model.certificateSigned.CertificateSignedReq
import com.izivia.ocpp.core20.model.certificateSigned.CertificateSignedResp
import com.izivia.ocpp.core20.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core20.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.core20.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core20.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core20.model.clearchargingprofile.ClearChargingProfileReq
import com.izivia.ocpp.core20.model.clearchargingprofile.ClearChargingProfileResp
import com.izivia.ocpp.core20.model.cleardisplaymessage.ClearDisplayMessageReq
import com.izivia.ocpp.core20.model.cleardisplaymessage.ClearDisplayMessageResp
import com.izivia.ocpp.core20.model.clearvariablemonitoring.ClearVariableMonitoringReq
import com.izivia.ocpp.core20.model.clearvariablemonitoring.ClearVariableMonitoringResp
import com.izivia.ocpp.core20.model.customerinformation.CustomerInformationReq
import com.izivia.ocpp.core20.model.customerinformation.CustomerInformationResp
import com.izivia.ocpp.core20.model.costupdated.CostUpdatedReq
import com.izivia.ocpp.core20.model.costupdated.CostUpdatedResp
import com.izivia.ocpp.core20.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core20.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core20.model.deletecertificate.DeleteCertificateReq
import com.izivia.ocpp.core20.model.deletecertificate.DeleteCertificateResp
import com.izivia.ocpp.core20.model.getbasereport.GetBaseReportReq
import com.izivia.ocpp.core20.model.getbasereport.GetBaseReportResp
import com.izivia.ocpp.core20.model.getchargingprofiles.GetChargingProfilesReq
import com.izivia.ocpp.core20.model.getchargingprofiles.GetChargingProfilesResp
import com.izivia.ocpp.core20.model.getcompositeschedule.GetCompositeScheduleReq
import com.izivia.ocpp.core20.model.getcompositeschedule.GetCompositeScheduleResp
import com.izivia.ocpp.core20.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.core20.model.getlocallistversion.GetLocalListVersionResp
import com.izivia.ocpp.core20.model.getdisplaymessages.GetDisplayMessagesReq
import com.izivia.ocpp.core20.model.getdisplaymessages.GetDisplayMessagesResp
import com.izivia.ocpp.core20.model.getinstalledcertificateids.GetInstalledCertificateIdsReq
import com.izivia.ocpp.core20.model.getinstalledcertificateids.GetInstalledCertificateIdsResp
import com.izivia.ocpp.core20.model.getlog.GetLogReq
import com.izivia.ocpp.core20.model.getlog.GetLogResp
import com.izivia.ocpp.core20.model.getmonitoringreport.GetMonitoringReportReq
import com.izivia.ocpp.core20.model.getmonitoringreport.GetMonitoringReportResp
import com.izivia.ocpp.core20.model.getreport.GetReportReq
import com.izivia.ocpp.core20.model.getreport.GetReportResp
import com.izivia.ocpp.core20.model.gettransactionstatus.GetTransactionStatusReq
import com.izivia.ocpp.core20.model.gettransactionstatus.GetTransactionStatusResp
import com.izivia.ocpp.core20.model.getvariables.GetVariablesReq
import com.izivia.ocpp.core20.model.getvariables.GetVariablesResp
import com.izivia.ocpp.core20.model.publishfirmware.PublishFirmwareReq
import com.izivia.ocpp.core20.model.publishfirmware.PublishFirmwareResp
import com.izivia.ocpp.core20.model.installcertificate.InstallCertificateReq
import com.izivia.ocpp.core20.model.installcertificate.InstallCertificateResp
import com.izivia.ocpp.core20.model.remotestart.RequestStartTransactionReq
import com.izivia.ocpp.core20.model.remotestart.RequestStartTransactionResp
import com.izivia.ocpp.core20.model.remotestop.RequestStopTransactionReq
import com.izivia.ocpp.core20.model.remotestop.RequestStopTransactionResp
import com.izivia.ocpp.core20.model.reservenow.ReserveNowReq
import com.izivia.ocpp.core20.model.reservenow.ReserveNowResp
import com.izivia.ocpp.core20.model.reset.ResetReq
import com.izivia.ocpp.core20.model.reset.ResetResp
import com.izivia.ocpp.core20.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.core20.model.sendlocallist.SendLocalListResp
import com.izivia.ocpp.core20.model.setchargingprofile.SetChargingProfileReq
import com.izivia.ocpp.core20.model.setchargingprofile.SetChargingProfileResp
import com.izivia.ocpp.core20.model.setdisplaymessage.SetDisplayMessageReq
import com.izivia.ocpp.core20.model.setdisplaymessage.SetDisplayMessageResp
import com.izivia.ocpp.core20.model.setmonitoringbase.SetMonitoringBaseReq
import com.izivia.ocpp.core20.model.setmonitoringbase.SetMonitoringBaseResp
import com.izivia.ocpp.core20.model.setvariablemonitoring.SetVariableMonitoringReq
import com.izivia.ocpp.core20.model.setvariablemonitoring.SetVariableMonitoringResp
import com.izivia.ocpp.core20.model.setmonitoringlevel.SetMonitoringLevelReq
import com.izivia.ocpp.core20.model.setmonitoringlevel.SetMonitoringLevelResp
import com.izivia.ocpp.core20.model.setnetworkprofile.SetNetworkProfileReq
import com.izivia.ocpp.core20.model.setnetworkprofile.SetNetworkProfileResp
import com.izivia.ocpp.core20.model.setvariables.SetVariablesReq
import com.izivia.ocpp.core20.model.setvariables.SetVariablesResp
import com.izivia.ocpp.core20.model.triggermessage.TriggerMessageReq
import com.izivia.ocpp.core20.model.triggermessage.TriggerMessageResp
import com.izivia.ocpp.core20.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core20.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core20.model.unpublishfirmware.UnpublishFirmwareReq
import com.izivia.ocpp.core20.model.unpublishfirmware.UnpublishFirmwareResp
import com.izivia.ocpp.core20.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core20.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata

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


    fun publishFirmware(meta: RequestMetadata, req: PublishFirmwareReq): OperationExecution<PublishFirmwareReq, PublishFirmwareResp>

    fun setNetworkProfile(meta: RequestMetadata, req: SetNetworkProfileReq): OperationExecution<SetNetworkProfileReq, SetNetworkProfileResp>

    fun getTransactionStatus(meta: RequestMetadata, req: GetTransactionStatusReq): OperationExecution<GetTransactionStatusReq, GetTransactionStatusResp>


    fun setMonitoringBase(meta: RequestMetadata, req: SetMonitoringBaseReq): OperationExecution<SetMonitoringBaseReq, SetMonitoringBaseResp>


    fun getDisplayMessages(meta: RequestMetadata, req: GetDisplayMessagesReq): OperationExecution<GetDisplayMessagesReq, GetDisplayMessagesResp>

    fun costUpdated(meta: RequestMetadata, req: CostUpdatedReq): OperationExecution<CostUpdatedReq, CostUpdatedResp>

    fun setDisplayMessage(meta: RequestMetadata, req: SetDisplayMessageReq): OperationExecution<SetDisplayMessageReq, SetDisplayMessageResp>


    fun deleteCertificate(meta: RequestMetadata, req: DeleteCertificateReq): OperationExecution<DeleteCertificateReq, DeleteCertificateResp>

    fun getMonitoringReport(meta: RequestMetadata, req: GetMonitoringReportReq): OperationExecution<GetMonitoringReportReq, GetMonitoringReportResp>

    fun clearVariableMonitoring(meta: RequestMetadata, req: ClearVariableMonitoringReq): OperationExecution<ClearVariableMonitoringReq, ClearVariableMonitoringResp>

}