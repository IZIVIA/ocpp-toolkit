package fr.simatix.cs.simulator.core20.model.remotestart

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.common.enumeration.RequestStartStopStatusEnumType

data class RequestStartTransactionResp(
    val status: RequestStartStopStatusEnumType,
    val transactionId: String? = null,
    val statusInfo: StatusInfoType? = null
)