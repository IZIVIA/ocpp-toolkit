package com.izivia.ocpp.utils

import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("com.izivia.ocpp.utils.OcppErrorListener")

enum class OcppErrorCategory {
    /** An incoming message has no registered handler. */
    UNHANDLED_ACTION,

    /** An incoming message or payload cannot be parsed, or an outgoing payload cannot be serialized. */
    PROTOCOL_ERROR,

    /** A registered handler threw an exception while processing an incoming message. */
    HANDLER_ERROR,

    /** The transport failed while processing a message, for instance when sending a response. */
    TRANSPORT_ERROR
}

/**
 * A failure of the toolkit while processing an OCPP message.
 *
 * Only [category] is always known: a message that cannot be parsed has no [messageId] nor [action], for instance.
 * The event never carries a message payload, but the message of [cause] may quote a fragment of it: parsing errors
 * usually do. Sanitize it before sending it to a third party.
 *
 * [ocppId] and [action] come from the charging station and are not validated. Bound them before using them as a metric
 * dimension, and do not trust them as an identity.
 *
 * @property ocppId the OCPP id of the charging station, as received by the handler once a SOAP message is parsed, and
 * from the URL until then
 * @property ocppVersion the OCPP version as a WebSocket subprotocol name, for instance `ocpp1.6`
 * @property errorCode the OCPP error code answered to the charging station, if any: never set over SOAP, which answers
 * with an HTTP status
 */
data class OcppErrorEvent(
    val category: OcppErrorCategory,
    val ocppId: String? = null,
    val ocppVersion: String? = null,
    val action: String? = null,
    val messageId: String? = null,
    val errorCode: MessageErrorCode? = null,
    val cause: Throwable? = null
)

/**
 * Observes the failures of the toolkit while processing OCPP messages. It cannot change how they are handled: the
 * answer sent to the charging station is the same with and without a listener.
 *
 * It is called concurrently, possibly on the server's I/O thread, and must not block: a blocked call delays every
 * charging station served by that thread. Hand the event off to your own executor. Anything it throws is logged and
 * ignored.
 */
fun interface OcppErrorListener {
    fun onError(event: OcppErrorEvent)
}

/**
 * Notifies this listener of [event]: a failing listener cannot disturb the caller. Call it with `?.`, which builds no
 * event without a listener.
 */
fun OcppErrorListener.notifySafely(event: OcppErrorEvent) {
    try {
        onError(event)
    } catch (t: Throwable) {
        // Throwable on purpose: a monitoring library missing at runtime fails with a NoClassDefFoundError
        logger.error("OCPP error listener failed on ${event.category}", t)
    }
}
