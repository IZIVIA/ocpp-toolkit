package com.izivia.ocpp.websocket

import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.json12.Ocpp12JsonParser
import com.izivia.ocpp.json15.Ocpp15JsonParser
import com.izivia.ocpp.json16.Ocpp16JsonParser
import com.izivia.ocpp.json20.Ocpp20JsonParser
import com.izivia.ocpp.transport.OcppCallErrorException
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageType

// Exhaustive on purpose: a new OCPP version must fail to compile here rather than at runtime.
internal fun getJsonMapper(ocppVersion: OcppVersion) = when (ocppVersion) {
    OcppVersion.OCPP_1_2 -> Ocpp12JsonParser()
    OcppVersion.OCPP_1_5 -> Ocpp15JsonParser()
    OcppVersion.OCPP_1_6 -> Ocpp16JsonParser()
    OcppVersion.OCPP_2_0 -> Ocpp20JsonParser()
}

/**
 * The result of the call [msgId] for [action] that this message answers, read by [parse] from its payload.
 *
 * Throws [OcppCallErrorException] with the details of a CALLERROR, and an [IllegalStateException] naming the call by
 * its message id and action when this message answers another call or is not a response: the payloads of the call and
 * of this message are left out of it, since they may carry an id tag or a credential.
 */
internal fun <P> WampMessage.resultOf(msgId: String, action: String, parse: (String) -> P): P {
    check(this.msgId == msgId) { "Wrong response received for [$msgId] [$action]: [${this.msgId}] received" }
    return when (msgType) {
        WampMessageType.CALL_RESULT -> parse(payload)
        WampMessageType.CALL_ERROR -> throw OcppCallErrorException(payload)
        else -> throw IllegalStateException("Unexpected response received for [$msgId] [$action]: $msgType")
    }
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
