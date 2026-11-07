package com.izivia.ocpp.websocket

import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.json.OcppJsonParser
import com.izivia.ocpp.json12.Ocpp12JsonParser
import com.izivia.ocpp.json15.Ocpp15JsonParser
import com.izivia.ocpp.json16.Ocpp16JsonParser
import com.izivia.ocpp.json20.Ocpp20JsonParser
import com.izivia.ocpp.transport.OcppCallErrorPayload
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.utils.OcppErrorCategory
import com.izivia.ocpp.utils.OcppErrorEvent
import com.izivia.ocpp.utils.OcppErrorListener
import com.izivia.ocpp.utils.notifySafely
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import mu.KLogger
import kotlin.reflect.KClass

// Exhaustive on purpose: a new OCPP version must fail to compile here rather than at runtime.
internal fun getJsonMapper(ocppVersion: OcppVersion) = when (ocppVersion) {
    OcppVersion.OCPP_1_2 -> Ocpp12JsonParser()
    OcppVersion.OCPP_1_5 -> Ocpp15JsonParser()
    OcppVersion.OCPP_1_6 -> Ocpp16JsonParser()
    OcppVersion.OCPP_2_0 -> Ocpp20JsonParser()
}

internal fun OcppCallErrorPayload.toJson(parser: OcppJsonParser) = parser.mapPayloadToString(this.exception)

/**
 * Answers the incoming [msg] with what [handle] returns, or with a CALLERROR reported to [errorListener] when its
 * payload cannot be parsed, [handle] throws, or the response cannot be serialized.
 */
internal fun <T : Any> answerCall(
    parser: OcppJsonParser,
    meta: WampMessageMeta,
    msg: WampMessage,
    clazz: KClass<T>,
    errorListener: OcppErrorListener?,
    logger: KLogger,
    handle: (T) -> Any?
): WampMessage {
    var failureCategory = OcppErrorCategory.PROTOCOL_ERROR
    return try {
        val request = parser.parsePayloadFromJson(msg.payload, clazz)
        failureCategory = OcppErrorCategory.HANDLER_ERROR
        val response = handle(request)
        failureCategory = OcppErrorCategory.PROTOCOL_ERROR
        WampMessage.CallResult(msg.msgId, parser.mapPayloadToString(response))
    } catch (e: Exception) { // TODO Better mapping of exceptions https://izivia.atlassian.net/browse/IDEV-497
        logger.error(e.message)
        errorListener?.notifySafely(
            OcppErrorEvent(
                failureCategory,
                meta.ocppId,
                meta.ocppVersion.subprotocol,
                msg.action,
                msg.msgId,
                MessageErrorCode.INTERNAL_ERROR,
                e
            )
        )
        WampMessage.CallError(
            msgId = msg.msgId,
            errorCode = MessageErrorCode.INTERNAL_ERROR,
            errorDescription = "",
            payload = OcppCallErrorPayload(e.message).toJson(parser)
        )
    }
}
