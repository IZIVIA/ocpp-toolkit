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

    override fun getRequestPayloadClass(action: String, context: String) = when (action.lowercase()) {
        "authorize" -> AuthorizeReq::class.java
        "bootnotification" -> BootNotificationReq::class.java
        "datatransfer" -> DataTransferReq::class.java
        "diagnosticsstatusnotification" -> DiagnosticsStatusNotificationReq::class.java
        "firmwarestatusnotification" -> FirmwareStatusNotificationReq::class.java
        "heartbeat" -> HeartbeatReq::class.java
        "metervalues" -> MeterValuesReq::class.java
        "starttransaction" -> StartTransactionReq::class.java
        "statusnotification" -> StatusNotificationReq::class.java
        "stoptransaction" -> StopTransactionReq::class.java
        else -> throw IllegalArgumentException("Action not recognized. action = ${action}. message = $context")
    }
}
