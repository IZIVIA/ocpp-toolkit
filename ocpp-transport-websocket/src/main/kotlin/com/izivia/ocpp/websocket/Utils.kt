package com.izivia.ocpp.websocket

import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.json.OcppJsonParser
import com.izivia.ocpp.json15.Ocpp15JsonParser
import com.izivia.ocpp.json16.Ocpp16JsonParser
import com.izivia.ocpp.json20.Ocpp20JsonParser
import com.izivia.ocpp.transport.OcppCallErrorPayload

internal fun getJsonMapper(ocppVersion: OcppVersion) = when (ocppVersion) {
    OcppVersion.OCPP_1_5 -> Ocpp15JsonParser()
    OcppVersion.OCPP_1_6 -> Ocpp16JsonParser()
    OcppVersion.OCPP_2_0 -> Ocpp20JsonParser()
    else -> throw IllegalStateException("Websocket transport is not supported by the ocpp version 1.5")
}

internal fun OcppCallErrorPayload.toJson(parser: OcppJsonParser) = parser.mapPayloadToString(this.exception)
