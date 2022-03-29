package fr.simatix.cs.simulator.api.model.remotestart

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.common.enumeration.RequestStartStopStatusEnumType

data class RequestStartTransactionResp(
    val status: RequestStartStopStatusEnumType,
    val transactionId: String? = null,
    val statusInfo: StatusInfoType? = null
): Response