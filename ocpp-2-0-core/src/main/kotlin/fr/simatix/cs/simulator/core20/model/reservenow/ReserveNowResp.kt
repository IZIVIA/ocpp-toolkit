package fr.simatix.cs.simulator.core20.model.reservenow

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.reservenow.enumeration.ReserveNowStatusEnumType

data class ReserveNowResp(
    val status: ReserveNowStatusEnumType,
    val statusInfo: StatusInfoType? =null
)