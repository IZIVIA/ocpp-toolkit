package com.izivia.ocpp.core20.impl

import com.izivia.ocpp.core20.CSMSOperations
import com.izivia.ocpp.core20.ChargePointOperations
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
import com.izivia.ocpp.core20.model.authorize.AuthorizeReq
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
import com.izivia.ocpp.core20.model.heartbeat.HeartbeatReq
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
import com.izivia.ocpp.core20.model.metervalues.MeterValuesReq
import com.izivia.ocpp.operation.information.ActionOcpp
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.transport.ServerTransport
import com.izivia.ocpp.transport.receiveMessage
import com.izivia.ocpp.transport.sendMessage
import kotlinx.datetime.Clock

typealias OcppId = String
typealias Action = String


class RealCSMSOperations(
    val server: ServerTransport,
    private val chargePointOperations: ChargePointOperations,
    acceptConnection: (OcppId) -> Boolean
) : CSMSOperations {


    init {

        server.receiveMessage("Heartbeat", OcppVersion.OCPP_2_0, { meta: RequestMetadata, req: HeartbeatReq ->
        chargePointOperations.heartbeat(meta, req).response }, acceptConnection)

        server.receiveMessage("Authorize", OcppVersion.OCPP_2_0, { meta: RequestMetadata, req: AuthorizeReq ->
            chargePointOperations.authorize(meta, req).response
        }, acceptConnection)

        server.receiveMessage("MeterValues", OcppVersion.OCPP_2_0, { meta: RequestMetadata, req: MeterValuesReq ->
            chargePointOperations.meterValues(meta, req).response
        }, acceptConnection)

        server.receiveMessage("StartTransaction", OcppVersion.OCPP_2_0, { meta: RequestMetadata, req: StartTransactionReq ->
            chargePointOperations.startTransaction(meta, req).response
        }, acceptConnection)

        server.receiveMessage("StopTransaction", OcppVersion.OCPP_2_0, { meta: RequestMetadata, req: StopTransactionReq ->
            chargePointOperations.stopTransaction(meta, req).response
        }, acceptConnection)

        server.receiveMessage("StatusNotification", OcppVersion.OCPP_2_0, { meta: RequestMetadata, req: StatusNotificationReq ->
            chargePointOperations.statusNotification(meta, req).response
        }, acceptConnection)

        server.receiveMessage("DataTransfer", OcppVersion.OCPP_2_0, { meta: RequestMetadata, req: DataTransferReq ->
            chargePointOperations.dataTransfer(meta, req).response
        }, acceptConnection)

        server.receiveMessage("BootNotification", OcppVersion.OCPP_2_0, { meta: RequestMetadata, req: BootNotificationReq ->
            chargePointOperations.bootNotification(meta, req).response
        }, acceptConnection)

        server.receiveMessage("FirmwareStatusNotification", OcppVersion.OCPP_2_0, { meta: RequestMetadata, req: FirmwareStatusNotificationReq ->
            chargePointOperations.firmwareStatusNotification(meta, req).response
        }, acceptConnection)

        server.receiveMessage("DiagnosticsStatusNotification", OcppVersion.OCPP_2_0, { meta: RequestMetadata, req: DiagnosticsStatusNotificationReq ->
            chargePointOperations.diagnosticsStatusNotification(meta, req).response
        }, acceptConnection)




    }

    private inline fun <T, reified P: Any> sendMessage(meta: RequestMetadata, req: T, action: String): OperationExecution<T, P>{
        val startTime = Clock.System.now()
        val resp = server.sendMessage<T,P>(csOcppId = meta.chargingStationId, action = action, message = req)
        val stopTime = Clock.System.now()
        return OperationExecution(ExecutionMetadata(
            reqMeta = meta,
            status = RequestStatus.SUCCESS,
            requestTime = startTime,
            responseTime = stopTime
        ), req, resp)
    }

    private fun <T> getActionFromReq(req: T): Action =
         when (req){
            is ResetReq -> ActionOcpp.RESET.value
            is ChangeAvailabilityReq -> ActionOcpp.CHANGE_AVAILABILITY.value
            is SetVariablesReq -> ActionOcpp.SET_VARIABLES.value
             is ClearCacheReq -> "ClearCache"
             else -> throw IllegalArgumentException("Unknown action")

        }

    override fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp> =
        sendMessage(meta = meta, req = req, getActionFromReq(req))

    override fun changeAvailability(meta: RequestMetadata, req: ChangeAvailabilityReq):
            OperationExecution<ChangeAvailabilityReq, ChangeAvailabilityResp>
            = sendMessage(meta = meta, req = req, action = "ChangeAvailability")

    override fun setVariables(meta: RequestMetadata,  req: SetVariablesReq):
            OperationExecution<SetVariablesReq, SetVariablesResp> =
        sendMessage(meta = meta, req = req, action = "SetVariables")

   override fun clearCache(meta: RequestMetadata,  req: ClearCacheReq): OperationExecution<ClearCacheReq, ClearCacheResp>{
       return sendMessage(meta,req,"ClearCache")
    }

    override fun requestStartTransaction(meta: RequestMetadata,  req: RequestStartTransactionReq): OperationExecution<RequestStartTransactionReq, RequestStartTransactionResp>{
        return sendMessage(meta,req,"RequestStartTransaction")
    }

    override fun requestStopTransaction(meta: RequestMetadata,  req: RequestStopTransactionReq): OperationExecution<RequestStopTransactionReq, RequestStopTransactionResp> =
        sendMessage(meta,req,"RequestStopTransaction")


    override fun unlockConnector(meta: RequestMetadata,  req: UnlockConnectorReq): OperationExecution<UnlockConnectorReq, UnlockConnectorResp> =
        sendMessage(meta = meta, req = req, action = "UnlockConnector")

    override fun getReport(meta: RequestMetadata,  req: GetReportReq): OperationExecution<GetReportReq, GetReportResp> =
        sendMessage(meta = meta, req = req, action = "GetReport")

    override fun getBaseReport(meta: RequestMetadata,  req: GetBaseReportReq): OperationExecution<GetBaseReportReq, GetBaseReportResp> =
        sendMessage(meta = meta, req = req, action = "GetBaseReport")

    override fun getVariables(meta: RequestMetadata, req : GetVariablesReq): OperationExecution<GetVariablesReq, GetVariablesResp> = sendMessage(meta,req,"GetVariables")

    override fun cancelReservation(meta: RequestMetadata, req: CancelReservationReq): OperationExecution<CancelReservationReq, CancelReservationResp> = sendMessage(meta,req,"CancelReservation")

    override fun clearChargingProfile(meta: RequestMetadata, req: ClearChargingProfileReq): OperationExecution<ClearChargingProfileReq, ClearChargingProfileResp> = sendMessage(meta,req,"GetCompositeSchedule")

    override fun getCompositeSchedule(meta: RequestMetadata, req: GetCompositeScheduleReq): OperationExecution<GetCompositeScheduleReq, GetCompositeScheduleResp> =
          sendMessage(meta = meta, req = req, action = "GetCompositeSchedule")

    override fun getLocalListVersion(meta: RequestMetadata, req: GetLocalListVersionReq): OperationExecution<GetLocalListVersionReq, GetLocalListVersionResp> =
          sendMessage(meta = meta, req = req, action = "GetLocalListVersion")

    override fun updateFirmware(meta: RequestMetadata, req : UpdateFirmwareReq): OperationExecution<UpdateFirmwareReq, UpdateFirmwareResp> =
          sendMessage(meta = meta, req = req, action = "UpdateFirmware")

    override fun sendLocalList(meta: RequestMetadata, req: SendLocalListReq): OperationExecution<SendLocalListReq, SendLocalListResp> =
          sendMessage(meta = meta, req = req, action = "SendLocalList")

    override fun triggerMessage(meta: RequestMetadata, req: TriggerMessageReq): OperationExecution<TriggerMessageReq, TriggerMessageResp> =
          sendMessage(meta = meta, req = req, action = "TriggerMessage")

    override fun setChargingProfile(meta: RequestMetadata, req: SetChargingProfileReq): OperationExecution<SetChargingProfileReq, SetChargingProfileResp> =
          sendMessage(meta = meta, req = req, action = "SetChargingProfile")

    override fun reserveNow(meta: RequestMetadata, req : ReserveNowReq): OperationExecution<ReserveNowReq, ReserveNowResp> =
          sendMessage(meta = meta, req = req, action = "ReserveNow")

    override fun dataTransfer(meta: RequestMetadata, req : DataTransferReq): OperationExecution<DataTransferReq, DataTransferResp> =
          sendMessage(meta = meta, req = req, action = "DataTransfer")

    override fun certificateSigned(meta: RequestMetadata, req: CertificateSignedReq): OperationExecution<CertificateSignedReq, CertificateSignedResp> =
          sendMessage(meta = meta, req = req, action = "CertificateSigned")

    override fun getLog(meta: RequestMetadata, req: GetLogReq): OperationExecution<GetLogReq, GetLogResp> =
          sendMessage(meta = meta, req = req, action = "GetLog")

    override fun clearDisplayMessage(meta: RequestMetadata, req: ClearDisplayMessageReq): OperationExecution<ClearDisplayMessageReq, ClearDisplayMessageResp> =
          sendMessage(meta = meta, req = req, action = "ClearDisplayMessage")

    override fun getChargingProfiles(meta: RequestMetadata, req: GetChargingProfilesReq): OperationExecution<GetChargingProfilesReq, GetChargingProfilesResp> =
          sendMessage(meta = meta, req = req, action = "GetChargingProfiles")

    override fun getInstalledCertificateIds(meta: RequestMetadata, req: GetInstalledCertificateIdsReq): OperationExecution<GetInstalledCertificateIdsReq, GetInstalledCertificateIdsResp> =
          sendMessage(meta = meta, req = req, action = "GetInstalledCertificateIds")

    override fun installCertificate(meta: RequestMetadata, req: InstallCertificateReq): OperationExecution<InstallCertificateReq, InstallCertificateResp> =
          sendMessage(meta = meta, req = req, action = "InstallCertificate")

    override fun customerInformation(meta: RequestMetadata, req: CustomerInformationReq): OperationExecution<CustomerInformationReq, CustomerInformationResp> =
          sendMessage(meta = meta, req = req, action = "CustomerInformation")

    override fun unpublishFirmware(meta: RequestMetadata, req: UnpublishFirmwareReq): OperationExecution<UnpublishFirmwareReq, UnpublishFirmwareResp> =
          sendMessage(meta = meta, req = req, action = "UnpublishFirmware")

    override fun setVariableMonitoring(meta: RequestMetadata, req: SetVariableMonitoringReq): OperationExecution<SetVariableMonitoringReq, SetVariableMonitoringResp> =
          sendMessage(meta = meta, req = req, action = "SetVariableMonitoring")

    override fun setMonitoringLevel(meta: RequestMetadata, req: SetMonitoringLevelReq): OperationExecution<SetMonitoringLevelReq, SetMonitoringLevelResp> =
          sendMessage(meta = meta, req = req, action = "SetMonitoringLevel")

    override fun publishFirmware(meta: RequestMetadata, req: PublishFirmwareReq): OperationExecution<PublishFirmwareReq, PublishFirmwareResp> =
          sendMessage(meta = meta, req = req, action = "PublishFirmware")

    override fun setNetworkProfile(meta: RequestMetadata, req: SetNetworkProfileReq): OperationExecution<SetNetworkProfileReq, SetNetworkProfileResp> =
          sendMessage(meta = meta, req = req, action = "SetNetworkProfile")

    override fun getTransactionStatus(meta: RequestMetadata, req: GetTransactionStatusReq): OperationExecution<GetTransactionStatusReq, GetTransactionStatusResp> =
          sendMessage(meta = meta, req = req, action = "GetTransactionStatus")

    override fun setMonitoringBase(meta: RequestMetadata, req: SetMonitoringBaseReq): OperationExecution<SetMonitoringBaseReq, SetMonitoringBaseResp> =
          sendMessage(meta = meta, req = req, action = "SetMonitoringBase")

    override fun getDisplayMessages(meta: RequestMetadata, req: GetDisplayMessagesReq): OperationExecution<GetDisplayMessagesReq, GetDisplayMessagesResp> =
          sendMessage(meta = meta, req = req, action = "GetDisplayMessages")

    override fun costUpdated(meta: RequestMetadata, req: CostUpdatedReq): OperationExecution<CostUpdatedReq, CostUpdatedResp> =
          sendMessage(meta = meta, req = req, action = "CostUpdated")

    override fun setDisplayMessage(meta: RequestMetadata, req: SetDisplayMessageReq): OperationExecution<SetDisplayMessageReq, SetDisplayMessageResp> =
        sendMessage(meta = meta, req = req, action = "setDisplayMessage")

    override fun deleteCertificate(meta: RequestMetadata, req: DeleteCertificateReq): OperationExecution<DeleteCertificateReq, DeleteCertificateResp> =
        sendMessage(meta = meta, req = req, action = "deleteCertificate")

    override fun getMonitoringReport(meta: RequestMetadata, req: GetMonitoringReportReq): OperationExecution<GetMonitoringReportReq, GetMonitoringReportResp> =
        sendMessage(meta = meta, req = req, action = "getMonitoringReport")

    override fun clearVariableMonitoring(meta: RequestMetadata, req: ClearVariableMonitoringReq): OperationExecution<ClearVariableMonitoringReq, ClearVariableMonitoringResp> =
        sendMessage(meta = meta, req = req, action = "clearVariableMonitoring")


}