package fr.simatix.cs.simulator.api.model.reservenow

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.reservenow.enumeration.ReserveNowStatusEnumType

data class ReserveNowResp(
    val status: ReserveNowStatusEnumType,
    val statusInfo: StatusInfoType
): Response