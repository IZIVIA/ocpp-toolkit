package com.izivia.ocpp.core12.model.changeavailability

import com.izivia.ocpp.core12.model.Response
import com.izivia.ocpp.core12.model.changeavailability.enumeration.AvailabilityStatus

data class ChangeAvailabilityResp(
    val status: AvailabilityStatus
): Response
