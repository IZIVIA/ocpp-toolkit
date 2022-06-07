package com.izivia.ocpp.api.model.changeavailability

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType
import com.izivia.ocpp.api.model.common.StatusInfoType

data class ChangeAvailabilityResp(
    val status: ChangeAvailabilityStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response