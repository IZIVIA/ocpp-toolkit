package fr.simatix.cs.simulator.api.model.datatransfer

import fr.simatix.cs.simulator.api.model.Request

data class DataTransferReq(
    val vendorId: String,
    val messageId: String? = null,
    val data: Any? = null,
): Request