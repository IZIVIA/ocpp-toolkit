package fr.simatix.cs.simulator.core20.model.changeavailability

import fr.simatix.cs.simulator.core20.model.changeavailability.enumeration.OperationalStatusEnumType
import fr.simatix.cs.simulator.core20.model.common.EVSEType

data class ChangeAvailabilityReq(
    val operationalStatus: OperationalStatusEnumType,
    val evse: EVSEType? = null
)