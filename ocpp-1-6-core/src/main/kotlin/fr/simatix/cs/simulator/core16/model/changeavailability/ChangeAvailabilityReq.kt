package fr.simatix.cs.simulator.core16.model.changeavailability

import fr.simatix.cs.simulator.core16.model.changeavailability.enumeration.AvailabilityType

data class ChangeAvailabilityReq(
    val connectorId: Int,
    val type: AvailabilityType
)