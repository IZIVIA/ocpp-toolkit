package com.izivia.ocpp.json

import com.izivia.ocpp.json.JsonMessageType.*

data class JsonMessage<T>(
    val msgType: JsonMessageType,
    val msgId: String,
    val action: String?,
    val payload: T
) {
    companion object {
        fun <T> Call(msgId: String, action: String, payload: T) = JsonMessage(CALL, msgId, action, payload)
        fun <T> CallResult(msgId: String, payload: T) = JsonMessage(CALL_RESULT, msgId, null, payload)
        fun <T> CallError(msgId: String, payload: T) = JsonMessage(CALL_ERROR, msgId, null, payload)
    }
}

enum class JsonMessageType(val id: Int) {
    CALL(2),
    CALL_RESULT(3),
    CALL_ERROR(4);

    companion object {
        fun fromId(id: Int) = values()
            .firstOrNull { it.id == id }
            ?: throw IllegalArgumentException("$id is not a valid json message type - supported types: ${values().map { it.id }}")
    }
}
