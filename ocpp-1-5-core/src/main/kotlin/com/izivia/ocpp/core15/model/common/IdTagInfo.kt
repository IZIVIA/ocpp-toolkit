package com.izivia.ocpp.core15.model.common

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.izivia.ocpp.core15.model.common.enumeration.AuthorisationStatus
import com.izivia.ocpp.utils.InstantDeserializer
import com.izivia.ocpp.utils.InstantSerializer
import kotlinx.datetime.Instant

data class IdTagInfo(
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val expiryDate: Instant? = null,
    val parentIdTag: String? = null,
    val status: AuthorisationStatus
)
