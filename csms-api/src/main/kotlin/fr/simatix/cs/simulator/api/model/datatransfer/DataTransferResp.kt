package fr.simatix.cs.simulator.api.model.datatransfer

import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.datatransfer.enumeration.DataTransferStatusEnumType

data class DataTransferResp(
    val status: DataTransferStatusEnumType,
    val data: Any? = null,
    val statusInfo: StatusInfoType? = null
)