package fr.simatix.cs.simulator.core20.model.reset

import fr.simatix.cs.simulator.core20.model.reset.enumeration.ResetEnumType

data class ResetReq(
    val type: ResetEnumType,
    val evseId: Int? = null
)