package com.izivia.ocpp.api.impl

import com.izivia.ocpp.api.CSMSApi
import com.izivia.ocpp.api.CSMSApiCallbacks
import com.izivia.ocpp.api.model.authorize.AuthorizeReq
import com.izivia.ocpp.api.model.authorize.AuthorizeResp
import com.izivia.ocpp.api.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.api.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.api.model.clearedcharginglimit.ClearedChargingLimitReq
import com.izivia.ocpp.api.model.clearedcharginglimit.ClearedChargingLimitResp
import com.izivia.ocpp.api.model.datatransfer.DataTransferReq
import com.izivia.ocpp.api.model.datatransfer.DataTransferResp
import com.izivia.ocpp.api.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.api.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.api.model.getcertificatestatus.GetCertificateStatusReq
import com.izivia.ocpp.api.model.getcertificatestatus.GetCertificateStatusResp
import com.izivia.ocpp.api.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.api.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.api.model.logstatusnotification.LogStatusNotificationReq
import com.izivia.ocpp.api.model.logstatusnotification.LogStatusNotificationResp
import com.izivia.ocpp.api.model.metervalues.MeterValuesReq
import com.izivia.ocpp.api.model.metervalues.MeterValuesResp
import com.izivia.ocpp.api.model.notifycharginglimit.NotifyChargingLimitReq
import com.izivia.ocpp.api.model.notifycharginglimit.NotifyChargingLimitResp
import com.izivia.ocpp.api.model.notifycustomerinformation.NotifyCustomerInformationReq
import com.izivia.ocpp.api.model.notifycustomerinformation.NotifyCustomerInformationResp
import com.izivia.ocpp.api.model.notifydisplaymessages.NotifyDisplayMessagesReq
import com.izivia.ocpp.api.model.notifydisplaymessages.NotifyDisplayMessagesResp
import com.izivia.ocpp.api.model.notifyevchargingneeds.NotifyEVChargingNeedsReq
import com.izivia.ocpp.api.model.notifyevchargingneeds.NotifyEVChargingNeedsResp
import com.izivia.ocpp.api.model.notifyevchargingschedule.NotifyEVChargingScheduleReq
import com.izivia.ocpp.api.model.notifyevchargingschedule.NotifyEVChargingScheduleResp
import com.izivia.ocpp.api.model.notifyevent.NotifyEventReq
import com.izivia.ocpp.api.model.notifyevent.NotifyEventResp
import com.izivia.ocpp.api.model.notifymonitoringreport.NotifyMonitoringReportReq
import com.izivia.ocpp.api.model.notifymonitoringreport.NotifyMonitoringReportResp
import com.izivia.ocpp.api.model.notifyreport.NotifyReportReq
import com.izivia.ocpp.api.model.notifyreport.NotifyReportResp
import com.izivia.ocpp.api.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationReq
import com.izivia.ocpp.api.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationResp
import com.izivia.ocpp.api.model.reportchargingprofiles.ReportChargingProfilesReq
import com.izivia.ocpp.api.model.reportchargingprofiles.ReportChargingProfilesResp
import com.izivia.ocpp.api.model.reservationstatusupdate.ReservationStatusUpdateReq
import com.izivia.ocpp.api.model.reservationstatusupdate.ReservationStatusUpdateResp
import com.izivia.ocpp.api.model.securityeventnotification.SecurityEventNotificationReq
import com.izivia.ocpp.api.model.securityeventnotification.SecurityEventNotificationResp
import com.izivia.ocpp.api.model.signcertificate.SignCertificateReq
import com.izivia.ocpp.api.model.signcertificate.SignCertificateResp
import com.izivia.ocpp.api.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.api.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.api.model.transactionevent.TransactionEventReq
import com.izivia.ocpp.api.model.transactionevent.TransactionEventResp
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import kotlinx.datetime.Clock.System.now

class DefaultCSMSApi(private val csmsApiCallbacks: CSMSApiCallbacks) : CSMSApi {

    override fun connect() = throw IllegalStateException("Server can't connect to client")

    override fun close() = throw IllegalStateException("Server can't connect to client")

