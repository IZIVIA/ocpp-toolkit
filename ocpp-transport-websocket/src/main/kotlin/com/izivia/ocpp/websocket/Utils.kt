package com.izivia.ocpp.websocket

import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.json12.Ocpp12JsonParser
import com.izivia.ocpp.json15.Ocpp15JsonParser
import com.izivia.ocpp.json16.Ocpp16JsonParser
import com.izivia.ocpp.json20.Ocpp20JsonParser
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.wamp.messages.WampMessage

// Exhaustive on purpose: a new OCPP version must fail to compile here rather than at runtime.
internal fun getJsonMapper(ocppVersion: OcppVersion) = when (ocppVersion) {
    OcppVersion.OCPP_1_2 -> Ocpp12JsonParser()
    OcppVersion.OCPP_1_5 -> Ocpp15JsonParser()
    OcppVersion.OCPP_1_6 -> Ocpp16JsonParser()
    OcppVersion.OCPP_2_0 -> Ocpp20JsonParser()
}

/** Description of the CALLERROR answering a call whose handling failed: fixed, so it never quotes the failure. */
internal const val INTERNAL_ERROR_DESCRIPTION = "Internal error"

/**
 * The CALLERROR answering the call [msgId] whose handling failed. OCPP-J 1.6 and 2.0.1 §4.2.3: "If there are no
 * error details you MUST fill in an empty object {}". The failure itself is never sent to the peer.
 */
internal fun internalErrorFor(msgId: String) = WampMessage.CallError(
    msgId = msgId,
    errorCode = MessageErrorCode.INTERNAL_ERROR,
    errorDescription = INTERNAL_ERROR_DESCRIPTION,
    payload = "{}"
)
