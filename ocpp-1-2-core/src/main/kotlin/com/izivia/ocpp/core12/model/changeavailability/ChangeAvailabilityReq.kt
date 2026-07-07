package com.izivia.ocpp.core12.model.changeavailability

import com.izivia.ocpp.core12.model.Request
import com.izivia.ocpp.core12.model.changeavailability.enumeration.AvailabilityType

data class ChangeAvailabilityReq(
    val connectorId: Int,
    val type: AvailabilityType
): Request
