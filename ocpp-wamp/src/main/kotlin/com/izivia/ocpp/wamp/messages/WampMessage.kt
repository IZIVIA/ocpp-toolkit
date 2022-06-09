package com.izivia.ocpp.wamp.messages

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion
import org.http4k.core.UriTemplate

data class WampMessage(val msgType:WampMessageType,val msgId:String,val action:String?,val payload:String) {
    fun toJson() = """[${msgType.id},"$msgId",${action?.let { "\"$it\", " }?:""}$payload]"""

    companion object {
        fun Call(msgId:String, action:String, payload: String) = WampMessage(WampMessageType.CALL, msgId, action, payload)
        fun CallResult(msgId:String, payload: String) = WampMessage(WampMessageType.CALL_RESULT, msgId, null, payload)
        fun CallError(msgId:String, payload: String) = WampMessage(WampMessageType.CALL_ERROR, msgId, null, payload)

        fun parse(str:String) = WampMessageParser.parse(str)
    }
}

object WampMessageParser {
    private val ocppMsgRegex = Regex("""\[\s*(\d+)\s*,\s*"([^"]+)"\s*(?:,\s*"([^"]+)"\s*)?,\s*(.+)]""")

    fun parse(string: String): WampMessage? =
        ocppMsgRegex.matchEntire(string)?.let {
            val (msgType, msgId, action, payload) = it.destructured
            WampMessage(WampMessageType.fromId(msgType.toInt()), msgId, action, payload)
        }
}

enum class WampMessageType(val id:Int) {
    CALL(2), CALL_RESULT(3), CALL_ERROR(4);

    companion object {
        fun fromId(id:Int) = values().filter { it.id == id }.firstOrNull()
            ?:throw IllegalArgumentException(
                "$id is not a valid Wamp message type - supported types: ${values().map { it.id }}")
    }
}

data class WampMessageMeta(val ocppVersion: OcppVersion, val ocppId: CSOcppId)
