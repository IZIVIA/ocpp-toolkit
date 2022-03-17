package fr.simatix.cs.simulator.core16.model.reset

import fr.simatix.cs.simulator.core16.model.reset.enumeration.ResetType

data class ResetReq(
    val type: ResetType
)