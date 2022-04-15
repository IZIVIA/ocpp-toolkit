package fr.simatix.cs.simulator.core20.model.logstatusnotification

import fr.simatix.cs.simulator.core20.model.logstatusnotification.enumeration.UploadLogStatusEnumType

class LogStatusNotificationReq(
    val status: UploadLogStatusEnumType,
    val requestId: Int? = null
)