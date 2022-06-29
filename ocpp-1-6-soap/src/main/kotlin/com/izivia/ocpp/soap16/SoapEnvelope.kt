package com.izivia.ocpp.soap16

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationReq

@JsonIgnoreProperties(ignoreUnknown = true)
data class SoapEnvelope(
    @JsonProperty("Header")
    val header: SoapHeader,
    @JsonProperty("Body")
    val body: SoapBody
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class SoapHeader(
    val chargeBoxIdentity: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class SoapBody(
    val statusNotificationRequest: StatusNotificationReq?,
    val heartbeatRequest: HeartbeatReq?
)