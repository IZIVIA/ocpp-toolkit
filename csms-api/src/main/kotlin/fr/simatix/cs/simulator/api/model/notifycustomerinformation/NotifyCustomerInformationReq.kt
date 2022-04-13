package fr.simatix.cs.simulator.api.model.notifycustomerinformation

import fr.simatix.cs.simulator.api.model.Request
import kotlinx.datetime.Instant

data class NotifyCustomerInformationReq(
    val data: String,
    val tbc: Boolean = false,
    val seqNo: Int,
    val generatedAt: Instant,
    val requestId: Int
): Request