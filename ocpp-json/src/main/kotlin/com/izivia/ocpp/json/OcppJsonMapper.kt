package com.izivia.ocpp.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.izivia.ocpp.utils.KotlinxInstantModule

object OcppJsonMapper : ObjectMapper(
    jacksonObjectMapper()
        .registerModule(KotlinxInstantModule())
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
)
