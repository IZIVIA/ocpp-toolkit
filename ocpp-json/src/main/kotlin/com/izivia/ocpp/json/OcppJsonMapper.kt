package com.izivia.ocpp.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.izivia.ocpp.utils.KotlinInstantModule

class OcppJsonMapper : ObjectMapper(
    jacksonObjectMapper()
        .registerModule(KotlinInstantModule())
        .setDefaultPropertyInclusion(JsonInclude.Include.NON_EMPTY)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
)
