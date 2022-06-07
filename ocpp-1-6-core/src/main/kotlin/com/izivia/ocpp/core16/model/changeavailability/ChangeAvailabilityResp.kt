package com.izivia.ocpp.core16.model.changeavailability

import com.izivia.ocpp.core16.model.changeavailability.enumeration.AvailabilityStatus

data class ChangeAvailabilityResp(
    val status: AvailabilityStatus
)