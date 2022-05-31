package fr.simatix.cs.simulator.api20

import fr.simatix.cs.simulator.core20.CSMSOperations
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
import fr.simatix.cs.simulator.operation.information.ExecutionMetadata
import fr.simatix.cs.simulator.operation.information.OperationExecution
import fr.simatix.cs.simulator.operation.information.RequestMetadata
import fr.simatix.cs.simulator.operation.information.RequestStatus
import kotlinx.datetime.Clock.System.now

open class DefaultCSMSOperations(val ocppCSCallbacks : IOcppCSCallbacks): CSMSOperations {
    override fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.reset(req)
        )

    override fun changeAvailability(
        meta: RequestMetadata,
        req: ChangeAvailabilityReq
    ): OperationExecution<ChangeAvailabilityReq, ChangeAvailabilityResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.changeAvailability(req)
        )

    override fun setVariables(
        meta: RequestMetadata,
        req: SetVariablesReq
    ): OperationExecution<SetVariablesReq, SetVariablesResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.setVariables(req)
        )

    override fun clearCache(
        meta: RequestMetadata,
        req: ClearCacheReq
    ): OperationExecution<ClearCacheReq, ClearCacheResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.clearCache(req)
        )

    override fun requestStartTransaction(
        meta: RequestMetadata,
        req: RequestStartTransactionReq
    ): OperationExecution<RequestStartTransactionReq, RequestStartTransactionResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.requestStartTransaction(req)
        )

    override fun requestStopTransaction(
        meta: RequestMetadata,
        req: RequestStopTransactionReq
    ): OperationExecution<RequestStopTransactionReq, RequestStopTransactionResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.requestStopTransaction(req)
        )

    override fun unlockConnector(
        meta: RequestMetadata,
        req: UnlockConnectorReq
    ): OperationExecution<UnlockConnectorReq, UnlockConnectorResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.unlockConnector(req)
        )

    override fun getReport(meta: RequestMetadata, req: GetReportReq): OperationExecution<GetReportReq, GetReportResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.getReport(req)
        )

    override fun getBaseReport(
        meta: RequestMetadata,
        req: GetBaseReportReq
    ): OperationExecution<GetBaseReportReq, GetBaseReportResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.getBaseReport(req)
        )

    override fun getVariables(
        meta: RequestMetadata,
        req: GetVariablesReq
    ): OperationExecution<GetVariablesReq, GetVariablesResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.getVariables(req)
        )

    override fun cancelReservation(
        meta: RequestMetadata,
        req: CancelReservationReq
    ): OperationExecution<CancelReservationReq, CancelReservationResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.cancelReservation(req)
        )

    override fun clearChargingProfile(
        meta: RequestMetadata,
        req: ClearChargingProfileReq
    ): OperationExecution<ClearChargingProfileReq, ClearChargingProfileResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.clearChargingProfile(req)
        )

    override fun getCompositeSchedule(
        meta: RequestMetadata,
        req: GetCompositeScheduleReq
    ): OperationExecution<GetCompositeScheduleReq, GetCompositeScheduleResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.getCompositeSchedule(req)
        )

    override fun getLocalListVersion(
        meta: RequestMetadata,
        req: GetLocalListVersionReq
    ): OperationExecution<GetLocalListVersionReq, GetLocalListVersionResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.getLocalListVersion(req)
        )

    override fun updateFirmware(
        meta: RequestMetadata,
        req: UpdateFirmwareReq
    ): OperationExecution<UpdateFirmwareReq, UpdateFirmwareResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.updateFirmware(req)
        )

    override fun sendLocalList(
        meta: RequestMetadata,
        req: SendLocalListReq
    ): OperationExecution<SendLocalListReq, SendLocalListResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.sendLocalList(req)
        )

    override fun triggerMessage(
        meta: RequestMetadata,
        req: TriggerMessageReq
    ): OperationExecution<TriggerMessageReq, TriggerMessageResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.triggerMessage(req)
        )

    override fun setChargingProfile(
        meta: RequestMetadata,
        req: SetChargingProfileReq
    ): OperationExecution<SetChargingProfileReq, SetChargingProfileResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.setChargingProfile(req)
        )

    override fun reserveNow(
        meta: RequestMetadata,
        req: ReserveNowReq
    ): OperationExecution<ReserveNowReq, ReserveNowResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.reserveNow(req)
        )

    override fun dataTransfer(
        meta: RequestMetadata,
        req: DataTransferReq
    ): OperationExecution<DataTransferReq, DataTransferResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.dataTransfer(req)
        )

    override fun certificateSigned(
        meta: RequestMetadata,
        req: CertificateSignedReq
    ): OperationExecution<CertificateSignedReq, CertificateSignedResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.certificateSigned(req)
        )

    override fun getLog(meta: RequestMetadata, req: GetLogReq): OperationExecution<GetLogReq, GetLogResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.getLog(req)
        )

    override fun clearDisplayMessage(
        meta: RequestMetadata,
        req: ClearDisplayMessageReq
    ): OperationExecution<ClearDisplayMessageReq, ClearDisplayMessageResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.clearDisplayMessage(req)
        )

    override fun getChargingProfiles(
        meta: RequestMetadata,
        req: GetChargingProfilesReq
    ): OperationExecution<GetChargingProfilesReq, GetChargingProfilesResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.getChargingProfiles(req)
        )

    override fun getInstalledCertificateIds(
        meta: RequestMetadata,
        req: GetInstalledCertificateIdsReq
    ): OperationExecution<GetInstalledCertificateIdsReq, GetInstalledCertificateIdsResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.getInstalledCertificateIds(req)
        )

    override fun installCertificate(
        meta: RequestMetadata,
        req: InstallCertificateReq
    ): OperationExecution<InstallCertificateReq, InstallCertificateResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.installCertificate(req)
        )

    override fun customerInformation(
        meta: RequestMetadata,
        req: CustomerInformationReq
    ): OperationExecution<CustomerInformationReq, CustomerInformationResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.customerInformation(req)
        )

    override fun unpublishFirmware(
        meta: RequestMetadata,
        req: UnpublishFirmwareReq
    ): OperationExecution<UnpublishFirmwareReq, UnpublishFirmwareResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.unpublishFirmware(req)
        )

    override fun setVariableMonitoring(
        meta: RequestMetadata,
        req: SetVariableMonitoringReq
    ): OperationExecution<SetVariableMonitoringReq, SetVariableMonitoringResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.setVariableMonitoring(req)
        )

    override fun setMonitoringLevel(
        meta: RequestMetadata,
        req: SetMonitoringLevelReq
    ): OperationExecution<SetMonitoringLevelReq, SetMonitoringLevelResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.setMonitoringLevel(req)
        )

    override fun publishFirmware(
        meta: RequestMetadata,
        req: PublishFirmwareReq
    ): OperationExecution<PublishFirmwareReq, PublishFirmwareResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.publishFirmware(req)
        )

    override fun setNetworkProfile(
        meta: RequestMetadata,
        req: SetNetworkProfileReq
    ): OperationExecution<SetNetworkProfileReq, SetNetworkProfileResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.setNetworkProfile(req)
        )

    override fun getTransactionStatus(
        meta: RequestMetadata,
        req: GetTransactionStatusReq
    ): OperationExecution<GetTransactionStatusReq, GetTransactionStatusResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.getTransactionStatus(req)
        )

    override fun setMonitoringBase(
        meta: RequestMetadata,
        req: SetMonitoringBaseReq
    ): OperationExecution<SetMonitoringBaseReq, SetMonitoringBaseResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.setMonitoringBase(req)
        )

    override fun getDisplayMessages(
        meta: RequestMetadata,
        req: GetDisplayMessagesReq
    ): OperationExecution<GetDisplayMessagesReq, GetDisplayMessagesResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.getDisplayMessages(req)
        )

    override fun costUpdated(
        meta: RequestMetadata,
        req: CostUpdatedReq
    ): OperationExecution<CostUpdatedReq, CostUpdatedResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.costUpdated(req)
        )

    override fun setDisplayMessage(
        meta: RequestMetadata,
        req: SetDisplayMessageReq
    ): OperationExecution<SetDisplayMessageReq, SetDisplayMessageResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.setDisplayMessage(req)
        )

    override fun deleteCertificate(
        meta: RequestMetadata,
        req: DeleteCertificateReq
    ): OperationExecution<DeleteCertificateReq, DeleteCertificateResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.deleteCertificate(req)
        )

    override fun getMonitoringReport(
        meta: RequestMetadata,
        req: GetMonitoringReportReq
    ): OperationExecution<GetMonitoringReportReq, GetMonitoringReportResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.getMonitoringReport(req)
        )

    override fun clearVariableMonitoring(
        meta: RequestMetadata,
        req: ClearVariableMonitoringReq
    ): OperationExecution<ClearVariableMonitoringReq, ClearVariableMonitoringResp> =
        OperationExecution(
            ExecutionMetadata(meta,RequestStatus.SUCCESS,now()),
            req,
            ocppCSCallbacks.clearVariableMonitoring(req)
        )
}