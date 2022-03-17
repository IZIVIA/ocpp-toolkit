package fr.simatix.cs.simulator.core16.model.changeavailability

import fr.simatix.cs.simulator.core16.model.changeavailability.enumeration.AvailabilityStatus

data class ChangeAvailabilityResp(
    val status: AvailabilityStatus
)