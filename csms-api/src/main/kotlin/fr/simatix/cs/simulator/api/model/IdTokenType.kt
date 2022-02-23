package fr.simatix.cs.simulator.api.model

import fr.simatix.cs.simulator.api.model.enumeration.IdTokenEnumType

data class IdTokenType (
    val idToken: String,
    val type: IdTokenEnumType,
    val additionalInfo: List<AdditionalInfoType>? = null
)
