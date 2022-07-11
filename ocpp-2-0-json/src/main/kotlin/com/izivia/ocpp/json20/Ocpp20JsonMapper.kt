package com.izivia.ocpp.json20

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.izivia.ocpp.core20.model.common.enumeration.MeasurandEnumType
import com.izivia.ocpp.core20.model.common.enumeration.ReadingContextEnumType
import com.izivia.ocpp.core20.model.notifyreport.enumeration.DataEnumType
import com.izivia.ocpp.utils.KotlinxInstantModule

object Ocpp20JsonMapper : ObjectMapper(
    jacksonObjectMapper()
        .registerModule(KotlinxInstantModule())
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .addMixIn(MeasurandEnumType::class.java, EnumMixin::class.java)
        .addMixIn(ReadingContextEnumType::class.java, EnumMixin::class.java)
        .addMixIn(DataEnumType::class.java, EnumMixin::class.java)
)

abstract class EnumMixin(
    @JsonValue val value: String
)
