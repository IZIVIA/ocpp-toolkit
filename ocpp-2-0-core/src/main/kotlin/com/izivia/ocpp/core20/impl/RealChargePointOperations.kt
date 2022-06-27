package com.izivia.ocpp.core20.impl

import com.izivia.ocpp.core20.CSMSOperations
import com.izivia.ocpp.core20.ChargePointOperations
import com.izivia.ocpp.core20.model.authorize.AuthorizeReq
import com.izivia.ocpp.core20.model.authorize.AuthorizeResp
import com.izivia.ocpp.core20.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core20.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core20.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.core20.model.certificateSigned.CertificateSignedReq
import com.izivia.ocpp.core20.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core20.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core20.model.clearchargingprofile.ClearChargingProfileReq
import com.izivia.ocpp.core20.model.cleardisplaymessage.ClearDisplayMessageReq
import com.izivia.ocpp.core20.model.clearedcharginglimit.ClearedChargingLimitReq
import com.izivia.ocpp.core20.model.clearedcharginglimit.ClearedChargingLimitResp
import com.izivia.ocpp.core20.model.clearvariablemonitoring.ClearVariableMonitoringReq
import com.izivia.ocpp.core20.model.costupdated.CostUpdatedReq
import com.izivia.ocpp.core20.model.customerinformation.CustomerInformationReq
import com.izivia.ocpp.core20.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core20.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core20.model.deletecertificate.DeleteCertificateReq
import com.izivia.ocpp.core20.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core20.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core20.model.getbasereport.GetBaseReportReq
import com.izivia.ocpp.core20.model.getcertificatestatus.GetCertificateStatusReq
import com.izivia.ocpp.core20.model.getcertificatestatus.GetCertificateStatusResp
import com.izivia.ocpp.core20.model.getchargingprofiles.GetChargingProfilesReq
import com.izivia.ocpp.core20.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.core20.model.getcompositeschedule.GetCompositeScheduleReq
import com.izivia.ocpp.core20.model.getdisplaymessages.GetDisplayMessagesReq
import com.izivia.ocpp.core20.model.getinstalledcertificateids.GetInstalledCertificateIdsReq
import com.izivia.ocpp.core20.model.getlog.GetLogReq
import com.izivia.ocpp.core20.model.getmonitoringreport.GetMonitoringReportReq
import com.izivia.ocpp.core20.model.getreport.GetReportReq
import com.izivia.ocpp.core20.model.gettransactionstatus.GetTransactionStatusReq
import com.izivia.ocpp.core20.model.getvariables.GetVariablesReq
import com.izivia.ocpp.core20.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core20.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core20.model.installcertificate.InstallCertificateReq
import com.izivia.ocpp.core20.model.logstatusnotification.LogStatusNotificationReq
import com.izivia.ocpp.core20.model.logstatusnotification.LogStatusNotificationResp
import com.izivia.ocpp.core20.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core20.model.metervalues.MeterValuesResp
import com.izivia.ocpp.core20.model.notifycharginglimit.NotifyChargingLimitReq
import com.izivia.ocpp.core20.model.notifycharginglimit.NotifyChargingLimitResp
import com.izivia.ocpp.core20.model.notifycustomerinformation.NotifyCustomerInformationReq
import com.izivia.ocpp.core20.model.notifycustomerinformation.NotifyCustomerInformationResp
import com.izivia.ocpp.core20.model.notifydisplaymessages.NotifyDisplayMessagesReq
import com.izivia.ocpp.core20.model.notifydisplaymessages.NotifyDisplayMessagesResp
import com.izivia.ocpp.core20.model.notifyevchargingneeds.NotifyEVChargingNeedsReq
import com.izivia.ocpp.core20.model.notifyevchargingneeds.NotifyEVChargingNeedsResp
import com.izivia.ocpp.core20.model.notifyevchargingschedule.NotifyEVChargingScheduleReq
import com.izivia.ocpp.core20.model.notifyevchargingschedule.NotifyEVChargingScheduleResp
import com.izivia.ocpp.core20.model.notifyevent.NotifyEventReq
import com.izivia.ocpp.core20.model.notifyevent.NotifyEventResp
import com.izivia.ocpp.core20.model.notifymonitoringreport.NotifyMonitoringReportReq
import com.izivia.ocpp.core20.model.notifymonitoringreport.NotifyMonitoringReportResp
import com.izivia.ocpp.core20.model.notifyreport.NotifyReportReq
import com.izivia.ocpp.core20.model.notifyreport.NotifyReportResp
import com.izivia.ocpp.core20.model.publishfirmware.PublishFirmwareReq
import com.izivia.ocpp.core20.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationReq
import com.izivia.ocpp.core20.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationResp
import com.izivia.ocpp.core20.model.remotestart.RequestStartTransactionReq
import com.izivia.ocpp.core20.model.remotestop.RequestStopTransactionReq
import com.izivia.ocpp.core20.model.reportchargingprofiles.ReportChargingProfilesReq
import com.izivia.ocpp.core20.model.reportchargingprofiles.ReportChargingProfilesResp
import com.izivia.ocpp.core20.model.reservationstatusupdate.ReservationStatusUpdateReq
import com.izivia.ocpp.core20.model.reservationstatusupdate.ReservationStatusUpdateResp
import com.izivia.ocpp.core20.model.reservenow.ReserveNowReq
import com.izivia.ocpp.core20.model.reset.ResetReq
import com.izivia.ocpp.core20.model.securityeventnotification.SecurityEventNotificationReq
import com.izivia.ocpp.core20.model.securityeventnotification.SecurityEventNotificationResp
import com.izivia.ocpp.core20.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.core20.model.setchargingprofile.SetChargingProfileReq
import com.izivia.ocpp.core20.model.setdisplaymessage.SetDisplayMessageReq
import com.izivia.ocpp.core20.model.setvariablemonitoring.SetVariableMonitoringReq
import com.izivia.ocpp.core20.model.setmonitoringlevel.SetMonitoringLevelReq
import com.izivia.ocpp.core20.model.setnetworkprofile.SetNetworkProfileReq
import com.izivia.ocpp.core20.model.setmonitoringbase.SetMonitoringBaseReq
import com.izivia.ocpp.core20.model.setvariables.SetVariablesReq
import com.izivia.ocpp.core20.model.signcertificate.SignCertificateReq
import com.izivia.ocpp.core20.model.signcertificate.SignCertificateResp
import com.izivia.ocpp.core20.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core20.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core20.model.transactionevent.TransactionEventReq
import com.izivia.ocpp.core20.model.transactionevent.TransactionEventResp
import com.izivia.ocpp.core20.model.triggermessage.TriggerMessageReq
import com.izivia.ocpp.core20.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core20.model.unpublishfirmware.UnpublishFirmwareReq
import com.izivia.ocpp.core20.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.operation.information.ActionOcpp.*
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import com.izivia.ocpp.transport.ClientTransport
import com.izivia.ocpp.transport.receiveMessage
import com.izivia.ocpp.transport.sendMessage
import kotlinx.datetime.Clock

