package com.izivia.ocpp.api20

import com.izivia.ocpp.core20.CSMSOperations
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
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
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