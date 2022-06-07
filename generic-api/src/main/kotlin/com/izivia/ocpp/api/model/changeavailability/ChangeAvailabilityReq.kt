package com.izivia.ocpp.api.model.changeavailability

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.changeavailability.enumeration.OperationalStatusEnumType
import com.izivia.ocpp.api.model.common.EVSEType

data class ChangeAvailabilityReq(
    val operationalStatus: OperationalStatusEnumType,
    val evse: EVSEType? = null
): Request