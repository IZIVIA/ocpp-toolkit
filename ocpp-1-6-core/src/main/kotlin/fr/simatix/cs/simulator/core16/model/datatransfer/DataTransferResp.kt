package fr.simatix.cs.simulator.core16.model.datatransfer

import fr.simatix.cs.simulator.core16.model.datatransfer.enumeration.DataTransferStatus

data class DataTransferResp(
    val status: DataTransferStatus,
    val data: String? = null
)