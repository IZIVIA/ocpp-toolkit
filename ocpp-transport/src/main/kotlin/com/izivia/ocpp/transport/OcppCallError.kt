package com.izivia.ocpp.transport

data class OcppCallErrorException(
    val payload: String
) : Exception(payload)

/**
 * Former details of the CALLERROR answering a call whose handler failed, which carried the exception message.
 *
 * No longer used: the toolkit now always sends an empty JSON object `{}` as CALLERROR details, as OCPP-J 1.6 and
 * 2.0.1 §4.2.3 require when there are no error details, and never sends the exception message to the peer.
 */
@Deprecated("CALLERROR details are now always an empty JSON object; this type is no longer used by the toolkit.")
data class OcppCallErrorPayload(val exception: String?)
