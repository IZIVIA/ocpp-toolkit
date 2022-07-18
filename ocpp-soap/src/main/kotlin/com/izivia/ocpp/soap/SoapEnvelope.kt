package com.izivia.ocpp.soap

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class SoapEnvelope<T : SoapBody>(
    @JsonProperty("Header")
    val header: SoapHeader,
    @JsonProperty("Body")
    val body: T
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class SoapHeader(
    @JsonProperty("MessageID")
    val messageId: String,
    @JsonProperty("Action")
    val action: String,
    val chargeBoxIdentity: String?,
    @JsonProperty("From")
    val from: SoapHeaderFrom?,
    @JsonProperty("To")
    val to: String?,
    @JsonProperty("RelatesTo")
    val relatesTo: String?
)

data class SoapHeaderFrom(
    @JsonProperty("Address")
    val address: String
)

interface SoapBody
