package fr.simatix.cs.simulator.api.model.reset

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.reset.enumeration.ResetEnumType

data class ResetReq(
    val type: ResetEnumType,
    val evseId: Int? = null
): Request