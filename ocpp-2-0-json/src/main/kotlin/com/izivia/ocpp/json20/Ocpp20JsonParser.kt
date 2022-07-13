package com.izivia.ocpp.json20

import com.izivia.ocpp.core20.model.authorize.AuthorizeReq
import com.izivia.ocpp.core20.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core20.model.clearedcharginglimit.ClearedChargingLimitReq
import com.izivia.ocpp.core20.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core20.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core20.model.getcertificatestatus.GetCertificateStatusReq
import com.izivia.ocpp.core20.model.heartbeat.HeartbeatReq
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
import com.izivia.ocpp.core20.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationReq
import com.izivia.ocpp.core20.model.reportchargingprofiles.ReportChargingProfilesReq
import com.izivia.ocpp.core20.model.reservationstatusupdate.ReservationStatusUpdateReq
import com.izivia.ocpp.core20.model.securityeventnotification.SecurityEventNotificationReq
import com.izivia.ocpp.core20.model.signcertificate.SignCertificateReq
import com.izivia.ocpp.core20.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core20.model.transactionevent.TransactionEventReq
import com.izivia.ocpp.json.OcppJsonParser

class Ocpp20JsonParser : OcppJsonParser(Ocpp20JsonObjectMapper) {

    override fun getRequestPayloadClass(action: String, context: String) = when (action) {
        "Authorize" -> AuthorizeReq::class.java
        "BootNotification" -> BootNotificationReq::class.java
        "ClearedChargingLimit" -> ClearedChargingLimitReq::class.java
        "DataTransfer" -> DataTransferReq::class.java
        "FirmwareStatusNotification" -> FirmwareStatusNotificationReq::class.java
        "GetCertificateStatus" -> GetCertificateStatusReq::class.java
        "Heartbeat" -> HeartbeatReq::class.java
        "LogStatusNotification" -> LogStatusNotificationReq::class.java
        "MeterValues" -> MeterValuesReq::class.java
        "NotifyChargingLimit" -> NotifyChargingLimitReq::class.java
        "NotifyCustomerInformation" -> NotifyCustomerInformationReq::class.java
        "NotifyDisplayMessages" -> NotifyDisplayMessagesReq::class.java
        "NotifyEVChargingNeeds" -> NotifyEVChargingNeedsReq::class.java
        "NotifyEVChargingSchedule" -> NotifyEVChargingScheduleReq::class.java
        "NotifyEvent" -> NotifyEventReq::class.java
        "NotifyMonitoringReport" -> NotifyMonitoringReportReq::class.java
        "NotifyReport" -> NotifyReportReq::class.java
        "PublishFirmwareStatusNotification" -> PublishFirmwareStatusNotificationReq::class.java
        "ReportChargingProfiles" -> ReportChargingProfilesReq::class.java
        "ReservationStatusUpdate" -> ReservationStatusUpdateReq::class.java
        "SecurityEventNotification" -> SecurityEventNotificationReq::class.java
        "SignCertificate" -> SignCertificateReq::class.java
        "StatusNotification" -> StatusNotificationReq::class.java
        "TransactionEvent" -> TransactionEventReq::class.java
        else -> throw IllegalArgumentException("Action not recognized. action = ${action}. message = $context")
    }
}
