package fr.simatix.cs.simulator.api.model.getlog

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.getlog.enumeration.LogEnumType

data class GetLogReq(
    val requestId: Int,
    val logType: LogEnumType,
    val log: LogParametersType,
    val retries: Int? = null,
    val retryInterval: Int? = null
): Request