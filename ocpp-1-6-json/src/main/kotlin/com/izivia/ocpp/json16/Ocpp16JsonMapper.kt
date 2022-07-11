package com.izivia.ocpp.json16

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.izivia.ocpp.core16.model.common.enumeration.Measurand
import com.izivia.ocpp.core16.model.common.enumeration.Phase
import com.izivia.ocpp.core16.model.common.enumeration.ReadingContext
import com.izivia.ocpp.utils.KotlinxInstantModule

object Ocpp16JsonMapper : ObjectMapper(
    jacksonObjectMapper()
        .registerModule(KotlinxInstantModule())
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .addMixIn(Measurand::class.java, EnumMixin::class.java)
        .addMixIn(ReadingContext::class.java, EnumMixin::class.java)
        .addMixIn(Phase::class.java, EnumMixin::class.java)
)

abstract class EnumMixin(
    @JsonValue val value: String
)
