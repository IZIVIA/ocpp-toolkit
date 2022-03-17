package fr.simatix.cs.simulator.core20.model.remotestop

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.common.enumeration.RequestStartStopStatusEnumType

data class RequestStopTransactionResp(
    val status: RequestStartStopStatusEnumType,
    val statusInfo: StatusInfoType? = null
)