package com.izivia.ocpp.core16.model.changeavailability

import com.izivia.ocpp.core16.model.changeavailability.enumeration.AvailabilityType

data class ChangeAvailabilityReq(
    val connectorId: Int,
    val type: AvailabilityType
)