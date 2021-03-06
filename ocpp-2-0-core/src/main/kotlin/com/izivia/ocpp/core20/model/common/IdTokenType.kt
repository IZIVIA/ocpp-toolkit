package com.izivia.ocpp.core20.model.common

import com.izivia.ocpp.core20.model.common.enumeration.IdTokenEnumType

data class IdTokenType (
    val idToken: String,
    val type: IdTokenEnumType,
    val additionalInfo: List<AdditionalInfoType>? = null
)
