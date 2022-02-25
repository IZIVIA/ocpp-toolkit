package fr.simatix.cs.simulator.api.model.common

import fr.simatix.cs.simulator.api.model.common.enumeration.IdTokenEnumType

data class IdTokenType (
    val idToken: String,
    val type: IdTokenEnumType,
    val additionalInfo: List<AdditionalInfoType>? = null
)
