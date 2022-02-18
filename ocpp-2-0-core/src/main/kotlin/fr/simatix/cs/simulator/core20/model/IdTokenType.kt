package fr.simatix.cs.simulator.core20.model

import fr.simatix.cs.simulator.core20.model.enumeration.IdTokenEnumType

data class IdTokenType (
    val idToken: String,
    val type: IdTokenEnumType,
    val additionalInfo: List<AdditionalInfoType>? = null
)
