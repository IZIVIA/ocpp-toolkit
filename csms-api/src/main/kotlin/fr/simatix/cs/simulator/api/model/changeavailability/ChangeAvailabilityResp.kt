package fr.simatix.cs.simulator.api.model.changeavailability

import fr.simatix.cs.simulator.api.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType
import fr.simatix.cs.simulator.api.model.common.StatusInfoType

data class ChangeAvailabilityResp(
    val status: ChangeAvailabilityStatusEnumType,
    val statusInfo: StatusInfoType? = null
)