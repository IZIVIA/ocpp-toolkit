package com.izivia.ocpp.json16

import com.izivia.ocpp.core16.model.authorize.AuthorizeReq
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core16.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core16.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core16.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.json.OcppJsonParser

class Ocpp16JsonParser : OcppJsonParser(Ocpp16JsonObjectMapper) {

    override fun getRequestPayloadClass(action: String, context: String) = when (action) {
        "Authorize" -> AuthorizeReq::class.java
        "BootNotification" -> BootNotificationReq::class.java
        "DataTransfer" -> DataTransferReq::class.java
        "DiagnosticsStatusNotification" -> DiagnosticsStatusNotificationReq::class.java
        "FirmwareStatusNotification" -> FirmwareStatusNotificationReq::class.java
        "Heartbeat" -> HeartbeatReq::class.java
        "MeterValues" -> MeterValuesReq::class.java
        "StartTransaction" -> StartTransactionReq::class.java
        "StatusNotification" -> StatusNotificationReq::class.java
        "StopTransaction" -> StopTransactionReq::class.java
        else -> throw IllegalArgumentException("Action not recognized. action = ${action}. message = $context")
    }
}
