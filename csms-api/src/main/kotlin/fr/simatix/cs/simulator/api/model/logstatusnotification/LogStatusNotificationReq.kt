package fr.simatix.cs.simulator.api.model.logstatusnotification

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.logstatusnotification.enumeration.UploadLogStatusEnumType

class LogStatusNotificationReq(
    val status: UploadLogStatusEnumType,
    val requestId: Int? = null
): Request