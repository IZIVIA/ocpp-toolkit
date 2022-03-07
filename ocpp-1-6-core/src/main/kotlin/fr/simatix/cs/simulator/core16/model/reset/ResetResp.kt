package fr.simatix.cs.simulator.core16.model.reset

import fr.simatix.cs.simulator.core16.model.reset.enumeration.ResetStatus

data class ResetResp(
    val status: ResetStatus
)