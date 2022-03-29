package fr.simatix.cs.simulator.api.model.remotestop

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.common.enumeration.RequestStartStopStatusEnumType

data class RequestStopTransactionResp(
    val status: RequestStartStopStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response