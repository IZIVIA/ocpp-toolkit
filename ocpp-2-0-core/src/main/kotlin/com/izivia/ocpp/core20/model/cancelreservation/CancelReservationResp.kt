package com.izivia.ocpp.core20.model.cancelreservation

import com.izivia.ocpp.core20.model.cancelreservation.enumeration.CancelReservationStatusEnumType
import com.izivia.ocpp.core20.model.common.StatusInfoType

data class CancelReservationResp(
    val status: CancelReservationStatusEnumType,
    val statusInfo: StatusInfoType? = null
)