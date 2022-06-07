package com.izivia.ocpp.api

import com.izivia.ocpp.api.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.api.model.cancelreservation.CancelReservationResp
import com.izivia.ocpp.api.model.certificateSigned.CertificateSignedReq
import com.izivia.ocpp.api.model.certificateSigned.CertificateSignedResp
import com.izivia.ocpp.api.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.api.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.api.model.clearcache.ClearCacheReq
import com.izivia.ocpp.api.model.clearcache.ClearCacheResp
import com.izivia.ocpp.api.model.clearchargingprofile.ClearChargingProfileReq
import com.izivia.ocpp.api.model.clearchargingprofile.ClearChargingProfileResp
import com.izivia.ocpp.api.model.cleardisplaymessage.ClearDisplayMessageReq
import com.izivia.ocpp.api.model.cleardisplaymessage.ClearDisplayMessageResp
import com.izivia.ocpp.api.model.clearvariablemonitoring.ClearVariableMonitoringReq
import com.izivia.ocpp.api.model.clearvariablemonitoring.ClearVariableMonitoringResp
import com.izivia.ocpp.api.model.costupdated.CostUpdatedReq
import com.izivia.ocpp.api.model.costupdated.CostUpdatedResp
import com.izivia.ocpp.api.model.customerinformation.CustomerInformationReq
import com.izivia.ocpp.api.model.customerinformation.CustomerInformationResp
import com.izivia.ocpp.api.model.datatransfer.DataTransferReq
import com.izivia.ocpp.api.model.datatransfer.DataTransferResp
import com.izivia.ocpp.api.model.deletecertificate.DeleteCertificateReq
import com.izivia.ocpp.api.model.deletecertificate.DeleteCertificateResp
import com.izivia.ocpp.api.model.getallvariables.GetAllVariablesReq
import com.izivia.ocpp.api.model.getallvariables.GetAllVariablesResp
import com.izivia.ocpp.api.model.getbasereport.GetBaseReportReq
import com.izivia.ocpp.api.model.getbasereport.GetBaseReportResp
import com.izivia.ocpp.api.model.getchargingprofiles.GetChargingProfilesReq
import com.izivia.ocpp.api.model.getchargingprofiles.GetChargingProfilesResp
import com.izivia.ocpp.api.model.getcompositeschedule.GetCompositeScheduleReq
import com.izivia.ocpp.api.model.getcompositeschedule.GetCompositeScheduleResp
import com.izivia.ocpp.api.model.getdisplaymessages.GetDisplayMessagesReq
import com.izivia.ocpp.api.model.getdisplaymessages.GetDisplayMessagesResp
import com.izivia.ocpp.api.model.getinstalledcertificateids.GetInstalledCertificateIdsReq
import com.izivia.ocpp.api.model.getinstalledcertificateids.GetInstalledCertificateIdsResp
import com.izivia.ocpp.api.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.api.model.getlocallistversion.GetLocalListVersionResp
import com.izivia.ocpp.api.model.getlog.GetLogReq
import com.izivia.ocpp.api.model.getlog.GetLogResp
import com.izivia.ocpp.api.model.getmonitoringreport.GetMonitoringReportReq
import com.izivia.ocpp.api.model.getmonitoringreport.GetMonitoringReportResp
import com.izivia.ocpp.api.model.getreport.GetReportReq
import com.izivia.ocpp.api.model.getreport.GetReportResp
import com.izivia.ocpp.api.model.gettransactionstatus.GetTransactionStatusReq
import com.izivia.ocpp.api.model.gettransactionstatus.GetTransactionStatusResp
import com.izivia.ocpp.api.model.getvariables.GetVariablesReq
import com.izivia.ocpp.api.model.getvariables.GetVariablesResp
import com.izivia.ocpp.api.model.installcertificate.InstallCertificateReq
import com.izivia.ocpp.api.model.installcertificate.InstallCertificateResp
import com.izivia.ocpp.api.model.publishfirmware.PublishFirmwareReq
import com.izivia.ocpp.api.model.publishfirmware.PublishFirmwareResp
import com.izivia.ocpp.api.model.remotestart.RequestStartTransactionReq
import com.izivia.ocpp.api.model.remotestart.RequestStartTransactionResp
import com.izivia.ocpp.api.model.remotestop.RequestStopTransactionReq
import com.izivia.ocpp.api.model.remotestop.RequestStopTransactionResp
import com.izivia.ocpp.api.model.reservenow.ReserveNowReq
import com.izivia.ocpp.api.model.reservenow.ReserveNowResp
import com.izivia.ocpp.api.model.reset.ResetReq
import com.izivia.ocpp.api.model.reset.ResetResp
import com.izivia.ocpp.api.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.api.model.sendlocallist.SendLocalListResp
import com.izivia.ocpp.api.model.setchargingprofile.SetChargingProfileReq
import com.izivia.ocpp.api.model.setchargingprofile.SetChargingProfileResp
import com.izivia.ocpp.api.model.setdisplaymessage.SetDisplayMessageReq
import com.izivia.ocpp.api.model.setdisplaymessage.SetDisplayMessageResp
import com.izivia.ocpp.api.model.setmonitoringbase.SetMonitoringBaseReq
import com.izivia.ocpp.api.model.setmonitoringbase.SetMonitoringBaseResp
import com.izivia.ocpp.api.model.setmonitoringlevel.SetMonitoringLevelReq
import com.izivia.ocpp.api.model.setmonitoringlevel.SetMonitoringLevelResp
import com.izivia.ocpp.api.model.setnetworkprofile.SetNetworkProfileReq
import com.izivia.ocpp.api.model.setnetworkprofile.SetNetworkProfileResp
import com.izivia.ocpp.api.model.setvariablemonitoring.SetVariableMonitoringReq
import com.izivia.ocpp.api.model.setvariablemonitoring.SetVariableMonitoringResp
import com.izivia.ocpp.api.model.setvariables.SetVariablesReq
import com.izivia.ocpp.api.model.setvariables.SetVariablesResp
import com.izivia.ocpp.api.model.triggermessage.TriggerMessageReq
import com.izivia.ocpp.api.model.triggermessage.TriggerMessageResp
import com.izivia.ocpp.api.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.api.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.api.model.unpublishfirmware.UnpublishFirmwareReq
import com.izivia.ocpp.api.model.unpublishfirmware.UnpublishFirmwareResp
import com.izivia.ocpp.api.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.api.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata

