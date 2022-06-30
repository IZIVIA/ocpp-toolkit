package com.izivia.ocpp.core15.model.changeavailability

import com.izivia.ocpp.core15.model.changeavailability.enumeration.AvailabilityStatus

data class ChangeAvailabilityResp(
    val status: AvailabilityStatus
)