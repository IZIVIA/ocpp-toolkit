package com.izivia.ocpp.api20

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
import com.izivia.ocpp.core20.model.costupdated.CostUpdatedReq
import com.izivia.ocpp.core20.model.costupdated.CostUpdatedResp
import com.izivia.ocpp.core20.model.customerinformation.CustomerInformationReq
import com.izivia.ocpp.core20.model.customerinformation.CustomerInformationResp
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
import com.izivia.ocpp.core20.model.getdisplaymessages.GetDisplayMessagesReq
import com.izivia.ocpp.core20.model.getdisplaymessages.GetDisplayMessagesResp
import com.izivia.ocpp.core20.model.getinstalledcertificateids.GetInstalledCertificateIdsReq
import com.izivia.ocpp.core20.model.getinstalledcertificateids.GetInstalledCertificateIdsResp
import com.izivia.ocpp.core20.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.core20.model.getlocallistversion.GetLocalListVersionResp
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
import com.izivia.ocpp.core20.model.installcertificate.InstallCertificateReq
import com.izivia.ocpp.core20.model.installcertificate.InstallCertificateResp
import com.izivia.ocpp.core20.model.publishfirmware.PublishFirmwareReq
import com.izivia.ocpp.core20.model.publishfirmware.PublishFirmwareResp
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
import com.izivia.ocpp.core20.model.setmonitoringlevel.SetMonitoringLevelReq
import com.izivia.ocpp.core20.model.setmonitoringlevel.SetMonitoringLevelResp
import com.izivia.ocpp.core20.model.setnetworkprofile.SetNetworkProfileReq
import com.izivia.ocpp.core20.model.setnetworkprofile.SetNetworkProfileResp
import com.izivia.ocpp.core20.model.setvariablemonitoring.SetVariableMonitoringReq
import com.izivia.ocpp.core20.model.setvariablemonitoring.SetVariableMonitoringResp
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