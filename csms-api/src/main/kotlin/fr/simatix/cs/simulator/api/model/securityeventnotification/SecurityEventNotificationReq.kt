package fr.simatix.cs.simulator.api.model.securityeventnotification

import fr.simatix.cs.simulator.api.model.Request
import kotlinx.datetime.Instant

data class SecurityEventNotificationReq(
    val type: String,
    val timestamp: Instant,
    val techInfo: String? = null
) : Request