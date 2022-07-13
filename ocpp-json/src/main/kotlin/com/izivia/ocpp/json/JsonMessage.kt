package com.izivia.ocpp.json

data class JsonMessage<T>(
    val msgType: JsonMessageType,
    val msgId: String,
    val action: String?,
    val payload: T
)

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
