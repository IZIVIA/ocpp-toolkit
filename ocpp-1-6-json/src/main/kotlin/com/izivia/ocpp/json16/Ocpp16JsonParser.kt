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
import com.izivia.ocpp.json.JsonMessage
import com.izivia.ocpp.json.OcppJsonParser
import kotlin.reflect.KClass

class Ocpp16JsonParser : OcppJsonParser() {

    private val mapper = Ocpp16JsonObjectMapper

    override fun parseAnyRequestFromJson(messageStr: String): JsonMessage<Any> {
        val parsed = parseAsString(messageStr)
            ?: throw IllegalArgumentException("Impossible parsing of message. message = $messageStr")

        if (parsed.action == null)
            throw IllegalArgumentException("The message action must not be null. message = $messageStr")

        val clazz = getRequestPayloadClass(parsed.action!!, messageStr)

        return JsonMessage(
            msgType = parsed.msgType,
            msgId = parsed.msgId,
            action = parsed.action,
            payload = mapper.readValue(parsed.payload, clazz)
        )
    }

    override fun <T : Any> parseFromJson(messageStr: String, clazz: KClass<T>): JsonMessage<T> {
        val parsed = parseAsString(messageStr)
            ?: throw IllegalArgumentException("Impossible parsing of message. message = $messageStr")

        return JsonMessage(
            msgType = parsed.msgType,
            msgId = parsed.msgId,
            action = parsed.action,
            payload = mapper.readValue(parsed.payload, clazz.java)
        )
    }

    private fun getRequestPayloadClass(action: String, context: String) = when (action) {
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
