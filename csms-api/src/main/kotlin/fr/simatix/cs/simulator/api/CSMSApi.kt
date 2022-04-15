package fr.simatix.cs.simulator.api

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.authorize.AuthorizeReq
import fr.simatix.cs.simulator.api.model.authorize.AuthorizeResp
import fr.simatix.cs.simulator.api.model.bootnotification.BootNotificationReq
import fr.simatix.cs.simulator.api.model.bootnotification.BootNotificationResp
import fr.simatix.cs.simulator.api.model.clearedcharginglimit.ClearedChargingLimitReq
import fr.simatix.cs.simulator.api.model.clearedcharginglimit.ClearedChargingLimitResp
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import fr.simatix.cs.simulator.api.model.getcertificatestatus.GetCertificateStatusReq
import fr.simatix.cs.simulator.api.model.getcertificatestatus.GetCertificateStatusResp
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.api.model.logstatusnotification.LogStatusNotificationReq
import fr.simatix.cs.simulator.api.model.logstatusnotification.LogStatusNotificationResp
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesResp
import fr.simatix.cs.simulator.api.model.notifycharginglimit.NotifyChargingLimitReq
import fr.simatix.cs.simulator.api.model.notifycharginglimit.NotifyChargingLimitResp
import fr.simatix.cs.simulator.api.model.notifycustomerinformation.NotifyCustomerInformationReq
import fr.simatix.cs.simulator.api.model.notifycustomerinformation.NotifyCustomerInformationResp
import fr.simatix.cs.simulator.api.model.notifydisplaymessages.NotifyDisplayMessagesReq
import fr.simatix.cs.simulator.api.model.notifydisplaymessages.NotifyDisplayMessagesResp
import fr.simatix.cs.simulator.api.model.notifyevchargingschedule.NotifyEVChargingScheduleReq
import fr.simatix.cs.simulator.api.model.notifyevchargingschedule.NotifyEVChargingScheduleResp
import fr.simatix.cs.simulator.api.model.notifyevent.NotifyEventReq
import fr.simatix.cs.simulator.api.model.notifyevent.NotifyEventResp
import fr.simatix.cs.simulator.api.model.notifymonitoringreport.NotifyMonitoringReportReq
import fr.simatix.cs.simulator.api.model.notifymonitoringreport.NotifyMonitoringReportResp
import fr.simatix.cs.simulator.api.model.notifyreport.NotifyReportReq
import fr.simatix.cs.simulator.api.model.notifyreport.NotifyReportResp
import fr.simatix.cs.simulator.api.model.reservationstatusupdate.ReservationStatusUpdateReq
import fr.simatix.cs.simulator.api.model.reservationstatusupdate.ReservationStatusUpdateResp
import fr.simatix.cs.simulator.api.model.statusnotification.StatusNotificationReq
import fr.simatix.cs.simulator.api.model.statusnotification.StatusNotificationResp
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionEventReq
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionEventResp
import fr.simatix.cs.simulator.operation.information.OperationExecution
import fr.simatix.cs.simulator.operation.information.RequestMetadata
import fr.simatix.cs.simulator.api.model.notifyevchargingneeds.NotifyEVChargingNeedsReq
import fr.simatix.cs.simulator.api.model.notifyevchargingneeds.NotifyEVChargingNeedsResp

interface CSMSApi {

    fun connect()

    fun close()

    fun heartbeat(meta: RequestMetadata, request: HeartbeatReq): OperationExecution<HeartbeatReq, HeartbeatResp>

    fun authorize(meta: RequestMetadata, request: AuthorizeReq): OperationExecution<AuthorizeReq, AuthorizeResp>

    fun meterValues(meta: RequestMetadata, request: MeterValuesReq): OperationExecution<MeterValuesReq, MeterValuesResp>

    fun dataTransfer(
        meta: RequestMetadata,
        request: DataTransferReq
    ): OperationExecution<DataTransferReq, DataTransferResp>

    fun bootNotification(
        meta: RequestMetadata,
        request: BootNotificationReq
    ): OperationExecution<BootNotificationReq, BootNotificationResp>

    fun transactionEvent(
        meta: RequestMetadata,
        request: TransactionEventReq
    ): OperationExecution<TransactionEventReq, TransactionEventResp>

    fun statusNotification(
        meta: RequestMetadata,
        request: StatusNotificationReq
    ): OperationExecution<StatusNotificationReq, StatusNotificationResp>

