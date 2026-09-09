package com.izivia.ocpp.json12

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.izivia.ocpp.core12.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.json.OcppJsonMapper

internal object Ocpp12JsonObjectMapper : ObjectMapper(
    OcppJsonMapper()
        .addMixIn(ChangeConfigurationReq::class.java, ChangeConfigurationReqMixin::class.java)
)

private abstract class ChangeConfigurationReqMixin {
    @get:JsonInclude(JsonInclude.Include.ALWAYS)
    abstract val value: String
}