class RealChargePointOperations(
    private val chargeStationId: String,
    private val client: ClientTransport,
    private val csmsOperations: CSMSOperations
) : ChargePointOperations {

    init {
        client.receiveMessage(RESET.value) { req: ResetReq ->
            csmsOperations.reset(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(CHANGE_AVAILABILITY.value) { req: ChangeAvailabilityReq ->
            csmsOperations.changeAvailability(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(SET_VARIABLES.value) { req: SetVariablesReq ->
            csmsOperations.setVariables(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(CLEAR_CACHE.value) { req: ClearCacheReq ->
            csmsOperations.clearCache(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(REQUEST_START_TRANSACTION.value) { req: RequestStartTransactionReq ->
            csmsOperations.requestStartTransaction(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(REQUEST_STOP_TRANSACTION.value) { req: RequestStopTransactionReq ->
            csmsOperations.requestStopTransaction(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(UNLOCK_CONNECTOR.value) { req: UnlockConnectorReq ->
            csmsOperations.unlockConnector(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(GET_VARIABLES.value) { req: GetVariablesReq ->
            csmsOperations.getVariables(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(GET_REPORT.value) { req: GetReportReq ->
            csmsOperations.getReport(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(GET_BASE_REPORT.value) { req: GetBaseReportReq ->
            csmsOperations.getBaseReport(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(SET_CHARGING_PROFILE.value) { req: SetChargingProfileReq ->
            csmsOperations.setChargingProfile(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(TRIGGER_MESSAGE.value) { req: TriggerMessageReq ->
            csmsOperations.triggerMessage(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(SEND_LOCAL_LIST.value) { req: SendLocalListReq ->
            csmsOperations.sendLocalList(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(GET_LOCAL_LIST_VERSION.value) { req: GetLocalListVersionReq ->
            csmsOperations.getLocalListVersion(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(CANCEL_RESERVATION.value) { req: CancelReservationReq ->
            csmsOperations.cancelReservation(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(CLEAR_CHARGING_PROFILE.value) { req: ClearChargingProfileReq ->
            csmsOperations.clearChargingProfile(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(GET_COMPOSITE_SCHEDULE.value) { req: GetCompositeScheduleReq ->
            csmsOperations.getCompositeSchedule(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(UPDATE_FIRMWARE.value) { req: UpdateFirmwareReq ->
            csmsOperations.updateFirmware(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(RESERVE_NOW.value) { req: ReserveNowReq ->
            csmsOperations.reserveNow(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(DATA_TRANSFER.value) { req: DataTransferReq ->
            csmsOperations.dataTransfer(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(CERTIFICATE_SIGNED.value) { req: CertificateSignedReq ->
            csmsOperations.certificateSigned(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(GET_LOG.value) { req: GetLogReq ->
            csmsOperations.getLog(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(CLEAR_DISPLAY_MESSAGE.value) { req: ClearDisplayMessageReq ->
            csmsOperations.clearDisplayMessage(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage(GET_INSTALLED_CERTIFICATE_IDS.value) { req: GetInstalledCertificateIdsReq ->
            csmsOperations.getInstalledCertificateIds(
                    RequestMetadata(chargeStationId),
                    req
            ).response
        }

        client.receiveMessage(INSTALL_CERTIFICATE.value) { req: InstallCertificateReq ->
            csmsOperations.installCertificate(
                    RequestMetadata(chargeStationId),
                    req
            ).response
        }

        client.receiveMessage(CUSTOMER_INFORMATION.value) { req: CustomerInformationReq ->
            csmsOperations.customerInformation(
                    RequestMetadata(chargeStationId),
                    req
            ).response
        }

        client.receiveMessage(UNPUBLISH_FIRMWARE.value) { req: UnpublishFirmwareReq ->
            csmsOperations.unpublishFirmware(
                    RequestMetadata(chargeStationId),
                    req
            ).response
        }

        client.receiveMessage(GET_CHARGING_PROFILES.value) { req: GetChargingProfilesReq ->
            csmsOperations.getChargingProfiles(
                    RequestMetadata(chargeStationId),
                    req
            ).response
        }

        client.receiveMessage(PUBLISH_FIRMWARE.value) { req: PublishFirmwareReq ->
            csmsOperations.publishFirmware(
                    RequestMetadata(chargeStationId),
                    req
            ).response
        }

        client.receiveMessage(SET_VARIABLE_MONITORING.value) { req: SetVariableMonitoringReq ->
            csmsOperations.setVariableMonitoring(
                    RequestMetadata(chargeStationId),
                    req
            ).response
        }


        client.receiveMessage(SET_MONITORING_LEVEL.value) { req: SetMonitoringLevelReq ->
            csmsOperations.setMonitoringLevel(
                    RequestMetadata(chargeStationId),
                    req
            ).response
        }

        client.receiveMessage(SET_NETWORK_PROFILE.value) { req: SetNetworkProfileReq ->
            csmsOperations.setNetworkProfile(
                    RequestMetadata(chargeStationId),
                    req
            ).response
        }

        client.receiveMessage(GET_TRANSACTION_STATUS.value) { req: GetTransactionStatusReq ->
            csmsOperations.getTransactionStatus(
                    RequestMetadata(chargeStationId),
                    req
            ).response
        }

        client.receiveMessage(SET_MONITORING_BASE.value) { req: SetMonitoringBaseReq ->
            csmsOperations.setMonitoringBase(
                    RequestMetadata(chargeStationId),
                    req
            ).response
        }

        client.receiveMessage(GET_DISPLAY_MESSAGES.value) { req: GetDisplayMessagesReq ->
            csmsOperations.getDisplayMessages(
                    RequestMetadata(chargeStationId),
                    req
            ).response
        }

        client.receiveMessage(COST_UPDATED.value) { req: CostUpdatedReq ->
            csmsOperations.costUpdated(
                    RequestMetadata(chargeStationId),
                    req
            ).response
        }

        client.receiveMessage(SET_DISPLAY_MESSAGE.value) { req: SetDisplayMessageReq ->
            csmsOperations.setDisplayMessage(
                    RequestMetadata(chargeStationId),
                    req
            ).response
        }

        client.receiveMessage(DELETE_CERTIFICATE.value) { req: DeleteCertificateReq ->
            csmsOperations.deleteCertificate(
                    RequestMetadata(chargeStationId),
                    req
            ).response
        }

        client.receiveMessage(GET_MONITORING_REPORT.value) { req: GetMonitoringReportReq ->
            csmsOperations.getMonitoringReport(
                    RequestMetadata(chargeStationId),
                    req
            ).response
        }

        client.receiveMessage(CLEAR_VARIABLE_MONITORING.value) { req: ClearVariableMonitoringReq ->
            csmsOperations.clearVariableMonitoring(
                RequestMetadata(chargeStationId),
                req
            ).response
        }
    }

    override fun heartbeat(meta: RequestMetadata, request: HeartbeatReq): OperationExecution<HeartbeatReq, HeartbeatResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun authorize(meta: RequestMetadata, request: AuthorizeReq): OperationExecution<AuthorizeReq, AuthorizeResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun meterValues(meta: RequestMetadata, request: MeterValuesReq): OperationExecution<MeterValuesReq, MeterValuesResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun transactionEvent(
        meta: RequestMetadata,
        request: TransactionEventReq
    ): OperationExecution<TransactionEventReq, TransactionEventResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun statusNotification(
        meta: RequestMetadata,
        request: StatusNotificationReq
    ): OperationExecution<StatusNotificationReq, StatusNotificationResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun dataTransfer(meta: RequestMetadata, request: DataTransferReq): OperationExecution<DataTransferReq, DataTransferResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun bootNotification(
        meta: RequestMetadata,
        request: BootNotificationReq
    ): OperationExecution<BootNotificationReq, BootNotificationResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun notifyReport(
        meta: RequestMetadata,
        request: NotifyReportReq
    ): OperationExecution<NotifyReportReq, NotifyReportResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun firmwareStatusNotification(
        meta: RequestMetadata,
        request: FirmwareStatusNotificationReq
    ): OperationExecution<FirmwareStatusNotificationReq, FirmwareStatusNotificationResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun clearedChargingLimit(
        meta: RequestMetadata,
        request: ClearedChargingLimitReq
    ): OperationExecution<ClearedChargingLimitReq, ClearedChargingLimitResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun getCertificateStatus(
        meta: RequestMetadata,
        request: GetCertificateStatusReq
    ): OperationExecution<GetCertificateStatusReq, GetCertificateStatusResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun notifyCustomerInformation(
        meta: RequestMetadata,
        request: NotifyCustomerInformationReq
    ): OperationExecution<NotifyCustomerInformationReq, NotifyCustomerInformationResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun notifyEvent(
        meta: RequestMetadata,
        request: NotifyEventReq
    ): OperationExecution<NotifyEventReq, NotifyEventResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun notifyEVChargingSchedule(
        meta: RequestMetadata,
        request: NotifyEVChargingScheduleReq
    ): OperationExecution<NotifyEVChargingScheduleReq, NotifyEVChargingScheduleResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun notifyChargingLimit(
        meta: RequestMetadata,
        request: NotifyChargingLimitReq
    ): OperationExecution<NotifyChargingLimitReq, NotifyChargingLimitResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun notifyDisplayMessages(
        meta: RequestMetadata,
        request: NotifyDisplayMessagesReq
    ): OperationExecution<NotifyDisplayMessagesReq, NotifyDisplayMessagesResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun notifyEVChargingNeeds(
        meta: RequestMetadata,
        request: NotifyEVChargingNeedsReq
    ): OperationExecution<NotifyEVChargingNeedsReq, NotifyEVChargingNeedsResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun logStatusNotification(
        meta: RequestMetadata,
        request: LogStatusNotificationReq
    ): OperationExecution<LogStatusNotificationReq, LogStatusNotificationResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun publishFirmwareStatusNotification(
        meta: RequestMetadata,
        request: PublishFirmwareStatusNotificationReq
    ): OperationExecution<PublishFirmwareStatusNotificationReq, PublishFirmwareStatusNotificationResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun notifyMonitoringReport(
        meta: RequestMetadata,
        request: NotifyMonitoringReportReq
    ): OperationExecution<NotifyMonitoringReportReq, NotifyMonitoringReportResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun reservationStatusUpdate(
        meta: RequestMetadata,
        request: ReservationStatusUpdateReq
    ): OperationExecution<ReservationStatusUpdateReq, ReservationStatusUpdateResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun securityEventNotification(
        meta: RequestMetadata,
        request: SecurityEventNotificationReq
    ): OperationExecution<SecurityEventNotificationReq, SecurityEventNotificationResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun signCertificate(
        meta: RequestMetadata,
        request: SignCertificateReq
    ): OperationExecution<SignCertificateReq, SignCertificateResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun reportChargingProfiles(
            meta: RequestMetadata,
            request: ReportChargingProfilesReq
    ): OperationExecution<ReportChargingProfilesReq, ReportChargingProfilesResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun connect() {
        client.connect()
    }

    override fun close() {
        client.close()
    }

    private inline fun <T, reified P> sendMessage(
        meta: RequestMetadata,
        action: Action,
        request: T
    ): OperationExecution<T, P> {
        val requestTime = Clock.System.now()
        val response: P = client.sendMessage(action, request)
        val responseTime = Clock.System.now()
        return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS, requestTime, responseTime), request, response)
    }

    private fun <T : Any> getActionFromReq(req: T): Action =
        when (req) {
            is HeartbeatReq -> HEARTBEAT.value
            is AuthorizeReq -> AUTHORIZE.value
            is MeterValuesReq -> METER_VALUES.value
            is TransactionEventReq -> TRANSACTION_EVENT.value
            is StatusNotificationReq -> STATUS_NOTIFICATION.value
            is DataTransferReq -> DATA_TRANSFER.value
            is BootNotificationReq -> BOOT_NOTIFICATION.value
            is NotifyReportReq -> NOTIFY_REPORT.value
            is FirmwareStatusNotificationReq -> FIRMWARE_STATUS_NOTIFICATION.value
            is ClearedChargingLimitReq -> CLEARED_CHARGING_LIMIT.value
            is GetCertificateStatusReq -> GET_CERTIFICATE_STATUS.value
            is NotifyCustomerInformationReq -> NOTIFY_CUSTOMER_INFORMATION.value
            is NotifyEventReq -> NOTIFY_EVENT.value
            is NotifyEVChargingScheduleReq -> NOTIFY_EV_CHARGING_SCHEDULE.value
            is NotifyChargingLimitReq -> NOTIFY_CHARGING_LIMIT.value
            is NotifyDisplayMessagesReq -> NOTIFY_DISPLAY_MESSAGES.value
            is NotifyEVChargingNeedsReq -> NOTIFY_EV_CHARGING_NEEDS.value
            is LogStatusNotificationReq -> LOG_STATUS_NOTIFICATION.value
            is PublishFirmwareStatusNotificationReq -> PUBLISH_FIRMWARE_STATUS_NOTIFICATION.value
            is NotifyMonitoringReportReq -> NOTIFY_MONITORING_REPORT.value
            is ReservationStatusUpdateReq -> RESERVATION_STATUS_UPDATE.value
            is SecurityEventNotificationReq -> SECURITY_EVENT_NOTIFICATION.value
            is SignCertificateReq -> SIGN_CERTIFICATE.value
            is ReportChargingProfilesReq -> REPORT_CHARGING_PROFILES.value
            else -> throw IllegalArgumentException("Unknown action ${req::class}")
        }
}
