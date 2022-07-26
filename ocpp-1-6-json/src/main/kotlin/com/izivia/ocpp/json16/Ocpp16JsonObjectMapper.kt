package com.izivia.ocpp.json16

import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.ObjectMapper
import com.izivia.ocpp.core16.model.common.enumeration.Measurand
import com.izivia.ocpp.core16.model.common.enumeration.Phase
import com.izivia.ocpp.core16.model.common.enumeration.ReadingContext
import com.izivia.ocpp.json.OcppJsonMapper

internal object Ocpp16JsonObjectMapper : ObjectMapper(
    OcppJsonMapper()
        .addMixIn(Measurand::class.java, EnumMixin::class.java)
        .addMixIn(ReadingContext::class.java, EnumMixin::class.java)
        .addMixIn(Phase::class.java, EnumMixin::class.java)
)

private abstract class EnumMixin(
    @JsonValue val value: String
)
