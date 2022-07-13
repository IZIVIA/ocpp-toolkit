package com.izivia.ocpp.websocket

import com.fasterxml.jackson.databind.ObjectMapper
import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.json16.Ocpp16JsonObjectMapper
import com.izivia.ocpp.json20.Ocpp20JsonObjectMapper
import com.izivia.ocpp.transport.OcppCallErrorPayload

internal fun getJsonMapper(ocppVersion: OcppVersion) = when (ocppVersion) {
    OcppVersion.OCPP_1_6 -> Ocpp16JsonObjectMapper
    OcppVersion.OCPP_2_0 -> Ocpp20JsonObjectMapper
    else -> throw IllegalStateException("Websocket transport is not supported by the ocpp version 1.5")
}

internal fun OcppCallErrorPayload.toJson(mapper: ObjectMapper) = mapper.writeValueAsString(this.exception)
