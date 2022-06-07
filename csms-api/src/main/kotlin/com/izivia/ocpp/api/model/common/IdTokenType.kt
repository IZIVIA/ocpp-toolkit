package com.izivia.ocpp.api.model.common

import com.izivia.ocpp.api.model.common.enumeration.IdTokenEnumType

data class IdTokenType (
    val idToken: String,
    val type: IdTokenEnumType,
    val additionalInfo: List<AdditionalInfoType>? = null
)

fun String.getTypeByIdToken(): IdTokenEnumType =
    if (this.matches(Regex("[0-9A-Fa-f]*"))) {
        when (this.toByteArray(Charsets.UTF_8).size) {
            0 -> IdTokenEnumType.NoAuthorization
            4 -> IdTokenEnumType.ISO14443
            7 -> IdTokenEnumType.ISO14443
            8 -> IdTokenEnumType.ISO15693
            9 -> IdTokenEnumType.eMAID
            else -> IdTokenEnumType.Central
        }
    } else IdTokenEnumType.Central
