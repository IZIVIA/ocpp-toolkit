package com.izivia.ocpp.core20.model.changeavailability

import com.izivia.ocpp.core20.model.changeavailability.enumeration.OperationalStatusEnumType
import com.izivia.ocpp.core20.model.common.EVSEType

data class ChangeAvailabilityReq(
    val operationalStatus: OperationalStatusEnumType,
    val evse: EVSEType? = null
)