package fr.simatix.cs.simulator.core20.model.datatransfer

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.datatransfer.enumeration.DataTransferStatusEnumType

data class DataTransferResp(
    val status: DataTransferStatusEnumType,
    val data: Any? = null,
    val statusInfo: StatusInfoType? = null
)