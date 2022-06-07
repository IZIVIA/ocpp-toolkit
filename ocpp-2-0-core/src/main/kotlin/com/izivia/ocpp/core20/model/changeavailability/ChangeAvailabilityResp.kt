package com.izivia.ocpp.core20.model.changeavailability

import com.izivia.ocpp.core20.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType
import com.izivia.ocpp.core20.model.common.StatusInfoType

data class ChangeAvailabilityResp(
    val status: ChangeAvailabilityStatusEnumType,
    val statusInfo: StatusInfoType? = null
)