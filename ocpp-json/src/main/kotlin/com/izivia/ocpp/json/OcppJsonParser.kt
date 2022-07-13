package com.izivia.ocpp.json

import kotlin.reflect.KClass

abstract class OcppJsonParser {

    abstract fun parseAnyRequestFromJson(messageStr: String): JsonMessage<Any>
    abstract fun <T : Any> parseFromJson(messageStr: String, clazz: KClass<T>): JsonMessage<T>

    protected fun parseAsString(messageStr: String): JsonMessage<String>? =
        ocppMsgRegex.matchEntire(messageStr)?.let {
            val (msgType, msgId, action, payload) = it.destructured
            JsonMessage(
                msgType = JsonMessageType.fromId(msgType.toInt()),
                msgId = msgId,
                action = action,
                payload = payload
            )
        }

    companion object {
        private val ocppMsgRegex = Regex("""\[\s*(\d+)\s*,\s*"([^"]+)"\s*(?:,\s*"([^"]+)"\s*)?,\s*(.+)]""")
    }
}

inline fun <reified T : Any> OcppJsonParser.parse(messageStr: String): JsonMessage<T> =
    parseFromJson(messageStr, T::class)