interface CSApi {

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

    fun getAllVariables(meta: RequestMetadata, req: GetAllVariablesReq): OperationExecution<GetAllVariablesReq, GetAllVariablesResp>

    fun cancelReservation(meta: RequestMetadata, req: CancelReservationReq): OperationExecution<CancelReservationReq, CancelReservationResp>

    fun clearChargingProfile(meta: RequestMetadata, req: ClearChargingProfileReq): OperationExecution<ClearChargingProfileReq, ClearChargingProfileResp>

    fun getCompositeSchedule(meta: RequestMetadata, req: GetCompositeScheduleReq): OperationExecution<GetCompositeScheduleReq, GetCompositeScheduleResp>

    fun getLocalListVersion(meta: RequestMetadata, req: GetLocalListVersionReq): OperationExecution<GetLocalListVersionReq, GetLocalListVersionResp>

    fun updateFirmware(meta: RequestMetadata, req: UpdateFirmwareReq): OperationExecution<UpdateFirmwareReq, UpdateFirmwareResp>

    fun sendLocalList(meta: RequestMetadata, req: SendLocalListReq): OperationExecution<SendLocalListReq, SendLocalListResp>

    fun triggerMessage(meta: RequestMetadata, req: TriggerMessageReq): OperationExecution<TriggerMessageReq, TriggerMessageResp>

    fun setChargingProfile(meta: RequestMetadata, req: SetChargingProfileReq): OperationExecution<SetChargingProfileReq, SetChargingProfileResp>

    fun reserveNow(meta: RequestMetadata, req: ReserveNowReq): OperationExecution<ReserveNowReq, ReserveNowResp>

    fun dataTransfer(meta: RequestMetadata, req: DataTransferReq): OperationExecution<DataTransferReq, DataTransferResp>

    fun certificateSigned(meta: RequestMetadata, req: CertificateSignedReq): OperationExecution<CertificateSignedReq, CertificateSignedResp>

    fun getLog(meta: RequestMetadata, req: GetLogReq): OperationExecution<GetLogReq, GetLogResp>

    fun clearDisplayMessage(meta: RequestMetadata, req: ClearDisplayMessageReq): OperationExecution<ClearDisplayMessageReq, ClearDisplayMessageResp>

    fun getChargingProfiles(meta: RequestMetadata, req: GetChargingProfilesReq): OperationExecution<GetChargingProfilesReq, GetChargingProfilesResp>

    fun getInstalledCertificateIds(meta: RequestMetadata, req: GetInstalledCertificateIdsReq): OperationExecution<GetInstalledCertificateIdsReq, GetInstalledCertificateIdsResp>

    fun installCertificate(meta: RequestMetadata, req: InstallCertificateReq): OperationExecution<InstallCertificateReq, InstallCertificateResp>

    fun customerInformation(meta: RequestMetadata, req: CustomerInformationReq): OperationExecution<CustomerInformationReq, CustomerInformationResp>


    fun unpublishFirmware(meta: RequestMetadata, req: UnpublishFirmwareReq): OperationExecution<UnpublishFirmwareReq, UnpublishFirmwareResp>


    fun publishFirmware(meta: RequestMetadata, req: PublishFirmwareReq): OperationExecution<PublishFirmwareReq, PublishFirmwareResp>

    fun setVariableMonitoring(meta: RequestMetadata, req: SetVariableMonitoringReq): OperationExecution<SetVariableMonitoringReq, SetVariableMonitoringResp>


    fun setMonitoringLevel(meta: RequestMetadata, req: SetMonitoringLevelReq): OperationExecution<SetMonitoringLevelReq, SetMonitoringLevelResp>


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