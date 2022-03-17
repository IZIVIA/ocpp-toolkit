package fr.simatix.cs.simulator.core20.model.reset

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.reset.enumeration.ResetStatusEnumType

data class ResetResp(
    val status: ResetStatusEnumType,
    val statusInfo: StatusInfoType? = null
)