package fr.simatix.cs.simulator.core16.model.datatransfer

data class DataTransferReq(
    val vendorId: String,
    val messageId: String? = null,
    val data: String? = null
)