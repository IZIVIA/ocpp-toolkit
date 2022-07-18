package com.izivia.ocpp.json

import com.fasterxml.jackson.databind.ObjectMapper
import kotlin.reflect.KClass

abstract class OcppJsonParser(private val mapper: ObjectMapper) {

    protected abstract fun getRequestPayloadClass(action: String, context: String): Class<out Any>

    fun parseAnyRequestFromJson(messageStr: String): JsonMessage<Any> {
        val parsed = parseAsStringPayloadFromJson(messageStr)
            ?: throw IllegalArgumentException("Impossible parsing of message. message = $messageStr")

        if (parsed.action == null)
            throw IllegalArgumentException("The message action must not be null. message = $messageStr")

        val clazz = getRequestPayloadClass(parsed.action, messageStr)

        return JsonMessage(
            msgType = parsed.msgType,
            msgId = parsed.msgId,
            action = parsed.action,
            payload = mapper.readValue(parsed.payload, clazz)
        )
    }

    fun <T : Any> parseFromJson(messageStr: String, clazz: KClass<T>): JsonMessage<T> {
        val parsed = parseAsStringPayloadFromJson(messageStr)
            ?: throw IllegalArgumentException("Impossible parsing of message. message = $messageStr")

        return JsonMessage(
            msgType = parsed.msgType,
            msgId = parsed.msgId,
            action = parsed.action,
            payload = mapper.readValue(parsed.payload, clazz.java)
        )
    }

    fun parseAsStringPayloadFromJson(messageStr: String): JsonMessage<String>? =
        ocppMsgRegex.matchEntire(messageStr)?.let {
            val (msgType, msgId, action, payload) = it.destructured
            JsonMessage(
                msgType = JsonMessageType.fromId(msgType.toInt()),
                msgId = msgId,
                action = action,
                payload = payload
            )
        }

    fun <T> mapPayloadToString(payload: T): String =
        mapper.writeValueAsString(payload)

    fun <T> mapToJson(message: JsonMessage<T>): String =
        """
            [
                ${message.msgType.id},
                "${message.msgId}",
                ${message.action?.let { "\"$it\", " } ?: ""}
                ${mapPayloadToString(message.payload)}
            ]
        """.trimIndent()

    companion object {
        private val ocppMsgRegex = Regex("""\[\s*(\d+)\s*,\s*"([^"]+)"\s*(?:,\s*"([^"]+)"\s*)?,\s*(.+)]""")
    }
}

inline fun <reified T : Any> OcppJsonParser.parseFromJson(messageStr: String): JsonMessage<T> =
    parseFromJson(messageStr, T::class)
