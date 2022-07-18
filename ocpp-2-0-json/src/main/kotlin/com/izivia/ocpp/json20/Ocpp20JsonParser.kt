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

    override fun getRequestPayloadClass(action: String, context: String) = when (action.lowercase()) {
        "authorize" -> AuthorizeReq::class.java
        "bootnotification" -> BootNotificationReq::class.java
        "clearedcharginglimit" -> ClearedChargingLimitReq::class.java
        "datatransfer" -> DataTransferReq::class.java
        "firmwarestatusnotification" -> FirmwareStatusNotificationReq::class.java
        "getcertificatestatus" -> GetCertificateStatusReq::class.java
        "heartbeat" -> HeartbeatReq::class.java
        "logstatusnotification" -> LogStatusNotificationReq::class.java
        "metervalues" -> MeterValuesReq::class.java
        "notifycharginglimit" -> NotifyChargingLimitReq::class.java
        "notifycustomerinformation" -> NotifyCustomerInformationReq::class.java
        "notifydisplaymessages" -> NotifyDisplayMessagesReq::class.java
        "notifyevchargingneeds" -> NotifyEVChargingNeedsReq::class.java
        "notifyevchargingschedule" -> NotifyEVChargingScheduleReq::class.java
        "notifyevent" -> NotifyEventReq::class.java
        "notifymonitoringreport" -> NotifyMonitoringReportReq::class.java
        "notifyreport" -> NotifyReportReq::class.java
        "publishfirmwarestatusnotification" -> PublishFirmwareStatusNotificationReq::class.java
        "reportchargingprofiles" -> ReportChargingProfilesReq::class.java
        "reservationstatusupdate" -> ReservationStatusUpdateReq::class.java
        "securityeventnotification" -> SecurityEventNotificationReq::class.java
        "signcertificate" -> SignCertificateReq::class.java
        "statusnotification" -> StatusNotificationReq::class.java
        "transactionevent" -> TransactionEventReq::class.java
        else -> throw IllegalArgumentException("Action not recognized. action = ${action}. message = $context")
    }
}
