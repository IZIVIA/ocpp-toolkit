package com.izivia.ocpp.json20

import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.ObjectMapper
import com.izivia.ocpp.core20.model.common.enumeration.MeasurandEnumType
import com.izivia.ocpp.core20.model.common.enumeration.ReadingContextEnumType
import com.izivia.ocpp.core20.model.notifyreport.enumeration.DataEnumType
import com.izivia.ocpp.json.OcppJsonMapper

object Ocpp20JsonObjectMapper : ObjectMapper(
    OcppJsonMapper
        .addMixIn(MeasurandEnumType::class.java, EnumMixin::class.java)
        .addMixIn(ReadingContextEnumType::class.java, EnumMixin::class.java)
        .addMixIn(DataEnumType::class.java, EnumMixin::class.java)
)

private abstract class EnumMixin(
    @JsonValue val value: String
)
