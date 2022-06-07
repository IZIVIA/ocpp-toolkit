package com.izivia.ocpp.core16.model.reset

import com.izivia.ocpp.core16.model.reset.enumeration.ResetType

data class ResetReq(
    val type: ResetType
)