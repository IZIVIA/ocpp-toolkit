package com.izivia.ocpp.api.model.cancelreservation

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.cancelreservation.enumeration.CancelReservationStatusEnumType
import com.izivia.ocpp.api.model.common.StatusInfoType

data class CancelReservationResp(
    val status: CancelReservationStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response