    fun notifyReport(
        meta: RequestMetadata,
        request: NotifyReportReq
    ): OperationExecution<NotifyReportReq, NotifyReportResp>

    fun firmwareStatusNotification(
            meta: RequestMetadata,
            request: FirmwareStatusNotificationReq
    ): OperationExecution<FirmwareStatusNotificationReq, FirmwareStatusNotificationResp>

    fun clearedChargingLimit(
        meta: RequestMetadata,
        request: ClearedChargingLimitReq
    ): OperationExecution<ClearedChargingLimitReq, ClearedChargingLimitResp>

    fun getCertificateStatus(
        meta: RequestMetadata,
        request: GetCertificateStatusReq
    ): OperationExecution<GetCertificateStatusReq, GetCertificateStatusResp>

    fun notifyCustomerInformation(
        meta: RequestMetadata,
        request: NotifyCustomerInformationReq
    ): OperationExecution<NotifyCustomerInformationReq, NotifyCustomerInformationResp>

    fun notifyEVChargingSchedule(
        meta: RequestMetadata,
        request: NotifyEVChargingScheduleReq
    ): OperationExecution<NotifyEVChargingScheduleReq, NotifyEVChargingScheduleResp>

    fun notifyEvent(
        meta: RequestMetadata,
        request: NotifyEventReq
    ): OperationExecution<NotifyEventReq, NotifyEventResp>

    fun notifyChargingLimit(
        meta: RequestMetadata,
        request: NotifyChargingLimitReq
    ): OperationExecution<NotifyChargingLimitReq, NotifyChargingLimitResp>

    fun notifyDisplayMessages(
        meta: RequestMetadata,
        request: NotifyDisplayMessagesReq
    ): OperationExecution<NotifyDisplayMessagesReq, NotifyDisplayMessagesResp>

    fun notifyEVChargingNeeds(
        meta: RequestMetadata,
        request: NotifyEVChargingNeedsReq
    ): OperationExecution<NotifyEVChargingNeedsReq, NotifyEVChargingNeedsResp>

    fun logStatusNotification(
        meta: RequestMetadata,
        request: LogStatusNotificationReq
    ): OperationExecution<LogStatusNotificationReq, LogStatusNotificationResp>


    fun notifyMonitoringReport(
        meta: RequestMetadata,
        request: NotifyMonitoringReportReq
    ): OperationExecution<NotifyMonitoringReportReq, NotifyMonitoringReportResp>

    fun reservationStatusUpdate(
        meta: RequestMetadata,
        request: ReservationStatusUpdateReq
    ): OperationExecution<ReservationStatusUpdateReq, ReservationStatusUpdateResp>
}

@Suppress("UNCHECKED_CAST")
fun CSMSApi.send(
    meta: RequestMetadata,
    request: Request
): OperationExecution<Request, Response> =
    when (request) {
        is HeartbeatReq -> heartbeat(meta, request)
        is AuthorizeReq -> authorize(meta, request)
        is MeterValuesReq -> meterValues(meta, request)
        is DataTransferReq -> dataTransfer(meta, request)
        is BootNotificationReq -> bootNotification(meta, request)
        is TransactionEventReq -> transactionEvent(meta, request)
        is StatusNotificationReq -> statusNotification(meta, request)
        is NotifyReportReq -> notifyReport(meta, request)
        is FirmwareStatusNotificationReq -> firmwareStatusNotification(meta, request)
        is ClearedChargingLimitReq -> clearedChargingLimit(meta, request)
        is GetCertificateStatusReq -> getCertificateStatus(meta, request)
        is NotifyCustomerInformationReq -> notifyCustomerInformation(meta, request)
        is NotifyEventReq -> notifyEvent(meta, request)
        is NotifyEVChargingScheduleReq -> notifyEVChargingSchedule(meta, request)
        is NotifyChargingLimitReq -> notifyChargingLimit(meta, request)
        is NotifyDisplayMessagesReq -> notifyDisplayMessages(meta, request)
        is NotifyEVChargingNeedsReq -> notifyEVChargingNeeds(meta, request)
        is LogStatusNotificationReq -> logStatusNotification(meta, request)
        is NotifyMonitoringReportReq -> notifyMonitoringReport(meta, request)
        is ReservationStatusUpdateReq -> reservationStatusUpdate(meta, request)
        else -> throw IllegalStateException()
    } as OperationExecution<Request, Response>