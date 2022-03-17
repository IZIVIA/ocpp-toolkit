package fr.simatix.cs.simulator.api.model.reset

import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.reset.enumeration.ResetStatusEnumType

data class ResetResp(
    val status: ResetStatusEnumType,
    val statusInfo: StatusInfoType? = null
)