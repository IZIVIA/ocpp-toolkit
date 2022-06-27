package com.izivia.ocpp.core20.impl

import com.izivia.ocpp.core20.CSMSOperations
import com.izivia.ocpp.core20.ChargePointOperations
import com.izivia.ocpp.core20.model.authorize.AuthorizeReq
import com.izivia.ocpp.core20.model.bootnotification.BootNotificationReq
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
import com.izivia.ocpp.core20.model.clearedcharginglimit.ClearedChargingLimitReq
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
import com.izivia.ocpp.core20.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core20.model.getbasereport.GetBaseReportReq
import com.izivia.ocpp.core20.model.getbasereport.GetBaseReportResp
import com.izivia.ocpp.core20.model.getcertificatestatus.GetCertificateStatusReq
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
import com.izivia.ocpp.core20.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core20.model.installcertificate.InstallCertificateReq
import com.izivia.ocpp.core20.model.installcertificate.InstallCertificateResp
import com.izivia.ocpp.core20.model.logstatusnotification.LogStatusNotificationReq
import com.izivia.ocpp.core20.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core20.model.notifycharginglimit.NotifyChargingLimitReq
import com.izivia.ocpp.core20.model.notifycustomerinformation.NotifyCustomerInformationReq
import com.izivia.ocpp.core20.model.notifydisplaymessages.NotifyDisplayMessagesReq
import com.izivia.ocpp.core20.model.notifyevchargingneeds.NotifyEVChargingNeedsReq
import com.izivia.ocpp.core20.model.notifyevchargingschedule.NotifyEVChargingScheduleReq
import com.izivia.ocpp.core20.model.notifyevent.NotifyEventReq
import com.izivia.ocpp.core20.model.notifymonitoringreport.NotifyMonitoringReportReq
import com.izivia.ocpp.core20.model.notifyreport.NotifyReportReq
import com.izivia.ocpp.core20.model.publishfirmware.PublishFirmwareReq
import com.izivia.ocpp.core20.model.publishfirmware.PublishFirmwareResp
import com.izivia.ocpp.core20.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationReq
import com.izivia.ocpp.core20.model.remotestart.RequestStartTransactionReq
import com.izivia.ocpp.core20.model.remotestart.RequestStartTransactionResp
import com.izivia.ocpp.core20.model.remotestop.RequestStopTransactionReq
import com.izivia.ocpp.core20.model.remotestop.RequestStopTransactionResp
import com.izivia.ocpp.core20.model.reportchargingprofiles.ReportChargingProfilesReq
import com.izivia.ocpp.core20.model.reservationstatusupdate.ReservationStatusUpdateReq
import com.izivia.ocpp.core20.model.reservenow.ReserveNowReq
import com.izivia.ocpp.core20.model.reservenow.ReserveNowResp
import com.izivia.ocpp.core20.model.reset.ResetReq
import com.izivia.ocpp.core20.model.reset.ResetResp
import com.izivia.ocpp.core20.model.securityeventnotification.SecurityEventNotificationReq
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
import com.izivia.ocpp.core20.model.signcertificate.SignCertificateReq
import com.izivia.ocpp.core20.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core20.model.transactionevent.TransactionEventReq
import com.izivia.ocpp.core20.model.triggermessage.TriggerMessageReq
import com.izivia.ocpp.core20.model.triggermessage.TriggerMessageResp
import com.izivia.ocpp.core20.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core20.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core20.model.unpublishfirmware.UnpublishFirmwareReq
import com.izivia.ocpp.core20.model.unpublishfirmware.UnpublishFirmwareResp
import com.izivia.ocpp.core20.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core20.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.operation.information.*
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
    acceptConnection: (OcppId) -> ChargingStationConfig
) : CSMSOperations {
    init {
        server.receiveMessage(
            ActionOcpp.HEARTBEAT.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: HeartbeatReq -> chargePointOperations.heartbeat(meta, req).response },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.AUTHORIZE.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: AuthorizeReq -> chargePointOperations.authorize(meta, req).response },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.METER_VALUES.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: MeterValuesReq -> chargePointOperations.meterValues(meta, req).response },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.TRANSACTION_EVENT.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: TransactionEventReq ->
                chargePointOperations.transactionEvent(
                    meta,
                    req
                ).response
            },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.STATUS_NOTIFICATION.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: StatusNotificationReq ->
                chargePointOperations.statusNotification(
                    meta,
                    req
                ).response
            },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.DATA_TRANSFER.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: DataTransferReq -> chargePointOperations.dataTransfer(meta, req).response },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.BOOT_NOTIFICATION.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: BootNotificationReq ->
                chargePointOperations.bootNotification(
                    meta,
                    req
                ).response
            },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.NOTIFY_REPORT.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: NotifyReportReq -> chargePointOperations.notifyReport(meta, req).response },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.FIRMWARE_STATUS_NOTIFICATION.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: FirmwareStatusNotificationReq ->
                chargePointOperations.firmwareStatusNotification(
                    meta,
                    req
                ).response
            },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.CLEARED_CHARGING_LIMIT.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: ClearedChargingLimitReq ->
                chargePointOperations.clearedChargingLimit(
                    meta,
                    req
                ).response
            },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.GET_CERTIFICATE_STATUS.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: GetCertificateStatusReq ->
                chargePointOperations.getCertificateStatus(
                    meta,
                    req
                ).response
            },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.NOTIFY_CUSTOMER_INFORMATION.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: NotifyCustomerInformationReq ->
                chargePointOperations.notifyCustomerInformation(
                    meta,
                    req
                ).response
            },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.NOTIFY_EVENT.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: NotifyEventReq -> chargePointOperations.notifyEvent(meta, req).response },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.NOTIFY_EV_CHARGING_SCHEDULE.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: NotifyEVChargingScheduleReq ->
                chargePointOperations.notifyEVChargingSchedule(
                    meta,
                    req
                ).response
            },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.NOTIFY_CHARGING_LIMIT.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: NotifyChargingLimitReq ->
                chargePointOperations.notifyChargingLimit(
                    meta,
                    req
                ).response
            },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.NOTIFY_DISPLAY_MESSAGES.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: NotifyDisplayMessagesReq ->
                chargePointOperations.notifyDisplayMessages(
                    meta,
                    req
                ).response
            },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.NOTIFY_EV_CHARGING_NEEDS.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: NotifyEVChargingNeedsReq ->
                chargePointOperations.notifyEVChargingNeeds(
                    meta,
                    req
                ).response
            },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.LOG_STATUS_NOTIFICATION.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: LogStatusNotificationReq ->
                chargePointOperations.logStatusNotification(
                    meta,
                    req
                ).response
            },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.PUBLISH_FIRMWARE_STATUS_NOTIFICATION.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: PublishFirmwareStatusNotificationReq ->
                chargePointOperations.publishFirmwareStatusNotification(
                    meta,
                    req
                ).response
            },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.NOTIFY_MONITORING_REPORT.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: NotifyMonitoringReportReq ->
                chargePointOperations.notifyMonitoringReport(
                    meta,
                    req
                ).response
            },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.RESERVATION_STATUS_UPDATE.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: ReservationStatusUpdateReq ->
                chargePointOperations.reservationStatusUpdate(
                    meta,
                    req
                ).response
            },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.SECURITY_EVENT_NOTIFICATION.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: SecurityEventNotificationReq ->
                chargePointOperations.securityEventNotification(
                    meta,
                    req
                ).response
            },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.SIGN_CERTIFICATE.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: SignCertificateReq ->
                chargePointOperations.signCertificate(
                    meta,
                    req
                ).response
            },
            acceptConnection
        )
        server.receiveMessage(
            ActionOcpp.REPORT_CHARGING_PROFILES.value,
            OcppVersion.OCPP_2_0,
            { meta: RequestMetadata, req: ReportChargingProfilesReq ->
                chargePointOperations.reportChargingProfiles(
                    meta,
                    req
                ).response
            },
            acceptConnection
        )
    }

    override fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun changeAvailability(
        meta: RequestMetadata,
        req: ChangeAvailabilityReq
    ): OperationExecution<ChangeAvailabilityReq, ChangeAvailabilityResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun setVariables(
        meta: RequestMetadata,
        req: SetVariablesReq
    ): OperationExecution<SetVariablesReq, SetVariablesResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun clearCache(
        meta: RequestMetadata,
        req: ClearCacheReq
    ): OperationExecution<ClearCacheReq, ClearCacheResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun requestStartTransaction(
        meta: RequestMetadata,
        req: RequestStartTransactionReq
    ): OperationExecution<RequestStartTransactionReq, RequestStartTransactionResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun requestStopTransaction(
        meta: RequestMetadata,
        req: RequestStopTransactionReq
    ): OperationExecution<RequestStopTransactionReq, RequestStopTransactionResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))


    override fun unlockConnector(
        meta: RequestMetadata,
        req: UnlockConnectorReq
    ): OperationExecution<UnlockConnectorReq, UnlockConnectorResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun getReport(meta: RequestMetadata, req: GetReportReq): OperationExecution<GetReportReq, GetReportResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun getBaseReport(
        meta: RequestMetadata,
        req: GetBaseReportReq
    ): OperationExecution<GetBaseReportReq, GetBaseReportResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun getVariables(
        meta: RequestMetadata,
        req: GetVariablesReq
    ): OperationExecution<GetVariablesReq, GetVariablesResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun cancelReservation(
        meta: RequestMetadata,
        req: CancelReservationReq
    ): OperationExecution<CancelReservationReq, CancelReservationResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun clearChargingProfile(
        meta: RequestMetadata,
        req: ClearChargingProfileReq
    ): OperationExecution<ClearChargingProfileReq, ClearChargingProfileResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun getCompositeSchedule(
        meta: RequestMetadata,
        req: GetCompositeScheduleReq
    ): OperationExecution<GetCompositeScheduleReq, GetCompositeScheduleResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun getLocalListVersion(
        meta: RequestMetadata,
        req: GetLocalListVersionReq
    ): OperationExecution<GetLocalListVersionReq, GetLocalListVersionResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun updateFirmware(
        meta: RequestMetadata,
        req: UpdateFirmwareReq
    ): OperationExecution<UpdateFirmwareReq, UpdateFirmwareResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun sendLocalList(
        meta: RequestMetadata,
        req: SendLocalListReq
    ): OperationExecution<SendLocalListReq, SendLocalListResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun triggerMessage(
        meta: RequestMetadata,
        req: TriggerMessageReq
    ): OperationExecution<TriggerMessageReq, TriggerMessageResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun setChargingProfile(
        meta: RequestMetadata,
        req: SetChargingProfileReq
    ): OperationExecution<SetChargingProfileReq, SetChargingProfileResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun reserveNow(
        meta: RequestMetadata,
        req: ReserveNowReq
    ): OperationExecution<ReserveNowReq, ReserveNowResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun dataTransfer(
        meta: RequestMetadata,
        req: DataTransferReq
    ): OperationExecution<DataTransferReq, DataTransferResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun certificateSigned(
        meta: RequestMetadata,
        req: CertificateSignedReq
    ): OperationExecution<CertificateSignedReq, CertificateSignedResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun getLog(meta: RequestMetadata, req: GetLogReq): OperationExecution<GetLogReq, GetLogResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun clearDisplayMessage(
        meta: RequestMetadata,
        req: ClearDisplayMessageReq
    ): OperationExecution<ClearDisplayMessageReq, ClearDisplayMessageResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun getChargingProfiles(
        meta: RequestMetadata,
        req: GetChargingProfilesReq
    ): OperationExecution<GetChargingProfilesReq, GetChargingProfilesResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun getInstalledCertificateIds(
        meta: RequestMetadata,
        req: GetInstalledCertificateIdsReq
    ): OperationExecution<GetInstalledCertificateIdsReq, GetInstalledCertificateIdsResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun installCertificate(
        meta: RequestMetadata,
        req: InstallCertificateReq
    ): OperationExecution<InstallCertificateReq, InstallCertificateResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun customerInformation(
        meta: RequestMetadata,
        req: CustomerInformationReq
    ): OperationExecution<CustomerInformationReq, CustomerInformationResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun unpublishFirmware(
        meta: RequestMetadata,
        req: UnpublishFirmwareReq
    ): OperationExecution<UnpublishFirmwareReq, UnpublishFirmwareResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun setVariableMonitoring(
        meta: RequestMetadata,
        req: SetVariableMonitoringReq
    ): OperationExecution<SetVariableMonitoringReq, SetVariableMonitoringResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun setMonitoringLevel(
        meta: RequestMetadata,
        req: SetMonitoringLevelReq
    ): OperationExecution<SetMonitoringLevelReq, SetMonitoringLevelResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun publishFirmware(
        meta: RequestMetadata,
        req: PublishFirmwareReq
    ): OperationExecution<PublishFirmwareReq, PublishFirmwareResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun setNetworkProfile(
        meta: RequestMetadata,
        req: SetNetworkProfileReq
    ): OperationExecution<SetNetworkProfileReq, SetNetworkProfileResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun getTransactionStatus(
        meta: RequestMetadata,
        req: GetTransactionStatusReq
    ): OperationExecution<GetTransactionStatusReq, GetTransactionStatusResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun setMonitoringBase(
        meta: RequestMetadata,
        req: SetMonitoringBaseReq
    ): OperationExecution<SetMonitoringBaseReq, SetMonitoringBaseResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun getDisplayMessages(
        meta: RequestMetadata,
        req: GetDisplayMessagesReq
    ): OperationExecution<GetDisplayMessagesReq, GetDisplayMessagesResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun costUpdated(
        meta: RequestMetadata,
        req: CostUpdatedReq
    ): OperationExecution<CostUpdatedReq, CostUpdatedResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun setDisplayMessage(
        meta: RequestMetadata,
        req: SetDisplayMessageReq
    ): OperationExecution<SetDisplayMessageReq, SetDisplayMessageResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun deleteCertificate(
        meta: RequestMetadata,
        req: DeleteCertificateReq
    ): OperationExecution<DeleteCertificateReq, DeleteCertificateResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun getMonitoringReport(
        meta: RequestMetadata,
        req: GetMonitoringReportReq
    ): OperationExecution<GetMonitoringReportReq, GetMonitoringReportResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    override fun clearVariableMonitoring(
        meta: RequestMetadata,
        req: ClearVariableMonitoringReq
    ): OperationExecution<ClearVariableMonitoringReq, ClearVariableMonitoringResp> =
        sendMessage(meta = meta, req = req, action = getActionFromReq(req))

    private inline fun <T, reified P : Any> sendMessage(
        meta: RequestMetadata,
        req: T,
        action: String
    ): OperationExecution<T, P> {
        val startTime = Clock.System.now()
        val resp = server.sendMessage<T, P>(csOcppId = meta.chargingStationId, action = action, message = req)
        val stopTime = Clock.System.now()
        return OperationExecution(
            ExecutionMetadata(
                reqMeta = meta,
                status = RequestStatus.SUCCESS,
                requestTime = startTime,
                responseTime = stopTime
            ), req, resp
        )
    }

    private fun <T : Any> getActionFromReq(req: T): Action = when (req) {
        is ResetReq -> ActionOcpp.RESET.value
        is ChangeAvailabilityReq -> ActionOcpp.CHANGE_AVAILABILITY.value
        is SetVariablesReq -> ActionOcpp.SET_VARIABLES.value
        is ClearCacheReq -> ActionOcpp.CLEAR_CACHE.value
        is RequestStartTransactionReq -> ActionOcpp.REQUEST_START_TRANSACTION.value
        is RequestStopTransactionReq -> ActionOcpp.REQUEST_STOP_TRANSACTION.value
        is UnlockConnectorReq -> ActionOcpp.UNLOCK_CONNECTOR.value
        is GetReportReq -> ActionOcpp.GET_REPORT.value
        is GetBaseReportReq -> ActionOcpp.GET_BASE_REPORT.value
        is GetVariablesReq -> ActionOcpp.GET_VARIABLES.value
        is CancelReservationReq -> ActionOcpp.CANCEL_RESERVATION.value
        is ClearChargingProfileReq -> ActionOcpp.CLEAR_CHARGING_PROFILE.value
        is GetCompositeScheduleReq -> ActionOcpp.GET_COMPOSITE_SCHEDULE.value
        is GetLocalListVersionReq -> ActionOcpp.GET_LOCAL_LIST_VERSION.value
        is UpdateFirmwareReq -> ActionOcpp.UPDATE_FIRMWARE.value
        is SendLocalListReq -> ActionOcpp.SEND_LOCAL_LIST.value
        is TriggerMessageReq -> ActionOcpp.TRIGGER_MESSAGE.value
        is SetChargingProfileReq -> ActionOcpp.SET_CHARGING_PROFILE.value
        is ReserveNowReq -> ActionOcpp.RESERVE_NOW.value
        is DataTransferReq -> ActionOcpp.DATA_TRANSFER.value
        is CertificateSignedReq -> ActionOcpp.CERTIFICATE_SIGNED.value
        is GetLogReq -> ActionOcpp.GET_LOG.value
        is ClearDisplayMessageReq -> ActionOcpp.CLEAR_DISPLAY_MESSAGE.value
        is GetChargingProfilesReq -> ActionOcpp.GET_CHARGING_PROFILES.value
        is GetInstalledCertificateIdsReq -> ActionOcpp.GET_INSTALLED_CERTIFICATE_IDS.value
        is InstallCertificateReq -> ActionOcpp.INSTALL_CERTIFICATE.value
        is CustomerInformationReq -> ActionOcpp.CUSTOMER_INFORMATION.value
        is UnpublishFirmwareReq -> ActionOcpp.UNPUBLISH_FIRMWARE.value
        is SetVariableMonitoringReq -> ActionOcpp.SET_VARIABLE_MONITORING.value
        is SetMonitoringLevelReq -> ActionOcpp.SET_MONITORING_LEVEL.value
        is PublishFirmwareReq -> ActionOcpp.PUBLISH_FIRMWARE.value
        is SetNetworkProfileReq -> ActionOcpp.SET_NETWORK_PROFILE.value
        is GetTransactionStatusReq -> ActionOcpp.GET_TRANSACTION_STATUS.value
        is SetMonitoringBaseReq -> ActionOcpp.SET_MONITORING_BASE.value
        is GetDisplayMessagesReq -> ActionOcpp.GET_DISPLAY_MESSAGES.value
        is CostUpdatedReq -> ActionOcpp.COST_UPDATED.value
        is SetDisplayMessageReq -> ActionOcpp.SET_DISPLAY_MESSAGE.value
        is DeleteCertificateReq -> ActionOcpp.DELETE_CERTIFICATE.value
        is GetMonitoringReportReq -> ActionOcpp.GET_MONITORING_REPORT.value
        is ClearVariableMonitoringReq -> ActionOcpp.CLEAR_VARIABLE_MONITORING.value
        else -> throw IllegalArgumentException("Unknown action ${req::class}")

    }


}