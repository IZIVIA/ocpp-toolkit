package fr.simatix.cs.simulator.core20.model.changeavailability

import fr.simatix.cs.simulator.core20.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType
import fr.simatix.cs.simulator.core20.model.common.StatusInfoType

data class ChangeAvailabilityResp(
    val status: ChangeAvailabilityStatusEnumType,
    val statusInfo: StatusInfoType? = null
)