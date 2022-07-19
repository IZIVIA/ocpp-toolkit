package com.izivia.ocpp.soap15

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import com.izivia.ocpp.soap.BaseResolver


@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "s:Envelope")
internal data class SoapEnvelopeOut<T>(
    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:s")
    val soap: String = "http://www.w3.org/2003/05/soap-envelope",
    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:a")
    val addr: String = "http://www.w3.org/2005/08/addressing",
    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:o")
    val ocpp: String,
    @JsonProperty("s:Header")
    val header: SoapHeaderOut,
    @JsonProperty("s:Body")
    @JsonTypeInfo(include= JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM)
    @JsonTypeIdResolver(BaseResolver::class)
    val body: T
)

@JsonIgnoreProperties(ignoreUnknown = true)
internal data class SoapHeaderOut(
    @JsonProperty("a:MessageID")
    val messageId: String,
    @JsonProperty("a:Action")
    val action: String,
    @JsonProperty("a:chargeBoxIdentity")
    val chargeBoxIdentity: String?,
    @JsonProperty("a:From")
    val from: SoapHeaderFromOut?,
    @JsonProperty("a:To")
    val to: String?,
    @JsonProperty("a:RelatesTo")
    val relatesTo: String?
)

internal data class SoapHeaderFromOut(
    @JsonProperty("a:Address")
    val address: String
)