    override fun heartbeat(
        meta: RequestMetadata,
        request: HeartbeatReq
    ): OperationExecution<HeartbeatReq, HeartbeatResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.heartbeat(request)
        )

    override fun authorize(
        meta: RequestMetadata,
        request: AuthorizeReq
    ): OperationExecution<AuthorizeReq, AuthorizeResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.authorize(request)
        )

    override fun meterValues(
        meta: RequestMetadata,
        request: MeterValuesReq
    ): OperationExecution<MeterValuesReq, MeterValuesResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.meterValues(request)
        )

    override fun dataTransfer(
        meta: RequestMetadata,
        request: DataTransferReq
    ): OperationExecution<DataTransferReq, DataTransferResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.dataTransfer(request)
        )

    override fun bootNotification(
        meta: RequestMetadata,
        request: BootNotificationReq
    ): OperationExecution<BootNotificationReq, BootNotificationResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.bootNotification(request)
        )

    override fun transactionEvent(
        meta: RequestMetadata,
        request: TransactionEventReq
    ): OperationExecution<TransactionEventReq, TransactionEventResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.transactionEvent(request)
        )

    override fun statusNotification(
        meta: RequestMetadata,
        request: StatusNotificationReq
    ): OperationExecution<StatusNotificationReq, StatusNotificationResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.statusNotification(request)
        )

    override fun notifyReport(
        meta: RequestMetadata,
        request: NotifyReportReq
    ): OperationExecution<NotifyReportReq, NotifyReportResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.notifyReport(request)
        )

    override fun firmwareStatusNotification(
        meta: RequestMetadata,
        request: FirmwareStatusNotificationReq
    ): OperationExecution<FirmwareStatusNotificationReq, FirmwareStatusNotificationResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.firmwareStatusNotification(request)
        )

    override fun clearedChargingLimit(
        meta: RequestMetadata,
        request: ClearedChargingLimitReq
    ): OperationExecution<ClearedChargingLimitReq, ClearedChargingLimitResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.clearedChargingLimit(request)
        )

    override fun getCertificateStatus(
        meta: RequestMetadata,
        request: GetCertificateStatusReq
    ): OperationExecution<GetCertificateStatusReq, GetCertificateStatusResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.getCertificateStatus(request)
        )

    override fun notifyCustomerInformation(
        meta: RequestMetadata,
        request: NotifyCustomerInformationReq
    ): OperationExecution<NotifyCustomerInformationReq, NotifyCustomerInformationResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.notifyCustomerInformation(request)
        )

    override fun notifyEVChargingSchedule(
        meta: RequestMetadata,
        request: NotifyEVChargingScheduleReq
    ): OperationExecution<NotifyEVChargingScheduleReq, NotifyEVChargingScheduleResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.notifyEVChargingSchedule(request)
        )

    override fun notifyEvent(
        meta: RequestMetadata,
        request: NotifyEventReq
    ): OperationExecution<NotifyEventReq, NotifyEventResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.notifyEvent(request)
        )

    override fun notifyChargingLimit(
        meta: RequestMetadata,
        request: NotifyChargingLimitReq
    ): OperationExecution<NotifyChargingLimitReq, NotifyChargingLimitResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.notifyChargingLimit(request)
        )

    override fun notifyDisplayMessages(
        meta: RequestMetadata,
        request: NotifyDisplayMessagesReq
    ): OperationExecution<NotifyDisplayMessagesReq, NotifyDisplayMessagesResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.notifyDisplayMessages(request)
        )

    override fun notifyEVChargingNeeds(
        meta: RequestMetadata,
        request: NotifyEVChargingNeedsReq
    ): OperationExecution<NotifyEVChargingNeedsReq, NotifyEVChargingNeedsResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.notifyEVChargingNeeds(request)
        )

    override fun logStatusNotification(
        meta: RequestMetadata,
        request: LogStatusNotificationReq
    ): OperationExecution<LogStatusNotificationReq, LogStatusNotificationResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.logStatusNotification(request)
        )

    override fun publishFirmwareStatusNotification(
        meta: RequestMetadata,
        request: PublishFirmwareStatusNotificationReq
    ): OperationExecution<PublishFirmwareStatusNotificationReq, PublishFirmwareStatusNotificationResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.publishFirmwareStatusNotification(request)
        )

    override fun notifyMonitoringReport(
        meta: RequestMetadata,
        request: NotifyMonitoringReportReq
    ): OperationExecution<NotifyMonitoringReportReq, NotifyMonitoringReportResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.notifyMonitoringReport(request)
        )

    override fun reservationStatusUpdate(
        meta: RequestMetadata,
        request: ReservationStatusUpdateReq
    ): OperationExecution<ReservationStatusUpdateReq, ReservationStatusUpdateResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.reservationStatusUpdate(request)
        )

    override fun securityEventNotification(
        meta: RequestMetadata,
        request: SecurityEventNotificationReq
    ): OperationExecution<SecurityEventNotificationReq, SecurityEventNotificationResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.securityEventNotification(request)
        )

    override fun signCertificate(
        meta: RequestMetadata,
        request: SignCertificateReq
    ): OperationExecution<SignCertificateReq, SignCertificateResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.signCertificate(request)
        )

    override fun reportChargingProfiles(
        meta: RequestMetadata,
        request: ReportChargingProfilesReq
    ): OperationExecution<ReportChargingProfilesReq, ReportChargingProfilesResp> =
        OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
            request,
            csmsApiCallbacks.reportChargingProfiles(request)
        )
}
































