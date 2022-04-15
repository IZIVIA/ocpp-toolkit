package fr.simatix.cs.simulator.core20.model.getlog

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.getlog.enumeration.LogStatusEnumType

data class GetLogResp(
    val status: LogStatusEnumType,
    val filename: String? = null,
    val statusInfo: StatusInfoType? = null
)