package fr.simatix.cs.simulator.api.model.bootnotification

import fr.simatix.cs.simulator.api.model.bootnotification.enumeration.RegistrationStatusEnumType
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import kotlinx.datetime.Instant

data class BootNotificationResp(
    val currentTime: Instant,
    val interval: Int,
    val status: RegistrationStatusEnumType,
    val statusInfo: StatusInfoType? = null
)