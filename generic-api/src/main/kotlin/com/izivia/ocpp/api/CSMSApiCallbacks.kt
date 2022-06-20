package com.izivia.ocpp.api

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

interface CSMSApiCallbacks {

    fun heartbeat(request: HeartbeatReq): HeartbeatResp = throw NotImplementedError()

    fun authorize(request: AuthorizeReq): AuthorizeResp = throw NotImplementedError()

    fun meterValues(request: MeterValuesReq): MeterValuesResp = throw NotImplementedError()

    fun dataTransfer(request: DataTransferReq): DataTransferResp = throw NotImplementedError()

    fun bootNotification(request: BootNotificationReq): BootNotificationResp = throw NotImplementedError()

    fun transactionEvent(request: TransactionEventReq): TransactionEventResp = throw NotImplementedError()

    fun statusNotification(request: StatusNotificationReq): StatusNotificationResp = throw NotImplementedError()

    fun notifyReport(request: NotifyReportReq): NotifyReportResp = throw NotImplementedError()

    fun firmwareStatusNotification(request: FirmwareStatusNotificationReq): FirmwareStatusNotificationResp = throw NotImplementedError()

    fun clearedChargingLimit(request: ClearedChargingLimitReq): ClearedChargingLimitResp = throw NotImplementedError()

    fun getCertificateStatus(request: GetCertificateStatusReq): GetCertificateStatusResp = throw NotImplementedError()

    fun notifyCustomerInformation(request: NotifyCustomerInformationReq): NotifyCustomerInformationResp = throw NotImplementedError()

    fun notifyEVChargingSchedule(request: NotifyEVChargingScheduleReq): NotifyEVChargingScheduleResp = throw NotImplementedError()

    fun notifyEvent(request: NotifyEventReq): NotifyEventResp = throw NotImplementedError()

    fun notifyChargingLimit(request: NotifyChargingLimitReq): NotifyChargingLimitResp = throw NotImplementedError()

    fun notifyDisplayMessages(request: NotifyDisplayMessagesReq): NotifyDisplayMessagesResp = throw NotImplementedError()

    fun notifyEVChargingNeeds(request: NotifyEVChargingNeedsReq): NotifyEVChargingNeedsResp = throw NotImplementedError()

    fun logStatusNotification(request: LogStatusNotificationReq): LogStatusNotificationResp = throw NotImplementedError()

    fun publishFirmwareStatusNotification(request: PublishFirmwareStatusNotificationReq): PublishFirmwareStatusNotificationResp = throw NotImplementedError()

    fun notifyMonitoringReport(request: NotifyMonitoringReportReq): NotifyMonitoringReportResp = throw NotImplementedError()

    fun reservationStatusUpdate(request: ReservationStatusUpdateReq): ReservationStatusUpdateResp = throw NotImplementedError()

    fun securityEventNotification(request: SecurityEventNotificationReq): SecurityEventNotificationResp = throw NotImplementedError()

    fun signCertificate(request: SignCertificateReq): SignCertificateResp = throw NotImplementedError()

    fun reportChargingProfiles(request: ReportChargingProfilesReq): ReportChargingProfilesResp = throw NotImplementedError()
}