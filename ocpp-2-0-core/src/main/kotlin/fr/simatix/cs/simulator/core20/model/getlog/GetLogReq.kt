package fr.simatix.cs.simulator.core20.model.getlog

import fr.simatix.cs.simulator.core20.model.getlog.enumeration.LogEnumType

data class GetLogReq(
    val requestId: Int,
    val logType: LogEnumType,
    val log: LogParametersType,
    val retries: Int? = null,
    val retryInterval: Int? = null
)