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


interface OcppCSCallbacks {
    fun reset(req: ResetReq): ResetResp = throw NotImplementedError()

    fun changeAvailability(req: ChangeAvailabilityReq): ChangeAvailabilityResp = throw NotImplementedError()

    fun setVariables(req: SetVariablesReq): SetVariablesResp = throw NotImplementedError()

    fun clearCache(req: ClearCacheReq):ClearCacheResp = throw NotImplementedError()

    fun requestStartTransaction(req: RequestStartTransactionReq): RequestStartTransactionResp = throw NotImplementedError()

    fun requestStopTransaction(req: RequestStopTransactionReq): RequestStopTransactionResp = throw NotImplementedError()

    fun unlockConnector(req: UnlockConnectorReq): UnlockConnectorResp = throw NotImplementedError()

    fun getReport(req: GetReportReq): GetReportResp = throw NotImplementedError()

    fun getBaseReport(req: GetBaseReportReq): GetBaseReportResp = throw NotImplementedError()

    fun getVariables(req : GetVariablesReq): GetVariablesResp = throw NotImplementedError()

    fun cancelReservation(req: CancelReservationReq): CancelReservationResp = throw NotImplementedError()

    fun clearChargingProfile(req: ClearChargingProfileReq): ClearChargingProfileResp = throw NotImplementedError()

    fun getCompositeSchedule(req: GetCompositeScheduleReq): GetCompositeScheduleResp = throw NotImplementedError()

    fun getLocalListVersion(req: GetLocalListVersionReq): GetLocalListVersionResp = throw NotImplementedError()

    fun updateFirmware(req : UpdateFirmwareReq): UpdateFirmwareResp = throw NotImplementedError()

    fun sendLocalList(req: SendLocalListReq): SendLocalListResp = throw NotImplementedError()

    fun triggerMessage(req: TriggerMessageReq): TriggerMessageResp = throw NotImplementedError()

    fun setChargingProfile(req: SetChargingProfileReq):SetChargingProfileResp = throw NotImplementedError()

    fun reserveNow(req : ReserveNowReq): ReserveNowResp = throw NotImplementedError()

    fun dataTransfer(req : DataTransferReq): DataTransferResp = throw NotImplementedError()

    fun certificateSigned(req: CertificateSignedReq): CertificateSignedResp = throw NotImplementedError()

    fun getLog(req: GetLogReq): GetLogResp = throw NotImplementedError()

    fun clearDisplayMessage(req: ClearDisplayMessageReq): ClearDisplayMessageResp = throw NotImplementedError()

    fun getChargingProfiles(req: GetChargingProfilesReq): GetChargingProfilesResp = throw NotImplementedError()

    fun getInstalledCertificateIds(req: GetInstalledCertificateIdsReq): GetInstalledCertificateIdsResp = throw NotImplementedError()

    fun installCertificate(req: InstallCertificateReq): InstallCertificateResp = throw NotImplementedError()

    fun customerInformation(req: CustomerInformationReq):CustomerInformationResp = throw NotImplementedError()

    fun unpublishFirmware(req: UnpublishFirmwareReq): UnpublishFirmwareResp = throw NotImplementedError()

    fun setVariableMonitoring(req: SetVariableMonitoringReq): SetVariableMonitoringResp = throw NotImplementedError()

    fun setMonitoringLevel(req: SetMonitoringLevelReq): SetMonitoringLevelResp = throw NotImplementedError()

    fun publishFirmware(req: PublishFirmwareReq): PublishFirmwareResp = throw NotImplementedError()

    fun setNetworkProfile(req: SetNetworkProfileReq): SetNetworkProfileResp = throw NotImplementedError()

    fun getTransactionStatus(req: GetTransactionStatusReq): GetTransactionStatusResp = throw NotImplementedError()

    fun setMonitoringBase(req: SetMonitoringBaseReq): SetMonitoringBaseResp = throw NotImplementedError()

    fun getDisplayMessages(req: GetDisplayMessagesReq): GetDisplayMessagesResp = throw NotImplementedError()

    fun costUpdated(req: CostUpdatedReq): CostUpdatedResp = throw NotImplementedError()

    fun setDisplayMessage(req: SetDisplayMessageReq):SetDisplayMessageResp = throw NotImplementedError()

    fun deleteCertificate(req: DeleteCertificateReq): DeleteCertificateResp = throw NotImplementedError()

    fun getMonitoringReport(req: GetMonitoringReportReq): GetMonitoringReportResp = throw NotImplementedError()

    fun clearVariableMonitoring(req: ClearVariableMonitoringReq): ClearVariableMonitoringResp = throw NotImplementedError()

}