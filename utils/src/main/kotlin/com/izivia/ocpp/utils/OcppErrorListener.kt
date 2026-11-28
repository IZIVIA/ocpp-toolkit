package com.izivia.ocpp.utils

import org.slf4j.LoggerFactory
import java.util.Objects

private val logger = LoggerFactory.getLogger("com.izivia.ocpp.utils.OcppErrorListener")

/**
 * The kind of a failure. Later versions may add categories: keep an `else` branch when matching them.
 */
enum class OcppErrorCategory {
    /** An incoming message has no registered handler. */
    UNHANDLED_ACTION,

    /** An incoming message or payload cannot be parsed, or an outgoing payload cannot be serialized. */
    PROTOCOL_ERROR,

    /**
     * A registered handler threw while processing an incoming message, an [Error] included. Through `WebsocketServer`,
     * an [Error] thrown while parsing the request or serializing the response lands here too.
     */
    HANDLER_ERROR,

    /**
     * The transport failed while processing a message, for instance when the calls executor does not run a call.
     * A network failure, such as a response that cannot be sent to a disconnected charging station, is not reported:
     * the disconnection already is.
     */
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
 * The toolkit builds the events, and later versions may add properties: this is not a data class, so that adding one
 * breaks no `copy` nor destructuring declaration.
 *
 * @property ocppId the OCPP id of the charging station, as received by the handler once a SOAP message is parsed, and
 * from the URL until then
 * @property ocppVersion the OCPP version as a WebSocket subprotocol name, for instance `ocpp1.6`
 * @property errorCode the OCPP error code answered to the charging station, if any: never set over SOAP, which answers
 * with an HTTP status
 */
class OcppErrorEvent(
    val category: OcppErrorCategory,
    val ocppId: String? = null,
    val ocppVersion: String? = null,
    val action: String? = null,
    val messageId: String? = null,
    val errorCode: MessageErrorCode? = null,
    val cause: Throwable? = null
) {
    override fun equals(other: Any?): Boolean =
        this === other || other is OcppErrorEvent &&
            category == other.category &&
            ocppId == other.ocppId &&
            ocppVersion == other.ocppVersion &&
            action == other.action &&
            messageId == other.messageId &&
            errorCode == other.errorCode &&
            cause == other.cause

    override fun hashCode(): Int = Objects.hash(category, ocppId, ocppVersion, action, messageId, errorCode, cause)

    override fun toString(): String =
        "OcppErrorEvent(category=$category, ocppId=$ocppId, ocppVersion=$ocppVersion, action=$action, " +
            "messageId=$messageId, errorCode=$errorCode, cause=$cause)"
}

/**
 * Observes the failures of the toolkit while processing OCPP messages. It cannot change how they are handled: the
 * answer sent to the charging station is the same with and without a listener.
 *
 * The [OcppErrorEvent.cause] it receives is the original exception, whose message the toolkit's own logs leave
 * out: that message, and those of its causes, may quote a payload fragment, an id tag or a credential, and so may
 * [OcppErrorEvent.toString], which renders it. Sanitize the cause before reporting it to an external system, such as
 * an error tracker or a metrics backend: [redactedForLog] gives a copy of it without any message.
 *
 * It is called concurrently, possibly on the server's I/O thread, and must not block: a blocked call delays every
 * charging station served by that thread. Hand the event off to your own executor. Anything it throws is logged, by
 * its class and stack trace without any message, and ignored.
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
        // a listener may wrap event.cause, whose message may quote a payload: only the classes are logged
        logger.error("OCPP error listener failed on ${event.category}: ${t.javaClass.name}", t.redactedForLog())
    }
}
