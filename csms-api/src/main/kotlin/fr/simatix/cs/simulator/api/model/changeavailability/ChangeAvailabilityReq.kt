package fr.simatix.cs.simulator.api.model.changeavailability

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.changeavailability.enumeration.OperationalStatusEnumType
import fr.simatix.cs.simulator.api.model.common.EVSEType

data class ChangeAvailabilityReq(
    val operationalStatus: OperationalStatusEnumType,
    val evse: EVSEType? = null
): Request