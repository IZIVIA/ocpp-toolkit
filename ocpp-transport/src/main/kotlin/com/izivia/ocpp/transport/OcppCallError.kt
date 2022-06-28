package com.izivia.ocpp.transport

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

data class OcppCallErrorException(
    val payload: String
) : Exception(payload)

data class OcppCallErrorPayload(
    val exception: String?
){
    private val mapper = jacksonObjectMapper()

    fun toJson() = mapper.writeValueAsString(this.exception)
}